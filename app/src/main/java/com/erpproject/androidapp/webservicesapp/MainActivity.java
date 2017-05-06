package com.erpproject.androidapp.webservicesapp;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView txtresult;
    String message_url = "http://192.168.177.1/formuser/message.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtresult=(TextView)findViewById(R.id.txtdata);



        try {
            ConnectivityManager connec
                    =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connec.getActiveNetworkInfo();
            if(networkInfo.isConnected()){
                new Backservicestask().execute();

            }


        }catch (SecurityException se){
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();

        }



    }


    class Backservicestask extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(),"start ",Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params) {



                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                URL url = new URL(message_url); // get link thorugh bytes

                                HttpURLConnection httpURLConnection =
                                        (HttpURLConnection) url.openConnection();

                                //InputStreamReader can convert bytes from url to charchaer
                                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                                //  BufferedReader convert charachter from inputStreamReader to string
                                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                                final  String getdata = bufferedReader.readLine();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        txtresult.setText(getdata);
                                    }
                                });

                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    };


                    Thread thread = new Thread(runnable);
                    thread.start();



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(),"Done Exxevuion method",Toast
                    .LENGTH_LONG).show();
           //get result from method doinbackgrond and then put this result in textview

            txtresult.setText(s);
        }
    }
}
