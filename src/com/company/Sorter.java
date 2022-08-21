package com.company;

import com.company.utility.InputDataType;

import java.io.IOException;
import java.util.LinkedList;

import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sorter {
    private static final Logger logger = Logger.getLogger(MergeSort.class.getName());
    private boolean isMapEmpty = true;
    private final TreeMap mp;
    private final boolean isAscending;
    private final InputDataType dataType;
    Sorter(List<String> inputFileNames, InputDataType dataType, boolean isAscending)
    {

        this.dataType = dataType;
        mp = createMap(dataType);



        this.isAscending = isAscending;
        for (String file : inputFileNames)
        {
            if (dataType.equals(InputDataType.Int))
            {
                try{
                    FileWrapperInt curWrap = new FileWrapperInt(isAscending, file);
                    Integer curInt = curWrap.nextValidInt();
                    if (!curWrap.isFileEnded){
                        addToMap(curInt,  curWrap);
                        isMapEmpty = false;
                    }




                }
                catch (IOException e) {
                    logger.log(Level.WARNING, "can't open "+ file);
                }
            }
            else {
                try{
                    FileWrapperStr curWrap = new FileWrapperStr(isAscending, file);
                    String curStr = curWrap.nextValidString();
                    if (!curWrap.isFileEnded){
                        addToMap(curStr,  curWrap);
                        isMapEmpty = false;
                    }







                }
                catch (IOException e) {
                    logger.log(Level.WARNING, "can't read from  file"+ file);
                }
            }
        }

    }

    public String getNextLine(){
        String res = null;

        if (mp.isEmpty()){
            isMapEmpty = true;
            return null;

        }
        if (dataType.equals( InputDataType.String))
        {
            LinkedList<FileWrapperStr> curList;
            FileWrapperStr curWrapper;
            if (isAscending) {
                res = (mp.firstKey().toString());
            }
            else{
                res = (mp.lastKey().toString());
            }

            curList = (LinkedList<FileWrapperStr>) mp.get(res);
            curWrapper = curList.getFirst();
            curList.removeFirst();

            if (curList.isEmpty())
                mp.remove(res);
            else
                mp.put(res, curList);
            String nextLine = curWrapper.nextValidString();
            if (nextLine!=null)
            {
                addToMap(nextLine, curWrapper);
            }
        }
        if (dataType.equals( InputDataType.Int))
        {
            Integer curNum = null;
            LinkedList <FileWrapperInt> curList;
            FileWrapperInt curWrapper;
            if (isAscending) {
                res = (mp.firstKey().toString());
            }
            else{
                res = (mp.lastKey().toString());
            }
            curNum = Integer.valueOf(res);

            curList = (LinkedList<FileWrapperInt>) mp.get(curNum);
            curWrapper = (FileWrapperInt) curList.getFirst();
            curList.removeFirst();

            if (curList.isEmpty())
                mp.remove(curNum);
            else
                mp.put(curNum , curList);
            Integer nextNum = curWrapper.nextValidInt();
            if (nextNum!=null)
            {
                addToMap(nextNum, curWrapper);
            }
        }


        return res;
    }

    private TreeMap createMap(InputDataType dataType)
    {
        if (dataType == InputDataType.Int)
            return new TreeMap<Integer, LinkedList<FileWrapperInt>>();
        if (dataType ==  InputDataType.String)
            return new TreeMap<String, LinkedList<FileWrapperStr>>();
        return null;
    }

    private void addToMap(String curStr, FileWrapperStr curWrap)
    {
        if (mp.get(curStr) == null) {
            LinkedList<FileWrapperStr> ll = new LinkedList<>();
            ll.add(curWrap);
            mp.put(curStr, ll);

        }
        else
        {
            LinkedList list;
            list = (LinkedList) mp.get(curStr);
            list.add(curWrap);
            mp.put(curStr, list);

        }
    }
    private void addToMap(Integer curInt, FileWrapperInt curWrap)
    {

        if (mp.get(curInt) == null) {

            LinkedList<FileWrapperInt> ll = new LinkedList<>();
            ll.add(curWrap);
            mp.put(curInt, ll);

        }
        else
        {
            LinkedList list;
            list = (LinkedList) mp.get(curInt);
            list.add(curWrap);
            mp.put(curInt, list);


        }
    }
    public boolean isEmpty()
    {
        return isMapEmpty;
    }


}
