package com.faramarz.tictacdev.thread.image_downloader;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.faramarz.tictacdev.thread.R;

import java.io.InputStream;
import java.net.URL;

public class DownloaderActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView img;
    Button btnDownload;
    ProgressDialog pg;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloder);
        img = findViewById(R.id.img);
        btnDownload = findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        DownloadImage downloadImage = new DownloadImage();
        downloadImage.execute("https://cnet4.cbsistatic.com/img/QJcTT2ab-sYWwOGrxJc0MXSt3UI=/2011/10/27/a66dfbb7-fdc7-11e2-8c7c-d4ae52e62bcc/android-wallpaper5_2560x1600_1.jpg");
    }

    public class DownloadImage extends AsyncTask<String, String, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pg = new ProgressDialog(DownloaderActivity.this);
            pg.setTitle("Downloading...");
            pg.setMessage("Please wait...");
            pg.setCancelable(false);
            pg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pg.show();
        }

        @Override
        protected Bitmap doInBackground(String... args) {
            try {

                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap image) {
            img.setImageBitmap(image);
            pg.dismiss();
        }
    }


}
