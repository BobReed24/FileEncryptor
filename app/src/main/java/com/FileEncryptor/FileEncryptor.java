package com.FileEncryptor;

import java.io.*;
import java.nio.file.*;

public class FileEncryptor {

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            System.out.println("Usage: encrypt|decrypt <inputFile> <outputFile>");
            return;
        }

        String command = args[0];
        Path inputPath = Paths.get(args[1]);
        Path outputPath = Paths.get(args[2]);

        if (command.equalsIgnoreCase("encrypt")) {
            encryptFile(inputPath, outputPath);
        } else if (command.equalsIgnoreCase("decrypt")) {
            decryptFile(inputPath, outputPath);
        } else {
            System.out.println("Unknown command.");
        }
    }

    private static void encryptFile(Path inputPath, Path outputPath) throws IOException {
        String data = new String(Files.readAllBytes(inputPath));
        String encrypted = customEncrypt(data);
        Files.write(outputPath, encrypted.getBytes());
        System.out.println("Encrypted to: " + outputPath);
    }

    private static void decryptFile(Path inputPath, Path outputPath) throws IOException {
        String data = new String(Files.readAllBytes(inputPath));
        String decrypted = customDecrypt(data);
        Files.write(outputPath, decrypted.getBytes());
        System.out.println("Decrypted to: " + outputPath);
    }

    // Custom encryption with human-readable characters
    private static String customEncrypt(String input) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int key = (31 + (i * 17)) % 256;  // Dynamic key
            // Shift the character and XOR with the key
            char encryptedChar = (char) (c + 5);  // Shift character
            encryptedChar = (char) (encryptedChar ^ key);  // XOR with key
            encrypted.append(encryptedChar);
        }
        return encrypted.toString();
    }

    // Custom decryption to reverse the encrypt method
    private static String customDecrypt(String input) {
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int key = (31 + (i * 17)) % 256;  // Same dynamic key
            // Reverse the XOR with the key
            char decryptedChar = (char) (c ^ key);  // XOR with key
            decryptedChar = (char) (decryptedChar - 5);  // Undo character shift
            decrypted.append(decryptedChar);
        }
        return decrypted.toString();
    }
}
