package br.ufrn.imd.campusteca;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import br.ufrn.imd.campusteca.R;
import br.ufrn.imd.campusteca.api.OAuthTokenRequest;

public class LoginActivity extends AppCompatActivity {

    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInButton = (Button) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                OAuthTokenRequest.getInstance().
                        getTokenCredential(LoginActivity.this,
                                "http://apitestes.info.ufrn.br/authz-server","campusteca-id",
                                "appcampusteca", intent);
            }
        });
    }
}
