package mark6.project.dhruv.jagrit.complete2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;

import com.dlazaro66.wheelindicatorview.WheelIndicatorItem;
import com.dlazaro66.wheelindicatorview.WheelIndicatorView;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by JAGRIT on 31-07-2015.
 */
public class MainActivityC extends AppCompatActivity implements MaterialTabListener {
    MaterialTabHost tabHost;
    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapterC adapter;
    CharSequence Titles[]={"DAILY","WEEKLY","MONTHLY"};
    int Numboftabs =3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainc);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        tabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);

        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new ViewPagerAdapterC(getSupportFragmentManager(),Titles,Numboftabs);
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


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent i = new Intent(MainActivityC.this,MainActivity.class);
            finish();
            startActivity(i);
        }
        return super.onKeyDown(keyCode, event);
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
}
