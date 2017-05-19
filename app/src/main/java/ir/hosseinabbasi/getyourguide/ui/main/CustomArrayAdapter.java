package ir.hosseinabbasi.getyourguide.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ir.hosseinabbasi.getyourguide.R;
import ir.hosseinabbasi.getyourguide.data.db.model.Review;
import ir.hosseinabbasi.getyourguide.data.db.model.Review.data;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

public class CustomArrayAdapter extends ArrayAdapter {

    List<Review.dataz> reviewsList;

    public CustomArrayAdapter(Context context, List<Review.dataz> list) {
        super(context, 0, list);
        reviewsList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final View result;

        if (convertView == null) {
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            convertView = mInflater.inflate(R.layout.row, parent, false);

            holder = new ViewHolder();
            holder.rating = (TextView) convertView.findViewById(R.id.rating);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.message = (TextView) convertView.findViewById(R.id.message);

            result=convertView;

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Review.dataz rv = (Review.dataz) reviewsList.get(position);
        holder.rating.setText(rv.getRating());
        holder.title.setText(rv.getTitle());
        holder.message.setText(rv.getMessage());

        return result;
    }

    static class ViewHolder {
        TextView rating;
        TextView title;
        TextView message;
    }
}
