package com.tcc.movego.movego;

import android.support.v4.app.Fragment;

import com.tcc.movego.movego.ContainerActivity;
import com.tcc.movego.movego.LoginFragment;

/**
 * Created by solange on 11/05/2016.
 */
public class CadastroActivity extends ContainerActivity{
    @Override
    protected Fragment createFragment() {

        return new CadastroFragment();
    }


}

