package com.example.hperchec.taquin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button preGameButton;
    Button scoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preGameButton = (Button) findViewById(R.id.preGameButton);
        preGameButton.setOnClickListener(this);
        scoreButton = (Button) findViewById(R.id.scoreButton);
        scoreButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.preGameButton:
                Intent preGameActivity = new Intent(MainActivity.this, PreGameActivity.class);
                startActivity(preGameActivity);
                break;
            case R.id.scoreButton:
                Intent scoreActivity = new Intent(MainActivity.this, ScoreActivity.class);
                startActivity(scoreActivity);
                break;
            default:

        }

    }

}
