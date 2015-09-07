package mark6.project.dhruv.jagrit.complete2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by JAGRIT on 31-07-2015.
 */
public class ViewPagerAdapterC extends FragmentStatePagerAdapter {
    CharSequence Titles[];
    int NumbOfTabs;

    public ViewPagerAdapterC(FragmentManager fm, CharSequence mTitles[], int mNumbTabs) {
        super(fm);
        this.Titles=mTitles;
        this.NumbOfTabs=mNumbTabs;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new Fragment();
        if(position == 0){
            DailyC dailyc = new DailyC();
            fragment= dailyc;

        }
        if(position == 1){
            WeeklyC weeklyc = new WeeklyC();
            fragment =weeklyc;
        }
        if(position == 2){
            MonthlyC monthlyc = new MonthlyC();
            fragment = monthlyc;
        }
        return fragment;
    }


    @Override
    public int getCount() {
        return NumbOfTabs;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }
}
