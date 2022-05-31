package level4;

import java.util.Random;
import java.util.Scanner;

public class HomeWorkApp {
    public static int SIZE = 3;
    public static int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {

        initMap();
        printMap();
        while (true) {
            int[] positionHuman = humanTurn();
            printMap();
            if (checkWin(positionHuman, DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            int[] positionAi = aiTurn();
            printMap();
            if (checkWin(positionAi, DOT_O)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }

        }
        System.out.println("Игра закончена");

    }

    public static boolean checkDiagonal(char[] diagonal, char symb) {
        int count = 0;
        for (int i = 0; i < diagonal.length; i++) {
            if (diagonal[i]  == symb) {
                count = count + 1;
            }
            else {
                count = 0; //возвращаем в исходное состояние
            }
            //проверка на количество
            if (count == DOTS_TO_WIN) {
                break;
            }
        }
        return (count == DOTS_TO_WIN);
    }

    public static boolean checkWin(int[] position, char symb) {
        /*
        if(map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) return
                true;
        if(map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) return
                true;
        if (map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) return
                true;
        if (map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) return
                true;
        if (map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) return
                true;
        if (map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) return
                true;
        if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return
                true;
        if (map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return
                true;
        return false;
        */

        int x = position[0];
        int y = position[1];

        //считаем в колонке
        for (int i = 0; i < map.length; i++) {
            if (map[i][x] != symb) {
                break;
            }
            if (i == map.length - 1) {
                return true;
            }
        }

        //считаем в строке
        for (int i = 0; i < map.length; i++) {
            if (map[y][i] != symb) {
                break;
            }
            if (i == map.length - 1) {
                return true;
            }
        }

        //считаем первую диагональ
        if (x == y) {
            for (int i = 0; i < map.length; i++) {
                if (map[i][i] != symb) {
                    break;
                }
                if (i == map.length - 1) {
                    return true;
                }
            }
        }

        //считаем вторую диагональ
        if (x + y == map.length - 1) {
            for (int i = 0; i < map.length; i++) {
                if (map[i][(map.length - 1) - i] != symb) {
                    break;
                }
                if (i == map.length - 1) {
                    return true;
                }
            }
        }

        return false;

    }

    public static boolean isMapFull() {
        for (int i = 0; i < map.length/*SIZE*/; i++) {
            for (int j = 0; j < map.length/*SIZE*/; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }
    public static int[] aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(map.length/*SIZE*/);
            y = rand.nextInt(map.length/*SIZE*/);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y +
                1));
        map[y][x] = DOT_O;

        //возвращаем координаты x, y в массиве
        int[] position = new int[2];
        position[0] = x;
        position[1] = y;
        return position;

    }

    public static int[] humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y)); // while(isCellValid(x, y) == false)
        map[y][x] = DOT_X;

        //возвращаем координаты x, y в массиве
        int[] position = new int[2];
        position[0] = x;
        position[1] = y;
        return position;

    }
    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= map.length/*SIZE*/; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < map.length/*SIZE*/; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < map.length/*SIZE*/; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}