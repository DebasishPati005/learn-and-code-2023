// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.Scanner;

public class Assignment1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter email address: ");
        String emaiAddress = scan.nextLine();
        System.out.print("Enter inbox  emails: ");
        int inboxEmails = scan.nextInt();
        System.out.print("Enter sent  emails: ");
        int sentEmails = scan.nextInt();
        System.out.print("Enter spam  emails: ");
        int spamEmails = scan.nextInt();
        
        String domainName = getDomainFromEmail(emaiAddress);
        double carbonEmissionByInbox = getInboxMailsCarbonEmission(inboxEmails);
        double carbonEmissionBySentMails = getSentMailCarbonEmission(sentEmails);
        double carbonEmissionBySpamMail = getSpamMailsCarbonEmission(spamEmails);

        System.out.println("{\n" +
        "source : " + domainName + "\n" +
        "inbox : " + carbonEmissionByInbox + " KG\n" +
        "sent : " + carbonEmissionBySentMails + " KG\n" +
        "spam : " + carbonEmissionBySpamMail + " KG\n" +
        "}");
    }
    
    static String getDomainFromEmail(String emailId){
    String wholeDomain = emailId.split("@")[1]; 
    return wholeDomain.length() > 1 ? wholeDomain.split("\\.")[0] : null;
    }
    
    static double getInboxMailsCarbonEmission(int inboxEmails){
        return 0.25 * inboxEmails;
    }
    
    static double getSentMailCarbonEmission(int inboxEmails){
        return 0.8 * inboxEmails;
    }
    
    static double getSpamMailsCarbonEmission(int inboxEmails){
        return 2 * inboxEmails;
    }
}