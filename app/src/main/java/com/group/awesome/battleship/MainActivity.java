package com.group.awesome.battleship;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    /**
     * The grid is composed of a set of infinitely thin lines that separate the
     * viewing area into cells. Throughout the API, grid lines are referenced by grid indices.
     * A grid with N columns has N + 1 grid indices that run from 0 through N inclusive.
     * Regardless of how GridLayout is configured, grid index 0 is fixed to the leading edge
     * of the container and grid index N is fixed to its trailing edge (after padding is taken into account).
     * https://developer.android.com/reference/android/widget/GridLayout.html
     */
    private GridLayout game_layout;


    Button next_turn_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game_layout = findViewById(R.id.game_grid);
        next_turn_btn = findViewById(R.id.next_turn_btn);

        next_turn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void setupGrid(int n_size){
        game_layout.setColumnCount(n_size);
        game_layout.setRowCount(n_size);

        for (int i = 0; i < n_size; i++) {
            for (int j = 0; j < n_size; j++) {
                Button btn = (Button) LayoutInflater.from(this).inflate(R.layout.grid_btn, game_layout);
                btn.setText(i + " x " + j);
                game_layout.addView(btn);
            }
        }
    }
}
