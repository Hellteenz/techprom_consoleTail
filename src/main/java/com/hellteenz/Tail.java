package com.hellteenz;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import static java.lang.System.out;

public class Tail {
    private int comNum;
    @Option(name = "-c", forbids = {"-n"})
    private int commandC;
    @Option(name = "-n", forbids = {"-c"})
    private int commandN;
    @Option(name = "-o")
    private String output;
    @Argument private List<String> files = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Tail voidTail = new Tail();
        voidTail.fileParsing(voidTail, args);
   }

    public void fileParsing (Tail voidTail, String[] args) throws IOException {
        voidTail.requestParsing(args);
        try (FileWriter outputFile = new FileWriter(output)) {
            for (String inputFile : voidTail.files) {
                String inputName = inputFile + ".txt";
                try (FileReader input = new FileReader("testInput/" + inputName)) {
                    Scanner scan = new Scanner(input);
                    if (voidTail.files.size() > 1) {
                        outputFile.write("\n" + inputName + "\n");
                    }
                    voidTail.commandChecker(voidTail, scan, outputFile);
                }
            }
        }
    }
    private void requestParsing(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try
        {
            parser.parseArgument(args);
        }
        catch (CmdLineException clEx)
        {
            out.println("ERROR: Unable to parse command-line options: " + clEx);
        }
    }
    public void commandChecker(Tail voidTail, Scanner scan, FileWriter outputFile) throws IOException {
        List<String> fileData = new ArrayList<>();
        while (scan.hasNextLine()) {
            fileData.add(scan.nextLine());
        }
        if (commandC != 0) {
            voidTail.commandC(fileData, commandC, outputFile);
        }
        else if (commandN != 0) {
            voidTail.commandN(fileData, commandN, outputFile);
        }
        else {
            voidTail.commandN(fileData, 10, outputFile);
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
        outputFile.write(symbolsStr.substring(extraSymbols).trim());
    }
    public void commandN(List<String> linesList, int num, FileWriter outputFile) throws IOException {
        for (int lineInd = linesList.size() - num; lineInd < linesList.size(); lineInd++) {
            outputFile.write(linesList.get(lineInd).trim());
            if (lineInd != linesList.size() - 1) outputFile.write("\r\n");
        }
    }
}
