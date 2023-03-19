package com.hellteenz;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        if (voidTail.files.size() > 1) {
            for (String inputFile : voidTail.files) {
                String inputName = inputFile + ".txt";
                FileReader input = new FileReader(inputName);
                Scanner scan = new Scanner(input);
                System.out.println(inputFile);
                voidTail.commandChecker(voidTail, voidTail.command, scan);
                input.close();
            }
        }
        if (voidTail.files.size() == 1) {
            String inputName = voidTail.files.get(0) + ".txt";
            FileReader input = new FileReader(inputName);
            Scanner scan = new Scanner(input);
            voidTail.commandChecker(voidTail, voidTail.command, scan);
            input.close();
        }
        else throw new FileNotFoundException("Input data hasn't any files");
    }

    public void fileParsing(String[] args) throws Error {
        boolean commandCheck = false;
        for (String data: args) {
            Matcher matcherC = Pattern.compile("\\[-c \\d\\]").matcher(data);
            Matcher matcherN = Pattern.compile("\\[-n \\d\\]").matcher(data);
            Matcher matcherFileName = Pattern.compile("[A-z]*").matcher(data);
            Matcher matcherO = Pattern.compile("\\[-o [A-z]\\]").matcher(data);
            if (matcherC.matches() && !commandCheck) {
                command = "c";
                comNum = data.charAt(4);
                commandCheck = true;
            }
            if (matcherN.matches() && !commandCheck) {
                command = "n";
                comNum = data.charAt(4);
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
    public void commandChecker(Tail voidTail, String command, Scanner scan) throws Error, IOException {
        List<String> fileData = new ArrayList<>();
        while (scan.hasNextLine()) {
            fileData.add(scan.nextLine());
        }
        FileWriter outputFile = new FileWriter(output);
        if (command == "c") {
            voidTail.commandC(fileData, voidTail.comNum, outputFile);
        }
        if (command == "n") {
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
