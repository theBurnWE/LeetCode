package com.shcepp.shdippsvr.business.util;

import com.shcepp.shdippsvr.business.exception.BizCheckException;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Collections;
import java.util.Iterator;

/**
 * HelpSignMessage
 *
 * @author BrunE
 * @date 2017-08-04 14:07
 **/
public class SignUtil {

    private static final Logger logger = LoggerFactory.getLogger(SignUtil.class);


  /*  *//**
     *
     * @param
     * @param privateKey
     *            商户自己的私钥证书
     * @return
     *//*
    public String signXmlForDom(Document doc, PrivateKey privateKey) {

        try {
            // 1. convert xml to Document
//            DOMParser parser = new DOMParser();
//            //parser.parse(new InputSource(sourceXml));
//            parser.parse(new InputSource(new ByteArrayInputStream(sourceXml.getBytes(SignHelper.CHAREST))));
//            Document doc = parser.getDocument();

            // 2. sign document with key
            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

            // Create a Reference to the enveloped document (in this case we are
            // signing the whole document, so a URI of "" signifies that) and
            // also specify the SHA1 digest algorithm and the ENVELOPED
            // Transform.
            Reference ref = fac.newReference("",
                    fac.newDigestMethod(DigestMethod.SHA1,
                            null),
                    Collections.singletonList(fac.newTransform(Transform.ENVELOPED,
                            (TransformParameterSpec) null)),
                    null,
                    null);

            // Create the SignedInfo
            SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS,
                    (C14NMethodParameterSpec) null),
                    fac.newSignatureMethod(SignatureMethod.RSA_SHA1,
                            null),
                    Collections.singletonList(ref));

            // Create a DOMSignContext and specify the DSA PrivateKey and
            // location of the resulting XMLSignature's parent element
            DOMSignContext dsc = new DOMSignContext(privateKey,
                    doc.getDocumentElement());

            // Create the XMLSignature (but don't sign it yet)
            XMLSignature signature = fac.newXMLSignature(si, null);

            // Marshal, generate (and sign) the enveloped signature
            signature.sign(dsc);

            // 3.convert document to xml
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer();
            transformer.setOutputProperty("encoding", "UTF-8");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            transformer.transform(new DOMSource(doc), new StreamResult(bos));
            String targetXml = bos.toString();

            return targetXml;
        } catch (Exception e) {
            BizCheckException ex = new BizCheckException("signXml fail"
                    + e.toString());
            logger.info(ex);
            return null;
        }
    }*/

    /**
     * @param sourceXml
     * @param privateKey 商户自己的私钥证书
     * @return
     */
    public String signXml(String sourceXml, PrivateKey privateKey) {

        try {
            // 1. convert xml to Document
            DOMParser parser = new DOMParser();
            //parser.parse(new InputSource(sourceXml));
            parser.parse(new InputSource(new ByteArrayInputStream(sourceXml.getBytes(CpnrSignHelper.CHAREST))));
            Document doc = parser.getDocument();

            // 2. sign document with key
            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

            // Create a Reference to the enveloped document (in this case we are
            // signing the whole document, so a URI of "" signifies that) and
            // also specify the SHA1 digest algorithm and the ENVELOPED
            // Transform.
            Reference ref = fac.newReference("",
                                             fac.newDigestMethod(DigestMethod.SHA1,
                                                                 null),
                                             Collections.singletonList(fac.newTransform(Transform.ENVELOPED,
                                                                                        (TransformParameterSpec) null)),
                                             null,
                                             null);

            // Create the SignedInfo
            SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS,
                                                                            (C14NMethodParameterSpec) null),
                                              fac.newSignatureMethod(SignatureMethod.RSA_SHA1,
                                                                     null),
                                              Collections.singletonList(ref));

            // Create a DOMSignContext and specify the DSA PrivateKey and
            // location of the resulting XMLSignature's parent element
            DOMSignContext dsc = new DOMSignContext(privateKey,
                                                    doc.getDocumentElement());

            // Create the XMLSignature (but don't sign it yet)
            XMLSignature signature = fac.newXMLSignature(si, null);

            // Marshal, generate (and sign) the enveloped signature
            signature.sign(dsc);

            // 3.convert document to xml
            Transformer transformer = TransformerFactory.newInstance()
                                                        .newTransformer();
            transformer.setOutputProperty("encoding", "UTF-8");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            transformer.transform(new DOMSource(doc), new StreamResult(bos));
            String targetXml = bos.toString(CpnrSignHelper.CHAREST);//bos.toString();//
            // targetXml = new String (targetXml.getBytes("gbk"),"UTF-8");
            return targetXml;
        } catch (Exception e) {
            BizCheckException ex = new BizCheckException("signXml fail"
                                                                 + e.toString());
            logger.info("sign xml fail error detail is:{}", ex);
            return null;
        }
    }

    /**
     * @param targetXml
     * @param publicKey 汇付提供的公钥证书
     * @return
     */
    public boolean verifyXml(String targetXml, PublicKey publicKey) {
        try {
            // 1.convert xml to ducument
            DOMParser parser = new DOMParser();
            parser.parse(new InputSource(new ByteArrayInputStream(targetXml.getBytes(CpnrSignHelper.CHAREST))));
            // 解析XML文档
            Document doc = parser.getDocument();

            // 2.verify document by key
            NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS,
                                                     "Signature");
            if (nl.getLength() == 0) {
                BizCheckException ex = new BizCheckException("Cannot find Signature element");
                logger.info("Cannot find Signature element", ex);
                throw new RuntimeException("Cannot find Signature element");
            }

            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

            DOMValidateContext valContext = new DOMValidateContext(publicKey,
                                                                   nl.item(0));
            XMLSignature signature = fac.unmarshalXMLSignature(valContext);
            boolean coreValidity = signature.validate(valContext);

            // Check core validation status
            if (coreValidity == false) {
                logger.info("Signature failed core validation");
                boolean sv = signature.getSignatureValue().validate(valContext);
                logger.info("signature validation status: " + sv);
                // check the validation status of each Reference
                Iterator<?> i = signature.getSignedInfo()
                                         .getReferences()
                                         .iterator();
                for (int j = 0; i.hasNext(); j++) {
                    boolean refValid = ((Reference) i.next()).validate(valContext);
                    logger.info("ref[" + j + "] validity status: " + refValid);
                }
            } else {
                logger.info("Signature passed core validation");
            }
            return coreValidity;
        } catch (Exception e) {
            logger.warn("error message is ", e);
            // TODO Auto-generated catch block
            throw new BizCheckException("signXml fail" + e.toString());
        }
    }

}
