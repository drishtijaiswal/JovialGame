package summerproject.game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EnemyDataManager extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "enemies.db";
    private static final String TABLE_NAME = "Enemies";
    private static final String ENNAME = "enname";
    private static final String ENHP = "enhp";
    private static final String ENMOVE = "enmove";
    private static final String ENATK1 = "enatk1";
    private static final String ENDAM1 = "endam1";
    private static final String ENATK2 = "enatk2";
    private static final String ENDAM2 = "endam2";
    private static final String ENATK3 = "enatk3";
    private static final String ENDAM3 = "endam3";
    private static final String ENSUPPATK = "ensuppatk";
    private static final String ENSUPPHEAL = "ensuppheal";
    private static final String ENACC = "enacc";
    private static final String ENRANGE = "enrange";
    private static final String ENEXP = "enexp";
    private static final String ENTYPE = "entype";
    private static final String ENIMG = "enimg";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "Create table "+TABLE_NAME+" ('"+ENNAME+"' text primary key, "+ENHP+" int, "+ENMOVE+" int, '"
            +ENATK1+"' text, "+ENDAM1+" int, '"+ENATK2+"' text, "+ENDAM2+" int, '"+ ENATK3+"' text, "+ENDAM3+" int, '"+ENSUPPATK+"' text, "+ENSUPPHEAL+" int, "
            +ENACC+" int, "+ENRANGE+" int, "+ENEXP+" int, '"+ENTYPE+"' text, '"+ENIMG+"' text);";

    public EnemyDataManager(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        emptyCheck();
    }

    public void addEnemy(String enname,int enhp,int enmove,String atk1,int dam1,String atk2,int dam2,String atk3,int dam3,String suppatk, int suppheal,int enacc,int enrange,int enexp,String entype, String enimg){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ENNAME,enname);
        values.put(ENHP,enhp);
        values.put(ENMOVE,enmove);
        values.put(ENATK1,atk1);
        values.put(ENDAM1,dam1);
        values.put(ENATK2,atk2);
        values.put(ENDAM2,dam2);
        values.put(ENATK3,atk3);
        values.put(ENDAM3,dam3);
        values.put(ENSUPPATK,suppatk);
        values.put(ENSUPPHEAL,suppheal);
        values.put(ENACC,enacc);
        values.put(ENRANGE,enrange);
        values.put(ENEXP,enexp);
        values.put(ENTYPE,entype);
        values.put(ENIMG,enimg);

        db.insert(TABLE_NAME , null, values);
        db.close();

    }

    public void initEnemiesTable(){

        addEnemy("Skeleton",15,1,"Sword Swing",6,"Lunging Slice",8,"Rolling Slice",12,"Bone Mender",-5,75,2,30,"Follow","skeleton");
        addEnemy("Dead Nurse",8,1,"Last Aid",3,"Last Aid",3,"Last Aid",3,"Undead Stitch",-7,50,1,40,"Follow","deadnurse");
        addEnemy("Royal Archer",15,0,"Quick Draw",4,"Full Draw",6,"Gamble Draw",12,"Splinter Arrow",15,60,5,65,"Guard","royalarcher");
        addEnemy("Dragon Hound",35,2,"Lunging Bite",6,"Savage Bite",12,"Throat Cutter",20,"Whimper",-2,60,1,175,"Follow","dragonhound");
        addEnemy("Anubis Guardian",120,0,"Palm Smash",8,"Black Hand",14,"Jackal Blade",35,"Valiant Effort",-8,55,1,450,"Guard","anguard");
        addEnemy("Black Emperor",666,1,"Royal Decree",15,"Imperial Sceptre",28,"Banish",66,"Dark Soul",99,45,1,666,"Follow","finalboss");

        db = this.getReadableDatabase();
        String query = "Select * from "+TABLE_NAME+";";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            while(cursor.moveToNext())
            Log.d("CURSOR DETAILS:",cursor.getInt(cursor.getColumnIndex(ENHP))+cursor.getInt(cursor.getColumnIndex(ENMOVE))+ cursor.getInt(cursor.getColumnIndex(ENACC))+cursor.getInt(cursor.getColumnIndex(ENRANGE))+cursor.getInt(cursor.getColumnIndex(ENEXP))+cursor.getString(cursor.getColumnIndex(ENTYPE))+cursor.getString(cursor.getColumnIndex(ENIMG)));
        }
    }

    public Enemy getEnemy(String enID){

        db = this.getReadableDatabase();
        Enemy enemy=new Enemy();
        String query = "Select * from "+TABLE_NAME+" where "+ENIMG+"='"+enID+"';";
        Log.d("SET ENEMY:",query);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            String[] atk=new String[]{cursor.getString(cursor.getColumnIndex(ENATK1)),
                    cursor.getString(cursor.getColumnIndex(ENATK2)),
                    cursor.getString(cursor.getColumnIndex(ENATK3)),
                    cursor.getString(cursor.getColumnIndex(ENSUPPATK))};

            int[] dmg=new int[]{cursor.getInt(cursor.getColumnIndex(ENDAM1)),
                    cursor.getInt(cursor.getColumnIndex(ENDAM2)),
                    cursor.getInt(cursor.getColumnIndex(ENDAM3)),
                    cursor.getInt(cursor.getColumnIndex(ENSUPPHEAL))};

            enemy = new Enemy(cursor.getString(cursor.getColumnIndex(ENNAME)),
                    cursor.getInt(cursor.getColumnIndex(ENHP)),
                    cursor.getInt(cursor.getColumnIndex(ENMOVE)),
                    atk,
                    dmg,
                    cursor.getInt(cursor.getColumnIndex(ENACC)),
                    cursor.getInt(cursor.getColumnIndex(ENRANGE)),
                    cursor.getInt(cursor.getColumnIndex(ENEXP)),
                    cursor.getString(cursor.getColumnIndex(ENTYPE)),
                    cursor.getString(cursor.getColumnIndex(ENIMG)));
        }
        Log.d("CURSOR DETAILS:",cursor.getInt(cursor.getColumnIndex(ENHP))+" "+cursor.getInt(cursor.getColumnIndex(ENMOVE))+" "
                + cursor.getInt(cursor.getColumnIndex(ENACC))+" "+cursor.getInt(cursor.getColumnIndex(ENRANGE))+" "
                + cursor.getInt(cursor.getColumnIndex(ENEXP))+" "+cursor.getString(cursor.getColumnIndex(ENTYPE))+" "
                +cursor.getString(cursor.getColumnIndex(ENIMG)));
        db.close();
        return enemy;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("Drop table Saves;");
        db.execSQL(TABLE_CREATE);
        this.db=db;
        Log.d("CREATE ENEMIES TABLE: ",TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void emptyCheck()
    {
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst())
        {
            return;
        }
        else
        {
            initEnemiesTable();
        }
        db.close();
    }
}
