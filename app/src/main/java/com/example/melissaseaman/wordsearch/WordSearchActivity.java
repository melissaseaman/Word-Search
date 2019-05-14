package com.example.melissaseaman.wordsearch;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

/**
 * @author Melissa Seaman
 * @version 1.0
 */

public class WordSearchActivity extends AppCompatActivity {
    private static final int ROWS = 10;
    private static final int COLUMNS = 10;
    private ArrayList<String> validWords;
    private ArrayList<Button> currentLetters;
    private int wordsFound;
    private char[] letters;
    private ArrayList<String> reservedStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_search);
        this.currentLetters = new ArrayList<>();
        this.wordsFound = 0;
        this.letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        setValidWords();
        setReservedSpaces();
        setButtonListeners();
    }

    private void setReservedSpaces(){
        String[] spaces = {"r1c1","r1c2","r1c3","r1c4","r1c5","r1c6","r1c7","r1c8","r1c9","r1c10","r2c3","r2c4","r2c5","r2c6","r2c7","r2c8",
        "r4c1","r5c1","r6c1","r7c1","r8c1","r3c6","r4c6","r5c6","r6c6","r7c6","r8c6","r9c6","r10c6","r4c8","r5c8","r6c8","r7c8","r8c8","r9c8","r10c2","r10c3","r10c4","r10c5"};
        this.reservedStrings = new ArrayList<>(Arrays.asList(spaces));

    }


    private String generateRandomLetter(){
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        char c = letters[r.nextInt(letters.length)];
        sb.append(c);
        return  sb.toString();

    }

    private void setValidWords(){
        this.validWords = new ArrayList<>();
        validWords.add("OBJECTIVEC");
        validWords.add("JAVA");
        validWords.add("KOTLIN");
        validWords.add("MOBILE");
        validWords.add("SWIFT");
        validWords.add("VARIABLE");
    }

    private String processWord(){
        StringBuilder sb =new StringBuilder();
        for(Button b: currentLetters) {
            sb.append(b.getText());
        }
        return sb.toString();
    }

    private void updateFoundWords(int i){
        TextView tv = (TextView) findViewById(R.id.wordsFoundText);
        tv.setText(i+R.string.words_found);
    }

    private boolean checkAdjacency(){
        String firstLetter = currentLetters.get(0).getTag().toString();
        String [] firstCoordinates = firstLetter.split("[rc]");
        int r1 = Integer.parseInt(firstCoordinates[1]);
        int c1 = Integer.parseInt(firstCoordinates[2]);

        String secondLetter = currentLetters.get(1).getTag().toString();
        String [] secondCoordinates = secondLetter.split("[rc]");
        int r2 = Integer.parseInt(secondCoordinates[1]);
        int c2 = Integer.parseInt(secondCoordinates[2]);
        //HORIZONTAL LEFT TO RIGHT
        if(r1==r2 && c2 == c1+1){
            //check remaining letters
            for(int i=2;i<currentLetters.size(); i++){
                r1 = r2;
                c1 = c2;
                String letter = currentLetters.get(i).getTag().toString();
                String [] coordinates = letter.split("[rc]");
                r2 = Integer.parseInt(coordinates[1]);
                c2 = Integer.parseInt(coordinates[2]);
                if(!(r1==r2) || !(c2==c1+1)){
                    return false;
                }
            }
        }
        //HORIZONTAL RIGHT TO LEFT
        else if(r1==r2 && c2 ==c1-1){
            //check remaining letters
            for(int i=2;i<currentLetters.size(); i++){
                r1 = r2;
                c1 = c2;
                String letter = currentLetters.get(i).getTag().toString();
                String [] coordinates = letter.split("[rc]");
                r2 = Integer.parseInt(coordinates[1]);
                c2 = Integer.parseInt(coordinates[2]);
                if(!(r1==r2) || !(c2==c1-1)){
                    return false;
                }
            }

        }

        //VERTICAL UP DOWN
        else if(r2 == r1+1 && c2 ==c1){
            //check remaining letters
            for(int i=2;i<currentLetters.size(); i++){
                r1 = r2;
                c1 = c2;
                String letter = currentLetters.get(i).getTag().toString();
                String [] coordinates = letter.split("[rc]");
                r2 = Integer.parseInt(coordinates[1]);
                c2 = Integer.parseInt(coordinates[2]);
                if(!(r2==r1+1) || !(c2==c1)){
                    return false;
                }
            }

        }

        //VERTICAL DOWN UP
        else if(r2 == r1-1 && c2 == c1){
            //check remaining letters
            for(int i=2;i<currentLetters.size(); i++){
                r1 = r2;
                c1 = c2;
                String letter = currentLetters.get(i).getTag().toString();
                String [] coordinates = letter.split("[rc]");
                r2 = Integer.parseInt(coordinates[1]);
                c2 = Integer.parseInt(coordinates[2]);
                if(!(r2==r1-1) || !(c2==c1)){
                    return false;
                }
            }

        }

        //DIAGONAL UPPER LEFT TO LOWER RIGHT
        else if(r2 == r1+1 && c2 == c1+1){
            //check remaining letters
            for(int i=2;i<currentLetters.size(); i++){
                r1 = r2;
                c1 = c2;
                String letter = currentLetters.get(i).getTag().toString();
                String [] coordinates = letter.split("[rc]");
                r2 = Integer.parseInt(coordinates[1]);
                c2 = Integer.parseInt(coordinates[2]);
                if(!(r2==r1+1) || !(c2==c1+1)){
                    return false;
                }
            }
        }

        //DIAGONAL LOWER RIGHT TO UPPER LEFT
        else if (r2 == r1 -1 && c2 == c1-1){
            //check remaining letters
            for(int i=2;i<currentLetters.size(); i++){
                r1 = r2;
                c1 = c2;
                String letter = currentLetters.get(i).getTag().toString();
                String [] coordinates = letter.split("[rc]");
                r2 = Integer.parseInt(coordinates[1]);
                c2 = Integer.parseInt(coordinates[2]);
                if(!(r2==r1-1) || !(c2==c1-1)){
                    return false;
                }
            }

        }

        //DIAGONAL UPPER RIGHT TO LOWER LEFT
        else if (r2 == r1+1 && c2 == c1 -1){
            //check remaining letters
            for(int i=2;i<currentLetters.size(); i++){
                r1 = r2;
                c1 = c2;
                String letter = currentLetters.get(i).getTag().toString();
                String [] coordinates = letter.split("[rc]");
                r2 = Integer.parseInt(coordinates[1]);
                c2 = Integer.parseInt(coordinates[2]);
                if(!(r2==r1+1) || !(c2==c1-1)){
                    return false;
                }
            }

        }

        //DIAGONAL LOWER LEFT TO UPPER RIGHT
        else if ( r2 == r1-1 && c2 == c1+1){
            //check remaining letters
            for(int i=2;i<currentLetters.size(); i++){
                r1 = r2;
                c1 = c2;
                String letter = currentLetters.get(i).getTag().toString();
                String [] coordinates = letter.split("[rc]");
                r2 = Integer.parseInt(coordinates[1]);
                c2 = Integer.parseInt(coordinates[2]);
                if(!(r2==r1-1) || !(c2==c1+1)){
                    return false;
                }
            }

        }

        //NOT ADJACENT
        else{
            //Toast.makeText(getBaseContext(), "NOT ADJACENT", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    private void setButtonListeners(){
        String buttonID;
        for(int i=1; i<=ROWS; i++){
            for(int j=1; j<=COLUMNS; j++){
                buttonID = "r"+i+"c"+j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                final Button b = (Button) findViewById(resID);
                if (!reservedStrings.contains(buttonID)){
                    b.setText(generateRandomLetter());
                }
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int color = ((ColorDrawable)b.getBackground()).getColor();
                        if(color == Color.TRANSPARENT){
                            b.setBackgroundColor(Color.YELLOW);
                            currentLetters.add(b);
                        }
                        else if(color == Color.YELLOW){
                            b.setBackgroundColor(Color.TRANSPARENT);
                            currentLetters.remove(b);
                        }

                    }
                });
            }
        }
        Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //call method to build a string from letters in the queue
                String currentWord = processWord();
                //Toast.makeText(getBaseContext(), currentWord, Toast.LENGTH_SHORT).show();
                //compare built word with words from valid words list
                if(validWords.contains(currentWord)&& checkAdjacency()) {
//                    //if match found, update text that says x/6 words found
                    //Toast.makeText(getBaseContext(), "Word Found!", Toast.LENGTH_SHORT).show();
                    wordsFound++;
                    updateFoundWords(wordsFound);
                    int resID = getResources().getIdentifier(currentWord, "id", getPackageName());
                    final TextView tv = (TextView) findViewById(resID);
                    tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//                    //turn buttons in word to green
                    Iterator itr = currentLetters.iterator();
                    while(itr.hasNext()){
                        Button button = (Button)itr.next();
                        button.setBackgroundColor(Color.GREEN);
                        itr.remove();

                    }

                   //clear current word arraylist
                   validWords.remove(currentWord);
               }
                else{
                    Toast.makeText(getBaseContext(), "Invalid Word!", Toast.LENGTH_SHORT).show();
                    //turn all buttons back to transparent
                    Iterator itr = currentLetters.iterator();
                    while(itr.hasNext()){
                        Button button = (Button)itr.next();
                        button.setBackgroundColor(Color.TRANSPARENT);
                        itr.remove();

                    }

               }
                if (validWords.size()== 0 && wordsFound == 6){
                    Toast.makeText(getBaseContext(), "YOU FOUND ALL THE WORDS!!!", Toast.LENGTH_SHORT).show();
                }
                //check if all words are found
                // if yes, display toast
            }
        });

    }
}
