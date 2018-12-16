package lt.ziltom.zilvinastomkevicius.projektas.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import lt.ziltom.zilvinastomkevicius.projektas.R;


public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void buttonAboutBackClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
