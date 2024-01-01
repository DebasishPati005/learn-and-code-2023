package com.assignment1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.mail.MessagingException;
import javax.mail.Store;

class EmailInfoRetriever {

    static String emailAddress;
    static String appKey;
    Map<String, Integer> EMAIL_INFO = new HashMap<String, Integer>() {
        {
            put(Constants.INBOX, 0);
            put(Constants.SPAM, 0);
            put(Constants.SENT, 0);
        }
    };

    Map<String, Integer> retrieveEmailInfo() {
        getUserInput();
        try {
            Store storeInstance = ConnectionMailServer.getConnectionStoreInstance(emailAddress, appKey);
            EMAIL_INFO.put(Constants.INBOX, storeInstance.getFolder(Constants.INBOX_FOLDER).getMessageCount());
            EMAIL_INFO.put(Constants.SPAM, storeInstance.getFolder(Constants.SPAM_FOLDER).getMessageCount());
            EMAIL_INFO.put(Constants.SENT, storeInstance.getFolder(Constants.SENT_FOLDER).getMessageCount());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return this.EMAIL_INFO;
    }

    private void getUserInput() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter email account details");
        System.out.println("Enter email address");
        emailAddress = scan.next();
        System.out.println("Enter 12 letter app key without any space");
        appKey = scan.next();
        scan.close();
    }
}