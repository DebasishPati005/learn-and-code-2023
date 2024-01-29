package com.assignment1;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class ConnectionMailServer {
	static Store getConnectionStoreInstance(String emailAddress, String appKey) {
		Properties mailProperties = new Properties();
		Store connectionStore = null;

		updateMailProperties(mailProperties);
		Session sessionInstance = Session.getInstance(mailProperties);

		try {
			connectionStore = sessionInstance.getStore(Constants.MAIL_STORE_PROTOCOL_VALUE);
			connectionStore.connect(Constants.IMAP_GMAIL_COM, emailAddress, appKey);
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return connectionStore;
	}

	static void updateMailProperties(Properties mailProperties) {
		mailProperties.setProperty(Constants.MAIL_STORE_PROTOCOL, Constants.MAIL_STORE_PROTOCOL_VALUE);
		mailProperties.setProperty(Constants.MAIL_IMAP_HOST, Constants.MAIL_IMAP_HOST_VALUE);
		mailProperties.setProperty(Constants.MAIL_IMAP_PORT, Constants.MAIL_IMAP_PORT_VALUE);
		mailProperties.put(Constants.MAIL_IMAPS_SSL_PROTOCOLS, Constants.MAIL_IMAPS_SSL_PROTOCOLS_VALUE);
		mailProperties.put(Constants.MAIL_IMAPS_SSL_CIPHERSUITES, Constants.MAIL_IMAPS_SSL_CIPHERSUITES_VALUE);
	}
}
