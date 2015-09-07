package mark6.project.dhruv.jagrit.complete2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by JAGRIT on 31-07-2015.
 */
public class
        ViewPagerAdapter  extends FragmentStatePagerAdapter{

    CharSequence Titles[];
    int NumbOfTabs;

    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbTabs) {
        super(fm);
        this.Titles=mTitles;
        this.NumbOfTabs=mNumbTabs;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new Fragment();
        if(position == 0){
            Daily daily = new Daily();
            fragment= daily;

        }
        if(position == 1){
            Weekly weekly = new Weekly();
            fragment =weekly;
        }
        if(position == 2){
            Monthly monthly = new Monthly();
            fragment = monthly;
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
