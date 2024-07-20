import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ChatApplication {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        Scanner scanner = new Scanner(System.in);

        try {
            serverSocket = new ServerSocket(8080);
            System.out.println("Server started. Waiting for client to connect...");

            socket = serverSocket.accept();
            System.out.println("Client connected");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                System.out.println("Enter message: ");
                String message = scanner.nextLine();

                out.println(message);
                System.out.println("Message sent");

                String response = in.readLine();
                System.out.println("Response from client: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
