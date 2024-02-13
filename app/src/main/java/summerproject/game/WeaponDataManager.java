package summerproject.game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class WeaponDataManager extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "weapons.db";
    private static final String TABLE_NAME = "Weapons";
    private static final String WPNAME = "wpname";
    private static final String WPCLASS = "wpclass";
    private static final String BASEDAM = "basedam";
    private static final String FLUXDAM = "fluxdam";
    private static final String BASESPUSE = "basespuse";
    private static final String ACC = "acc";
    private static final String CRITCHANCE = "critchance";
    private static final String CRITMOD = "critmod";
    private static final String RANGE = "range";
    private static final String SPBASEDAM = "spbasedam";
    private static final String SPFLUXDAM = "spfluxdam";
    private static final String SPUSE = "spuse";
    private static final String SPRANGE = "sprange";
    private static final String WPNID = "wpnid";

    SQLiteDatabase db;
    private static final String TABLE_CREATE = "Create table "+TABLE_NAME+" ("+WPNAME+" text primary key, "+WPCLASS+" text, "+BASEDAM+" int, "+FLUXDAM+" int, "+BASESPUSE+" int, "+ACC+" int, "+
            CRITCHANCE+" int, "+CRITMOD+" int, "+RANGE+" int, "+SPBASEDAM+" int, "+SPFLUXDAM+" int, "+SPUSE+" int, "+SPRANGE+" int, "+WPNID+" text);";

    public WeaponDataManager(Context context)
    {
        super(context , DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("Drop table Weapons;");
        db.execSQL(TABLE_CREATE);
        this.db=db;
        Log.d("CREATE WPN TABLE: ",TABLE_CREATE);
        initWeaponsTable();
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
            initWeaponsTable();
        }
        cursor.close();
        db.close();

    }


    public void addWeapon(String name, String wpclass, int basedmg,int fluxdmg, int basespuse, int acc,int critch,int critmod,int range,int spdmg,int spfluxdmg,int spuse,int sprange, String wpnid)
    {
        ContentValues values = new ContentValues();

        values.put(WPNAME,name);
        values.put(WPCLASS,wpclass);
        values.put(BASEDAM,basedmg);
        values.put(FLUXDAM,fluxdmg);
        values.put(BASESPUSE,basespuse);
        values.put(ACC,acc);
        values.put(CRITCHANCE,critch);
        values.put(CRITMOD,critmod);
        values.put(RANGE,range);
        values.put(SPBASEDAM,spdmg);
        values.put(SPFLUXDAM,spfluxdmg);
        values.put(SPUSE,spuse);
        values.put(SPRANGE,sprange);
        values.put(WPNID,wpnid);


        db.insert(TABLE_NAME , null, values);
    }

    public void initWeaponsTable(){

//        db = this.getWritableDatabase();
        addWeapon("Wooden sword","Knight",10,2,5,100,0,100,2,15,3,10,1,"wooden_sword");
        addWeapon("Slingshot","Ranger",4,1,8,95,5,150,3,10,5,25,3,"slingshot");
        addWeapon("Bent staff","Mage",2,1,10,95,0,100,2,20,10,25,5,"bent_staff");
        addWeapon("Sturdy sword","Knight",18,7,15,95,5,150,2,35,15,35,1,"sturdy_sword");
        addWeapon("Supple bow","Ranger",10,5,16,90,10,200,4,20,5,50,6,"supple_bow");
        addWeapon("Arcane staff","Mage",10,5,22,95,5,130,2,45,10,65,5,"arcane_staff");
        addWeapon("Laevateinn","Knight",35,7,25,96,8,180,2,77,0,40,2,"laevateinn");
        addWeapon("Mistletoe Bow","Ranger",25,3,20,98,25,300,4,65,10,100,7,"mistletoe");
        addWeapon("Branch of Yggdrasil","Mage",15,3,0,85,5,160,2,180,50,200,8,"yggdrasil");

        Log.d("WEAPONS: ","Weapons Added");
//        db.close();
    }

    public Weapon getWeapon(String name){

        db = this.getReadableDatabase();
        Weapon weapon;
        String query = "Select * from "+TABLE_NAME+" where "+WPNAME+"='"+name+"';";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            weapon = new Weapon(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9), cursor.getInt(10), cursor.getInt(11), cursor.getInt(12), cursor.getString(13));
            cursor.close();
            db.close();
            return weapon;
        }else{
            Log.d("WEAPON SEARCH: ","Not found");
            cursor.close();
            db.close();
            return null;
        }
    }

    public String[] getWeaponList(){
        db=this.getReadableDatabase();
        String[] WeaponList=new String[10];
        String query="Select * from "+TABLE_NAME+";";
        Cursor cursor = db.rawQuery(query, null);
        String info="--";
        int i=0;
        while (cursor.moveToNext()){
            WeaponList[i]=cursor.getString(cursor.getColumnIndex(WPNAME));
            //            WeaponList[i]=getWeapon(cursor.getString(cursor.getColumnIndex(WPNAME))).toString();
            i++;
        }
        return WeaponList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
