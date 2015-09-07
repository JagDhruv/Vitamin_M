package mark6.project.dhruv.jagrit.complete2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class Monthly extends Fragment {
    ProgressBar progressBar;
    ProgressBar progressBar2;
    ProgressBar progressBar3;
    private Handler mHandler = new Handler();
    private int mProgressStatus=0;
    private int i ;
    private int i2;
    private int i3;
    MainSQLiteClass database;
    TextView tv11,tv22,tv33;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.monthly,container,false);
        tv11 = (TextView) v.findViewById(R.id.monthRec);
        tv22 = (TextView) v.findViewById(R.id.monthAvg);
        tv33 = (TextView) v.findViewById(R.id.monthTotal);
        Calendar cal = Calendar.getInstance();
        final SharedPreferences preferences = getActivity().getSharedPreferences("money", Context.MODE_PRIVATE);
        int recommend = preferences.getInt("save", 0);
        int maxDaysInMonth = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        int monthlyRecommend = (recommend*maxDaysInMonth)/maxDaysInMonth;


        try {



            //******************************
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

            int averageMonth;
            int allBigNumberSum1=allBigNumberSum;
            if (allBigNumberSum==0){
                allBigNumberSum1++;
                averageMonth = allBigSum/allBigNumberSum1;
            }
            else {
                averageMonth = allBigSum / allBigNumberSum;
            }


            //*******************************


            int allMonthSum = database.addAllMonthly();

            //i=monthlyRecommend;
            //i2=averageMonth;
            //i3=allMonthSum;



            if(monthlyRecommend>averageMonth && monthlyRecommend>allMonthSum){
                i=100;
                if(monthlyRecommend==0){
                    monthlyRecommend=monthlyRecommend+1;
                }
                i2=(averageMonth*100)/monthlyRecommend;
                i3=(allMonthSum*100)/monthlyRecommend;
            }
            else if(averageMonth>monthlyRecommend && averageMonth>allMonthSum){
                i2=100;
                if(averageMonth==0){
                    averageMonth=averageMonth+1;
                }
                i=(monthlyRecommend*100)/averageMonth;
                i3=(allMonthSum*100)/averageMonth;
            }
            else if(allMonthSum>monthlyRecommend && allMonthSum>averageMonth){
                i3=100;
                if(allMonthSum==0){
                    allMonthSum++;
                }
                i=(monthlyRecommend*100)/allMonthSum;
                i2=(averageMonth*100)/allMonthSum;
            }

            tv11.setText(String.valueOf(monthlyRecommend));
            tv22.setText(String.valueOf(averageMonth));
            tv33.setText(String.valueOf(allMonthSum));
            database.close();


        }
        catch(Exception e){
            Toast.makeText(getActivity(),String.valueOf(e),Toast.LENGTH_LONG).show();
        } //catch (PackageManager.NameNotFoundException e) {
          //  e.printStackTrace();
        //}


        Calendar calendar = Calendar.getInstance();
        if(calendar.get(Calendar.DATE)==calendar.getActualMaximum(Calendar.DATE)){

            int j = database.deleteMonthly();
        }



        progressBar = (ProgressBar)v.findViewById(R.id.progressBar);
        doSomeWork(i,progressBar);

        progressBar2 = (ProgressBar) v.findViewById(R.id.progressBar2);
        doSomeWork(i2,progressBar2);

        progressBar3 = (ProgressBar) v.findViewById(R.id.progressBar3);
        doSomeWork(i3,progressBar3);
        return v;
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
}
