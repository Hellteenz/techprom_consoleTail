import com.hellteenz.Tail;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TailTest {
    @Test
    void test() throws IOException {
        Tail testTail = new Tail();
        String commandToWap = "-n 3 -o outputFile WAP";
        testTail.fileParsing(testTail, commandToWap.split(" "));

    }
}
