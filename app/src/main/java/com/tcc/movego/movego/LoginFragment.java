package com.tcc.movego.movego;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.tcc.movego.movego.utils.Constante;

/**
 * Created by solange on 10/05/2016.
 */
public class LoginFragment extends Fragment {

    private EditText mEtLogin, mEtSenha;
    private Button mLogin, mCadastrar;
    private static final String TAG = "LoginFragment";
    private ProgressDialog mProgressDialog;
    private AuthData mAuthData;
    Firebase ref = new Firebase(Constante.FIREBASE_URL);
    private Firebase.AuthStateListener mAuthStateListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_fragment, container, false);

        mEtLogin = (EditText) v.findViewById(R.id.et_login_loginfragment);
        mEtSenha = (EditText) v.findViewById(R.id.et_senha_loginfragment);
        mLogin = (Button) v.findViewById(R.id.btn_login_loginfragment);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginWithPassword();
            }
        });

        mCadastrar = (Button) v.findViewById(R.id.btn_cadastrar_loginfragment);
        mCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(getActivity(), CadastroActivity.class);
                startActivity(newIntent);
            }
        });



        mAuthStateListener = new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if(authData != null){
                    mProgressDialog.hide();
                    setAuthenticatedUser(authData);
                }else{

                }

            }
        };
        ref.removeAuthStateListener(mAuthStateListener);
        return v;
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(getContext())
                .setTitle("Erro")
                .setMessage("Usuário ou senha incorreto")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private class AuthResultHandler implements Firebase.AuthResultHandler {

        private final String provider;

        public AuthResultHandler(String provider) {
            this.provider = provider;
        }

        @Override
        public void onAuthenticated(AuthData authData) {
            mProgressDialog.hide();
            Log.i(TAG, provider + " auth successful");
            setAuthenticatedUser(authData);
            Toast.makeText(getContext(), "Sucesso!", Toast.LENGTH_LONG).show();
            Intent newIntent = new Intent(getActivity(), NovoPedidoActivity.class);
            startActivity(newIntent);
            getActivity().finish();
        }

        @Override
        public void onAuthenticationError(FirebaseError firebaseError) {
            mProgressDialog.hide();
            showErrorDialog(firebaseError.toString());
        }
    }


    private void setAuthenticatedUser(AuthData authData) {
        if (authData != null) {
            String name = null;
            if (authData.getProvider().equals("password")) {
                name = authData.getUid();
            } else {
                Log.e(TAG, "Invalid provider: " + authData.getProvider());
            }
            if (name != null) {
                Log.e(TAG, "Logged in as " + name);
            }
        } else {
            /* No authenticated user show all the login buttons */
            mLogin.setVisibility(View.VISIBLE);
        }
        this.mAuthData = authData;
        /* invalidate options menu to hide/show the logout button */
    }

    public void loginWithPassword() {
        String login = mEtLogin.getText().toString().trim();
        String senha = mEtSenha.getText().toString().trim();
        ref.authWithPassword(login,senha , new AuthResultHandler("password"));
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setTitle("Carregando");
        mProgressDialog.setMessage("Autenticando...");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }
}
