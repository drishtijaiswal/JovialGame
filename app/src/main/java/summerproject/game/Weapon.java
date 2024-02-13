package summerproject.game;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

public class Weapon implements Parcelable{

    String name;
    String wpclass;
    int basedmg;
    int fluxdmg;
    int basespuse;
    int acc;
    int critch;
    float critmod;
    int range;
    int spdmg;
    int spfluxdmg;
    int spuse;
    int sprange;
    String wpnid;

    public Weapon(String name, String wpclass,int basedmg,int fluxdmg,int basespuse,int acc,int critch,int critmod,int range,int spdmg,int spfluxdmg,int spuse,int sprange, String wpnid){
        this.name=name;
        this.wpclass=wpclass;
        this.basedmg=basedmg;
        this.fluxdmg=fluxdmg;
        this.basespuse=basespuse;
        this.acc=acc;
        this.critch=critch;
        this.critmod=((float)critmod/100);
        this.range=range;
        this.spdmg=spdmg;
        this.spfluxdmg=spfluxdmg;
        this.spuse=spuse;
        this.sprange=sprange;
        this.wpnid=wpnid;
    }

    public int getDamageDone(){
        Random random=new Random();
        int accuracy=random.nextInt(100);
        int finaldmg=0;
        if(accuracy<=acc){
            finaldmg=(basedmg-fluxdmg)+random.nextInt((2*(fluxdmg)+1));
        }
        return finaldmg;
    }

    public int getSPDamageDone(){
        Random random=new Random();
        int accuracy=random.nextInt(100);
        int finaldmg=0;
        if(accuracy<=acc){
            finaldmg=(spdmg-spfluxdmg)+random.nextInt((2*(spfluxdmg)+1));
        }
        return finaldmg;
    }

    public String toString(){
        return name;
    }

    public String getStats(){
        String wpnInfo=name+":: Damage: "+basedmg+"~"+fluxdmg+" Range: "+range+" Acc: "+acc +" Crit: x"+critmod+"/"+critch+"%";
        return wpnInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(basedmg);
        dest.writeInt(fluxdmg);
        dest.writeInt(basespuse);
        dest.writeInt(acc);
        dest.writeInt(critch);
        dest.writeFloat(critmod);
        dest.writeInt(range);
        dest.writeInt(spdmg);
        dest.writeInt(spfluxdmg);
        dest.writeInt(spuse);
        dest.writeInt(sprange);
        dest.writeString(wpnid);
    }

    protected Weapon(Parcel in){
        name=in.readString();
        basedmg=in.readInt();
        fluxdmg=in.readInt();
        basespuse=in.readInt();
        acc=in.readInt();
        critch=in.readInt();
        critmod=in.readFloat();
        range=in.readInt();
        spdmg=in.readInt();
        spfluxdmg=in.readInt();
        spuse=in.readInt();
        sprange=in.readInt();
        wpnid=in.readString();
    }

    public static final Creator<Weapon> CREATOR = new Creator<Weapon>() {
        @Override
        public Weapon createFromParcel(Parcel in) {

            return new Weapon(in);
        }

        @Override
        public Weapon[] newArray(int size) {
            return new Weapon[size];
        }
    };
}
