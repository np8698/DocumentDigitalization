package com.advanced.ocr.calculation;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DigitalDocumentAdapter extends RecyclerView.Adapter<DigitalDocumentAdapter.ViewHolder> {

    private ArrayList<scanArrayList> digitalDocumentModelArrayList;

    private Context context;

    public DigitalDocumentAdapter(Context context, ArrayList<scanArrayList> digitalDocumentModelArrayList) {
        this.digitalDocumentModelArrayList = digitalDocumentModelArrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.linearlayout_digital_document, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        scanArrayList digitalDocumentModel = digitalDocumentModelArrayList.get(i);

        viewHolder.tvSerialNumber.setText(String.valueOf(digitalDocumentModel.getSerialNumber()));

        viewHolder.tvArea.setText(String.valueOf(digitalDocumentModel.getArea()));

        viewHolder.tvWidth.setText(String.valueOf(digitalDocumentModel.getWidth()));

        viewHolder.tvLength.setText(String.valueOf(digitalDocumentModel.getLength()));

        if(digitalDocumentModel.getArea()==0.0) {
            viewHolder.itemView.setBackgroundColor(Color.RED);
            viewHolder.tvSerialNumber.setTextColor(Color.WHITE);
            viewHolder.tvLength.setTextColor(Color.WHITE);
            viewHolder.tvWidth.setTextColor(Color.WHITE);
            viewHolder.tvArea.setTextColor(Color.WHITE);
            viewHolder.tvLengthTitle.setTextColor(Color.WHITE);
            viewHolder.tvWidthTitle.setTextColor(Color.WHITE);
            viewHolder.tvAreaTitle.setTextColor(Color.WHITE);

        } else {
            viewHolder.itemView.setBackgroundColor(Color.WHITE);
            viewHolder.tvSerialNumber.setTextColor(Color.BLACK);
            viewHolder.tvLength.setTextColor(Color.BLACK);
            viewHolder.tvWidth.setTextColor(Color.BLACK);
            viewHolder.tvArea.setTextColor(Color.BLACK);
            viewHolder.tvLengthTitle.setTextColor(Color.BLACK);
            viewHolder.tvWidthTitle.setTextColor(Color.BLACK);
            viewHolder.tvAreaTitle.setTextColor(Color.BLACK);
        }

    }

    @Override
    public int getItemCount() { return digitalDocumentModelArrayList.size();    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvSerialNumber;
        TextView tvLength;
        TextView tvWidth;
        TextView tvArea;
        TextView tvLengthTitle;
        TextView tvWidthTitle;
        TextView tvAreaTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSerialNumber = itemView.findViewById(R.id.tvSerialNumber);
            tvLength = itemView.findViewById(R.id.tvLength);
            tvWidth = itemView.findViewById(R.id.tvWidth);
            tvArea = itemView.findViewById(R.id.tvArea);
            tvLengthTitle = itemView.findViewById(R.id.tvLengthTitle);
            tvWidthTitle = itemView.findViewById(R.id.tvWidthTitle);
            tvAreaTitle = itemView.findViewById(R.id.tvAreaTitle);

        }
    }

}
