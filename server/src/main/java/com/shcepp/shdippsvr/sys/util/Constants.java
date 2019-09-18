package com.shcepp.shdippsvr.sys.util;

/**
 * @author  mlzhang
 * @version Jun 29, 2011 5:07:37 PM 
 * description
 */
public class Constants {
	public static final String APP_CODE = "SHDIPP";

	public static final String REDIS_USERID = "USERID_";
	public static final int REDIS_OUT_TIME_30 = 1800;	// 30分钟
	public static final int REDIS_OUT_TIME = 1296000;
	public static final int REDIS_TIMEOUT = 864000;		//10天
	public static final String VERSION_V1 = "v1.0";
	public static final String SUCCESS = "1";
	public static final String FAILURE = "0";
	public static final String SENDER = "EFEP-JOB";
	public static final String MSG_TYPE_SEND_FOUND = "040500";
	public static final String MSG_TYPE_SEND_WITH_DRAW = "100040";
	//开关值
	public static final String SWITCH_ON = "ON";
	public static final String SWITCH_OFF = "OFF";
	//发送标志
	public static final String SEND_SWITCH_ON = "00";
	public static final String SEND_SWITCH_OFF = "01";
	//验证码类型 1:注册 2:重置密码
	public static final String CAPTCHA_REG_TYPE = "1";
	public static final String CAPTCHA_RESET_TYPE = "2";
	//管理员邮箱
	public static final String ADMIN_EMAIL = "service@dtradex.com";
	/**
	 * 手机号码正则表达式
	 */
	public static final String MOBILE_VERIFY_REGEX = "^1[\\d]{10}$";
	/**
	 * 短信需求参数
	 */
	public static final String SMGCONTENTTAMPLE = "phonefg_wxp";
	public static final String RESETPASSTAMPLE = "phonefg_wxp";

	public static String showInfo(String code) {
		String info = null;
		switch (code) {
			case CLIENT_ERROR_000.ERROR_CODE001:
				info = CLIENT_ERROR_000.ERROR_INFO001;
				break;
			case CLIENT_ERROR_000.ERROR_CODE002:
				info = CLIENT_ERROR_000.ERROR_INFO002;
				break;
			case CLIENT_ERROR_000.ERROR_CODE003:
				info = CLIENT_ERROR_000.ERROR_INFO003;
				break;
			case CLIENT_ERROR_000.ERROR_CODE004:
				info = CLIENT_ERROR_000.ERROR_INFO004;
				break;
			case CLIENT_ERROR_000.ERROR_CODE005:
				info = CLIENT_ERROR_000.ERROR_INFO005;
				break;
			case CLIENT_ERROR_000.ERROR_CODE006:
				info = CLIENT_ERROR_000.ERROR_INFO006;
				break;
			case CLIENT_ERROR_000.ERROR_CODE007:
				info = CLIENT_ERROR_000.ERROR_INFO007;
				break;

			case CLIENT_ERROR_100.ERROR_CODE101:
				info = CLIENT_ERROR_100.ERROR_INFO101;
				break;
			case CLIENT_ERROR_100.ERROR_CODE102:
				info = CLIENT_ERROR_100.ERROR_INFO101;
				break;
			case CLIENT_ERROR_100.ERROR_CODE103:
				info = CLIENT_ERROR_100.ERROR_INFO103;
				break;
			case CLIENT_ERROR_100.ERROR_CODE104:
				info = CLIENT_ERROR_100.ERROR_INFO104;
				break;

			case USER_ERROR_200.ERROR_CODE201:
				info = USER_ERROR_200.ERROR_INFO201;
				break;
			case USER_ERROR_200.ERROR_CODE202:
				info = USER_ERROR_200.ERROR_INFO202;
				break;
			case USER_ERROR_200.ERROR_CODE203:
				info = USER_ERROR_200.ERROR_INFO203;
				break;
			case USER_ERROR_200.ERROR_CODE204:
				info = USER_ERROR_200.ERROR_INFO204;
				break;
			case USER_ERROR_200.ERROR_CODE205:
				info = USER_ERROR_200.ERROR_INFO205;
				break;
			case USER_ERROR_200.ERROR_CODE206:
				info = USER_ERROR_200.ERROR_INFO206;
				break;
			case USER_ERROR_200.ERROR_CODE207:
				info = USER_ERROR_200.ERROR_INFO207;
				break;
			case USER_ERROR_200.ERROR_CODE208:
				info = USER_ERROR_200.ERROR_INFO208;
				break;
			case USER_ERROR_200.ERROR_CODE209:
				info = USER_ERROR_200.ERROR_INFO209;
				break;
			case USER_ERROR_200.ERROR_CODE210:
				info = USER_ERROR_200.ERROR_INFO210;
				break;
			case USER_ERROR_200.ERROR_CODE211:
				info = USER_ERROR_200.ERROR_INFO211;
				break;
			case USER_ERROR_200.ERROR_CODE212:
				info = USER_ERROR_200.ERROR_INFO212;
				break;
			case USER_ERROR_200.ERROR_CODE213:
				info = USER_ERROR_200.ERROR_INFO213;
				break;
			case USER_ERROR_200.ERROR_CODE214:
				info = USER_ERROR_200.ERROR_INFO214;
				break;
			case USER_ERROR_200.ERROR_CODE215:
				info = USER_ERROR_200.ERROR_INFO215;
				break;
			case USER_ERROR_200.ERROR_CODE216:
				info = USER_ERROR_200.ERROR_INFO216;
				break;
			case USER_ERROR_200.ERROR_CODE217:
				info = USER_ERROR_200.ERROR_INFO217;
				break;

			case CLIENT_ERROR_900.ERROR_CODE901:
				info = CLIENT_ERROR_900.ERROR_INFO901;
				break;
			case CLIENT_ERROR_900.ERROR_CODE902 :
				info = CLIENT_ERROR_900.ERROR_INFO902;
				break;
			case CLIENT_ERROR_900.ERROR_CODE903 :
				info = CLIENT_ERROR_900.ERROR_INFO903;
				break;
			case CLIENT_ERROR_900.ERROR_CODE904:
				info = CLIENT_ERROR_900.ERROR_INFO904;
				break;
			case CLIENT_ERROR_900.ERROR_CODE905 :
				info = CLIENT_ERROR_900.ERROR_INFO905;
				break;
			case CLIENT_ERROR_900.ERROR_CODE906 :
				info = CLIENT_ERROR_900.ERROR_INFO906;
				break;
			case CLIENT_ERROR_900.ERROR_CODE907 :
				info = CLIENT_ERROR_900.ERROR_INFO907;
				break;
			case CLIENT_ERROR_900.ERROR_CODE908 :
				info = CLIENT_ERROR_900.ERROR_INFO908;
				break;
			default:
				info = "未知错误,请稍后再试";
				break;
		}
		return info;
	}

