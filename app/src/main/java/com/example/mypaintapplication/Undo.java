package com.example.mypaintapplication;

public class Undo {
    float x;
    float y;
    int colour;
    int radius;

    Undo(float x, float y, int colour, int radius){
        this.x = x;
        this.y = y;
        this.colour = colour;
        this.radius = radius;
    }
}
