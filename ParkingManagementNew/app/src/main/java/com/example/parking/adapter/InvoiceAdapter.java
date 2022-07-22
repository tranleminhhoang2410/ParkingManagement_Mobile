package com.example.parking.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parking.R;
import com.example.parking.model.Invoice;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder>{

    ArrayList<Invoice> invoiceArrayList;

    public InvoiceAdapter(ArrayList<Invoice> invoiceArrayList) {
        this.invoiceArrayList = invoiceArrayList;
    }

    @NonNull
    @Override
    public InvoiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_invoice, parent, false);

        return new InvoiceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceAdapter.ViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("#,###");

        holder.slotId.setText(invoiceArrayList.get(position).getSlotId());
        holder.vehicleId.setText(invoiceArrayList.get(position).getVehicleId());
        holder.checkIn.setText(invoiceArrayList.get(position).getCheckinTime().split("T")[0]+" "+invoiceArrayList.get(position).getCheckinTime().split("T")[1]);
        holder.checkOut.setText(invoiceArrayList.get(position).getCheckoutTime().equals("0001-01-01T00:00:00")?
                "Vehicle is parking.":invoiceArrayList.get(position).getCheckoutTime().split("T")[0]+" "+invoiceArrayList.get(position).getCheckoutTime().split("T")[1]);
        holder.fee.setText(formatter.format(invoiceArrayList.get(position).getTotalPaid())+" VND");
    }

    @Override
    public int getItemCount() {
        return invoiceArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView slotId;
        TextView vehicleId;
        TextView checkIn;
        TextView checkOut;
        TextView fee;

        public ViewHolder(View view) {
            super(view);
            slotId = (TextView) view.findViewById(R.id.tv_slotId);
            vehicleId = (TextView) view.findViewById(R.id.tv_vehicleId);
            checkIn = (TextView) view.findViewById(R.id.tv_checkIn);
            checkOut = (TextView) view.findViewById(R.id.tv_checkOut);
            fee = (TextView) view.findViewById(R.id.tv_totalPaid);
        }
    }
}
