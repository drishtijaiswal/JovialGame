package summerproject.game;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainScreen extends Activity{

    public static BGGrid grid;
    public static Player player;
    public static Weapon weapon;
    public WeaponDataManager weaponDataManager;

    static int MOVES;
    static int turns;

    ProgressBar HPbar;
    ProgressBar SPbar;

    static Button atkButton;
    static Button spButton;

    static Resources res;
    static Context ctx;

    static TextView HPtxt;
    static TextView SPtxt;
    static TextView movesCounter;
    static ImageView wpnimg;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_screen);

        res=getResources();
        ctx=getApplicationContext();

        atkButton=(Button)findViewById(R.id.atkbutton);
        spButton=(Button)findViewById(R.id.spbutton);

        HPbar=(ProgressBar)findViewById(R.id.hpBar);
        SPbar=(ProgressBar)findViewById(R.id.spBar);

        HPtxt=(TextView)findViewById(R.id.hpLabel);
        SPtxt=(TextView)findViewById(R.id.spLabel);
        wpnimg=(ImageView)findViewById(R.id.wpnimg);

        movesCounter=(TextView)findViewById(R.id.movesCounter);

        Intent i=getIntent();
        Bundle b=i.getExtras();
        player=b.getParcelable("Player");

        HPbar.setMax((int) player.mhp);
        HPbar.setProgress(player.chp);
        SPbar.setMax((int) player.msp);
        SPbar.setProgress(player.csp);

        weapon=new Weapon("Wooden sword","Sword",10,2,5,100,0,1,2,15,3,10,1,"wooden_sword");
        weaponDataManager=new WeaponDataManager(this);

        String wpn_img_id="wooden_sword";
        int resId = getResources().getIdentifier(wpn_img_id,"drawable", getPackageName());
        wpnimg.setImageDrawable(ContextCompat.getDrawable(this,resId));

        String pcclass=player.pcclass;
        if(pcclass.equals("Knight")){
            MOVES=2;
        }else if(pcclass.equals("Ranger")){
            MOVES=3;
        }else if(pcclass.equals("Mage")){
            MOVES=1;
        }
        turns=MOVES;
        movesCounter.setText(Integer.toString(turns));
        grid=(BGGrid)findViewById(R.id.gridbg);
        grid.setStage(player.stage);
        grid.map.zoneID=player.stage;
        grid.setPlayer(player,player.pos/10,player.pos%10);
        updateStatus();
        Log.d("PLAYERSTATS: "," Got player data:\nName:"+player.name+"\nHP:"+player.chp+"/"+player.mhp+"\nSP:"+player.csp+"/"+player.msp);
    }




    public void move(View v){
        if(turns>0) {
            if (v == findViewById(R.id.ubut)) {
                grid.moveUp();
            } else if (v == findViewById(R.id.dbut)) {
                grid.moveDown();
            } else if (v == findViewById(R.id.lbut)) {
                grid.moveLeft();
            } else if (v == findViewById(R.id.rbut)) {
                grid.moveRight();
            }
            turns--;
        }
        player.csp+=(player.msp*5)/100;
        grid.clearSFX();
        grid.invalidate();
        updateStatus();
    }

    public void attack(View v){
        if(turns>0) {
            player.csp-=weapon.basespuse;
            grid.atkRange(weapon.range);
            turns--;
        }
        updateStatus();
    }

    public static void enemyTurn(){
        grid.clearSFX();
        grid.blockMap();
        grid.moveEnemy();
        turns=MOVES;
    }

    public void spATK(View v){
        if(turns>0) {
            player.csp-=weapon.spuse;
            grid.spatkRange(weapon.sprange);
            turns--;
        }
        updateStatus();
    }

    public void saveGame(View v){

        player=grid.player;
        player.prepareSavable(player.chp,player.csp,grid.pcRow,grid.pcCol,grid.map.zoneID);
        Intent startgame= new Intent(this,SaveLoadGame.class);
        Bundle extras=new Bundle();
        extras.putInt("State",2);
        extras.putParcelable("Player",player);
        startgame.putExtras(extras);
        startActivity(startgame);
    }

    public void checkInventory(View v){
        Intent inventory= new Intent(this,Inventory.class);
        startActivity(inventory);
    }

    public static void changeWeapon(Weapon wpn){
        weapon=wpn;
        String wpn_img_id=wpn.wpnid;
        int resId = res.getIdentifier(wpn_img_id,"drawable",ctx.getPackageName());
        wpnimg.setImageDrawable(ContextCompat.getDrawable(ctx,resId));
    }

    public static void updateStatus(){

        if(player.csp<weapon.basespuse){
            atkButton.setEnabled(false);
        }else {
            atkButton.setEnabled(true);
        }

        if(player.csp<weapon.spuse){
            spButton.setEnabled(false);
        }else {
            spButton.setEnabled(true);
        }
        if(player.csp>player.msp){
            player.csp=(int)player.msp;
        }
        if(player.chp>player.mhp){
            player.chp=(int)player.mhp;
        }


        movesCounter.setText(Integer.toString(turns));
        HPtxt.setText(grid.player.chp+"/"+(int)grid.player.mhp+" HP");
        SPtxt.setText(grid.player.csp+"/"+(int)grid.player.msp+" SP");
    }

}
