package io.acssuplb.github.friendster;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewProfile extends AppCompatActivity {
    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_NAME = "EXTRA_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        final Button edit_friend = (Button) this.findViewById(R.id.btn_edit);
        final Button remove_friend = (Button) this.findViewById(R.id.btn_remove);
        final EditText name_field = (EditText) this.findViewById(R.id.lbl_name);
        final Bundle extras = getIntent().getBundleExtra(BUNDLE_EXTRAS);

        ((EditText)findViewById(R.id.lbl_name)).setText(extras.getString(EXTRA_NAME));

        remove_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ViewProfile.this)
                        .setTitle("Removing from Friends List")
                        .setMessage("Are you sure you want to remove " + extras.getString(EXTRA_NAME) + "?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DBHandler db = new DBHandler(ViewProfile.this);
                                Intent intent = new Intent(ViewProfile.this, ViewFriends.class);
                                db.removeFriend(extras.getString(EXTRA_NAME));
                                String prompt = "Removed from list successfully!";
                                Toast.makeText(ViewProfile.this, prompt, Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        }).show();
            }
        });

        edit_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler db = new DBHandler(ViewProfile.this);
                Intent intent = new Intent(ViewProfile.this, ViewProfile.class);

                String newName = name_field.getText().toString();

                db.editFriend(extras.getString(EXTRA_NAME), newName);
                String prompt = "Updated contact successfully!";
                Toast.makeText(ViewProfile.this, prompt, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}
