package com.wqian001.lovereminder;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.AccountPicker;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by qian on 3/13/2016.
 */
public class Oauth{

    private Context context;
    private String Token;
    private static final String TAG = "tag";

    public Oauth(Context context){
        this.context = context;
        pickUserAccount();
    }
    private void pickUserAccount() {
        String[] accountTypes = new String[]{"com.google"};
        Intent intent = AccountPicker.newChooseAccountIntent(null, null,
                accountTypes, true, null, null, null, null);
        ((Activity)context).startActivityForResult(intent, Constant.REQUEST_PICK_ACCOUNT);
    }

    // must be Async Task
    public String getToken(Account account, Context context){
        String str = "";
        this.context = context;
        try{
            str = new Token().execute(account).get();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        catch(ExecutionException e){
            e.printStackTrace();
        }
        return str;
    }

    private void setToken(String Token){
        this.Token = Token;
    }

    private class Token extends AsyncTask<Account, Void, String>{
        @Override
        protected String doInBackground(Account... para){
            String Token = null;
            try{
                Token = fetchToken(para[0]);
            }
            catch(IOException e){
                return null;
            }

            return Token;

        }

        @Override
        protected void onPostExecute(String result){
            setToken(result);

        }

        private String fetchToken(Account account) throws IOException {
            String str = "";
            try {
                //Attempt to get an OAuth token from Play Services
                str =  GoogleAuthUtil.getToken(context, account, "oauth2:profile email");
            } catch (UserRecoverableAuthException e) {
                // User hasn't granted permission yet. Show the permission dialog.
                Intent intent = ((UserRecoverableAuthException) e).getIntent();
                ( (Activity) context).startActivityForResult(intent, Constant.REQUEST_ERROR_RECOVER);
            } catch (GoogleAuthException e) {
                Log.d(TAG, "Fatal Exception", e);
            }
             catch (IOException ioEx) {
                 Log.d(TAG, "transient error encountered: " + ioEx.getMessage());
            }
            catch(Exception e){
                Log.d(TAG, e.getMessage());
            }
            return str;
        }





    }


}
