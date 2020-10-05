package util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.example.minesweeper.StartGame;

public class Grid extends GridView {
    public Grid(Context context, AttributeSet att){
        super(context, att);
        setNumColumns(StartGame.Width);
        setAdapter(new GridAdapter());

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    private class GridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return StartGame.Width * StartGame.Height;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return StartGame.getTileAt(i);
        }


    }
}
