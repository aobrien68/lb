package card.game;

import java.io.IOException;
// import java.io.PrintWriter;
// import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
// import java.util.Scanner;
import java.net.ServerSocket;

public class LBServer {
    private ServerSocket socket;
    private Random rng;
    private Color[] deck;
    private LBPlayer[] players;
    private int currentPlayerIndex;



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

    private LBPlayer nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % 4;
        while (true) {
            LBPlayer player = players[currentPlayerIndex];
            if (player.isAlive())
                return player;
            currentPlayerIndex = (currentPlayerIndex + 1) % 4;
        }
    }

    private LBPlayer getPreviousPlayer() {
        int i = (currentPlayerIndex - 1) % 4;
        while (true) {
            LBPlayer player = players[i];
            if (player.isAlive())
                return player;
            i = (i - 1) % 4;
        }
    }

    private int getAliveCount() {
        int count = 0;
        for (LBPlayer player: players) {
            if (player.isAlive())
                count++;
        }
        return count;
    }

    private void dealHands() {
        shuffle();
        for (int i=0; i<getAliveCount(); i++) {
            LBPlayer player = nextPlayer();
            ArrayList<Color> hand = new ArrayList<>();
            for (int j=0; j<5; j++) {
                hand.add(deck[i * 5 + j]);
            }
            player.setHand(hand);
        }
    }


    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(54321);
        LBServer server = new LBServer(socket);
        while (server.getAliveCount() > 1) {
            server.dealHands();
            //while ()
        }
    }
}