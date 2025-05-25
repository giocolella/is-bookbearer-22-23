package com.example.bookbearer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookbearer.beans.LightBook;
import com.example.bookbearer.beans.ListRead;
import com.example.bookbearer.beans.ListToBeRead;
import com.example.bookbearer.lists.ListReadAdapter;
import com.example.bookbearer.lists.ListToBeReadAdapter;
import com.example.bookbearer.lists.ListsPres;
import com.example.bookbearer.lists.ListsPresenter;
import com.example.bookbearer.lists.ListsView;
import com.example.bookbearer.search.ListAdapter;
import com.example.bookbearer.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DashboardFragment extends Fragment implements ListsView {

    private Button listaLettiButton;
    private Button listaDaLeggereButton;
    private ListView listview;
    private ListsPres presenter;
    private ListReadAdapter listReadAdapter;
    private ListToBeReadAdapter listToBeReadAdapter;

    public DashboardFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        listaLettiButton = view.findViewById(R.id.lettiHomeButton);
        listaDaLeggereButton = view.findViewById(R.id.daleggereHomeButton);
        listview = view.findViewById(R.id.listaHome);

        presenter = new ListsPresenter(this);

        listaLettiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.readSelected();
            }
        });

        listaDaLeggereButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.toBeReadSelected();
            }
        });

        return view;
    }

    @Override
    public void listsMessage(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void userReadResults(ArrayList<ListRead> l) {
        listReadAdapter = new ListReadAdapter(getContext(),l);

        listview.setAdapter(listReadAdapter);

        listview.setClickable(false);
        //listReadAdapter.notifyDataSetChanged();
    }

    @Override
    public void userToBeReadResults(ArrayList<ListToBeRead> l) {
        listToBeReadAdapter = new ListToBeReadAdapter(getContext(),l);

        listview.setAdapter(listToBeReadAdapter);

        listview.setClickable(false);
        //listToBeReadAdapter.notifyDataSetChanged();
    }

    public ListsPres getPresenter(){
        return presenter;
    }



}