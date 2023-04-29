package com.example.savedatatomemory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
        EditText mEtText;
        Button mBtnSave, mBtnLoad;

        @Override
        protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEtText = (EditText) findViewById(R.id.et_message);
        mBtnSave = (Button) findViewById(R.id.btn_save);
        mBtnLoad = (Button) findViewById(R.id.btn_load);
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mEtText.getText().toString();

                if (!message.isEmpty()) {
                    try {
                        FileOutputStream fout = openFileOutput("textfile.txt", MODE_PRIVATE);
                        OutputStreamWriter osw = new OutputStreamWriter(fout);
                        osw.write(message);
                        osw.flush();
                        osw.close();
                        Toast.makeText(getApplicationContext(), "File saved successfully", Toast.LENGTH_LONG).show();
                        mEtText.setText("");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Type some data to save..", Toast.LENGTH_LONG).show();
                }
            }
        });

        mBtnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fin = openFileInput("textfile.txt");
                    InputStreamReader isr = new InputStreamReader(fin);
                    char[] inputBuffer = new char[100];
                    String s = "";
                    int charRead = 0;
                    while ((charRead = isr.read(inputBuffer)) > 0) {
                        String readString = String.copyValueOf(inputBuffer, 0, charRead);
                        s += readString;
                        inputBuffer = new char[100];
                    }
                    mEtText.setText(s);
                    Toast.makeText(getApplicationContext(), "File loaded successfully", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    }
