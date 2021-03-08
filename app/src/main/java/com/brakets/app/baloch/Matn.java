package com.brakets.app.baloch;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Matn extends AppCompatActivity {

  private DataBase db;
  private HashMap<String , Object> book;


  Toolbar toolbar;
  TextView txtdata;
  TextView txtauthor;
  TextView txttitle;

  WebView matn;
  ImageView imgfav,imgvisit;
  String main_txt;





  @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.matn);

    toolbar=(Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


    db=new DataBase(getBaseContext());

    Bundle data=getIntent().getExtras();

    db.open();


    book=  db.getBookContent(data.getString("id"));




    toolbar=(Toolbar)findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    txtauthor=(TextView)findViewById(R.id.txtbookauthor);
    txttitle=(TextView)findViewById(R.id.txtbooktitle);
    matn = (WebView) findViewById(R.id.matn);
    imgfav=(ImageView) findViewById(R.id.imgfav);
    imgvisit=(ImageView)findViewById(R.id.imgvisit);

    txttitle.setText(book.get("title").toString());
    txtauthor.setText(book.get("author").toString());


    String font_size = String.valueOf( db.getFontSize() );

    String main_txt =
      "<html>" +
        "<head></head>" +
        "<body dir='rtl' style='font-size: " +
        font_size + "px; text-align: justify;' >" +
        book.get("content").toString() +
        "</body>" +
        "</html>";
    matn.loadDataWithBaseURL(
      null , main_txt , "text/html; charset=utf-8" , null , null
    );

    imgfav.setImageResource( Integer.parseInt(book.get("fav_flag").toString()));
    imgvisit.setImageResource( Integer.parseInt(book.get("see_flag").toString()));
    db.close();

  }





  public void btnsee(View v)
  {

    db.open();

    int id = Integer.parseInt( book.get("id").toString() );

    if( db.getBookVisitState(id) == 1 )
    {
      db.setBookVisitState( id , 0 );

      imgvisit.setImageResource( R.drawable.not_see );
    }
    else
    {
      db.setBookVisitState( id , 1 );

      imgvisit.setImageResource(R.drawable.see);
    }

    db.close();

  }

  public void btnfave(View v)
  {


    db.open();

    int id = Integer.parseInt( book.get("id").toString() );

    if( db.getBookFavoriteState(id) == 1 )
    {
      db.setBookFavoriteState( id , 0 );

      imgfav.setImageResource( R.drawable.not_favorite );
    }
    else
    {
      db.setBookFavoriteState( id , 1 );

      imgfav.setImageResource(R.drawable.is_favorite);
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
