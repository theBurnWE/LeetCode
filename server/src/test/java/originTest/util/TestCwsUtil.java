package originTest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.net.URI;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Document;

/**
 * @author BrunE
 * @date 2019-03-07 15:58
 **/
public class TestCwsUtil {
    /**
     * Commons logging.
     */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(TestCwsUtil.class);
    /**
     * Default character encoding.
     */
    public static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * Match an asterisk character.
     */
    private static final Pattern asteriskPtn = Pattern.compile("*",
                                                               Pattern.LITERAL);
    /**
     * Match one back-slash.
     */
    private static final Pattern BackSlashPtn = Pattern.compile("\\",
                                                                Pattern.LITERAL);
    /**
     * A cached simple date format for generating time-stamps.
     */
    private static final AtomicReference<DateFormat> dateFormatPool = new AtomicReference<DateFormat>();
    /**
     * Match one equal character.
     */
    private static final Pattern EqualPtn = Pattern.compile("=",
                                                            Pattern.LITERAL);
    /**
     * Escaped back slash character.
     */
    private static final String EscBackSlash = "\\\\";
    /**
     * Escaped equal character.
     */
    private static final String EscEqual = "\\=";
    /**
     * Escaped forward slash character.
     */
    private static final String EscForwardSlash = "\\/";
    /**
     * Escaped left parenthesis character.
     */
    private static final String EscLParen = "\\(";
    /**
     * Escaped right parenthesis character.
     */
    private static final String EscRParen = "\\)";
    /**
     * Escaped semicolon character.
     */
    private static final String EscSemicolon = "\\;";
    /**
     * Match one forward-slash.
     */
    private static final Pattern ForwardSlashPtn = Pattern.compile("/",
                                                                   Pattern.LITERAL);
    /**
     * Match one right parenthesis character.
     */
    private static final Pattern LParenPtn = Pattern.compile("(",
                                                             Pattern.LITERAL);
    /**
     * Match leading and/or trailing white spaces.
     */
    private static final Pattern OuterWhiteSpacesPtn = Pattern.compile("\\A\\s+|\\s+\\z");
    /**
     * Match "%2F".
     */
    private static final Pattern pct2FPtn = Pattern.compile("%2F",
                                                            Pattern.LITERAL);
    /**
     * Match "%7E".
     */
    private static final Pattern pct7EPtn = Pattern.compile("%7E",
                                                            Pattern.LITERAL);
    /**
     * Match a + character.
     */
    private static final Pattern plusPtn = Pattern.compile("+", Pattern.LITERAL);
    /**
     * Match one right parenthesis character.
     */
    private static final Pattern RParenPtn = Pattern.compile(")",
                                                             Pattern.LITERAL);
    /**
     * Match one semicolon character.
     */
    private static final Pattern SemicolonPtn = Pattern.compile(";",
                                                                Pattern.LITERAL);
    /**
     * Match one or more white spaces.
     */
    private static final Pattern WhiteSpacesPtn = Pattern.compile("\\s+");
    /**
     * For creating xml dates.
     */
    private static final ThreadLocal<DatatypeFactory> threadDTF = new ThreadLocal<DatatypeFactory>();

    /**
     * For creating xml documents.
     */
    private static final ThreadLocal<DocumentBuilderFactory> threadDBF = new ThreadLocal<DocumentBuilderFactory>();

    /**
     * Thread local transformer factory.
     */
    private static final ThreadLocal<TransformerFactory> threadTF = new ThreadLocal<TransformerFactory>();

    /**
     * Map from enum classes to hash maps of names.
     */
    private static final ConcurrentHashMap<Object, HashMap<String, Object>> enumMaps = new ConcurrentHashMap<Object, HashMap<String, Object>>();

    /**
     * Hide utility class constructor.
     */
    private TestCwsUtil() {
        // Hidden constructor
    }

    /**
     * Calculate String to Sign for SignatureVersion 0
     *
     * @param parameters request parameters
     * @return String to Sign
     */
    private static String calculateStringToSignV0(Map<String, String> parameters) {
        StringBuilder data = new StringBuilder();
        data.append(parameters.get("Action"))
            .append(parameters.get("Timestamp"));
        return data.toString();
    }

    /**
     * Calculate String to Sign for SignatureVersion 1
     *
     * @param parameters request parameters
     * @return String to Sign
     */
    private static String calculateStringToSignV1(Map<String, String> parameters) {
        StringBuilder data = new StringBuilder();
        Map<String, String> sorted = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
        sorted.putAll(parameters);
        Iterator<Entry<String, String>> pairs = sorted.entrySet().iterator();
        while (pairs.hasNext()) {
            Map.Entry<String, String> pair = pairs.next();
            data.append(pair.getKey());
            data.append(pair.getValue());
        }
        return data.toString();
    }

