package mark6.project.dhruv.jagrit.complete2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends AppCompatActivity  implements MaterialTabListener{


    MaterialTabHost tabHost;
    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    CharSequence Titles[]={"DAILY","WEEKLY","MONTHLY"};
    int Numboftabs =3;
    int count=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        tabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);

        Calendar calendar = Calendar.getInstance();
        //if (calendar.get(Calendar.HOUR_OF_DAY) == 0 && calendar.get(Calendar.MINUTE) == 1 && calendar.get(Calendar.SECOND) == 0) {
        //    count++;
        //}
        final SharedPreferences preferences = getSharedPreferences("average", MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("save2", count);

        try {
            //Open the file first time
            //1.
            Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .getBoolean("isFirstRun", true);
            //2.
            if (isFirstRun) {
                //show start activity
                startActivity(new Intent(MainActivity.this, FirstLaunch.class));
                Toast.makeText(MainActivity.this, "First Run", Toast.LENGTH_LONG)
                        .show();

                //*****************************************************//
                //Broadcast to Daily
                Calendar calendar1 = Calendar.getInstance();

                AlarmManager am3 = (AlarmManager) getSystemService(ALARM_SERVICE);
                Intent intent2 = new Intent(MainActivity.this, ReceiverDailyOne.class);
                int day1 = calendar1.get(Calendar.HOUR_OF_DAY);
                int day2 = calendar1.get(Calendar.MINUTE);

                int day3 = 24-day1;
                int day4 = 60-day2;
                int day5= day3*3600000 + day4*60000;


                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent2, 0);
                //Calendar calendar12 = Calendar.getInstance();
                //calendar12.setTimeInMillis(System.currentTimeMillis());
                //calendar12.add(Calendar.SECOND, 60);
                calendar1.setTimeInMillis(System.currentTimeMillis());
                calendar1.add(Calendar.MILLISECOND,day5);
                am3.set(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(), pendingIntent);

                //***********************************************************//


                //******************************************************//
                //Broadcasr to send to weekly
                AlarmManager am2 = (AlarmManager) getSystemService(ALARM_SERVICE);
                Intent intent1 = new Intent(MainActivity.this, ReceiverWeeklyOne.class);
                //Calendar calendar1 = Calendar.getInstance();
                int week = calendar1.getActualMaximum(Calendar.DAY_OF_WEEK) - calendar1.get(Calendar.DAY_OF_WEEK);
                //int week = 0;
                calendar1.setTimeInMillis(System.currentTimeMillis());
                calendar1.add(Calendar.MILLISECOND, week * 86400000);
                PendingIntent pendingIntent2 = PendingIntent.getBroadcast(this, 1234, intent1, 0);
                am2.set(AlarmManager.RTC_WAKEUP,calendar1.getTimeInMillis(), pendingIntent2);
                //End of Broadcast
                //**********************************************************//


                //**************************************************//
                //Broadcast to send to monthly
                AlarmManager am1 = (AlarmManager) getSystemService(ALARM_SERVICE);
                Intent intent = new Intent(MainActivity.this, ReceiverClass.class);
                //Calendar cal1 = Calendar.getInstance();
                //cal1.set(Calendar.HOUR_OF_DAY,0);
                //cal1.set(Calendar.MINUTE, 1);
                Calendar cal1 = Calendar.getInstance();
                int date = 0;

                date = getDate();
                int dat1 = cal1.getActualMaximum(Calendar.DAY_OF_MONTH);
                int day = dat1 - date;

                cal1.setTimeInMillis(System.currentTimeMillis());
                cal1.add(Calendar.MILLISECOND,day*86400000);
                PendingIntent pendingIntent1 = PendingIntent.getBroadcast(this, 123, intent, 0);

                am1.set(AlarmManager.RTC_WAKEUP, cal1.getTimeInMillis(), pendingIntent1);
            }
//3.
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putBoolean("isFirstRun", false).commit();

//End of file first time


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
            pager = (ViewPager) findViewById(R.id.pager);
            adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);
            pager.setAdapter(adapter);

            pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    tabHost.setSelectedNavigationItem(position);
                }
            });
            for (int i = 0; i < adapter.getCount(); i++) {
                tabHost.addTab(
                        tabHost.newTab()
                                .setText(adapter.getPageTitle(i))
                                .setTabListener(this)
                );
            }

            //Step 1: make ImageView
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.ic_action_new);

            //Step 2:Put Floating Action Button
            FloatingActionButton actionButton = new FloatingActionButton.Builder(this).setContentView(imageView)
                    .setBackgroundDrawable(R.drawable.select_button)
                    .build();

            actionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, AddKarDe.class);
                    startActivity(intent);
                }
            });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id==R.id.circular_view){
            Intent i = new Intent(MainActivity.this,MainActivityC.class);
            finish();
            startActivity(i);

        }
        if (id == R.id.change_money){
            Intent intent = new Intent(MainActivity.this,FirstLaunch.class);
            startActivity(intent);
            finish();
            MainSQLiteClass sqLiteClass = new MainSQLiteClass(this);
            sqLiteClass.open();
            sqLiteClass.deleteDaily();
            sqLiteClass.deleteWeekly();
            sqLiteClass.deleteMonthly();
            sqLiteClass.close();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        pager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

   int getDate() throws PackageManager.NameNotFoundException {
        // get date time in custom format
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String pingu= sdf.format(new Date(this
                .getPackageManager()
                .getPackageInfo("mark6.project.dhruv.jagrit.complete2", 0)
                .firstInstallTime));

        int i = Integer.parseInt(pingu);
        return i;
    }

    private int getMonth() throws PackageManager.NameNotFoundException {
        // get date time in custom format
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String pingu= sdf.format(new Date(this
                .getPackageManager()
                .getPackageInfo("mark6.project.dhruv.jagrit.complete2", 0)
                .firstInstallTime));

        int i = Integer.parseInt(pingu);
        return i;
    }
}
