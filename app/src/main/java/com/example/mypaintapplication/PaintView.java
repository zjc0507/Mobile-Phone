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

import static android.graphics.Color.RED;


public class PaintView extends View implements View.OnTouchListener {

    private Random random = new Random();
    //arrarylist for change coclor
    private ArrayList<Point> points = new ArrayList<Point>();
    public int radius = 40;
    private boolean touchDown = false;
    //private PointF point = new PointF(500, 200);
    float x;
    float y;
    float oldX;
    float oldY;
    int currentTime;
    int oldTime = -2000000000;




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

//      int width = getWidth();
//        int height = getHeight() - 300;
//
//        if (point.x + x >= width) {
//            x = -x;
//        }else if((point.x + x) < 0){
//            x = -x;
//        }
//
//        if (point.y + y >= height){
//            y = -y;
//            point.y = height;
//        }else if ((point.y + y) < 0){
//            y = -y;
//        }
//
//        point.x += x;
//        point.y += y;
//        y = y + g;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {


        if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
            touchDown = true;
            x = event.getX();
            y = event.getY();
            //创建新的point 有4个属性，seekbar后大小不变
            Point point = new Point(event.getX(),event.getY(),random.nextInt(), radius);
            //point = new Point();
            //point.x = event.getX();
            //point.y = event.getY();
            //point.colour = random.nextInt();
            //point = new Point(x,y);//shayisi
            //point = new Point(x, y, random.nextInt());
            //添加元素到数组里
            points.add(point);
            ArrayList<Redo> redo = new ArrayList<Redo>();
            //redo.add(points);

            invalidate();
        }
        else if(event.getAction() == MotionEvent.ACTION_UP){
            touchDown = false;
        }
        return true;
    }
}
