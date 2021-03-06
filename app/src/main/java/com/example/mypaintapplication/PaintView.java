package com.example.mypaintapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.provider.Settings;
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
    //arrarylist for changing coclor
    ArrayList<Point> points = new ArrayList<Point>();
    ArrayList<Point> redo = new ArrayList<Point>();
    public int radius = 40;
    private boolean touchDown = false;
    //private PointF point = new PointF(500, 200);
    private float x;
    private float y;
    private float oldX;
    private float oldY;
    private int currentTime;
    private int oldTime = -2000000000;
    int countTouch = 0;

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
                //points.get(points.size() - 1).colour = random.nextInt();
               // System.out.println("数组长度"+points.size());
                oldTime = currentTime;
//                int countPoints = points.size();
//                int indexLastPoint = countPoints - 1;
//                Point lastPoint = points.get(indexLastPoint);
            //change all points during langpress
                for(int i = 1; i<=countTouch;i++){
                    points.get(points.size() - i).colour = random.nextInt();
                }
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
        int action = event.getActionMasked();
        x = event.getX();
        y = event.getY();
        countTouch=event.getPointerCount();



        //判断是否是多点
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("ACTION_DOWN");
                touchDown = true;
            case MotionEvent.ACTION_POINTER_DOWN:
                System.out.println("ACTION_POINTER_DOWN");
            case MotionEvent.ACTION_MOVE:
                System.out.println("ACTION_MOVE");
                for(int i = 0; i < event.getPointerCount(); i++) {
                    //创建新的point 有4个属性，seekbar后大小不变
                    Point point = new Point(event.getX(i),event.getY(i),random.nextInt(), radius);
                    //添加元素到数组里
                    points.add(point);
                }
                //当出现新点清空redolist；
                if(redo.size()!=0){
                    redo.clear();
                }

                invalidate();
                break;
            case MotionEvent.ACTION_POINTER_UP:
                System.out.println("ACTION_POINTER_UP");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("ACTION_UP");
                touchDown = false;
                break;
        }

        //undo
//        MainActivity.btnundo = findViewById(R.id.btnundo);
//        btnundo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //redo.get(reodo.size() - 1).colour = random.nextInt();
//                undo.add(points.get(lastPoint));
//                System.out.println(undo.size());
//            }
//        });

        return true;
    }

    //undo 找到数组中的最后一个，移除，添加到redolist里
    public void undo(){
        if(points.size() == 0)
            return;
        Point lastPoint = points.get(points.size() - 1);
        points.remove(lastPoint);
        redo.add(lastPoint);
        System.out.println(redo.size());
    }

    //redo
    public void redo(){
        if(redo.size() == 0)
            return;

        points.add(redo.remove(redo.size() - 1));
    }


}
