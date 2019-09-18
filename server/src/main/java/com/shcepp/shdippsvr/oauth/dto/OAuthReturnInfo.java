package com.shcepp.shdippsvr.oauth.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

//"redirect_uri":null,"flag":"T","authorcode":"AC-2-W6HxpXJTk6aAxfxHtaLd81FnqxlyZ6PTg3akuL2u0RaSh6FcQZ"}
public class OAuthReturnInfo {
	@JsonProperty("redirect_uri")
	private String redirectUri;
	@JsonProperty("flag")
	private String flag;
	@JsonProperty("errorInfo")
	private String errorInfo;
	@JsonProperty("errorCode")
	private String errorCode;
	@JsonProperty("refresh_token")
	private String refreshToken;
	@JsonProperty("access_token")
	private String accessToken;

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
