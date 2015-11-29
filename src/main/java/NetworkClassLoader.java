import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Владислав on 29.11.2015.
 */
public class NetworkClassLoader extends ClassLoader {
    public Class findClass(String name) {
        byte[] b = loadClassData();
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData()  {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int data;
        try {
            Socket socket = new Socket(InetAddress.getByName("localhost"), 8098);
            InputStream inputStream = socket.getInputStream();
            while((data = inputStream.read()) != -1) {
                buffer.write(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toByteArray();
    }
}
