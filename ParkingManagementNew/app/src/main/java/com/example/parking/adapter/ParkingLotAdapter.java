package com.example.parking.adapter;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parking.R;
import com.example.parking.model.Lot;
import com.example.parking.utils.DataHolder;

import java.util.ArrayList;

public class ParkingLotAdapter extends RecyclerView.Adapter<ParkingLotAdapter.ViewHolder> {

    private ArrayList<Lot> lotArrayList;

    public ParkingLotAdapter(ArrayList<Lot> lotArrayList) {
        this.lotArrayList = lotArrayList;
    }

    @NonNull
    @Override
    public ParkingLotAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_parking_lot, parent, false);

        return new ParkingLotAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParkingLotAdapter.ViewHolder holder, int position) {

        Lot lot = lotArrayList.get(position);
        Resources res = holder.itemView.getContext().getResources();
        holder.lotIdButton.setText(lot.getArea()+lot.getPosition());
        if(lot.getStatus() == false){
            holder.lotStatus.setText("Empty");
            holder.lotStatus.setTextColor(res.getColor(R.color.success));

            holder.lotIdButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(holder.itemView.getContext(), "Empty lot!!!", Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putString("LotId", lot.getArea()+lot.getPosition());
                    bundle.putInt("TypeId", lot.getVehicleTypeId());
                    Navigation.findNavController(v).navigate(R.id.navigation_check_in, bundle);
                }
            });
        }else{
            holder.lotStatus.setText("Parked");
            holder.lotStatus.setTextColor(res.getColor(com.google.android.material.R.color.design_default_color_error));
            holder.lotIdButton.setBackgroundColor(res.getColor(com.google.android.material.R.color.design_default_color_error));
            holder.lotParkingVehicle.setText(lot.getParkingVehicle().getId());

            holder.lotIdButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(lot.getParkingVehicle().getUserId() == DataHolder.getInstance().getLoginUser().getId()){
//                        Toast.makeText(holder.itemView.getContext(), "Your vehicle parked lot!!!", Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putString("LotId", lot.getArea()+lot.getPosition());
                        bundle.putInt("TypeId", lot.getVehicleTypeId());
                        Navigation.findNavController(v).navigate(R.id.navigation_check_out, bundle);
                    }else{
                        Toast.makeText(holder.itemView.getContext(), "Not your vehicle", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return lotArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button lotIdButton;
        TextView lotStatus;
        TextView lotParkingVehicle;
        LinearLayout checklistItemsLayout;

        public ViewHolder(View view) {
            super(view);
            lotIdButton = (Button) view.findViewById(R.id.btn_lotId);
            lotStatus = (TextView) view.findViewById(R.id.tv_status);
            lotParkingVehicle = (TextView) view.findViewById(R.id.tv_parkingVehicle);
            checklistItemsLayout = (LinearLayout) view.findViewById(R.id.checklistItemsLayout);
        }
    }
}
