package com.wqian001.lovereminder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import com.google.api.services.proximitybeacon.v1beta1.Proximitybeacon;
import com.google.api.services.proximitybeacon.v1beta1.ProximitybeaconRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by qian on 3/13/2016.
 */
public class ProximityBeacon {
    enum Status{
        ACTIVE, DECOMMISSIONED, INACTIVE
    }
    enum expectedStability{
        STABLE, PORTABLE, MOBILE, ROVING
    }
    private static String TAG = "tag";
    private String beaconName;
    private byte[] id;
    private Status Status;
    private expectedStability Stability;
    private double lat;
    private double lng;
    private Context context;
    private JSONObject Beacon = new JSONObject();
    private int REQUEST_PICK_ACCOUNT;
    private int REQUEST_ERROR_RECOVER;
    public ProximityBeacon(String beaconName, byte[] id, Context mContext){
        this.beaconName = beaconName;
        this.id = id;
        LocationManager lm = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        this.lng = location.getLongitude();
        this.lat = location.getLatitude();
        this.Stability = expectedStability.MOBILE;
        this.Status = Status.ACTIVE;

        JSONObject AdvertisedId = new JSONObject();
        JSONObject LatLng = new JSONObject();
        try{
            AdvertisedId.put("type" , "EDDYSTONE");
            AdvertisedId.put("id", id);
            LatLng.put("latitude", lat);
            LatLng.put("longitude", lng);
            Beacon.put("beaconName", beaconName);
            Beacon.put("advertisedId", AdvertisedId);
            Beacon.put("latLng", LatLng);
            Beacon.put("status", Status);
            Beacon.put("expectedStability", Stability);
        }
        catch (JSONException e) {

        }

    }



    public boolean register(){
        boolean result = false;

        HttpURLConnection connection = null;
        try{
            // create connection
            URL url = new URL(Constant.beacons_register);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(Constant.http_post);

        }
        catch(MalformedURLException e){
            // Exception throw with bad url
        }
        catch(IOException e){
            // Exception throw with no connection
        }

        return result;
    }
}
