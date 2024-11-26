package card.game;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Duplexer {
    private Socket socket;
    private Scanner scanner;
    private PrintWriter writer;



    public Duplexer(Socket socket) throws IOException {
        this.socket = socket;
        scanner = new Scanner(socket.getInputStream());
        writer = new PrintWriter(socket.getOutputStream());
    }



    public void send(String msg) {
        writer.println(msg);
        writer.flush();
    }

    public String read() {
        return scanner.nextLine();
    }

    public void close() throws IOException {
        socket.close();
        scanner.close();
        writer.close();
    }
}
