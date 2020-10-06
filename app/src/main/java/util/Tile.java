package util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.minesweeper.R;
import com.example.minesweeper.StartGame;

public class Tile extends BaseTile implements View.OnClickListener{

    public Tile(Context context, int position){
        super(context);
        setPosition(position);
        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isClicked()){
            if(isMine())
                drawMine(canvas);
            else
                drawNumber(canvas);
        }
        else {
            drawButton(canvas);
        }
    }

    private void drawMine(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawButton(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.button);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    public void onClick(View view){
        StartGame.click(getXpos(), getYpos());
    }

    private void drawNumber(Canvas canvas){
        Drawable drawable = null;

        switch (getValue()) {
            case 0:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button0);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button1);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button2);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button3);
                break;
            case 4:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button4);
                break;
            case 5:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button5);
                break;
            case 6:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button6);
                break;
            case 7:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button7);
                break;
            case 8:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button8);
                break;
            default:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button0);
        }

        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);

    }
}
