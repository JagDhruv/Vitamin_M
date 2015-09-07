
package mark6.project.dhruv.jagrit.complete2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by JAGRIT on 31-07-2015.
 */
public class Daily extends Fragment{
    ProgressBar progressBar;
    ProgressBar progressBar2;
    ProgressBar progressBar3;
    private Handler mHandler = new Handler();
    private int mProgressStatus=0;
    private int i ;
    private int i2;
    private int i3;
    Button b1;
    MainSQLiteClass database;
    SQLiteHelper helper;
    //

    TextView tv,tv2,tv3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.daily, container, false);

        tv = (TextView) v.findViewById(R.id.textView);
        tv2 = (TextView) v.findViewById(R.id.textView2);
        tv3 = (TextView) v.findViewById(R.id.textView3);

        final SharedPreferences preferences = getActivity().getSharedPreferences("money", Context.MODE_PRIVATE);
        int getRecommend =preferences.getInt("save", 0);

        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);

        int recommend=0;
        try {
            if(month == getMonth()-1){
                recommend =getRecommend / (cal.getActualMaximum(Calendar.DAY_OF_MONTH) - getDate());
            }
            else{
                recommend =getRecommend / (cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        try {

            database = new MainSQLiteClass(getActivity());
            database.open();

            int allBigSum = database.addAllMonthly() + database.addAllToday() + database.addAllWeekly();
            int allBigNumberSum;

            int monthAveragge = cal.get(Calendar.MONTH);
            if( monthAveragge == getMonth()-1){
                allBigNumberSum = cal.get(Calendar.DAY_OF_MONTH)-getDate();
            }
            else{
                allBigNumberSum = cal.get(Calendar.DAY_OF_MONTH);
            }

            int dailyAverage;
            int allBigNumberSum1=allBigNumberSum;
            if (allBigNumberSum==0){
                allBigNumberSum1++;
                dailyAverage = allBigSum/allBigNumberSum1;
            }
            else {
                dailyAverage = allBigSum / allBigNumberSum;
            }

            if(recommend>database.addAllToday() && recommend>dailyAverage) {
                i3 = database.addAllToday() * 100 / recommend;
                i2 = (dailyAverage * 100) / recommend;
                i = 100;
            }

            else if(dailyAverage>=database.addAllToday() && dailyAverage>=recommend){
                i2=100;
                i=(recommend*100)/dailyAverage;
                i3 = (database.addAllToday() *100) / recommend;
            }

            else if(database.addAllToday()>=recommend && database.addAllToday()>=dailyAverage){
                i3=100;
                i = (recommend*100) / database.addAllToday();
                i2 = (dailyAverage * 100) / database.addAllToday();
            }

            tv.setText(String.valueOf(recommend));
            tv2.setText(String.valueOf(dailyAverage));
            tv3.setText(String.valueOf(database.addAllToday()));

            database.close();
        }
        catch(Exception e){
            Toast.makeText(getActivity(),String.valueOf(e),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getActivity(),FirstLaunch.class);
            startActivity(intent);
        }



       /*     Calendar calendar = Calendar.getInstance();
            if (calendar.get(Calendar.HOUR_OF_DAY)==0  && calendar.get(Calendar.MINUTE)==1) {
                database.open();
                long i = database.insertIntoWeekly();
                int j=database.deleteDaily();
                database.close();
            }
*/

        progressBar = (ProgressBar)v.findViewById(R.id.progressBar);
        doSomeWork(i,progressBar);

        progressBar2 = (ProgressBar) v.findViewById(R.id.progressBar2);
        doSomeWork(i2,progressBar2);

        progressBar3 = (ProgressBar) v.findViewById(R.id.progressBar3);
        doSomeWork(i3, progressBar3);

        /*if(database.checkIfMonthlyHasAnything() && database.checkIfWeeklyHasAnything()){
          //Add all items of the monthly
           //add total number of items
           Toast.makeText(getActivity(),"Arre kutte ki gaand chaat",Toast.LENGTH_LONG).show();
       }

        else{
           if(database.checkIfWeeklyHasAnything()){
               //Add all items of weekly
               //add total number of items
           }
           else {
               //Add All items of daily
               //Add total number of items
           }
       }*/






        return v;
    }

    public void doSomeWork( final int i, final ProgressBar p) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mProgressStatus < i  ){

                    mProgressStatus +=1;
                    //update the Progress bar
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            p.setProgress(mProgressStatus);

                        }
                    });
                    try{
                        //Display progress slowly
                        Thread.sleep(50);
                    } catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }



    private int getMonth() throws PackageManager.NameNotFoundException {
        // get date time in custom format
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String pingu= sdf.format(new Date(getActivity()
                .getPackageManager()
                .getPackageInfo("mark6.project.dhruv.jagrit.complete2", 0)
                .firstInstallTime));

        int i = Integer.parseInt(pingu);
        return i;
    }
    private int getDate() throws PackageManager.NameNotFoundException {
        // get date time in custom format
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String pingu= sdf.format(new Date(getActivity()
                .getPackageManager()
                .getPackageInfo("mark6.project.dhruv.jagrit.complete2", 0)
                .firstInstallTime));

        int i = Integer.parseInt(pingu);
        return i;
    }

}
