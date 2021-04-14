package com.nirajgupta.musicdirectory;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Screen2Activity extends AppCompatActivity {

    Button buttonSubmit;
    EditText etTitle, etDescription;

    final String url = "http://ec2-3-19-242-59.us-east-2.compute.amazonaws.com:8000/djangoapi/v1/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen2);

        etTitle = (EditText) findViewById(R.id.idEditTitle);
        etDescription = (EditText) findViewById(R.id.idEditDescription);

        addListenerOnButtonSubmit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_info:
                infoClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void infoClicked() {
        Intent i = new Intent(Screen2Activity.this, AuthorInfo.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }

    public void addListenerOnButtonSubmit() {

        final Context context = this;
        buttonSubmit = (Button) findViewById(R.id.button2);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String Title = etTitle.getText().toString();
                String Description = etDescription.getText().toString();

                new AddSong().execute(Title, Description);

                onBackPressed();
            }
        });
    }


    public class AddSong extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String Title = strings[0];
            String Description = strings[1];

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody formBody = new FormBody.Builder()
                        .add("title", Title)
                        .add("description", Description)
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        .post(formBody)
                        .build();
                Response response = null;

                try {
                    response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {

                        Toast.makeText(Screen2Activity.this,"Added Successfully",Toast.LENGTH_LONG).show();
//                            Intent i = new Intent(Screen2Activity.this, MainActivity.class);
//                            startActivity(i);
                            finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }

}
