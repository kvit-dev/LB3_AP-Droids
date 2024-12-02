package utils;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class BattleFile {
    private Scanner scanner;

    public BattleFile(Scanner scanner) {
        this.scanner = scanner;
    }

    private String getFileName() {
        System.out.println("Enter the file name: ");
        String fname = scanner.next() + ".txt";

        if (fname.trim().isEmpty()) {
            System.out.println(Colors.RED + "Invalid file name" + Colors.RESET);
            return null;
        }
        return fname;
    }

    public void saveBattleFile(List<String> battleLog) {
        if (battleLog.isEmpty()) {
            System.out.println(Colors.RED + "No battles to save here!" + Colors.RESET);
            return;
        }

        String fileName = getFileName();
        if (fileName == null) return;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String log : battleLog) {
                writer.write(log);
                writer.newLine();
            }
            System.out.println(Colors.GREEN + "Battle saved successfully to " + fileName + Colors.RESET);
        } catch (IOException e) {
            System.out.println(Colors.RED + "Error. The battle wasn't saved to file: " + e.getMessage() + Colors.RESET);
        }
    }

    public void loadBattleFile(List<String> battleLog) {
        String fileName = getFileName();
        if (fileName == null) return;

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println(Colors.RED + "File " + fileName + " does not exist!" + Colors.RESET);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            battleLog.clear();
            while ((line = reader.readLine()) != null) {
                battleLog.add(line);
                System.out.println(line);
            }
            System.out.println(Colors.GREEN + "Battle loaded successfully from " + fileName + Colors.RESET);
        } catch (IOException e) {
            System.out.println(Colors.RED + "Error. The battle cannot be loaded from file: " + e.getMessage() + Colors.RESET);
        }
    }
}
