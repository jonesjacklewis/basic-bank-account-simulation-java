package com.jackljones.www.basicBankAccountSimulator.interfaces;

public interface IScanner {
    public String nextLine();
    public boolean hasNextLine();
    public void close();
}
