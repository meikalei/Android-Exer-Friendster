package io.acssuplb.github.friendster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import io.acssuplb.github.friendster.adapter.FriendListAdapter;

import static android.nfc.NfcAdapter.EXTRA_ID;

public class ViewFriends extends AppCompatActivity implements FriendListAdapter.ItemClickCallback {
    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_NAME = "EXTRA_NAME";
    private static final String EXTRA_ID = "EXTRA_ID";
    private RecyclerView recView;
    private FriendListAdapter flAdapter;
    private DBHandler db;
    private ArrayList friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_friends);

        recView = (RecyclerView)findViewById(R.id.f_list);
        recView.setLayoutManager(new LinearLayoutManager(this));

        db = new DBHandler(this);
        friends = (ArrayList)db.getAllFriends();

        flAdapter = new FriendListAdapter(db.getAllFriends(), this);
        recView.setAdapter(flAdapter);
        flAdapter.setItemClickCallback(this);
    }


    @Override
    public void onItemClick(int p) {
        Friend item = (Friend)friends.get(p);

        Intent in = new Intent(ViewFriends.this, ViewProfile.class);

        Bundle extras = new Bundle();
        extras.putString(EXTRA_NAME, item.getName());
        extras.putInt(EXTRA_ID, item.getId());
        in.putExtra(BUNDLE_EXTRAS, extras);

        startActivity(in);
    }
}
