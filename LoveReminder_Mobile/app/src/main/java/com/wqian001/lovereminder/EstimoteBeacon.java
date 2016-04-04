package com.wqian001.lovereminder;

import android.bluetooth.BluetoothManager;
import android.content.Context;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.MacAddress;

import java.util.UUID;

/**
 * Created by qian on 3/13/2016.
 */
public class EstimoteBeacon {
    private Context context;
    private Beacon mBeacon;
    public EstimoteBeacon(Context context){
        this.context = context;
        BluetoothManager mBluetoothManger = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        this.mBeacon = new Beacon(UUID.randomUUID(),MacAddress.fromString(mBluetoothManger.getAdapter().getAddress()), Constant.Major, Constant.Minor, 0,0 );
        // let the beacon rise;

    }

    public Beacon getmBeacon(){
        return this.mBeacon;
    }

}
