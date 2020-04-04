package exam.defencepreparation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dmax.dialog.SpotsDialog;
import exam.defencepreparation.news.NewsDetail;
import exam.defencepreparation.news.News_Daily;

import static exam.defencepreparation.R.layout.interface_news;


public class New_JOB extends AppCompatActivity {
    AdView mAdView;
    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabase;
    TextView read;
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_airforce);
        dialog = new SpotsDialog(New_JOB.this);
        dialog.show();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("JOB");
        mDatabase.keepSynced(true);
        mRecyclerView=(RecyclerView)findViewById(R.id.rec_airforce);

        // LinearLayout layout=(LinearLayout)findViewById(R.id.linearLayout);
        mRecyclerView.hasFixedSize();
        LinearLayoutManager mLayoutManger = new LinearLayoutManager(this);
        mLayoutManger.setReverseLayout(true);
        mLayoutManger.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLayoutManger);

        //banner ads code here


        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdClosed() {
                // Toast.makeText(getActivity(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Toast.makeText(getActivity(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                //  Toast.makeText(getActivity(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });

        mAdView.loadAd(adRequest1);




    }
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
    public void onStart() {
        super.onStart();


        // Toast.makeText(News_Daily.this, "Swipe Left", Toast.LENGTH_SHORT).show();

        FirebaseRecyclerAdapter<NewsDetail, New_JOB.MyViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<NewsDetail, New_JOB.MyViewHolder>
                (NewsDetail.class , interface_news, New_JOB.MyViewHolder.class,mDatabase) {

            @Override
            protected void populateViewHolder(New_JOB.MyViewHolder viewHolder, final NewsDetail model, int position) {

                viewHolder.setTopic(model.getTopic());
                viewHolder.setDetail(model.getDetail());
                viewHolder.setDate(model.getDate());
                viewHolder.setImage(getApplicationContext(), model.getImage());
                dialog.dismiss();

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String topic="";
                        String detail="";
                        String date="";
                        String image="";
                        String link="JOB";


                        topic=model.getTopic();
                        detail=model.getDetail();
                        date=model.getDate();
                        image=model.getImage();



                        Intent imgFullScrn = new Intent(New_JOB.this, Rec_htmlView.class);

                        imgFullScrn.putExtra("topic",topic);
                        imgFullScrn.putExtra("detail",detail);
                        imgFullScrn.putExtra("date",date);
                        imgFullScrn.putExtra("image",image);
                        imgFullScrn.putExtra("datalink",link);

                        startActivity(imgFullScrn);
                    }
                });

            }
        };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    static  public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView post_desc;
        View mView;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;
        }

        public void setTopic(String topic)
        {
            TextView Topic=(TextView)mView.findViewById(R.id.news_title);
            Topic.setText(topic);
        }

        public void setDetail(String detail){
            post_desc = (TextView)mView.findViewById(R.id.news_desc);
            post_desc.setText(detail);

        }

        public void setDate(String date){
            TextView  Date = (TextView)mView.findViewById(R.id.date);
            Date.setText(date);
        }

        public void setImage(final Context ctx, final String image){
            final KenBurnsView post_image=(KenBurnsView) mView.findViewById(R.id.new_pic);
            Picasso.with(ctx).load(image).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.loadingpic).into(post_image, new Callback() {
                @Override
                public void onSuccess() {

                }
                @Override
                public void onError() {
                    Picasso.with(ctx).load(image).placeholder(R.drawable.loadingpic).into(post_image);

                }
            });
        }
    }
}

