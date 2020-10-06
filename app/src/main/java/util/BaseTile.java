package util;

import android.content.Context;
import android.view.View;

import com.example.minesweeper.StartGame;

public abstract class BaseTile extends View {
    private int value;
    private boolean isRevealed;
    private boolean isClicked;
    private boolean isMine;
    private boolean isHealth;
    private boolean isFlagged;
    private int position;
    private int x, y;

    public BaseTile(Context context) {
        super(context);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        isRevealed = false;
        isClicked = false;
        isMine = false;
        isFlagged = false;

        if (value == -1)
            isMine = true;

        this.value = value;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked() {
        this.isClicked = true;
        this.isRevealed = true;
        invalidate();
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public int getYpos() {
        return y;
    }

    public int getXpos() {
        return x;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        x = position % StartGame.Width;
        y = position / StartGame.Width;

        invalidate();
    }

    public boolean isHealth() {
        return isHealth;
    }

    public void setHealth(boolean health) {
        isHealth = health;
    }
}
