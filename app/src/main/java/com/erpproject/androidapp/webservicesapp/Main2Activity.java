package com.erpproject.androidapp.webservicesapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Button btnstart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        progressDialog=new ProgressDialog(this);
        btnstart=(Button)findViewById(R.id.btnstart);
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                mytask  mytask = new mytask();
//                mytask.execute();

                new mytask().execute();
            }
        });

    }
    class  mytask extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {


            for(long i = 0 ;i<10001;i++){

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(),"Done Exxevuion method",Toast
            .LENGTH_LONG).show();
            progressDialog.dismiss();
        }
    }
}
