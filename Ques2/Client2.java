package Ques2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println("Client Connected ");
        Scanner scanner = new Scanner(System.in);

        while (true) {

            Socket socket = new Socket("127.0.0.2", 12345);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            System.out.print("Enter your message : ");
            String message = scanner.nextLine();
            oos.writeObject(message);

            String response = (String) ois.readObject();
            System.out.println("Response from server : " + response);
        }
    }
}
