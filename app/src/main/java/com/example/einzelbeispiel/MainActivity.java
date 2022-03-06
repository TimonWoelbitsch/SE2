package com.example.einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sendmassage(View view) throws IOException {
        EditText editText = (EditText) findViewById(R.id.number_matrik);
        String message = editText.getText().toString();

        server(message);



    }


    public void server(String matno) throws IOException {



        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String sentence;
            String modifiedSentence;
            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);
            System.out.println("Test1");
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            sentence = matno;

            outToServer.writeBytes(sentence + "\n");

            modifiedSentence = inFromServer.readLine();

            System.out.println("FROM SERVER: " + modifiedSentence);

            clientSocket.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }




    }



}