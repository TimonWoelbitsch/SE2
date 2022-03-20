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
        //onClick starts sendmassage in send_1
    public void sendmassage(View view) throws IOException {
        //EditTextview - enter Matno
        EditText editText = (EditText) findViewById(R.id.number_matrik);
        //take text from EditTextview from user
        String message = editText.getText().toString();
        //take server response
        TextView response = (TextView)findViewById(R.id.server_response);

            //get response from method server
            String servermessage = server(message);
            //TextView response - server (Matno, converted)
            response.setText(servermessage); //modifiedSentence

            //now check content of servermessage

            //compare String from server with "Dies ist keine ...".
            if(servermessage.equalsIgnoreCase("Dies ist keine gueltige Matrikelnummer")){

            }
            else {
                Button calculate = findViewById(R.id.berechnen);
                calculate.setVisibility(View.VISIBLE);

                calculate.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(MainActivity.this, Calculate.class);
                        //Send Matno to new activity for accsess
                        myIntent.putExtra("key", message); //Stored String
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

            //Create welcome (clientSocket) at port 53212
            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);

            //Create output stream, attached to socket
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            //Create input stream, attached to socket
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            sentence = matno;

            //Write out line to socket
            outToServer.writeBytes(sentence + "\n");

            //Read line from server
            modifiedSentence = inFromServer.readLine();
            System.out.println("FROM SERVER: " + modifiedSentence);
            clientSocket.close();
            return modifiedSentence;

        }
        catch (Exception e){
            e.printStackTrace();

        }


        return null;
    }



}