package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.Iterator;

public class Player {
    private float x,y, prevX, prevY;
    private final Bitmap playerImg;
    private final Bitmap playerLeft;
    private final Bitmap playerRight;
    private Bitmap curImage;
    private Paint paint = new Paint();
    private final float dpi;
    private int frameTicks = 0, shotTicks = 0;
    private final Resources res;


    ArrayList<Laser> lasers = new ArrayList<>();

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

        DisplayMetrics dm = res.getDisplayMetrics();
        dpi = dm.densityDpi;

        x = (dm.widthPixels/2f) - (playerImg.getWidth() /2f);
        y = (dm.heightPixels * 0.75f);

    }

    /**
     * updates player location and shoots lasers
     * @param touchX
     * @param touchY
     */
    public void update(int touchX,int touchY){
        if(touchX > 0 && touchY > 0){
            this.x = touchX - (playerImg.getWidth() / 2f);
            this.y = touchY - (playerImg.getHeight() * 2f);
        }

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
            if(!laser.isOnScreen()){
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
        canvas.drawBitmap(curImage, this.x, this.y, this.paint);

        for (Laser laser : lasers) {
            laser.draw(canvas);
        }
    }

}
