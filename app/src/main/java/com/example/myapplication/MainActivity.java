package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {


    Integer current_guess = 0;
    Integer target_guess = 0;
    Context context = null;
    CharSequence text = "Hello toast!";
    int duration = 0;
    LinkedList guesses = new LinkedList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn_place_guess = findViewById(R.id.place_guess);
        final TextView input_guess = findViewById(R.id.guess_input);
        input_guess.setInputType(InputType.TYPE_CLASS_NUMBER);
        final TextView guess_feedback = findViewById(R.id.textView3);

        duration = Toast.LENGTH_SHORT;
        context = getApplicationContext();

        Toast toast = Toast.makeText(context, "Rate einfach eine Zahl zwischen 0 und 10 :)", duration);
        toast.show();

        generate_RandomNumber();

        input_guess.setText("");

        btn_place_guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    current_guess = Integer.parseInt(input_guess.getText().toString());
                }
                catch (Exception e){
                    guess_feedback.setText("Please enter an Integer. Try again!");
                    return;
                }


                guesses.add(current_guess);

                if(current_guess < target_guess){

                    String feedback = "Zu Niedrig!\n";

                    for (int i = 0; i < guesses.size(); i++) {
                        feedback+=" "+(guesses.get(i));
                    }

                    guess_feedback.setText(feedback);
                    input_guess.setText("");
                }
                else if(current_guess > target_guess){

                    String feedback = "Zu Hoch!\n";

                    for (int i = 0; i < guesses.size(); i++) {
                        feedback+=" "+(guesses.get(i));
                    }

                    guess_feedback.setText(feedback);
                    input_guess.setText("");
                }
                else if(current_guess == target_guess){
                    guess_feedback.setText("Korrekt! Du hast "+guesses.size()+" Versuche benötigt um "+target_guess+" zu erraten.");
                    input_guess.setText("");

                    Toast toast = Toast.makeText(context, "Das Spiel wurde zurückgesetzt. Rate einfach erneut eine Zahl zwischen 0 und 10 :)", duration);
                    toast.show();
                    generate_RandomNumber();
                    guesses = new LinkedList<Integer>();
                }
            }
        });

    }

    public void generate_RandomNumber(){
        target_guess = ((int)(Math.random() * 10));
    }
}
