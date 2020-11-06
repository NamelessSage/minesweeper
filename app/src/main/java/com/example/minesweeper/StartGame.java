package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Timer;
import util.Generator;
import util.Tile;

public class StartGame extends AppCompatActivity {
    public static int Width;
    public static int Height;
    private Context context = this;
    private static Context context2;
    private static Tile[][] GameGrid;
    private static Handler mHandler;
    private static int elapsedTime;
    private static TextView timerTextView;
    private static LinearLayout blockLayout;
    public Tile[][] InitGameGrid(int W, int H) {
        return new Tile[W][H];
    }
    private static int healthCount;
    private Button resetButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        elapsedTime = 0;
        Intent intent = getIntent();
        Width = intent.getIntExtra(MainActivity.EXTRA_WIDTH, 5);
        Height = intent.getIntExtra(MainActivity.EXTRA_HEIGHT, 5);
        GameGrid = InitGameGrid(Width, Height);
        generateGrid(context);
        setContentView(R.layout.activity_game);


        healthCount = 0;
        context2 = context;
        timerTextView = findViewById(R.id.timer);
        blockLayout = findViewById(R.id.blockPress);
        blockLayout.setVisibility(View.GONE);
        mHandler = new Handler();
        startTimer();


        resetButton = findViewById(R.id.resetToStart);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });

    }

    private void resetGame() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }

    static Runnable StatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                updateTimer();
            } finally {
                mHandler.postDelayed(StatusChecker, 1000);
            }
        }
    };

    public static void updateTimer() {
        elapsedTime++;
        String t = String.valueOf(elapsedTime);
        timerTextView.setText(t);
    }

    public void startTimer() {
        StatusChecker.run();
    }

    public static void stopTimer() {
        mHandler.removeCallbacks(StatusChecker);
    }

    public void generateGrid(Context context) {
        int[][] Grid = Generator.generateGrid(Width, Height);
        for (int x = 0; x < Width; x++) {
            for (int y = 0; y < Height; y++) {
                GameGrid[x][y] = new Tile(context, y * Width + x);
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
        if (xpos >= 0 && ypos >= 0 && xpos < Width && ypos < Height && !getTileAt(xpos, ypos).isClicked() && !getTileAt(xpos, ypos).isFlagged()) {
            getTileAt(xpos, ypos).setClicked();
            if (getTileAt(xpos, ypos).getValue() == 0 || getTileAt(xpos, ypos).isHealth()) {
                if(getTileAt(xpos, ypos).isHealth()){
                    healthCount++;
                }
                for (int xi = -1; xi <= 1; xi++) {
                    for (int yi = -1; yi <= 1; yi++) {
                        if (xi != yi)
                            click(xpos + xi, ypos + yi);
                    }
                }
            }

            if (getTileAt(xpos, ypos).isMine()) {
                if (healthCount <= 0){
                    gameLost();
                }
                else {
                    healthCount--;
                    getTileAt(xpos, ypos).setExploded(true);
                }
            }
        }
        gameFinished();
    }

    public static void gameLost() {
        Toast.makeText(context2, "Game Lost", Toast.LENGTH_SHORT).show();
        for (int x = 0; x < Width; x++) {
            for (int y = 0; y < Height; y++) {
                getTileAt(x, y).setRevealed(true);
            }
        }
        StartGame.stopTimer();
        blockLayout.setVisibility(View.VISIBLE);
    }

    public static void placeFlag(int xpos, int ypos) {
        boolean isflagged = getTileAt(xpos, ypos).isFlagged();
        getTileAt(xpos, ypos).setFlagged(!isflagged);
        getTileAt(xpos, ypos).invalidate();
        gameFinished();
    }

    public static void gameFinished() {
        int bombsleft = Generator.bombsGlobal;
        int notRevealed = Width * Height;

        for (int x = 0; x < Width; x++) {
            for (int y = 0; y < Height; y++) {
                if (getTileAt(x, y).isRevealed() || getTileAt(x, y).isFlagged()) {
                    notRevealed--;
                }
                if(getTileAt(x, y).isMine()) {
                    if (getTileAt(x, y).isFlagged() || getTileAt(x, y).isExploded()) {
                        bombsleft--;
                    }
                }
            }
        }
        if (bombsleft <= 0 && notRevealed <= 0) {
            Toast.makeText(context2, "You won" , Toast.LENGTH_SHORT).show();
            blockLayout.setVisibility(View.VISIBLE);
            stopTimer();
        }
    }


}