    /**
     * Calculate String to Sign for SignatureVersion 2
     *
     * @param serviceUri
     * @param parameters request parameters
     * @return String to Sign
     */
    static String calculateStringToSignV2(URI serviceUri,
                                          Map<String, String> parameters) {
        StringBuilder data = new StringBuilder();
        data.append("POST");
        data.append("\n");
        data.append(serviceUri.getHost().toLowerCase());
        if (!usesStandardPort(serviceUri)) {
            data.append(":");
            data.append(serviceUri.getPort());
        }
        data.append("\n");
        String uri = serviceUri.getPath();
        data.append(TestCwsUtil.urlEncode(uri, true));
        data.append("\n");
        Map<String, String> sorted = new TreeMap<String, String>();
        sorted.putAll(parameters);
        Iterator<Map.Entry<String, String>> pairs = sorted.entrySet()
                                                          .iterator();
        while (pairs.hasNext()) {
            Map.Entry<String, String> pair = pairs.next();
            String key = pair.getKey();
            data.append(TestCwsUtil.urlEncode(key, false));
            data.append("=");
            String value = pair.getValue();
            data.append(TestCwsUtil.urlEncode(value, false));
            if (pairs.hasNext()) {
                data.append("&");
            }
        }
        return data.toString();
    }

    /**
     * Clean white space. Remove leading and trailing, and replace internal runs
     * with a single space character.
     *
     * @param s
     * @return The clean string.
     */
    private static String cleanWS(String s) {
        s = replaceAll(s, OuterWhiteSpacesPtn, "");
        s = replaceAll(s, WhiteSpacesPtn, " ");
        return s;
    }

    /**
     * Replace a pattern in a string.
     * <p>
     * Do not do recursive replacement. Return the original string if no changes
     * are required.
     *
     * @param s The string to search.
     * @param p The pattern to search for.
     * @param r The string to replace occurrences with.
     * @return The new string.
     */
    static String replaceAll(String s, Pattern p, String r) {
        int n = s == null ? 0 : s.length();
        if (n == 0) {
            return s;
        }
        Matcher m = p.matcher(s);
        if (!m.find()) {
            return s;
        }
        StringBuilder buf = new StringBuilder(n + 12);
        int k = 0;
        do {
            buf.append(s, k, m.start());
            buf.append(r);
            k = m.end();
        } while (m.find());
        if (k < n) {
            buf.append(s, k, n);
        }
        return buf.toString();
    }

    /**
     * Computes RFC 2104-compliant HMAC signature.
     *
     * @param data      The data to sign.
     * @param key       The key to use for signing.
     * @param algorithm The signing algorithm.
     * @return The signature.
     */
    public static String sign(String data, String key, String algorithm) {
        try {
            String md5Str = "";
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update((data + key).getBytes(TestCwsUtil.DEFAULT_ENCODING));
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            // 32位的加密
            md5Str = buf.toString();
            return md5Str;
        } catch (Exception e) {
            throw TestCwsUtil.wrap(e);
        }
    }

    /**
     * URL encode a value.
     *
     * @param value
     * @param path  true if is a path and '/' should not be encoded.
     * @return The encoded string.
     */
    protected static String urlEncode(String value, boolean path) {
        try {
            value = URLEncoder.encode(value, DEFAULT_ENCODING);
        } catch (Exception e) {
            throw wrap(e);
        }
        value = replaceAll(value, plusPtn, "%20");
        value = replaceAll(value, asteriskPtn, "%2A");
        value = replaceAll(value, pct7EPtn, "~");
        if (path) {
            value = replaceAll(value, pct2FPtn, "/");
        }
        return value;
    }

    /**
     * Get a thread local DocumentBuilderFactory.
     * <p>
     * DocumentBuilderFactory is NOT thread safe. This method uses a thread
     * local to create one per calling thread.
     *
     * @return The instance for this thread.
     */
    static DocumentBuilderFactory getDBF() {
        DocumentBuilderFactory dbf = threadDBF.get();
        if (dbf == null) {
            dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            threadDBF.set(dbf);
        }
        return dbf;
    }

