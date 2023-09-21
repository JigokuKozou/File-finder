package ru.shchelkin;

import ru.shchelkin.algorithm.ControlSumAlgorithm;
import ru.shchelkin.algorithm.Md5Algorithm;
import ru.shchelkin.utils.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    private static final ConsoleInterface userInterface = new ConsoleInterface();
    private static final ControlSumAlgorithm controlSumAlgorithm = new Md5Algorithm();

    public static void main(String[] args) {
        int action = 0;

        do {
            try {
                action = userInterface.requestOption("Выберите действие:",
                        List.of("Получить контрольную сумму", "Поиск по контрольной сумме", "Выйти"));
            }
            catch (NumberFormatException e) {
                userInterface.showError("Ошибка: выбрано не число");
                continue;
            }

            if (action == 1) {
                String filePath = userInterface.requestString("Введите путь до файла: ");

                try {
                    // Создаем поток для чтения файла
                    FileInputStream fis = new FileInputStream(filePath);

                    userInterface.showMessage("Контрольная сумма: " + controlSumAlgorithm.getControlSum(fis));

                } catch (FileNotFoundException e) {
                    userInterface.showError("Ошибка: путь до файла не существует");
                } catch (RuntimeException e) {
                    userInterface.showError("Неизвестная ошибка: " + e.getMessage());
                    e.printStackTrace();
                }
            }

            if (action == 2) {
                String searchableControlSum  = userInterface.requestString("Введите контрольную сумму: ");
                String directoryPath  = userInterface.requestString("Введите путь до директории: ");
                userInterface.showMessage("Пожалуйста подождите. Сканирование...");

                File directory = new File(directoryPath);
                List<File> files = FileUtils.getAllFiles(directory);
                for (File file : files) {
                    try (FileInputStream fis = new FileInputStream(file)) {
                        if (searchableControlSum.equals(controlSumAlgorithm.getControlSum(fis))) {
                            userInterface.showMessage(file.getPath());
                        }
                    } catch (FileNotFoundException e) {
                        userInterface.showError("Ошибка: путь до файла не существует");
                    } catch (IOException e) {
                        userInterface.showError("Неизвестная ошибка: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
            userInterface.showMessage("==============================================================");
        }
        while (action != 3);

        userInterface.close();
    }
}