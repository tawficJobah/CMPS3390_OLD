package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Laser implements GameObject{

    private float x, y;
    private Bitmap laser;
    private float dpi;
    private Paint paint = new Paint();
    private float health = 100f;
    private final int width, height;

    /**
     * constructor for laser
     * @param res
     */
    public Laser(Resources res){
        laser = BitmapFactory.decodeResource(res,R.mipmap.bullet);
        width = laser.getWidth();
        height = laser.getHeight();
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
    @Override
    public float getHeight(){
        return height;
    }

    /**
     * TODO
     * @return
     */
    @Override
    public boolean isAlive() {
        return health > 0f;
    }

    /**
     * TODO
     * @return
     */
    @Override
    public float getHealth() {
        return health;
    }

    /**
     * TODO
     * @param damage
     * @return
     */
    @Override
    public float takeDamage(float damage) {
        return health -= damage;
    }

    /**
     * TODO
     * @param repairAmount
     * @return
     */
    @Override
    public float addHealth(float repairAmount) {
        return health += repairAmount;
    }

    /**
     * gets the x value
     * @return
     */
    @Override
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
    @Override
    public float getY() {
        return y;
    }

    /**
     * gets width
     * @return
     */
    @Override
    public float getWidth() {
        return width;
    }

    /**
     * sets the y value
     * @param y
     */
    public void setY(float y) {
        this.y = y;
    }
}
