package exam.defencepreparation.Static_GK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import exam.defencepreparation.R;

public class About extends AppCompatActivity {
    TextView contact;
    Button logout;
    private FirebaseAuth mAuth;

    ImageView whatsapp , instagram , teligram;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        logout=findViewById(R.id.rate);
        Button btn_share=(Button)findViewById(R.id.shareit);
        mAuth = FirebaseAuth.getInstance();

        btn_share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shareIt();
            }
        });

        whatsapp = (ImageView)findViewById(R.id.whatsapp);
        instagram=(ImageView)findViewById(R.id.instagram);
        teligram=(ImageView)findViewById(R.id.teligram);


        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/917888522842")));


            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/defence_inside/")));
            }
        });

        teligram.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/joinchat/Nuw66w3_EVB7vIi231IA5A")));

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   mAuth.signOut();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=exam.defencepreparation")));
                //Intent intent= new Intent(About.this, Welcome.class);
                //startActivity(intent);
            }
        });



    }
    private void shareIt() {
        //sharing implementation here
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "ArmedForces");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "Prepare youself for various defence examination and interviews.visit https://play.google.com/store/apps/details?id=exam.defencepreparation");
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}