# FileEncryptor

A simple Java-based file encryption tool that encrypts and decrypts files using a custom, math-heavy algorithm. The encryption works with human-readable characters, making it easy to test and view the output.

## Features

- **Custom Encryption Method**: Uses character shifting and XOR operations for encoding/decoding.
- **Encrypt/Decrypt Text Files**: Encrypts and decrypts files with human-readable content.
- **Cross-Platform**: Should work on any platform with Java installed.

---

## Getting Started

1. **Clone the repository**:
   ```bash
   git clone https://github.com/BobReed24/FileEncryptor.git
   cd FileEncryptor

2. **Build the project using Gradle**:
  ```bash
  ./gradlew build
  ```

3. **Run the program**:
  - To encrypt a file:
    ```bash
    java -cp build/classes/java/main com.FileEncryptor.FileEncryptor encrypt input.txt encrypted.txt
    ```
  - To decrypt a file:
    ```bash
    java -cp build/classes/java/main com.FileEncryptor.FileEncryptor decrypt encrypted.txt decrypted.txt
    ```

## How It Works
  - Encryption:
      The text is shifted by a value (e.g., +5), then XOR'd with a dynamic key based on the position in the file.

  - Decryption:
      The process is reversed by undoing the shift (-5) and applying the XOR with the same dynamic key.

This is a fun, non-secure encryption method for educational purposes, not suitable for real-world security.
