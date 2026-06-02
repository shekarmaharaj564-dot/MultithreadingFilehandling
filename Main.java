package com.filehandling;

public class Main {

    public static void main(String[] args) {

        String outputFile = "src/log.txt";

        FileProcessingThread file1 =
                new FileProcessingThread("src/input1.txt", outputFile);

        FileProcessingThread file2 =
                new FileProcessingThread("src/input2.txt", outputFile);

        FileProcessingThread file3 =
                new FileProcessingThread("src/input3.txt", outputFile);

        file1.start();
        file2.start();
        file3.start();

        try {

            file1.join();
            file2.join();
            file3.join();

            System.out.println("All files processed successfully.");

        } catch (InterruptedException e) {

            System.out.println("Main thread interrupted.");
            e.printStackTrace();
        }
    }
}