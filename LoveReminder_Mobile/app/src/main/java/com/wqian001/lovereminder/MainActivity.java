package com.wqian001.lovereminder;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Beacon;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.PublishCallback;
import com.google.android.gms.nearby.messages.PublishOptions;
import com.google.android.gms.nearby.messages.SubscribeCallback;
import com.google.android.gms.nearby.messages.SubscribeOptions;

// nearby API
//  AIzaSyBBBNb-mJ1b0Eztr0W2c9Y-oIaPChK8oJs
public class MainActivity extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    private BeaconManager beaconManger;
    private Beacon myBeacon;
    private GoogleApiClient mGoogleApiClient;
    private Message mDeviceInfoMessage;
    private MessageListener mMessageListener;
    private static String TAG = "Test";
    private boolean mResolvingError = false;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beaconManger = new BeaconManager(this);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.publish_button);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Nearby.MESSAGES_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        // Create a new message listener.
        mMessageListener = new MessageListener() {
            @Override
            public void onFound(final Message message) {
                final String nearbyMessageString = new String(message.getContent());

                // Do something with the message string.
                Log.i(TAG, nearbyMessageString);
            }

            // Called when a message is no longer detectable nearby.
            public void onLost(final Message message) {
                final String nearbyMessageString = new String(message.getContent());
                // Take appropriate action here (update UI, etc.)
            }
        };

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publish();
            }
        });

/*        Uri uriUrl = Uri.parse("http://10.0.2.2:3000");
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);*/
    }

    @Override
    public void onStart(){
        super.onStart();
        mGoogleApiClient.connect();
        Log.d(TAG, "Something");
    }

    @Override
    public void onStop(){
        super.onStop();
        mGoogleApiClient.disconnect();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void publish(){
        Log.i(TAG, "Trying to publish.");
        // Set a simple message payload.
        String strMsg = "This is NearbyPubSub.";
        mDeviceInfoMessage = new Message(strMsg.getBytes());
        // Cannot proceed without a connected GoogleApiClient.
        // Reconnect and execute the pending task in onConnected().
        if (!mGoogleApiClient.isConnected()) {
            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        } else {
            PublishOptions options = new PublishOptions.Builder()
                    .setCallback(new PublishCallback() {
                        @Override
                        public void onExpired() {
                            Log.i(TAG, "No longer publishing.");
                        }
                    }).build();

            Nearby.Messages.publish(mGoogleApiClient, mDeviceInfoMessage, options)
                    .setResultCallback(new ResultCallback<Status>() {

                        @Override
                        public void onResult(Status status) {
                            if (status.isSuccess()) {
                                Log.i(TAG, "Published successfully.");
                            } else {
                                Log.i(TAG, "Could not publish.");
                                // Check whether consent was given;
                                // if not, prompt the user for consent.
                                handleUnsuccessfulNearbyResult(status);
                            }
                        }
                    });
        }

    }

    // Subscribe to receive messages.
    private void subscribe() {
        Log.i(TAG, "Trying to subscribe.");
        // Cannot proceed without a connected GoogleApiClient.
        // Reconnect and execute the pending task in onConnected().
        if (!mGoogleApiClient.isConnected()) {
            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        } else {
            SubscribeOptions options = new SubscribeOptions.Builder()
                    .setCallback(new SubscribeCallback() {
                        @Override
                        public void onExpired() {
                            Log.i(TAG, "No longer subscribing.");
                        }
                    }).build();

            Nearby.Messages.subscribe(mGoogleApiClient, mMessageListener, options)
                    .setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            if (status.isSuccess()) {
                                Log.i(TAG, "Subscribed successfully.");
                            } else {
                                Log.i(TAG, "Could not subscribe.");
                                // Check whether consent was given;
                                // if not, prompt the user for consent.
                            }
                        }
                    });
        }
    }


    private void unpublish() {
        Log.i(TAG, "Trying to unpublish.");
        Nearby.Messages.unpublish(mGoogleApiClient, mDeviceInfoMessage);
    }

    private void unsubscribe() {
        Log.i(TAG, "Trying to unsubscribe.");
        Nearby.Messages.unsubscribe(mGoogleApiClient, mMessageListener);
    }

    private void handleUnsuccessfulNearbyResult(Status status) {
        Log.i(TAG, "Processing error, status = " + status);
        if (mResolvingError) {
            // Already attempting to resolve an error.
            return;
        } else if (status.hasResolution()) {
            try {
                mResolvingError = true;
                status.startResolutionForResult(this,
                        1001);
            } catch (IntentSender.SendIntentException e) {
                mResolvingError = false;
                Log.i(TAG, "Failed to resolve error status.", e);
            }
        } else {
            if (status.getStatusCode() == CommonStatusCodes.NETWORK_ERROR) {
                Toast.makeText(getApplicationContext(),
                        "No connectivity, cannot proceed. Fix in 'Settings' and try again.",
                        Toast.LENGTH_LONG).show();
            } else {
                // To keep things simple, pop a toast for all other error messages.
                Toast.makeText(getApplicationContext(), "Unsuccessful: " +
                        status.getStatusMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onConnectionSuspended(int arg){}

    @Override
    public void onConnected(Bundle arg){
        if(mGoogleApiClient.isConnected()){
            Log.d(TAG,"Google_Api_Client: It was connected on (onConnected) function, working as it should.");
        }
        else{
            Log.d(TAG,"Google_Api_Client: It was NOT connected on (onConnected) function, It is definetly bugged.");
        }
        Nearby.Messages.getPermissionStatus(mGoogleApiClient);
        subscribe();

    }



    @Override
    public void onConnectionFailed(ConnectionResult arg){
        Log.d(TAG, arg.toString() + arg.getErrorCode());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001) {
            // User was presented with the Nearby opt-in dialog and pressed "Allow".
            mResolvingError = false;
            if (resultCode == Activity.RESULT_OK) {
                // Execute the pending subscription and publication tasks here.
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // User declined to opt-in. Reset application state here.
            } else {
                Toast.makeText(this, "Failed to resolve error with code " + resultCode,
                        Toast.LENGTH_LONG).show();
                }
            }
        }

    }
