package com.sysq.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.VisualPosition;
import com.intellij.openapi.editor.event.SelectionEvent;
import com.intellij.openapi.editor.event.SelectionListener;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.impl.status.PositionPanel;
import com.sysq.data.DataCenter;
import com.sysq.dialog.AddBookMarksDialog;
import com.sysq.model.BookMarks;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PopupAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        // 选择模型
        //SelectionModel selectionModel = editor.getSelectionModel();
        LogicalPosition caret = editor.getCaretModel().getLogicalPosition();
        VirtualFile virtualFile = e.getRequiredData(CommonDataKeys.PSI_FILE).getVirtualFile();
        DataCenter.FILE_PATH = virtualFile.getPath();
        DataCenter.FILE_NAME = virtualFile.getName();
        DataCenter.LINE = caret.line + 1;
        //VisualPosition selectionStartPosition = selectionModel.getSelectionStartPosition();
        //if (selectionStartPosition != null) {
        //    DataCenter.LINE = selectionStartPosition.getLine();
        //}
        AddBookMarksDialog addBookMarksDialog = new AddBookMarksDialog();
        addBookMarksDialog.show();

    }
}
