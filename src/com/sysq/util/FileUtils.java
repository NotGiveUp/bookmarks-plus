package com.sysq.util;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.fileEditor.ex.FileEditorProviderManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * @author linjingze
 * @date 2021/11/25 4:35 下午
 */
public class FileUtils {

    public static void openFileInPanel(final String filePath, final Project project, final int line) {
        ApplicationManager.getApplication().invokeLater(() -> {
            VirtualFile file = LocalFileSystem.getInstance().refreshAndFindFileByPath(filePath);
            if (file != null && file.isValid()) {
                FileEditorProvider[] providers = FileEditorProviderManager.getInstance()
                        .getProviders(project, file);
                if (providers.length != 0) {
                    OpenFileDescriptor descriptor = new OpenFileDescriptor(project, file, line, 0);
                    FileEditorManager.getInstance(project).openTextEditor(descriptor, false);
                }
            }
        });
    }
}
