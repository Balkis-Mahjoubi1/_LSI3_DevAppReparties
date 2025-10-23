package clientPackage;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SimpleClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // changer par IP serveur pour test LAN
        int port = 1234;

        try (
            Socket socket = new Socket(serverAddress, port);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);
        ) {
            System.out.println("Connecté au serveur " + serverAddress + ":" + port);
            System.out.println("Serveur : " + in.readLine());

            String message;
            while (true) {
                System.out.print("Vous : ");
                message = sc.nextLine();
                out.println(message);

                String response = in.readLine();
                if (response == null) break;
                System.out.println(response);

                if (message.equalsIgnoreCase("bye")) break;
            }

        } catch (IOException e) {
            System.err.println("Erreur client : " + e.getMessage());
        }
    }
}
