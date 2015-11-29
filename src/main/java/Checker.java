import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Владислав on 03.09.2015.
 */
@SuppressWarnings("deprecation")
public class Checker implements Runnable{
    private Socket socket;
    private Socket socketMain;

    public Checker(Socket socket, Socket socketMain){
        this.socket = socket;
        this.socketMain = socketMain;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String fromClient = in.readLine();
            String mas[] = fromClient.split(" ");

            if (DigestUtils.md5Hex((mas[0])).equals((mas[2]))) { //если хэши логинов равны
                ServerThread.appendArea("Logins are equal");
                if (DigestUtils.md5Hex((mas[1])).equals((mas[3]))) { //если хэши паролей одинаковы
                    ServerThread.appendArea("Passwords are equal");
                    String s = MyRequest.checkUser((mas[2]), (mas[3]));
                    System.out.println(s);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    if (s.equals("OK")) { //и если в запросе все совпадает
                        ServerThread.appendArea("Response says ok");
                        out.println("OK");
                        out.flush();

                        ServerThread.appendArea("Client " + mas[0] + " connected");

                        ServerThread.socketMap.put(mas[0], socketMain); //кладем в карту имя пользователя и сокет
                        new Thread(new Server(mas[0], socketMain)).start(); //и запускаем сервер для этого клиента
                        ServerThread.clients++;

                        socket.close();
                        in.close();
                        out.close();

                        Thread.currentThread().interrupt();
                    }
                    else {
                        out.println("Invalid login/password");
                        out.flush();

                        out.close();
                        in.close();
                    }
                } else {
                    out.println("Invalid login/password");
                    out.flush();

                    out.close();
                    in.close();
                }
            }
            else {
                out.println("Invalid login/password");
                out.flush();

                out.close();
                in.close();
            }

        } catch (IOException e) {
            ServerThread.appendArea("IOException checker");
            Thread.currentThread().stop();
        }
    }
}
