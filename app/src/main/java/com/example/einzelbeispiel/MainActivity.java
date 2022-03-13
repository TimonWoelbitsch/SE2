package com.example.einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
            else {
                Button calculate = findViewById(R.id.berechnen);
                calculate.setVisibility(View.VISIBLE);
                calculate.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(MainActivity.this, Calculate.class);
                        myIntent.putExtra("key", message);
                        MainActivity.this.startActivity(myIntent);
                    }



                });
            }


    }


    public String server(String matno) throws IOException {

        try {
            Button calculate = findViewById(R.id.berechnen);
            calculate.setVisibility(View.INVISIBLE);
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



}