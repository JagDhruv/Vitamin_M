package mark6.project.dhruv.jagrit.complete2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlazaro66.wheelindicatorview.WheelIndicatorItem;
import com.dlazaro66.wheelindicatorview.WheelIndicatorView;

/**
 * Created by JAGRIT on 31-07-2015.
 */
public class DailyC extends Fragment {

    TextView tvTotal,tvBills,tvClothing,tvElectronics,tvEntertainment,tvGrocery,tvOthers,tvTravel;
    MainSQLiteClass sqLiteClass;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dailyc, container, false);
        WheelIndicatorView wheelIndicatorView = (WheelIndicatorView) v.findViewById(R.id.wheel_indicator_view);

        sqLiteClass = new MainSQLiteClass(getActivity());
        sqLiteClass.open();
/*
        tvBills= (TextView) v.findViewById(R.id.daily_bils);
        tvClothing= (TextView) v.findViewById(R.id.daily_clothing);
        tvElectronics= (TextView) v.findViewById(R.id.daily_electronics);
        tvEntertainment= (TextView) v.findViewById(R.id.daily_entertainemnt);
        tvGrocery= (TextView) v.findViewById(R.id.daily_grocery);
        tvOthers= (TextView) v.findViewById(R.id.daily_others);
        tvTravel= (TextView) v.findViewById(R.id.daily_travel);

*/

        int total = sqLiteClass.addAllToday();
        int bills = sqLiteClass.addTodayBills();
        int clothes = sqLiteClass.addTodayClothing();
        int electronics = sqLiteClass.addTodayElectronics();
        int entertainment = sqLiteClass.addTodayEntertainment();
        int grocery = sqLiteClass.addTodayGrocery();
        int others = sqLiteClass.addTodayOthers();
        int travel = sqLiteClass.addTodayTravel();

/*
        tvTotal.setText(String.valueOf(total));
        tvBills.setText(String.valueOf(bills));
        tvClothing.setText(String.valueOf(clothes));
        tvElectronics.setText(String.valueOf(electronics));
        tvEntertainment.setText(String.valueOf(entertainment));
        tvGrocery.setText(String.valueOf(grocery));
        tvOthers.setText(String.valueOf(others));
        tvTravel.setText(String.valueOf(travel));

*/

        //dummy data
        int percentageOfExerciseDone = 100;
        float b = ((float)bills/total);
        float b1=((float)clothes/total);
        float b2=((float)electronics/total);
        float b3=((float)entertainment/total);
        float b4=((float)grocery/total);
        float b5=((float)others/total);
        float b6=((float)travel/total);

        float bb1 =(float) Math.round((b*10))/10;
        float bb2 =(float) Math.round((b1*10))/10;
        float bb3 =(float) Math.round((b2*10))/10;
        float bb4 =(float) Math.round((b3*10))/10;
        float bb5 =(float) Math.round((b4*10))/10;
        float bb6 =(float) Math.round((b5*10))/10;
        float bb7 =(float) Math.round((b6*10))/10;
/*
        tvTotal.setText(String.valueOf(bb1));
        tvBills.setText(String.valueOf(bb2));
        tvClothing.setText(String.valueOf(bb3));
        tvElectronics.setText(String.valueOf(bb4));
        tvEntertainment.setText(String.valueOf(bb5));
        tvGrocery.setText(String.valueOf(bb6));
        tvOthers.setText(String.valueOf(bb7));
*/
        /*
        tvTotal.setText(String.valueOf(b1));
        tvBills.setText(String.valueOf(b2));
        tvClothing.setText(String.valueOf(b3));
        tvElectronics.setText(String.valueOf(b4));
        tvEntertainment.setText(String.valueOf(b5));
        tvGrocery.setText(String.valueOf(b6));
        tvOthers.setText(String.valueOf(b));
*/
        wheelIndicatorView.setFilledPercent(percentageOfExerciseDone);



        WheelIndicatorItem Bills= new WheelIndicatorItem((float)bb1, Color.parseColor("#800080"));
        WheelIndicatorItem Clothing = new WheelIndicatorItem((float)bb2,Color.parseColor("#0000CD"));
        WheelIndicatorItem Electronics = new WheelIndicatorItem((float)bb3,Color.parseColor("#FFD700"));
        WheelIndicatorItem Entertainment = new WheelIndicatorItem((float)bb4,Color.parseColor("#03A9F4"));
        WheelIndicatorItem Grocery = new WheelIndicatorItem((float)bb5,Color.parseColor("#0000FF"));
        WheelIndicatorItem Others = new WheelIndicatorItem((float)bb6,Color.parseColor("#3F51B5"));
        WheelIndicatorItem Travel = new WheelIndicatorItem((float)bb7,Color.parseColor("#F44336"));





        wheelIndicatorView.addWheelIndicatorItem(Bills);
        wheelIndicatorView.addWheelIndicatorItem(Clothing);
        wheelIndicatorView.addWheelIndicatorItem(Electronics);
        wheelIndicatorView.addWheelIndicatorItem(Entertainment);
        wheelIndicatorView.addWheelIndicatorItem(Grocery);
        wheelIndicatorView.addWheelIndicatorItem(Others);
        wheelIndicatorView.addWheelIndicatorItem(Travel);

        wheelIndicatorView.startItemsAnimation();

        sqLiteClass.close();
        return v;
    }
}
