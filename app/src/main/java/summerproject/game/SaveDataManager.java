package summerproject.game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SaveDataManager extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "saves.db";
    private static final String TABLE_NAME = "Saves";
    private static final String SLOT = "slot";
    private static final String PCNAME = "pcname";
    private static final String PCCLASS = "pcclass";
    private static final String CURHP = "curhp";
    private static final String MAXHP = "maxhp";
    private static final String CURSP = "cursp";
    private static final String MAXSP = "maxsp";
    private static final String LVL = "level";
    private static final String XP = "xp";
    private static final String STAGE = "stage";
    private static final String POS = "pos";

    WeaponDataManager weaponManager;

    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table Saves (slot integer primary key, pcname text, pcclass text, curhp integer, maxhp double, cursp integer, maxsp double, level integer, xp integer, stage integer, pos integer);";

    public SaveDataManager(Context context)
    {
        super(context , DATABASE_NAME, null, DATABASE_VERSION);
        weaponManager=new WeaponDataManager(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;
    }

    public void saveData(int slot, Player player)
    {
        db=this.getReadableDatabase();
        String query = "select * from Saves where slot="+slot;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            String deleteQuery = "delete from Saves where slot="+slot;
            db.execSQL(deleteQuery);
        }
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SLOT,slot);
        values.put(PCNAME,player.name);
        values.put(PCCLASS,player.pcclass);
        values.put(CURHP,player.chp);
        values.put(MAXHP,player.mhp);
        values.put(CURSP,player.csp);
        values.put(MAXSP,player.msp);
        values.put(LVL,player.lvl);
        values.put(XP,player.xp);
        values.put(STAGE,player.stage);
        values.put(POS,player.pos);

        db.insert(TABLE_NAME , null, values);
        db.close();
    }

    public String loadSaveInfo(int slot){
        db = this.getReadableDatabase();
        String query = "select pcname, curhp, maxhp, cursp, maxsp, stage from Saves where slot="+slot;
        Cursor cursor = db.rawQuery(query, null);
        String info="Save file blank";
        if (cursor != null && cursor.moveToFirst()) {
                info = "SAVE: " + cursor.getString(0) +" HP "+ cursor.getInt(1) +"/"+ cursor.getInt(2) +" SP "+ cursor.getInt(3) +"/"+ cursor.getInt(4)+" Stage: "+cursor.getInt(5);
        }
        Log.d("SAVELOADER: ",info+cursor.getCount());
        db.close();
        return info;
    }

    public Player loadSave(int slot){
        Player player=new Player("Default Joe","Debug Monkey",100,100,100,100,1,1,1,70);

        db=this.getReadableDatabase();
        String query = "select pcname, pcclass, curhp, maxhp, cursp, maxsp, level, xp, stage, pos from Saves where slot="+slot;
        String info="";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            player = new Player(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getFloat(3), cursor.getInt(4), cursor.getFloat(5), cursor.getInt(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9));
            info = "SAVE: " + cursor.getString(0) +" "+ cursor.getInt(1) +" "+ cursor.getInt(2) +" "+ cursor.getInt(3) +" "+ cursor.getInt(4);
        }
        Log.d("Player loaded: ",info);
        db.close();
        return player;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String query = "DROP TABLE IF EXISTS"+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
