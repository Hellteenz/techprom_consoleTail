import com.hellteenz.Tail;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TailTest {
    String[] command;
    @Test
    void WAP() throws IOException {
        Tail testTail = new Tail();
        String commandToWap = "-n 3 -o outputFile1 WAP";
        command = commandToWap.split(" ");
        testTail.fileParsing(testTail, command);
        assertEquals(-1, Files.mismatch(Path.of("outputFile1"), Path.of("outputCheck/test1_WAP_3str.txt")));
    }
    @Test
    void Essay() throws IOException {
        Tail testTail = new Tail();
        String commandToWap = "-c 26 -o outputFile2 Essay2";
        command = commandToWap.split(" ");
        testTail.fileParsing(testTail, command);
        assertEquals(-1, Files.mismatch(Path.of("outputFile2"), Path.of("outputCheck/test2_Essay2_26sym.txt")));
    }
    @Test
    void Quatar() throws IOException {
        Tail testTail = new Tail();
        String commandToWap = "-o outputFile3 Quatar";
        command = commandToWap.split(" ");
        testTail.fileParsing(testTail, command);
        assertEquals(-1, Files.mismatch(Path.of("outputFile3"), Path.of("outputCheck/test3_Quatar_nullCommand.txt")));
    }
    @Test
    void a_27() throws IOException {
        Tail testTail = new Tail();
        String commandToWap = "-c 48 -o outputFile4 27a";
        command = commandToWap.split(" ");
        testTail.fileParsing(testTail, command);
        assertEquals(-1, Files.mismatch(Path.of("outputFile4"), Path.of("outputCheck/test4_27a_48sym.txt")));
    }
    @Test
    void b_27() throws IOException {
        Tail testTail = new Tail();
        String commandToWap = "-n 68 -o outputFile5 27b";
        command = commandToWap.split(" ");
        testTail.fileParsing(testTail, command);
        assertEquals(-1, Files.mismatch(Path.of("outputFile5"), Path.of("outputCheck/test5_27b_68str.txt")));
    }
}
