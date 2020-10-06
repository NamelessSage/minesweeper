package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import util.Generator;
import util.Tile;

public class StartGame extends AppCompatActivity {
    public static int Width;
    public static int Height;
    private Context context = this;
    private static Tile[][] GameGrid;


    public Tile[][] InitGameGrid(int W, int H) {
        return new Tile[W][H];
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();


        //StartGame.context = getApplicationContext();

//        int x = intent.getIntExtra(MainActivity.EXTRA_WIDTH, 5);
//        int y = intent.getIntExtra(MainActivity.EXTRA_HEIGHT, 5);
        Width =intent.getIntExtra(MainActivity.EXTRA_WIDTH, 5);
        Height =intent.getIntExtra(MainActivity.EXTRA_HEIGHT, 5);
//        Width = Integer.max(x, y);
//        Height = Integer.min(x, y);
        GameGrid = InitGameGrid(Width, Height);

        generateGrid(context);
        setContentView(R.layout.activity_game);
    }


    public void generateGrid(Context context) {
        int[][] Grid = Generator.generateGrid(Width, Height);
        for (int x = 0; x < Width; x++) {
            for (int y = 0; y < Height; y++) {
                GameGrid[x][y] = new Tile(context, y * Height + x);
                GameGrid[x][y].setValue(Grid[x][y]);
                GameGrid[x][y].invalidate();
            }
        }
    }

    public static Tile getTileAt(int position) {
        int x = position % Width;
        int y = position / Width;
        return GameGrid[x][y];
    }

    public static Tile getTileAt(int x, int y) {
        return GameGrid[x][y];
    }

    public static void click(int xpos, int ypos) {
        if(xpos>=0 && ypos >=0 && xpos<Width && ypos<Height && !getTileAt(xpos, ypos).isClicked() ){
            getTileAt(xpos, ypos).setClicked();
        }

    }

}