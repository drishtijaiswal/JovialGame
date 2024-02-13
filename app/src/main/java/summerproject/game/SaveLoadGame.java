package summerproject.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SaveLoadGame extends AppCompatActivity {

    SaveDataManager fileLoader;
    RadioGroup slot;
    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_load_screen);

        fileLoader=new SaveDataManager(getApplicationContext());

        RadioButton slot1=(RadioButton)findViewById(R.id.slot1);
        RadioButton slot2=(RadioButton)findViewById(R.id.slot2);
        RadioButton slot3=(RadioButton)findViewById(R.id.slot3);

        slot1.setText(fileLoader.loadSaveInfo(1));
        slot2.setText(fileLoader.loadSaveInfo(2));
        slot3.setText(fileLoader.loadSaveInfo(3));

        Button save=(Button)findViewById(R.id.saveButton);
        Button load=(Button)findViewById(R.id.loadButton);

        Intent i=getIntent();
        Bundle b=i.getExtras();
        int state=b.getInt("State");
        if(state==1){
            load.setVisibility(View.VISIBLE);
            save.setVisibility(View.GONE);
        }
        if(state==2){
            player=b.getParcelable("Player");
            save.setVisibility(View.VISIBLE);
            load.setVisibility(View.GONE);
        }
    }

    public void load(View v){
        slot=(RadioGroup)findViewById(R.id.slotSelection);
        int selectedSlot=slot.getCheckedRadioButtonId();
        if(selectedSlot==R.id.slot1){
            player=fileLoader.loadSave(1);
        }else if(selectedSlot==R.id.slot2){
            player=fileLoader.loadSave(2);
        }else if(selectedSlot==R.id.slot3){
            player=fileLoader.loadSave(3);
        }else{
            Toast.makeText(getApplicationContext(),"Select a slot to load",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent startgame= new Intent(this,MainScreen.class);
        startgame.putExtra("Player",player);
        startActivity(startgame);
    }

    public void save(View v){
        slot=(RadioGroup)findViewById(R.id.slotSelection);
        int selectedSlot=slot.getCheckedRadioButtonId();
        int slot;
        if(selectedSlot==R.id.slot1){
            slot=1;
        }else if(selectedSlot==R.id.slot2){
            slot=2;
        }else if(selectedSlot==R.id.slot3){
            slot=3;
        }else{
            Toast.makeText(getApplicationContext(),"Select a slot to save",Toast.LENGTH_SHORT).show();
            return;
        }
        fileLoader.saveData(slot,player);
        finish();
    }
}
