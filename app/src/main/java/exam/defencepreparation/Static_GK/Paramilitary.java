package exam.defencepreparation.Static_GK;

import android.os.Bundle;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import exam.defencepreparation.E_Books.Grammar;
import exam.defencepreparation.E_Books.MyPageAdapter_English;
import exam.defencepreparation.R;


public class Paramilitary extends AppCompatActivity {
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


        adapter.getFragment(new Airforce(),"army");
        adapter.getFragment(new Grammar(),"airforce");

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
