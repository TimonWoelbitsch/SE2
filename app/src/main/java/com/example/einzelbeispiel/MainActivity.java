package com.example.einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sendmassage(View view) throws IOException {
        EditText editText = (EditText) findViewById(R.id.number_matrik);
        String message = editText.getText().toString();
        TextView response = (TextView)findViewById(R.id.server_response);

            String servermessage = server(message);
            response.setText(servermessage);


            if(servermessage.equalsIgnoreCase("Dies ist keine gueltige Matrikelnummer")){

            }
            else{
                Button calculate = findViewById(R.id.berechnen);
                calculate.setVisibility(View.VISIBLE);
            }


    }


    public String server(String matno) throws IOException {

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String sentence;
            String modifiedSentence;
            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            sentence = matno;

            outToServer.writeBytes(sentence + "\n");

            modifiedSentence = inFromServer.readLine();
            System.out.println(modifiedSentence);
            clientSocket.close();
            return modifiedSentence;

        }
        catch (Exception e){
            e.printStackTrace();

        }


        return null;
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