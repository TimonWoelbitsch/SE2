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
        String value = intent.getStringExtra("key"); //Stored String
        TextView response = (TextView)findViewById(R.id.resultview);

        //value = Matno
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

        int[] resultreverse = new int[result.length];
        int i = 0;
        for(int k = resultreverse.length-1; k > 0; k--){
            resultreverse[i] = result [k];
            i++;
        }



        String joinedNumbers = Arrays.toString(result).replace("[","").replace("]","").replace(",", " ");
        String reverseNumbers = Arrays.toString(resultreverse).replace("[","").replace("]","").replace(",", " ");
        System.out.println(reverseNumbers);

        return reverseNumbers;
    }
}