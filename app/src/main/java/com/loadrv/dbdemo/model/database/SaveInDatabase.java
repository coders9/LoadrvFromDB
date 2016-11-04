package com.loadrv.dbdemo.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.loadrv.dbdemo.model.helper.Constants;
import com.loadrv.dbdemo.model.helper.Utils;
import com.loadrv.dbdemo.pojo.ChannelList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mc11 on 3/11/16.
 */

public class SaveInDatabase extends SQLiteOpenHelper {
    private static final String TAG = SaveInDatabase.class.getSimpleName();

    public SaveInDatabase(Context context) {
       // super(context, Constants.DATABASE.DB_NAME, null, Constants.DATABASE.DB_VERSION);
        super(context, Environment.getExternalStorageDirectory()+ File.separator+Constants.DATABASE.DB_NAME, null, Constants.DATABASE.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(Constants.DATABASE.CREATE_TABLE_QUERY);
        } catch (SQLException ex) {
            Log.d(TAG, ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversio, int newversion) {
        db.execSQL(Constants.DATABASE.DROP_QUERY);
        this.onCreate(db);
    }

    public void addFlower(ChannelList vchannel) {

        Log.d(TAG, "Values Got " + vchannel.getClass().getSimpleName());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // values.put(Constants.DATABASE.PRODUCT_ID, vchannel.getProductId());
        values.put(Constants.DATABASE.TITLE, vchannel.getTitle());
        values.put(Constants.DATABASE.DESCRIPTION, vchannel.getDescription());
        values.put(Constants.DATABASE.PUBLIHED_AT, vchannel.getDatetime());
        values.put(Constants.DATABASE.THUMBNAIL_URL,Utils.getPictureByteOfArray(Utils.StringToBitMap(vchannel.getThumbnailurl())));

        try {
            db.insert(Constants.DATABASE.TABLE_NAME, null, values);
        } catch (Exception e) {

        }
        db.close();
    }


    public List<ChannelList> getallData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Constants.DATABASE.GET_VCHANNEL_QUERY, null);

        final List<ChannelList> vcList = new ArrayList<>();

        if (cursor.getCount() > 0) {

            if (cursor.moveToFirst()) {
                do {
                    ChannelList vchannel = new ChannelList();
                    // vchannel.setFromDatabase(true);
                    vchannel.setTitle(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.TITLE)));
                    vchannel.setDescription(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.DESCRIPTION)));
                    vchannel.setDatetime(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.PUBLIHED_AT)));
                    vchannel.setThumbnailurl(Utils.BitMapToString(Utils.getBitmapFromByte(cursor.getBlob(cursor.getColumnIndex(Constants.DATABASE.THUMBNAIL_URL)))));


                    vcList.add(vchannel);
                    // publishFlower(flower);

                } while (cursor.moveToNext());
            }

        }
        return vcList;
    }

    }
