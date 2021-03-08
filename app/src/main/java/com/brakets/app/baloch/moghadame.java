package com.brakets.app.baloch;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class moghadame extends AppCompatActivity {

  private Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_moghadame);

    toolbar=(Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

    TextView txtmatn=(TextView) findViewById(R.id.txtmatn);
    txtmatn.setMovementMethod(LinkMovementMethod.getInstance());
    txtmatn.setText(Html.fromHtml(getString(R.string.moghadame)));
    Typeface Tf=Typeface.createFromAsset(getAssets(),"font.ttf");
    txtmatn.setTypeface(Tf);
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
}
