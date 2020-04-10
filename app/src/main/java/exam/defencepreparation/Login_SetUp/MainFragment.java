package exam.defencepreparation.Login_SetUp;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import exam.defencepreparation.CAPF_AC_2020.Capf_ac_2020_home;
import exam.defencepreparation.R;
import exam.defencepreparation.SSB.SSB_Final;
import exam.defencepreparation.Static_GK.DashBoard_Video;
import exam.defencepreparation.defence_blog.Defence_Blog;
import exam.defencepreparation.news.News_Daily;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    CardView mycard1, mycard2, mycard3, mycard4, mycard5;
    Intent i, ii, iii, iiii, iiiii;
    LinearLayout ll;
    Dialog myDialog;
    AdView mAdView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trydashboard, container, false);
        mycard1 = (CardView) view.findViewById(R.id.bankcardId);
        mycard2 = (CardView) view.findViewById(R.id.defenceinside);
        mycard3 = (CardView) view.findViewById(R.id.ssb);
        mycard4 = (CardView) view.findViewById(R.id.video);
        mycard5 = (CardView) view.findViewById(R.id.shares);
        mAdView = (AdView)view.findViewById(R.id.adView);
        AdRequest adsRequest = new AdRequest.Builder()

                .build();

        i = new Intent(getActivity(), News_Daily.class);
        ii = new Intent(getActivity(), Defence_Blog.class);
        iii = new Intent(getActivity(), SSB_Final.class);
        iiii = new Intent(getActivity(), Capf_ac_2020_home.class);
        iiiii = new Intent(getActivity(), DashBoard_Video.class);


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

        return view;
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

