import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Владислав on 01.09.2015.
 */
public class ServerThread implements Runnable{
    public static JTextArea area;
    private static JScrollPane pane;
    public static Map<String, Socket> socketMap = new LinkedHashMap<>();
    public static int clients;

    public static void appendArea(String text){
        area.append(text + '\n');
    }

    /*
    public ServerThread (){
        JFrame frame = new JFrame("Server");
        frame.setLayout(new FlowLayout());
        frame.setSize(350, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        area = new JTextArea();
        area.setEditable(false);
        pane = new JScrollPane(area);
        pane.setPreferredSize(new Dimension(300, 320));
        frame.add(pane);

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        appendArea("Server Started");
        try{
            ServerSocket serverSocket = new ServerSocket(8777);
            ServerSocket serverSocketMain = new ServerSocket(8778);
            while(true){
                Socket socket = serverSocket.accept();
                Socket socketMain = serverSocketMain.accept();

                new Thread(new Checker(socket, socketMain)).start();
            }
        }catch (IOException e){
            appendArea("IOException ServerThread");
        }
    }

    public static void sendAll(String user, String message) {
        for(Socket s : socketMap.values()){
            try {
                PrintWriter out = new PrintWriter(s.getOutputStream());
                out.println(user + ": " + message);
                out.flush();
            }catch (IOException e){
                appendArea("IOException sendAll");
            }
        }
    }
*/
    @Override
    public void run() {
        JFrame frame = new JFrame("Server");
        frame.setLayout(new FlowLayout());
        frame.setSize(350, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        area = new JTextArea();
        area.setEditable(false);
        pane = new JScrollPane(area);
        pane.setPreferredSize(new Dimension(300, 320));
        frame.add(pane);

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        appendArea("Server Started");
        try{
            ServerSocket serverSocket = new ServerSocket(8777);
            ServerSocket serverSocketMain = new ServerSocket(8778);
            while(true){
                Socket socket = serverSocket.accept();
                Socket socketMain = serverSocketMain.accept();

                new Thread(new Checker(socket, socketMain)).start();
            }
        }catch (IOException e){
            appendArea("IOException ServerThread");
        }
    }

    public static void sendAll(String user, String message) {
        for(Socket s : socketMap.values()){
            try {
                PrintWriter out = new PrintWriter(s.getOutputStream());
                out.println(user + ": " + message);
                out.flush();
            }catch (IOException e){
                appendArea("IOException sendAll");
            }
        }
    }
}
