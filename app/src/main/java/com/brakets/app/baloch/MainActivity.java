package com.brakets.app.baloch;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;



public class MainActivity extends AppCompatActivity {


  Toolbar toolbar;
  ImageView imageView,img;
  private long back_pressed;
  Button btn1,btn2,btn3,btn4,btn5,btn6,btn7;

  @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

    toolbar=(Toolbar)findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    imageView=(ImageView) findViewById(R.id.imageView);
    img=(ImageView) findViewById(R.id.img);

    btn1=(Button) findViewById(R.id.btn1);
    btn2=(Button) findViewById(R.id.btn2);
    btn3=(Button) findViewById(R.id.btn3);
    btn4=(Button) findViewById(R.id.btn4);
    btn5=(Button) findViewById(R.id.btn5);
    btn6=(Button) findViewById(R.id.btn6);
    btn7=(Button) findViewById(R.id.btn7);

    //btn1.setAnimation(AnimationUtils.loadAnimation(this,R.anim.trans_four));
    //btn2.setAnimation(AnimationUtils.loadAnimation(this,R.anim.trans_five));
    //btn3.setAnimation(AnimationUtils.loadAnimation(this,R.anim.trans_six));
    //btn4.setAnimation(AnimationUtils.loadAnimation(this,R.anim.trans_seven));
//
    DrawableImageViewTarget imageViewTarget=new DrawableImageViewTarget(imageView);
    Glide.with(this).load(R.raw.books).into(imageViewTarget);



  }


  public void btnListMataleb(View v)
  {


    Intent matalb=new Intent("android.intent.action.LISTMATALBACTIVITY");
    startActivity(matalb);

  }
  public void btnSearch(View v)
  {

    Intent search=new Intent("android.intent.action.SEARCHACTIVITY");
    startActivity(search);

  }
  public void btnFavorit(View v)
  {

    Intent fav=new Intent("android.intent.action.FAVACTIVITY");
    startActivity(fav);

  }

  public void btnSetting(View V)
  {

    Intent setting=new Intent("android.intent.action.SETTINGACTIVITY");
    startActivity(setting);


  }

  public void btnMoghadame(View V)
  {

    Intent setting=new Intent("android.intent.action.MOGHADAME");
    startActivity(setting);


  }

  public void btnPishgoftar(View V)
  {

    Intent setting=new Intent("android.intent.action.PISHGOFTAR");
    startActivity(setting);


  }

  public void btnManabee(View V)
  {

    Intent setting=new Intent("android.intent.action.MANABEE");
    startActivity(setting);


  }


  public void btnInfo(View v)
  {

    Intent info=new Intent("android.intent.action.ABOUTUSACTIVITY");
    startActivity(info);

  }

  @Override
  public void onBackPressed() {

    if(back_pressed + 2000>System.currentTimeMillis())
      super.onBackPressed();

    else
      Toast.makeText(getBaseContext(),"برای خروج دکمه بازگشت را دو بار بزنید .",Toast.LENGTH_SHORT).show();
    back_pressed=System.currentTimeMillis();



  }
  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
  }

}

