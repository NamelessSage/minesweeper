package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private Spinner xSpin;
    private Spinner ySpin;
    private int Width = 20;
    private int Height = 20;
    public static String EXTRA_WIDTH = "WIDTH";
    public static String EXTRA_HEIGHT = "HEIGHT";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xSpin = findViewById(R.id.xDropdownlist);
        ySpin = findViewById(R.id.yDropdownlist);

        List<String> list = new ArrayList<>();
        for (int i = 5; i < 100; i++) {
            list.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        xSpin.setAdapter(adapter);
        ySpin.setAdapter(adapter);

        xSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Width = Integer.parseInt(xSpin.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ySpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Height = Integer.parseInt(ySpin.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start_new_game();
            }
        });
    }
    //Goes to new activity and passes variables to it
    public void start_new_game() {
        Intent intent = new Intent(this, StartGame.class);
        intent.putExtra(EXTRA_WIDTH, Width);
        intent.putExtra(EXTRA_HEIGHT, Height);
        startActivity(intent);
    }


}