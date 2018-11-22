import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Producer producer = new Producer();
        Socket clientSocket = new Socket("127.0.0.1", 44444);
        OutputAppender appender = new OutputAppender();
        final int numberToTest = Integer.parseInt(args[0]);


        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            for (int i = 0; i < numberToTest; i++) {
                Record record = producer.produce();
                System.out.println("Writing record №=" + i + "  " + record.csvFormat() + " to stream");
                appender.tcpSend(record, bufferedWriter);
                System.out.println(bufferedReader.readLine());
            }
        }
    }
}
