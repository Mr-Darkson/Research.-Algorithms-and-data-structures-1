package org.example;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class GenerateTests {
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        Scanner console = new Scanner(System.in);
        int x_size, y_size;
        System.out.println("Введите размеры матрицы (Ширина, высота)");
        y_size = console.nextInt();
        x_size = console.nextInt();
        System.out.println("Введите числовой разброс");
        int spread = console.nextInt();
        int [][] matrix = new int[y_size][x_size];
        matrix[0][0] = random.nextInt(spread);
        for(int y = 0; y < matrix.length; y++) {
            for(int x = 0; x < matrix[0].length; x++) {
                if(x == 0 && y == 0) {
                    continue;
                }

                if(y-1 < 0) {
                    matrix[y][x] = matrix[y][x-1] + random.nextInt(1,spread);
                } else if (x - 1 < 0) {
                    matrix[y][x] = matrix[y-1][x] + random.nextInt(1,spread);
                }
                else {
                    matrix[y][x] = Math.max(matrix[y-1][x], matrix[y][x-1]) + random.nextInt(1,spread);
                }
            }
        }

        for(int[] i : matrix) {
            for(int j : i) {
                System.out.print(j + " ");
            }
            System.out.println("\n");
        }
        System.out.println("Желаете сохранить в файл?");
        console.nextLine();
        if(console.nextLine().toLowerCase().equals("да")) {
            System.out.println("Введите название сохранения без расширения");
            String fileName = console.nextLine() + ".txt";
            FileWriter writer = new FileWriter(new File("tests/" + fileName));
            writer.write(y_size + " " + x_size + "\n");
            for(int[] i : matrix) {
                for(int j : i) {
                    writer.write(j + " ");
                }
                writer.write("\n");
            }
            writer.close();
        }

    }
}
