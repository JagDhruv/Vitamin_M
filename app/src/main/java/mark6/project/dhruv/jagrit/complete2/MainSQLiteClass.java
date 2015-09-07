package mark6.project.dhruv.jagrit.complete2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

/**
 * Created by SONY on 30-07-2015.
 */
public class MainSQLiteClass {

    SQLiteDatabase database;
    SQLiteHelper helper;

    MainSQLiteClass(Context context) {
        helper = new SQLiteHelper(context);
    }

    public void open() throws SQLiteException {
        database = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }





    //Insert into groceries
    public long insertGroceries(int groceries) {
        ContentValues cv = new ContentValues();
        /*Cursor cursor = database.query(helper.TABLE_DAILY, new String[]{helper.COLUMN_DAILY_ID, helper.COLUMN_DAILY_GROCERIES, helper.COLUMN_DAILY_BILLS,
                helper.COLUMN_DAILY_ENTERTAINMENT, helper.COLUMN_DAILY_CLOTHING, helper.COLUMN_DAILY_TRAVEL, helper.COLUMN_DAILY_ELECTRONICS,
                helper.COLUMN_DAILY_OTHERS}, null, null, null, null, null);*/
        cv.put(helper.COLUMN_DAILY_GROCERIES, groceries);
        return database.insert(helper.TABLE_DAILY, null, cv);
    }

    //Insert into bills
    public long insertBills(int bills) {
        ContentValues cv = new ContentValues();
        cv.put(helper.COLUMN_DAILY_BILLS,bills);
        return database.insert(helper.TABLE_DAILY, null, cv);
    }

    //Insert into clothes
    public long insertClothing(int clothing) {
        ContentValues cv = new ContentValues();
        cv.put(helper.COLUMN_DAILY_CLOTHING,clothing);
        return database.insert(helper.TABLE_DAILY, null, cv);
    }

    //Insert into others
    public long insertOthers(int others) {
        ContentValues cv = new ContentValues();
        cv.put(helper.COLUMN_DAILY_OTHERS,others);
        return database.insert(helper.TABLE_DAILY, null, cv);
    }

    //Insert into Travel
    public long insertTravel(int travel) {
        ContentValues cv = new ContentValues();
        cv.put(helper.COLUMN_DAILY_TRAVEL,travel);
        return database.insert(helper.TABLE_DAILY, null, cv);
    }

    //Insert into Entertainment
    public long insertEntertainment(int entertainment) {
        ContentValues cv = new ContentValues();
        cv.put(helper.COLUMN_DAILY_ENTERTAINMENT,entertainment);
        return database.insert(helper.TABLE_DAILY, null, cv);
    }

    //Insert into Electronics
    public long insertElectonics(int electronics) {
        ContentValues cv = new ContentValues();
        cv.put(helper.COLUMN_DAILY_ELECTRONICS,electronics);
        return database.insert(helper.TABLE_DAILY, null, cv);
    }




    public Cursor getTodayShit() {
        return database.query(helper.TABLE_DAILY, new String[]{helper.COLUMN_DAILY_ID, helper.COLUMN_DAILY_GROCERIES, helper.COLUMN_DAILY_BILLS,
                helper.COLUMN_DAILY_ENTERTAINMENT, helper.COLUMN_DAILY_CLOTHING, helper.COLUMN_DAILY_TRAVEL, helper.COLUMN_DAILY_ELECTRONICS,
                helper.COLUMN_DAILY_OTHERS}, null, null, null, null, null);
    }

    public Cursor getWeeklyShit() {
        return database.query(helper.TABLE_WEEKLY, new String[]{helper.COLUMN_WEEKLY_ID, helper.COLUMN_WEEKLY_GROCERIES, helper.COLUMN_WEEKLY_BILLS,
                helper.COLUMN_WEEKLY_ENTERTAINMENT, helper.COLUMN_WEEKLY_CLOTHING, helper.COLUMN_WEEKLY_TRAVEL, helper.COLUMN_WEEKLY_ELECTRONICS,
                helper.COLUMN_WEEKLY_OTHERS}, null, null, null, null, null);
    }

