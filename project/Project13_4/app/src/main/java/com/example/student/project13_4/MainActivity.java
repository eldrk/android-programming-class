package com.example.student.project13_4;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapView;
// (중요) 패키지명은 애플리케이션 설정의 Android 패키지명과 반드시 일치해야 함
import android.os.Bundle;
import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapView;

public class MainActivity extends NMapActivity {

    private NMapView mMapView;// 지도 화면 View
    private final String CLIENT_ID = "0pizm5mucbw6k7XlRkDW";// 애플리케이션 클라이언트 아이디 값

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapView = new NMapView(this);
        setContentView(mMapView);
        mMapView.setClientId(CLIENT_ID); // 클라이언트 아이디 값 설정
        mMapView.setClickable(true);
        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);
        mMapView.requestFocus();
    }
}