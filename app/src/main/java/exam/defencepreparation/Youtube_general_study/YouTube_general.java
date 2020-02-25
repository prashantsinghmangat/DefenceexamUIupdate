package exam.defencepreparation.Youtube_general_study;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import exam.defencepreparation.R;


public class YouTube_general extends AppCompatActivity {


    TabLayout mtab;
    ViewPager mpage;
    InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defence__blog);


        mtab=findViewById(R.id.tab1);
        mpage=findViewById(R.id.pager1);

        MyPageAdapter_General_study adapter=new MyPageAdapter_General_study(getSupportFragmentManager());

        adapter.getFragment(new Science(),"navy");
        adapter.getFragment(new History(),"army");
        adapter.getFragment(new Polity(),"airforce");
        adapter.getFragment(new Geography(),"navy");
        adapter.getFragment(new Econmy(),"navy");

        //adapter.getFragment(new Bsf(),"navy");
        mpage.setAdapter(adapter);
        mtab.setupWithViewPager(mpage);


        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

        AdRequest adRequest = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });



    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

}
