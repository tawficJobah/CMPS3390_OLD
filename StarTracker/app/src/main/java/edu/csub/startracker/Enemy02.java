package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

public class Enemy02 implements GameObject{

    private float x, y, ySpeed;
    private float health = 100;
    private Bitmap enemy;
    private Bitmap enemy_fast;
    private Bitmap curImage;
    private final int screenWidth, screenHeight, dpi;
    private final int width, height;
    private Paint paint = new Paint();
    private int frameTick = 0, launchTick;

    /**
     * constuctor for enemy two
     * @param res
     * @param x
     * @param y
     */
    public Enemy02(Resources res, float x, float y){
        dpi = res.getDisplayMetrics().densityDpi;
        screenWidth = res.getDisplayMetrics().widthPixels;
        screenHeight = res.getDisplayMetrics().heightPixels;

        enemy = BitmapFactory.decodeResource(res,R.mipmap.enemy02);
        enemy_fast = BitmapFactory.decodeResource(res,R.mipmap.enemy02_fast);
        curImage = enemy;

        width = curImage.getWidth();
        height = curImage.getHeight();

        this.x = x;
        this.y = y;

        ySpeed = 0.01f * dpi;

        launchTick = new Random().nextInt(120 - 30) + 30;
    }

    /**
     * updates enemy twos location and info
     */
    @Override
    public void update() {
        frameTick++;

        if(frameTick == launchTick){
            curImage = enemy_fast;
            ySpeed *= 4f;
        }

        y += ySpeed;
    }

    /**
     * draws enemy two
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(curImage,x,y,paint);
    }

    /**
     * gets x
     * @return
     */
    @Override
    public float getX() {
        return x;
    }

    /**
     * gets Y for e2
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
     * gets height
     * @return
     */
    @Override
    public float getHeight() {
        return height;
    }

    /**
     * checks if e2 is alive
     * @return
     */
    @Override
    public boolean isAlive() {
        return health > 0f;
    }

    /**
     * returns e2s health
     * @return
     */
    @Override
    public float getHealth() {
        return health;
    }

    /**
     * minuses e2s heath and returns it
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
}
