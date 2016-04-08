package com.wqian001.lovereminder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.util.Log;

import com.estimote.sdk.Beacon;
import com.google.api.services.proximitybeacon.v1beta1.Proximitybeacon;
import com.google.api.services.proximitybeacon.v1beta1.ProximitybeaconRequest;
import com.google.api.services.proximitybeacon.v1beta1.model.AdvertisedId;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

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
    private Status Status;
    private UUID id;
    private expectedStability Stability;
    private double lat;
    private double lng;
    private Context context;
    private String mAuthToken;
    private JSONObject mBeacon = new JSONObject();

    public ProximityBeacon(Context mContext, String mAuthToken){
        // getting lat and lng
        this.context = mContext;
        LocationManager lm = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
        //Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        //this.lng = location.getLongitude();
        //this.lat = location.getLatitude();
        //this.id = beacon.getProximityUUID();
        this.Stability = expectedStability.MOBILE;
        this.Status = Status.ACTIVE;


        JSONObject AdvertisedId_obj = new JSONObject();
        JSONObject LatLng = new JSONObject();
        try{
            AdvertisedId_obj.put("type" , "EDDYSTONE");
            AdvertisedId_obj.put("id", new AdvertisedId().encodeId(UUID.randomUUID().toString().getBytes()));
            //LatLng.put("latitude", lat);
            //LatLng.put("longitude", lng);
            mBeacon.put("advertisedId", AdvertisedId_obj);
            //mBeacon.put("latLng", LatLng);
            mBeacon.put("status", Status);
            mBeacon.put("expectedStability", Stability);
        }
        catch (JSONException e) {

        }
    }

    public String registerBeacon(){
        try{
            return new Register().execute().get();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        catch(ExecutionException e){
            e.printStackTrace();
        }
        return null;
    }

    class Register extends AsyncTask<Void, Void, String>{
        @Override
        protected String doInBackground(Void... args){
            return register();
        }

        public String register(){
            String response = "";
            HttpURLConnection connection = null;
            try{

                // create connection
                URL url = new URL(Constant.beacons_register);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("content-Type",
                        "application/json; charset=UTF-8");
                connection.setRequestProperty("content-encoding", "gzip");
                connection.setRequestProperty("cache-control", "private");

                connection.setRequestProperty("content-Language", "en-US");
                connection.setRequestProperty("authorization",
                        "Bearer " + "832395154519-vbnlfckoemu5rars838uicq1ag7sdi4q.apps.googleusercontent.com");
                connection.setRequestMethod(Constant.http_post);
                connection.setUseCaches(false);
                connection.setDoOutput(true);

                // send request
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                Log.d(TAG, mBeacon.toString());
                wr.writeBytes(mBeacon.toString());
                wr.close();

                // get response
                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                StringBuilder responseBuilder = new StringBuilder();
                String str = "";
                while((str = rd.readLine()) != null){
                    responseBuilder.append(str);
                    responseBuilder.append('\r');
                }
                rd.close();
                response = responseBuilder.toString();
            }
            catch(MalformedURLException e){
                // Exception throw with bad url
                e.printStackTrace();
            }
            catch(IOException e){
                // Exception throw with no connection
                e.printStackTrace();
            }
            finally {
                if(connection != null){
                    connection.disconnect();
                }
            }
            return response;

        }
    }

}