    public Cursor getMonthlyShit() {
        return database.query(helper.TABLE_MONTHLY, new String[]{helper.COLUMN_MONTHLY_ID, helper.COLUMN_MONTHLY_GROCERIES, helper.COLUMN_MONTHLY_BILLS,
                helper.COLUMN_MONTHLY_ENTERTAINMENT, helper.COLUMN_MONTHLY_CLOTHING, helper.COLUMN_MONTHLY_TRAVEL, helper.COLUMN_MONTHLY_ELECTRONICS,
                helper.COLUMN_MONTHLY_OTHERS}, null, null, null, null, null);
    }





    //•    That will take the weekly table and put into monthly table
    public long insertIntoMonthly() {
        ContentValues cv = new ContentValues();

        Cursor cursor = database.query(helper.TABLE_WEEKLY, new String[]{helper.COLUMN_WEEKLY_ID, helper.COLUMN_WEEKLY_GROCERIES, helper.COLUMN_WEEKLY_BILLS,
                helper.COLUMN_WEEKLY_ENTERTAINMENT, helper.COLUMN_WEEKLY_CLOTHING, helper.COLUMN_WEEKLY_TRAVEL, helper.COLUMN_WEEKLY_ELECTRONICS,
                helper.COLUMN_WEEKLY_OTHERS}, null, null, null, null, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int groceries = cursor.getInt(cursor.getColumnIndex(helper.COLUMN_WEEKLY_GROCERIES));
            cv.put(helper.COLUMN_MONTHLY_GROCERIES, groceries);
            int bills = cursor.getInt(cursor.getColumnIndex(helper.COLUMN_WEEKLY_BILLS));
            cv.put(helper.COLUMN_MONTHLY_BILLS, bills);
            int entertainment = cursor.getInt(cursor.getColumnIndex(helper.COLUMN_WEEKLY_ELECTRONICS));
            cv.put(helper.COLUMN_MONTHLY_ENTERTAINMENT, entertainment);
            int clothing = cursor.getInt(cursor.getColumnIndex(helper.COLUMN_WEEKLY_CLOTHING));
            cv.put(helper.COLUMN_MONTHLY_CLOTHING, clothing);
            int travel = cursor.getInt(cursor.getColumnIndex(helper.COLUMN_WEEKLY_TRAVEL));
            cv.put(helper.COLUMN_MONTHLY_TRAVEL, travel);
            int electronics = cursor.getInt(cursor.getColumnIndex(helper.COLUMN_WEEKLY_ELECTRONICS));
            cv.put(helper.COLUMN_MONTHLY_ELECTRONICS, electronics);
            int others = cursor.getInt(cursor.getColumnIndex(helper.COLUMN_WEEKLY_OTHERS));
            cv.put(helper.COLUMN_MONTHLY_OTHERS, others);

            database.insert(helper.TABLE_MONTHLY, null, cv);

        }


        return 0;
    }
    //•    That will take the todays table and put into weekly table
    public long insertIntoWeekly() {
        ContentValues cv = new ContentValues();

        Cursor cursor = database.query(helper.TABLE_DAILY, new String[]{helper.COLUMN_DAILY_ID, helper.COLUMN_DAILY_GROCERIES, helper.COLUMN_DAILY_BILLS,
                helper.COLUMN_DAILY_ENTERTAINMENT, helper.COLUMN_DAILY_CLOTHING, helper.COLUMN_DAILY_TRAVEL, helper.COLUMN_DAILY_ELECTRONICS,
                helper.COLUMN_DAILY_OTHERS}, null, null, null, null, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int groceries = cursor.getInt(cursor.getColumnIndex(helper.COLUMN_DAILY_GROCERIES));
            cv.put(helper.COLUMN_WEEKLY_GROCERIES, groceries);
            int bills = cursor.getInt(cursor.getColumnIndex(helper.COLUMN_DAILY_BILLS));
            cv.put(helper.COLUMN_WEEKLY_BILLS, bills);
            int entertainment = cursor.getInt(cursor.getColumnIndex(helper.COLUMN_DAILY_ELECTRONICS));
            cv.put(helper.COLUMN_WEEKLY_ENTERTAINMENT, entertainment);
            int clothing = cursor.getInt(cursor.getColumnIndex(helper.COLUMN_DAILY_CLOTHING));
            cv.put(helper.COLUMN_WEEKLY_CLOTHING, clothing);
            int travel = cursor.getInt(cursor.getColumnIndex(helper.COLUMN_DAILY_TRAVEL));
            cv.put(helper.COLUMN_WEEKLY_TRAVEL, travel);
            int electronics = cursor.getInt(cursor.getColumnIndex(helper.COLUMN_DAILY_ELECTRONICS));
            cv.put(helper.COLUMN_WEEKLY_ELECTRONICS, electronics);
            int others = cursor.getInt(cursor.getColumnIndex(helper.COLUMN_DAILY_OTHERS));
            cv.put(helper.COLUMN_WEEKLY_OTHERS, others);

            database.insert(helper.TABLE_WEEKLY, null, cv);

        }


        return 0;
    }


