package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class EnemySpawner {
    private ArrayList<GameObject> enemies;
    float x, y = 0;
    int screenWidth,screenHeight;
    int wave = 10, enemy01Spawned = 0;
    int frameTick = 0, spawnTick, waveTick = 0;
    Resources res;
    private Paint paint = new Paint();

    /**
     * constructor for enemy spawner
     * @param res
     */
    public EnemySpawner(Resources res){
        enemies = new ArrayList<>();
        this.res = res;
        screenWidth = res.getDisplayMetrics().widthPixels;
        screenHeight = res.getDisplayMetrics().heightPixels;
        spawnTick = new Random().nextInt(120 - 60);
        paint.setColor(Color.WHITE);
        paint.setTextSize(screenWidth * 0.05f);
    }

    /**
     * spawns the enemies and deletes them on collison
     */
    public void update(){
        frameTick++;

        if(frameTick >= spawnTick){
            frameTick = 0;
            spawnTick = new Random().nextInt(120 - 60);

            x = new Random().nextInt((int)(screenWidth * 0.75f - screenWidth * 0.05f)) + screenWidth * 0.05f;
            y = new Random().nextInt((int)(screenHeight * 0.90f - screenHeight * 0.05f))+ screenHeight * 0.05f;


            int tmp = (int)Math.round(Math.random());

            if(tmp == 0 && enemy01Spawned < wave) {
                enemies.add(new Enemy01(res,x,y));
                enemy01Spawned++;
            } else {
                tmp = 1;
            }
        }
        if(enemy01Spawned >= wave){
            waveTick++;
        }
        if(waveTick >=10){
            wave++;
            waveTick = 0;
            enemy01Spawned = 0;
        }
        for(Iterator<GameObject> iterator = enemies.iterator(); iterator.hasNext();){
            GameObject go = iterator.next();
            go.update();
            if(enemy01Spawned == 5){
                iterator.remove();
            }
        }
    }

    /**
     * draws enemies
     * @param canvas
     */
    public void draw(Canvas canvas){
      //  canvas.drawText("Wave: " + wave, screenWidth * 0.05f, screenWidth * 0.05f, paint);
        for(GameObject go : enemies) { go.draw(canvas); }
    }

    /**
     * returns the enemies
     * @return
     */
    public ArrayList<GameObject> getEnemies(){
        return enemies;
    }

}
