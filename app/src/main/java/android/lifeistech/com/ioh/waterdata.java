package android.lifeistech.com.ioh;

/**
 * Created by fumiyauchiyama on 2017/07/16.
 */

public class waterdata {

    int ch0;
    int ch1;

    //からのコンストラクタ
    public waterdata(){



    }

    public waterdata(int ch0,int ch1){
        this.ch0 = ch0;
        this.ch1 = ch1;
    }

    public int getch0(){return ch0;}

    public void setch0(int ch0){this.ch0 = ch0;}

    public int getch1(){return ch1;}

    public void setch1(int ch1){this.ch1 = ch1;}
}
