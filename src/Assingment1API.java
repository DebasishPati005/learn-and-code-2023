import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Assingment1API {

    public static void main(String[] args) {

        JSONParser jsonParserObj = new JSONParser();
        String apiUrl = "https://api.jsonbin.io/v3/qs/653f79a954105e766fc8fd69";
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter email address: ");
        String emailId = scan.nextLine();
        System.out.print("Wait data is fetching ");

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                Object obj = jsonParserObj.parse(response.toString());
                // Get elements within the JSON structure which is provided by jsonbin.io
                // converted into JSON
                JSONObject jsonObject = (JSONObject) obj;
                // System.out.println(jsonObject.get("record") + "\n");

                // Get elements within the JSON structure which is provided by jsonbin.io
                JSONArray arrayOfEmails = (JSONArray) jsonObject.get("record");
                JSONObject matchedEmailObject = findEmailAddressDetails(arrayOfEmails, emailId);

                System.out.print("Success data is fetched! 😜");
                calculateAndPrintCarbonEmission(matchedEmailObject);

            } else {
                System.out.println("Failed to load Email Details\n Response code is: " + responseCode);
            }
            connection.disconnect();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        scan.close();
    }

    public static JSONObject findEmailAddressDetails(JSONArray emailRecordArray, String emailId) {
        JSONObject matchedEmailDetails = null;
        for (Object eachEmailRecord : emailRecordArray) {
            JSONObject emailDetail = (JSONObject) eachEmailRecord;
            String eachEmail = (String) emailDetail.get("email");

            if (eachEmail.equals(emailId)) {
                matchedEmailDetails = (JSONObject) eachEmailRecord;
            }
        }
        return matchedEmailDetails;
    }

    public static void calculateAndPrintCarbonEmission(JSONObject emailDetailObject) {
        if (emailDetailObject == null) {
            System.out.println("Sorry no email record found with your given id");
        } else {
            System.out.println("{\n" +
                    "source : " + getDomainFromEmail((String) emailDetailObject.get("email")) + "\n" +
                    "inbox : " + getInboxMailsCarbonEmission((long) emailDetailObject.get("inbox")) + " KG\n" +
                    "sent : " + getSentMailCarbonEmission((long) emailDetailObject.get("sent")) + " KG\n" +
                    "spam : " + getSpamMailsCarbonEmission((long) emailDetailObject.get("spam")) + " KG\n" +
                    "}");
        }
    }

    static String getDomainFromEmail(String emailId) {
        String wholeDomain = emailId.split("@")[1];
        return wholeDomain.length() > 1 ? wholeDomain.split("\\.")[0] : null;
    }

    static double getInboxMailsCarbonEmission(long inboxEmails) {
        return 0.25 * inboxEmails;
    }

    static double getSentMailCarbonEmission(long inboxEmails) {
        return 0.8 * inboxEmails;
    }

    static double getSpamMailsCarbonEmission(long inboxEmails) {
        return 2 * inboxEmails;
    }
}