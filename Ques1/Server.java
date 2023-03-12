package Ques1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(6789);
        List<String> idList = new ArrayList<>();
        List<ObjectOutputStream> oosList = new ArrayList<>();
        List<Socket> socketList = new ArrayList<>();

        ObjectOutputStream oos = null;
        ObjectInputStream ois;

        for (int i = 0; i < 3; i++){
            Socket server = serverSocket.accept();

            oos = new ObjectOutputStream(server.getOutputStream());
            ois = new ObjectInputStream(server.getInputStream());

            String message = (String) ois.readObject();
            System.out.println("Value from Client : " + message);

            if (message.equals("NO")) {
                oos.writeObject("Client can not Join Election");
            } else {
                idList.add(message);
                oosList.add(oos);
                socketList.add(server);
            }
        }

        int max = 0;
        for (String id : idList) {
            int givenID = Integer.parseInt(id);

            if (givenID > max) {
                max = givenID;
            }
        }

        System.out.println("Election Start By : " + socketList.get(0).getLocalAddress().getHostAddress());

        for (ObjectOutputStream os : oosList) {
            os.writeObject("New Coordinator is " + max);
        }
    }
}
