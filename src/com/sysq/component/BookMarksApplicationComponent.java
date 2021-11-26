package com.sysq.component;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerListener;
import com.sysq.BookMarksManager;
import com.sysq.data.DataCenter;
import com.sysq.model.BookMarks;

import java.util.List;

/**
 * @author linjingze
 * @date 2021/11/26 10:03 上午
 */
public class BookMarksApplicationComponent implements ApplicationComponent {

    public void initComponent() {
        ProjectManager.getInstance().addProjectManagerListener(new ProjectManagerListener() {
            public void projectOpened(final Project project) {
                List<BookMarks> bookMarksList = BookMarksManager.readData();
                DataCenter.init(bookMarksList);
            }

            public void projectClosed(final Project project) {
                BookMarksManager.saveData(DataCenter.BOOKMARKS_LIST);
            }
        });
    }
}
