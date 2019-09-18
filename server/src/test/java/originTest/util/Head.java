package originTest.util;

/**
 * @author BrunE
 * @date 2018-12-17 21:14
 **/
public class Head implements  java.io.Serializable {

    private static final long serialVersionUID = 1848365413137616670L;
    private String PRODUCTTYPE;

    private String TERMINALID;

    private String MERCHANTID;
    private String VERSION;
    private String TRXTYPE;

    public String getPRODUCTTYPE() {
        return PRODUCTTYPE;
    }

    public void setPRODUCTTYPE(String PRODUCTTYPE) {
        this.PRODUCTTYPE = PRODUCTTYPE;
    }

    public String getTERMINALID() {
        return TERMINALID;
    }

    public void setTERMINALID(String TERMINALID) {
        this.TERMINALID = TERMINALID;
    }

    public String getMERCHANTID() {
        return MERCHANTID;
    }

    public void setMERCHANTID(String MERCHANTID) {
        this.MERCHANTID = MERCHANTID;
    }

    public String getVERSION() {
        return VERSION;
    }

    public void setVERSION(String VERSION) {
        this.VERSION = VERSION;
    }

    public String getTRXTYPE() {
        return TRXTYPE;
    }

    public void setTRXTYPE(String TRXTYPE) {
        this.TRXTYPE = TRXTYPE;
    }
}
