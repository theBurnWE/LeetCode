package com.shcepp.shdippsvr.business.util;

/**  
 * @Title: HttpsErrorSign.java
 * @Package com.shcepp.business.util
 * @Description: HTTPS错误码表
 * @author EP-mlzhang
 * @date 2013年10月30日 下午3:48:05
 * @version V1.0  
 */
public class HttpsErrorSign {
	public static final String REPLY_CODE_005 = "报文JSON串无法解析";
	public static final String REPLY_CODE_006 = "MD5验签失败";
	public static final String REPLY_CODE_007 = "东方支付扣款失败";
	public static final String REPLY_CODE_008 = "业务类型不存在";	
	public static final String REPLY_CODE_009 = "报文类型与业务类型不匹配";
	public static final String REPLY_CODE_010 = "外币支付必须带31记录";
	public static final String REPLY_CODE_011 = "重新支付币种与之前交易不符";
	public static final String REPLY_CODE_012 = "发送方必须与表单中的收款方一致";
	public static final String REPLY_CODE_013 = "表单中的币种在费目中不存在";
	public static final String REPLY_CODE_014 = "该收款方无法做反向支付";
	public static final String REPLY_CODE_015 = "该收款方不存在此业务类型";
	public static final String REPLY_CODE_016 = "报文信息与原始账单不符无法反向支付";
	
	public static final String REPLY_CODE_210 = "00记录不存在";
	public static final String REPLY_CODE_211 = "MessageType不存在";
	public static final String REPLY_CODE_212 = "MessageType值不正确";
	public static final String REPLY_CODE_213 = "FileDesc超长";
	public static final String REPLY_CODE_214 = "FileFunction值不正确";
	public static final String REPLY_CODE_215 = "SenderCode不存在";
	public static final String REPLY_CODE_216 = "SenderCode超长";
	public static final String REPLY_CODE_217 = "ReceiverCode不存在";
	public static final String REPLY_CODE_218 = "ReceiverCode超长";
	public static final String REPLY_CODE_219 = "FileCreateTime不存在";
	public static final String REPLY_CODE_220 = "FileCreateTime格式不正确";
	public static final String REPLY_CODE_221 = "MessageContent不存在";
	public static final String REPLY_CODE_222 = "MessageContent超长";
	
	public static final String REPLY_CODE_230 = "10记录不存在";
	public static final String REPLY_CODE_231 = "BizType不存在";
	public static final String REPLY_CODE_232 = "BizType超长";
	public static final String REPLY_CODE_233 = "BizTypeName不存在";
	public static final String REPLY_CODE_234 = "BizTypeName超长";
	public static final String REPLY_CODE_235 = "PayerCode与登录用户不一致";
	public static final String REPLY_CODE_245 = "PayerCode超长";
	public static final String REPLY_CODE_236 = "PayerName超长";
	public static final String REPLY_CODE_237 = "RelId不存在";
	public static final String REPLY_CODE_238 = "RelId超长";
	public static final String REPLY_CODE_239 = "RelPassword超长";
	public static final String REPLY_CODE_240 = "BillMark不存在";
	public static final String REPLY_CODE_241 = "BillMark超长";
	public static final String REPLY_CODE_242 = "BusinessDescription1超长";
	public static final String REPLY_CODE_243 = "BusinessDescription2超长";
	public static final String REPLY_CODE_244 = "BusinessDescription3超长";
	
	
	public static final String REPLY_CODE_250 = "14记录大于2次";
	public static final String REPLY_CODE_251 = "PayeeCode不存在";
	public static final String REPLY_CODE_252 = "PayeeCode超长";
	public static final String REPLY_CODE_253 = "PayeeAccount不存在";
	public static final String REPLY_CODE_254 = "PayeeAccount超长";
	public static final String REPLY_CODE_255 = "AccountBank不存在";
	public static final String REPLY_CODE_256 = "AccountBank超长";
	public static final String REPLY_CODE_257 = "AccountName不存在";
	public static final String REPLY_CODE_258 = "AccountName超长";
	public static final String REPLY_CODE_259 = "Currency不存在";
	public static final String REPLY_CODE_260 = "Currency格式不正确";
	public static final String REPLY_CODE_261 = "14记录不存在";
	
	
	
	public static final String REPLY_CODE_270 = "15记录大于99次";
	public static final String REPLY_CODE_271 = "FeeItem不存在";
	public static final String REPLY_CODE_272 = "FeeItem超长";
	public static final String REPLY_CODE_273 = "FeeCode不存在";
	public static final String REPLY_CODE_274 = "FeeCode超长";
	public static final String REPLY_CODE_275 = "PayableAmount不存在";
	public static final String REPLY_CODE_276 = "PayableAmount格式不正确";
	public static final String REPLY_CODE_277 = "PayableCurr不存在";
	public static final String REPLY_CODE_278 = "PayableCurr格式不正确";
	public static final String REPLY_CODE_279 = "ConversionCurr不存在";
	public static final String REPLY_CODE_280 = "ConversionCurr格式不正确";
	public static final String REPLY_CODE_281 = "ExchangeRate不存在";
	public static final String REPLY_CODE_282 = "ExchangeRate格式不正确";
	public static final String REPLY_CODE_283 = "PayableRmb不存在";
	public static final String REPLY_CODE_284 = "PayableRmb格式不正确";
	public static final String REPLY_CODE_285 = "Description超长";
	public static final String REPLY_CODE_286 = "15记录不存在";
	public static final String REPLY_CODE_287 = "各费目应付币种或者兑换币种不一致";
	public static final String REPLY_CODE_288 = "各费目应付币种或者兑换币种与收款方账号中的币种不一致";
	public static final String REPLY_CODE_289 = "收款方币种个数与费目中币种个数不一致";
	public static final String REPLY_CODE_269 = "费目中币种为单币支付只能有一个14记录";
	
	public static final String REPLY_CODE_290 = "31记录不存在";
	public static final String REPLY_CODE_291 = "InvoiceNumber不存在";
	public static final String REPLY_CODE_292 = "InvoiceNumber超长";
	public static final String REPLY_CODE_293 = "InvoicePayer不存在";
	public static final String REPLY_CODE_294 = "InvoicePayer超长";
	public static final String REPLY_CODE_295 = "InvoicePayerContact不存在";
	public static final String REPLY_CODE_296 = "InvoicePayerContact超长";
	public static final String REPLY_CODE_297 = "InvoicePayerTel不存在";
	public static final String REPLY_CODE_298 = "InvoicePayerTel超长";
	public static final String REPLY_CODE_299 = "InvoicePayerAddr超长";
	public static final String REPLY_CODE_300 = "InvoicePayerZip超长";
	public static final String REPLY_CODE_301 = "Description1超长";
	public static final String REPLY_CODE_302 = "Description2超长";
	public static final String REPLY_CODE_303 = "Description3超长";

	
	public static final String REPLY_CODE_304 = "DetailColumn1不存在";
	public static final String REPLY_CODE_305 = "DetailColumn1超长";
	public static final String REPLY_CODE_306 = "DetailColumn2超长";
	public static final String REPLY_CODE_307 = "DetailColumn3超长";
	public static final String REPLY_CODE_308 = "DetailColumn4超长";
	public static final String REPLY_CODE_309 = "DetailColumn5超长";
	public static final String REPLY_CODE_310 = "DetailColumn6超长";
	public static final String REPLY_CODE_311 = "DetailColumn7超长";
	public static final String REPLY_CODE_312 = "DetailColumn8超长";
	public static final String REPLY_CODE_320 = "50记录大于9999";
	
}
