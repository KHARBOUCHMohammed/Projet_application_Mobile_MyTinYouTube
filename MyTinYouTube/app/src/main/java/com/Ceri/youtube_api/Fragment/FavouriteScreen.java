package com.Ceri.youtube_api.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Ceri.youtube_api.Database.FavouriteList;
import com.Ceri.youtube_api.R;
import com.Ceri.youtube_api.adapter.AdapterFavourite;

import java.util.ArrayList;
import java.util.List;

//This fragment shows all the favourite list videos
public class FavouriteScreen extends Fragment {
    //Creating list to storing different type of data for video
    private List<String> videoYTList = new ArrayList<String>();
    private List<String> thumblist = new ArrayList<String>();
    private List<String> videoid = new ArrayList<String>();
    private List<String> title = new ArrayList<String>();
    private List<String> des = new ArrayList<String>();
    private List<String> dop = new ArrayList<String>();
    FavouriteList db;
    private AdapterFavourite adapter;
    private LinearLayoutManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favourite_screen_fragement, null);


//Setting up recyclerview to show all the favourite videos
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        adapter = new AdapterFavourite(getContext(), videoYTList, thumblist, videoid, title, des, dop);
        manager = new LinearLayoutManager(getContext());
        manager.setReverseLayout(true);
        manager.setStackFromEnd(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        //Fetching all data from database
        db = new FavouriteList(getContext());
        Cursor data = db.getdataurl();
        Cursor data1 = db.getdatathumb();
        Cursor data2 = db.getvideoid();
        Cursor data3 = db.title();
        Cursor data4 = db.discription();
        Cursor data5 = db.dop();

        if (data.getCount() == 0 || data1.getCount() == 0) {
            Toast.makeText(getContext(), "No Favourites", Toast.LENGTH_SHORT).show();

        }

        while (data.moveToNext()) {
            videoYTList.add(data.getString(0));

        }
        while (data1.moveToNext()) {
            thumblist.add(data1.getString(0));

        }
        while (data2.moveToNext()) {
            videoid.add(data2.getString(0));
            Log.d("Tag", data2.getString(0));

        }
        while (data3.moveToNext()) {
            title.add(data3.getString(0));
            Log.d("Tag", data3.getString(0));

        }
        while (data4.moveToNext()) {
            des.add(data4.getString(0));
            Log.d("Tag", data4.getString(0));

        }
        while (data5.moveToNext()) {
            dop.add(data5.getString(0));
            Log.d("Tag", data5.getString(0));

        }


        return view;
    }


}
