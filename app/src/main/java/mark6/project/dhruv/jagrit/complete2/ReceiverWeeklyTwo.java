package mark6.project.dhruv.jagrit.complete2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by SONY on 10-08-2015.
 */
public class ReceiverWeeklyTwo extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        MainSQLiteClass sqLiteClass = new MainSQLiteClass(context);
        sqLiteClass.open();
        long j = sqLiteClass.insertIntoMonthly();
        int i = sqLiteClass.deleteWeekly();
        sqLiteClass.close();
    }
}
