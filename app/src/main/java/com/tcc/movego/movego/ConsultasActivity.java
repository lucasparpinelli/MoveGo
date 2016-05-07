package com.tcc.movego.movego;

import android.support.v4.app.Fragment;

public class ConsultasActivity extends ContainerActivity {


    @Override
    protected Fragment createFragment() {

        return new PedidosFragment();
    }
}
