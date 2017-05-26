package com.example.sony.members;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ParseJSON extends ActionBarActivity implements View.OnClickListener{

    private String myJSONString;

    private static final String JSON_ARRAY ="result";
    private static final String NAME = "name";
    private static final String PHOONE= "phone";
    private static final String DISTRICT = "district";
    private static final String POST = "post";

    private JSONArray users = null;

    private int TRACK = 0;

    private EditText editname;
    private EditText editphone;
    private EditText editdistrict;
    private EditText editpost;

    Button btnPrev;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_json);

        Intent intent = getIntent();
        myJSONString = intent.getStringExtra(MainActivity.MY_JSON);


        editname = (EditText) findViewById(R.id.editname);
        editphone = (EditText) findViewById(R.id.editphone);
        editdistrict = (EditText) findViewById(R.id.editdistrict);
        editpost = (EditText) findViewById(R.id.editpost);

        btnPrev = (Button) findViewById(R.id.buttonPrev);
        btnNext = (Button) findViewById(R.id.buttonNext);

        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        extractJSON();

        showData();
    }



    private void extractJSON(){
        try {
            JSONObject jsonObject = new JSONObject(myJSONString);
            users = jsonObject.getJSONArray(JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void moveNext(){
        if(TRACK<users.length()){
            TRACK++;
        }
        showData();
    }

    private void movePrev(){
        if(TRACK>0){
            TRACK--;
        }
        showData();
    }

    private void showData(){
        try {
            JSONObject jsonObject = users.getJSONObject(TRACK);

            editname.setText(jsonObject.getString(NAME));

            editphone.setText(jsonObject.getString(PHOONE));
            editdistrict.setText(jsonObject.getString(DISTRICT));
            editpost.setText(jsonObject.getString(POST));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void onClick(View v) {
        if(v == btnNext){
            moveNext();
        }
        if(v == btnPrev){
            movePrev();
        }
    }
}