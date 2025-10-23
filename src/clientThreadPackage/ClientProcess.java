package clientThreadPackage;

import java.io.*;
import java.net.*;

public class ClientProcess extends Thread {
    private Socket socket;
    private int clientNumber;

    public ClientProcess(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
    }

    public void run() {
        try (
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true);
        ) {
            // Message de bienvenue + numéro d'ordre
            out.println("Bonjour ! Vous êtes le client n°" + clientNumber);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("[Client " + clientNumber + "] : " + message);

                // Répondre au client
                out.println("Serveur : message reçu → " + message);

                if (message.equalsIgnoreCase("bye")) {
                    out.println("Déconnexion du serveur... Au revoir !");
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("Erreur avec le client n°" + clientNumber + " : " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Erreur lors de la fermeture du socket client n°" + clientNumber);
            }
            System.out.println("Client n°" + clientNumber + " déconnecté.\n");
        }
    }
}
