import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Tail {

    private List<String> files = new ArrayList<>();
    private String output;
    private Boolean commandCheck = false;
    private String command;

    public static void main(String[] args) throws FileNotFoundException {
        Tail voidTail = new Tail();
        voidTail.fileParsing(args);
        if (voidTail.files.size() > 1) {
            for (String inputFile : voidTail.files) {
                String inputName = inputFile + ".txt";
                FileReader input = new FileReader(inputName);
                System.out.println(inputFile);
                voidTail.commandChecker(voidTail, voidTail.command, input);
            }
        }
        if (voidTail.files.size() == 1) {
            String inputName = voidTail.files.get(0) + ".txt";
            FileReader input = new FileReader(inputName);
            voidTail.commandChecker(voidTail, voidTail.command, input);
        }
        else throw new FileNotFoundException("Input data hasn't any files");
    }

    public void fileParsing(String[] args) throws Error {
        for (String data: args) {
            Matcher matcherC = Pattern.compile("\\[-c \\d\\]").matcher(data);
            Matcher matcherN = Pattern.compile("\\[-n \\d\\]").matcher(data);
            Matcher matcherFileName = Pattern.compile("[A-z]*").matcher(data);
            Matcher matcherO = Pattern.compile("\\[-o [A-z]\\]").matcher(data);
            if (matcherC.matches() && !commandCheck) {
                command = "c";
                commandCheck = true;
            }
            if (matcherN.matches() && !commandCheck) {
                command = "n";
                commandCheck = true;
            }
            if (matcherFileName.matches()) {
                files.add(data);
            }
            if (matcherO.matches()) {
                output = data;
            }
            else throw new Error("Parse Error");
        }
    }
    public void commandChecker(Tail voidTail, String command, FileReader input) throws Error{
        if (command == "c") {
            voidTail.commandC(input);
        }
        if (command == "n") {
            voidTail.commandN(input);
        }
        else throw new Error("Wrong command");
    }
    public void commandC(FileReader input) {
    }
    public void commandN(FileReader input) {

    }
}
