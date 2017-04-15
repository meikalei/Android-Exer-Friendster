package io.acssuplb.github.friendster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "friendster";
    private static final String TABLE_FRIENDS = "friends";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private Context mContext;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_FRIENDS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIENDS);
        onCreate(db);
    }

    public void addFriend(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, friend.getName());
        db.insert(TABLE_FRIENDS, null, values);
        db.close();
    }

    public void editFriend(String oldName,String newName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_FRIENDS +
                " SET " + KEY_NAME + "=?, " +
                " WHERE " + KEY_NAME + "=?";
        String[] args = {newName, oldName};

        db.execSQL(query, args);
        db.close();
    }

    public void removeFriend(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_FRIENDS +
                " WHERE " + KEY_NAME + "=?";
        String[] args = {name};

        db.execSQL(query, args);
        db.close();
    }

    public List<Friend> getAllFriends() {
        List<Friend> friendList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_FRIENDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Friend friend = new Friend();
                friend.setId(Integer.parseInt(cursor.getString(0)));
                friend.setName(cursor.getString(1));
                friendList.add(friend);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return friendList;
    }
}