    /**
     * Gets an enum instance from the enum class and name.
     * <p>
     * Caches hashmaps of enum names. If enum has an Other entry it will be
     * returned instead of null when no match is found.
     *
     * @param cls
     * @param name
     * @return The found instance.
     */
    @SuppressWarnings("unchecked")
    static <T> T getEnumValue(Class<T> cls, String name) {
        HashMap<String, Object> enumMap = enumMaps.get(cls);
        if (enumMap == null) {
            T[] consts = cls.getEnumConstants();
            enumMap = new HashMap<String, Object>(consts.length);
            for (T e : consts) {
                enumMap.put(((Enum<?>) e).toString(), e);
            }
            enumMaps.put(cls, enumMap);
        }
        T v = (T) enumMap.get(name);
        if (v == null) {
            v = (T) enumMap.get("Other");
        }
        return v;
    }

    /**
     * Get a ISO 8601 formatted timestamp of now.
     *
     * @return The time stamp.
     */
    static String getFormattedTimestamp() {
        DateFormat df = dateFormatPool.getAndSet(null);
        if (df == null) {
            df = createISODateFormat();
        }
        String timestamp = df.format(new Date());
        dateFormatPool.set(df);
        return timestamp;
    }

    /**
     * Parse an ISO 8601 formatted timestamp
     *
     * @return the parsed date
     */
    static Date parseTimestamp(String timestamp) throws ParseException {
        DateFormat df = dateFormatPool.getAndSet(null);
        if (df == null) {
            df = createISODateFormat();
        }
        Date date = df.parse(timestamp);
        dateFormatPool.set(df);
        return date;
    }

