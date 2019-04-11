package com.advanced.ocr.calculation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ScanedDocumentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scaned_document);

        ImageView ivLastScannedDocumentImage = findViewById(R.id.ivLastScannedImage);
        Picasso.get().load(R.drawable.ic_t3).resize(1000,1200).into(ivLastScannedDocumentImage);



    }
}