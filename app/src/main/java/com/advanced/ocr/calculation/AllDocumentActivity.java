package com.advanced.ocr.calculation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllDocumentActivity extends AppCompatActivity {

    private final static String TAG = AllDocumentActivity.class.getSimpleName();

    private ArrayList<String> titleArrayList = new ArrayList<>();
    private AllDocumentAdapter documentAdapter;
    private RecyclerView rvAllDocument;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_document);

        rvAllDocument = findViewById(R.id.rvAllDocument);

        documentAdapter = new AllDocumentAdapter(titleArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvAllDocument.setLayoutManager(mLayoutManager);
        rvAllDocument.setAdapter(documentAdapter);

        getTitleDocuments();

    }


    private void getTitleDocuments() {

        String sTag = "string_req";

        String sUrl = "http://192.168.0.109:60169/get_all_uploads/saurav1297";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, sUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String sResponse) {


                        try {
                            JSONObject mJSONObject = new JSONObject(sResponse);

                            JSONArray mJSONArray = mJSONObject.getJSONArray("all_images_uploaded_by_user_data");
                            Log.d("mArraylength", String.valueOf(mJSONArray.length()));
                            for (int i = 0; i < mJSONArray.length(); i++) {
                                JSONObject mjJSONObject = mJSONArray.getJSONObject(i);
                                String sDocTitle = mjJSONObject.getString("image_path");
                                Log.d("AllDocument_Response", sDocTitle);
                                titleArrayList.add(sDocTitle);
                            }
                            documentAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            Log.d("catch", e.toString());
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", error.toString());

                    }
                });


        AppController.getInstance().addToRequestQueue(stringRequest, sTag);
    }


    public void ScannedDocumentImage(View view) {
        Intent intent = new Intent(this, ScanedDocumentActivity.class);
        startActivity(intent);

    }

    public void ShowDigitalDocument(View view){
        Intent intent = new Intent(this, DigitalDocumentActivity.class);
        startActivity(intent);
    }
}
