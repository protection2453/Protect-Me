package FragmentFile;

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
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import ModelFile.Audio;
import ModelFile.Scrum_Controll;
import ModelFile.Scrum_NoControll;
import andbook.example.protect_me_release.R;

public class Main_Fragment extends Fragment{

    //GPS
    static final int REQUEST_CODE_LOCATION=2;
    private LocationManager locationManagerGPS;
    private Geocoder geocoder;
    private LocationManager locationManager;
    private SmsManager smsManager;
    private String emergency_comment;
    private List<Address> addresses;

    //버튼 활성화 비활성화를 위한 인텐트 공유를 통한 서비스 제어
    private Intent ScrumOnOff; //비명소리 on off 제어

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        final ImageButton all_sos = v.findViewById(R.id.mainView_All_SOS); //통합기능
        final ImageButton record = v.findViewById(R.id.mainView_Audio_record); //녹음기능
        final ImageButton sms_trans = v.findViewById(R.id.mainView_SMS_Trans); //자동 문자신고
        final ImageButton call = v.findViewById(R.id.mainView_Call_112); //자동 신고전화
        final ImageButton scrum_on = v.findViewById(R.id.mainView_Scrum_On); //비명기능 on
        final ImageButton scrum_off = v.findViewById(R.id.mainView_Scrum_Off); //비명기능 off
        final ImageButton warning = v.findViewById(R.id.mainView_Warning); //앱 사용 알림


        //GPS ON/OFF 확인하여 OFF일 시 GPS 설정화면으로 이동진행
        locationManagerGPS = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        //GPS 미 설정시 false 설정 시 true
        if (!locationManagerGPS.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
           //GPS 설정 off일 시 설정화면으로 이동
           new AlertDialog.Builder(getActivity(), R.style.MyCustomDialogStyle)
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
                           getActivity().finish();
                       }
                   }).show();

       }
        else //GPS 설정 on일 시 기능 수행 가능
        {
            all_sos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //긴급 문자 신고 접수
                    AutoSMS_Location_Transmission();
                    //비명소리 기능
                    Intent Scrum_sos = new Intent(getActivity(), Scrum_NoControll.class);
                    getActivity().startService(Scrum_sos);
                    Intent Audio_sos = new Intent(getActivity(), Audio.class);
                    getActivity().startService(Audio_sos);
                    //작업완료 시 백그라운드 종료
                    getActivity().stopService(Scrum_sos);
                    getActivity().stopService(Audio_sos);

                }
            });

            sms_trans.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AutoSMS_Location_Transmission();
                }
            });
        }
        //GPS 기능이 필요없는 기능들
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //긴급 전화 신고
                AutoCall_112();
            }
        });
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 백그라운드로 녹음진행
                Intent Audio = new Intent(getActivity(), Audio.class);
                getActivity().startService(Audio);
                //작업완료 시 백그라운드 종료
                getActivity().stopService(Audio);

            }
        });

        //비명소리 사용전 of, off 셋팅
        scrum_on.setEnabled(true);
        scrum_off.setEnabled(false);
        scrum_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //비명소리 기능
                ScrumOnOff = new Intent(getActivity(), Scrum_Controll.class);
                getActivity().startService(ScrumOnOff);

                scrum_on.setEnabled(false); //비명 lock
                scrum_off.setEnabled(true); //비명 해제 unlock
            }
        });
        scrum_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().stopService(ScrumOnOff);

                scrum_off.setEnabled(false); //비명 unlock
                scrum_on.setEnabled(true); //비명해제 lock
            }
        });


        warning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity(), R.style.MyCustomDialogStyle)
                        .setTitle("나를 지켜줘")
                        .setIcon(R.drawable.icon96)
                        .setMessage("원활한 사용을 위해 사용자께서는 통합 서비스와 개별 녹음 서비스를 동시에 사용하지마십시오.\n" +
                                "통합서비스의 '녹음' 과 개별 '녹음' 서비스는 동시에 사용이 불가합니다.\n" +
                                "그외의 기능은 동시에 사용이 가능합니다.\n감사합니다.")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        });
        return v;
    }

    //자동 112 신고 전화
    private void AutoCall_112() {
        Intent Call = new Intent();
        Call.setAction(Intent.ACTION_CALL);
        Call.setData(Uri.parse("tel:01039932977"));
        startActivity(Call);
    }

    //자동 112 문자 신고 접수 [리스너를 통해서 위치값으 업데이트 하여 위치 수신 진행1]
    private LocationListener locationListener = new LocationListener()
    {
        //위치값이 갱신되면 이벤트 발생
        //위치 제공자 GPS:위성 수신으로 정확도가 높다, 실내사용 불가,위치 제공자 Network:인터넷 엑세스 수신으로 정확도가 아쉽다, 실내 사용 가능
        @SuppressLint("UnlocalizedSms")
        @Override
        public void onLocationChanged(Location location) {

            locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
            smsManager = SmsManager.getDefault();
            geocoder = new Geocoder(getContext(), Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(
                        location.getLatitude(), location.getLongitude(), 1
                );
            } catch (IOException e) {
                System.err.println("MainView IOException error");
            }
            if (addresses == null)
                emergency_comment = "도와주세요 빨리 이리로 와주세요 제 위치는 " + location.getLongitude() + " , " + location.getLatitude() + "입니다 빨리와주세요.";
            else
                emergency_comment = "도와주세요 빨리 이리로 와주세요 제 위치는 " + addresses.get(0).getAddressLine(0) + "입니다 빨리와주세요.";

            //Toast.makeText(getContext(), emergency_comment, Toast.LENGTH_SHORT).show();
            smsManager.sendTextMessage("01039932977", null, emergency_comment, null, null);
            Toast.makeText(getContext(), "문자신고 완료(나를 지켜줘)", Toast.LENGTH_SHORT).show();
            locationManager.removeUpdates(locationListener); //추후 112로 번호 변경
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

        locationManager=(LocationManager)getContext().getSystemService(Context.LOCATION_SERVICE);

        if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION);
            //무조건 퍼미션을 허용한다는 전제조건하에 진행 (필수적 권한)
            Toast.makeText(getContext(), "문자신고 진행(나를 지켜줘)", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getContext(), "문자신고 진행(나를 지켜줘)", Toast.LENGTH_SHORT).show();
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
                        new AlertDialog.Builder(getContext(),R.style.MyCustomDialogStyle)
                                .setTitle("사용 권한의 문제발생")
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
                                        .setData(Uri.parse("package:" + getContext().getPackageName()));
                                getContext().startActivity(intent);
                            }
                        }).setCancelable(false).show();
                        return;
                    }
                }
            }
        }
    }
}
