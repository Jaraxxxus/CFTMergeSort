package com.company;


import com.company.utility.InputDataType;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandLineParser {
    private static final Logger logger = Logger.getLogger(CommandLineParser.class.getName());
    String[] args;
    public CommandLineParser(String[] args) {
        this.args = args;

    }



    public boolean parse() {
        boolean isCorrect = true;
        boolean ascFlag = false;
        boolean typeFlag = false;
        if (args.length < 3) {
           isCorrect = false;

        }else {
            if (args[0].equals("-a") || args[0].equals("-d") || args[1].equals("-a") || args[1].equals("-d")) {
                if ((args[0].equals("-a") || args[0].equals("-d")) && (args[1].equals("-a") || args[1].equals("-d")))
                    isCorrect = false;
                if (args[0].equals("-d")||args[1].equals("-d"))
                {
                    logger.log(Level.INFO, "sorting in descending order");
                    MergeSort.isAscending  = false;
                }
                else
                {
                    logger.log(Level.INFO, "sorting in ascending order");
                }

                ascFlag =true;
            }
            if (args[0].equals("-s") || args[0].equals("-i") || args[1].equals("-s") || args[1].equals("-i")) {
                if ((args[0].equals("-s") || args[0].equals("-i")) && (args[1].equals("-s") || args[1].equals("-i"))){
                    isCorrect = false;

                }


                if (args[1].equals("-s")|| args[0].equals("-s")){
                    MergeSort.dataType = InputDataType.String;
                } else {

                    MergeSort.dataType = InputDataType.Int;
                }
                logger.log(Level.INFO, "datatype is " + MergeSort.dataType);

                typeFlag = true;

            }
        }
        if (!isCorrect || !typeFlag){
            logger.log(Level.WARNING, "incorrect arguments"  );
            return false;
        }

        if (isCorrect && !ascFlag)
        {
            MergeSort.outputFile = args[1];
            MergeSort.inputFileNames.addAll(Arrays.asList(args).subList(2, args.length));
        } else {
            MergeSort.outputFile = args[2];
            MergeSort.inputFileNames.addAll(Arrays.asList(args).subList(3, args.length));
        }


        return true;


    }
}
