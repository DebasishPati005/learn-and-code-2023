package FirstAssignment;

import java.util.Scanner;

import javax.mail.MessagingException;
import javax.mail.Store;

public class CarbonFootprint {
	public static final double CARBON_EMISSION_OF_INBOX_MAIL = 0.3;
	public static final double CARBON_EMISSION_OF_SPAM_MAIL = 0.7;
	public static final double CARBON_EMISSION_OF_SENT_MAIL = 0.4;

	public static int inboxMails;
	public static int spamMails;
	public static int sentMails;

	public static void main(String[] args) throws MessagingException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter email account details");
		System.out.println("Enter email address");
		String emailAddress = scan.next();
		System.out.println("Enter 12 letter app key without any space");
		String appKey = scan.next();

		Store storeInstance = ConnectionMailServer.getConnectionStoreInstance(emailAddress, appKey);

		inboxMails = storeInstance.getFolder("Inbox").getMessageCount();
		spamMails = storeInstance.getFolder("[Gmail]/Spam").getMessageCount();
		sentMails = storeInstance.getFolder("[Gmail]/Sent Mailmr.debasish005@gmail.com")
				.getMessageCount();

		printCarbonFootprint();

		scan.close();
	}

	private static void printCarbonFootprint() {
		System.out.println("{\n" + "inbox : " + inboxMails * CARBON_EMISSION_OF_INBOX_MAIL + " KG\n"
				+ "sent : " + sentMails * CARBON_EMISSION_OF_SENT_MAIL + " KG\n" + "spam : "
				+ spamMails * CARBON_EMISSION_OF_SPAM_MAIL + " KG\n" + "}");
	}

}

