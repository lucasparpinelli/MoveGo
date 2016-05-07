package com.tcc.movego.movego;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import layout.NovoPedidoFragment;

public class NovoPedidoActivity extends ContainerActivity {

    @Override
    protected Fragment createFragment() {

        return new NovoPedidoFragment();
    }
}
