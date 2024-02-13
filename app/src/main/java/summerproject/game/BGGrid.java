package summerproject.game;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class BGGrid extends View {

    private static int width;
    private static int height;

    private static final int rows = 8;
    private static final int cols = 10;

    public static Resources res;

    private static Tile[][] mTiles;

    Map map;

    public static int[][] bgTiles;
    public static int[][] unitPos;
    public static int[][] sfx;

    public static Player player;
    public static Enemy[] enemy;
    public static EnemyDataManager enemyDataManager;

    int enemyCount=0;

    public static int pcRow;
    public static int pcCol;
    public static int pcSprite=R.drawable.knightsprite;

    private static int x0 = 0;
    private static int y0 = 0;
    private static int xSize = 0;
    private static int ySize=0;

    int atkMode=0;


    public BGGrid(final Context context, final AttributeSet attrs) {
        super(context, attrs);

        this.mTiles = new Tile[rows][cols];
        setFocusable(true);
        enemyDataManager=new EnemyDataManager(getContext());

        map=new Map();

        sfx=new int[][]{{0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0}};

        pcCol=0;
        pcRow=7;
        res= getResources();
        unitPos= new int[][]{{0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0},
                {0,0,0,1,1,1,1,1,0,R.drawable.skeleton},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,1,1,1,0,1,1,0},
                {0,0,0,0,1,1,0,R.drawable.skeleton,0,0},
                {0,0,0,0,1,0,0,0,0,0},
                {pcSprite,0,0,0,0,0,0,R.drawable.deadnurse,0,0}};
        bgTiles=new int[][]{
                {R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1},
                {R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.water1,R.drawable.wood1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1},
                {R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.wood1,R.drawable.wood1,R.drawable.wood1,R.drawable.wood1,R.drawable.wood1,R.drawable.sand1,R.drawable.sand1},
                {R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1},
                {R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.wood1,R.drawable.wood1,R.drawable.wood1,R.drawable.sand1,R.drawable.wood1,R.drawable.wood1,R.drawable.sand1},
                {R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.wood1,R.drawable.wood1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1},
                {R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.wood1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1},
                {R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1,R.drawable.sand1}};
        buildTiles();
        blockMap();
    }

    public void setStage(int stage){
        bgTiles=map.zoneMap[stage].bgTiles;
        unitPos=map.zoneMap[stage].unitPos;
        buildTiles();
        blockMap();
        blockMap();
        super.invalidate();
    }

    private void buildTiles() {
        enemyCount=0;
        Resources res = getResources();
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                mTiles[r][c] = new Tile(c, r, BitmapFactory.decodeResource(res, bgTiles[r][c]));
                if((unitPos[r][c]!=0)&&(unitPos[r][c]!=pcSprite)&&(unitPos[r][c]!=1)){
                    enemyCount++;
                }
            }
        }
        enemy=new Enemy[enemyCount];
        int i=0;
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                if((unitPos[r][c]!=0)&&(unitPos[r][c]!=pcSprite)&&(unitPos[r][c]!=1)){
                    enemy[i]=enemyDataManager.getEnemy(getResources().getResourceEntryName(unitPos[r][c]));
                    enemy[i].setPos(r,c,getContext());
                    i++;
                }
            }
        }
    }

    public void changeZone(int direction){
        bgTiles=map.getZoneBG(direction);
        unitPos=map.getZoneUnits(direction);
        buildTiles();
        blockMap();
        invalidate();
    }

    public void setPlayer(Player pc,int r, int c){
        player=pc;
        unitPos[pcRow][pcCol]=0;
        if(player.pcclass.equals("Knight")){
            pcSprite=R.drawable.knightsprite;
        }else if(player.pcclass.equals("Ranger")){
            pcSprite=R.drawable.rangersprite;
        }else if(player.pcclass.equals("Mage")){
            pcSprite=R.drawable.magesprite;
        }
        unitPos[r][c]=pcSprite;
        pcCol=c;
        pcRow=r;

    }

    public void moveUp(){
        if((pcRow >0)&&(unitPos[pcRow-1][pcCol]==0)) {
            unitPos[pcRow][pcCol]=0;
            pcRow--;
            unitPos[pcRow][pcCol]=pcSprite;
        }

    }

    public void moveDown(){
        if((pcRow <7)&&(unitPos[pcRow+1][pcCol]==0)) {
            unitPos[pcRow][pcCol]=0;
            pcRow++;
            unitPos[pcRow][pcCol]=pcSprite;
        }
    }

    public void moveLeft(){
        if((pcCol >0)&&(unitPos[pcRow][pcCol-1]==0)) {
            unitPos[pcRow][pcCol]=0;
            pcCol--;
            unitPos[pcRow][pcCol]=pcSprite;
        }
        else if((pcCol==0)) {
            if(map.zoneID==0){
                return;
            }
            unitPos[pcRow][pcCol]=0;
            changeZone('L');
            setPlayer(player,pcRow,9);
            resizeAllBitmaps(xSize,ySize);
        }
    }

    public void moveRight(){
        if((pcCol <9)&&(unitPos[pcRow][pcCol+1]==0)) {
            unitPos[pcRow][pcCol]=0;
            pcCol++;
            unitPos[pcRow][pcCol]=pcSprite;
        }
        else if((pcCol==9)) {
            if(map.zoneID==(map.zoneMap.length-1)){
                Intent intent=new Intent(getContext(),GameOver.class);
                intent.putExtra("WIN",1);
                getContext().startActivity(intent);
                return;
            }
            unitPos[pcRow][pcCol]=0;
            changeZone('R');
            setPlayer(player,pcRow,0);
            resizeAllBitmaps(xSize,ySize);
        }
    }

    public void blockMap(){
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                if(unitPos[r][c]==1)
                for (int j = 0; j < enemyCount; j++) {
                    enemy[j].setBlocked(r, c);
                }
            }
        }

    }

    public void moveEnemy(){
        for(int i=0;i<enemyCount;i++){

            //Make enemy[i]'s current position accessible to all enemies[j]
            for(int j=0;j<enemyCount;j++){
                if(j!=i){
                enemy[j].unblock(enemy[i].row,enemy[i].col);
//                Log.d("MOVEENEMY: ","Unblocked by "+enemy[i].name+" for "+enemy[j].name);
                }
            }


            int row=enemy[i].row;
            int col=enemy[i].col;
            unitPos[row][col]=0;
            enemy[i].moveToPlayer(pcRow,pcCol);

            int enemysprite=getResources().getIdentifier(enemy[i].imgID,"drawable",getContext().getPackageName());

            //Attack if able
            if(enemy[i].attackFlag==1){
                player.chp-=enemyAttack(enemy[i]);
                //Player dead?
                if(player.chp<=0){
                    Intent intent=new Intent(getContext(),GameOver.class);
                    getContext().startActivity(intent);
                }
            }

            //Block enemy's current position
            unitPos[enemy[i].row][enemy[i].col]=enemysprite;
                for(int j=0;j<enemyCount;j++){
                    if(j!=i) {
                        enemy[j].setBlocked(enemy[i].row, enemy[i].col);
//                        Log.d("MOVEENEMY: ", "Blocked by "+enemy[i].name+" for "+enemy[j].name);
                    }
                }
        }
    }

    public void atkRange(int range){
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                if( (Math.abs(pcRow-r)+Math.abs(pcCol-c)) <=range){
                    sfx[r][c]=R.drawable.pcmovefx;
                }
            }
            }
        atkMode=1;
        invalidate();
    }

    public void spatkRange(int range){
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                if( (Math.abs(pcRow-r)+Math.abs(pcCol-c)) <=range){
                    sfx[r][c]=R.drawable.pcmovefx;
                }
            }
        }
        atkMode=2;
        invalidate();
    }

    public void PCattack(int r, int c){
        if(atkMode==1) {
            if (sfx[r][c] == R.drawable.pcmovefx) {
                sfx[r][c] = R.drawable.enemymovefx;
            } else {
                Log.d("ATTACK: ", "Out of attack range");
            }
            if ((unitPos[r][c] != 0) && (unitPos[r][c] != pcSprite) && (unitPos[r][c] != 1)) {
                Log.d("ATTACK: ", "Clicking enemy");
                for (int i = 0; i < enemyCount; i++) {
                    if ((enemy[i].row == r) && (enemy[i].col == c)) {
                        enemy[i].doDamage(MainScreen.weapon.getDamageDone());
                    }
                }
            }
        }
        if(atkMode==2) {
            if (sfx[r][c] == R.drawable.pcmovefx) {
                sfx[r][c] = R.drawable.enemymovefx;
            } else {
                Log.d("ATTACK: ", "Out of attack range");
            }
            if ((unitPos[r][c] != 0) && (unitPos[r][c] != pcSprite) && (unitPos[r][c] != 1)) {
                Log.d("ATTACK: ", "Clicking enemy");
                for (int i = 0; i < enemyCount; i++) {
                    if ((enemy[i].row == r) && (enemy[i].col == c)) {
                        enemy[i].doDamage(MainScreen.weapon.getSPDamageDone());
                    }
                }
            }
        }
        atkMode=0;
        invalidate();
    }

    public int enemyAttack(Enemy enemy){
        if(enemy.attackFlag==0){
            return 0;
        }
        int damageDone=0;
        int i=0;
        Random accRoll=new Random();
        if(accRoll.nextInt(100)<enemy.acc){
            Log.d("ENEMY ATTACKED: ",enemy.name+" missed!");
            return 0;
        }

        double HPleft=(double) enemy.HP/(double) enemy.maxHP;
        if(HPleft>=0.75){
            i=0;
        }else if((HPleft>=0.5)&&(HPleft<0.75)){
            i=1;
        }else if((HPleft>=0.3)&&(HPleft<0.5)){
            i=2;
        }else{
            i=3;
        }

        damageDone=enemy.dmg[i];

        if(damageDone<0){
            enemy.HP+=damageDone;
            Log.d("ENEMY HEALED: ",enemy.name+" ("+HPleft+" "+enemy.HP+"/"+enemy.maxHP+")used "+ enemy.atk[i]+" and healed "+damageDone+" damage");
            return 0;
        } else{
            Log.d("ENEMY ATTACKED: ",enemy.name+" ("+HPleft+" "+enemy.HP+"/"+enemy.maxHP+")used "+ enemy.atk[i]+" and did "+damageDone+" damage");
            return damageDone;
        }
    }

    public void resizeAllBitmaps(int newWidth, int newHeight) {
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                mTiles[r][c].resizeBitmap(newWidth,newHeight);
            }
        }
