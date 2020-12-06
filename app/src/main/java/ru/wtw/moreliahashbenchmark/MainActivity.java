package ru.wtw.moreliahashbenchmark;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.theromus.sha.Keccak;

import java.nio.charset.Charset;

import static com.theromus.sha.Parameters.KECCAK_256;
import static com.theromus.sha.Parameters.SHA3_256;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Button buttonStartBenchmark = findViewById(R.id.buttonStart);
        buttonStartBenchmark.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        byte[] data;
        Keccak keccak = new Keccak();
        long counter;
        int degreeCounter;
        long maxCounter;
        int maxDegreeCount;
        String algo;
        long startTime = System.nanoTime();

        long stopTime = System.nanoTime();

        TextView editIterCount = findViewById(R.id.editTextIterCount);
        EditText editResult = findViewById(R.id.editResult);
        maxDegreeCount = Integer.parseInt(editIterCount.getText().toString());

        for (degreeCounter = 0; degreeCounter < maxDegreeCount; degreeCounter++ ) {
            maxCounter = 1;
            for (int i = 1; i <= degreeCounter; i++) {
                maxCounter *= 10;
            }
            algo = "KECCAK_256";
            data = "The quick brown fox jumps over the lazy dog".getBytes(Charset.forName("UTF-8"));
            startTime = System.currentTimeMillis();
            for (counter = 0; counter < maxCounter; counter++) {
                data = keccak.getHash(data, KECCAK_256);
            }
            stopTime = System.currentTimeMillis();
            editResult.setText(editResult.getText()+"\n"+algo+" iteration "+String.valueOf(maxCounter)+" time "+String.valueOf(stopTime-startTime)+" ms");
            algo = "SHA3_256";
            data = "The quick brown fox jumps over the lazy dog".getBytes(Charset.forName("UTF-8"));
            startTime = System.currentTimeMillis();
            for (counter = 0; counter < maxCounter; counter++) {
                data = keccak.getHash(data, SHA3_256);
            }
            stopTime = System.currentTimeMillis();
            editResult.setText(editResult.getText()+"\n"+algo+" iteration "+String.valueOf(maxCounter)+" time "+String.valueOf(stopTime-startTime)+" ms");

        }

    }
}