package com.example.ppc_tp3_isea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;

public class Screen3Activity extends AppCompatActivity {

    ImageView image1;
    Button button1;
    ImageView image2;
    Button button2;
    ImageView image3;
    Button button3;
    ImageView image4;
    Button button4;
    ImageView image5;
    Button button5;
    ImageView image6;
    Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);
        image1=findViewById(R.id.Photo1);
        button1=findViewById(R.id.button_Photo1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                sendImage(image1);
            }
        });
        image2=findViewById(R.id.Photo2);
        button2=findViewById(R.id.button_Photo2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                sendImage(image2);
            }
        });
        image3=findViewById(R.id.Photo3);
        button3=findViewById(R.id.button_Photo3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                sendImage(image3);
            }
        });
        image4=findViewById(R.id.Photo4);
        button4=findViewById(R.id.button_Photo4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                sendImage(image4);
            }
        });
        image5=findViewById(R.id.Photo5);
        button5=findViewById(R.id.button_Photo5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                sendImage(image5);
            }
        });
        image6=findViewById(R.id.Photo6);
        button6=findViewById(R.id.button_Photo6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                sendImage(image6);
            }
        });
    }

    protected void sendImage(ImageView image){
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        BitmapDrawable drawable = (BitmapDrawable)image.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        File f = new File(getExternalCacheDir()+"/"+getResources().getString(R.string.app_name)+".png");
        Intent shareintent;

        try {
            FileOutputStream outputStream = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

            outputStream.flush();
            outputStream.close();

            shareintent = new Intent(Intent.ACTION_SEND);
            shareintent.setType("image/*");
            shareintent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
            shareintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        }catch (Exception e){
            throw new RuntimeException();
        }
        startActivity(Intent.createChooser(shareintent, "Share Image"));
    }
}