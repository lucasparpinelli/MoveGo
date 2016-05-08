package com.tcc.movego.movego;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.client.Firebase;

import layout.NovoPedidoFragment;

/**
 * Created by solange on 07/05/2016.
 */
public class PedidosFragment extends Fragment {

    private Button mNovoPedidoButton;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pedidos_fragment, container, false);
        mNovoPedidoButton = (Button)v.findViewById(R.id.novo_pedido_button);
        mNovoPedidoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(getActivity(), NovoPedidoActivity.class);
                startActivity(newIntent);
            }
        });

        return v;
    }

}
