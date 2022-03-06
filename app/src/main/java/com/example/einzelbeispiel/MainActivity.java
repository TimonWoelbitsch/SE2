package com.example.einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;

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


        if(server(message)){
            String result = clickbait(message);
            response.setText(result);
        }else{
            response.setText("Dies ist keine gueltige Matrikelnummer");
        }
    }


    public boolean server(String matno) throws IOException {

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
            if(modifiedSentence=="Dies ist keine gueltige Matrikelnummer"){
                return false;
            }
            clientSocket.close();

            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
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
        return Arrays.toString(result);
    }


}