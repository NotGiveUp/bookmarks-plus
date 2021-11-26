package com.sysq.data;

import com.sysq.model.BookMarks;
import com.sysq.util.DataConvert;
import org.apache.commons.collections.CollectionUtils;

import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author linjingze
 * @date 2021/11/25 1:06 下午
 */
public class DataCenter {

    public static int LINE;

    public static String FILE_NAME;

    public static String FILE_PATH;

    public final static LinkedList<BookMarks> BOOKMARKS_LIST = new LinkedList<>();

    public static List<BookMarks> SEARCH_RESULT_LIST;

    public static String[] HEADERS = {"描述", "文件名", "行号"};

    public final static DefaultTableModel DEFAULT_TABLE = new DefaultTableModel(null, HEADERS);

    public static void search(String search) {
        reset();
        SEARCH_RESULT_LIST = BOOKMARKS_LIST.stream()
                .filter(b -> b.getDesc().contains(search)).collect(Collectors.toList());
        for (BookMarks bookMarks : SEARCH_RESULT_LIST) {
            DEFAULT_TABLE.addRow(DataConvert.convert(bookMarks));
        }
    }

    public static List<BookMarks> getBookMarks(){
        return SEARCH_RESULT_LIST == null ? BOOKMARKS_LIST : SEARCH_RESULT_LIST;
    }


    public static void reset() {
        DEFAULT_TABLE.setDataVector(null, HEADERS);
    }

    public static void reload() {
        reset();
        for (BookMarks bookMarks : BOOKMARKS_LIST) {
            DEFAULT_TABLE.addRow(DataConvert.convert(bookMarks));
        }
        SEARCH_RESULT_LIST = null;
    }

    public static void reload(List<BookMarks> initList) {
        reset();
        for (BookMarks bookMarks : initList) {
            DEFAULT_TABLE.addRow(DataConvert.convert(bookMarks));
        }
    }

    public static void init(List<BookMarks> initList) {
        if (CollectionUtils.isEmpty(initList)) {
            return;
        }
        reset();
        BOOKMARKS_LIST.clear();
        for (BookMarks bookMarks : initList) {
            BOOKMARKS_LIST.add(bookMarks);
            DEFAULT_TABLE.addRow(DataConvert.convert(bookMarks));
        }
    }

}