//        mTiles[0][0].resizeBitmap(newWidth,newHeight);
//        mTiles[5][5].resizeBitmap(newWidth,newHeight);

    }








    @Override
    public void invalidate() {
        for(int i=0;i<enemyCount;i++){
            if(enemy[i].HP<=0){
                unitPos[enemy[i].row][enemy[i].col]=0;
                player.addXP(enemy[i].exp);
                for(int j=i;j<(enemyCount-1);j++){
                    enemy[j]=enemy[j+1];
                }
                enemyCount--;
            }
        }
        MainScreen.updateStatus();
        if(MainScreen.turns<=0) {
            MainScreen.enemyTurn();
        }
        super.invalidate();
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        super.onSizeChanged(xNew, yNew, xOld, yOld);

        float logicalDensity = Resources.getSystem().getDisplayMetrics().density;
        int px = (int) Math.ceil(180*logicalDensity);


        width = getScreenWidth()-px;
//        height = getScreenHeight();
        height = yNew;

        this.xSize = getSquareSizeWidth(width);
        this.ySize = getSquareSizeHeight(height);

        computeOrigins(width, height);
        resizeAllBitmaps(xSize, ySize);
    }

    @Override
    protected void onDraw(final Canvas canvas) {

        Resources res = getResources();

        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                final int xCoord = getXCoord(r);
                final int yCoord = getYCoord(c);

//                Rect tileRect = new Rect(xCoord,yCoord,0,0);
                final Rect tileRect = new Rect(
                    xCoord,          //left (top when rotated)
                    yCoord,          // top (right when rotated)
                    xCoord + xSize,  // right (bottom when rotated)
                    yCoord + ySize   // bottom (left when rotated)
                );

                mTiles[r][c].setTileRect(tileRect);
                mTiles[r][c].draw(canvas);
                if((unitPos[r][c]!=0)&&(unitPos[r][c]!=1)) {
                    mTiles[r][c].unitDraw(canvas,BitmapFactory.decodeResource(res, unitPos[r][c]));
                }
                if(sfx[r][c]!=0) {
                    mTiles[r][c].sfxDraw(canvas,BitmapFactory.decodeResource(res, unitPos[r][c]),BitmapFactory.decodeResource(res, sfx[r][c]));
                }

            }
        }
    }

    public void clearSFX(){
        sfx=new int[][]{{0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0}};
    }

    public boolean onTouchEvent(final MotionEvent event) {
        final int x = (int) event.getY();                   //X and Y are flipped on the board
        final int y = (int) event.getX();

        Tile tile;
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                tile = mTiles[r][c];
                if (tile.isTouched(x, y)) {
                    PCattack(r, c);
                }
            }
        }
        return true;
    }

    protected int getSquareSizeWidth(final int width) {

        return width/cols;
    }

    protected int getSquareSizeHeight(final int height) {

        return height/rows;
    }

    protected int getXCoord(final int x) {

        return y0 + (ySize*x);
    }

    protected int getYCoord(final int y) {

        return x0 + (xSize*y);
    }

    protected void computeOrigins(final int width, final int height) {
        this.x0 = width  - xSize * 10;
        this.y0 = height - ySize * 8;
    }

}
