package exam.defencepreparation.Previous_year_paper;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import exam.defencepreparation.R;


public class English_Section extends AppCompatActivity {

    TabLayout mtab;
    ViewPager mpage;
    InterstitialAd mInterstitialAd;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defence__blog);


        mtab=findViewById(R.id.tab1);
        mpage=findViewById(R.id.pager1);

        MyPageAdapter_English adapter=new MyPageAdapter_English(getSupportFragmentManager());


        adapter.getFragment(new vocab(),"army");
        adapter.getFragment(new Grammar(),"airforce");
        adapter.getFragment(new Speech(),"navy");
        adapter.getFragment(new Capf_paper(),"navy");
        mpage.setAdapter(adapter);
        mtab.setupWithViewPager(mpage);
        // Full screen ads


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
