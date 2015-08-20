package com.example.mahmoud.asynctask_and_network;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by mahmoud on 20/08/2015.
 */
public class homeFragment extends Fragment {
    MyListener myListener;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View V = inflater.inflate(R.layout.home_layout, container, false);
        listView = (ListView) V.findViewById(R.id.listView);
        return V;
    }

    public void FillArrayList(ArrayList<String> myArray) {
        ArrayAdapter adapter = new ArrayAdapter(this.getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, myArray);
        listView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myListener = (MyListener) activity;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myListener.FillData();
    }

    public interface MyListener {
        public void FillData();
    }
}
