package com.sysq.window;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.sysq.data.DataCenter;
import com.sysq.model.BookMarks;
import com.sysq.util.FileUtils;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * @author linjingze
 * @date 2021/11/25 12:54 下午
 */
public class BookmarksWindow {


    private JTextField tfDesc;
    private JTable tbBookMarks;
    private JPanel mainPanel;

    public BookmarksWindow(Project project, ToolWindow toolWindow) {
        init(project);
        tfDesc.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == 10) {
                    String descText = tfDesc.getText();
                    if (StringUtils.isNotBlank(descText)) {
                        DataCenter.search(descText);
                    }
                    else{
                        DataCenter.reload();
                    }
                }
            }
        });
    }

    public JPanel getContent() {
        return mainPanel;
    }


    private void init(Project project) {
        tbBookMarks.setModel(DataCenter.DEFAULT_TABLE);
        tbBookMarks.setEnabled(true);
        tbBookMarks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbBookMarks.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int iSelRow = tbBookMarks.getSelectedRow();
                BookMarks bookMarks = DataCenter.getBookMarks().get(iSelRow);
                if (bookMarks != null) {
                    FileUtils.openFileInPanel(bookMarks.getFilePath(), project, bookMarks.getBookMarksLine() - 1);
                }
            }
        });
        tbBookMarks.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == 0 || keyCode == KeyEvent.VK_BACK_SPACE) {
                    int selectedRow = ((JTable) e.getSource()).getSelectedRow();
                    BookMarks remove = DataCenter.getBookMarks().remove(selectedRow);
                    DataCenter.reload(DataCenter.getBookMarks());
                    DataCenter.BOOKMARKS_LIST.remove(remove);
                }
            }
        });

    }


}
