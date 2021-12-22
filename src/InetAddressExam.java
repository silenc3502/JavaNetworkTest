import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressExam {
    public static void main(String[] args) {
        try {
            InetAddress local = InetAddress.getLocalHost();
            System.out.println("Localhost IP: " + local.getHostAddress());

            InetAddress[] iaArr = InetAddress.getAllByName("www.naver.com");

            for (InetAddress remote : iaArr) {
                System.out.println("www.naver.com IP: " + remote.getHostAddress());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
