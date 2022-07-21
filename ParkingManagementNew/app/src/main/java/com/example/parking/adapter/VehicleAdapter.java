package com.example.parking.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parking.R;
import com.example.parking.model.Vehicle;

import java.util.ArrayList;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> {

    private ArrayList<Vehicle> vehicleArrayList;

    public VehicleAdapter(ArrayList<Vehicle> vehicleArrayList) {
        this.vehicleArrayList = vehicleArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_vehicle, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_vehicleId.setText(vehicleArrayList.get(position).getId());
        holder.tv_status.setText(vehicleArrayList.get(position).getParking()==true?"Parking":"Available");
        int typeId = vehicleArrayList.get(position).getVehicleType().getId();
        switch (typeId){
            case 1:
                holder.iv_type.setImageResource(R.drawable.ic_car);
                break;
            case 2:
                holder.iv_type.setImageResource(R.drawable.ic_bus);
                break;
            case 3:
                holder.iv_type.setImageResource(R.drawable.ic_truck);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return vehicleArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_vehicleId;
        public TextView tv_status;
        public ImageView iv_type;

        public ViewHolder(View view) {
            super(view);
            tv_vehicleId = (TextView) view.findViewById(R.id.tv_vehicleId);
            tv_status = (TextView) view.findViewById(R.id.tv_status);
            iv_type = (ImageView) view.findViewById(R.id.iv_vehicleType);
        }
    }
}
