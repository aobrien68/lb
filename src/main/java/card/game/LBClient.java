package card.game;

import java.io.IOException;
import java.net.Socket;

public class LBClient extends Duplexer{
    
    public LBClient(Socket socket) throws IOException {
        super(socket);
    }



    public static void main(String[] args) throws IOException {
        LBClient client = new LBClient(new Socket("localhost", 54321));
        
    }
}