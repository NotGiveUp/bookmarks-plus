package com.sysq;

import com.fasterxml.jackson.core.type.TypeReference;
import com.intellij.ide.util.PropertiesComponent;
import com.sysq.model.BookMarks;
import com.sysq.util.JsonUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Collections;
import java.util.List;

/**
 * @author linjingze
 * @date 2021/11/26 9:27 上午
 */
public class BookMarksManager {

    public static final String PROPERTY_FILE_LOCATION = "com.sysq.plugins.bks";

    public static String getFolderPath() {
        return PropertiesComponent.getInstance().getValue(PROPERTY_FILE_LOCATION, System.getProperty("user.home") + System.getProperty("file.separator") + ".ideabks");
    }

    public static File getDataFile() {
        File dataFile = null;
        File fileLocationFolder = new File(getFolderPath());
        try {
            if (!fileLocationFolder.exists()) {
                if (fileLocationFolder.mkdir()) {
                    dataFile = new File(fileLocationFolder, "bks.json");
                    createIfNoExist(dataFile);
                }
            } else {
                dataFile = new File(fileLocationFolder, "bks.json");
                createIfNoExist(dataFile);

            }
        } catch (IOException e) {
            dataFile = null;
        }
        return dataFile;
    }

    public static File getNewDataFile() {
        File dataFile = null;
        File fileLocationFolder = new File(getFolderPath());
        try {
            if (!fileLocationFolder.exists()) {
                if (fileLocationFolder.mkdir()) {
                    dataFile = new File(fileLocationFolder, "bks.json");
                    createNewFile(dataFile);
                }
            } else {
                dataFile = new File(fileLocationFolder, "bks.json");
                createNewFile(dataFile);

            }
        } catch (IOException e) {
            dataFile = null;
        }
        return dataFile;
    }

    private static void createIfNoExist(File dataFile) throws IOException {
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }

    }

    private static void createNewFile(File dataFile) throws IOException {
        if (dataFile.exists()) {
            dataFile.delete();
        }
        dataFile.createNewFile();
    }

    public static boolean saveData(List<BookMarks> bookMarksList) {
        File dataFile = getNewDataFile();
        if (dataFile != null) {
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dataFile, false), "UTF-8"))) {
                writer.write(JsonUtils.serialize(bookMarksList));
                writer.flush();
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    public static List<BookMarks> readData() {
        try {
            return JsonUtils.deserialize(FileUtils.readFileToString(getDataFile(), "UTF-8"), new TypeReference<List<BookMarks>>() {
            });
        } catch (IOException e) {
            return null;
        }
    }


}
