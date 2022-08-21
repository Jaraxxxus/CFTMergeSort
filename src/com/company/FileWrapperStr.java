package com.company;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

final class FileWrapperStr extends FileWrapper{
    private static final Logger logger = Logger.getLogger(FileWrapperStr.class.getName());
    private final String fileName;

    String prevStr = null;
    FileWrapperStr(boolean isAscending, String fileName) throws IOException {
        super(isAscending, fileName);
        this.fileName = fileName;
    }



    public final String nextValidString(){
        String res = null;

        while(res == null){
            if (isFileEnded){
                if (null != reader) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace(System.err);
                    }
                }
                return null;

            }


            res = nextLineStr();

        }

        return res;

    }



    private String nextLineStr(){
        String cur = null;
        try {
            cur=reader.readLine();
        } catch (IOException e) {
            logger.log(Level.WARNING, "can't read from file"+ fileName);

        }

        if (cur!=null){

            if(cur.contains(" "))
            {
                logger.log(Level.WARNING, "the string in file"+ fileName+" contains spaces" );
                return null;
            }

            if (prevStr != null){
                if (isAscending)
                {
                    if (prevStr.compareTo(cur)>0) {
                        logger.log(Level.WARNING , "strings in "+ fileName +" incorrectly sorted");
                        return null;

                    }

                }
                else {
                    if (cur.compareTo(prevStr)>0)
                    {
                        logger.log(Level.WARNING , "strings in "+ fileName +" incorrectly sorted");
                        return null;
                    }
                }
            }
            prevStr = cur ;


        }
        else isFileEnded = true;
        return cur;

    }
}
