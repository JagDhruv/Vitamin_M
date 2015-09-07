package mark6.project.dhruv.jagrit.complete2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by SONY on 26-08-2015.
 */
public class Splash extends Activity {

    ImageView imageView;
    private Animation mAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        imageView = (ImageView) findViewById(R.id.imageView);
        mAnim= AnimationUtils.loadAnimation(this,R.anim.view_animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Splash.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            imageView.startAnimation(mAnim);

        }
    }
}
