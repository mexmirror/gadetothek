package ch.hsr.mge.gadgeothek;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ch.hsr.mge.gadgeothek.domain.Reservation;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {
    private List<Reservation> reservationList;
    public ReservationAdapter(List<Reservation> list){
        this.reservationList = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rowlayout, parent, false);
        TextView vendor = (TextView)view.findViewById(R.id.row_vendor);
        TextView available = (TextView)view.findViewById(R.id.row_available);
        TextView product = (TextView)view.findViewById(R.id.row_product);
        TextView price = (TextView)view.findViewById(R.id.row_price);
        ViewHolder viewHolder = new ViewHolder(view, vendor, product, available, price);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Reservation reservation = reservationList.get(position);
        holder.vendor.setText(reservation.getGadget().getManufacturer());
        holder.product.setText(reservation.getGadget().getName());
        String formattedDate = new SimpleDateFormat("dd.MM.yy").format(reservation.getReservationDate());
        holder.available.setText("Available: " + formattedDate);
        holder.price.setText(String.valueOf(reservation.getGadget().getPrice()) + " CHF");
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public void add(int position, Reservation res){
        reservationList.add(position, res);
        notifyItemInserted(position);
    }
    public void remove(int position, Reservation res){
        reservationList.remove(res);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView vendor;
        public TextView product;
        public TextView available;
        public TextView price;

        public ViewHolder(View itemView, TextView vendor, TextView product, TextView available, TextView price) {
            super(itemView);
            this.vendor = vendor;
            this.product = product;
            this.available = available;
            this.price = price;

        }
    }
}
