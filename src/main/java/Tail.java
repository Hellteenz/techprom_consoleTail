import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Tail {
    private Pattern patternC = Pattern.compile("\\[-c \\d\\]");
    private Pattern patternN = Pattern.compile("\\[-n \\d\\]");
    private Pattern patternO = Pattern.compile("\\[-o [A-z]\\]");
    private Pattern patternFileName = Pattern.compile("[A-z]*");

    private List<String> files = new ArrayList<>();
    private String output;
    private Boolean commandCheck = false;
    private String command;

    public static void main(String[] args) {
        Tail voidTail = new Tail();
        voidTail.fileParsing(args);

    }

    public void fileParsing(String[] args) throws Error {
        for (String data: args) {
            Matcher matcherC = patternC.matcher(data);
            Matcher matcherN = patternN.matcher(data);
            Matcher matcherFileName = patternFileName.matcher(data);
            Matcher matcherO = patternO.matcher(data);
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
}
