package summerproject.game;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Inventory extends AppCompatActivity {

    WeaponDataManager weaponDataManager;
    ListView weaponList;
    String[] weaponArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_screen);

        weaponDataManager=new WeaponDataManager(this);
        weaponList=(ListView)findViewById(R.id.wpnList);

        weaponArray= weaponDataManager.getWeaponList();
        for(int i=0;i<weaponArray.length;i++){
            if(weaponArray[i]!=null) {
                Log.d("WEAPON FOUND: ", weaponArray[i]);
            }else{
                weaponArray[i]="";
            }
        }

        ArrayAdapter<String> weaponArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,weaponArray);

        weaponList.setAdapter(weaponArrayAdapter);

        weaponList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!weaponArray[position].equals("")) {
                    String wpname=weaponArray[position];
                    MainScreen.changeWeapon(weaponDataManager.getWeapon(wpname));
                    finish();
                }
            }
        });
    }
}
