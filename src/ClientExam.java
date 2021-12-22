import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientExam {
    public static void main(String[] args) {
        Socket socket = null;

        try {
            socket = new Socket();

            System.out.println("연결 요청");
            socket.connect(new InetSocketAddress("localhost", 33333));

            System.out.println("연결 성공");
        } catch (Exception e) { }

        if (!socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) { }
        }
    }
}
