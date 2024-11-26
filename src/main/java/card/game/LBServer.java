package card.game;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.net.ServerSocket;

public class LBServer {
    private ServerSocket socket;
    private Scanner scanner;
    private Random rng;


    public LBServer(ServerSocket socket) throws IOException {
        this.socket = socket;
        rng = new Random();
    }



    private void shuffle(Color[] deck) {
        for (int i=0; i<19; i++) {
            int j = rng.nextInt(21 - i);
            Color temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
    }

    // private LBPlayer getNextPlayer() {

    // }


    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(54321);
        LBServer server = new LBServer(socket);
        //Socket[] clients = new Socket[4];
        LBPlayer[] players = new LBPlayer[4];
        for (int i=0; i<4; i++) {
            players[i] = new LBPlayer(socket.accept());
        }
        Color[] deck = new Color[20];
        do {
            
        } while (true);
    }
}