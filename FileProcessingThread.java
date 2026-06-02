package com.filehandling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessingThread extends Thread {

    private String inputFile;
    private String outputFile;

    public FileProcessingThread(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    @Override
    public void run() {

        int lineCount = 0;
        int wordCount = 0;

        try (
                BufferedReader br = new BufferedReader(new FileReader(inputFile));
                BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true))
        ) {

            String line;

            while ((line = br.readLine()) != null) {

                lineCount++;

                String[] words = line.trim().split("\\s+");

                if (!line.trim().isEmpty()) {
                    wordCount += words.length;
                }
            }

            synchronized (FileProcessingThread.class) {

                bw.write("File Name : " + inputFile);
                bw.newLine();

                bw.write("Lines : " + lineCount);
                bw.newLine();

                bw.write("Words : " + wordCount);
                bw.newLine();

                bw.write("--------------------------------");
                bw.newLine();

                bw.flush();
            }

            System.out.println(inputFile + " processed successfully.");

        } catch (IOException e) {

            System.out.println("Error processing file: " + inputFile);
            e.printStackTrace();
        }
    }
}