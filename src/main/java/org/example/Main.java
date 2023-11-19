package org.example;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        //int[][] matrix = loadArray();
        /*
        System.out.println("Выберите поиск");
        int selectedSort = 1;
        System.out.println(1 + " - Simple Binary");
        System.out.println(2 + " - Binary RGHT TOP to LEFT FLOOR");
        System.out.println(3 + " - SECOND but with exp");
        int select = console.nextInt();

        if (select == 1 || select == 2 || select == 3) {
            selectedSort = select;
            System.out.println();
        } else {
            System.out.println("Такого поиска не существует. Выбран поиск по умолчанию (Simple Binary)");
        }

        System.out.println("Введите искомое число");
        int serchNumber = console.nextInt();
        */

        for(int x = 1; x <= 13; x++) {
            int m = (int)Math.pow(2, x);
            int n = (int)Math.pow(2,13);
            //first rule target = 2 * n + 1;
            //second rule target = 16 * n + 1;

            int serchNumber =16 * n + 1;
            System.out.println("Тест номер " + x);
            int[][] matrix = firstRule(m, n);
            binarySearch(matrix, serchNumber);
            ladderSearch(matrix, serchNumber);
            exponentSearch(matrix, serchNumber);

        }




        /*if (select == 2) {
            ladderSearch(matrix, console.nextInt());
        } else if (select == 3) {
            exponentSearch(matrix, console.nextInt());
        } else {
            binarySearch(matrix, console.nextInt());
        }*/


    }

    public static int[][] firstRule(int y, int x) {
        int[][] matrix = new int[y][x];
        for(int i = 0; i < y; i++) {
            for(int j = 0; j < x; j++) {
                matrix[i][j] = (x / y * i + j) * 2;
                //System.out.print(matrix[i][j] + " ");
            }
            //System.out.println("\n");
        }
        return matrix;
    }
    public static int[][] secondRule(int y, int x) {
        int[][] matrix = new int[y][x];
        for(int i = 0; i < y; i++) {
            for(int j = 0; j < x; j++) {
                matrix[i][j] = (x / y * i * j) * 2;
            }
        }
        return matrix;
    }

    public static int[][] loadArray() throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader("tests/test6.txt"));) {
            int[] size = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); //Height, Width
            int[][] matrix = new int[size[0]][size[1]];
            for (int i = 0; i < size[0]; i++) {
                String[] line = reader.readLine().split(" ");
                for (int j = 0; j < size[1]; j++) {
                    matrix[i][j] = Integer.parseInt(line[j]);
                }
            }

            System.out.println("Матрица успешно загружена!");
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
            return matrix;
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить матрицу");
        }

    }

    public static void binarySearch(int[][] matrix, int target) {
        long start = System.nanoTime();
        BigDecimal a = BigDecimal.valueOf(start);
        for (int row = 0; row < matrix.length; row++) {
            int result = Arrays.binarySearch(matrix[row], target);
            if (result >= 0) {
                System.out.println("Искомое число в колонке " + (row + 1) + " под индексом " + result + ". Это - " + matrix[row][result]);
                long finish = System.nanoTime();
                BigDecimal b = BigDecimal.valueOf(finish);
                System.out.println("Время выполнения: " + b.subtract(a).divide(BigDecimal.valueOf(1000)).toString() + " mcs");
                return;
            }
        }
        System.out.println("Число не найдено");
        long finish = System.nanoTime();
        BigDecimal b = BigDecimal.valueOf(finish);
        System.out.println("Время выполнения: " + b.subtract(a).divide(BigDecimal.valueOf(1000)).toString() + " mcs");
    }

    public static void ladderSearch(int[][] matrix, int target) {
        long start = System.nanoTime();
        BigDecimal a = BigDecimal.valueOf(start);
        int yPos = 0;
        int xPos = matrix[0].length - 1;
        while (true) {
            if (matrix[yPos][xPos] == target) {
                System.out.println("Искомое число в строке " + (yPos + 1) + " под индексом " + xPos + ". Это - " + matrix[yPos][xPos]);
                long finish = System.nanoTime();
                BigDecimal b = BigDecimal.valueOf(finish);
                System.out.println("Время выполнения: " + b.subtract(a).divide(BigDecimal.valueOf(1000)).toString() + " mcs");
                return;
            } else if (matrix[yPos][xPos] > target && xPos - 1 >= 0) {
                xPos -= 1;
            } else if (matrix[yPos][xPos] < target && yPos + 1 < matrix.length) {
                yPos += 1;
            } else {
                System.out.println("Число не найдено");
                long finish = System.nanoTime();
                BigDecimal b = BigDecimal.valueOf(finish);
                System.out.println("Время выполнения: " + b.subtract(a).divide(BigDecimal.valueOf(1000)).toString() + " mcs");
                return;
            }
        }
    }

    public static void exponentSearch(int[][] matrix, int target) {
        long start = System.nanoTime();
        BigDecimal a = BigDecimal.valueOf(start);
        int yPos = 0;
        int xPos = matrix[0].length - 1;
        int step = 1;
        while (true) {
            if (matrix[yPos][xPos] == target) {
                System.out.println("Искомое число в строке " + (yPos + 1) + " под индексом " + xPos + ". Это - " + matrix[yPos][xPos]);
                long finish = System.nanoTime();
                BigDecimal b = BigDecimal.valueOf(finish);
                System.out.println("Время выполнения: " + b.subtract(a).divide(BigDecimal.valueOf(1000)).toString() + " mcs");
                return;
            }
            if (matrix[yPos][xPos] > target) {
                if (xPos - step < 0) { //Если мы зашли за границу массива степом - просто проверяем весь массив бинарным поиском
                    int result = Arrays.binarySearch(matrix[yPos], 0, xPos, target);
                    if(result >= 0) { //Если f(x) -> a > 0, то нужное число есть в массиве
                        System.out.println("Искомое число в строке " + (yPos + 1) + " под индексом " + result + ". Это - " + matrix[yPos][result]);
                        long finish = System.nanoTime();
                        BigDecimal b = BigDecimal.valueOf(finish);
                        System.out.println("Время выполнения: " + b.subtract(a).divide(BigDecimal.valueOf(1000)).toString() + " mcs");
                        return;
                    }
                    else {
                        xPos = Math.abs(result)-2;
                        yPos++;
                        step = 1;
                    }
                }
                else if (matrix[yPos][xPos-step] < target) { //Если нашли меньшее число раньше - проверяем бинарным поиском кусок массива
                    int result = Arrays.binarySearch(matrix[yPos],xPos-step, xPos, target);
                    if(result >= 0) {
                        System.out.println("Искомое число в строке " + (yPos + 1) + " под индексом " + result + ". Это - " + matrix[yPos][result]);
                        long finish = System.nanoTime();
                        BigDecimal b = BigDecimal.valueOf(finish);
                        System.out.println("Время выполнения: " + b.subtract(a).divide(BigDecimal.valueOf(1000)).toString() + " mcs");
                        return;
                    }
                    else {
                        xPos = Math.abs(result)-2;
                        yPos++;
                        step = 1;
                    }
                }
                else {
                    step*=2;
                }

            }
            else if(matrix[yPos][xPos] < target) {
                yPos+=1;
            }
            if(xPos == 0 && matrix[yPos][xPos] > target || yPos >= matrix.length) {
                System.out.println("Число не найденно");
                long finish = System.nanoTime();
                BigDecimal b = BigDecimal.valueOf(finish);
                System.out.println("Время выполнения: " + b.subtract(a).divide(BigDecimal.valueOf(1000)).toString() + " mcs");
                return;
            }
        }
    }
}