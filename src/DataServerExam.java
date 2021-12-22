import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class DataServerExam {
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

                byte[] bytes = null;
                String message = null;

                InputStream is = socket.getInputStream();
                bytes = new byte[100];
                int readByteCount = is.read(bytes);

                message = new String(bytes, 0, readByteCount, "UTF-8");
                System.out.println("데이터 수신 완료: " + message);

                OutputStream os = socket.getOutputStream();
                message = "Hello Client";
                bytes = message.getBytes("UTF-8");

                os.write(bytes);
                os.flush();
                System.out.println("데이터 송신 완료");

                is.close();
                os.close();
                socket.close();
            }
        } catch (Exception e) { }

        if (!serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) { }
        }
    }
}
