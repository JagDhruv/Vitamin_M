package mark6.project.dhruv.jagrit.complete2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by JAGRIT on 31-07-2015.
 */
public class Weekly extends Fragment {
    ProgressBar progressBar;
    ProgressBar progressBar2;
    ProgressBar progressBar3;
    private Handler mHandler = new Handler();
    private int mProgressStatus=0;
    private int i;
    private int i2;
    private int i3;
    MainSQLiteClass database;
    SQLiteHelper helper;
    TextView tvrecomment,tvAvg,tvTotal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.weekly,container,false);
        tvrecomment = (TextView) v.findViewById(R.id.weeklypro1);
        tvAvg = (TextView) v.findViewById(R.id.weeklyavg);

        tvTotal = (TextView) v.findViewById(R.id.totalWeek);
        //Dhruv Code
        try {
            //First set up the recommended value
            final SharedPreferences preferences = getActivity().getSharedPreferences("money", Context.MODE_PRIVATE);
            int recommend = preferences.getInt("save", 0);
           //Important
            Calendar cal = Calendar.getInstance();
            int i = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
            int weeklyrecommend = recommend * 7/ (cal.getActualMaximum(Calendar.DAY_OF_MONTH) - getDate());
            database = new MainSQLiteClass(getActivity());
            database.open();

            //*******************************//
            //Calculate Average of week

            int allBigSum = database.addAllMonthly() + database.addAllToday() + database.addAllWeekly();
            int allBigNumberSum;

            int monthAveragge = cal.get(Calendar.MONTH);
            if( monthAveragge == getMonth()-1){
                allBigNumberSum = cal.get(Calendar.DAY_OF_MONTH)-getDate();
            }
            else{
                allBigNumberSum = cal.get(Calendar.DAY_OF_MONTH);
            }

            int averageWeek;
            int allBigNumberSum1=allBigNumberSum;
            if (allBigNumberSum==0){
                allBigNumberSum1++;
                averageWeek = allBigSum/allBigNumberSum1*7;
            }
            else {
                averageWeek = allBigSum / allBigNumberSum*7;
            }

            //Calculate Total of the week
            int allWeekSum = database.addAllWeekly();

            if (weeklyrecommend > averageWeek && weeklyrecommend > allWeekSum) {
                i = 100;
                if(weeklyrecommend==0){
                    weeklyrecommend++;
                }
                i2 = (averageWeek * 100) / weeklyrecommend;
                i3 = (allWeekSum * 100) / weeklyrecommend;
            }
            else if(averageWeek>weeklyrecommend && averageWeek>allWeekSum){
                i2=100;
                if(averageWeek==0){
                    averageWeek++;
                }
                i=(weeklyrecommend*100)/averageWeek;
                i3=(allWeekSum*100)/averageWeek;
            }
            else if(allWeekSum>weeklyrecommend && allWeekSum>averageWeek){
                i3=100;
                if (allWeekSum==0){
                    allWeekSum++;
                }
                i=(weeklyrecommend*100)/allWeekSum;
                i2=(averageWeek*100)/allWeekSum;
            }
            tvrecomment.setText(String.valueOf(weeklyrecommend));
            tvAvg.setText(String.valueOf(averageWeek));
            tvTotal.setText(String.valueOf(allWeekSum));

            progressBar = (ProgressBar)v.findViewById(R.id.progressBar);
            doSomeWork(i,progressBar);

            progressBar2 = (ProgressBar) v.findViewById(R.id.progressBar2);
            doSomeWork(i2, progressBar2);

            progressBar3 = (ProgressBar) v.findViewById(R.id.progressBar3);
            doSomeWork(i3, progressBar3);
            return v;
        }


        catch(ArithmeticException e){
            Intent intent = new Intent(getActivity(),FirstLaunch.class);
            startActivity(intent);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        if(calendar.get(Calendar.DAY_OF_WEEK)==2){
            long i = database.insertIntoMonthly();
            int j = database.deleteWeekly();
        }


        //End Of Dhruv Code


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

}
