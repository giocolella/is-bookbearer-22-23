package com.example.bookbearer.reviews;

import android.content.Context;
import android.widget.ArrayAdapter;

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
import com.example.bookbearer.beans.Review;

import java.util.ArrayList;

public class ReviewListAdapter extends ArrayAdapter<Review> {

    public ReviewListAdapter(Context context, ArrayList<Review> reviewArrayList){
        super(context, R.layout.my_review_list_item,reviewArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Review review = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.my_review_list_item,parent,false);
        }

        TextView rScore = convertView.findViewById(R.id.reviewScore);
        TextView rAuthor = convertView.findViewById(R.id.reviewAuthor);
        TextView rDesc = convertView.findViewById(R.id.reviewDescription);

        rScore.append(review.getPunteggio());
        rAuthor.append(review.getAutore());
        rDesc.append(review.getDescrizione());
        if(convertView == null){
            Log.d("zureview","null");
        }
        return convertView;
    }
}
