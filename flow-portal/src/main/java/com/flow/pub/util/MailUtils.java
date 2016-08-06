package com.flow.pub.util;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
public class MailUtils {

	/**
	 * 发送邮件
	 * 
	 * @param smtpServer
	 *            邮件服务器
	 * @param userName
	 *            登录的用户名
	 * @param password
	 *            登录的密码
	 * @param fromMail
	 *            发送人地址
	 * @param mainTo
	 *            对方邮箱地址
	 * @param mailSubject
	 *            邮件主题
	 * @param mailContent
	 *            邮件内容
	 * @param attachments
	 *            附件 List<String> 附件路径
	 * @return
	 */
	public static boolean sendMails(String smtpServer, String userName, String passWord, String fromMail,
			String mailTo, String mailSubject, String mailContent, List<String> attachments)
			throws UnsupportedEncodingException {
		// 创建邮件Session所需的Properties对象
		Properties props = new Properties();
		props.put("mail.smtp.host", smtpServer);
		props.put("mail.smtp.auth", "true");
		final String username = userName;
		final String password = passWord;
		// 创建Session对象
		Session session = Session.getDefaultInstance(props
		// 以匿名内部类的形式创建登录服务器的认证对象
				, new Authenticator() {
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		session.getDebug();
		try {
			// 构造MimeMessage并设置相关属性值
			MimeMessage msg = new MimeMessage(session);
			// 设置发件人
			msg.setFrom(new InternetAddress(fromMail));
			// 设置收件人 发给多个收件人以";"区分开
			String[] mailto = mailTo.split(";");
			InternetAddress[] addresses = new InternetAddress[mailto.length];
			for (int i = 0; i < mailto.length; i++) {
				if (null != mailto[i] && !"".equals(mailto) && checkEmail(mailto[i])) {
					addresses[i] = new InternetAddress(mailto[i]);
				}
			}
			// InternetAddress[] addresses = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, addresses);
			// 设置邮件主题
			// subject = transferChinese(subject);
			msg.setSubject(mailSubject);
			// 构造Multipart
			Multipart mp = new MimeMultipart();
			// 向Multipart添加正文
			MimeBodyPart mbpContent = new MimeBodyPart();
			mbpContent.setText(mailContent);
			// 将BodyPart添加到MultiPart中
			mp.addBodyPart(mbpContent);
			// 向Multipart添加附件
			// 遍历附件列表，将所有文件添加到邮件消息里
			if (null != attachments) {
				for (String efile : attachments) {
					MimeBodyPart mbpFile = new MimeBodyPart();
					// 以文件名创建FileDataSource对象
					FileDataSource fds = new FileDataSource(efile);
					// 处理附件
					mbpFile.setDataHandler(new DataHandler(fds));
					// 中文文件名处理
					String filename = new String(MimeUtility.encodeText(fds.getName(), "GBK", "B"));
					mbpFile.setFileName(filename);
					// 将BodyPart添加到MultiPart中
					mp.addBodyPart(mbpFile);
				}
				// 清空附件列表
				attachments.clear();
			}
			// 向Multipart添加MimeMessage
			msg.setContent(mp);
			// 设置发送日期
			msg.setSentDate(new Date());
			// 发送邮件
			Transport.send(msg);
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		}
		return true;
	}

	// 把邮件主题转换为中文
	@SuppressWarnings("unused")
	private static String transferChinese(String strText) {
		try {
			strText = MimeUtility.encodeText(new String(strText.getBytes(), "gb2312"), "gb2312", "b");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strText;
	}

	/**
	 * 验证邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
	
		boolean flag = sendMails("smtp.exmail.qq.com", "zoujiajian@9fbank.com.cn", "zjj108495",
				"zoujiajian@9fbank.com.cn", "1084958277@qq.com", "测试邮件标题！", "发送邮件测试！", null);
		if (flag)
			System.out.println("邮件发送成功");
	}
	
}