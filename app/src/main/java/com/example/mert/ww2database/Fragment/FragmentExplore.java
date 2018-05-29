package com.example.mert.ww2database.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mert.ww2database.Activity.ExploreActivity;
import com.example.mert.ww2database.Model.Explore;
import com.example.mert.ww2database.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FragmentExplore extends Fragment {

    ListView listView;
    FirebaseListAdapter adapter;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_explore,container,false);

        listView=view.findViewById(R.id.listview_explore);
        Query query=FirebaseDatabase.getInstance().getReference().child("explore");
        FirebaseListOptions<Explore> options = new FirebaseListOptions.Builder<Explore>().setLayout(R.layout.listview_explore).setLifecycleOwner(getActivity()).setQuery(query,Explore.class).build();

        adapter=new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView tvDate=v.findViewById(R.id.tvDate);
                TextView tvMainArticle=v.findViewById(R.id.tvMainArticle);
                ImageView ivImg=v.findViewById(R.id.ivImg);
                TextView tvDesc=v.findViewById(R.id.tvDesc);



                final Explore explore= (Explore) model;
                tvDate.setText("Added : "+explore.getContentDate().toString());
                tvMainArticle.setText(explore.getContentName().toString());
                Glide.with(getContext()).load(explore.getContentImg().toString()).into(ivImg);
                tvDesc.setText(((Explore) model).getContentDesc().toString());

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getContext(), ExploreActivity.class);
                        intent.putExtra("explore",explore);
                        startActivity(intent);
                    }
                });
            }
        };
        listView.setAdapter(adapter);



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
