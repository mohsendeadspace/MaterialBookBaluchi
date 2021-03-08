package com.brakets.app.baloch;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.Toolbar;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by MoHseN.DeaDSpacE on 17/08/2016.
 */
public class SettingActivity extends AppCompatActivity {

   AppCompatSeekBar seekbar;
    public int bright;
    ImageView fontup,fontdown,settings;
    TextView txtFont;
    private DataBase db;
    private Toolbar toolbar;


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitting);

        toolbar=(Toolbar) findViewById(R.id.toolbar);





        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        

        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        settings=(ImageView) findViewById(R.id.settings) ;
        DrawableImageViewTarget imageView=new DrawableImageViewTarget(settings);
        Glide.with(this).load(R.raw.sitting).into(imageView);

        db=new DataBase(getBaseContext());
        fontup=(ImageView)findViewById(R.id.fontup);
        fontdown=(ImageView)findViewById(R.id.fontdown);
        txtFont=(TextView)findViewById(R.id.txtfont) ;


        db.open();


        int font_size = db.getFontSize();
        txtFont.setText(
                getString(R.string.font_size_settings_sample) + " " +
                        String.valueOf( font_size ) );
         txtFont.setTextSize( font_size );


        db.close();





    }
    public void  btnfontUP(View v)
    {

        db.open();

        int size = db.getFontSize();

        if( db.setFontSize( size + 1 ) )
        {
            size ++;
            txtFont.setText(
                    getString(R.string.font_size_settings_sample) + " " +
                            String.valueOf( size )
            );
            txtFont.setTextSize( size );
        }

        db.close();
    }
    public  void btnfontDown(View v)
    {

        db.open();

        int size = db.getFontSize();

        if( db.setFontSize( size - 1 ) ) {
            size --;
            txtFont.setText(
                    getString(R.string.font_size_settings_sample) + " " +
                            String.valueOf( size )
            );
            txtFont.setTextSize( size );
        }

        db.close();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if(id==android.R.id.home)
        {
            finish();


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
