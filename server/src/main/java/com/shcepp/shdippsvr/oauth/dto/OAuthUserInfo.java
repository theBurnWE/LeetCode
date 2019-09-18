package com.shcepp.shdippsvr.oauth.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class OAuthUserInfo extends OAuthResponse implements Serializable {

	public String permission;

	public UserInfo info;
	
	public static class UserInfo{

		public Map<String,String> attrs;
		public List<UmRole> roles;
		public List<UmPermission> permits;
		
		public static class UmPermission{
			public int  id;
			public String code;
			public String name;
			public String url;
			public String flag;
			public String meno;
			public long subsysId;
		}		
		
		public static class UmRole{
			public int id;
			public String flag;
			public String meno;
			public String name;
			public String roleCode;
			public long subsystemId;
		}
	}
	

}
