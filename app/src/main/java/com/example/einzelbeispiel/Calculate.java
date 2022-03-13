package com.example.einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;

public class Calculate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        Intent intent = getIntent();
        String value = intent.getStringExtra("key"); //if it's a string you stored.
        TextView response = (TextView)findViewById(R.id.resultview);

        response.setText(clickbait(value));


    }

    public String clickbait(String matno){

        int [] click = new int[matno.length()];
        int numbers =0;
        for(int i = 0; i < click.length; i++){
            int number = Integer.parseInt(String.valueOf(matno.charAt(i)));
            if(number!= 1 && number!= 2 && number!= 3 && number!= 5 && number!= 7){
                click[i] = number;
                numbers++;
            }
            else{
                click[i] = 9;
            }

        }
        Arrays.sort(click);

        int[] result=new int[numbers];
        for(int j = 0; j<numbers; j++){
            result[j] = click [j];
        }

        String joinedNumbers = Arrays.toString(result).replace("[","").replace("]","").replace(",", " ");

        return joinedNumbers;
    }
}