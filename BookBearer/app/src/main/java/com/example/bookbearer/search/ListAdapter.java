package com.example.bookbearer.search;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.bookbearer.R;
import com.example.bookbearer.beans.LightBook;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<LightBook> {

    public ListAdapter(Context context, ArrayList<LightBook> lightBookArrayList){
        super(context, R.layout.my_list_item,lightBookArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LightBook lBook = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.my_list_item,parent,false);
        }

        ImageView lImage = convertView.findViewById(R.id.listImage);
        TextView lTitle = convertView.findViewById(R.id.listTitle);
        TextView lAuthor = convertView.findViewById(R.id.listAuthor);

        Glide.with(getContext()).load(lBook.getImageUrl()).centerCrop().into(lImage);
        lTitle.setText(lBook.getTitle());
        lAuthor.setText(lBook.getAuthor());
        if(convertView == null){
            Log.d("zusalt","null");
        }
        return convertView;
    }
}