	public static class CLIENT_FLAG {
		public static final String FLAG_T = "T";
		public static final String FLAG_F = "F";
	}

	public static class CLIENT_ERROR_000{
		public static final String ERROR_CODE001 = "001";
		public static final String ERROR_INFO001 = "未知错误，请稍后再试";
		public static final String ERROR_CODE002 = "002";
		public static final String ERROR_INFO002 = "该单证已经被修改无法保存！";
		public static final String ERROR_CODE003 = "003";
		public static final String ERROR_INFO003 = "数据库操作发生错误，自动回滚。";
		public static final String ERROR_CODE004 = "004";
		public static final String ERROR_INFO004 = "更新失败，请稍候再试";
		public static final String ERROR_CODE005 = "005";
		public static final String ERROR_INFO005 = "存储过程调用异常";
		public static final String ERROR_CODE006 = "006";
		public static final String ERROR_INFO006 = "业务异常";
		public static final String ERROR_CODE007 = "007";
		public static final String ERROR_INFO007 = "定时任务异常";
	}
	
	public static class CLIENT_ERROR_100{
		public static final String ERROR_CODE101 = "101";
		public static final String ERROR_INFO101 = "请求参数有误或缺失";
		public static final String ERROR_CODE102 = "102";
		public static final String ERROR_INFO102 = "未找到对应单证信息";
		public static final String ERROR_CODE103 = "103";
		public static final String ERROR_INFO103 = "存在相同单号无法新增";
		public static final String ERROR_CODE104 = "104";
		public static final String ERROR_INFO104 = "非法调用";
	}

