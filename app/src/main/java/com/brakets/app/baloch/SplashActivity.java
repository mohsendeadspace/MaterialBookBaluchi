package com.brakets.app.baloch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by MoHseN.DeaDSpacE on 19/08/2016.
 */
public class SplashActivity extends AppCompatActivity {


    TextView txtsplash;
    ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);


        //txtsplash=(TextView)findViewById(R.id.txtsplash) ;
       // txtsplash.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale));

        //img=(ImageView) findViewById(R.id.imganim) ;
        //img.setAnimation(AnimationUtils.loadAnimation(this,R.anim.trans_five));

        Thread splashscreen=new Thread(){

            public void run()
            {

                try{

                    sleep(6000);

                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {

                    Intent splash=new Intent("android.intent.action.MAINACTIVITY");
                    startActivity(splash);
                }

            }



        };
        splashscreen.start();



    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
