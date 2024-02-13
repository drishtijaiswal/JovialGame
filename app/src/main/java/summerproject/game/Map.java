package summerproject.game;

import android.util.Log;
import android.widget.Toast;

public class Map {
    int zoneID=0;

    Zone z1=new Zone();
    Zone z2=new Zone();
    Zone z3=new Zone();
    Zone z4=new Zone();
    Zone z5=new Zone();
    Zone z6=new Zone();
    Zone z7=new Zone();
    Zone z8=new Zone();
    Zone z9=new Zone();

    public static Zone[] zoneMap;

    public Map(){
        zoneMap=new Zone[]{z1,z2,z3,z4,z5,z6,z7,z8,z9};

        initZones();
    }

    public int[][] getZoneBG(int direction){
        if(direction=='L'){
            if(zoneID==0) {
                Log.d("Location: ","Can't go more left, id="+zoneID);
                return zoneMap[zoneID].bgTiles;
            }
            else{
                Log.d("Location: ","Going left, id="+zoneID);
                zoneID--;
                return zoneMap[zoneID].bgTiles;
            }
        } else if(direction=='R'){
            if(zoneID==(zoneMap.length-1)) {
                Log.d("Location: ","Can't go more right, id="+zoneID);
                return zoneMap[zoneID].bgTiles;
            }
            else{
                Log.d("Location: ","Going right, id="+zoneID);
                zoneID++;
                return zoneMap[zoneID].bgTiles;
            }
        }
        Log.d("ZONE FAILURE: ","Could not fetch bgTiles");
        return zoneMap[zoneID].bgTiles;
    }

    public int[][] getZoneUnits(int direction){
        return zoneMap[zoneID].unitPos;
    }


    class Zone{
        public int[][] bgTiles;
        public int[][] unitPos;
    }

