package com.advanced.ocr.calculation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class AllDocumentAdapter extends RecyclerView.Adapter<AllDocumentAdapter.ViewHolder> {

    private ArrayList<String> TitleDocument;

    public AllDocumentAdapter(ArrayList<String> title) {
        this.TitleDocument = title;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.linearlayout_all_document, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {



        String title = TitleDocument.get(i);

        viewHolder.tvTitle.setText(title);
        Log.d( "Title",title);
    }

    @Override
    public int getItemCount() {
        return TitleDocument.size();
    }

    public void setData(ArrayList<String> titles){
        this.TitleDocument = titles;
        Log.d("length",String.valueOf(TitleDocument.size()));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        Button bScanDocument;
        Button bDigitalDocument;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvDocumentTitle);
            bScanDocument = itemView.findViewById(R.id.bScannedDocument);
            bDigitalDocument = itemView.findViewById(R.id.bDigitalDocument);
        }

    }

}
