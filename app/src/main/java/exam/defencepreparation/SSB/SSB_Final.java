package exam.defencepreparation.SSB;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import exam.defencepreparation.R;


public class SSB_Final extends AppCompatActivity {

    TabLayout mtab;
    ViewPager mpage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defence__blog);


        mtab=findViewById(R.id.tab1);
        mpage=findViewById(R.id.pager1);

        MyPageAdapter adapter=new MyPageAdapter(getSupportFragmentManager());

        adapter.getFragment(new SSB(),"Chat");
        adapter.getFragment(new OIR(),"Chat");
        adapter.getFragment(new PPDT(),"Chat");
        adapter.getFragment(new WAT(),"Contact");
        adapter.getFragment(new SRT(),"Status");
        mpage.setAdapter(adapter);
        mtab.setupWithViewPager(mpage);


    }
}
