package summerproject.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        TextView gravetxt=(TextView)findViewById(R.id.gravetxt);
        Button restart=(Button)findViewById(R.id.restartbutton);


        Intent i=getIntent();
        int win=i.getIntExtra("WIN",0);

        gravetxt.setText("Here lies "+MainScreen.player.name);

        if(win==1){
            gravetxt.setText("Here lies the tyrant Black Emperor\nSlain by the great hero\n"+MainScreen.player.name);
            restart.setText("CONGRATULATIONS!");
        }

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainMenu.class);
                startActivity(i);
            }
        });
    }
}
