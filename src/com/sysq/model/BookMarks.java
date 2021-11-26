package com.sysq.model;

import java.sql.PreparedStatement;

/**
 * @author linjingze
 * @date 2021/11/24 9:40 下午
 */
public class BookMarks {

    private String desc;

    private String remark;

    private String fileName;

    private Integer bookMarksLine;

    private String filePath;

    public BookMarks(String desc) {
        this.desc = desc;
    }

    public BookMarks() {
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getBookMarksLine() {
        return bookMarksLine;
    }

    public void setBookMarksLine(Integer bookMarksLine) {
        this.bookMarksLine = bookMarksLine;
    }

    @Override
    public String toString() {
        return "BookMarks{" +
                "desc='" + desc + '\'' +
                ", remark='" + remark + '\'' +
                ", fileName='" + fileName + '\'' +
                ", bookMarksLine=" + bookMarksLine +
                '}';
    }
}
