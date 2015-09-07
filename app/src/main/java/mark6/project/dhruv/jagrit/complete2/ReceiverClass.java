package mark6.project.dhruv.jagrit.complete2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by SONY on 03-08-2015.
 */
public class ReceiverClass extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        MainSQLiteClass sqLiteClass = new MainSQLiteClass(context);
        sqLiteClass.open();

        final SharedPreferences preferences = context.getSharedPreferences("PREFERENCE", context.MODE_PRIVATE);
        preferences.edit().clear().commit();

        int i = sqLiteClass.deleteMonthly();
        sqLiteClass.close();
        Toast.makeText(context,"Again First Launch",Toast.LENGTH_LONG).show();



        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent1 = new Intent(context,ReceiverClassTwo.class);

        Calendar calendar = Calendar.getInstance();
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.MILLISECOND,days*86400000);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,123,intent1,0);
        am.setRepeating(AlarmManager.RTC_WAKEUP,0,calendar.getTimeInMillis(),pendingIntent);
    }
}
