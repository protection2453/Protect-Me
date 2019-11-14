package ModelFile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import andbook.example.protect_me_release.R;

public class TestAllSOS extends AppCompatActivity{

    private String Phone_regex = "^\\d{3}\\d{3,4}\\d{4}$";


    //GPS
    private LocationManager locationManagerGPS;
    static final int REQUEST_CODE_LOCATION=2;
    private Geocoder geocoder;
    private LocationManager locationManager;
    private SmsManager smsManager;
    private String emergency_comment;
    private List<Address> addresses;
    private static String usr_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testall);

        Button Test_All = findViewById(R.id.Test_all_action);
        final EditText TestAll_value = findViewById(R.id.Test_all_edit);
        final Context context = this;

        //GPS ON/OFF 확인하여 OFF일 시 GPS 설정화면으로 이동진행
        locationManagerGPS = (LocationManager)getSystemService(Context.LOCATION_SERVICE);


        //GPS 미 설정시 false 설정 시 true
        if (!locationManagerGPS.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //GPS 설정 off일 시 설정화면으로 이동
            new AlertDialog.Builder(this, R.style.MyCustomDialogStyle)
                    .setTitle("GPS 미설정")
                    .setIcon(R.drawable.icon96)
                    .setMessage("GPS가 미설정 되어 위급 시 위치추적에 어려움이 있으니 GPS 사용을 허용해주세요\n" +
                            "허용한 뒤, 원활한 사용을 위해 앱을 다시 켜주십시오.")
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent setting = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            setting.addCategory(Intent.CATEGORY_DEFAULT);
                            startActivity(setting);
                            finish();
                        }
                    }).show();

        } else //GPS 설정 on일 시 기능 수행 가능
        {
            Test_All.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Pattern p = Pattern.compile(Phone_regex);
                    Matcher m = p.matcher(TestAll_value.getText().toString());
                    if(TestAll_value.getText().toString().getBytes().length > 0){
                        if(Pattern.matches(Phone_regex,TestAll_value.getText().toString())){
                            //문자 신고
                            usr_phone=TestAll_value.getText().toString(); //gps수신 후 테스트 전송을 위해 usr 전화번호 입력
                            AutoSMS_Location_Transmission();
                            //비명소리
                            Intent scrum = new Intent(getApplicationContext(), TestScrum.class);
                            startService(scrum);
                            stopService(scrum);
                            //녹음
                            Intent audio = new Intent(getApplicationContext(), TestAudio.class);
                            startService(audio);
                            stopService(audio);
                        }
                        else{
                            new AlertDialog.Builder(context, R.style.MyCustomDialogStyle)
                                    .setTitle("입력값 오류")
                                    .setIcon(R.drawable.icon96)
                                    .setMessage("테스트하고자 하는 번호를 올바르게 입력해주세요")
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    }).show();
                        }
                    }
                    else{
                        new AlertDialog.Builder(context, R.style.MyCustomDialogStyle)
                                .setTitle("입력값 오류")
                                .setIcon(R.drawable.icon96)
                                .setMessage("테스트하고자 하는 번호를 올바르게 입력해주세요")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).show();
                    }
                }
            });
        }
    }



    //자동 112 문자 신고 접수 [리스너를 통해서 위치값으 업데이트 하여 위치 수신 진행1]
    private LocationListener locationListener = new LocationListener()
    {
        //위치값이 갱신되면 이벤트 발생
        //위치 제공자 GPS:위성 수신으로 정확도가 높다, 실내사용 불가,위치 제공자 Network:인터넷 엑세스 수신으로 정확도가 아쉽다, 실내 사용 가능
        @SuppressLint("UnlocalizedSms")
        @Override
        public void onLocationChanged(Location location) {

            locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            smsManager = SmsManager.getDefault();
            geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(
                        location.getLatitude(), location.getLongitude(), 1
                );
            } catch (IOException e) {
                System.err.println("IOException error");
            }
            if (addresses == null)
                emergency_comment = "도와주세요 빨리 이리로 와주세요 제 위치는 " + location.getLongitude() + " , " + location.getLatitude() + "입니다 빨리와주세요.";
            else
                emergency_comment = "도와주세요 빨리 이리로 와주세요 제 위치는 " + addresses.get(0).getAddressLine(0) + "입니다 빨리와주세요.";

            //Toast.makeText(getApplicationContext(), emergency_comment+usr_phone, Toast.LENGTH_SHORT).show();
            smsManager.sendTextMessage(usr_phone, null, emergency_comment, null, null);
            Toast.makeText(getApplicationContext(), "문자신고 완료(나를 지켜줘)", Toast.LENGTH_SHORT).show();
            locationManager.removeUpdates(locationListener);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            //disabled시
            // Toast.makeText(getContext(),"onStatusChanged provider: "+provider+" status: "+status
            //  +"Bundle: "+extras, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            //enabled시
            // Toast.makeText(getContext(),"onProviderEnabled provider: "+provider, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String provider) {
            //변경시
            //Toast.makeText(getContext(),"onProviderDisabled provider: "+provider, Toast.LENGTH_SHORT).show();
        }
    };

    //자동 112 문자 신고 접수 [업데이트된 위치 값을 얻어와 수신2]
    private void AutoSMS_Location_Transmission(){

        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION);
            //무조건 퍼미션을 허용한다는 전제조건하에 진행 (필수적 권한)
            Toast.makeText(getApplicationContext(), "문자신고 진행(나를 지켜줘)", Toast.LENGTH_SHORT).show();
            try {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,//등록할 위치 제공자
                        100, //통지사이의 최소 시간 간격
                        1, //통지사이의 최소 변경 거리
                        locationListener);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,//등록할 위치 제공자
                        100, //통지사이의 최소 시간 간격
                        1, //통지사이의 최소 변경 거리
                        locationListener);
            } catch (SecurityException e) {
                System.err.println("security error");
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "문자신고 진행(나를 지켜줘)", Toast.LENGTH_SHORT).show();
            try {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,//등록할 위치 제공자
                        1000, //통지사이의 최소 시간 간격
                        1, //통지사이의 최소 변경 거리
                        locationListener);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,//등록할 위치 제공자
                        1000, //통지사이의 최소 시간 간격
                        1, //통지사이의 최소 변경 거리
                        locationListener);
            } catch (SecurityException e) {
                System.err.println("security error");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResult){
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);

        if(requestCode==REQUEST_CODE_LOCATION){
            if(grantResult.length > 0){
                for (int aGrantResult : grantResult) {
                    if (aGrantResult == PackageManager.PERMISSION_DENIED) {
                        //권한이 하나라도 거부 될 시
                        new AlertDialog.Builder(this,R.style.MyCustomDialogStyle)
                                .setTitle("사용 권한의 문제 발생")
                                .setMessage("저희 서비스 사용을 위해서는 서비스의 요구권한을 필수적으로 허용해주셔야합니다.")
                                .setPositiveButton("종료", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).setNegativeButton("권한 설정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                        .setData(Uri.parse("package:" + getPackageName()));
                                startActivity(intent);
                            }
                        }).setCancelable(false).show();
                        return;
                    }
                }
            }
        }
    }
}
