package com.tcc.movego.movego;

import com.firebase.client.Firebase;

/**
 * Created by solange on 07/05/2016.
 */
public class MoveGo extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
