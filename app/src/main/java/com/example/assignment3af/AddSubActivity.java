package com.example.assignment3af;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddSubActivity extends AppCompatActivity implements View.OnClickListener {

    static final String COUNT = "count";
    static final String RANDOM = "random";
    static final int RANDOM_NUMBER_REQUEST = 1;
    TextView mainNum;
    Button buttonAdd;
    Button buttonSub;
    Button buttonGet;
    int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub);


        mainNum = (TextView)(findViewById(R.id.text_total_num));

        // the three buttons we need to complete the assignment
        buttonAdd = (Button)findViewById(R.id.butAdd);
        buttonSub = (Button)findViewById(R.id.butSub);
        buttonGet = (Button)findViewById(R.id.butGet);

        //we implemented View.onClickListener above so we can use "this"
        buttonAdd.setOnClickListener(this);
        buttonSub.setOnClickListener(this);
        buttonGet.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_sub, menu);
        return true;
    }
    @Override
    public void onClick(View v){

        //set-up a switch statement since we are handling all the buttons here
        switch (v.getId()) {
            case R.id.butAdd:
                counter++;
                mainNum.setText(String.valueOf(counter));
                break;
            case R.id.butSub:
                counter--;
                mainNum.setText(String.valueOf(counter));
                break;
            case R.id.butGet:
                Intent intent = new Intent(this, RandomActivity.class);
                
                //even though startActivityForResult is deprecated I left it in here, it is less complex
                //than the new way.  Easy to change but going to leave it here for now.  Still works
                startActivityForResult(intent, RANDOM_NUMBER_REQUEST);
                break;

        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.random:
                Intent intent = new Intent(getApplicationContext(),RandomActivity.class);
                intent.putExtra(COUNT, counter);  //send the current counter to the second Activity
                startActivity(intent);
                return true;

            default:
                return true;
        }

        //   return super.onOptionsItemSelected(item);
    }

    //this will handle the return call asking for a value only works when the get button passes us to Random
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RANDOM_NUMBER_REQUEST) {
            if (resultCode == RESULT_OK) {

                //received back the random value
                counter = data.getIntExtra(RANDOM, 0);
                mainNum.setText(String.valueOf(counter));

                //Just put this in for testing purposes so I know when this method is the called method
                //Remove before production
                Context context = getApplicationContext();
                CharSequence text = "Called Back with return value!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }
}

