package mark6.project.dhruv.jagrit.complete2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by SONY on 03-08-2015.
 */
public class ReceiverClassTwo extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        MainSQLiteClass mainSQLiteClass = new MainSQLiteClass(context);
        mainSQLiteClass.open();
        final SharedPreferences preferences = context.getSharedPreferences("PREFERENCE", context.MODE_PRIVATE);
        preferences.edit().clear().commit();

        int j = mainSQLiteClass.deleteMonthly();
        mainSQLiteClass.close();
    }
}
