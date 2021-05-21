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
    private float x,y,speed;
    private float jumpS,weight;
    private final Bitmap playerImg;
    private Paint paint = new Paint();
    private final float dpi;
    private final Resources res;
    private final int width, height;
    private float floorHeight;

    /**
     * constructor for player
     * @param res
     */
    public Player(Resources res){
        this.res = res;
        playerImg = BitmapFactory.decodeResource(res,R.mipmap.player);
        width = playerImg.getWidth();
        height = playerImg.getHeight();
        DisplayMetrics dm = res.getDisplayMetrics();
        dpi = dm.densityDpi;
        x = (dm.widthPixels/2f) - (playerImg.getWidth() /2f);
        y = (dm.heightPixels * 0.90f);
        speed = 3;
        weight = 1;
        floorHeight = (dm.heightPixels * 0.90f);

    }

    /**
     * basic jumping for player with a nice gravity like bounce
     * @param touchX
     * @param touchY
     */
    public void updateTouch(int touchX, int touchY){

        //checks if there was a click before player jumps
        //not sure why it keeps jumping over and over
        if(touchX > 0 && touchY > 0) {
            this.x = touchX + speed;
            //checks to see if y is equal to floorheight then sets
            //the players y to jump 20 pixels up
            if (y == floorHeight) jumpS = 20;
            //slows down the jump by minus the y and using weights to slightly slow it down
            this.y -= jumpS;
            jumpS -= weight;
            //checks if y is below the floor so it doesnt go through
            if (y >= floorHeight) {
                this.y = floorHeight;
            }
        }
    }
    @Override
    public void update() {  }

    /**
     * sets the picture to the player position
     * @param canvas
     */
    public void draw(Canvas canvas) { canvas.drawBitmap(playerImg, this.x, this.y, this.paint); }

    /**
     * gets the x axis
     * @return
     */
    @Override
    public float getX() {
        return x;
    }

    /**
     * gets the y axis
     * @return
     */
    @Override
    public float getY() {
        return y;
    }

    /**
     * gets the width of the player
     * @return
     */
    @Override
    public float getWidth() {
        return width;
    }

    /**
     * gets the height of the player
     * @return
     */
    @Override
    public float getHeight() {
        return height;
    }

    /**
     * returns if player is alive
     * @return
     */
    @Override
    public boolean isAlive() {
        return true;
    }
}
