package com.advanced.ocr.calculation;

import android.content.Intent;
import android.graphics.Color;
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

public class DigitalDocumentActivity extends AppCompatActivity {

    private DigitalDocumentAdapter digitalDocumentAdapter;
    private ArrayList<scanArrayList> scanArrayListsObject;
    private RecyclerView rvDigittalDocument;

    private Toolbar toolbar;

    private double dArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_document);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Demo");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        scanArrayListsObject = new ArrayList<>();

        digitalDocumentAdapter = new DigitalDocumentAdapter(DigitalDocumentActivity.this, scanArrayListsObject);
        rvDigittalDocument = findViewById(R.id.rvDigitalDocument);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvDigittalDocument.setLayoutManager(mLayoutManager);
        rvDigittalDocument.setAdapter(digitalDocumentAdapter);

        DigitalDocument();
    }

    public void DigitalDocument()
    {
        String sTag = "string_req";
        String sURL = "http://192.168.0.108:57700/get_single_upload/nishtha1297/t3.jpg";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, sURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String sResponse) {

                        try {
                            JSONObject mJSONObject = new JSONObject(sResponse);

                            JSONArray mJSONArray = mJSONObject.getJSONArray("all_images_uploaded_by_user_data");
                            Log.d("DigitalmArraylength", String.valueOf(mJSONArray.length()));
                            for (int i = 0; i < mJSONArray.length(); i++) {
                                JSONObject mjJSONObject = mJSONArray.getJSONObject(i);
                                String sDocTitle = mjJSONObject.getString("image_path");
                                Log.d("DigitalResponse", sDocTitle);
                                JSONArray valueJASONArray = mjJSONObject.getJSONArray("single_entry_values");
                                for (int j = 0; j < valueJASONArray.length(); j++) {
                                    JSONObject singleEntryValuesJASONObject = valueJASONArray.getJSONObject(j);
                                    scanArrayList objectScanArrayList = new scanArrayList();
                                    objectScanArrayList.setArea(singleEntryValuesJASONObject.getDouble("area"));
                                    objectScanArrayList.setLength(singleEntryValuesJASONObject.getDouble("length"));
                                    objectScanArrayList.setWidth(singleEntryValuesJASONObject.getDouble("width"));
                                    objectScanArrayList.setSerialNumber(singleEntryValuesJASONObject.getInt("entry_number"));

                                    scanArrayListsObject.add(objectScanArrayList);
                                }
                            }

                            digitalDocumentAdapter.notifyDataSetChanged();

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

        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void logOut(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
