package com.jackljones.www.basicBankAccountSimulator.utilities;

import com.jackljones.www.basicBankAccountSimulator.interfaces.IScanner;
import java.util.Scanner;

public class ScannerInputHandler implements IScanner {
    private Scanner scanner;

    public ScannerInputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String nextLine() {
        if(this.scanner == null) {
            throw new IllegalArgumentException("Scanner object cannot be null");
        }
        return this.scanner.nextLine();
    }

    @Override
    public boolean hasNextLine() {
        if(this.scanner == null) {
            throw new IllegalArgumentException("Scanner object cannot be null");
        }
        return this.scanner.hasNextLine();
    }

    @Override
    public void close() {
        if(this.scanner == null) {
            throw new IllegalArgumentException("Scanner object cannot be null");
        }
        this.scanner.close();
    }
}
