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
    private ArrayList<Laser> lasers;
    private ArrayList<GameObject> enemies;
    private GameActivity gameActivity;

    private EnemySpawner spawner;
    private final float screenWidth,screenHeight;
    private Paint textPaint = new Paint();
    private Paint highScorePaint = new Paint();
    HighScore highScore = HighScore.getInstance();
    /**
     * creates background
     * creates player
     * constructor for game scene
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

        lasers = player.getLasers();
        enemies = spawner.getEnemies();
        gameActivity = context;
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
        player.updateTouch(touchX,touchY);
        player.update();
        spawner.update();
        checkAllCollisions();
        checkEnemiesOffScreen();
    }

    /**
     * checks if enemy is off screen and shows game over
     */
    private void checkEnemiesOffScreen() {
        for(GameObject go : enemies){
            if(go.getY() > screenHeight){
                player.takeDamage(100);
                go.takeDamage(100);
                gameActivity.gameOver();
            }
        }
    }

    /**
     * collisions check
     */
    private void checkAllCollisions() {
        for(Laser laser : lasers){
            for(GameObject go : enemies){
                if(checkCollision(laser,go)){
                    laser.takeDamage((100));
                    go.takeDamage(25);
                    highScore.addScore(25);
                }
            }
        }

        for(GameObject go : enemies){
            if(checkCollision(player, go)){
                player.takeDamage(100);
                go.takeDamage(100);
                gameActivity.gameOver();
            }
        }
    }

    /**
     * checks collisions between player and enemy
     * @param g1
     * @param g2
     * @return
     */
    private boolean checkCollision(GameObject g1, GameObject g2){
        return g1.getX() < g2.getX() + g2.getWidth() &&
                g1.getX() + g1.getWidth() > g2.getX() &&
                g1.getY() < g2.getY() + g2.getHeight() &&
                g1.getY() + g1.getHeight() > g2.getY();
    }


    /**
     * draws the game scene
     */
    private void draw(){
        if(getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();

            background1.draw(canvas);
            background2.draw(canvas);


            if(!player.isAlive()){
                canvas.drawText("LOSER!!",screenWidth / 8f,screenHeight / 2f,textPaint);
            }

            canvas.drawText(String.format("Score: %s",
                    highScore.getCurScore()),screenWidth * 0.02f,
                    screenHeight * 0.11f,highScorePaint);
            player.draw(canvas);
            spawner.draw(canvas);

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
