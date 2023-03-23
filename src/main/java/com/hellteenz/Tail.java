package com.hellteenz;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Tail {
    private final List<String> files = new ArrayList<>();
    private String output;
    private String command;
    private int comNum;

    public static void main(String[] args) throws IOException {
        Tail voidTail = new Tail();
        voidTail.fileParsing(voidTail, args);
   }

    public void fileParsing (Tail voidTail, String[] args) throws IOException {
        voidTail.requestParsing(args);
        FileWriter outputFile = new FileWriter(output);
        for (String inputFile : voidTail.files) {
            String inputName = inputFile + ".txt";
            FileReader input = new FileReader(inputName);
            Scanner scan = new Scanner(input);
            if (voidTail.files.size() > 1) {
                outputFile.write(inputName + "\n");
            }
            voidTail.commandChecker(voidTail, voidTail.command, scan, outputFile);
            input.close();
            outputFile.write("\n");
        }
        outputFile.close();
    }

    public void requestParsing(String[] args) throws Error {
        boolean commandCheck = false;
        boolean isOutputName = false;
        for (int comInd = 0; comInd < args.length; comInd++) {
            Matcher matcherFileName = Pattern.compile("[A-z._\\d]*").matcher(args[comInd]);
            Matcher matcherNum = Pattern.compile("\\d*").matcher(args[comInd]);
            if (Objects.equals(args[comInd], "-c") && !commandCheck) {
                command = "c";
                commandCheck = true;
            }
            else if (Objects.equals(args[comInd], "-n") && !commandCheck) {
                command = "n";
                commandCheck = true;
            }
            else if (matcherNum.matches()) {
                comNum = Integer.parseInt(args[comInd]);
            }
            else if (matcherFileName.matches() && !Objects.equals(args[comInd - 1], "-o") && !matcherNum.matches()) {
                files.add(args[comInd]);
            }
            else if (Objects.equals(args[comInd], "-o")) {
                isOutputName = true;
            }
            else if (matcherFileName.matches() && isOutputName) {
                output = args[comInd];
                isOutputName = false;
            }
            else throw new Error("Parse Error");
        }
    }
    public void commandChecker(Tail voidTail, String command, Scanner scan, FileWriter outputFile) throws IOException {
        List<String> fileData = new ArrayList<>();
        while (scan.hasNextLine()) {
            fileData.add(scan.nextLine());
        }
        if (Objects.equals(command, "c")) {
            voidTail.commandC(fileData, voidTail.comNum, outputFile);
        }
        else if (Objects.equals(command, "n")) {
            voidTail.commandN(fileData, voidTail.comNum, outputFile);
        }
        else {
            voidTail.nullCommand(fileData, outputFile);
        }
    }
    public void commandC(List<String> linesList, int num, FileWriter outputFile) throws IOException {
        int lineSize = 0;
        StringBuilder symbolsStr = new StringBuilder();
        for (int lineInd = linesList.size() - 1; lineSize < num; lineInd--) {
            lineSize += linesList.get(lineInd).length();
            symbolsStr.append(linesList.get(lineInd));
            symbolsStr.append(" ");
        }
        int extraSymbols = lineSize - num;
        outputFile.write(String.valueOf(symbolsStr.substring(extraSymbols)));
    }
    public void commandN(List<String> linesList, int num, FileWriter outputFile) throws IOException {
        for (int lineInd = linesList.size() - num; lineInd < linesList.size(); lineInd++) {
            outputFile.write(linesList.get(lineInd) + "\n");
        }
    }
    public void nullCommand(List<String> linesList, FileWriter outputFile) throws IOException {
        for (int lineInd = linesList.size() - 10; lineInd < linesList.size(); lineInd++) {
            outputFile.write(linesList.get(lineInd) + "\n");
        }
    }
}
