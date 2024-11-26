package card.game;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.net.ServerSocket;

public class LBServer {
    private ServerSocket socket;
    private Scanner scanner;


    public LBServer(ServerSocket socket) throws IOException {
        this.socket = socket;
    }



    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(54321);
        LBServer server = new LBServer(socket);
        //Socket[] clients = new Socket[4];
        Scanner[] scanners = new Scanner[4];
        PrintWriter[] writers = new PrintWriter[4];
        for (int i=0; i<4; i++) {
            Socket client = socket.accept();
            scanners[i] = new Scanner(client.getInputStream());
            writers[i] = new PrintWriter(client.getOutputStream());
        }
    }
}