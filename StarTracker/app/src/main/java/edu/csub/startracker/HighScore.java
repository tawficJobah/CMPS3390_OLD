package edu.csub.startracker;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public final class HighScore {
    private static final HighScore INSTANCE = new HighScore();
    private int curScore = 0;
    private int highScore = 0;
    private String name = "Player 1";
    private FirebaseFirestore db;

    /**
     * constructor
     */
    private HighScore(){
        db = FirebaseFirestore.getInstance();
    };

    /**
     * gets instance of of this class
     * @return
     */
    public static HighScore getInstance(){
        return INSTANCE;
    }

    /**
     * adds current score to highscore
     * @param score
     */
    public void addScore(int score){
        curScore += score;
        if(curScore > highScore){
            highScore = curScore;
        }
    }

    /**
     * returns the current score
     * @return
     */
    public int getCurScore(){
        return curScore;
    }

    /**
     * returns high score
     * @return
     */
    public int getHighScore(){
        return highScore;
    }

    /**
     * resets the current score
     */
    public void resetCurScore() {
        curScore = 0;
    }

    /**
     * sets the name to the player
     * @param playerName
     */
    public void setPlayerName(String playerName) {
        name = playerName;
    }

    /**
     * gets the players name
     * @return
     */
    public String getPlayerName() {
        return name;
    }

    /**
     * gets highscores from the firebase DB
     * @param howMany
     * @param highScores
     * @param context
     */
    public void getHighScores(int howMany, ListView highScores, Context context){
        ArrayList<String> topScores = new ArrayList<>();

        db.collection("HighScore")
                .orderBy("score", Query.Direction.DESCENDING)
                .limit(howMany)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                String tmpString = String.format("%s: %s,",doc.getId(),
                                        doc.get("score"));
                                topScores.add(tmpString);
                                Log.d("SCORE", tmpString);
                            }
                            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(context,
                                    android.R.layout.simple_list_item_1,topScores);
                            highScores.setAdapter(itemsAdapter);
                        }
                    }
                });
    }


    /**
     * posts high score to the DB
     */
    public void postHighScore(){
        Map<String, Integer> hs = new HashMap<>();
        hs.put("score",highScore);

        db.collection("HighScore").document(name)
                .set(hs)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("data", name + "'s score was set");
                    }
                });
    }


}