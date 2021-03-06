package com.actions;

import com.calculations.SumUpCalculator;
import com.presenter.ConsolePresenter;
import com.transactions.TransactionReader;
import com.views.SummarizeView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class SummarizeAction extends BaseAction {
    private ConsolePresenter presenter;
    private TransactionReader transactionReader;

    public SummarizeAction() throws IOException {
        this.presenter = new ConsolePresenter(new SummarizeView());
        this.transactionReader = new TransactionReader();
    }

    @Override
    public boolean invoke(String actionName) {
        if (actionName.equals("summarize")) {
            presenter.getShow();
            this.displaySummary();
            return true;
        }
        return invokeNext(actionName);
    }

    public void setTransactionReader(TransactionReader transactionReader) {
        this.transactionReader = transactionReader;
    }

    private void displaySummary() {
        try {
            var transactions =  this.transactionReader.readFile();
            var calculator = new SumUpCalculator(transactions);
            calculator.printSummary();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("First you have to add transactions!");
        }
    }
}
