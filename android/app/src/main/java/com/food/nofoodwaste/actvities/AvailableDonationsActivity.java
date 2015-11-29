package com.food.nofoodwaste.actvities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.food.nofoodwaste.R;
import com.food.nofoodwaste.adapters.DonationsListAdapter;
import com.food.nofoodwaste.utils.FoodObject;
import com.food.nofoodwaste.utils.OnItemClickListener;

import java.util.ArrayList;

public class AvailableDonationsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DonationsListAdapter donationsListAdapter;
    private ArrayList<FoodObject> foodObjects;
    private OnItemClickListener onItemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_donations);
        //initView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        ab.setDisplayHomeAsUpEnabled(true);

        onItemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(),"clicked: "+position,Toast.LENGTH_SHORT).show();
            }
        };

        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        LinearLayoutManager llm = new LinearLayoutManager(AvailableDonationsActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        donationsListAdapter = new DonationsListAdapter(getApplicationContext(),foodObjects);
        recyclerView.setAdapter(donationsListAdapter);
        donationsListAdapter.setOnItemClickListener(onItemClickListener);

        foodObjects = new ArrayList<>();



    }
}
