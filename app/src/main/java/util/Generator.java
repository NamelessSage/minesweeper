package util;


import java.util.Random;

public class Generator {

    //Places bombs and then fills all information about each cell
    public static int[][] generateGrid(int width, int height) {
        Random ran = new Random();

        int bombs = (int) (width * height * 0.2);
        if (bombs <= 0) {
            bombs = 1;
        }

        int[][] grid = new int[width][height];
        for (int x = 0; x < width; x++) {
            grid[x] = new int[height];
        }

        while (bombs > 0) {
            int x = ran.nextInt(width);
            int y = ran.nextInt(height);
            if (grid[x][y] != -1) {
                grid[x][y] = -1;
                bombs--;
            }
        }
        grid = bombsAround(grid, width, height);
        return grid;
    }

    //Fills all of the grid tiles with bomb information around them
    private static int[][] bombsAround(int grid[][], int width, int height) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = getBombsAround(grid, x, y, width, height);
            }
        }
        return grid;
    }

    //Check cells around and return how many were bombs
    private static int getBombsAround(int grid[][], int x, int y, int width, int height) {
        if (grid[x][y] == -1)
            return -1;
        int count = 0;
        if (isBomb(grid, x - 1, y - 1, width, height)) count++;
        if (isBomb(grid, x, y - 1, width, height)) count++;
        if (isBomb(grid, x + 1, y - 1, width, height)) count++;
        if (isBomb(grid, x - 1, y, width, height)) count++;
        if (isBomb(grid, x + 1, y, width, height)) count++;
        if (isBomb(grid, x - 1, y + 1, width, height)) count++;
        if (isBomb(grid, x, y + 1, width, height)) count++;
        if (isBomb(grid, x + 1, y + 1, width, height)) count++;
        return count;
    }

    //Check if a cell is a bomb
    private static boolean isBomb(int grid[][], int x, int y, int width, int height) {
        if (x >= 0 && y >= 0 && x < width && y < height) {
            return grid[x][y] == -1;
        }
        return false;
    }
}
