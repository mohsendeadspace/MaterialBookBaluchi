package com.brakets.app.baloch;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by MoHseN.DeaDSpacE on 17/08/2016.
 */
public class ListMatalbActivity extends AppCompatActivity {


    ListView listView;
    private DataBase db;
    private List<HashMap<String,Object>> books_list;
    private Toolbar toolbar;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmataleb);

        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


        listView=(ListView)findViewById(R.id.listView);
        db=new DataBase(getBaseContext());
        db.open();

            books_list=db.getTableofContent();
            String[] from={"title","author","fav_flag","see_flag"};
              int[] to={R.id.txttitle,R.id.txtauthor,R.id.setfav,R.id.setsee};
        SimpleAdapter adb=new SimpleAdapter(getBaseContext(),books_list,R.layout.tbl_content_list_row,from,to);
        listView.setAdapter(adb);

        db.close();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent matn=new Intent(ListMatalbActivity.this, Matn.class);
                String my_id=books_list.get(position).get("id").toString();
                matn.putExtra("id",my_id);
                startActivity(matn);

            }
        });

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