	public static class USER_ERROR_200 {
		public static final String ERROR_CODE201 = "201";
		public static final String ERROR_INFO201 = "此用户未注册，请先注册！";
		public static final String ERROR_CODE202 = "202";
		public static final String ERROR_INFO202 = "您已注册！";
		public static final String ERROR_CODE203 = "203";
		public static final String ERROR_INFO203 = "统一身份认证缺少数据！";
		public static final String ERROR_CODE204 = "204";
		public static final String ERROR_INFO204 = "同一用户不能同时注册！";
		public static final String ERROR_CODE205 = "205";
		public static final String ERROR_INFO205 = "缺少筛选参数，请联系软件开发商！";
		public static final String ERROR_CODE206 = "206";
		public static final String ERROR_INFO206 = "您不是企业管理员，无法操作！";
		public static final String ERROR_CODE207 = "207";
		public static final String ERROR_INFO207 = "用户信息获取异常！";
		public static final String ERROR_CODE208 = "208";
		public static final String ERROR_INFO208 = "OAuth异常！";
		public static final String ERROR_CODE209 = "209";
		public static final String ERROR_INFO209 = "登陆失败！";
		public static final String ERROR_CODE210 = "210";
		public static final String ERROR_INFO210 = "认证凭据与用户名不匹配，请联系软件开发商！";
		public static final String ERROR_CODE211 = "211";
		public static final String ERROR_INFO211 = "重新预约失败!";
		public static final String ERROR_CODE212 = "212";
		public static final String ERROR_INFO212 = "保存账单失败!";
		public static final String ERROR_CODE213 = "213";
		public static final String ERROR_INFO213 = "保存发票失败!";
		public static final String ERROR_CODE214 = "214";
		public static final String ERROR_INFO214 = "1302异常!";
		public static final String ERROR_CODE215 = "215";
		public static final String ERROR_INFO215 = "1304异常!";
		public static final String ERROR_CODE216 = "216";
		public static final String ERROR_INFO216 = "1305异常!";
		public static final String ERROR_CODE217 = "217";
		public static final String ERROR_INFO217 = "支付异常!";
	}

	public static class CLIENT_ERROR_900{
		public static final String ERROR_CODE901 = "901";
		public static final String ERROR_INFO901 = "验证参数有误或缺失";
		public static final String ERROR_CODE902 = "902";
		public static final String ERROR_INFO902 = "验证信息失败";
		public static final String ERROR_CODE903 = "903";
		public static final String ERROR_INFO903 = "服务器异常";
		public static final String ERROR_CODE904 = "904";
		public static final String ERROR_INFO904 = "用户授权失效，请重新登录";
		public static final String ERROR_CODE905 = "905";
		public static final String ERROR_INFO905 = "认证凭据为空";
		public static final String ERROR_CODE906 = "906";
		public static final String ERROR_INFO906 = "无权访问";
		public static final String ERROR_CODE907 = "907";
		public static final String ERROR_INFO907 = "网络异常，请稍后再试！";
		public static final String ERROR_CODE908 = "908";
		public static final String ERROR_INFO908 = "OAuth异常！";
		public static final String ERROR_CODE909 = "909";
		public static final String ERROR_INFO909 = "连续访问次数太多，请稍后再试";
	}

	public static class USER_REG_ERROR {
		public static final String ERROR_CODE000 = "000";
		public static final String ERROR_INFO000 = "账号已存在不能注册";
		public static final String ERROR_CODE001 = "001";
		public static final String ERROR_INFO001 = "发送短信错误";
		public static final String ERROR_CODE002 = "002";
		public static final String ERROR_INFO002 = "验证码发送错误";
		public static final String ERROR_CODE003 = "003";
		public static final String ERROR_INFO003 = "账号类型错误";
		public static final String ERROR_CODE004 = "004";
		public static final String ERROR_INFO004 = "验证码错误";
		public static final String ERROR_CODE005 = "005";
		public static final String ERROR_INFO005 = "注册错误";
		public static final String ERROR_CODE006 = "006";
		public static final String ERROR_INFO006 = "密码必须大于6位小于16位且不能为纯字母、纯数字或纯符号";
		public static final String ERROR_CODE007 = "007";
		public static final String ERROR_INFO007 = "手机短信不发送";
		public static final String ERROR_CODE008 = "008";
		public static final String ERROR_INFO008 = "邮件不发送";
		public static final String ERROR_CODE009 = "009";
		public static final String ERROR_INFO009 = "手机号已存在不能绑定";
		public static final String ERROR_CODE010 = "010";
		public static final String ERROR_INFO010 = "邮箱已存在不能绑定";
		public static final String ERROR_CODE011 = "011";
		public static final String ERROR_INFO011 = "必须绑定手机号或者邮箱";
		public static final String ERROR_CODE012 = "012";
		public static final String ERROR_INFO012 = "修改密码失败";
		public static final String ERROR_CODE013 = "013";
		public static final String ERROR_INFO013 = "重置密码失败";
		public static final String ERROR_CODE014 = "014";
		public static final String ERROR_INFO014 = "用户名长度4-20位，必须以字母数字开头结尾，可以包括数字、减号和下划线";
	}

	public static class PRODUCT_ERROR {
		public static final String ERROR_CODE000 = "000";
		public static final String ERROR_INFO000= "查询产品信息错误";
		public static final String ERROR_CODE001 = "001";
		public static final String ERROR_INF0001 = "查询产品排行错误";
		public static final String ERROR_CODE002 = "002";
		public static final String ERROR_INF0002 = "点赞产品错误";
		public static final String ERROR_CODE003 = "003";
		public static final String ERROR_INF0003 = "收藏产品错误";
		public static final String ERROR_CODE004 = "004";
		public static final String ERROR_INF0004 = "缓存产品排行错误";
	}


}
