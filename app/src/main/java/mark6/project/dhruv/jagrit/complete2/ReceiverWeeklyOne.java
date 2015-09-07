package mark6.project.dhruv.jagrit.complete2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by SONY on 10-08-2015.
 */
public class ReceiverWeeklyOne extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        MainSQLiteClass sqLiteClass = new MainSQLiteClass(context);
        sqLiteClass.open();
        long j = sqLiteClass.insertIntoMonthly();
        int i = sqLiteClass.deleteWeekly();
        sqLiteClass.close();

        Toast.makeText(context,"Transferred to monthly",Toast.LENGTH_LONG).show();

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent1 = new Intent(context,ReceiverWeeklyTwo.class);
        Calendar calendar = Calendar.getInstance();
        int week = calendar.getActualMaximum(Calendar.DAY_OF_WEEK);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 123, intent1, 0);

        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.MILLISECOND,week*86400000);
        am.setRepeating(AlarmManager.RTC_WAKEUP,0,calendar.getTimeInMillis(),pendingIntent);

    }
}
