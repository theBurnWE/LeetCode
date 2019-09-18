package com.shcepp.shdippsvr.sys.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import com.shcepp.shdippsvr.business.util.DateUtils;
import com.shcepp.shdippsvr.sys.service.IBizImgRoutingService;
import com.shcepp.shdippsvr.sys.util.ImageUtil;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("bizImgRoutingService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BizImgRoutingService implements IBizImgRoutingService {

////	@Resource(name = "ewBizImgDAO")
////	IBizImgDAO bizImgDAO;
//
//	public String getRealUrl(String imgPath) {
//		// TODO Auto-generated method stub
//		String ret = imgPath;
//		String newURI = ret;
//		int iIndex = newURI.substring(3).indexOf("/");
//		if (iIndex == -1)
//			return "";
//		String imgSize = newURI.substring(3).substring(0, iIndex);
//		String imgId = newURI.substring(3).substring(iIndex + 1);
//
////		List<EwBizImg> list = bizImgDAO.getImgByIdAndSize(imgId, imgSize);
//
////		if (list != null && list.size() > 0) {
////			ret = list.get(0).getRealUrl();
////			ret = ret.replace("{size}", list.get(0).getImgSize());
////		}
//		return ret;
//	}
//
//	private boolean saveAllAvatarImg(byte[] picData, String picName,
//			String imgFolder) throws IOException {
//
//		// 保存头像图片为三种尺寸
//
//		// 保存大图
//		File imageBig = new File(imgFolder + "big" + picName);
//		// 创建输出流
//		FileOutputStream outStreamBig = new FileOutputStream(imageBig);
//		// 写入数据
//		outStreamBig.write(picData);
//		// 关闭输出流
//		outStreamBig.close();
//
//		// 保存中图
//		ImageUtil.resize(imgFolder + "big" + picName, imgFolder + "middle" + picName, 50, 50);
//
//		// 保存小图
//		ImageUtil.resize(imgFolder + "big" + picName, imgFolder + "small" + picName, 30, 30);
//
//		return true;
//	}
//
//	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
//	public Object saveAvatarImg(byte[] picData, long uploaderId)
//			throws Exception {
//		String avatarImgFileName = StringUtils.genRandomImgName() + ".jpg";
//		String svrRootPath = SysOptionBuffer.getInstance().getProperty(
//				"img.svrRootPath");
//		if (!StringUtils.notBlank(svrRootPath)) {
//			throw new Exception("上传目录不存在");
//		}
//		String svrSubFolder = svrRootPath + DateUtils.getThisDay() + "/";
//
//		File folder = IOUtils.createOrGetDirectory(svrSubFolder);
//		EwBizImg ret = null;
//		if (folder != null) {
//			boolean bSave = false;
//			try {
//				bSave = saveAllAvatarImg(picData, avatarImgFileName,
//						svrSubFolder);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//
//			// 保存文件成功
//			if (bSave) {
////				ret = bizImgDAO.saveAnImg(svrSubFolder + "{size}" + avatarImgFileName, uploaderId, "all");
//			}
//		}
//		return ret;
//	}
//
//
//	private boolean saveAllSizePostImg(byte[] picData, String picName,
//			String imgFolder) throws IOException {
//
//		// 保存头像图片为三种尺寸
//
//		// 保存大图
//		File imageBig = new File(imgFolder + "big" + picName);
//		// 创建输出流
//		FileOutputStream outStreamBig = new FileOutputStream(imageBig);
//		// 写入数据
//		outStreamBig.write(picData);
//		// 关闭输出流
//		outStreamBig.close();
//
//
//
//		// 保存中图
//		ImageUtil.resizeFixedMaxWidth(imgFolder + "big" + picName, imgFolder + "middle" + picName, 440, 1);
//
//		// 保存小图
//		ImageUtil.resizeFixedMaxHeight(imgFolder + "big" + picName, imgFolder + "small" + picName, 120, 1);
//
//		return true;
//	}
//
//	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
//	public EwBizImg savePostImg(byte[] picData, long uploaderId)
//			throws Exception {
//		String postImgFileName = StringUtils.genRandomImgName() + ".jpg";
//		String svrRootPath = SysOptionBuffer.getInstance().getProperty(
//				"img.svrRootPath");
//		if (!StringUtils.notBlank(svrRootPath)) {
//			throw new Exception("上传目录不存在");
//		}
//		String svrSubFolder = svrRootPath + DateUtils.getThisDay() + "/";
//
//		File folder = IOUtils.createOrGetDirectory(svrSubFolder);
//		EwBizImg ret = null;
//		if (folder != null) {
//			boolean bSave = false;
//			try {
//				bSave = saveAllSizePostImg(picData, postImgFileName,
//						svrSubFolder);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//
//			// 保存文件成功
//			if (bSave) {
//				ret = bizImgDAO.saveAnImg(svrSubFolder + "{size}" + postImgFileName, uploaderId, "all");
//			}
//		}
//		return ret;
//	}
//
	public static IBizImgRoutingService getFromApplicationContext(
			ApplicationContext ctx) {
		return (IBizImgRoutingService) ctx.getBean("bizImgRoutingService");
	}
}