    /**
     * @return a new ISO 8601 date formatter
     */
    static DateFormat createISODateFormat() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        return df;
    }

    /**
     * Create a new instance of a class, wrap exceptions.
     *
     * @param cls
     * @return The new instance.
     */
    static <T> T newInstance(Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (Exception e) {
            throw wrap(e);
        }
    }

    /**
     * Computes RFC 2104-compliant HMAC signature for request parameters
     * Implements AWS Signature, as per following spec:
     * <p>
     * If Signature Version is 0, it signs concatenated Action and Timestamp
     * <p>
     * If Signature Version is 1, it performs the following:
     * <p>
     * Sorts all parameters (including SignatureVersion and excluding Signature,
     * the value of which is being created), ignoring case.
     * <p>
     * Iterate over the sorted list and append the parameter name (in original
     * case) and then its value. It will not URL-encode the parameter values
     * before constructing this string. There are no separators.
     * <p>
     * If Signature Version is 2, string to sign is based on following:
     * <p>
     * 1. The HTTP Request Method followed by an ASCII newline (%0A) 2. The HTTP
     * Host header in the form of lowercase host, followed by an ASCII newline.
     * 3. The URL encoded HTTP absolute path component of the URI (up to but not
     * including the query string parameters); if this is empty use a forward
     * '/'. This parameter is followed by an ASCII newline. 4. The concatenation
     * of all query string components (names and values) as UTF-8 characters
     * which are URL encoded as per RFC 3986 (hex characters MUST be uppercase),
     * sorted using lexicographic byte ordering. Parameter names are separated
     * from their values by the '=' character (ASCII character 61), even if the
     * value is empty. Pairs of parameter and values are separated by the '&'
     * character (ASCII code 38).
     *
     * @param serviceUri       Including host, port, api name, and api version
     * @param parameters
     * @param signatureVersion
     * @param signatureMethod
     * @param aswSecretKey
     * @return The base64 encoding of the signature.
     */
    static String signParameters(URI serviceUri,
                                 String signatureVersion,
                                 String signatureMethod,
                                 Map<String, String> parameters,
                                 String aswSecretKey) {
        parameters.put("SignatureVersion", signatureVersion);
        String algorithm = "HmacSHA1";
        String stringToSign = null;
        if ("0".equals(signatureVersion)) {
            stringToSign = calculateStringToSignV0(parameters);
        } else if ("1".equals(signatureVersion)) {
            stringToSign = calculateStringToSignV1(parameters);
        } else if ("2".equals(signatureVersion)) {
            algorithm = signatureMethod;
            parameters.put("SignatureMethod", algorithm);
            stringToSign = calculateStringToSignV2(serviceUri, parameters);
        } else {
            throw new IllegalArgumentException("Invalid Signature Version specified");
        }
        return sign(stringToSign, aswSecretKey, algorithm);
    }

    /**
     * Get xml string for contents of node.
     *
     * @param node
     * @return The node as xml.
     */
    static String toXmlString(Node node) {
        try {
            Transformer transformer = getTF().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
                                          "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(node);
            transformer.transform(source, result);
            return sw.toString();
        } catch (Exception e) {
            throw wrap(e);
        }
    }

    /**
     * Determine if a uri uses https.
     *
     * @param uri
     * @return true if uses https.
     */
    static boolean usesHttps(URI uri) {
        return uri.getScheme().equals("https");
    }

    /**
     * Determine if a url uses the standard port.
     * <p>
     * Port 80 for http, 443 for https.
     *
     * @param uri
     * @return true if standard port is used.
     */
    static boolean usesStandardPort(URI uri) {
        int portNumber = uri.getPort();
        if (portNumber == -1) {
            return true;
        }
        String schema = uri.getScheme();
        int standardPort = schema.equals("https") ? 443 : 80;
        return portNumber == standardPort;
    }

    /**
     * Close a Closeable if it is not null.
     *
     * @param a The Closeable or null.
     */
    public static void close(Closeable a) {
        try {
            if (a != null) {
                a.close();
            }
        } catch (Exception e) {
            throw wrap(e);
        }
    }

    /**
     * Escape application name before using to form user agent string.
     * <p>
     * Clean up white space and then escape back slash and forward slash
     * characters.
     *
     * @param s
     * @return The escaped app name.
     */
    public static String escapeAppName(String s) {
        s = cleanWS(s);
        s = replaceAll(s, BackSlashPtn, EscBackSlash);
        s = replaceAll(s, ForwardSlashPtn, EscForwardSlash);
        return s;
    }

    /**
     * Escape an application version string.
     *
     * @param s
     * @return The escaped app version.
     */
    public static String escapeAppVersion(String s) {
        s = cleanWS(s);
        s = replaceAll(s, BackSlashPtn, EscBackSlash);
        s = replaceAll(s, LParenPtn, EscLParen);
        return s;
    }

    /**
     * Standardize and escape an attribute name.
     * <p>
     * Clean white space. Escape back-slashes and equals-sign with a back-slash.
     *
     * @param s The attribute name to standardize and escape.
     * @return The standardized and escaped attribute name.
     */
    public static String escapeAttributeName(String s) {
        s = cleanWS(s);
        s = replaceAll(s, BackSlashPtn, EscBackSlash);
        s = replaceAll(s, EqualPtn, EscEqual);
        return s;
    }

    /**
     * Standardize and escape an attribute value.
     * <p>
     * Clean white space. Escape back-slashes, semi-colons, and right
     * parenthesis with a back-slash.
     *
     * @param s The attribute value to standardize and escape.
     * @return The standardized and escaped attribute value.
     */
    public static String escapeAttributeValue(String s) {
        s = cleanWS(s);
        s = replaceAll(s, BackSlashPtn, EscBackSlash);
        s = replaceAll(s, SemicolonPtn, EscSemicolon);
        s = replaceAll(s, RParenPtn, EscRParen);
        return s;
    }

    /**
     * Get a thread local DatatypeFactory.
     * <p>
     * DatatypeFactory is NOT required to be thread safe. This method uses a
     * thread local to create one per calling thread.
     *
     * @return A thread local DatatypeFactory.
     */
    public static DatatypeFactory getDTF() {
        DatatypeFactory dtf = threadDTF.get();
        if (dtf == null) {
            try {
                dtf = DatatypeFactory.newInstance();
            } catch (Exception e) {
                throw wrap(e);
            }
            threadDTF.set(dtf);
        }
        return dtf;
    }

    /**
     * Get a thread local transformer factory.
     *
     * @return The factory.
     */
    public static TransformerFactory getTF() {
        TransformerFactory tf = threadTF.get();
        if (tf == null) {
            tf = TransformerFactory.newInstance();
            threadTF.set(tf);
        }
        return tf;
    }

    /**
     * Wrap Checked exceptions in runtime exception.
     *
     * @param e
     * @return e, wrapped in a runtime exception if necessary.
     */
    public static RuntimeException wrap(Throwable e) {
        if (e instanceof RuntimeException) {
            throw (RuntimeException) e;
        }
        throw new RuntimeException(e);
    }

    /**
     * 将xml文件解析为String
     *
     * @param filePath
     * @return
     */
    public static String getSourceXml(String filePath) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        FileInputStream inputStream = null;
        Document doc = null;
        try {

            builder = factory.newDocumentBuilder();
            // 通过文件流的形式读取文件
            inputStream = new FileInputStream(filePath);
            doc = builder.parse(inputStream);

            Source source = new DOMSource(doc);

            StringWriter stringWriter = new StringWriter();

            Result res = new StreamResult(stringWriter);
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transFormer = transFactory.newTransformer();
            transFormer.setOutputProperty(OutputKeys.INDENT, "yes");
            transFormer.transform(source, res);
            return stringWriter.getBuffer().toString();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    return null;
                }
            }
        }
    }
}