    public void initZones(){


        z1.bgTiles=new int[][]{
                {R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1},
                {R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.sand1,R.drawable.wood1,R.drawable.wood1,R.drawable.wood1,R.drawable.wood1,R.drawable.sand1,R.drawable.sand1},
                {R.drawable.water1,R.drawable.water1,R.drawable.sand1, R.drawable.sand1,R.drawable.wood1,R.drawable.sand1,R.drawable.sand1,R.drawable.wood1,R.drawable.sand1,R.drawable.sand1},
                {R.drawable.water1,R.drawable.sand1, R.drawable.sand1, R.drawable.sand1,R.drawable.wood1,R.drawable.wood1,R.drawable.sand1,R.drawable.wood1,R.drawable.sand1,R.drawable.sand1},
                {R.drawable.water1,R.drawable.sand1, R.drawable.sand1, R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1},
                {R.drawable.water1,R.drawable.water1,R.drawable.sand1, R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1},
                {R.drawable.water1,R.drawable.water1,R.drawable.sand1, R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1},
                {R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1}};

        z2.bgTiles=new int[][]{
                {R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1},
                {R.drawable.grass1,R.drawable.wood1, R.drawable.wood1, R.drawable.wood1, R.drawable.grass1,R.drawable.wood1, R.drawable.wood1, R.drawable.wood1, R.drawable.grass1,R.drawable.grass1},
                {R.drawable.sand1, R.drawable.wood1, R.drawable.sand1, R.drawable.wood1, R.drawable.sand1, R.drawable.wood1, R.drawable.sand1, R.drawable.wood1, R.drawable.sand1, R.drawable.sand1},
                {R.drawable.sand1, R.drawable.wood1, R.drawable.sand1, R.drawable.wood1, R.drawable.sand1, R.drawable.wood1, R.drawable.sand1, R.drawable.wood1, R.drawable.sand1, R.drawable.sand1},
                {R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1},
                {R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1},
                {R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1},
                {R.drawable.grass1,R.drawable.grass1,R.drawable.sand1, R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1}};


        z3.bgTiles=new int[][]{
                {R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1},
                {R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1},
                {R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1},
                {R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1},
                {R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1},
                {R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1},
                {R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1},
                {R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1}};

        z4.bgTiles=new int[][]{
                {R.drawable.grass1,R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1},
                {R.drawable.grass1,R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1},
                {R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.stone1,R.drawable.stone1},
                {R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.stone1,R.drawable.stone1},
                {R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.stone1,R.drawable.stone1},
                {R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.sand1, R.drawable.stone1,R.drawable.stone1},
                {R.drawable.grass1,R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1},
                {R.drawable.grass1,R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1}};

        z5.bgTiles=new int[][]{
                {R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.stone1},
                {R.drawable.wall1, R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.wall1, R.drawable.stone1},
                {R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.wall1, R.drawable.stone1},
                {R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.wall1, R.drawable.stone1},
                {R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.wall1, R.drawable.wall1, R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1},
                {R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.wall1, R.drawable.wall1, R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1},
                {R.drawable.wall1, R.drawable.stone1,R.drawable.stone1,R.drawable.wall1, R.drawable.wall1, R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1},
                {R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.stone1}};

        z6.bgTiles=new int[][]{
                {R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1},
                {R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1},
                {R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1},
                {R.drawable.stone1,R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.stone1},
                {R.drawable.stone1,R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.stone1},
                {R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1},
                {R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1},
                {R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1}};

        z7.bgTiles=new int[][]{
                {R.drawable.stone1,R.drawable.stone1,R.drawable.wall1, R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.wall1, R.drawable.stone1,R.drawable.stone1,R.drawable.stone1},
                {R.drawable.stone1,R.drawable.stone1,R.drawable.wall1, R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.wall1, R.drawable.stone1,R.drawable.stone1,R.drawable.stone1},
                {R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1},
                {R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1},
                {R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1},
                {R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1},
                {R.drawable.stone1,R.drawable.stone1,R.drawable.wall1, R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.wall1, R.drawable.stone1,R.drawable.stone1,R.drawable.stone1},
                {R.drawable.stone1,R.drawable.stone1,R.drawable.wall1, R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.wall1, R.drawable.stone1,R.drawable.stone1,R.drawable.stone1}};

        z8.bgTiles=new int[][]{
                {R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.stone1,R.drawable.stone1,R.drawable.wall1},
                {R.drawable.stone1,R.drawable.wall1, R.drawable.stone1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.stone1,R.drawable.wall1, R.drawable.wall1},
                {R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.stone1,R.drawable.stone1,R.drawable.wall1},
                {R.drawable.stone1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.stone1},
                {R.drawable.stone1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.grass1,R.drawable.stone1},
                {R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.stone1,R.drawable.stone1,R.drawable.wall1},
                {R.drawable.stone1,R.drawable.wall1, R.drawable.stone1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.stone1,R.drawable.wall1, R.drawable.wall1},
                {R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.stone1,R.drawable.stone1,R.drawable.wall1}};

        z9.bgTiles=new int[][]{
                {R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.stone1, R.drawable.stone1, R.drawable.stone1, R.drawable.stone1, R.drawable.stone1, R.drawable.wall1, R.drawable.wall1},
                {R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.stone1, R.drawable.wall1,  R.drawable.wall1,  R.drawable.wall1,  R.drawable.stone1, R.drawable.wall1, R.drawable.wall1},
                {R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.stone1, R.drawable.wall1,  R.drawable.wall1,  R.drawable.wall1,  R.drawable.stone1, R.drawable.wall1, R.drawable.wall1},
                {R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1, R.drawable.stone1, R.drawable.stone1, R.drawable.stone1, R.drawable.stone1, R.drawable.stone1,R.drawable.stone1},
                {R.drawable.stone1,R.drawable.stone1,R.drawable.stone1,R.drawable.stone1, R.drawable.stone1, R.drawable.stone1, R.drawable.stone1, R.drawable.stone1, R.drawable.stone1,R.drawable.stone1},
                {R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.stone1, R.drawable.wall1,  R.drawable.wall1,  R.drawable.wall1,  R.drawable.stone1, R.drawable.wall1, R.drawable.wall1},
                {R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.stone1, R.drawable.wall1,  R.drawable.wall1,  R.drawable.wall1,  R.drawable.stone1, R.drawable.wall1, R.drawable.wall1},
                {R.drawable.wall1, R.drawable.wall1, R.drawable.wall1, R.drawable.stone1, R.drawable.stone1, R.drawable.stone1, R.drawable.stone1, R.drawable.stone1, R.drawable.wall1, R.drawable.wall1}};

//        z1.bgTiles=new int[][]{
//                {R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1},
//                {R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.wood1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1},
//                {R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.wood1,R.drawable.wood1,R.drawable.wood1,R.drawable.wood1,R.drawable.wood1,R.drawable.sand1,R.drawable.sand1},
//
//                {R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1},
//                {R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.wood1,R.drawable.wood1,R.drawable.wood1,R.drawable.sand1,R.drawable.wood1,R.drawable.wood1,R.drawable.sand1},
//
//                {R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.wood1,R.drawable.wood1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1},
//                {R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.wood1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1},
//                {R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1}};

        int s=R.drawable.skeleton;
        int n=R.drawable.deadnurse;
        int a=R.drawable.royalarcher;
        int h=R.drawable.dragonhound;
        int m=R.drawable.anguard;
        int x=R.drawable.finalboss;

        z1.unitPos=new int[][]{
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 1, 1, 1, 1, 0, 0},
                {1, 1, 0, 0, 1, 0, s, 1, 0, 0},
                {1, 0, 0, 0, 1, 1, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, s, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0}};

