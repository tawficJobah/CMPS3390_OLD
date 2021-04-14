package edu.csub.startracker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.PrimitiveIterator;

public class GameView extends SurfaceView implements Runnable {

    private final Background background1;
    private final Background background2;
    private boolean isPlaying = true;
    private Thread thread;
    private int touchX,touchY;
    private final Player player;

    /**
     * creates background
     * creates player
     * constructor for game scene
     * @param context
     * @param screenX
     * @param screenY
     */
    public GameView(Context context, int screenX, int screenY) {
        super(context);

        Resources res = getResources();

        background1 = new Background(screenX,screenY,res);
        background2 = new Background(screenX,screenY,res);
        background2.setY(screenY);

        player = new Player(res);
    }

    /**
     * reads click of the mouse or touch
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event){
        touchX = (int)event.getX();
        touchY = (int)event.getY();
        return true;
    }

    /**
     * main driver of the game
     */
    @Override
    public void run() {
        while(isPlaying){
            update();
            draw();
            sleep();
        }
    }

    /**
     * updates game scene
     */
    private void update() {
        background1.update();
        background2.update();
        player.update(touchX,touchY);
    }

    /**
     * draws the game scene
     */
    private void draw(){
        if(getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();

            background1.draw(canvas);
            background2.draw(canvas);
            player.draw(canvas);

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    /**
     * pauses scene
     */
    public void pause(){
        isPlaying = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * starts playing the game
     *
     */
    public void resume(){
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * function that slows down game
     */
    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
