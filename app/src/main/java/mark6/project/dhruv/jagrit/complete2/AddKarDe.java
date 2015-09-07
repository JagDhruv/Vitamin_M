package mark6.project.dhruv.jagrit.complete2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by JAGRIT on 31-07-2015.
 */
public class AddKarDe extends Activity {


    FancyButton b1,b2,b3,b4,b5,b6 ,b7;
    EditText editText;

    MainSQLiteClass database;
    Button udaDe;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_kar_de);
       editText= (EditText) findViewById(R.id.editText);
        b1 = (FancyButton) findViewById(R.id.button);
        b2 = (FancyButton) findViewById(R.id.button2);
        b3 = (FancyButton) findViewById(R.id.button3);
        b4= (FancyButton) findViewById(R.id.button4);
        b5 = (FancyButton) findViewById(R.id.button8);
        b6=(FancyButton)findViewById(R.id.button9);
        b7 = (FancyButton) findViewById(R.id.button10);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Vitamin M");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        database = new MainSQLiteClass(this);




         b1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 database.open();
                 try {
                     String number = editText.getText().toString();
                     int asliNumber = Integer.parseInt(number);

                     /*
                     SharedPreferences preferences = getSharedPreferences("money", MODE_PRIVATE);
                     int  yo = preferences.getInt("save", 0);
                     int yoo = yo-asliNumber;
                     final SharedPreferences.Editor editor = preferences.edit();
                     editor.putInt("save",yoo);
                     editor.commit();
                     */
                     long i = database.insertGroceries(asliNumber);
                     database.close();
                     Intent intent = new Intent(AddKarDe.this, MainActivity.class);
                     finish();
                     startActivity(intent);



                 } catch (Exception e) {
                     Toast.makeText(AddKarDe.this, "You have to enter a number", Toast.LENGTH_SHORT).show();
                 }


             }
         });

         b2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 database.open();
                 try {
                     String number = editText.getText().toString();
                     int asliNumber = Integer.parseInt(number);


                     /*
                     SharedPreferences preferences = getSharedPreferences("money", MODE_PRIVATE);
                     int  yo = preferences.getInt("save", 0);
                     int yoo = yo-asliNumber;
                     final SharedPreferences.Editor editor = preferences.edit();
                     editor.putInt("save",yoo);
                     editor.commit();
                     */
                     long i = database.insertBills(asliNumber);

                     database.close();
                     Intent intent = new Intent(AddKarDe.this, MainActivity.class);
                     finish();
                     startActivity(intent);
                 } catch (Exception e) {
                     Toast.makeText(AddKarDe.this, "You have to enter a number", Toast.LENGTH_SHORT).show();
                 }

             }
         });

         b3.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 try {
                     database.open();
                     String number = editText.getText().toString();
                     int asliNumber = Integer.parseInt(number);
                     long i = database.insertClothing(asliNumber);


                     /*
                     SharedPreferences preferences = getSharedPreferences("money", MODE_PRIVATE);
                     int  yo = preferences.getInt("save", 0);
                     int yoo = yo-asliNumber;
                     final SharedPreferences.Editor editor = preferences.edit();
                     editor.putInt("save",yoo);
                     editor.commit();
                     */
                     database.close();
                     Intent intent = new Intent(AddKarDe.this, MainActivity.class);
                     finish();
                     startActivity(intent);
                 }

                 catch (Exception e){
                     Toast.makeText(AddKarDe.this, "You have to enter a number", Toast.LENGTH_SHORT).show();
                 }
             }
         });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    database.open();
                    String number = editText.getText().toString();
                    int aslinumber = Integer.parseInt(number);


                     /*
                     SharedPreferences preferences = getSharedPreferences("money", MODE_PRIVATE);
                     int  yo = preferences.getInt("save", 0);
                     int yoo = yo-asliNumber;
                     final SharedPreferences.Editor editor = preferences.edit();
                     editor.putInt("save",yoo);
                     editor.commit();
                     */
                    long i = database.insertEntertainment(aslinumber);
                    database.close();
                    Intent intent = new Intent(AddKarDe.this, MainActivity.class);
                    finish();
                    startActivity(intent);
                }
                catch (Exception e){
                    Toast.makeText(AddKarDe.this, "You have to enter a number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    database.open();
                    String number = editText.getText().toString();
                    int asliNumber = Integer.parseInt(number);


                     /*
                     SharedPreferences preferences = getSharedPreferences("money", MODE_PRIVATE);
                     int  yo = preferences.getInt("save", 0);
                     int yoo = yo-asliNumber;
                     final SharedPreferences.Editor editor = preferences.edit();
                     editor.putInt("save",yoo);
                     editor.commit();
                     */
                    long i = database.insertElectonics(asliNumber);

                    database.close();
                    Intent intent = new Intent(AddKarDe.this, MainActivity.class);
                    finish();
                    startActivity(intent);
                }
                catch (Exception e){
                    Toast.makeText(AddKarDe.this, "You have to enter a number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    database.open();
                    String number = editText.getText().toString();
                    int asliNumber = Integer.parseInt(number);


                     /*
                     SharedPreferences preferences = getSharedPreferences("money", MODE_PRIVATE);
                     int  yo = preferences.getInt("save", 0);
                     int yoo = yo-asliNumber;
                     final SharedPreferences.Editor editor = preferences.edit();
                     editor.putInt("save",yoo);
                     editor.commit();
                     */
                    long i = database.insertTravel(asliNumber);

                    database.close();
                    Intent intent = new Intent(AddKarDe.this, MainActivity.class);
                    finish();
                    startActivity(intent);
                }
                catch (Exception e){
                    Toast.makeText(AddKarDe.this, "You have to enter a number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    database.open();
                    String number = editText.getText().toString();
                    int asliNumber = Integer.parseInt(number);
                    long i = database.insertOthers(asliNumber);



                     /*
                     SharedPreferences preferences = getSharedPreferences("money", MODE_PRIVATE);
                     int  yo = preferences.getInt("save", 0);
                     int yoo = yo-asliNumber;
                     final SharedPreferences.Editor editor = preferences.edit();
                     editor.putInt("save",yoo);
                     editor.commit();
                     */
                    database.close();
                    Intent intent = new Intent(AddKarDe.this, MainActivity.class);
                    finish();
                    startActivity(intent);
                }
                catch (Exception e){
                    Toast.makeText(AddKarDe.this, "You have to enter a number", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
