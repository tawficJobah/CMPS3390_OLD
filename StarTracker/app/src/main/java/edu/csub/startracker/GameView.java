package edu.csub.startracker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.PrimitiveIterator;

public class GameView extends SurfaceView implements Runnable {

    private final Background background1;
    private final Background background2;
    private boolean isPlaying = true;
    private Thread thread;
    private int touchX,touchY;
    private final Player player;
    private ArrayList<GameObject> enemies;
    private GameActivity gameActivity;
    private EnemySpawner spawner;
    private final float screenWidth,screenHeight;
    private Paint textPaint = new Paint();
    private Paint highScorePaint = new Paint();

    /**
     * constructor to set parameters
     * @param context
     * @param screenX
     * @param screenY
     */
    public GameView(GameActivity context, int screenX, int screenY) {
        super(context);
        Resources res = getResources();
        screenHeight = res.getDisplayMetrics().widthPixels;
        screenWidth = res.getDisplayMetrics().heightPixels;
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(screenWidth * 0.1f);
        highScorePaint.setColor(Color.WHITE);
        highScorePaint.setTextSize(screenWidth * 0.04f);

        background1 = new Background(screenX,screenY,res);
        background2 = new Background(screenX,screenY,res);
        background2.setY(screenY);

        player = new Player(res);
        spawner = new EnemySpawner(res);

        enemies = spawner.getEnemies();
        gameActivity = context;
    }

    /**
     * returns the x and y of the mouse click
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
     * main game engine
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
     * updates background,touchevents, and enemy spawning
     * couldnt get collisions to work properly
     */
    private void update() {
        background1.update();
        background2.update();
        player.updateTouch(touchX,touchY);
        player.update();
        spawner.update();
        //checkCollision(player, (GameObject) enemies);
    }

    /**
     * draws enemies and background
     */
    private void draw(){
        if(getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            background1.draw(canvas);
            background2.draw(canvas);
            if(!player.isAlive()){
                canvas.drawText("LOSER!!",screenWidth / 8f,screenHeight / 2f,textPaint);
            }
            player.draw(canvas);
            spawner.draw(canvas);

            getHolder().unlockCanvasAndPost(canvas);
        }
    }
    private boolean checkCollision(GameObject g1, GameObject g2){
        return g1.getY() < g2.getY() + g2.getHeight() &&
                g1.getY() + g1.getHeight() > g2.getY();
    }

    /**
     * checks for game still playing
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
     * resumes and restarts game
     */
    public void resume(){
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * puts the game to sleep for a few miliseconds
     */
    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
