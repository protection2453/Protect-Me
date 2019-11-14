package FragmentFile;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import AlarmFile.AlarmChannels;
import ModelFile.TestAllSOS;
import ModelFile.TestCall;
import andbook.example.protect_me_release.R;


public class TestOrSetting_Fragment extends Fragment {

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle
            savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_testorsetting, container, false);

        ImageButton alarmTest = v.findViewById(R.id.AlarmTest);
        ImageButton systemSetting = v.findViewById(R.id.SystemSetting);
        ImageButton test_Call = v.findViewById(R.id.Test_Call_112);
        ImageButton test_SOS = v.findViewById(R.id.TestSOS);

        //알람 시스템 셋팅
        systemSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    goToNotificationSetting();
                }else{
                    AlarmChannels.sendNotification_notOreo(getActivity());
                }
            }
        });

        alarmTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    AlarmChannels.sendNotification(getActivity(), (int) (System.currentTimeMillis() / 1000), AlarmChannels.Channel.MESSAGE_ID);
                } else {
                    AlarmChannels.sendNotification_notOreo(getActivity());
                }
            }
        });

        //테스트 신고전화
        test_Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Test_call = new Intent(getActivity(),TestCall.class);
                startActivity(Test_call);
            }
        });

        test_SOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Test_sos = new Intent(getActivity(), TestAllSOS.class);
                startActivity(Test_sos);
            }
        });
        return v;
    }

    //시스템 셋팅으로 이동하는 함수
    @TargetApi(Build.VERSION_CODES.O)
    private void goToNotificationSetting(){
        Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE,getActivity().getPackageName());
        startActivity(intent);
    }
}
