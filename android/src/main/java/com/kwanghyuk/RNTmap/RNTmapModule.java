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

public class RNTmapModule extends ReactContextBaseJavaModule {

    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";
    private static String APIKEY = ""; // API Key
    private final ReactApplicationContext curAppContext;

    public RNTmapModule(ReactApplicationContext reactContext) {
        super(reactContext);
        curAppContext = reactContext;
    }

    @ReactMethod
    public void setApiKey(String val, Callback errorCallback, Callback successCallback) {
      try {
        APIKEY = val;
        successCallback.invoke(val);
      } catch(Exception e) {
        errorCallback.invoke(e.getMessage());
      }
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
    public void invokeRoute(String dest, double fx, double fy, Callback errorCallback, Callback successCallback) {
        try {
            TMapView tmapview = new TMapView(getReactApplicationContext());
            tmapview.setSKPMapApiKey(APIKEY);
            TMapTapi tmaptapi = new TMapTapi(curAppContext);
            tmaptapi.invokeRoute(dest, fx, fy);
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

    @Override
    public String getName() {
        return "RNTmap";
    }
}
