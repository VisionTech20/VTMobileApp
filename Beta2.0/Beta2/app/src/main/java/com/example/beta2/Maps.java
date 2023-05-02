package com.example.beta2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Maps extends AppCompatActivity {

    public void onImageClick(View view) {
        ImageView imageView = (ImageView) view;
        int imageId = imageView.getId();

        switch (imageId) {
            case R.id.image1:
                showBlownUpImage(R.drawable.ml);
                break;
            case R.id.image2:
                showBlownUpImage(R.drawable.ritsonc);
                break;
            case R.id.image3:
                showBlownUpImage(R.drawable.biko);
                break;
        }
    }

    private void showBlownUpImage(int imageResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(imageResId);
        builder.setView(imageView);
        builder.create().show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Maps.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Maps.this,loginActrivity.class);
                startActivity(intent);
            }
        });

    }
}