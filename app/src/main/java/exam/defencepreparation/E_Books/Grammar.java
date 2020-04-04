package exam.defencepreparation.E_Books;


import android.app.Dialog;
import android.os.Bundle;

import android.text.Html;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import exam.defencepreparation.R;
import exam.defencepreparation.news.NewsDetail;

import static exam.defencepreparation.R.layout.pdffile;

/**
 * A simple {@link Fragment} subclass.
 */
public class Grammar extends Fragment {

    AdView mAdView;

    public Grammar() {
        // Required empty public constructor
    }

    Dialog myDialog;
    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myDialog = new Dialog(getActivity());
        View view=inflater.inflate(R.layout.english_youtube, container, false);
        TextView textView=(TextView)view.findViewById(R.id.text_1);
        TextView textView2=(TextView)view.findViewById(R.id.text_2);
        TextView textView3=(TextView)view.findViewById(R.id.text_3);
        textView.setText("Important Topics Pdf");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Notes_pdf");
        mDatabase.keepSynced(true);


        mRecyclerView=(RecyclerView)view.findViewById(R.id.rec_1);
        mRecyclerView.hasFixedSize();

        LinearLayoutManager mLayoutManger = new LinearLayoutManager(this.getActivity());
        mLayoutManger.setReverseLayout(true);
        mLayoutManger.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLayoutManger);
        mAdView = (AdView) view.findViewById(R.id.adView);
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
        FirebaseRecyclerAdapter<NewsDetail, vocab.MyViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<NewsDetail, vocab.MyViewHolder>
                (NewsDetail.class ,pdffile, vocab.MyViewHolder.class,mDatabase) {

            @Override
            protected void populateViewHolder(vocab.MyViewHolder viewHolder, final NewsDetail model, int position) {
                viewHolder.setTopic(model.getTopic());
                viewHolder.setDetail(model.getDetail());

            }
        };
        firebaseRecyclerAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);


        // for second recycler view code here



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
            TextView Topic=(TextView)mView.findViewById(R.id.news_title);
            Topic.setText(topic);
        }

        public void setDetail(String detail){
            post_desc = (TextView)mView.findViewById(R.id.news_desc);
            //   post_desc.setText(detail);
            post_desc.setText(Html.fromHtml(detail));
            Linkify.addLinks(post_desc, Linkify.ALL);
        }
    }


}

