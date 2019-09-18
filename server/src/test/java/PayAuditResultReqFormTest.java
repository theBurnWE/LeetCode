import java.util.HashMap;

@SuppressWarnings("serial")
public class PayAuditResultReqFormTest  {


//	private Shenhe_Result_Req PARAMS;

    private HashMap<String,String> PARAMS;
	private  Head GENERAL;

	public HashMap<String,String> getPARAMS() {
		return PARAMS;
	}

	public void setPARAMS(HashMap<String,String> PARAMS) {
		this.PARAMS = PARAMS;
	}

	public Head getGENERAL() {
		return GENERAL;
	}

	public void setGENERAL(Head GENERAL) {
		this.GENERAL = GENERAL;
	}
}
