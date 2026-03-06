import java.io.*;
import java.net.*;
import java.util.*;

/**
 * ChatServer - Multi-threaded chat server
 */
public class ChatServer {
    
    private static final int PORT = 59001;
    private static Vector<PrintWriter> clientWriters = new Vector<>();
    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("   Lehman Multi-Platform Chat Server");
        System.out.println("===========================================");
        
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("ChatServer started on port " + PORT);
            System.out.println("Waiting for clients...");
            System.out.println();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New connection from: " + clientSocket.getRemoteSocketAddress());
                new ClientHandler(clientSocket).start();
            }
            
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String clientName;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                out.println("SERVER: Enter your name: ");
                clientName = in.readLine();
                if (clientName == null || clientName.trim().isEmpty()) {
                    clientName = "Anonymous";
                }
                
                synchronized (clientWriters) {
                    clientWriters.add(out);
                }

                broadcast("SERVER: " + clientName + " has joined the chat!");
                System.out.println(clientName + " joined the chat.");
                
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.equalsIgnoreCase("QUIT")) {
                        break;
                    }
                    broadcast(clientName + ": " + message);
                }

            } catch (IOException e) {
            } finally {
                cleanup();
            }
        }

        private void broadcast(String msg) {
            synchronized (clientWriters) {
                for (PrintWriter w : clientWriters) {
                    w.println(msg);
                }
            }
        }

        private void cleanup() {
            if (clientName != null) {
                synchronized (clientWriters) {
                    clientWriters.remove(out);
                }
                broadcast("SERVER: " + clientName + " has left the chat.");
                System.out.println(clientName + " left the chat.");
            }
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (socket != null) socket.close();
            } catch (IOException e) { }
        }
    }
}