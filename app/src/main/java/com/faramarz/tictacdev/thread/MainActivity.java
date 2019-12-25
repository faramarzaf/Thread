package com.faramarz.tictacdev.thread;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.faramarz.tictacdev.thread.image_downloader.DownloaderActivity;
import com.faramarz.tictacdev.thread.image_downloader.DownloaderSample2;

public class MainActivity extends AppCompatActivity {

    TextView txtMain;
    Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    Boolean isSDSupportedDevice = Environment.isExternalStorageRemovable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMain = findViewById(R.id.txtMain);
        launchDelayThread();
        changeTextThread();
    }

    void launchDelayThread() {
        // usually use handler when you want change any part of view in specific time.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "After 3 seconds", Toast.LENGTH_SHORT).show();
            }
        }, 3000);
    }

    void changeTextThread() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txtMain.setText("Text Changed");
            }
        }, 3000);


    }

    void runOnUi() {
        // runOnUiThread is a kind of handler that just happen in main thread
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // do something
            }
        });
    }


   /* public class MyAsync extends AsyncTask<String, Void, Void> {

        // first param --> param             the return type of doInBackground method
        // second param --> progress    the return type of onProgressUpdate method
        // third param -->result             the return type of onPostExecute method

        @Override
        protected void onPreExecute() {
            // do something in ui thread before background  work start
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... voids) {
            // do something in background
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // do something in ui-thread
            super.onPostExecute(aVoid);
        }
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 1, "Download image");
        menu.add(0, 2, 2, "Download media");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                startActivity(new Intent(this, DownloaderActivity.class));
                break;
            case 2:

                if (isSDSupportedDevice && isSDPresent) {
                    // yes SD-card is present
                    startActivity(new Intent(this, DownloaderSample2.class));
                } else {
                    Toast.makeText(this, "NO SD card", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, DownloaderSample2.class));
                    break;
                }

            default:
                break;

        }

        return true;
    }
}

