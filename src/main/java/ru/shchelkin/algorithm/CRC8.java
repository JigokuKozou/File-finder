package ru.shchelkin.algorithm;

import java.io.FileInputStream;
import java.io.IOException;

public class CRC8 implements ControlSumAlgorithm {

    private static final int POLYNOMIAL = 0x07;
    private static final int INITIAL_VALUE = 0x00;

    @Override
    public String getControlSum(FileInputStream fis) {
        int crc = INITIAL_VALUE;

        try {
            int data;
            while ((data = fis.read()) != -1) {
                crc ^= data;
                for (int i = 0; i < 8; i++) {
                    if ((crc & 0x80) != 0) {
                        crc = (crc << 1) ^ POLYNOMIAL;
                    } else {
                        crc <<= 1;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.format("%02x", crc);
    }
}
