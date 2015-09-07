package mark6.project.dhruv.jagrit.complete2;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Calendar;

import at.markushi.ui.util.UiHelper;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by JAGRIT on 01-08-2015.
 */
public class FirstLaunch extends Activity{
    Toolbar toolbar;

EditText editText;
    FancyButton button;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_launch);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("VITAMIN M");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
editText = (EditText) findViewById(R.id.text);
        button = (FancyButton) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    //Get value of expenditure
                    SharedPreferences preferences = getSharedPreferences("money",MODE_PRIVATE);
                    String text = editText.getText().toString();
                    int num = Integer.parseInt(text);

                    final SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("save",num);
                    editor.commit();
                    Intent intent = new Intent(FirstLaunch.this,MainActivity.class);
                    finish();
                    startActivity(intent);
                }
                catch (Exception e){
                    Toast.makeText(FirstLaunch.this, "Enter a value", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


            finish();
            //moveTaskToBack(true);
        //System.exit(0);



        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }
}