        z2.unitPos=new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 0, 0},
                {0, 1, 0, 1, s, 1, 0, 1, s, 0},
                {0, 1, 0, 1, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, n, 0}};

        z3.unitPos=new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, n, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, n, 0, a, n, 0}};

        z4.unitPos=new int[][]{
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, h, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

        z5.unitPos=new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {1, 0, n, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, h, 0, 0},
                {1, 0, 0, 1, 1, 0, 0, 0, h, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 0}};

        z6.unitPos=new int[][]{
                {0, a, 0, 0, 0, 0, 0, 0, a, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, a, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, a, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, a, 0, 0, 0, 0, 0, 0, a, 0}};

        z7.unitPos=new int[][]{
                {0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 0, 0, a, 0},
                {0, 0, 0, 0, 0, 0, 0, h, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, h, 0, 0},
                {0, 0, 0, 1, 1, 1, 0, 0, a, 0},
                {0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 0, 0, 0}};

        z8.unitPos=new int[][]{
                {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
                {0, 1, 0, 1, 1, 1, 1, 0, 1, a},
                {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, m, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, a, 0},
                {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
                {0, 1, 0, 1, 1, 1, 1, 0, 1, a},
                {0, 0, 0, 1, 1, 1, 1, 0, 0, 0}};

        z9.unitPos=new int[][]{
                {1, 1, 1, 0, 0, 0, 0, 0, 1, 1},
                {1, 1, 1, 0, 1, 1, 1, h, 1, 1},
                {1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, x, 0},
                {1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 0, 1, 1, 1, a, 1, 1},
                {1, 1, 1, 0, 0, 0, 0, 0, 1, 1}};


//        z1.unitPos= new int[][]{{0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,1,0,0,0,0},
//                {0,0,0,1,1,1,1,1,0,R.drawable.skeleton},
//                {0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,1,1,1,0,1,1,0},
//                {0,0,0,0,1,1,0,R.drawable.skeleton,0,0},
//                {0,0,0,0,1,0,0,0,0,0},
//                {0,0,0,0,0,0,0,R.drawable.deadnurse,0,0}};
//
//        z2.unitPos= new int[][]{{0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,R.drawable.skeleton,0},
//                {0,0,0,0,0,0,0,R.drawable.skeleton,0,R.drawable.skeleton},
//                {0,0,0,0,0,0,0,0,R.drawable.skeleton,0},
//                {0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0}};
//
//        z3.unitPos= new int[][]{{0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0},
//                {1,1,0,0,0,0,0,0,0,0},
//                {1,R.drawable.deadnurse,0,0,0,0,0,0,0,0},
//                {1,1,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0},
//                {1,1,1,1,1,1,1,1,1,1}};
//
//        z4.unitPos= new int[][]{{0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,0,0,0,0,0}};
    }


}
