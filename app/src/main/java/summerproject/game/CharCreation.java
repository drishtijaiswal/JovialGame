package summerproject.game;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CharCreation extends AppCompatActivity {

    EditText nametxt;
    EditText hptxt;
    EditText sptxt;
    RadioGroup classbutton;
    Button startbutton;
    Button setSPbutton;
    SaveDataManager savefile;
    WeaponDataManager weaponDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charcreation);

        savefile=new SaveDataManager(getApplicationContext());
        weaponDataManager=new WeaponDataManager(getApplicationContext());


        nametxt=(EditText)findViewById(R.id.charnametxt);
        hptxt=(EditText)findViewById(R.id.charHPtxt);
        sptxt=(EditText)findViewById(R.id.charSPtxt);
        classbutton=(RadioGroup)findViewById(R.id.charclassselection);
        startbutton=(Button)findViewById(R.id.startnewgamebutton);
        setSPbutton=(Button)findViewById(R.id.calcSPbutton);
        classbutton.check(R.id.charknightchoice);
    }

    public void onStartClick(View v) {

        int classSelected=classbutton.getCheckedRadioButtonId();
        int charHP=Integer.parseInt(hptxt.getText().toString());


        int charSP=Integer.parseInt(sptxt.getText().toString());


        String pcclass="Knight";
        if(classSelected==R.id.charknightchoice){
            pcclass="Knight";
        } else if(classSelected==R.id.charrangerchoice){
            pcclass="Ranger";
        } else if(classSelected==R.id.charmagechoice){
            pcclass="Mage";
        }

        setSPbutton.callOnClick();


        String name=nametxt.getText().toString();
        if(name.equals("")){
            Toast.makeText(getApplicationContext(),"Enter a name",Toast.LENGTH_SHORT).show();
        }
        else {
            if(charHP==42){
                charHP=9999;
                charSP=9999;
            }

            Player player=new Player(name,pcclass,charHP,(double)charHP,charSP,(double)charSP,1,0,0,41);
            Intent startgame= new Intent(this,MainScreen.class);
            startgame.putExtra("Player",player);
            startActivity(startgame);
            finish();
        }
    }

    public void calcSP(View v){
        int charHP=Integer.parseInt(hptxt.getText().toString());

        if(charHP>200){
            charHP=200;
        } else if(charHP<=10){
            charHP=10;
        }

        int charSP=200-charHP;
        if(charHP<=40){
            charSP=200-(charHP/2);
        }

        hptxt.setText(Integer.toString(charHP));
        sptxt.setText(Integer.toString(charSP));
        startbutton.setEnabled(true);


    }
}
