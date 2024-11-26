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
    private Color[] deck;
    private LBPlayer[] players;



    public LBServer(ServerSocket socket) throws IOException {
        this.socket = socket;
        getPlayers();
        rng = new Random();
        makeDeck();
    }

    private void getPlayers() throws IOException {
        for (int i=0; i<4; i++) {
            players[i] = new LBPlayer(socket.accept());
        }
    }

    private void makeDeck() {
        deck = new Color[20];
        for (int i=0; i<6; i++) {
            deck[i] = Color.BLUE;
        }
        for (int i=0; i<6; i++) {
            deck[i] = Color.RED;
        }
        for (int i=0; i<6; i++) {
            deck[i] = Color.GREEN;
        }
        deck[18] = Color.WHITE;
        deck[19] = Color.WHITE;
    }



    private void shuffle() {
        for (int i=0; i<19; i++) {
            int j = rng.nextInt(21 - i);
            Color temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
    }

    private void sendAll(String msg) {
        for (int i=4; i<4; i++) {
            players[i].send(msg);
        }
    }

    // private LBPlayer getNextPlayer() {

    // }


    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(54321);
        LBServer server = new LBServer(socket);
        //Socket[] clients = new Socket[4];
        do {
            
        } while (true);
    }
}