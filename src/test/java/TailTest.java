import com.hellteenz.Tail;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TailTest {
    String[] command;
    @Test
    void Essay() throws IOException {
        Tail testTail = new Tail();
        String commandToWap = "-c 26 -o outputFile1 Essay2";
        command = commandToWap.split(" ");
        testTail.fileParsing(testTail, command);
        assertEquals(-1, Files.mismatch(Path.of("outputFile1"), Path.of("outputCheck/test1_Essay2_26sym.txt")));
    }
    @Test
    void Quatar() throws IOException {
        Tail testTail = new Tail();
        String commandToWap = "-o outputFile2 Quatar";
        command = commandToWap.split(" ");
        testTail.fileParsing(testTail, command);
        assertEquals(-1, Files.mismatch(Path.of("outputFile2"), Path.of("outputCheck/test2_Quatar_nullCommand.txt")));
    }
    @Test
    void a_27() throws IOException {
        Tail testTail = new Tail();
        String commandToWap = "-c 48 -o outputFile3 27a";
        command = commandToWap.split(" ");
        testTail.fileParsing(testTail, command);
        assertEquals(-1, Files.mismatch(Path.of("outputFile3"), Path.of("outputCheck/test3_27a_48sym.txt")));
    }
    @Test
    void b_27() throws IOException {
        Tail testTail = new Tail();
        String commandToWap = "-n 68 -o outputFile4 27b";
        command = commandToWap.split(" ");
        testTail.fileParsing(testTail, command);
        assertEquals(-1, Files.mismatch(Path.of("outputFile4"), Path.of("outputCheck/test4_27b_68str.txt")));
    }
}
