package summerproject.game;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Player implements Parcelable{
    String name;
    String pcclass;
    int chp;
    double mhp;
    int csp;
    double msp;
    int lvl;
    int xp;

    int stage;
    int pos;

    double dmgmult=1.0;
    double hpadd=1.0;
    double spadd=1.0;


    public Player(String pname,String pcclass, int chp, double mhp, int csp, double msp, int level, int exp, int stage, int position){
        this.name=pname;
        this.pcclass=pcclass;
        this.chp=chp;
        this.mhp=mhp;
        this.csp=csp;
        this.msp=msp;
        this.lvl=level;
        this.xp=exp;
        this.stage=stage;
        this.pos=position;

        setMultipliers();

    }

    public void setMultipliers(){
        if(pcclass.equals("Knight")){
            hpadd =16;
            spadd =12;
        } else if(pcclass.equals("Ranger")){
            hpadd =14;
            spadd =14;
        } else if(pcclass.equals("Mage")){
            hpadd =10;
            spadd =18;
        }
    }

    protected Player(Parcel in) {
        name = in.readString();
        pcclass=in.readString();
        chp = in.readInt();
        mhp = in.readDouble();
        csp = in.readInt();
        msp = in.readDouble();
        lvl = in.readInt();
        xp = in.readInt();
        stage = in.readInt();
        pos = in.readInt();

        setMultipliers();
    }

    public void addXP(int xp){
        this.xp+=xp;
        if(xp>=( (lvl+1)*10) ){
            Log.d("LEVELUP:","You leveled up to "+(++lvl));
            chp+=hpadd;
            csp+=spadd;
            mhp+=hpadd;
            msp+=spadd;
            dmgmult*=1.1;
        }
    }

    public void prepareSavable(int chp, int csp, int row, int col, int stage){
        this.chp=chp;
        this.csp=csp;
        this.pos=(row*10)+col;
        this.stage=stage;
    }

    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(pcclass);
        dest.writeInt(chp);
        dest.writeDouble(mhp);
        dest.writeInt(csp);
        dest.writeDouble(msp);
        dest.writeInt(lvl);
        dest.writeInt(xp);
        dest.writeInt(stage);
        dest.writeInt(pos);
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

}
