package com.example.assignment3af;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class RandomActivity extends AppCompatActivity {


    static final String COUNT = "count";
    static final String RANDOM = "random";

    TextView mainView2;
    TextView mainNum2;
    Button buttonRdn;
    Button buttonSave;
    Random rand = new Random();
    int randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        //Make the random and save buttons work

        mainView2 = (TextView) (findViewById(R.id.text_random));
        mainNum2 = (TextView) (findViewById(R.id.text_random_num));
        buttonRdn = (Button) findViewById(R.id.butRandom);
        buttonSave = (Button) findViewById(R.id.butSave);

        //get the value passed in by the other activity
        Intent intent = getIntent();
        int count = intent.getIntExtra(COUNT, 0);

        //set the textfield to the received value
        mainNum2.setText(String.valueOf(count));


        //listener for the Random button
        buttonRdn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                randomNumber = rand.nextInt(99 - 0) + 0;
                mainNum2.setText(String.valueOf(randomNumber));

            }

        });

        //Listener For the save button
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent intent = new Intent();
                intent.putExtra(RANDOM, randomNumber);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_sub, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.addSubtract:
                Intent intent = new Intent(getApplicationContext(),AddSubActivity.class);
                startActivity(intent);
                return true;

            default:
                return true;
        }
    }



}