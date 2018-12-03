package com.moapps.appviewer.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moapps.appviewer.R;
import com.moapps.appviewer.API.pojo.AppList;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder>{

	private List<AppList.AppData> items;
	private Context context;

	class ViewHolder extends RecyclerView.ViewHolder {

		public TextView tittle, textView_app_status, textView_app_payment;
		public ImageView image, imageView_app_status, imageView_app_payment;

		public ViewHolder(View itemView) {
			super(itemView);
			tittle = itemView.findViewById(R.id.textView_tittle);
            textView_app_status = itemView.findViewById(R.id.textView_app_status);
            textView_app_payment = itemView.findViewById(R.id.textView_app_payment);
			image = itemView.findViewById(R.id.imageView_image);
            imageView_app_status = itemView.findViewById(R.id.imageView_app_status);
            imageView_app_payment = itemView.findViewById(R.id.imageView_app_payment);
		}
	}

	public AppsAdapter(Context context, List<AppList.AppData> items) {
		this.context = context;
		this.items = items;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.apps_list_row, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		AppList.AppData item = items.get(position);
		holder.tittle.setText(item.getApplicationName());
		if (item.getApplicationStatus() == false) {
            holder.textView_app_status.setText(R.string.app_status_not_finished);
            holder.imageView_app_status.setImageResource(R.drawable.icon_nezakoncheno);
        } else {
            holder.textView_app_status.setText(R.string.app_status_finished);
            holder.imageView_app_status.setImageResource(R.drawable.icon_zakoncheno);
        }
        if (item.getPayment() == false) {
            holder.textView_app_payment.setText(R.string.app_payment_not_payed);
            holder.imageView_app_payment.setImageResource(R.drawable.icon_oplacheno_ili_net);
        } else {
		    if (item.getEndOfPayment() != null) {
                holder.textView_app_payment.setText(R.string.app_payment_payed+ " до " + item.getEndOfPayment());
            } else {
                holder.textView_app_payment.setText(R.string.app_payment_payed);
            }
            holder.imageView_app_payment.setImageResource(R.drawable.icon_oplacheno_ili_net);
        }

		if ( !(item.getApplicationIcoUrl().isEmpty() || item.getApplicationIcoUrl().length() == 0) ) {
			Picasso.get()
					.load(item.getApplicationIcoUrl())
					.fit()
					.centerCrop()
					.placeholder(R.drawable.img_loading)
					.error(R.drawable.ic_error)
					.into(holder.image);
		}
	}

	@Override
	public int getItemCount() {
		return items.size();
	}
	public AppList.AppData getItem(int position) {
		return items.get(position);
	}

}