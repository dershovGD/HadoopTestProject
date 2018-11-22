import java.io.*;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Producer producer = new Producer();
        Socket clientSocket = new Socket("127.0.0.1", 44444);
        OutputAppender appender = new OutputAppender();
        final int numberToTest = Integer.parseInt(args[0]);
        Logger slf4jLogger = LoggerFactory.getLogger("some-logger");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            for (int i = 0; i < numberToTest; i++) {
                Record record = producer.produce();
                slf4jLogger.info("Writing record №=" + i + "  " + record.csvFormat() + " to stream");
                appender.tcpSend(record, bufferedWriter);
                slf4jLogger.info(bufferedReader.readLine());
            }
        }
    }
}
