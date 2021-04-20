package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.Iterator;

public class Player implements GameObject{
    private float x,y, prevX, prevY;
    private final Bitmap playerImg;
    private final Bitmap playerLeft;
    private final Bitmap playerRight;
    private Bitmap curImage;
    private Paint paint = new Paint();
    private final float dpi;
    private int frameTicks = 0, shotTicks = 0;
    private final Resources res;
    private final int width, height;


    ArrayList<Laser> lasers = new ArrayList<>();
    private float health = 100f;

    /**
     * constructor for player class
     * @param res
     */
    public Player(Resources res){
        this.res = res;
        playerImg = BitmapFactory.decodeResource(res,R.mipmap.player);
        playerLeft = BitmapFactory.decodeResource(res,R.mipmap.player_left);
        playerRight = BitmapFactory.decodeResource(res,R.mipmap.player_right);

        curImage = playerImg;
        width = curImage.getWidth();
        height = curImage.getHeight();

        DisplayMetrics dm = res.getDisplayMetrics();
        dpi = dm.densityDpi;

        x = (dm.widthPixels/2f) - (playerImg.getWidth() /2f);
        y = (dm.heightPixels * 0.75f);

    }

    /**
     * updates player location
     * @param touchX
     * @param touchY
     */
    public void updateTouch(int touchX, int touchY){
        if(touchX > 0 && touchY > 0){
            this.x = touchX - (playerImg.getWidth() / 2f);
            this.y = touchY - (playerImg.getHeight() * 2f);
        }
    }

    /**
     * updates players location and info
     */
    @Override
    public void update() {
        if(health <=0) return;
        if(Math.abs(x - prevX) < 0.04 * dpi){
            frameTicks++;
        }else {
            frameTicks = 0;
        }

        if(this.x < prevX - 0.04 * dpi) {
            curImage = playerLeft;
        }else if(this.x > prevX + 0.04 * dpi){
            curImage = playerRight;
        } else if (frameTicks > 15){
            curImage = playerImg;
        }

        prevX = x;
        prevY = y;

        shotTicks++;

        if(shotTicks >= 20){
            Laser tmp = new Laser(this.res);
            tmp.setX(x + (playerImg.getWidth() /2) - tmp.getMidX());
            tmp.setY(y - tmp.getHeight() / 2f);
            lasers.add(tmp);

            shotTicks = 0;
        }

        for(Iterator<Laser> iterator = lasers.iterator(); iterator.hasNext();){
            Laser laser = iterator.next();
            if(!laser.isOnScreen() || !laser.isAlive()){
                iterator.remove();
            }
        }


        /*for(Laser laser : lasers){
            if(!laser.isOnScreen()){
                lasers.remove(laser);
            }
        }*/

        for(Laser laser: lasers){
            laser.update();
        }

    }

    /**
     * draws the player
     * @param canvas
     */
    public void draw(Canvas canvas) {
        if(health <=0) return;
        canvas.drawBitmap(curImage, this.x, this.y, this.paint);

        for (Laser laser : lasers) {
            laser.draw(canvas);
        }
    }

    /**
     * get X
     * @return
     */
    @Override
    public float getX() {
        return x;
    }

    /**
     * gets y
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
     * checks if player is alive
     * @return
     */
    @Override
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * checks players health
     * @return
     */
    @Override
    public float getHealth() {
        return health;
    }

    /**
     * returns health after damage
     * @param damage
     * @return
     */
    @Override
    public float takeDamage(float damage) {
        return health -= damage;
    }

    /**
     * TODO add way to add health
     * @param repairAmount
     * @return
     */
    @Override
    public float addHealth(float repairAmount) {
        return health += repairAmount;
    }

    public ArrayList<Laser> getLasers(){
        return lasers;
    }

}
