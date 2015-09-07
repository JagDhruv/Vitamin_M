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
public class ReceiverDailyOne extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        MainSQLiteClass sqLiteClass = new MainSQLiteClass(context);
        sqLiteClass.open();
        long j = sqLiteClass.insertIntoWeekly();
        int i = sqLiteClass.deleteDaily();
        sqLiteClass.close();
        Toast.makeText(context,"transferred",Toast.LENGTH_LONG).show();


        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent1 = new Intent(context,ReceiverDailyTwo.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,123,intent1,0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.MILLISECOND,86400000);
        am.setRepeating(AlarmManager.RTC_WAKEUP,0,calendar.getTimeInMillis(),pendingIntent);

    }
}
