package s2017s25.kr.hs.mirim.waterqualitynotice;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends Activity {
    ImageButton btnSearch;
    ImageView waterGradeImg;
    TextView waterGradeName;
    TextView waterGradeDesc;
    String waterGrade = "B";
    private String locationProvider = null;
    private Location lastKnownLocation = null;
    private double latitude;
    private double longitude;
    String address;
    TextView addressTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // custom title bar
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(), "현재 위치 데이터를 불러오는 중입니다.", Toast.LENGTH_LONG).show();
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);

        btnSearch = findViewById(R.id.btn_search);
        waterGradeDesc = findViewById(R.id.water_grade_desc);
        waterGradeName = findViewById(R.id.water_grade_name);
        waterGradeImg = findViewById(R.id.water_grade_img);
        addressTxt = findViewById(R.id.txt_address);

        locationProvider = ("GPS").toLowerCase();

        // Update location to get.
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        lm.removeUpdates(locationListener);    // Stop the update if it is in progress.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(locationProvider, 0, 0, locationListener);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });
        waterGradeSetting(waterGrade);

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
    /**
     * 위도,경도로 주소구하기
     * @param lat
     * @param lng
     * @return 주소
     */
    public static String getAddress(Context mContext, double lat, double lng) {
        String nowAddress ="현재 위치를 확인 할 수 없습니다.";
        Geocoder geocoder = new Geocoder(mContext, Locale.KOREA);
        List<Address> address;
        try {
            if (geocoder != null) {
                //세번째 파라미터는 좌표에 대해 주소를 리턴 받는 갯수로
                //한좌표에 대해 두개이상의 이름이 존재할수있기에 주소배열을 리턴받기 위해 최대갯수 설정
                address = geocoder.getFromLocation(lat, lng, 1);

                if (address != null && address.size() > 0) {
                    // 주소 받아오기
                    String currentLocationAddress = address.get(0).getAddressLine(0).toString();
                    nowAddress  = currentLocationAddress;

                }
            }

        } catch (IOException e) {
            Toast.makeText(mContext, "주소를 가져 올 수 없습니다.", Toast.LENGTH_LONG).show();

            e.printStackTrace();
        }
        return nowAddress;
    }

    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            LocationManager lm = (LocationManager)getSystemService(Context. LOCATION_SERVICE);

            // Get the last location, and update UI.
            lastKnownLocation = location;
            latitude = lastKnownLocation .getLatitude();
            longitude = lastKnownLocation .getLongitude();
            address = getAddress(getApplicationContext(),latitude, longitude);
            addressTxt.setText(address);
            Toast.makeText(getApplicationContext(), "현재 위치 데이터를 성공적으로 불러왔습니다.", Toast.LENGTH_LONG).show();

            // Stop the update to prevent changing the location.
            lm.removeUpdates( this );
        }

        @Override
        public void onProviderDisabled(String provider) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}

    };

}
