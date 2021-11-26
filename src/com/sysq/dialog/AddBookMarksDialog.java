package com.sysq.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.psi.PsiManager;
import com.intellij.ui.EditorTextField;
import com.sysq.data.DataCenter;
import com.sysq.model.BookMarks;
import com.sysq.util.DataConvert;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

import static com.sysq.data.DataCenter.BOOKMARKS_LIST;
import static com.sysq.data.DataCenter.DEFAULT_TABLE;

/**
 * @author linjingze
 * @date 2021/11/24 9:27 下午
 */
public class AddBookMarksDialog extends DialogWrapper {

    private EditorTextField desc;

    public AddBookMarksDialog() {
        super(true);
        setTitle("添加书签");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel jPanel = new JPanel(new BorderLayout());
        this.desc = new EditorTextField();
        jPanel.add(desc, BorderLayout.NORTH);
        return jPanel;
    }

    @Override
    protected JComponent createSouthPanel() {
        JPanel jPanel = new JPanel();
        JButton jButton = new JButton("添加");
        jButton.addActionListener(e -> {
            String descText = desc.getText();
            BookMarks bookMarks = new BookMarks();
            bookMarks.setBookMarksLine(DataCenter.LINE);
            bookMarks.setFileName(DataCenter.FILE_NAME);
            bookMarks.setFilePath(DataCenter.FILE_PATH);
            if ("".equals(descText)) {
                descText = bookMarks.getFileName() + ":" + (bookMarks.getBookMarksLine());
            }
            bookMarks.setDesc(descText);
            BOOKMARKS_LIST.add(bookMarks);
            DEFAULT_TABLE.addRow(DataConvert.convert(bookMarks));
            close(1);
        });
        jPanel.add(jButton);
        return jPanel;
    }

}
