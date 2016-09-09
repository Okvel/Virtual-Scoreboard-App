package by.liauko.minskscoreboard.database.contract;

import by.liauko.minskscoreboard.database.entry.Entry;
import by.liauko.minskscoreboard.database.entry.StopEntry;
import by.liauko.minskscoreboard.database.entry.TransportEntry;
import by.liauko.minskscoreboard.database.entry.TransportTypeEntry;

public class ScheduleDatabaseContract {
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_STOP_ENTRY = "CREATE TABLE " + StopEntry.TABLE_NAME + " (" +
            Entry._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT," + Entry.NAME_COLUMN_NAME +
            TEXT_TYPE + COMMA_SEP + Entry.LINK_COLUMN_NAME + TEXT_TYPE + ");";
    public static final String SQL_CREATE_TRANSPORT_TYPE_ENTRY = "CREATE TABLE " +
            TransportTypeEntry.TABLE_NAME + " (" + TransportTypeEntry._ID + INTEGER_TYPE +
            " PRIMARY KEY AUTOINCREMENT," + TransportTypeEntry.NAME_COLUMN_NAME + TEXT_TYPE + ");";
    public static final String SQL_CREATE_TRANSPORT_ENTRY = "CREATE TABLE " + TransportEntry.TABLE_NAME + " (" +
            Entry._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT," + Entry.NAME_COLUMN_NAME +
            TEXT_TYPE + COMMA_SEP + Entry.LINK_COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
            TransportEntry.TYPE_COLUMN_NAME + INTEGER_TYPE + ")";
    public static final String SQL_FILL_TRANSPORT_TYPE = "INSERT INTO " + TransportTypeEntry.TABLE_NAME +
            " VALUES (BUS, TROLLEYBUS, TRAM);";
    public static final String SQL_CREATE_DATABASE = SQL_CREATE_STOP_ENTRY +
            SQL_CREATE_TRANSPORT_ENTRY + SQL_CREATE_TRANSPORT_TYPE_ENTRY + SQL_FILL_TRANSPORT_TYPE;
    public final static String SQL_DELETE_DATABASE = "DELETE TABLE IF EXISTS " + StopEntry.TABLE_NAME +
            "; DELETE TABLE IF EXISTS " + TransportEntry.TABLE_NAME + "; DELETE TABLE IF EXISTS " +
            TransportTypeEntry.TABLE_NAME;
}
