package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Laser {

    private float x, y;
    private Bitmap laser;
    private float dpi;
    private Paint paint = new Paint();

    /**
     * constructor for laser
     * @param res
     */
    public Laser(Resources res){
        laser = BitmapFactory.decodeResource(res,R.mipmap.bullet);
        dpi = res.getDisplayMetrics().densityDpi;
    }

    /**
     * checks to see if laser is on screen
     * @return
     */
    public boolean isOnScreen(){
        return !(y < getHeight());
    }

    /**
     * updates the y
     */
    public void update(){
        y -= 0.1f * dpi;
    }

    /**
     * draws the laser
     * @param canvas
     */
    public void draw(Canvas canvas){
        canvas.drawBitmap(laser,this.x,this.y,this.paint);
    }

    /**
     * gets the middle of the x
     * @return
     */
    public float getMidX(){
        return laser.getWidth() / 2f;
    }

    /**
     * gets the height of the laser
     * @return
     */
    public float getHeight(){
        return laser.getHeight();
    }

    /**
     * gets the x value
     * @return
     */
    public float getX() {
        return x;
    }

    /**
     * sets the x value
     * @param x
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * gets the y
     * @return
     */
    public float getY() {
        return y;
    }

    /**
     * sets the y value
     * @param y
     */
    public void setY(float y) {
        this.y = y;
    }
}
