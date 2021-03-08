package com.brakets.app.baloch;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by MoHseN.DeaDSpacE on 17/08/2016.
 */
public class SearchActivity extends AppCompatActivity {


    private DataBase db;
    ListView resulteSearch;
    private List<HashMap<String, Object>> resulte_books;
    private Toolbar toolbar;
    Button Search;


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Search=(Button) findViewById(R.id.Search);
        Search.setAnimation(AnimationUtils.loadAnimation(this,R.anim.rotate));

        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RadioButton rbTitle = (RadioButton) findViewById(R.id.srtitle);
        rbTitle.setAnimation(AnimationUtils.loadAnimation(this,R.anim.trans_four));

        RadioButton rbContent = (RadioButton) findViewById(R.id.srcontent);

        rbContent.setAnimation(AnimationUtils.loadAnimation(this,R.anim.trans_five));
        RadioButton rbAuthor = (RadioButton) findViewById(R.id.srauthor);

        rbAuthor.setAnimation(AnimationUtils.loadAnimation(this,R.anim.trans_six));

        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


        resulteSearch = (ListView) findViewById(R.id.resulteSearch);

        db = new DataBase(getBaseContext());


    }


    public void btnSearch(View v)
    {
        resulteSearch.setAdapter(null);

        RadioGroup rGroup = (RadioGroup) findViewById(R.id.radioGroup);

        int selected_item_id = rGroup.getCheckedRadioButtonId();

        RadioButton rbTitle = (RadioButton) findViewById(R.id.srtitle);
        RadioButton rbContent = (RadioButton) findViewById(R.id.srcontent);
        RadioButton rbAuthor = (RadioButton) findViewById(R.id.srauthor);

        String search_by = "";

        if( selected_item_id == rbTitle.getId() ) {
            search_by = "title";
        } else if( selected_item_id == rbContent.getId() ) {
            search_by = "content";
        } else if( selected_item_id == rbAuthor.getId() ) {
            search_by = "author";
        }

        EditText etSearchKey = (EditText) findViewById(R.id.edtsearch);

        if( etSearchKey.getText().length() < 1 )
        {
            Toast.makeText(getApplicationContext(),"متن مورد نظر را وارد نمایید",Toast.LENGTH_SHORT).show();

            return;
        }

        String key = etSearchKey.getText().toString().trim();

        String[] keys = key.split("\\s+");

        String query = search_by + " LIKE '%" + keys[0] + "%'";

        StringBuilder sb = new StringBuilder();

        for( int i = 1; i < keys.length; i ++ )
        {
            sb.append( " OR " + search_by + " LIKE '%" + keys[i] + "%'" );
        }

        query = query + sb.toString();



        showResultOfSearch( query );

    }


    public  void showResultOfSearch(String quary)
    {
            db.open();


        resulte_books=db.getTableOfResulteSearch(quary);


            db.close();

        if(resulte_books.size()<1)
        {
            Toast.makeText(getApplicationContext(),"جستجو بی نتیجه بود !!!",Toast.LENGTH_SHORT).show();
            return;
        }


        String[] from={"title","author","fav_flag","see_flag"};
        int[] to={R.id.txttitle,R.id.txtauthor,R.id.setfav,R.id.setsee};
        SimpleAdapter adb=new SimpleAdapter(getBaseContext(),resulte_books,R.layout.tbl_content_list_row,from,to);
        resulteSearch.setAdapter(adb);



        resulteSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent matn=new Intent(SearchActivity.this, Matn.class);
                String my_id=resulte_books.get(position).get("id").toString();
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
