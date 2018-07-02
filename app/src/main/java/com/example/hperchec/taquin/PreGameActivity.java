package com.example.hperchec.taquin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

/**
 * Created by hperchec on 18/05/18.
 */

public class PreGameActivity extends AppCompatActivity {

    int chosenSize = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game);
    }

    public void radioClick(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        switch (v.getId()) {
            case R.id.gridSize3:
                if(checked)
                    chosenSize = 3;
                    break;
            case R.id.gridSize4:
                if(checked)
                    chosenSize = 4;
                    break;
            case R.id.gridSize5:
                if(checked)
                    chosenSize = 5;
                    break;
            case R.id.gridSize6:
                if(checked)
                    chosenSize = 6;
                    break;
        }

    }

    public void startGameClick(View v) {
        Intent gameActivity = new Intent(PreGameActivity.this, GameActivity.class);
        gameActivity.putExtra("chosenSize",chosenSize);
        startActivity(gameActivity);
    }

}
