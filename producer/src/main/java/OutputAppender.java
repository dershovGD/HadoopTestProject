import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class OutputAppender {

    public void writeToFile(Record record, Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        byte[] bytes = record.csvFormat().append(System.lineSeparator()).toString().getBytes();
        Files.write(path, bytes, StandardOpenOption.APPEND);
    }

    public void tcpSend(Record record, Writer writer) throws IOException {
            final String s = record.csvFormat().append(System.lineSeparator()).toString();
            writer.append(s);
            writer.flush();
    }
}
