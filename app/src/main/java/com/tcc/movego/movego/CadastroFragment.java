package com.tcc.movego.movego;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.tcc.movego.movego.model.Usuario;
import com.tcc.movego.movego.utils.Constante;

/**
 * Created by solange on 11/05/2016.
 */
public class CadastroFragment extends Fragment {

    private RadioGroup mRgPfPj;
    private RadioButton mRbPf, mRbPj;
    private EditText mNome, mSobrenome, mRazaoSocial, mCnpj, mCpf, mEndereco, mComplemento, mBairro, mCidade, mEstado, mCep, mTelefone, mCelular, mEmail, mSenha;
    private Button mBtnCancelar, mBtnSalvar;
    private Usuario mUsuario;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mUsuario = new Usuario();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.cadastro_fragment, container, false);

        mRgPfPj = (RadioGroup) v.findViewById(R.id.rg_pfpj_cadastrofragment);
        mRgPfPj.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int rgSelectedId = mRgPfPj.getCheckedRadioButtonId();
                if (rgSelectedId == mRbPf.getId()) {
                    limparCampos();
                    mNome.setVisibility(View.VISIBLE);
                    mNome.requestFocus();
                    mSobrenome.setVisibility(View.VISIBLE);
                    mCpf.setVisibility(View.VISIBLE);
                    mEndereco.setVisibility(View.VISIBLE);
                    mComplemento.setVisibility(View.VISIBLE);
                    mBairro.setVisibility(View.VISIBLE);
                    mCidade.setVisibility(View.VISIBLE);
                    mEstado.setVisibility(View.VISIBLE);
                    mCep.setVisibility(View.VISIBLE);
                    mTelefone.setVisibility(View.VISIBLE);
                    mCelular.setVisibility(View.VISIBLE);
                    mEmail.setVisibility(View.VISIBLE);
                    mSenha.setVisibility(View.VISIBLE);

                    mRazaoSocial.setVisibility(View.GONE);
                    mCnpj.setVisibility(View.GONE);
                } else {
                    limparCampos();
                    mRazaoSocial.setVisibility(View.VISIBLE);
                    mRazaoSocial.requestFocus();
                    mCnpj.setVisibility(View.VISIBLE);
                    mEndereco.setVisibility(View.VISIBLE);
                    mComplemento.setVisibility(View.VISIBLE);
                    mBairro.setVisibility(View.VISIBLE);
                    mCidade.setVisibility(View.VISIBLE);
                    mEstado.setVisibility(View.VISIBLE);
                    mCep.setVisibility(View.VISIBLE);
                    mTelefone.setVisibility(View.VISIBLE);
                    mCelular.setVisibility(View.VISIBLE);
                    mEmail.setVisibility(View.VISIBLE);
                    mSenha.setVisibility(View.VISIBLE);

                    mNome.setVisibility(View.GONE);
                    mSobrenome.setVisibility(View.GONE);
                    mCpf.setVisibility(View.GONE);
                }
            }
        });

        mRbPf = (RadioButton) v.findViewById(R.id.rb_pf_cadastrofragment);
        mRbPf.setSelected(true);

        mRbPj = (RadioButton) v.findViewById(R.id.rb_pj_cadastrofragment);

        mNome = (EditText) v.findViewById(R.id.et_nome_cadastrofragment);
        mSobrenome = (EditText) v.findViewById(R.id.et_sobrenome_cadastrofragment);
        mRazaoSocial = (EditText) v.findViewById(R.id.et_razao_social_cadastrofragment);
        mRazaoSocial.setVisibility(View.GONE);
        mCnpj = (EditText) v.findViewById(R.id.et_cnpj_cadastrofragment);
        mCnpj.setVisibility(View.GONE);
        mCpf = (EditText) v.findViewById(R.id.et_cpf_cadastrofragment);
        mEndereco = (EditText) v.findViewById(R.id.et_endereco_cadastrofragment);
        mComplemento = (EditText) v.findViewById(R.id.et_complemento_cadastrofragment);
        mBairro = (EditText) v.findViewById(R.id.et_bairro_cadastrofragment);
        mCidade = (EditText) v.findViewById(R.id.et_cidade_cadastrofragment);
        mEstado = (EditText) v.findViewById(R.id.et_estado_cadastrofragment);
        mCep = (EditText) v.findViewById(R.id.et_cep_cadastrofragment);
        mTelefone = (EditText) v.findViewById(R.id.et_telefone_cadastrofragment);
        mCelular = (EditText) v.findViewById(R.id.et_celular_cadastrofragment);
        mEmail = (EditText) v.findViewById(R.id.et_email_cadastrofragment);
        mSenha = (EditText) v.findViewById(R.id.et_senha_cadastrofragment);

        mBtnCancelar = (Button) v.findViewById(R.id.btn_cancelar_cadastrofragment);
        mBtnSalvar = (Button) v.findViewById(R.id.btn_salvar_cadastrofragment);
        mBtnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarCadastro();
            }
        });


        return v;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.limpar_campos_menu, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.limpar_campos_itemmenu:
                limparCampos();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void limparCampos() {
        mNome.setText("");
        mSobrenome.setText("");
        mRazaoSocial.setText("");
        mCnpj.setText("");
        mCpf.setText("");
        mEndereco.setText("");
        mComplemento.setText("");
        mBairro.setText("");
        mCidade.setText("");
        mEstado.setText("");
        mCep.setText("");
        mTelefone.setText("");
        mCelular.setText("");
        mEmail.setText("");
        mSenha.setText("");
    }

    private void salvarCadastro() {
//        if (validacao()) {
            //getActivity().finish();
            Firebase ref = new Firebase(Constante.FIREBASE_URL);
            String email = mEmail.getText().toString();
            String senha = mSenha.getText().toString();
            Firebase usuarioDatabse = ref.child("users");
            mUsuario.setNome(mNome.getText().toString());
            mUsuario.setSobrenome(mSobrenome.getText().toString());
            ref.createUser(email, senha, new Firebase.ResultHandler() {
                @Override
                public void onSuccess() {
                    Toast.makeText(getContext(), "Usuario criado com sucesso", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(FirebaseError firebaseError) {
                    showErrorDialog(firebaseError.toString());
                }
            });
            usuarioDatabse.push().setValue(mUsuario);
        //}
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(getContext())
                .setTitle("Erro")
                .setMessage("Verifique os campos")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

//    private boolean validacao() {
//        if (mNome.getText().equals("") || mSobrenome.getText().equals("") || mCpf.getText().equals("") || mEndereco.getText().equals("") ||
//                mComplemento.getText().equals("") || mBairro.getText().equals("") || mCidade.getText().equals("") || mEstado.getText().equals("") ||
//                mCep.getText().equals("") || mTelefone.getText().equals("") || mCelular.getText().equals("") || mEmail.getText().equals("") || mSenha.getText().equals("")) {
//            Snackbar.make(getView(), "Preencha os campos", Snackbar.LENGTH_LONG).show();
//            return false;
//        }
//        return true;
//    }
}
