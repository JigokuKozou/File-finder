package ru.shchelkin.algorithm;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Algorithm implements ControlSumAlgorithm{

    // Объект MessageDigest для вычисления контрольной суммы
    private final MessageDigest md;

    public Md5Algorithm() {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getControlSum(FileInputStream fis) {
        try {
            // Создаем буфер для чтения файла
            byte[] buffer = new byte[1024];
            int bytesRead;

            // Читаем файл и обновляем контрольную сумму
            while ((bytesRead = fis.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }

            // Закрываем поток для чтения файла
            fis.close();

            // Получаем контрольную сумму в виде массива байт
            byte[] digest = md.digest();

            // Преобразуем массив байт в шестнадцатеричную строку
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
