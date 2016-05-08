package layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.Firebase;
import com.tcc.movego.movego.R;
import com.tcc.movego.movego.model.Pedido;
import com.tcc.movego.movego.utils.Constante;

import java.util.Date;

public class NovoPedidoFragment extends Fragment {
    public static NovoPedidoFragment newInstance() {
        NovoPedidoFragment fragment = new NovoPedidoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.novo_pedido_fragment, container, false);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.novo_pedido_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.adicionar_pedido_itemmenu:
                //getActivity().finish();
                Firebase ref = new Firebase(Constante.FIREBASE_URL);
                Firebase pedidoDatabse = ref.child("users").child("1");
                Pedido pedido = new Pedido();
                pedido.setDataEntrega(new Date());
                pedido.setDestinatario("Xablau");
                pedido.setOrigem("Bahia");
                pedido.setEmbalagem("Material");
                pedido.setViacao(null);
                pedido.setDestino("Sao Paulo");
                pedido.setDataExpedicao(new Date());
                pedido.setTipoEncomenda(null);
                pedido.setTipoServico(null);
                pedidoDatabse.setValue(pedido);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
