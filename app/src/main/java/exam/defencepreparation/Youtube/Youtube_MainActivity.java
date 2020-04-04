package exam.defencepreparation.Youtube;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import exam.defencepreparation.R;
import exam.defencepreparation.SSB.OIR;
import exam.defencepreparation.common.Config;
import exam.defencepreparation.news.NewsDetail;

import static exam.defencepreparation.R.layout.grid_youtube_3;
import  static exam.defencepreparation.R.layout.youtube_rec_design;

public class Youtube_MainActivity extends YouTubeBaseActivity {
    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabase;
    private static final String TAG = Youtube_MainActivity.class.getSimpleName();
    private String videoID,databaseID,About,Date;
    private YouTubePlayerView youTubePlayerView;
    TextView about, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube__main);
        videoID = getIntent().getStringExtra("video_id");
        About = getIntent().getStringExtra("detail");
        Date = getIntent().getStringExtra("date");


        databaseID = getIntent().getStringExtra("database_id");

        youTubePlayerView = findViewById(R.id.youtube_player_view);
        initializeYoutubePlayer();

        about=findViewById(R.id.topic1);
        about.setText(About);
        date=findViewById(R.id.time);
        date.setText(Date);

        // recycler_View
        mDatabase = FirebaseDatabase.getInstance().getReference(databaseID);
        mDatabase.keepSynced(true);
        mRecyclerView=(RecyclerView)findViewById(R.id.rec_youtube);
        mRecyclerView.hasFixedSize();
        LinearLayoutManager mLayoutManger = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,true);
        mLayoutManger.setReverseLayout(true);
        mLayoutManger.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLayoutManger);


    }

    // back press

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /**
     * initialize the youtube player
     */
    private void initializeYoutubePlayer() {
        youTubePlayerView.initialize(Config.DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer,
                                                boolean wasRestored) {

                //if initialization success then load the video id to youtube player
                if (!wasRestored) {
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    youTubePlayer.loadVideo(videoID);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                //print or show error if initialization failed
                Log.e(TAG, "Youtube_Model_All Player View initialization failed");
            }
        });
    }

    // getting data from server
    @Override
    public void onStart() {
        super.onStart();



        FirebaseRecyclerAdapter<NewsDetail, OIR.MyViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<NewsDetail, OIR.MyViewHolder>
                (NewsDetail.class ,grid_youtube_3, OIR.MyViewHolder.class,mDatabase) {

            @Override
            protected void populateViewHolder(OIR.MyViewHolder viewHolder, final NewsDetail model, int position) {
                viewHolder.setTopic(model.getTopic());
                viewHolder.setDetail(model.getDetail());
                viewHolder.setDate(model.getDate());
                viewHolder.setImage(getApplicationContext(), model.getImage());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String topic="";
                        String detail="";
                        String date="";
                        String image="";


                        topic=model.getTopic();
                        detail=model.getDetail();
                        date=model.getDate();
                        image=model.getImage();

                        Intent imgFullScrn = new Intent(Youtube_MainActivity.this, Youtube_MainActivity.class);
                        imgFullScrn.putExtra("video_id",topic);
                        imgFullScrn.putExtra("detail",detail);
                        imgFullScrn.putExtra("date",date);

                        imgFullScrn.putExtra("database_id",databaseID);

                        startActivity(imgFullScrn);
                    }
                });

            }
        };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

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