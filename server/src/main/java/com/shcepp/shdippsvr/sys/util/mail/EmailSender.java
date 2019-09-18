package com.shcepp.shdippsvr.sys.util.mail;


import javax.mail.Session;
import javax.mail.internet.MimeUtility;
import java.util.Properties;
import java.util.Vector;

public class EmailSender {
    
    private String to = "";// 收件人
    private String from = "";// 发件人
    private String host = "";// smtp主机
    private String username = "";// 邮箱账号
    private String password = "";// 邮箱密码
    private String filename = "";// 附件文件名
    private String subject = "";// 邮件主题
    private String content = "";// 邮件正文
    private Vector file = new Vector();// 附件文件集合
    
    /**
     * 　* <br>
     * 　　* 方法说明：默认构造器 <br>
     * 　　* 输入参数： <br>
     * 　　* 返回类型：
     */
    
    public EmailSender() {
    
    }
    
    /**
     * 　　* <br>
     * 　　* 方法说明：构造器，提供直接的参数传入 <br>
     * 　　* 输入参数： <br>
     * 　　* 返回类型：
     */
    
    public EmailSender(String to, String from, String smtpServer,
                       String username, String password, String subject, String content) {
        this.to = to;
        this.from = from;
        host = smtpServer;
        this.username = username;
        this.password = password;
        this.subject = subject;
        this.content = content;
    }
    
    /**
     * 　　* <br>
     * 　　* 方法说明：设置邮件服务器地址 <br>
     * 　　* 输入参数：String host 邮件服务器地址名称 <br>
     * 　　* 返回类型：
     */
    
    public void setHost(String host) {
        this.host = host;
    }
    
    /**
     * 　　* <br>
     * 　　* 方法说明：设置登录服务器校验密码 <br>
     * 　　* 输入参数： <br>
     * 　　* 返回类型：
     */
    
    public void setPassWord(String pwd) {
        password = pwd;
    }
    
    /**
     * 　　* <br>
     * 　　* 方法说明：设置登录服务器校验用户 <br>
     * 　　* 输入参数： <br>
     * 　　* 返回类型：
     */
    
    public void setUserName(String usn) {
        username = usn;
    }
    
    /**
     * 　　* <br>
     * 　　* 方法说明：设置邮件发送目的邮箱 <br>
     * 　　* 输入参数： <br>
     * 　　* 返回类型：
     */
    
    public void setTo(String to) {
        this.to = to;
    }
    
    /**
     * 　　* <br>
     * 　　* 方法说明：设置邮件发送源邮箱 <br>
     * 　　* 输入参数： <br>
     * 　　* 返回类型：
     */
    
    public void setFrom(String from) {
        this.from = from;
    }
    
    /**
     * 　　* <br>
     * 　　* 方法说明：设置邮件主题 <br>
     * 　　* 输入参数： <br>
     * 　　* 返回类型：
     */
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    /**
     * 　　* <br>
     * 　　* 方法说明：设置邮件内容 <br>
     * 　　* 输入参数： <br>
     * 　　* 返回类型：
     */
    
    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * 　　* <br>
     * 　　* 方法说明：把主题转换为中文 <br>
     * 　　* 输入参数：String strText <br>
     * 　　* 返回类型：
     */
    
    public String transferChinese(String strText) {
        try {
            strText = MimeUtility.encodeText(new String(strText.getBytes(),
                    "UTF-8"), "UTF-8", "B");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return strText;
    }
    
    /**
     * 　　* <br>
     * 　　* 方法说明：往附件组合中添加附件 <br>
     * 　　* 输入参数： <br>
     * 　　* 返回类型：
     */
    
    public void attachfile(String fname) {
        file.addElement(fname);
    }
    
    /**
     * 　　* <br>
     * 　　* 方法说明：发送邮件 <br>
     * 　　* 输入参数： <br>
     * 　　* 返回类型：boolean 成功为true，反之为false
     */
    
    public boolean sendMail() {
        // 构造mail session
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "false");
        
        Session session = Session.getDefaultInstance(props, null);
        try {
            
            MailUtils.sendEmail(from, host, username,
                    password, subject, content,
                    new String[]{to});
        }
        catch(Exception mex) {
            mex.printStackTrace();
            return false;
        }
        return true;
    }
    
    /**
     * 　　* <br>
     * 　　* 方法说明：主方法，用于测试 <br>
     * 　　* 输入参数： <br>
     * 　　* 返回类型：
     */
    
}