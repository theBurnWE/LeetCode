package com.shcepp.shdippsvr.business.util;

import com.shcepp.shdippsvr.sys.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * 图片压缩工具类 提供的方法中可以设定生成的 缩略图片的大小尺寸、压缩尺寸的比例、图片的质量等
 * <p>
 * 调用示例：
 * resiz(srcImg, tarDir + "car_1_maxLength_11-220px-hui.jpg", 220, 0.7F);
 *
 * @author cevencheng
 * @project haohui-b2b
 * @create 2012-3-22 下午8:29:01
 */
public class ImageUtil {

    private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * * 图片文件读取
     *
     * @param srcImgPath
     * @return
     */
    private static BufferedImage InputImage(String srcImgPath) throws RuntimeException {

        BufferedImage srcImage = null;
        FileInputStream in = null;
        try {
            // 构造BufferedImage对象  
            File file = new File(srcImgPath);
            in = new FileInputStream(file);
            byte[] b = new byte[5];
            in.read(b);
            srcImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取图片文件出错！", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException("读取图片文件出错！", e);
                }
            }
        }
        return srcImage;
    }

    /**
     * * 将图片按照指定的图片尺寸、源图片质量压缩(默认质量为1)
     *
     * @param srcImgPath :源图片路径
     * @param outImgPath :输出的压缩图片的路径
     * @param new_w      :压缩后的图片宽
     * @param new_h      :压缩后的图片高
     */
    public static void resize(String srcImgPath, String outImgPath,
                              int new_w, int new_h) {
        resize(srcImgPath, outImgPath, new_w, new_h, 1F);
    }

    /**
     * 将图片按照指定的尺寸比例、源图片质量压缩(默认质量为1)
     *
     * @param srcImgPath :源图片路径
     * @param outImgPath :输出的压缩图片的路径
     * @param ratio      :压缩后的图片尺寸比例
     *                   百分比
     */
    public static void resize(String srcImgPath, String outImgPath,
                              float ratio) {
        resize(srcImgPath, outImgPath, ratio, 1F);
    }

    /**
     * 将图片按照指定长或者宽的最大值来压缩图片(默认质量为1)
     *
     * @param srcImgPath :源图片路径
     * @param outImgPath :输出的压缩图片的路径
     * @param maxLength  :长或者宽的最大值
     *                   图片质量
     */
    public static void resize(String srcImgPath, String outImgPath,
                              int maxLength) {
        resize(srcImgPath, outImgPath, maxLength, 1F);
    }

    /**
     * * 将图片按照指定的图片尺寸、图片质量压缩
     *
     * @param srcImgPath :源图片路径
     * @param outImgPath :输出的压缩图片的路径
     * @author cevencheng
     */
    public static void resize(String srcImgPath, String outImgPath) {
        try {
            int new_w;
            int new_h;

            // 得到图片
            BufferedImage src = InputImage(srcImgPath);
            int old_w = src.getWidth();
            // 得到源图宽
            int old_h = src.getHeight();

            if (old_w > old_h) {
                new_w = 400;
                new_h = 300;
            } else if (old_w < old_h) {
                new_w = 400;
                new_h = 300;
            } else {
                new_w = 400;
                new_h = 400;
            }

            // 得到源图长
            // 根据原图的大小生成空白画布
            BufferedImage tempImg = new BufferedImage(old_w, old_h,
                                                      BufferedImage.TYPE_INT_RGB);
            // 在新的画布上生成原图的缩略图
            Graphics2D g = tempImg.createGraphics();
            g.setColor(Color.white);
            g.fillRect(0, 0, old_w, old_h);
            g.drawImage(src, 0, 0, old_w, old_h, Color.white, null);
            g.dispose();
            BufferedImage newImg = new BufferedImage(new_w, new_h,
                                                     BufferedImage.TYPE_INT_RGB);
            newImg.getGraphics().drawImage(
                    tempImg.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0,
                    0, null);
            // 调用方法输出图片文件
            outImage(outImgPath, newImg, 1);

        } catch (Exception ex) {
            logger.error("在保存缩略图的时候出错，错误信息为:", ex);
        }
    }

    /**
     * * 将图片按照指定的图片尺寸、图片质量压缩
     *
     * @param srcImgPath :源图片路径
     * @param outImgPath :输出的压缩图片的路径
     * @param new_w      :压缩后的图片宽
     * @param new_h      :压缩后的图片高
     * @param per        :百分比
     * @author cevencheng
     */
    public static void resize(String srcImgPath, String outImgPath,
                              int new_w, int new_h, float per) {
        try {

            // 得到图片
            BufferedImage src = InputImage(srcImgPath);
            int old_w = src.getWidth();
            // 得到源图宽
            int old_h = src.getHeight();

            // 得到源图长
            // 根据原图的大小生成空白画布
            BufferedImage tempImg = new BufferedImage(old_w, old_h,
                                                      BufferedImage.TYPE_INT_RGB);
            // 在新的画布上生成原图的缩略图
            Graphics2D g = tempImg.createGraphics();
            g.setColor(Color.white);
            g.fillRect(0, 0, old_w, old_h);
            g.drawImage(src, 0, 0, old_w, old_h, Color.white, null);
            g.dispose();
            BufferedImage newImg = new BufferedImage(new_w, new_h,
                                                     BufferedImage.TYPE_INT_RGB);
            newImg.getGraphics().drawImage(
                    tempImg.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0,
                    0, null);
            // 调用方法输出图片文件
            outImage(outImgPath, newImg, per);

        } catch (Exception ex) {
            logger.error("在保存缩略图的时候出错，错误信息为:", ex);
        }
    }

    /**
     * * 将图片按照指定的尺寸比例、图片质量压缩
     *
     * @param srcImgPath :源图片路径
     * @param outImgPath :输出的压缩图片的路径
     * @param ratio      :压缩后的图片尺寸比例
     * @param per        :百分比
     * @author cevencheng
     */
    public static void resize(String srcImgPath, String outImgPath,
                              float ratio, float per) {
        // 得到图片  
        BufferedImage src = InputImage(srcImgPath);
        int old_w = src.getWidth();
        // 得到源图宽  
        int old_h = src.getHeight();
        // 得到源图长  
        int new_w = 0;
        // 新图的宽  
        int new_h = 0;
        // 新图的长  
        BufferedImage tempImg = new BufferedImage(old_w, old_h,
                                                  BufferedImage.TYPE_INT_RGB);
        Graphics2D g = tempImg.createGraphics();
        g.setColor(Color.white);
        // 从原图上取颜色绘制新图g.fillRect(0, 0, old_w, old_h);  
        g.drawImage(src, 0, 0, old_w, old_h, Color.white, null);
        g.dispose();
        // 根据图片尺寸压缩比得到新图的尺寸new_w = (int) Math.round(old_w * ratio);  
        new_h = (int) Math.round(old_h * ratio);
        BufferedImage newImg = new BufferedImage(new_w, new_h,
                                                 BufferedImage.TYPE_INT_RGB);
        newImg.getGraphics().drawImage(
                tempImg.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0,
                0, null);
        // 调用方法输出图片文件OutImage(outImgPath, newImg, per);  
    }

    /**
     * 指定长或者宽的最大值来压缩图片
     * 推荐使用此方法
     *
     * @param srcImgPath :源图片路径
     * @param outImgPath :输出的压缩图片的路径
     * @param maxLength  :长或者宽的最大值
     * @param per        :图片质量
     * @author cevencheng
     */
    public static void resize(String srcImgPath, String outImgPath,
                              int maxLength, float per) {
        // 得到图片  
        BufferedImage src = InputImage(srcImgPath);
        int old_w = src.getWidth();
        // 得到源图宽  
        int old_h = src.getHeight();
        // 得到源图长  
        int new_w = 0;
        // 新图的宽  
        int new_h = 0;
        // 新图的长  
        BufferedImage tempImg = new BufferedImage(old_w, old_h,
                                                  BufferedImage.TYPE_INT_RGB);
        Graphics2D g = tempImg.createGraphics();
        g.setColor(Color.white);
        // 从原图上取颜色绘制新图  
        g.fillRect(0, 0, old_w, old_h);
        g.drawImage(src, 0, 0, old_w, old_h, Color.white, null);
        g.dispose();
        // 根据图片尺寸压缩比得到新图的尺寸  
        if (old_w > old_h) {
            // 图片要缩放的比例  
            new_w = maxLength;
            new_h = (int) Math.round(old_h * ((float) maxLength / old_w));
        } else {
            new_w = (int) Math.round(old_w * ((float) maxLength / old_h));
            new_h = maxLength;
        }
        BufferedImage newImg = new BufferedImage(new_w, new_h,
                                                 BufferedImage.TYPE_INT_RGB);
        newImg.getGraphics().drawImage(
                tempImg.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0,
                0, null);
        // 调用方法输出图片文件  
        outImage(outImgPath, newImg, per);
    }

    /**
     * 将图片压缩成指定最大宽度的图片
     * 如果宽度没有超过，则保留原图
     *
     * @param srcImgPath
     * @param outImgPath
     * @param width
     * @param per
     */
    public static void resizeFixedMaxWidth(String srcImgPath, String outImgPath,
                                           int width, float per) {
        // 得到图片
        BufferedImage src = InputImage(srcImgPath);
        int old_w = src.getWidth();
        // 得到源图宽
        int old_h = src.getHeight();
        // 得到源图长
        int new_w = 0;
        // 新图的宽
        int new_h = 0;
        // 新图的长
        BufferedImage tempImg = new BufferedImage(old_w, old_h,
                                                  BufferedImage.TYPE_INT_RGB);
        Graphics2D g = tempImg.createGraphics();
        g.setColor(Color.white);
        // 从原图上取颜色绘制新图
        g.fillRect(0, 0, old_w, old_h);
        g.drawImage(src, 0, 0, old_w, old_h, Color.white, null);
        g.dispose();
        // 根据图片尺寸压缩比得到新图的尺寸
        // 如果宽度不超过设置宽度，则不进行压缩
        if (old_w > width) {

            // 图片要缩放的比例
            new_w = width;
            new_h = (int) Math.round(old_h * ((float) width / old_w));
        } else {
            new_w = old_w;
            new_h = old_h;
        }
        BufferedImage newImg = new BufferedImage(new_w, new_h,
                                                 BufferedImage.TYPE_INT_RGB);
        newImg.getGraphics().drawImage(
                tempImg.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0,
                0, null);
        // 调用方法输出图片文件
        outImage(outImgPath, newImg, per);
    }

    /**
     * 将图片压缩成指定最大高度的图片
     * 如果高度没有超过，则保留原图
     *
     * @param srcImgPath
     * @param outImgPath
     * @param height
     * @param per
     */
    public static void resizeFixedMaxHeight(String srcImgPath, String outImgPath,
                                            int height, float per) {
        // 得到图片
        BufferedImage src = InputImage(srcImgPath);
        int old_w = src.getWidth();
        // 得到源图宽
        int old_h = src.getHeight();
        // 得到源图长
        int new_w = 0;
        // 新图的宽
        int new_h = 0;
        // 新图的长
        BufferedImage tempImg = new BufferedImage(old_w, old_h,
                                                  BufferedImage.TYPE_INT_RGB);
        Graphics2D g = tempImg.createGraphics();
        g.setColor(Color.white);
        // 从原图上取颜色绘制新图
        g.fillRect(0, 0, old_w, old_h);
        g.drawImage(src, 0, 0, old_w, old_h, Color.white, null);
        g.dispose();
        // 根据图片尺寸压缩比得到新图的尺寸
        // 如果高度不超过设置高度，则不进行压缩
        if (old_h > height) {

            // 图片要缩放的比例
            new_h = height;
            new_w = (int) Math.round(old_w * ((float) height / old_h));
        } else {
            new_w = old_w;
            new_h = old_h;
        }
        BufferedImage newImg = new BufferedImage(new_w, new_h,
                                                 BufferedImage.TYPE_INT_RGB);
        newImg.getGraphics().drawImage(
                tempImg.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0,
                0, null);
        // 调用方法输出图片文件
        outImage(outImgPath, newImg, per);
    }

    /**
     * * 将图片文件输出到指定的路径，并可设定压缩质量
     *
     * @param outImgPath
     * @param newImg
     * @param per
     * @author cevencheng
     */
    private static void outImage(String outImgPath, BufferedImage newImg, float per) {
        // 判断输出的文件夹路径是否存在，不存在则创建  
        File file = new File(outImgPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        // 输出到文件流
        try {
            FileOutputStream fos = null;

            String fileSuffix = StringUtils.isNotEmptyWithNUllCheckStr(outImgPath) ? outImgPath.substring(outImgPath.lastIndexOf(".") + 1) : "";

            ImageIO.write(newImg, fileSuffix, new File(outImgPath));

        } catch (Exception e) {
            logger.info("在压缩图片的时候出错，错误原因为:", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 图片剪切工具方法
     *
     * @param srcfile 源图片
     * @param outfile 剪切之后的图片
     * @param x       剪切顶点 X 坐标
     * @param y       剪切顶点 Y 坐标
     * @param width   剪切区域宽度
     * @param height  剪切区域高度
     * @throws IOException
     * @author cevencheng
     */
    public static void cut(File srcfile, File outfile, int x, int y, int width, int height) throws IOException {
        FileInputStream is = null;
        ImageInputStream iis = null;
        try {
            // 读取图片文件
            is = new FileInputStream(srcfile);

            /**
             * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader 声称能够解码指定格式。
             * 参数：formatName - 包含非正式格式名称 .（例如 "jpeg" 或 "tiff"）等 。
             */
            Iterator it = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = (ImageReader) it.next();
            // 获取图片流
            iis = ImageIO.createImageInputStream((Object) is);

            /**
             *iis:读取源.true:只向前搜索 .将它标记为 ‘只向前搜索’。
             * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
             */
            reader.setInput(iis, true);

            /**
             *描述如何对流进行解码的类.用于指定如何在输入时从 Java Image I/O
             * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件 将从其 ImageReader 实现的
             * getDefaultReadParam 方法中返回 ImageReadParam 的实例。
             */
            ImageReadParam param = reader.getDefaultReadParam();

            /**
             * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
             * 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。
             */
            Rectangle rect = new Rectangle(x, y, width, height);

            // 提供一个 BufferedImage，将其用作解码像素数据的目标。
            param.setSourceRegion(rect);

            /**
             * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将 它作为一个完整的
             * BufferedImage 返回。
             */
            BufferedImage bi = reader.read(0, param);

            // 保存新图片
            ImageIO.write(bi, "jpg", outfile);
        } finally {
            if (is != null) {
                is.close();
            }
            if (iis != null) {
                iis.close();
            }
        }
    }

    public static void main(String args[]) throws Exception {
        String srcImg = "E:\\GitWorkspace\\SHDIPP_SVR\\attachment\\shdipp\\null\\2019-09-09\\Capture001.png";
        String tarDir = "E:\\GitWorkspace\\SHDIPP_SVR\\attachment\\shdipp\\null\\2019-09-09\\u.jpg";
        resize(srcImg, tarDir);
    }
}  
