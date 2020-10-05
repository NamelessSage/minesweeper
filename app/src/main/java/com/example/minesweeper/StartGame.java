package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import util.Generator;
import util.Tile;

public class StartGame extends AppCompatActivity {
    public static int Width;
    public static int Height;
    private static Context context;
    private static Tile[][] GameGrid;

    public Tile[][] InitGameGrid(int W, int H) {
        return new Tile[W][H];
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        StartGame.context = getApplicationContext();
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        Width = intent.getIntExtra(MainActivity.EXTRA_WIDTH, 20);
        Height = intent.getIntExtra(MainActivity.EXTRA_HEIGHT, 20);
        GameGrid = InitGameGrid(Width, Height);

        generateGrid(context);
    }


    public void generateGrid(Context context) {
        this.context = context;
        int[][] Grid = Generator.generateGrid(Width, Height);
        for (int x = 0; x < Width; x++) {
            for (int y = 0; y < Height; y++) {
                GameGrid[x][y] = new Tile(context,y*Height+x);
                GameGrid[x][y].setValue(Grid[x][y]);
                GameGrid[x][y].invalidate();
            }
        }
    }

    public static View getTileAt(int position) {
        int x = position % Width;
        int y = position / Height;
        return GameGrid[x][y];
    }


}