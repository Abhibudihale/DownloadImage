package com.example.downloadimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btn;
    ImageView imageView;

    public class ImageDownload extends AsyncTask<String , Void , Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                Log.i("Inside Download","");
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream in = connection.getInputStream();

                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;


            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }



        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn);
        imageView=findViewById(R.id.imageView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("Btn pressed","");
                ImageDownload task = new ImageDownload();
                Bitmap myImage;
                try{

                    myImage = task.execute("https://i.pinimg.com/736x/73/93/be/7393bedf034cd2eaf5a1025ccb3f0532.jpg").get();
                    imageView.setImageBitmap(myImage);

                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });
    }
}