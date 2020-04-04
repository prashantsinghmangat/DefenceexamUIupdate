package exam.defencepreparation.Static_GK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import exam.defencepreparation.Previous_year_paper.English_Section;
import exam.defencepreparation.Quiz.Home;
import exam.defencepreparation.R;
import exam.defencepreparation.Youtube_general_study.YouTube_general;


public class DashBoard_Video extends AppCompatActivity {
    CardView mycard1,mycard2,mycard3,mycard4,mycard5,mycard6;
    Intent i,ii,iii,iiii,iiiii,pro;
    LinearLayout ll;
    AdView mAdView;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board__video);

     //   ll = (LinearLayout) findViewById(R.id.ll);
        mycard1 = (CardView) findViewById(R.id.bankcardId);
        mycard2 = (CardView) findViewById(R.id.defenceinside);
        mycard3 = (CardView) findViewById(R.id.promo);
        mycard4 = (CardView) findViewById(R.id.share);
        mycard5 = (CardView) findViewById(R.id.quiz);
        mycard6 = (CardView) findViewById(R.id.download);


        i = new Intent(this,YouTube_general.class);
        ii = new Intent(this, English_Section.class);
       iii = new Intent(this, exam.defencepreparation.E_Books.English_Section.class);
        iiii = new Intent(this,About.class);
        iiiii = new Intent(this,Home.class);



        mycard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
        mycard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ii);
            }
        });
        mycard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/indian_army_inspirational/")));
            }
        });

        mycard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(iiii);
            }
        });
        mycard5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(iiiii);
            }
        });

        mycard6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(iii);
            }
        });






        // ads coding

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adsRequest = new AdRequest.Builder()

                .build();

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdClosed() {
             //   Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
               // Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
               // Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });

        mAdView.loadAd(adsRequest);
    }
    // banner ads


    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

}

