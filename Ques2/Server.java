package Ques2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Server Started");

        while (true) {
            Socket server = serverSocket.accept();

            ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(server.getInputStream());

            String message = (String) ois.readObject();
            System.out.println("Message From Client : " + message);

            MapReduce mapReduce = new MapReduce(message);

            if (server.getLocalAddress().getHostAddress().equals("127.0.0.1")) {
                mapReduce.startWithVowel();
                mapReduce.sort();
                oos.writeObject(mapReduce.showOutput());
            } else if (server.getLocalAddress().getHostAddress().equals("127.0.0.2")) {
                mapReduce.isArticle();
                mapReduce.sort();
                oos.writeObject(mapReduce.showOutput());
            }
        }
    }
}
