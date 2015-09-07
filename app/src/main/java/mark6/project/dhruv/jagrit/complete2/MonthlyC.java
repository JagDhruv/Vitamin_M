package mark6.project.dhruv.jagrit.complete2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class MonthlyC extends Fragment {

    MainSQLiteClass sqLiteClass;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.monthlyc, container, false);
        WheelIndicatorView wheelIndicatorView = (WheelIndicatorView) v.findViewById(R.id.wheel_indicator_view);
        //dummy data
        sqLiteClass = new MainSQLiteClass(getActivity());
        sqLiteClass.open();



        int total = sqLiteClass.addAllMonthly();
        int grocery=sqLiteClass.addMonthlyGrocery();
        int clothes=sqLiteClass.addMonthlyClothing();
        int travel=sqLiteClass.addMonthlyTravel();
        int electronics=sqLiteClass.addMonthlyElectronics();
        int entertainment=sqLiteClass.addMonthlyEntertainment();
        int others=sqLiteClass.addMonthlyOther();
        int bills=sqLiteClass.addMonthlyBills();






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
        tvTotal.setText(String.valueOf(b1));
        tvBills.setText(String.valueOf(b2));
        tvClothing.setText(String.valueOf(b3));
        tvElectronics.setText(String.valueOf(b4));
        tvEntertainment.setText(String.valueOf(b5));
        tvGrocery.setText(String.valueOf(b6));
        tvOthers.setText(String.valueOf(b));
*/
        wheelIndicatorView.setFilledPercent(percentageOfExerciseDone);



        WheelIndicatorItem Bills= new WheelIndicatorItem((float)bb1
                , Color.parseColor("#800080"));
        WheelIndicatorItem Others = new WheelIndicatorItem((float)bb2,Color.parseColor("#0000CD"));
        WheelIndicatorItem Grocery = new WheelIndicatorItem((float)bb3,Color.parseColor("#FFD700"));
        WheelIndicatorItem Entertainment = new WheelIndicatorItem((float)bb4,Color.parseColor("#03A9F4"));
        WheelIndicatorItem Electronics = new WheelIndicatorItem((float)bb5,Color.parseColor("#0000FF"));
        WheelIndicatorItem Clothing = new WheelIndicatorItem((float)bb6,Color.parseColor("#3F51B5"));
        WheelIndicatorItem Travel = new WheelIndicatorItem((float)bb7,Color.parseColor("#F44336"));





        wheelIndicatorView.addWheelIndicatorItem(Bills);
        wheelIndicatorView.addWheelIndicatorItem(Others);
        wheelIndicatorView.addWheelIndicatorItem(Grocery);
        wheelIndicatorView.addWheelIndicatorItem(Entertainment);
        wheelIndicatorView.addWheelIndicatorItem(Electronics);
        wheelIndicatorView.addWheelIndicatorItem(Clothing);
        wheelIndicatorView.addWheelIndicatorItem(Travel);

        wheelIndicatorView.startItemsAnimation();

        sqLiteClass.close();
        return v;
    }
}
