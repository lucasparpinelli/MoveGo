package com.tcc.movego.movego;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.firebase.client.Firebase;

public class ConsultasActivity extends ContainerActivity {

    @Override
    protected Fragment createFragment() {

        return new PedidosListFragment();
    }


}
