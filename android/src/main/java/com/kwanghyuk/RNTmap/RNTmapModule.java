package com.kwanghyuk.RNTmap;

import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.skp.Tmap.TMapGpsManager;
import com.skp.Tmap.TMapTapi;
import com.skp.Tmap.TMapView;

import java.util.HashMap;
import java.util.Map;

public class TmapModule extends ReactContextBaseJavaModule {

    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";
    private static final String APIKEY = ""; // API Key
    private final ReactApplicationContext curAppContext;

    public TmapModule(ReactApplicationContext reactContext) {
        super(reactContext);
        curAppContext = reactContext;
    }

    @ReactMethod
    public void getZoomLevel(Callback errorCallback, Callback successCallback) {
      try {
        TMapView tmapview = new TMapView(getReactApplicationContext());
        int level = tmapview.getZoomLevel();
        successCallback.invoke(level);
      } catch (Exception e) {
          errorCallback.invoke(e.getMessage());
      }
    }

    @ReactMethod
    public void callTmap(Callback errorCallback, Callback successCallback) {
        try {
            TMapView tmapview = new TMapView(getReactApplicationContext());
            tmapview.setSKPMapApiKey(APIKEY);
            TMapTapi tmaptapi = new TMapTapi(curAppContext);
            tmaptapi.invokeRoute("T 타워", 126.984098f, 37.566385f);
            successCallback.invoke("good");
        } catch (Exception e) {
            errorCallback.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public void isInstalled(Callback errorCallback, Callback successCallback) {
        try {
            TMapTapi tmaptapi = new TMapTapi(curAppContext);
            boolean ret = tmaptapi.isTmapApplicationInstalled();
            successCallback.invoke(ret);
        } catch (Exception e) {
            errorCallback.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public void goPlayStoreForTmap(Callback error, Callback success) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=com.skt.tmap.ku"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            curAppContext.startActivity(intent);
        }catch (Exception e) {
            error.invoke(e.getMessage());
        }
    }


    // 필수 사항 : 자바스크립트에서 보여지는 모듈 이름 (ReactContextBaseJavaModule를 사용하려면 필수)
    @Override
    public String getName() {
        return "RNTmap";
    }
}
