package mark6.project.dhruv.jagrit.complete2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by JAGRIT on 01-08-2015.
 */
public class Entry extends Activity {
    Toolbar toolbar;
    Button button;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.setTitle("ENTER MONEY");

        button = (Button) findViewById(R.id.buttonok);
        editText= (EditText) findViewById(R.id.editText);

    }
}
