package layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.tcc.movego.movego.DatePickerFragment;
import com.tcc.movego.movego.R;
import com.tcc.movego.movego.model.Pedido;
import com.tcc.movego.movego.utils.Constante;

import java.util.Date;

public class NovoPedidoFragment extends Fragment {

    private EditText mEtOrigem, mEtDestino, mEtDestinatario, mEtEmbalagem;
    private Spinner mSpinnerViacoes, mSpinnerTipoEncomenda, mSpinnerTipoServico;
    private Button mButtonrDataExpedicao, mButtonDataEntrega, mButtonSalvar;
    private String DIALOG_DATA_ENTREGA = "DataEntrega";
    private String DIALOG_DATA_EXPEDICAO = "DataExpedicao";
    private static int REQUEST_DATA_ENTREGA = 100;
    private static int REQUEST_DATA_EXPEDICAO = 101;
    private Pedido mPedido;


    public static NovoPedidoFragment newInstance() {
        NovoPedidoFragment fragment = new NovoPedidoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mPedido = new Pedido();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.novo_pedido_fragment, container, false);

        mEtOrigem = (EditText) v.findViewById(R.id.et_origem_novopedido);
        mEtDestino = (EditText) v.findViewById(R.id.et_destino_novopedido);
        mEtDestinatario = (EditText) v.findViewById(R.id.et_destinatario_novopedido);
        mEtEmbalagem = (EditText) v.findViewById(R.id.et_embalagem_novopedido);

        mSpinnerViacoes = (Spinner) v.findViewById(R.id.spinner_viacoes_novopedido);
        mSpinnerTipoEncomenda = (Spinner) v.findViewById(R.id.spinner_tipo_encomenda_novopedido);
        mSpinnerTipoServico = (Spinner) v.findViewById(R.id.spinner_tipo_servico_novopedido);

        mButtonDataEntrega = (Button) v.findViewById(R.id.btn_data_entrega_novopedido);
        mButtonDataEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(new Date());
                dialog.setTargetFragment(NovoPedidoFragment.this, REQUEST_DATA_ENTREGA);
                dialog.show(fm,DIALOG_DATA_ENTREGA);
            }
        });
        mButtonrDataExpedicao = (Button) v.findViewById(R.id.btn_data_expedicao_novopedido);
        mButtonrDataExpedicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(new Date());
                dialog.setTargetFragment(NovoPedidoFragment.this, REQUEST_DATA_EXPEDICAO);
                dialog.show(fm,DIALOG_DATA_EXPEDICAO);
            }
        });
        mButtonSalvar = (Button) v.findViewById(R.id.btn_salvar_novopedido);
        mButtonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               salvarPedido();
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK){
            return;
        }

        if(requestCode == REQUEST_DATA_ENTREGA){
            Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mButtonDataEntrega.setText(date.toString());
            mPedido.setDataEntrega(date.getTime());
        }

        if(requestCode == REQUEST_DATA_EXPEDICAO){
            Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mButtonrDataExpedicao.setText(date.toString());
            mPedido.setDataExpedicao(date.getTime());
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.novo_pedido_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.adicionar_pedido_itemmenu:

                salvarPedido();

                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvarPedido() {
        if(validacao()) {
            //getActivity().finish();
            Firebase ref = new Firebase(Constante.FIREBASE_URL);
            Firebase pedidoDatabse = ref.child("users").child("pedidos");
            mPedido.setDestino(mEtDestino.getText().toString());
            mPedido.setEmbalagem(mEtEmbalagem.getText().toString());
            mPedido.setOrigem(mEtOrigem.getText().toString());
            mPedido.setDestinatario(mEtDestinatario.getText().toString());
            mPedido.setViacao(mSpinnerViacoes.getSelectedItem().toString());
            mPedido.setTipoEncomenda(mSpinnerTipoEncomenda.getSelectedItem().toString());
            mPedido.setTipoServico(mSpinnerTipoServico.getSelectedItem().toString());
            pedidoDatabse.push().setValue(mPedido);
        }
    }

    private boolean validacao() {
        if (mEtOrigem.getText().equals("") || mEtDestino.getText().equals("") || mEtDestinatario.getText().equals("") || mEtEmbalagem.getText().equals("") ||
            mSpinnerViacoes.getSelectedItemPosition() == 0 || mSpinnerTipoServico.getSelectedItemPosition() == 0 || mSpinnerTipoEncomenda.getSelectedItemPosition() == 0) {
            Snackbar.make(getView(), "Preencha os campos", Snackbar.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
