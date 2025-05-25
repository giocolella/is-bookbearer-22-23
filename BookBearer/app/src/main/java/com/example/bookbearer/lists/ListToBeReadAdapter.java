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
import com.example.bookbearer.beans.ListToBeRead;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListToBeReadAdapter extends ArrayAdapter<ListToBeRead> {

    private ArrayList<ListToBeRead> lToBeRead1 = new ArrayList<>();
    private ListsModel lMod = new ListsModel();

    public ListToBeReadAdapter(Context context, ArrayList<ListToBeRead> lToBeRead){
        super(context, R.layout.my_toberead_list_item,lToBeRead);
        lToBeRead1 = lToBeRead;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ListToBeRead lToBeRead = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.my_toberead_list_item,parent,false);
        }

        ImageView lImage = convertView.findViewById(R.id.tobereadlistImage);
        TextView lTitle = convertView.findViewById(R.id.tobereadlistTitle);
        TextView lAuthor = convertView.findViewById(R.id.tobereadlistAuthor);
        Button addRead = convertView.findViewById(R.id.addToRead);
        Button deleteToBeRead = convertView.findViewById(R.id.deleteToBeRead);
        addRead.setTag(position);
        deleteToBeRead.setTag(position);

        addRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer index = (Integer) view.getTag();
                Map<String,Object> m = new HashMap<>();
                m.put("ISBN",lToBeRead1.get(index).getISBN());
                m.put("autore",lToBeRead1.get(index).getAuthor());
                m.put("bookImg",lToBeRead1.get(index).getBookImg());
                m.put("titolo",lToBeRead1.get(index).getTitle());
                lMod.addRead(m);
                int i = index;
                lToBeRead1.remove(i);
                notifyDataSetChanged();
            }
        });

        deleteToBeRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer index = (Integer) view.getTag();
                Map<String,Object> m = new HashMap<>();
                m.put("ISBN",lToBeRead1.get(index).getISBN());
                m.put("autore",lToBeRead1.get(index).getAuthor());
                m.put("bookImg",lToBeRead1.get(index).getBookImg());
                m.put("titolo",lToBeRead1.get(index).getTitle());
                lMod.deleteToBeRead(m);
                int i = index;
                lToBeRead1.remove(i);
                notifyDataSetChanged();
            }
        });

        Glide.with(getContext()).load(lToBeRead.getBookImg()).centerCrop().into(lImage);
        lTitle.setText(lToBeRead.getTitle());
        lAuthor.setText(lToBeRead.getAuthor());
        if(convertView == null){
            Log.d("zusalt","null");
        }
        return convertView;
    }

}
