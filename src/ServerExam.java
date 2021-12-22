import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExam {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 33333));

            while (true) {
                System.out.println("연결 대기");

                Socket socket = serverSocket.accept();
                InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();

                System.out.println("연결 수락: " + isa.getHostName());
            }
        } catch (Exception e) { }

        if (!serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) { }
        }
    }
}
