package com.tcc.movego.movego;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.tcc.movego.movego.model.Pedido;
import com.tcc.movego.movego.utils.Constante;

/**
 * Created by Victor on 08/05/16.
 */
public class PedidosListFragment extends Fragment {
    private static final String TAG = "PedidosListFragment";
    private RecyclerView mRecyclerView;
    private Firebase mReference;
    private FirebaseRecyclerAdapter<Pedido, PedidoViewHolder> mAdapter;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReference = new Firebase ("https://movego.firebaseio.com/users/pedidos");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pedidos_list_fragment,container,false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.pedidos_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new FirebaseRecyclerAdapter<Pedido,PedidoViewHolder>(Pedido.class,R.layout.pedidos_list_item, PedidoViewHolder.class,mReference) {
            @Override
            protected void populateViewHolder(PedidoViewHolder pedidoViewHolder, Pedido pedido, int i) {
                pedidoViewHolder.mTitleTextView.setText(pedido.getEmbalagem());
                pedidoViewHolder.mDescriptionTextView.setText(pedido.getDestino());
            }
        };
        Log.d(TAG,"onCreateView - mAdapter size:"+ mAdapter.getItemCount());
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }

    public static class PedidoViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTextView;
        private TextView mDescriptionTextView;

        public PedidoViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.pedido_item_title);
            mDescriptionTextView = (TextView) itemView.findViewById(R.id.pedido_item_description);
        }
    }
}
