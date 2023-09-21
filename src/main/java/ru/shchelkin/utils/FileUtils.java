package ru.shchelkin.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<File> getAllFiles(File directory) {
        List<File> fileList = new ArrayList<>();
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    fileList.addAll(getAllFiles(file));
                } else {
                    fileList.add(file);
                }
            }
        }
        return fileList;
    }
}
