package com.example.hperchec.taquin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by hperchec on 02/07/18.
 */

public class PostGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_game);
    }

    public void backMenuClick(View v) {
        Intent mainActivity = new Intent(PostGameActivity.this, MainActivity.class);
        startActivity(mainActivity);
    }


}
