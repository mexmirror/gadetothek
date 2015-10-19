package ch.hsr.mge.gadgeothek;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ch.hsr.mge.gadgeothek.domain.Reservation;

public class ReservationAdapter extends RecyclerView.Adapter<ViewHolder> {
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
        return new ViewHolder(view, vendor, product, available, price);
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

    public Reservation remove(int position){
        Reservation reservation = reservationList.get(position);
        reservationList.remove(position);
        return reservation;
    }

}
