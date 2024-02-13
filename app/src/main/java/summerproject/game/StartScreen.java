package summerproject.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class StartScreen extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.start_screen);
        Button startbut=(Button)findViewById(R.id.startbut);
    }
        public void onStartClick(View v) {
            if (v.getId() == R.id.startbut) {

                WeaponDataManager weapons=new WeaponDataManager(this);
                weapons.emptyCheck();
                startActivity(new Intent(StartScreen.this, MainMenu.class));
                finish();
            }
        }
}
