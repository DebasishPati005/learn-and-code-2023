package FirstAssignment;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class ConnectionMailServer {
	static Store getConnectionStoreInstance(String emailAddress, String appKey) {
		Properties mailProperties = new Properties();
		Store connectionStore = null;
		mailProperties.setProperty("mail.store.protocol", "imaps");
		mailProperties.setProperty("mail.imap.host", "smtp.gmail.com");
		mailProperties.setProperty("mail.imap.port", "465");
		mailProperties.put("mail.imaps.ssl.protocols", "TLSv1.2");
		mailProperties.put("mail.imaps.ssl.ciphersuites", "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256");
		Session sessionInstance = Session.getInstance(mailProperties);

		try {
			connectionStore = sessionInstance.getStore("imaps");
			connectionStore.connect("imap.gmail.com", emailAddress, appKey);
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return connectionStore;
	}
}
