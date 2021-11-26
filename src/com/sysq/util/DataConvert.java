package com.sysq.util;

import com.sysq.model.BookMarks;

public class DataConvert {

    public static String[] convert(BookMarks bookMarks) {
        String[] row = new String[5];
        row[0] = bookMarks.getDesc();
        row[1] = bookMarks.getFileName();
        row[2] = String.valueOf(bookMarks.getBookMarksLine());
        return row;
    }
}