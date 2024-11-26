package card.game;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class LBPlayer {
    private Socket socket;
    private Scanner scanner;
    private PrintWriter writer;
    private byte shots;
    private List<Color> hand;



    public LBPlayer(Socket socket) throws IOException {
        scanner = new Scanner(socket.getInputStream());
        writer = new PrintWriter(socket.getOutputStream());
    }



    public boolean isAlive() {
        return shots != 17;
    }

    public byte getShots() {
        return shots;
    }

    public void incrementShots() {
        shots++;
    }

    public void kill() {
        shots = 17;
        scanner.close();
    }

    public void send(String msg) {
        writer.println(msg);
    }

    public void setHand(List<Color> hand) {
        this.hand = hand;
    }

    public void close() throws IOException {
        socket.close();
        scanner.close();
        writer.close();
    }
}
