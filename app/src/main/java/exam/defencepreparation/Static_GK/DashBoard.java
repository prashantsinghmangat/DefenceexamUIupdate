package exam.defencepreparation.Static_GK;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.messaging.FirebaseMessaging;

import exam.defencepreparation.CAPF_AC_2020.Capf_ac_2020_home;
import exam.defencepreparation.R;
import exam.defencepreparation.SSB.SSB_Final;
import exam.defencepreparation.defence_blog.Defence_Blog;
import exam.defencepreparation.news.News_Daily;


public class DashBoard extends AppCompatActivity {
    CardView mycard1, mycard2, mycard3, mycard4, mycard5;
    Intent i, ii, iii, iiii, iiiii;
    LinearLayout ll;
    Dialog myDialog;
    AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        FirebaseMessaging.getInstance().subscribeToTopic("NEWS");

        myDialog = new Dialog(this);

      //  ll = (LinearLayout) findViewById(R.id.ll);
        mycard1 = (CardView) findViewById(R.id.bankcardId);
        mycard2 = (CardView) findViewById(R.id.defenceinside);
        mycard3 = (CardView) findViewById(R.id.ssb);
        mycard4 = (CardView) findViewById(R.id.video);
        mycard5 = (CardView) findViewById(R.id.shares);


        i = new Intent(this, News_Daily.class);
        ii = new Intent(this, Defence_Blog.class);
        iii = new Intent(this, SSB_Final.class);
        iiii = new Intent(this, Capf_ac_2020_home.class);
        iiiii = new Intent(this,DashBoard_Video.class);




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
                    startActivity(iii);
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
              //  Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                //Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
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


    @Override
    public void onBackPressed() {
        finish();
    }
}

