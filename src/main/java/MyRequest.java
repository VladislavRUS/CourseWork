import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Владислав on 08.11.2015.
 */
public class MyRequest {
    public static String sendPOST(String password, String hardNumber) {
        try {
            String response = "";
            String POST_PARAMS = "pass=" + password + "&hards=" + hardNumber;
            URL obj = new URL("http://localhost:8103/CourseWork/Checker");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");

            // For POST only - START
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(POST_PARAMS.getBytes());
            os.flush();
            os.close();

            // For POST only - END
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response = inputLine;
                }

                in.close();
            } else {
                response = "POST request not worked";
            }
            return response;
        }catch (IOException e){
            return "Failed";
        }
    }

    public static String checkUser(String userName, String userPassword) {
        try {
            String response = "";
            String POST_PARAMS = "username=" + userName + "&userpass=" + userPassword;
            URL obj = new URL("http://localhost:8103/CourseWork/Checker");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");

            // For POST only - START
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(POST_PARAMS.getBytes());
            os.flush();
            os.close();
            // For POST only - END

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response = inputLine;
                }

                in.close();
            } else {
                response = "POST request not worked";
            }
            return response;
        }catch (IOException e){
            return "Failed";
        }
    }
}
