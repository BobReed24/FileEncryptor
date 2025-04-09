package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.Base64;

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
        byte[] data = Files.readAllBytes(inputPath);
        byte[] encrypted = customEncrypt(data);
        Files.write(outputPath, encrypted);
        System.out.println("Encrypted to: " + outputPath);
    }

    private static void decryptFile(Path inputPath, Path outputPath) throws IOException {
        byte[] data = Files.readAllBytes(inputPath);
        byte[] decrypted = customDecrypt(data);
        Files.write(outputPath, decrypted);
        System.out.println("Decrypted to: " + outputPath);
    }

    // Custom encryption: reverse + base64 encode
    private static byte[] customEncrypt(byte[] input) {
        byte[] reversed = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            reversed[i] = input[input.length - i - 1];
        }
        return Base64.getEncoder().encode(reversed);
    }

    // Custom decryption: base64 decode + reverse
    private static byte[] customDecrypt(byte[] input) {
        byte[] decoded = Base64.getDecoder().decode(input);
        byte[] reversed = new byte[decoded.length];
        for (int i = 0; i < decoded.length; i++) {
            reversed[i] = decoded[decoded.length - i - 1];
        }
        return reversed;
    }
}
