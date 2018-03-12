package work.model.service;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {

	private String reciever;
	private Pattern mailPattern = Pattern.compile("([A-Za-z0-9]+[@][a-zA-Z0-9]+[.][A-Za-z0-9]+)");
	private String naverID = "hana5638";
	private String naverPW = "gksk1111";
	

	public JavaMail(String sendTo)  {
		Matcher matcher = mailPattern.matcher(sendTo);
		if (!matcher.find()) {
			System.out.println("[" + sendTo + "]맞는 양식이 아닙니다.");
		}
		this.reciever = sendTo;
	}

	public String getReciever() {
		return this.reciever;
	}

	public boolean sendMail(String title, String contents, boolean isHTML) {
		try {
			Message mm = new MimeMessage(getSession());
			mm.setFrom(new InternetAddress(naverID+"@naver.com"));
			mm.setRecipient(Message.RecipientType.TO, new InternetAddress(reciever));
			mm.setSubject(title);
			if (isHTML) {
				mm.setContent(contents, "text/html; charset=utf-8");
			} else {
				mm.setText(contents);
			}
			Transport.send(mm);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	private Session getSession() throws NotLoggedInException {
		if (naverID == null || naverPW == null) {
			throw new NotLoggedInException();
		}
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.naver.com");
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.naver.com");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(naverID, naverPW);
			}
		});
		return session;
	}
}


class NotLoggedInException extends Exception {
	private static final long serialVersionUID = 1L;
	public NotLoggedInException() {
		super();
	}
}
