import java.io.*;
import java.net.Socket;
import java.util.Calendar;

/**
 * Created by Владислав on 01.09.2015.
 */
@SuppressWarnings("deprecation")
public class Server implements Runnable {
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String userName;

    public Server(String userName, Socket socket) throws IOException{
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());
        this.userName = userName;
    }

    public void _online(){
        out.println("Now online: ");
        out.flush();
        if(ServerThread.clients > 1) {
            for (String s : ServerThread.socketMap.keySet()) {
                if (!s.equals(userName)) {
                    out.println(s);
                    out.flush();
                }
            }
        }
        else {
            out.println("<nobody>" + '\n');
            out.flush();
        }
    }

    public void _commands(){
        out.println("To work with Server you can you these commands: ");
        out.println("_private <username>");
        out.println("_help");
        out.println(" _time");
        out.println("_online" + '\n');
        //out.println("_files");
        //out.println("_download <filename>" + '\n');
        out.flush();
    }

    @Override
    public void run() {
        String fromClient;

        out.println("Welcome to the Server, " + userName + "!" + '\n');

        out.println("Current time is: " + Calendar.getInstance().getTime() + '\n');
        out.flush();


        _commands();
        _online();

        ServerThread.sendAll(userName, "<Client connected>");

        while (true){
            try {
                while ((fromClient = in.readLine())!= null){
                    String []mas = fromClient.split(" ");
                    switch (mas[0]){
                        case "_private":{
                            if(!userName.equals(mas[1])) {
                                if (ServerThread.socketMap.containsKey(mas[1])) {
                                    PrintWriter privateOut = new PrintWriter(ServerThread.socketMap.get(mas[1]).getOutputStream());
                                    out.println("You are in a private chat with: " + mas[1]);
                                    out.println("Type _all to return to the public chat");
                                    out.flush();
                                    while (!(fromClient = in.readLine()).equals("_all")) {
                                        privateOut.println(userName + ": (only for you) : " + fromClient);
                                        out.println(userName + ": (only for " + mas[1] + ") :" + fromClient);

                                        privateOut.flush();
                                        out.flush();
                                    }
                                    out.println("You are in the public chat");
                                    out.flush();

                                    break;

                                } else {
                                    out.println("There is no such user!");
                                    out.flush();

                                    break;
                                }
                            } else {
                                out.println("You cannot add yourself to private chat");
                                out.flush();

                                break;
                            }
                        }

                        case "_help":{
                            _commands();
                            break;
                        }

                        case "_time":{
                            out.println(Calendar.getInstance().getTime().toString());
                            out.flush();
                            break;
                        }

                        case "_online":{
                            _online();
                            break;
                        }

                        default:{
                            ServerThread.sendAll(userName, fromClient);
                            break;
                        }
                    }
                }

        }catch (IOException e){
                ServerThread.clients--;
                ServerThread.sendAll(userName, "<Client disconnected>");
                ServerThread.appendArea("Client " + userName + " disconnected" + '\n');
                ServerThread.socketMap.remove(userName);
                Thread.currentThread().stop();
            }
        }
    }
}
