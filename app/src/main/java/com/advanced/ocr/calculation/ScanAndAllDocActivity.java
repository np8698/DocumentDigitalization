package com.advanced.ocr.calculation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ScanAndAllDocActivity extends AppCompatActivity {

    private final static String TAG = ScanAndAllDocActivity.class.getSimpleName();

    private AllDocumentAdapter documentAdapter;
    private ArrayList<scanArrayList> scArrayListObject;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_and_all_doc);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Demo");

        scArrayListObject = new ArrayList<>();

        ImageView ivDocument = findViewById(R.id.ivScannedImage);

        Picasso.get()
                .load(R.drawable.ic_t3)
                .resize(700, 1000)
                .into(ivDocument);

    }

    public void scanDocuments(View view) {
        Intent intent = new Intent(this, DigitalDocumentActivity.class);
        startActivity(intent);
    }

    public void allDocuments(View view) {
        Intent intent = new Intent(this, AllDocumentActivity.class);
        startActivity(intent);
    }

    public void logOut(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void FullImage(View view){

        Intent intent = new Intent(this, ScanedDocumentActivity.class);
        startActivity(intent);

    }
}
