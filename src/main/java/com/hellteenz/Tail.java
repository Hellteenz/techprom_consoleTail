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
        voidTail.fileParsing(args);
        for (String inputFile : voidTail.files) {
            String inputName = inputFile + ".txt";
            FileReader input = new FileReader(inputName);
            Scanner scan = new Scanner(input);
            if (voidTail.files.size() > 1) {
                System.out.println(inputFile);
            }
            voidTail.commandChecker(voidTail, voidTail.command, scan);
            input.close();
        }
    }

    public void fileParsing(String[] args) throws Error {
        boolean commandCheck = false;
        boolean isOutputName = false;
        for (int comInd = 0; comInd < args.length; comInd++) {
            Matcher matcherFileName = Pattern.compile("[A-z._\\d]*").matcher(args[comInd]);
            Matcher matcherNum = Pattern.compile("\\d").matcher(args[comInd]);
            if (Objects.equals(args[comInd], "-c") && !commandCheck) {
                command = "c";
                commandCheck = true;
            }
            else if (Objects.equals(args[comInd], "-n") && !commandCheck) {
                command = "n";
                commandCheck = true;
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
            else if (matcherNum.matches()) {
                comNum = Integer.parseInt(args[comInd]);
            }
            else throw new Error("Parse Error");
        }
    }
    public void commandChecker(Tail voidTail, String command, Scanner scan) throws IOException {
        List<String> fileData = new ArrayList<>();
        while (scan.hasNextLine()) {
            fileData.add(scan.nextLine());
        }
        FileWriter outputFile = new FileWriter(output);
        if (Objects.equals(command, "c")) {
            voidTail.commandC(fileData, voidTail.comNum, outputFile);
        }
        if (Objects.equals(command, "n")) {
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
            lineSize += lineInd;
            symbolsStr.append(files.get(lineInd));
            symbolsStr.append(" ");
        }
        int extraSymbols = lineSize - num;
        outputFile.write(symbolsStr.substring(extraSymbols));
        outputFile.close();
    }
    public void commandN(List<String> linesList, int num, FileWriter outputFile) throws IOException {
        for (int lineInd = linesList.size() - num; lineInd < linesList.size(); lineInd++) {
            outputFile.write(linesList.get(lineInd));
        }
        outputFile.close();
    }
    public void nullCommand(List<String> linesList, FileWriter outputFile) throws IOException {
        for (int lineInd = linesList.size() - 10; lineInd < linesList.size(); lineInd++) {
            outputFile.write(linesList.get(lineInd));
        }
        outputFile.close();
    }
}
