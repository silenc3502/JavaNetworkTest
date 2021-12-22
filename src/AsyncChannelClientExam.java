import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncChannelClientExam {
    public static void main (String [] args)
            throws Exception {

        new AsyncChannelClientExam().go();
    }

    private void go()
            throws IOException, InterruptedException, ExecutionException {

        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 3883);
        Future future = client.connect(hostAddress);
        future.get(); // returns null

        System.out.println("Client is started: " + client.isOpen());
        System.out.println("Sending messages to server: ");

        String [] messages = new String [] {"Time goes fast.", "What now?", "Bye."};

        for (int i = 0; i < messages.length; i++) {

            byte [] message = new String(messages [i]).getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message);
            Future result = client.write(buffer);

            while (! result.isDone()) {
                System.out.println("... ");
            }

            System.out.println(messages [i]);
            buffer.clear();
            Thread.sleep(3000);
        } // for

        client.close();
    }
}
