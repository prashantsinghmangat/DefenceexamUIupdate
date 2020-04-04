package exam.defencepreparation.news;


import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import exam.defencepreparation.R;

public class News_Daily extends AppCompatActivity {
    TabLayout mtab;
    ViewPager mpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defence__blog);


        mtab=findViewById(R.id.tab1);
        mpage=findViewById(R.id.pager1);

        MyPageAdapter adapter=new MyPageAdapter(getSupportFragmentManager());


        adapter.getFragment(new Army(),"army");
        adapter.getFragment(new Airforce(),"airforce");
        adapter.getFragment(new Navy(),"navy");
        //adapter.getFragment(new Bsf(),"navy");
        mpage.setAdapter(adapter);
        mtab.setupWithViewPager(mpage);



    }
}