    //•    delete today table
    public int deleteDaily(){
        return database.delete(helper.TABLE_DAILY,null,null);
    }
    //•    delete weekly table
    public int deleteWeekly(){
        return database.delete(helper.TABLE_WEEKLY,null,null);
    }
    //•    delete monthly table
    public int deleteMonthly(){
        return database.delete(helper.TABLE_MONTHLY,null,null);
    }

    //    •    sum of groceries in today
    public int addTodayGrocery(){
        int total=0,next=0;
        Cursor c= database.query(helper.TABLE_DAILY, new String[]{helper.COLUMN_DAILY_ID, helper.COLUMN_DAILY_GROCERIES, helper.COLUMN_DAILY_BILLS,
                helper.COLUMN_DAILY_ENTERTAINMENT, helper.COLUMN_DAILY_CLOTHING, helper.COLUMN_DAILY_TRAVEL, helper.COLUMN_DAILY_ELECTRONICS,
                helper.COLUMN_DAILY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            next=c.getInt(c.getColumnIndex(helper.COLUMN_DAILY_GROCERIES));
            total = total+next;
        }
        return total;
    }
    //    •    sum of bills in today
    public int addTodayBills(){
        int total=0,next=0;
        Cursor c= database.query(helper.TABLE_DAILY, new String[]{helper.COLUMN_DAILY_ID, helper.COLUMN_DAILY_GROCERIES, helper.COLUMN_DAILY_BILLS,
                helper.COLUMN_DAILY_ENTERTAINMENT, helper.COLUMN_DAILY_CLOTHING, helper.COLUMN_DAILY_TRAVEL, helper.COLUMN_DAILY_ELECTRONICS,
                helper.COLUMN_DAILY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            next=c.getInt(c.getColumnIndex(helper.COLUMN_DAILY_BILLS));
            total = total+next;
        }
        return total;
    }
    //    •    sum of entertainment in today
    public int addTodayEntertainment(){
        int total=0,next=0;
        Cursor c= database.query(helper.TABLE_DAILY, new String[]{helper.COLUMN_DAILY_ID, helper.COLUMN_DAILY_GROCERIES, helper.COLUMN_DAILY_BILLS,
                helper.COLUMN_DAILY_ENTERTAINMENT, helper.COLUMN_DAILY_CLOTHING, helper.COLUMN_DAILY_TRAVEL, helper.COLUMN_DAILY_ELECTRONICS,
                helper.COLUMN_DAILY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            next=c.getInt(c.getColumnIndex(helper.COLUMN_DAILY_ENTERTAINMENT));
            total = total+next;
        }
        return total;
    }
    //    •    sum of electronic in today
    public int addTodayElectronics(){
        int total=0,next=0;
        Cursor c= database.query(helper.TABLE_DAILY, new String[]{helper.COLUMN_DAILY_ID, helper.COLUMN_DAILY_GROCERIES, helper.COLUMN_DAILY_BILLS,
                helper.COLUMN_DAILY_ENTERTAINMENT, helper.COLUMN_DAILY_CLOTHING, helper.COLUMN_DAILY_TRAVEL, helper.COLUMN_DAILY_ELECTRONICS,
                helper.COLUMN_DAILY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            next=c.getInt(c.getColumnIndex(helper.COLUMN_DAILY_ELECTRONICS));
            total = total+next;
        }
        return total;
    }
    //    •    sum of clothing in today
    public int addTodayClothing(){
        int total=0,next=0;
        Cursor c= database.query(helper.TABLE_DAILY, new String[]{helper.COLUMN_DAILY_ID, helper.COLUMN_DAILY_GROCERIES, helper.COLUMN_DAILY_BILLS,
                helper.COLUMN_DAILY_ENTERTAINMENT, helper.COLUMN_DAILY_CLOTHING, helper.COLUMN_DAILY_TRAVEL, helper.COLUMN_DAILY_ELECTRONICS,
                helper.COLUMN_DAILY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            next=c.getInt(c.getColumnIndex(helper.COLUMN_DAILY_CLOTHING));
            total = total+next;
        }
        return total;
    }
    //    •    sum of others in today
    public int addTodayOthers(){
        int total=0,next=0;
        Cursor c= database.query(helper.TABLE_DAILY, new String[]{helper.COLUMN_DAILY_ID, helper.COLUMN_DAILY_GROCERIES, helper.COLUMN_DAILY_BILLS,
                helper.COLUMN_DAILY_ENTERTAINMENT, helper.COLUMN_DAILY_CLOTHING, helper.COLUMN_DAILY_TRAVEL, helper.COLUMN_DAILY_ELECTRONICS,
                helper.COLUMN_DAILY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            next=c.getInt(c.getColumnIndex(helper.COLUMN_DAILY_OTHERS));
            total = total+next;
        }
        return total;
    }
    //    •    sum of travel in today
    public int addTodayTravel(){
        int total=0,next=0;
        Cursor c= database.query(helper.TABLE_DAILY, new String[]{helper.COLUMN_DAILY_ID, helper.COLUMN_DAILY_GROCERIES, helper.COLUMN_DAILY_BILLS,
                helper.COLUMN_DAILY_ENTERTAINMENT, helper.COLUMN_DAILY_CLOTHING, helper.COLUMN_DAILY_TRAVEL, helper.COLUMN_DAILY_ELECTRONICS,
                helper.COLUMN_DAILY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            next=c.getInt(c.getColumnIndex(helper.COLUMN_DAILY_TRAVEL));
            total = total+next;
        }
        return total;
    }


    //    •    sum of groceries in weekly
    public int addWeeklyGrocery(){
        int total=0,next=0;
        Cursor c= database.query(helper.TABLE_WEEKLY, new String[]{helper.COLUMN_WEEKLY_ID, helper.COLUMN_WEEKLY_GROCERIES, helper.COLUMN_WEEKLY_BILLS,
                helper.COLUMN_WEEKLY_ENTERTAINMENT, helper.COLUMN_WEEKLY_CLOTHING, helper.COLUMN_WEEKLY_TRAVEL, helper.COLUMN_WEEKLY_ELECTRONICS,
                helper.COLUMN_WEEKLY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            next=c.getInt(c.getColumnIndex(helper.COLUMN_WEEKLY_GROCERIES));
            total = total+next;
        }
        return total;
    }
    //    •    sum of bills in weekly
    public int addWeeklyBills(){
        int total=0,next=0;
        Cursor c= database.query(helper.TABLE_WEEKLY, new String[]{helper.COLUMN_WEEKLY_ID, helper.COLUMN_WEEKLY_GROCERIES, helper.COLUMN_WEEKLY_BILLS,
                helper.COLUMN_WEEKLY_ENTERTAINMENT, helper.COLUMN_WEEKLY_CLOTHING, helper.COLUMN_WEEKLY_TRAVEL, helper.COLUMN_WEEKLY_ELECTRONICS,
                helper.COLUMN_WEEKLY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            next=c.getInt(c.getColumnIndex(helper.COLUMN_WEEKLY_BILLS));
            total = total+next;
        }
        return total;
    }
    //    •    sum on entertainment in weekly
    public int addWeeklyEntertainment(){
        int total=0,next=0;
        Cursor c= database.query(helper.TABLE_WEEKLY, new String[]{helper.COLUMN_WEEKLY_ID, helper.COLUMN_WEEKLY_GROCERIES, helper.COLUMN_WEEKLY_BILLS,
                helper.COLUMN_WEEKLY_ENTERTAINMENT, helper.COLUMN_WEEKLY_CLOTHING, helper.COLUMN_WEEKLY_TRAVEL, helper.COLUMN_WEEKLY_ELECTRONICS,
                helper.COLUMN_WEEKLY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            next=c.getInt(c.getColumnIndex(helper.COLUMN_WEEKLY_ENTERTAINMENT));
            total = total+next;
        }
        return total;
    }
    //    •    sum of electronic in weekly
    public int addWeeklyElectronics(){
        int total=0,next=0;
        Cursor c= database.query(helper.TABLE_WEEKLY, new String[]{helper.COLUMN_WEEKLY_ID, helper.COLUMN_WEEKLY_GROCERIES, helper.COLUMN_WEEKLY_BILLS,
                helper.COLUMN_WEEKLY_ENTERTAINMENT, helper.COLUMN_WEEKLY_CLOTHING, helper.COLUMN_WEEKLY_TRAVEL, helper.COLUMN_WEEKLY_ELECTRONICS,
                helper.COLUMN_WEEKLY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            next=c.getInt(c.getColumnIndex(helper.COLUMN_WEEKLY_ELECTRONICS));
            total = total+next;
        }
        return total;
    }
    //    •    sum of clothing in weekly
    public int addWeeklyClothing(){
        int total=0,next=0;
        Cursor c= database.query(helper.TABLE_WEEKLY, new String[]{helper.COLUMN_WEEKLY_ID, helper.COLUMN_WEEKLY_GROCERIES, helper.COLUMN_WEEKLY_BILLS,
                helper.COLUMN_WEEKLY_ENTERTAINMENT, helper.COLUMN_WEEKLY_CLOTHING, helper.COLUMN_WEEKLY_TRAVEL, helper.COLUMN_WEEKLY_ELECTRONICS,
                helper.COLUMN_WEEKLY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            next=c.getInt(c.getColumnIndex(helper.COLUMN_WEEKLY_CLOTHING));
            total = total+next;
        }
        return total;
    }
    //    •    sum of other in weekly
    public int addWeeklyOther(){
        int total=0,next=0;
        Cursor c= database.query(helper.TABLE_WEEKLY, new String[]{helper.COLUMN_WEEKLY_ID, helper.COLUMN_WEEKLY_GROCERIES, helper.COLUMN_WEEKLY_BILLS,
                helper.COLUMN_WEEKLY_ENTERTAINMENT, helper.COLUMN_WEEKLY_CLOTHING, helper.COLUMN_WEEKLY_TRAVEL, helper.COLUMN_WEEKLY_ELECTRONICS,
                helper.COLUMN_WEEKLY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            next=c.getInt(c.getColumnIndex(helper.COLUMN_WEEKLY_OTHERS));
            total = total+next;
        }
        return total;
    }
    //    •    sum of travel in weekly
    public int addWeeklyTravel(){
        int total=0,next=0;
        Cursor c= database.query(helper.TABLE_WEEKLY, new String[]{helper.COLUMN_WEEKLY_ID, helper.COLUMN_WEEKLY_GROCERIES, helper.COLUMN_WEEKLY_BILLS,
                helper.COLUMN_WEEKLY_ENTERTAINMENT, helper.COLUMN_WEEKLY_CLOTHING, helper.COLUMN_WEEKLY_TRAVEL, helper.COLUMN_WEEKLY_ELECTRONICS,
                helper.COLUMN_WEEKLY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            next=c.getInt(c.getColumnIndex(helper.COLUMN_WEEKLY_TRAVEL));
            total = total+next;
        }
        return total;
    }



    //    •    sum of groceries in monthly
    public int addMonthlyGrocery(){
        int total=0,next=0;
        Cursor c= database.query(helper.TABLE_MONTHLY, new String[]{helper.COLUMN_MONTHLY_ID, helper.COLUMN_MONTHLY_GROCERIES, helper.COLUMN_MONTHLY_BILLS,
                helper.COLUMN_MONTHLY_ENTERTAINMENT, helper.COLUMN_MONTHLY_CLOTHING, helper.COLUMN_MONTHLY_TRAVEL, helper.COLUMN_MONTHLY_ELECTRONICS,
                helper.COLUMN_MONTHLY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            next=c.getInt(c.getColumnIndex(helper.COLUMN_MONTHLY_GROCERIES));
            total = total+next;
        }
        return total;
    }
    //    •    sum of bills in monthly
    public int addMonthlyBills(){
        int total=0,next=0;
        Cursor c= database.query(helper.TABLE_MONTHLY, new String[]{helper.COLUMN_MONTHLY_ID, helper.COLUMN_MONTHLY_GROCERIES, helper.COLUMN_MONTHLY_BILLS,
                helper.COLUMN_MONTHLY_ENTERTAINMENT, helper.COLUMN_MONTHLY_CLOTHING, helper.COLUMN_MONTHLY_TRAVEL, helper.COLUMN_MONTHLY_ELECTRONICS,
                helper.COLUMN_MONTHLY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            next=c.getInt(c.getColumnIndex(helper.COLUMN_MONTHLY_BILLS));
            total = total+next;
        }
        return total;
    }
    //    •    sum of entertainment in monthly
    public int addMonthlyEntertainment(){
        int total=0,next=0;
        Cursor c= database.query(helper.TABLE_MONTHLY, new String[]{helper.COLUMN_MONTHLY_ID, helper.COLUMN_MONTHLY_GROCERIES, helper.COLUMN_MONTHLY_BILLS,
                helper.COLUMN_MONTHLY_ENTERTAINMENT, helper.COLUMN_MONTHLY_CLOTHING, helper.COLUMN_MONTHLY_TRAVEL, helper.COLUMN_MONTHLY_ELECTRONICS,
                helper.COLUMN_MONTHLY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            next=c.getInt(c.getColumnIndex(helper.COLUMN_MONTHLY_ENTERTAINMENT));
            total = total+next;
        }
        return total;
    }
    //    •    sum of electronics in monthly
    public int addMonthlyElectronics(){
        int total=0,next=0;
        Cursor c= database.query(helper.TABLE_MONTHLY, new String[]{helper.COLUMN_MONTHLY_ID, helper.COLUMN_MONTHLY_GROCERIES, helper.COLUMN_MONTHLY_BILLS,
                helper.COLUMN_MONTHLY_ENTERTAINMENT, helper.COLUMN_MONTHLY_CLOTHING, helper.COLUMN_MONTHLY_TRAVEL, helper.COLUMN_MONTHLY_ELECTRONICS,
                helper.COLUMN_MONTHLY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            next=c.getInt(c.getColumnIndex(helper.COLUMN_MONTHLY_ELECTRONICS));
            total = total+next;
        }
        return total;
    }
    //    •    sum of clothing in monthly
    public int addMonthlyClothing(){
        int total=0,next=0;
        Cursor c= database.query(helper.TABLE_MONTHLY, new String[]{helper.COLUMN_MONTHLY_ID, helper.COLUMN_MONTHLY_GROCERIES, helper.COLUMN_MONTHLY_BILLS,
                helper.COLUMN_MONTHLY_ENTERTAINMENT, helper.COLUMN_MONTHLY_CLOTHING, helper.COLUMN_MONTHLY_TRAVEL, helper.COLUMN_MONTHLY_ELECTRONICS,
                helper.COLUMN_MONTHLY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            next=c.getInt(c.getColumnIndex(helper.COLUMN_MONTHLY_CLOTHING));
            total = total+next;
        }
        return total;
    }
    //    •    sum of other in monthly
    public int addMonthlyOther(){
        int total=0,next=0;
        Cursor c= database.query(helper.TABLE_MONTHLY, new String[]{helper.COLUMN_MONTHLY_ID, helper.COLUMN_MONTHLY_GROCERIES, helper.COLUMN_MONTHLY_BILLS,
                helper.COLUMN_MONTHLY_ENTERTAINMENT, helper.COLUMN_MONTHLY_CLOTHING, helper.COLUMN_MONTHLY_TRAVEL, helper.COLUMN_MONTHLY_ELECTRONICS,
                helper.COLUMN_MONTHLY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            next=c.getInt(c.getColumnIndex(helper.COLUMN_MONTHLY_OTHERS));
            total = total+next;
        }
        return total;
    }
    //    •    sum of travel in monthly
    public int addMonthlyTravel(){
        int total=0,next=0;
        Cursor c= database.query(helper.TABLE_MONTHLY, new String[]{helper.COLUMN_MONTHLY_ID, helper.COLUMN_MONTHLY_GROCERIES, helper.COLUMN_MONTHLY_BILLS,
                helper.COLUMN_MONTHLY_ENTERTAINMENT, helper.COLUMN_MONTHLY_CLOTHING, helper.COLUMN_MONTHLY_TRAVEL, helper.COLUMN_MONTHLY_ELECTRONICS,
                helper.COLUMN_MONTHLY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            next=c.getInt(c.getColumnIndex(helper.COLUMN_MONTHLY_TRAVEL));
            total = total+next;
        }
        return total;
    }

    //Sum of all in Daily
    public int addAllToday(){
        int total=0;
        Cursor c= database.query(helper.TABLE_DAILY, new String[]{helper.COLUMN_DAILY_ID, helper.COLUMN_DAILY_GROCERIES, helper.COLUMN_DAILY_BILLS,
                helper.COLUMN_DAILY_ENTERTAINMENT, helper.COLUMN_DAILY_CLOTHING, helper.COLUMN_DAILY_TRAVEL, helper.COLUMN_DAILY_ELECTRONICS,
                helper.COLUMN_DAILY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            int next1=c.getInt(c.getColumnIndex(helper.COLUMN_DAILY_GROCERIES));
            int next2=c.getInt(c.getColumnIndex(helper.COLUMN_DAILY_BILLS));
            int next3=c.getInt(c.getColumnIndex(helper.COLUMN_DAILY_ENTERTAINMENT));
            int next4=c.getInt(c.getColumnIndex(helper.COLUMN_DAILY_ELECTRONICS));
            int next5=c.getInt(c.getColumnIndex(helper.COLUMN_DAILY_CLOTHING));
            int next6=c.getInt(c.getColumnIndex(helper.COLUMN_DAILY_OTHERS));
            int next7=c.getInt(c.getColumnIndex(helper.COLUMN_DAILY_TRAVEL));
            total = total+next1+next2+next3+next4+next5+next6+next7;
        }
        return total;
    }
    //Sum of all in Weekly
    public int addAllWeekly(){
        int total=0;
        Cursor c= database.query(helper.TABLE_WEEKLY, new String[]{helper.COLUMN_WEEKLY_ID, helper.COLUMN_WEEKLY_GROCERIES, helper.COLUMN_WEEKLY_BILLS,
                helper.COLUMN_WEEKLY_ENTERTAINMENT, helper.COLUMN_WEEKLY_CLOTHING, helper.COLUMN_WEEKLY_TRAVEL, helper.COLUMN_WEEKLY_ELECTRONICS,
                helper.COLUMN_WEEKLY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            int next1=c.getInt(c.getColumnIndex(helper.COLUMN_WEEKLY_GROCERIES));
            int next2=c.getInt(c.getColumnIndex(helper.COLUMN_WEEKLY_BILLS));
            int next3=c.getInt(c.getColumnIndex(helper.COLUMN_WEEKLY_ENTERTAINMENT));
            int next4=c.getInt(c.getColumnIndex(helper.COLUMN_WEEKLY_ELECTRONICS));
            int next5=c.getInt(c.getColumnIndex(helper.COLUMN_WEEKLY_CLOTHING));
            int next6=c.getInt(c.getColumnIndex(helper.COLUMN_WEEKLY_OTHERS));
            int next7=c.getInt(c.getColumnIndex(helper.COLUMN_WEEKLY_TRAVEL));
            total = total+next1+next2+next3+next4+next5+next6+next7;
        }
        return total;
    }
    //Sum of all in Monthly
    public int addAllMonthly(){
        int total=0;
        Cursor c= database.query(helper.TABLE_MONTHLY, new String[]{helper.COLUMN_MONTHLY_ID, helper.COLUMN_MONTHLY_GROCERIES, helper.COLUMN_MONTHLY_BILLS,
                helper.COLUMN_MONTHLY_ENTERTAINMENT, helper.COLUMN_MONTHLY_CLOTHING, helper.COLUMN_MONTHLY_TRAVEL, helper.COLUMN_MONTHLY_ELECTRONICS,
                helper.COLUMN_MONTHLY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            int next1=c.getInt(c.getColumnIndex(helper.COLUMN_MONTHLY_GROCERIES));
            int next2=c.getInt(c.getColumnIndex(helper.COLUMN_MONTHLY_BILLS));
            int next3=c.getInt(c.getColumnIndex(helper.COLUMN_MONTHLY_ENTERTAINMENT));
            int next4=c.getInt(c.getColumnIndex(helper.COLUMN_MONTHLY_ELECTRONICS));
            int next5=c.getInt(c.getColumnIndex(helper.COLUMN_MONTHLY_CLOTHING));
            int next6=c.getInt(c.getColumnIndex(helper.COLUMN_MONTHLY_OTHERS));
            int next7=c.getInt(c.getColumnIndex(helper.COLUMN_MONTHLY_TRAVEL));
            total = total+next1+next2+next3+next4+next5+next6+next7;
        }
        return total;
    }


    public boolean checkIfMonthlyHasAnything(){
        String count ="SELECT count(*) FROM "+helper.TABLE_MONTHLY;
        Cursor cursor = database.rawQuery(count, null);
        cursor.moveToFirst();
        int icount = cursor.getInt(0);
        if(icount>0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkIfWeeklyHasAnything(){
        String count ="SELECT count(*) FROM "+helper.TABLE_WEEKLY;
        Cursor cursor = database.rawQuery(count,null);
        cursor.moveToFirst();
        int icount = cursor.getInt(0);
        if(icount>0){
            return true;
        }
        else{
            return false;
        }
    }

    public int findMonthlyTerms(){
        int total=0;
        Cursor c= database.query(helper.TABLE_MONTHLY, new String[]{helper.COLUMN_MONTHLY_ID, helper.COLUMN_MONTHLY_GROCERIES, helper.COLUMN_MONTHLY_BILLS,
                helper.COLUMN_MONTHLY_ENTERTAINMENT, helper.COLUMN_MONTHLY_CLOTHING, helper.COLUMN_MONTHLY_TRAVEL, helper.COLUMN_MONTHLY_ELECTRONICS,
                helper.COLUMN_MONTHLY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            total = total+1;
        }
        return total;
    }
    public int findWeeklyTerms(){
        int total=0;
        Cursor c= database.query(helper.TABLE_WEEKLY, new String[]{helper.COLUMN_WEEKLY_ID, helper.COLUMN_WEEKLY_GROCERIES, helper.COLUMN_WEEKLY_BILLS,
                helper.COLUMN_WEEKLY_ENTERTAINMENT, helper.COLUMN_WEEKLY_CLOTHING, helper.COLUMN_WEEKLY_TRAVEL, helper.COLUMN_WEEKLY_ELECTRONICS,
                helper.COLUMN_WEEKLY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            total=total+1;
        }
        return total;
    }
    public int findDailyTerms(){
        int total = 0;
        Cursor c= database.query(helper.TABLE_DAILY, new String[]{helper.COLUMN_DAILY_ID, helper.COLUMN_DAILY_GROCERIES, helper.COLUMN_DAILY_BILLS,
                helper.COLUMN_DAILY_ENTERTAINMENT, helper.COLUMN_DAILY_CLOTHING, helper.COLUMN_DAILY_TRAVEL, helper.COLUMN_DAILY_ELECTRONICS,
                helper.COLUMN_DAILY_OTHERS}, null, null, null, null, null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            total=total+1;
        }
        return total;

    }

}