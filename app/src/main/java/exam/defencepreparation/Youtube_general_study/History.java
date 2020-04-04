package exam.defencepreparation.Youtube_general_study;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import exam.defencepreparation.R;
import exam.defencepreparation.Youtube.Youtube_MainActivity;
import exam.defencepreparation.news.NewsDetail;

import  static exam.defencepreparation.R.layout.grid_youtube_3;

/**
 * A simple {@link Fragment} subclass.
 */
public class History extends Fragment {

     AdView mAdView;
    public History() {
        // Required empty public constructor
    }

    Dialog myDialog;
    private RecyclerView mRecyclerView,nRecyclerView,oRecyclerView;
    private DatabaseReference mDatabase,nDatabase,oDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myDialog = new Dialog(getActivity());
        View view=inflater.inflate(R.layout.english_youtube, container, false);
        TextView textView=(TextView)view.findViewById(R.id.text_1);
        TextView textView2=(TextView)view.findViewById(R.id.text_2);
        TextView textView3=(TextView)view.findViewById(R.id.text_3);
        textView.setText("Modern history ");
        textView2.setText("Medieval history");
        textView3.setText("Ancient history ");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Modern");
        mDatabase.keepSynced(true);
        nDatabase = FirebaseDatabase.getInstance().getReference().child("Medieval");
        nDatabase.keepSynced(true);
        oDatabase = FirebaseDatabase.getInstance().getReference().child("geography");
        oDatabase.keepSynced(true);

        mRecyclerView=(RecyclerView)view.findViewById(R.id.rec_1);
        mRecyclerView.hasFixedSize();
        nRecyclerView=(RecyclerView)view.findViewById(R.id.rec_2);
        nRecyclerView.hasFixedSize();
        oRecyclerView=(RecyclerView)view.findViewById(R.id.rec_3);
        oRecyclerView.hasFixedSize();
        LinearLayoutManager mLayoutManger = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, true);
        mLayoutManger.setReverseLayout(true);
        mLayoutManger.setStackFromEnd(true);

        LinearLayoutManager nLayoutManger = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, true);
        nLayoutManger.setReverseLayout(true);
        nLayoutManger.setStackFromEnd(true);

        LinearLayoutManager oLayoutManger = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, true);
        oLayoutManger.setReverseLayout(true);
        oLayoutManger.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLayoutManger);
        nRecyclerView.setLayoutManager(nLayoutManger);
        oRecyclerView.setLayoutManager(oLayoutManger);



        mAdView = (AdView) view.findViewById(R.id.adView);



        AdRequest adRequest1 = new AdRequest.Builder()

                .build();

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


        return view;

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
        FirebaseRecyclerAdapter<NewsDetail, History.MyViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<NewsDetail, History.MyViewHolder>
                (NewsDetail.class ,grid_youtube_3, History.MyViewHolder.class,mDatabase) {

            @Override
            protected void populateViewHolder(History.MyViewHolder viewHolder, final NewsDetail model, int position) {
                viewHolder.setTopic(model.getTopic());
                viewHolder.setDetail(model.getDetail());
                viewHolder.setDate(model.getDate());
                viewHolder.setImage(getActivity().getApplicationContext(), model.getImage());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String topic="";
                        String detail="";
                        String date="";
                        String image="";
                        String link="Modern";


                        topic=model.getTopic();
                        detail=model.getDetail();
                        date=model.getDate();
                        image=model.getImage();

                        Intent imgFullScrn = new Intent(getActivity(), Youtube_MainActivity.class);
                        imgFullScrn.putExtra("video_id",topic);
                        imgFullScrn.putExtra("database_id",link);
                        imgFullScrn.putExtra("date",date);
                        imgFullScrn.putExtra("detail",detail);
                        startActivity(imgFullScrn);
                    }



                });

            }
        };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);


        // for second recycler view code here

        super.onStart();
        FirebaseRecyclerAdapter<NewsDetail, History.MyViewHolder> firebaseRecyclerAdapter2=new FirebaseRecyclerAdapter<NewsDetail, History.MyViewHolder>
                (NewsDetail.class ,grid_youtube_3, History.MyViewHolder.class,nDatabase) {

            @Override
            protected void populateViewHolder(History.MyViewHolder viewHolder, final NewsDetail model, int position) {
                viewHolder.setTopic(model.getTopic());
                viewHolder.setDetail(model.getDetail());
                viewHolder.setDate(model.getDate());
                viewHolder.setImage(getActivity().getApplicationContext(), model.getImage());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String topic="";
                        String detail="";
                        String date="";
                        String image="";
                        String link="Medieval";


                        topic=model.getTopic();
                        detail=model.getDetail();
                        date=model.getDate();
                        image=model.getImage();

                        Intent imgFullScrn = new Intent(getActivity(), Youtube_MainActivity.class);
                        imgFullScrn.putExtra("video_id",topic);
                        imgFullScrn.putExtra("database_id",link);
                        imgFullScrn.putExtra("date",date);
                        imgFullScrn.putExtra("detail",detail);
                        startActivity(imgFullScrn);
                    }



                });

            }
        };

        nRecyclerView.setAdapter(firebaseRecyclerAdapter2);

        // for second recycler view code here

        super.onStart();

        FirebaseRecyclerAdapter<NewsDetail, MyViewHolder> firebaseRecyclerAdapter3=new FirebaseRecyclerAdapter<NewsDetail, MyViewHolder>
                (NewsDetail.class ,grid_youtube_3, History.MyViewHolder.class,oDatabase) {

            @Override
            protected void populateViewHolder(History.MyViewHolder viewHolder, final NewsDetail model, int position) {
                viewHolder.setTopic(model.getTopic());
                viewHolder.setDetail(model.getDetail());
                viewHolder.setDate(model.getDate());
                viewHolder.setImage(getActivity().getApplicationContext(), model.getImage());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String topic="";
                        String detail="";
                        String date="";
                        String image="";
                        String link="geography";


                        topic=model.getTopic();
                        detail=model.getDetail();
                        date=model.getDate();
                        image=model.getImage();

                        Intent imgFullScrn = new Intent(getActivity(), Youtube_MainActivity.class);
                        imgFullScrn.putExtra("video_id",topic);
                        imgFullScrn.putExtra("database_id",link);
                        imgFullScrn.putExtra("date",date);
                        imgFullScrn.putExtra("detail",detail);
                        startActivity(imgFullScrn);
                    }



                });

            }
        };

        oRecyclerView.setAdapter(firebaseRecyclerAdapter3);

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView post_desc;
        public   View mView;
        public MyViewHolder(View itemView)


        {
            super(itemView);
            mView=itemView;

        }

        public void setTopic(String topic)
        {
            TextView Topic=(TextView)mView.findViewById(R.id.topic1);
            Topic.setText(topic);
        }

        public void setDetail(String detail){
            post_desc = (TextView)mView.findViewById(R.id.tittle);
            post_desc.setText(detail);
        }

        public void setDate(String date){
            TextView  Date = (TextView)mView.findViewById(R.id.time);
            Date.setText(date);
        }

        public void setImage(final Context ctx, final String image){
            final ImageView post_image=(ImageView) mView.findViewById(R.id.card_image);
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

