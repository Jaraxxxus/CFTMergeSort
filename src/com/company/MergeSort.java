package com.company;

import com.company.utility.InputDataType;


import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MergeSort {
    private static final Logger logger = Logger.getLogger(MergeSort.class.getName());
    static boolean isAscending = true;
    static InputDataType dataType = null;
    static String outputFile;
    static List<String> inputFileNames = new ArrayList<>();
    public static void main(String[] args) {




        if (!new CommandLineParser(args).parse())
            return;
        logger.log(Level.INFO, "output file is " + outputFile);

        Sorter sorter = new Sorter(inputFileNames, dataType, isAscending);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile)))
        {
            while (!sorter.isEmpty())
            {
                String line;
                if (( line = sorter.getNextLine()) !=null)
                {
                    bw.write(line);
                    bw.newLine();
                }


            }

        }
        catch(IOException ex){
            //logger.log(Level.WARNING, "can't open " + outputFile);
            logger.log(Level.SEVERE, "Exception: ", ex);
        }




    }





}
