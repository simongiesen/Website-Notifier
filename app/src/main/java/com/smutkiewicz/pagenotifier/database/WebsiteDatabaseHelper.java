package com.smutkiewicz.pagenotifier.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WebsiteDatabaseHelper extends SQLiteOpenHelper {

    public WebsiteDatabaseHelper(Context context) {
        super(context, DbDescription.DB_NAME, null, DbDescription.DB_VERSION);
    }

    // tworzy tabelę stron po utworzeniu bazy danych
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String query = makeCreateTableSQLQuery();
        db.execSQL(query);
    }

    private String makeCreateTableSQLQuery() {
        // kod SQL tworzący tabelę kontaktów
        final String createTable =
                "CREATE TABLE " + DbDescription.DB_TABLE_NAME + "(" +
                        DbDescription.KEY_ID + " " + DbDescription.ID_OPTIONS + ", " +
                        DbDescription.KEY_NAME + " " + DbDescription.NAME_OPTIONS + ", " +
                        DbDescription.KEY_URL + " " + DbDescription.URL_OPTIONS + ", " +
                        DbDescription.KEY_ALERTS + " " + DbDescription.ALERTS_OPTIONS + ", " +
                        DbDescription.KEY_DELAY + " " + DbDescription.DELAY_OPTIONS +
                ");";
        return createTable;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) { }
}
