import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class DataClientExam {
    public static void main(String[] args) {
        Socket socket = null;

        try {
            socket = new Socket();

            System.out.println("연결 요청");
            socket.connect(new InetSocketAddress("localhost", 33333));

            System.out.println("연결 성공");

            byte[] bytes = null;
            String message = null;

            OutputStream os = socket.getOutputStream();
            message = "Hello Server";
            bytes = message.getBytes("UTF-8");

            os.write(bytes);
            os.flush();

            System.out.println("데이터 전송 완료");

            InputStream is = socket.getInputStream();
            bytes = new byte[100];

            int readByteCount = is.read(bytes);
            message = new String(bytes, 0, readByteCount, "UTF-8");
            System.out.println("데이터 수신 성공: " + message);

            os.close();
            is.close();
        } catch (Exception e) { }

        if (!socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) { }
        }
    }
}
