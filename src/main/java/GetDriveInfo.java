import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Владислав on 08.11.2015.
 */
public class GetDriveInfo {
    static String getSerialNumber(){
        String command = "cmd.exe /c wmic diskdrive get SerialNumber";
        Process process;
        BufferedReader inputStream;
        String line;
        try{
            process = Runtime.getRuntime().exec(command);
            inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
            for(int i = 0; i < 2; i++)
                inputStream.readLine();
            line = inputStream.readLine();
        } catch (IOException e) {
            return "Error";
        }
        return line;
    }
}
