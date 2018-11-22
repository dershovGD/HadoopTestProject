import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static final int numberToTest = 30000;
    private static final Path output = Paths.get("/Users/dershov/Documents/output1.csv");


    public static void main(String[] args) throws IOException, InterruptedException {
        Producer producer = new Producer();
        Socket clientSocket = new Socket("127.0.0.1", 44444);
        OutputWriter writer = new OutputWriter();
//        Thread clientThread = new Thread(() -> {
        try (OutputStreamWriter bufferedWriter = new OutputStreamWriter(clientSocket.getOutputStream())) {
            for (int i = 0; i < numberToTest; i++) {
                Record record = producer.produce();
                System.out.println("Writing record №="+i + "  " + record.csvFormat() + " to stream");
                writer.tcpSend(record, bufferedWriter);
                Thread.sleep(200);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        });
//        clientThread.start();

    }
}
