package com.company;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

final class FileWrapperInt  extends FileWrapper{
    private static final Logger logger = Logger.getLogger(FileWrapperInt.class.getName());
    private Integer prevNum;
    private final String fileName;
    FileWrapperInt(boolean isAscending, String fileName) throws IOException {
        super(isAscending, fileName);
        this.fileName = fileName;
    }


    private Integer nextLineInt(){
        String cur = null;
        Integer number= null;
        try {
            cur=reader.readLine();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Exception: "+ e.getMessage());

        }

        if (cur!=null){

            try {
                number = Integer.valueOf(cur);
            } catch (NumberFormatException ex)
            {
                logger.log(Level.SEVERE, "Exception: "+ ex.getMessage());
            }

            if (number == null)
                return null;






            if (prevNum != null){
                if (isAscending)
                {
                    if (prevNum>number)
                    {
                        logger.log(Level.WARNING , "strings in "+ fileName +" incorrectly sorted");
                        return null;

                    }




                }
                else {
                    if (number>prevNum) {

                        logger.log(Level.WARNING, "strings in " + fileName + " incorrectly sorted");
                        return null;
                    }
                }
            }
            prevNum = number ;


        }
        else isFileEnded = true;
        return number;

    }

    public Integer nextValidInt(){
        Integer res = null;
        while(res == null){
            if (isFileEnded){
                if (null != reader) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        logger.log(Level.SEVERE, "Exception: " + e.getMessage());
                    }
                }
                return null;

            }


            res = nextLineInt();

        }

        return res;

    }



}
