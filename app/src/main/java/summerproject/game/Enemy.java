package summerproject.game;

        import android.app.Dialog;
        import android.content.Context;
        import android.util.Log;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.Comparator;
        import java.util.PriorityQueue;
        import java.util.Random;
        import java.util.ResourceBundle;
        import java.util.Stack;

public class Enemy {

    Context context;
    int attackFlag=0;

    //Enemy stats
    public int row;
    public int col;
    public int HP;
    public int maxHP;
    public String name;
    public int move;
    public String[] atk;
    public int[] dmg;
    public int acc;
    public int range;
    public int exp;
    public String type;
    public String imgID;

    //Variables for AStar path computation
    Cell[][] grid;

    PriorityQueue<Cell> open;                       //Queue of cells to be evaluated. Comparable by fCost
    boolean closed[][];                             //Array of inaccessible cells or evaluated cells

    ArrayList<Cell> endSpaces;
    int endR, endC;                                 //Target position

    //Constructors
    public Enemy(){
    }

    public Enemy(String name,int maxHP,int move,String[] atk,int[] dmg,int acc,int range,int exp,String type,String imgID){
        this.maxHP=maxHP;
        HP=maxHP;
        this.name=name;
        this.move=move;
        this.atk=atk;
        this.dmg=dmg;
        this.acc=acc;
        this.range=range;
        this.exp=exp;
        this.type=type;
        this.imgID=imgID;

        grid= new Cell[8][10];
        for(int x=0;x<8;++x) {
            for (int y = 0; y < 10; ++y) {
                grid[x][y] = new Cell(x, y);
            }
        }
        setHCost(7,0);

        endSpaces=new ArrayList<>();
    }

    public void setPos(int r, int c, Context ctx){
        row=r;
        col=c;
        context=ctx;
    }

    public void moveToPlayer(int pcR, int pcC){

        endR=pcR;
        endC=pcC;

        setEndSpaces(endR,endC);

        attackFlag=0;

        if(endSpaces.contains(grid[row][col])){
            endSpaces.clear();
            attackFlag=1;
            return;
        }

        if(type.equals("Guard")){
            return;
        }

        setHCost(endR,endC);//

        closed = new boolean[8][10];
        open = new PriorityQueue<>(15, new Comparator<Cell>(){      //New priority queue sorted by fCost
            @Override
            public int compare(Cell c1, Cell c2) {
                return (c1.fCost < c2.fCost ? -1: c1.fCost > c2.fCost ? 1 : 0);
            }});


        try {
            AStar();
        }
        catch(NullPointerException e){
            return;
        }



        Cell current=endSpaces.get(0);
        int i=1;
        while(current.parent==null){
            if(i<endSpaces.size()) {
                current = endSpaces.get(i);
                i++;
            }
            else{
                return;
            }
        }

        String path = "";
        Cell[] pathArr = new Cell[50];
        Cell start = grid[row][col];
        int pathn = -1;
        while (current != start) {
            try {
                path = path + " -> " + current.parent;
                pathArr[++pathn] = current;
                current = current.parent;
                Log.d("", path);
            }catch(ArrayIndexOutOfBoundsException e){
                Log.d("PATHFINDING ERROR: "," Breaking out of infinite loop");
                pathn--;
                break;
            }
        }
        Log.d("", path);
        if (pathn >=0) {
            for (i = move; i >= 1; i--) {
                Log.d("ENEMY MOVE:", "Moving "+name+" to: " + (pathArr[pathn]));
                current = pathArr[pathn--];
                row = current.r;
                col = current.c;
            }
        }
        endSpaces.clear();
    }


    public void setEndSpaces(int targetr, int targetc) {
        endSpaces.add(grid[targetr][targetc]);
        for (int c = 0; c < 10; c++) {
            for (int r = 0; r < 8; r++) {
                if ((Math.abs(targetr - r) + Math.abs(targetc - c)) <= range) {
                    if((r!=targetr)||(c!=targetc)) {
                        endSpaces.add(grid[r][c]);
                    }
                }
            }
        }
    }

    public void setBlocked(int r, int c) {
        grid[r][c].blocked=true;
    }
    public void unblock(int r, int c) {
        grid[r][c].blocked=false;
    }

    void checkAndUpdateCost(Cell current, Cell t, int cost) {
        if (t.blocked || closed[t.r][t.c])          //Check if target Cell t is blocked(null) or already evaluated(in closed)
            return;

        int t_final_cost = t.hCost + cost;          //hCost is heuristic cost of t and cost is cost from start cell to t

        boolean inOpen = open.contains(t);
        if (!inOpen || t_final_cost < t.fCost) {    //Runs if t is neither in open queue nor in closed[][] array and if calculated final cost is less than stored fCost
            t.fCost = t_final_cost;
            t.parent = current;                     //Set parent to current cell, only if cost is less than stored fCost

            if (!inOpen)                            //Adds t to open queue if it's not there
                open.add(t);
        }
    }

    public void setHCost(int targetr, int targetc){

        for(int i=0;i<8;++i){
            for(int j=0;j<10;++j) {
                grid[i][j].hCost = Math.abs(i - targetr) + Math.abs(j - targetc);
            }
        }
    }

    public void doDamage(int dmgtaken){
        this.HP-=dmgtaken;
        Log.d("ENEMY DAMAGED:",name+" took "+dmgtaken+" damage");
        if(HP<=0){
            Log.d("ENEMY SLAIN:",name);
        }
    }



    private class Cell {
        int hCost = 0;                              //Heuristic cost
        int fCost = 0;                              //G+H
        int r, c;
        boolean blocked;
        Cell parent;
        Cell child;

        Cell(int r, int c) {
            this.r = r;
            this.c = c;
            this.blocked=false;
        }

        @Override
        public String toString() {
            return "["+this.r+", "+this.c+"]";
        }
    }

    public void AStar(){

        open.add(grid[row][col]);
//        Log.d("OPENED:",grid[row][col].toString());
        Cell current;
        while(true){

            current=open.poll();                            //Return and remove the head of the priority queue
//            Log.d("POLLED:",current.toString());

            closed[current.r][current.c]=true;
//            Log.d("CLOSED:",current.toString());

            if(current.blocked==true){                      //If current if invalid or inaccessible, skip this iteration
                break;
            }
            if(endSpaces.contains(current)){
                return;
            }

            Cell next;

            if(current.r-1>=0){
                next=grid[current.r-1][current.c];
                checkAndUpdateCost(current,next,current.fCost+1);
            }
            if(current.c-1>=0){
                next=grid[current.r][current.c-1];
                checkAndUpdateCost(current,next,current.fCost+1);
            }
            if(current.r+1<=7){
                next=grid[current.r+1][current.c];
                checkAndUpdateCost(current,next,current.fCost+1);
            }
            if(current.c+1<=9){
                next=grid[current.r][current.c+1];
                checkAndUpdateCost(current,next,current.fCost+1);
            }
        }

    }
}
