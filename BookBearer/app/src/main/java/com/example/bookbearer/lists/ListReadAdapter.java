package com.example.bookbearer.lists;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.bookbearer.R;
import com.example.bookbearer.beans.ListRead;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListReadAdapter extends ArrayAdapter<ListRead> {

    private ArrayList<ListRead> lRead1 = new ArrayList<>();
    private ListsModel lMod = new ListsModel();
    public ListReadAdapter(Context context, ArrayList<ListRead> lRead){
        super(context, R.layout.my_read_list_item,lRead);
        lRead1 = lRead;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ListRead lRead = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.my_read_list_item,parent,false);
        }

        ImageView lImage = convertView.findViewById(R.id.readlistImage);
        TextView lTitle = convertView.findViewById(R.id.readlistTitle);
        TextView lAuthor = convertView.findViewById(R.id.readlistAuthor);
        Button deleteread = convertView.findViewById(R.id.deleteRead);
        deleteread.setTag(position);

        deleteread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer index = (Integer) view.getTag();
                Map<String,Object> m = new HashMap<>();
                m.put("ISBN",lRead1.get(index).getISBN());
                m.put("autore",lRead1.get(index).getAuthor());
                m.put("bookImg",lRead1.get(index).getBookImg());
                m.put("titolo",lRead1.get(index).getTitle());
                lMod.deleteRead(m);
                int i = index;
                lRead1.remove(i);
                notifyDataSetChanged();
            }
        });

        Glide.with(getContext()).load(lRead.getBookImg()).centerCrop().into(lImage);
        lTitle.setText(lRead.getTitle());
        lAuthor.setText(lRead.getAuthor());
        Log.d("autore",lRead.getAuthor());
        if(convertView == null){
            Log.d("zusalt","null");
        }
        return convertView;
    }

}
