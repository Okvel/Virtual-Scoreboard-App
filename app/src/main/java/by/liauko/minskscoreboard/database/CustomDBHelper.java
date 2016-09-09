package by.liauko.minskscoreboard.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collection;

import by.liauko.minskscoreboard.database.contract.ScheduleDatabaseContract;
import by.liauko.minskscoreboard.database.entry.Entry;
import by.liauko.minskscoreboard.database.entry.TransportEntry;
import by.liauko.minskscoreboard.entity.Favourite;

public class CustomDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ScoreboardDatabase.db";

    public CustomDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ScheduleDatabaseContract.SQL_CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(ScheduleDatabaseContract.SQL_DELETE_DATABASE);
        onCreate(sqLiteDatabase);
    }

    public long insert(String tableName, String name, String link) {
        ContentValues values = new ContentValues();
        values.put(Entry.NAME_COLUMN_NAME, name);
        values.put(Entry.LINK_COLUMN_NAME, link);

        return getWritableDatabase().insert(tableName, null, values);
    }

    public Collection<Favourite> selectAll(String tableName, TransportType type) {
        ArrayList<Favourite> favourites = new ArrayList<>();
        String selection = null;
        String[] selectionArgs = null;
        if (TransportEntry.TABLE_NAME.equals(tableName)) {
            selection = TransportEntry.TYPE_COLUMN_NAME + "=?";
            selectionArgs = new String[] {type.toString()};
        }
        Cursor cursor = getReadableDatabase().query(tableName, null, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                favourites.add(fillFavourite(cursor));
            } while (cursor.moveToNext());
        }

        cursor.close();

        return favourites;
    }

    public boolean delete(String tableName, Collection<Long> idList) {
        boolean result = false;
        int queryResult = 0;
        SQLiteDatabase database = getWritableDatabase();
        for (Long id : idList) {
            queryResult += database.delete(tableName,
                    Entry._ID + "=?", new String[]{String.valueOf(id)});
        }
        if (queryResult != 0) {
            result = true;
        }

        return result;
    }

    private Favourite fillFavourite(Cursor cursor) {
        Favourite entity = new Favourite();
        entity.setId(cursor.getLong(cursor.getColumnIndex(Entry._ID)));
        entity.setName(cursor.getString(cursor.getColumnIndex(Entry.NAME_COLUMN_NAME)));
        entity.setLink(cursor.getString(cursor.getColumnIndex(Entry.LINK_COLUMN_NAME)));

        return entity;
    }
}
