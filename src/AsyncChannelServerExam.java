import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import java.util.concurrent.Future;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;

public class AsyncChannelServerExam {

    public static void main (String [] args)
            throws Exception {

        new AsyncChannelServerExam().go();
    }

    private void go()
            throws IOException, InterruptedException, ExecutionException {

        AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 3883);
        serverChannel.bind(hostAddress);

        System.out.println("Server channel bound to port: " + hostAddress.getPort());
        System.out.println("Waiting for client to connect... ");

        Future acceptResult = serverChannel.accept();
        AsynchronousSocketChannel clientChannel = (AsynchronousSocketChannel) acceptResult.get();

        System.out.println("Messages from client: ");

        if ((clientChannel != null) && (clientChannel.isOpen())) {

            while (true) {

                ByteBuffer buffer = ByteBuffer.allocate(32);
                Future result = clientChannel.read(buffer);

                while (! result.isDone()) {
                    // do nothing
                }

                buffer.flip();
                String message = new String(buffer.array()).trim();
                System.out.println(message);

                if (message.equals("Bye.")) {

                    break; // while loop
                }

                buffer.clear();

            } // while()

            clientChannel.close();

        } // end-if

        serverChannel.close();
    }
}