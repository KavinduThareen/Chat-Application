package lk.ijse.sarver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread{
    private ArrayList<Server> clients;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Server(Socket socket,ArrayList<Server> clients){
        try{
            this.socket=socket;
            this.clients=clients;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            String msg;
            while ((msg = reader.readLine()) != null) {
                if (msg.equalsIgnoreCase( "exit")) {
                    break;
                }
                for (Server cl : clients) {
                    cl.writer.println(msg);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                reader.close();
                writer.close();
                socket.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }
}
