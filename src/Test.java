import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {

    public static void main(String[] args) {
        JSONParser jsonP = new JSONParser();
        try {
          String apiUrl = "https://api.jsonbin.io/v3/qs/653f6c0854105e766fc8f84f"; // Replace with the actual API URL
            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
          connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                Object obj = jsonP.parse(response.toString());
                // Get elements within the JSON structure which is provided by jsonbin.io converted into JSON 
                JSONObject jsonObject = (JSONObject) obj;
                JSONObject empolyeeDetails = (JSONObject) jsonObject.get("record");
                System.out.println(jsonObject.get("record") + "\n");

                // Get elements within the JSON structure which is provided by jsonbin.io
                String firstName = (String) empolyeeDetails.get("firstName");
                String lastName = (String) empolyeeDetails.get("lastName");

                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);

                JSONArray addressArray = (JSONArray) empolyeeDetails.get("address");
                for (Object addressObj : addressArray) {
                    JSONObject address = (JSONObject) addressObj;
                    String city = (String) address.get("city");
                    String street = (String) address.get("street");
                    String state = (String) address.get("state");
                    System.out.println("City: " + city);
                    System.out.println("Street: " + street);
                    System.out.println("State: " + state);
                }
            } else {
                System.out.println("API request failed with response code: " + responseCode);
            }
            connection.disconnect();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
