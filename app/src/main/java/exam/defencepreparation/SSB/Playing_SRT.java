package exam.defencepreparation.SSB;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import exam.defencepreparation.Quiz.Common.Common;
import exam.defencepreparation.R;

public class Playing_SRT extends AppCompatActivity implements View.OnClickListener{

    final static long INTERVAL = 3000;
    final static long TIMEOUT = 15000;
    int progressValue = 0;

    CountDownTimer countDownTimer;

    int index = 0,score = 0,thisQuestion = 0,totalQuestion,correctAnswer;
    ProgressBar progressBar;
    ImageView questionImage;
    Button btnA,btnB,btnC,btnD,continu;
    AdView mAdView;

    TextView txtScore,txtQuestionNum,questionText,correct_ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playing_srt);

        mAdView = (AdView)findViewById(R.id.adView);
        txtScore = findViewById(R.id.txtScore);
        correct_ans = findViewById(R.id.answer_explain);
        txtQuestionNum = findViewById(R.id.txtTotalQuestion);
        questionText = findViewById(R.id.question_text);
        questionImage = findViewById(R.id.question_image);

        progressBar = findViewById(R.id.progressBar);
        btnA = findViewById(R.id.btnAnswerA);
        continu = findViewById(R.id.continu);
        btnB = findViewById(R.id.btnAnswerB);
        btnC = findViewById(R.id.btnAnswerC);
        btnD = findViewById(R.id.btnAnswerD);
        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);

        //  continu.setOnClickListener(this);
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
                // Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onAdFailedToLoad(int errorCode) {
                //Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onAdLeftApplication() {
                //Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });

        mAdView.loadAd(adsRequest);
    }


    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }



    @Override
    public void onClick(View view) {
        Button clickdButton = (Button)view;

        countDownTimer.cancel();
        if (index < totalQuestion){
            if (clickdButton.getText().equals(Common.questionList.get(index).getCorrectAnswer())){
                score+= 10;
                correctAnswer++;
                showQuestion(++index);
            }
            else {
                btnA.setVisibility(View.GONE);
                btnB.setVisibility(View.GONE);
                btnC.setVisibility(View.GONE);
                btnD.setVisibility(View.GONE);

                correct_ans.setVisibility(View.VISIBLE);
                correct_ans.setText(Common.questionList.get(index).getCorrect());
                continu.setVisibility(View.VISIBLE);
                continu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showQuestion(++index);
                    }
                });
            }
        }

        else if(index  == totalQuestion){
            if (clickdButton.getText().equals(Common.questionList.get(index).getCorrectAnswer())) {
                score += 10;
                correctAnswer++;
                showQuestion(++index);
            }
            else {
                Intent intent = new Intent(this,SSB_Final.class);
                Bundle dataSend = new Bundle();
                dataSend.putInt("SCORE",score);
                dataSend.putInt("TOTAL",totalQuestion);
                dataSend.putInt("CORRECT",correctAnswer);
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();
            } }

            txtScore.setText(String.format("%d",score));
    }
    private void showQuestion(int index) {
        if (index < totalQuestion){
            thisQuestion++;
            txtQuestionNum.setText(String.format("%d / %d",thisQuestion,totalQuestion));
            progressBar.setProgress(0);
            progressValue=0;

            btnA.setVisibility(View.VISIBLE);
            btnB.setVisibility(View.VISIBLE);
            btnC.setVisibility(View.VISIBLE);
            btnD.setVisibility(View.VISIBLE);
            continu.setVisibility(View.GONE);

            if (Common.questionList.get(index).getIsImageQuestion().equals("T")) {
                Picasso.with(getBaseContext())
                        .load(Common.questionList.get(index).getImage())
                        .into(questionImage);
                questionText.setText(Common.questionList.get(index).getQuestion());
                questionImage.setVisibility(View.VISIBLE);
                questionText.setVisibility(View.VISIBLE);
                correct_ans.setVisibility(View.GONE);

            }
            else {
                questionText.setText(Common.questionList.get(index).getQuestion());
                questionImage.setVisibility(View.GONE);
                questionText.setVisibility(View.VISIBLE);
                correct_ans.setVisibility(View.GONE);
            }
            btnA.setText(Common.questionList.get(index).getAnswerA());
            btnB.setText(Common.questionList.get(index).getAnswerB());
            btnC.setText(Common.questionList.get(index).getAnswerC());
            btnD.setText(Common.questionList.get(index).getAnswerD());

            countDownTimer.start();
        }
        else {
            Intent intent = new Intent(this,SSB_Final.class);
            Bundle dataSend = new Bundle();
            dataSend.putInt("SCORE",score);
            dataSend.putInt("TOTAL",totalQuestion);
            dataSend.putInt("CORRECT",correctAnswer);
            intent.putExtras(dataSend);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mAdView != null) {
           mAdView.resume();
        }
        totalQuestion = Common.questionList.size();
        countDownTimer = new CountDownTimer(TIMEOUT,INTERVAL) {
            @Override
            public void onTick(long minisec) {
                progressBar.setProgress(progressValue);
                progressValue++;
            }
            @Override
            public void onFinish() {
                countDownTimer.cancel();
                showQuestion(++index);
            }
        };
        showQuestion(index);
    }

    @Override
    public void onBackPressed() {
         finish();
    }
}