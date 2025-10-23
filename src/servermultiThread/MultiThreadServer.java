package servermultiThread;

import java.io.*;
import java.net.*;
import clientThreadPackage.ClientProcess;

public class MultiThreadServer {

    public static void main(String[] args) {
        int port = 1234; 
        int clientCount = 0;

        System.out.println("=== TP3 : Serveur Multi-threads (Sockets TCP) ===");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur démarré sur le port " + port + ".");
            System.out.println("En attente des connexions clients...\n");

            while (true) {
                // Attente d'un client
                Socket clientSocket = serverSocket.accept();
                clientCount++;

                // Affichage des infos du client connecté
                System.out.println("Connexion du client n°" + clientCount +
                        " depuis " + clientSocket.getRemoteSocketAddress());

                // Création d’un thread pour gérer le client
                ClientProcess clientThread = new ClientProcess(clientSocket, clientCount);
                clientThread.start();
            }

        } catch (IOException e) {
            System.err.println("Erreur du serveur : " + e.getMessage());
        }
    }
}
