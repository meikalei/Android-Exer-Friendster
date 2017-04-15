package io.acssuplb.github.friendster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button add_friend_btn = (Button) findViewById(R.id.add_friend_btn);
        add_friend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler db = new DBHandler(MainActivity.this);
                EditText add_name = (EditText)findViewById(R.id.add_name);
                Friend friend = new Friend(0, add_name.getText().toString());
                db.addFriend(friend);
                Toast.makeText(getApplicationContext(),
                        "Successfully added " + add_name.getText().toString(), Toast.LENGTH_LONG).show();

            }
        });

        Button view_friends_btn = (Button) findViewById(R.id.view_friends_btn);
        view_friends_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DBHandler db = new DBHandler(MainActivity.this);
//                List<Friend> friendList = db.getAllFriends();
//
//                for (Friend friend : friendList) {
//                    String log = "Id: " + friend.getId() + " ,Name: " + friend.getName();
//                    Toast.makeText(getApplicationContext(), log, Toast.LENGTH_LONG).show();
//                }
                Intent i = new Intent(MainActivity.this,ViewFriends.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
