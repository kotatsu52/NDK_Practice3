package my.kotatsu.ndk_practice3;

import java.nio.ByteBuffer;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView  tv1 = (TextView) findViewById(R.id.tv1);
        TextView  tv2 = (TextView) findViewById(R.id.tv2);
        TextView  tv3 = (TextView) findViewById(R.id.tv3);
        TextView  tv4 = (TextView) findViewById(R.id.tv4);
        TextView  tv5 = (TextView) findViewById(R.id.tv5);
        TextView  tv6 = (TextView) findViewById(R.id.tv6);
        double time1,time2,time3;

        //-------------------------------//
        //①メモリコピー
        //-------------------------------//
        final int MEM_SIZE=5000000; //メモリサイズ
        //JAVA
        ByteBuffer src = ByteBuffer.allocateDirect(MEM_SIZE);
        ByteBuffer dest = ByteBuffer.allocateDirect(MEM_SIZE);
        src.clear();
        dest.clear();
        byte a='a';

        for(int i=0;i<MEM_SIZE;i++){
        	src.put(a);
        }
        src.position(0);
        long start1 = System.currentTimeMillis();
        dest.put(src);
        long stop1 = System.currentTimeMillis();

        //C
        time1 = copyBuffer(MEM_SIZE);
        src = null;
        dest = null;

        //-------------------------------//
        //②浮動小数点演算
        //-------------------------------//
        // JAVA
        float f=0.0f;
    	int i;
    	int times = 10000000; //演算回数
    	long start3 = System.currentTimeMillis();
    	for( i=0; i< times; i++){
    		f += 0.0000001f;
    	}
    	long stop3 = System.currentTimeMillis();

        // C
    	time2 = floatPoint(times);
    	Log.d("NDK_PRACTICE3","float:" + f);

        //-------------------------------//
        //③整数演算
        //-------------------------------//
        // JAVA
    	int j = 0;
    	long start5 = System.currentTimeMillis();
    	for( i=0; i< times; i++){
    		j++;
    	}
    	long stop5 = System.currentTimeMillis();

        // C
    	time3 = integer(times);
    	Log.d("NDK_PRACTICE3","Intejer:" + j);

    	tv1.setText("メモリコピー：JAVA 処理時間：" + (stop1 - start1) + " msec");
    	tv2.setText("メモリコピー：C    処理時間：" + (time1) + " msec");
        tv3.setText("浮動小数点演算：JAVA 処理時間：" + (stop3 - start3) + " msec");
        tv4.setText("浮動小数点演算：C    処理時間：" + (time2) + " msec");
        tv5.setText("整数演算：JAVA 処理時間：" + (stop5 - start5) + " msec");
        tv6.setText("整数演算：C    処理時間：" + (time3) + " msec");

    }

    public native double copyBuffer(int memSize);
    public native double floatPoint(long times);
    public native double integer(long times);

    static {
        System.loadLibrary("ndk_practice");
    }

}
