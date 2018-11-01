package s2017s25.kr.hs.mirim.waterqualitynotice;

import android.app.Activity;
import android.content.Intent;
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
    String waterGrade = "A";

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

        switch (waterGrade){
            case "A":
                waterGradeImg.setImageDrawable(getResources().getDrawable(R.drawable.a, getApplicationContext().getTheme()));
                waterGradeDesc.setText(R.string.water_rank_a_desc);
                waterGradeName.setText(R.string.water_rank_a);
                break;
            case "B":
                waterGradeImg.setImageDrawable(getResources().getDrawable(R.drawable.b, getApplicationContext().getTheme()));
                waterGradeDesc.setText(R.string.water_rank_b_desc);
                waterGradeName.setText(R.string.water_rank_b);
                break;
            case "C":
                waterGradeImg.setImageDrawable(getResources().getDrawable(R.drawable.c, getApplicationContext().getTheme()));
                waterGradeDesc.setText(R.string.water_rank_c_desc);
                waterGradeName.setText(R.string.water_rank_c);
                break;
        }

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });



    }
}
