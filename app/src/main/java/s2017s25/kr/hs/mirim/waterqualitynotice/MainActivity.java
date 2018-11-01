package s2017s25.kr.hs.mirim.waterqualitynotice;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
    ImageButton btnSearch;
    ImageView waterGradeImg;
    TextView waterGradeName;
    TextView waterGradeDesc;
    String waterGrade = "B";
    //위치정보 객체
    LocationManager lm = null;
    //위치정보 장치 이름
    String provider = null;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // custom title bar
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);

        btnSearch=findViewById(R.id.btn_search);
        waterGradeDesc=findViewById(R.id.water_grade_desc);
        waterGradeName=findViewById(R.id.water_grade_name);
        waterGradeImg=findViewById(R.id.water_grade_img);

        waterGradeSetting(waterGrade);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });



    }

    public void waterGradeSetting(String waterGrade){

        switch (waterGrade){
            case "A":
                waterGradeImg.setImageDrawable(getResources().getDrawable(R.drawable.a, getApplicationContext().getTheme()));
                waterGradeDesc.setText(R.string.water_rank_a_desc);
                waterGradeName.setText(R.string.water_rank_a);
                waterGradeName.setTextColor(getResources().getColor(R.color.waterRankA, getApplicationContext().getTheme()));
                break;
            case "B":
                waterGradeImg.setImageDrawable(getResources().getDrawable(R.drawable.b, getApplicationContext().getTheme()));
                waterGradeDesc.setText(R.string.water_rank_b_desc);
                waterGradeName.setText(R.string.water_rank_b);
                waterGradeName.setTextColor(getResources().getColor(R.color.waterRankB, getApplicationContext().getTheme()));
                break;
            case "C":
                waterGradeImg.setImageDrawable(getResources().getDrawable(R.drawable.c, getApplicationContext().getTheme()));
                waterGradeDesc.setText(R.string.water_rank_c_desc);
                waterGradeName.setText(R.string.water_rank_c);
                waterGradeName.setTextColor(getResources().getColor(R.color.waterRankC, getApplicationContext().getTheme()));
                break;
        }
    }
}
