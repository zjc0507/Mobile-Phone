package com.example.mypaintapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private Button redo;
    private Button undo;
    private PaintView paintView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       seekBar = findViewById(R.id.seekBar);
       redo = findViewById(R.id.redo);
       undo = findViewById(R.id.undo);
       paintView = findViewById(R.id.view);

       seekBar.setMax(80);
       seekBar.setProgress(20);
       seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               paintView.radius = progress + 20;
           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {

           }

       });

       //redo
       redo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //redo.get(reodo.size() - 1).colour = random.nextInt();
           }
       });


   }
}
