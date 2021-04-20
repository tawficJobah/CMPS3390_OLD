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
     private final Bitmap enemy, enemy_left, enemy_right;
     private Bitmap curImage;
     private final float dpi;
     private int screenWidth, screenHeight;
     private Paint paint = new Paint();

    /**
     * constructor for enemy
     * @param res
     * @param x
     * @param y
     */
     public Enemy01(Resources res, float x, float y) {
         this.x = x;
         this.y = y;
         enemy = BitmapFactory.decodeResource(res, R.mipmap.enemy01);
         enemy_left = BitmapFactory.decodeResource(res, R.mipmap.enemy01_left);
         enemy_right = BitmapFactory.decodeResource(res, R.mipmap.enemy01_right);
         curImage = enemy;
         width = curImage.getWidth();
         height = curImage.getHeight();

         dpi = res.getDisplayMetrics().densityDpi;
         screenHeight = res.getDisplayMetrics().heightPixels;
         screenWidth = res.getDisplayMetrics().widthPixels;

         ySpeed = 0.02f * dpi;
     }

    /**
     * updates enemies locations and speeds
     */
    @Override
    public void update() {
        float xOff = (float) (0.01f * screenWidth * Math.sin(y/ (0.04f * screenHeight)));
        x += xOff;
        curImage = xOff > 0 ? enemy_left: enemy_right;
        if(Math.abs(xOff) < 2) curImage = enemy;

        y += ySpeed;
    }

    /**
     * draws enemies
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
     * gets Y
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
     * checks if enemy one is alive
     * @return
     */
    @Override
    public boolean isAlive() {
        return health > 0f;
    }

    /**
     * returns enemy ones health
     * @return
     */
    @Override
    public float getHealth() {
        return health;
    }

    /**
     * lowers enemies health
     * @param damage
     * @return
     */
    @Override
    public float takeDamage(float damage) {
        return health -= damage;
    }

    /**
     * TODO add enemies gaining health
     * @param repairAmount
     * @return
     */
    @Override
    public float addHealth(float repairAmount) {
        return health += repairAmount;
    }
}
