package com.example.kenstate.personvisitstatusupdate;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends Activity {
    Button button;
    Bundle bundle;
    String value;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Initializing button XML button id. findViewById is a method which
         * helps to initialize with particular id. btn_go_to_another_activity is
         * a button name which I have given in XML file
         */
        button = (Button)findViewById(R.id.messageReceiveBtn);
        // Initializing EditText view
        editText = (EditText) findViewById(R.id.dateMessagesSent);
        // Bundle
        bundle = new Bundle();
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // Get value from EditText from main_activity layout
                value = editText.getText().toString();
                bundle.putString("value", value);
                /*
                 * Intent is just like glue which helps to navigate one activity
                 * to another.
                 */
                Intent intent = new Intent(MainActivity.this,
                        PersonDetails.class);
                // Pass data to AnotherActivity
                intent.putExtras(bundle);
                startActivity(intent); // startActivity allow you to move
            }
        });
    }}

