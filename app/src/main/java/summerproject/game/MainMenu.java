package summerproject.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
    }

    public void charCreate(View view){
        Intent i= new Intent(this,CharCreation.class);
        startActivity(i);
    }

    public void loadGame(View view){
        Intent i= new Intent(this,SaveLoadGame.class);
        i.putExtra("State",1);
        startActivity(i);
    }
}
