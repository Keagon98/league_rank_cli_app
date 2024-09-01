package com.spandigital.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileReaderService {

    /**
     * @description This method reads the text file from the give file path
     * @param scanner
     * @return BufferedReader
     */
    public static BufferedReader readFile(Scanner scanner) {
        boolean isValidFile = false;
        BufferedReader reader = null;

        do {
            try {
                var filePath = scanner.nextLine();
                reader = new BufferedReader(new FileReader(filePath));
                isValidFile = true;
            }  catch (IOException e) {
                System.out.println("File not found: Please enter a valid file path!");
            }
        } while (!isValidFile);

        return reader;
    }
}
