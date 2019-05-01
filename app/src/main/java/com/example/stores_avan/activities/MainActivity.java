package com.example.stores_avan.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stores_avan.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Button btnPForm;
    Button btnProduct;
    Button btnHistory;
    Button btnStatus;
    Button btnt;
    Button btnt2;
    static final int GOOGLE_SIGN =123;
    FirebaseAuth mAuth;
    Button btn_login;
    TextView text;
    ImageView image;
    ProgressBar progressBar;
    GoogleSignInClient mGoogleSignInClient;
    Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = findViewById(R.id.login);
        btn_logout = findViewById(R.id.logout);
        image = findViewById(R.id.imageView2);
        progressBar=findViewById(R.id.progress_circular);

        mAuth= FirebaseAuth.getInstance();

        GoogleSignInOptions googleSignInOptions =new GoogleSignInOptions
                .Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);

        if (mAuth.getCurrentUser() != null) {
            FirebaseUser user = mAuth.getCurrentUser();
            updateUI(user);
        }


        btnt = findViewById(R.id.xyz);
        btnt2 = findViewById(R.id.abc);

        btn_login.setOnClickListener(v -> SignInGoogle());
        btn_logout.setOnClickListener(v -> Logout());

        btnt.setOnClickListener(v -> showT());
        btnt2.setOnClickListener(v -> showE());

    }

    private void Logout() {
        FirebaseAuth.getInstance().signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                task -> updateUI(null));
    }

    public void SignInGoogle(){
        progressBar.setVisibility(View.VISIBLE);
        Intent signIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signIntent,GOOGLE_SIGN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGN){
            Task<GoogleSignInAccount> task= GoogleSignIn
                    .getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "Login attempt: "+ account.getEmail());
                if(account != null) firebaseAuthWithGoogle(account);
            } catch (ApiException e){
                Log.w("TAG","Google Sign in failed",e);
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("TAG", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        progressBar.setVisibility(View.INVISIBLE);

                        Log.d("TAG", "signInWithCredential:success");

                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        progressBar.setVisibility(View.INVISIBLE);

                        Log.w("TAG", "signInWithCredential:failure", task.getException());

                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            String photo = String.valueOf(user.getPhotoUrl());

            Toast.makeText(this,name, Toast.LENGTH_SHORT).show();
            Toast.makeText(this,email, Toast.LENGTH_SHORT).show();
            Picasso.get().load(photo).into(image);
            btn_logout.setVisibility(View.VISIBLE);
            btn_login.setVisibility(View.INVISIBLE);
        } else {
            //text.setText("Firebase Login \n");
            Picasso.get().load(R.drawable.avan_logo).into(image);
            btn_logout.setVisibility(View.INVISIBLE);
            btn_login.setVisibility(View.VISIBLE);
        }
    }

    public void showE(){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
    public void showT(){
        Intent intent = new Intent(this, recycle.class);
        startActivity(intent);
    }

}
