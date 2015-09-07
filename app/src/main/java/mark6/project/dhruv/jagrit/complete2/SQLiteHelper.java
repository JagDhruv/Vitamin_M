package mark6.project.dhruv.jagrit.complete2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SONY on 30-07-2015.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ussuary.db";
    public static final int DATABSE_VERSION = 1;

    public static final String TABLE_DAILY="daily";
    public static final String TABLE_WEEKLY = "weEkly";
    public static final String TABLE_MONTHLY = "monthly";

    public static final String COLUMN_DAILY_ID="_id";
    public static final String COLUMN_WEEKLY_ID="_id";
    public static final String COLUMN_MONTHLY_ID="_id";

    public static final String COLUMN_DAILY_GROCERIES = "dgroceries";
    public static final String COLUMN_DAILY_BILLS="dbills";
    public static final String COLUMN_DAILY_ENTERTAINMENT = "dentertainemnt";
    public static final String COLUMN_DAILY_CLOTHING = "dclothing";
    public static final String COLUMN_DAILY_TRAVEL = "dtravel";
    public static final String COLUMN_DAILY_ELECTRONICS = "delectronics";
    public static final String COLUMN_DAILY_OTHERS = "dothers";

    public static final String COLUMN_WEEKLY_GROCERIES = "wgroceries";
    public static final String COLUMN_WEEKLY_BILLS="wbills";
    public static final String COLUMN_WEEKLY_ENTERTAINMENT="wentertainment";
    public static final String COLUMN_WEEKLY_CLOTHING="wclothing";
    public static final String COLUMN_WEEKLY_TRAVEL = "wtravel";
    public static final String COLUMN_WEEKLY_ELECTRONICS="welectronics";
    public static final String COLUMN_WEEKLY_OTHERS="others";

    public static final String COLUMN_MONTHLY_GROCERIES="mgroceries";
    public static final String COLUMN_MONTHLY_BILLS="mbills";
    public static final String COLUMN_MONTHLY_ENTERTAINMENT="mentertainemnt";
    public static final String COLUMN_MONTHLY_CLOTHING="mclothing";
    public static final String COLUMN_MONTHLY_TRAVEL="mtravel";
    public static final String COLUMN_MONTHLY_ELECTRONICS="melectronics";
    public static final String COLUMN_MONTHLY_OTHERS="mothers";

    public static final String TABLE_CREATE_DAILY="create table "+TABLE_DAILY+
            "( "+COLUMN_DAILY_ID+" integer primary key autoincrement"+
            ", "+COLUMN_DAILY_GROCERIES+ " integer"+
            ", "+COLUMN_DAILY_BILLS+ " integer"+
            ", "+COLUMN_DAILY_ENTERTAINMENT+ " integer"+
            ", "+COLUMN_DAILY_CLOTHING+ " integer"+
            ", "+COLUMN_DAILY_TRAVEL+ " integer"+
            ", "+COLUMN_DAILY_ELECTRONICS+ " integer"+
            ", "+COLUMN_DAILY_OTHERS+ " integer);";
    public static final String TABLE_CREATE__WEEKLY="create table "+TABLE_WEEKLY+
            "( "+COLUMN_WEEKLY_ID+" integer primary key autoincrement"+
            ", "+COLUMN_WEEKLY_GROCERIES+ " integer"+
            ", "+COLUMN_WEEKLY_BILLS+ " integer"+
            ", "+COLUMN_WEEKLY_ENTERTAINMENT+ " integer"+
            ", "+COLUMN_WEEKLY_CLOTHING+ " integer"+
            ", "+COLUMN_WEEKLY_TRAVEL+ " integer"+
            ", "+COLUMN_WEEKLY_ELECTRONICS+ " integer"+
            ", "+COLUMN_WEEKLY_OTHERS+ " integer);";;
    public static final String TABLE_CREATE__MONTHLY="create table "+TABLE_MONTHLY+
            "( "+COLUMN_MONTHLY_ID+" integer primary key autoincrement"+
            ", "+COLUMN_MONTHLY_GROCERIES+ " integer"+
            ", "+COLUMN_MONTHLY_BILLS+ " integer"+
            ", "+COLUMN_MONTHLY_ENTERTAINMENT+ " integer"+
            ", "+COLUMN_MONTHLY_CLOTHING+ " integer"+
            ", "+COLUMN_MONTHLY_TRAVEL+ " integer"+
            ", "+COLUMN_MONTHLY_ELECTRONICS+ " integer"+
            ", "+COLUMN_MONTHLY_OTHERS+ " integer);";;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_DAILY);
        db.execSQL(TABLE_CREATE__WEEKLY);
        db.execSQL(TABLE_CREATE__MONTHLY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}