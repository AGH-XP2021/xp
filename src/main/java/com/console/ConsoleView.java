package com.console;

import com.transactions.IncorrectDataException;
import com.transactions.TransactionReader;
import com.transactions.TransactionSaver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private TransactionSaver transactionSaver;
    private TransactionReader transactionReader;
    private Scanner scanner;

    public ConsoleView() throws IncorrectDataException, IOException {
        this.transactionSaver = new TransactionSaver();
        this.transactionSaver.appendDataToFile(this.transactionSaver.getColumnNames());
        this.scanner = new Scanner(System.in);
    }

    public String menu() {
        return "\n" +
                "Choose action:\n" +
                "add - add transaction\n" +
                "read - read transactions\n" +
                "exit - exit console\n" +
                "ACTION: ";
    }

    public List<String> addTransaction() {
        List<String> transactionDetails = new ArrayList<>();

        System.out.println("Adding new transaction...");

        for (String column : transactionSaver.getColumnNames()) {
            System.out.print(column + ": ");
            transactionDetails.add(scanner.nextLine());
        }

        return transactionDetails;
    }

    public void readTransactions() {
        try {
            this.transactionReader = new TransactionReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Reading transactions...");
            for (String[] transaction : this.transactionReader.readFile()) {
                System.out.println(Arrays.toString(transaction));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("First you have to add transactions!");
        }
    }

    public void consoleLoop() {
        while(true){
            System.out.print(menu());
            switch (scanner.nextLine()) {
                case "add":
                    try {
                        transactionSaver.appendDataToFile(addTransaction());
                    } catch (IncorrectDataException e) {
                        System.out.println("Exception occurred" + e.toString());

                    } catch (IOException e) {
                        System.out.println("Failed write to file" + e.toString());
                    }
                    break;
                case "read":
                    readTransactions();
                    break;
                case "exit":
                    System.out.println("Program exited");
                    System.exit(0);
                    break;
                default:
                    System.out.println("No match for command, try again...");
                    break;
            }
        }
    }
}
