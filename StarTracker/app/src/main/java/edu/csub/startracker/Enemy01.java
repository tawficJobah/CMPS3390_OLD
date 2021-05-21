package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Enemy01 implements GameObject{
     private float x, y, ySpeed;
     private final float width, height;
     private float health = 100f;
     private final Bitmap enemy;
     private Bitmap curImage;
     private final float dpi;
     private int screenWidth, screenHeight;
     private Paint paint = new Paint();

    /**
     * enemy constructor that sets the width, height, and image
     * @param res
     * @param x
     * @param y
     */
     public Enemy01(Resources res, float x, float y) {
         this.x = x;
         this.y = y;
         enemy = BitmapFactory.decodeResource(res, R.mipmap.enemy01);
         curImage = enemy;
         width = curImage.getWidth();
         height = curImage.getHeight();
         dpi = res.getDisplayMetrics().densityDpi;
         screenHeight = res.getDisplayMetrics().heightPixels;
         screenWidth = res.getDisplayMetrics().widthPixels;
     }
    @Override
    public void update() { }

    /**
     * draws image
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(curImage,x,y,paint);
    }

    /**
     * returns x axis
     * @return
     */
    @Override
    public float getX() {
        return x;
    }

    /**
     * returns y axis
     * @return
     */
    @Override
    public float getY() {
        return y;
    }

    /**
     * returns width
     * @return
     */
    @Override
    public float getWidth() {
        return width;
    }

    /**
     * returns height
     * @return
     */
    @Override
    public float getHeight() {
        return height;
    }

    /**
     * returns health of enemy
     * @return
     */
    @Override
    public boolean isAlive() {
        return health > 0f;
    }

}
