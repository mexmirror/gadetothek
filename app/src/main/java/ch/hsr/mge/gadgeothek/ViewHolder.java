package ch.hsr.mge.gadgeothek;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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
