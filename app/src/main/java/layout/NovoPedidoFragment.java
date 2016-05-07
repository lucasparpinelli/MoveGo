package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tcc.movego.movego.R;

public class NovoPedidoFragment extends Fragment {
    public static NovoPedidoFragment newInstance() {
        NovoPedidoFragment fragment = new NovoPedidoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.novo_pedido_fragment, container, false);
        return v;
    }

}
