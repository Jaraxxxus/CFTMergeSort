package com.company;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


abstract class FileWrapper {
    protected final boolean isAscending;
    protected boolean isFileEnded = false;
    BufferedReader reader;
    FileWrapper(boolean isAscending, String fileName) throws IOException {
        this.isAscending = isAscending;

        FileReader fr = new FileReader(fileName);
        reader = new BufferedReader(fr);




    }












}
