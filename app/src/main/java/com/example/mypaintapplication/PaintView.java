package com.example.mypaintapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import static android.content.ContentValues.TAG;
import static android.graphics.Color.RED;


public class PaintView extends View implements View.OnTouchListener {

    private Random random = new Random();
    //arrarylist for change coclor
    private ArrayList<Point> points = new ArrayList<Point>();
    public int radius = 40;
    private boolean touchDown = false;
    //private PointF point = new PointF(500, 200);
    private float x;
    private float y;
    private float oldX;
    private float oldY;
    private int currentTime;
    private int oldTime = -2000000000;

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }

    public PaintView(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
    }

    public PaintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
       // paint.setColor(RED);

        //change color
        for(Point pt : points){
            paint.setColor(pt.colour);
            canvas.drawCircle(pt.x, pt.y, pt.radius, paint);
        }
        currentTime = (int) (System.currentTimeMillis());
        //System.out.println(currentTime);
        //System.out.println("laoX::"+oldX+"laoY::"+oldY+"laoshijian----"+oldTime);

        if(x == oldX && y == oldY && touchDown == true){
            if(currentTime > oldTime + 500) {
                points.get(points.size() - 1).colour = random.nextInt();
               // System.out.println("数组长度"+points.size());
                oldTime = currentTime;

            }
        }

        invalidate();
        oldX = x;
        oldY = y;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int firstIndex, secondIndex;
        int action = event.getActionMasked();
        int actionIndex = event.getActionIndex();
        x = event.getX();
        y = event.getY();

        if(action == MotionEvent.ACTION_POINTER_DOWN || action == MotionEvent.ACTION_MOVE){
            touchDown = true;
            //创建新的point 有4个属性，seekbar后大小不变
            Point point = new Point(event.getX(),event.getY(),random.nextInt(), radius);
            //添加元素到数组里
            points.add(point);
            invalidate();
        }
        else if(action == MotionEvent.ACTION_POINTER_UP) {
            touchDown = false;
        }
        return true;
    }
}
