package com.group.awesome.battleship;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Stack;

import static com.group.awesome.battleship.SudokuGridView.SIZE;

public class MainActivity extends AppCompatActivity {

    /**
     * The grid is composed of a set of infinitely thin lines that separate the
     * viewing area into cells. Throughout the API, grid lines are referenced by grid indices.
     * A grid with N columns has N + 1 grid indices that run from 0 through N inclusive.
     * Regardless of how GridLayout is configured, grid index 0 is fixed to the leading edge
     * of the container and grid index N is fixed to its trailing edge (after padding is taken into account).
     * https://developer.android.com/reference/android/widget/GridLayout.html
     */
    private SudokuGridView sudokuBoard;

    private Button solve_btn;
    private Button undo_btn;
    private Button validate_btn;
    private Button restart_btn;
    private Button new_btn;

    private LinearLayout selectorGrid;

    private Stack<Move> moves = new Stack<>();

    private View.OnClickListener selectorClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            hideChooseNumber();

            // insert number
            int val = (int) view.getTag();

            int x = (int)view.getTag(R.id.X_CORD);
            int y = (int)view.getTag(R.id.Y_CORD);

            selectNumber(x, y, val, true);
            clearBoardSelections();
        }
    };

    private void selectNumber(int x, int y, int val, boolean isInteraction){

        // remember move if it was a user interaction, to avoid undo's appearing as interactions.
        if(isInteraction){
            moves.push(new Move(x, y, sudokuBoard.getVal(x, y)));
        }

        sudokuBoard.setVal(x, y, val);
    }

    private View.OnClickListener gridListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int x = (int)view.getTag(R.id.X_CORD);
            int y = (int)view.getTag(R.id.Y_CORD);

            if(sudokuBoard.isChangable(x, y)){
                showChooseNumber(x, y);

                clearBoardSelections();
                sudokuBoard.setSelected(x, y);
            }
        }
    };

    private void clearBoardSelections(){
        for (int i = 0; i < sudokuBoard.getBoard().grid.length; i++) {
            for (int j = 0; j < sudokuBoard.getBoard().grid[i].length; j++) {
                if(sudokuBoard.isChangable(i, j)){
                    sudokuBoard.setNotSelected(i, j);
                }

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sudokuBoard = findViewById(R.id.board_grid);

        solve_btn = findViewById(R.id.solve_btn);
        solve_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When solving, undoing makes no sense.
                moves.clear();
                new SudokuSolver().solve(sudokuBoard.getBoard(), false, 0,0);
                sudokuBoard.syncBoard();
            }
        });
        undo_btn = findViewById(R.id.undo_btn);
        undo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!moves.empty()){
                    Move move = moves.pop();
                    selectNumber(move.x, move.y, move.oldVal, false);
                }
            }
        });
        validate_btn = findViewById(R.id.is_board_valid_btn);
        validate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sudokuBoard.isBoardValid()){
                    Toast.makeText(MainActivity.this, "Board is valid!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Board is not valid!", Toast.LENGTH_LONG).show();
                }
            }
        });
        restart_btn = findViewById(R.id.restart);
        restart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moves.clear();
                sudokuBoard.reset();
            }
        });
        new_btn = findViewById(R.id.new_board_btn);
        new_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sudokuBoard.createBoard(50);
                clearBoardSelections();
            }
        });
        selectorGrid = findViewById(R.id.number_selector_grid);

        hideChooseNumber();

        fillSelector();

        getWindow().getDecorView().getRootView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                view.removeOnLayoutChangeListener(this);
                /*
                 * Since we want the height to match the width, and we don't know the width until
                 * the layout has been made, we have to listen for the layout before we build
                 * the grid.
                 */
                setupGrid();
                sudokuBoard.createBoard(50);
            }
        });
    }

    private void fillSelector(){
        for (int i = 1; i <= 9; i++) {
            Button btn = new Button(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.weight = 1;
            btn.setLayoutParams(lp);
            btn.setText(String.valueOf(i));
            btn.setTag(i);
            btn.setOnClickListener(selectorClick);
            selectorGrid.addView(btn);
        }
    }

    private void showChooseNumber(int x,int y){
        for (int i = 0; i < selectorGrid.getChildCount(); i++) {
            View child = selectorGrid.getChildAt(i);
            child.setTag(R.id.X_CORD, x);
            child.setTag(R.id.Y_CORD, y);
        }
        selectorGrid.setVisibility(View.VISIBLE);
    }

    private void hideChooseNumber(){
        selectorGrid.setVisibility(View.INVISIBLE);
    }

    private void setupGrid(){

        // Ensure that there's space for all buttons, by dividing size by number of buttons
        int btn_size = getWindow().getDecorView().getRootView().getWidth() / SIZE;

        ViewGroup.LayoutParams lp = sudokuBoard.getLayoutParams();
        lp.height = lp.width;
        sudokuBoard.setLayoutParams(lp);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {

                Button btn = (Button) LayoutInflater.from(this).inflate(R.layout.grid_btn, null);
                ViewGroup.LayoutParams btnLp = new ViewGroup.LayoutParams(btn_size, btn_size);
                btn.setLayoutParams(btnLp);
                btn.setText(i + " x " + j);
                btn.setTag(R.id.X_CORD, j);
                btn.setTag(R.id.Y_CORD, i);

                btn.setOnClickListener(gridListener);

                sudokuBoard.addView(btn);
            }
        }
    }
}