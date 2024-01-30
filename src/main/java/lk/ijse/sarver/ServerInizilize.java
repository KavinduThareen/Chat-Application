package lk.ijse.sarver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerInizilize {
    private static ArrayList<Server> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket =new ServerSocket(3002);

        Socket accept;

        while (true){
            System.out.println("waiting for client.....");
            accept= serverSocket.accept();
            System.out.println("Client Connected");
            Server clientThread =new Server(accept,clients);
            clients.add(clientThread);
            clientThread.start();
        }
    }
}
