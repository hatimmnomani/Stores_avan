package com.example.stores_avan.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
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

import com.example.stores_avan.Entities.userId;
import com.example.stores_avan.R;
import com.example.stores_avan.server.ServerFetch;
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
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import static android.widget.Toast.makeText;


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
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

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
            showE();
        }

        sharedPreferences = getSharedPreferences("com.avan.stores.userprofile", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        btnt2 = findViewById(R.id.abc);

        btn_login.setOnClickListener(v -> SignInGoogle());
        btn_logout.setOnClickListener(v -> Logout());


        btnt2.setOnClickListener(v -> showE());

    }

    private void Logout() {
        FirebaseAuth.getInstance().signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                task -> updateUI(null));
        Toast.makeText(MainActivity.this,"This Google user is not registed",Toast.LENGTH_SHORT).show();
        //Log.d(TAG,"This Google user is not registed");
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
                        //progressBar.setVisibility(View.INVISIBLE);

                        Log.d("TAG", "signInWithCredential:success");

                        FirebaseUser user = mAuth.getCurrentUser();
                        serverAuth(user);
                        Intent i = new Intent(this,ServerFetch.class);
                        i.putExtra("fetch","update");

                        updateUI(user);

                    } else {
                        progressBar.setVisibility(View.INVISIBLE);

                        Log.w("TAG", "signInWithCredential:failure", task.getException());

                        makeText(MainActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }
    public void serverAuth(FirebaseUser user){
        Intent intent = new Intent(this, ServerFetch.class);
        intent.putExtra("email",user.getEmail());
        intent.putExtra("name",user.getDisplayName());
        intent.putExtra("fetch","auth");
        startService(intent);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            //String photo = String.valueOf(user.getPhotoUrl());

            makeText(this,name, Toast.LENGTH_SHORT).show();
            makeText(this,email, Toast.LENGTH_SHORT).show();
            //Picasso.get().load(photo).into(image);
            btn_logout.setVisibility(View.VISIBLE);
            btn_login.setVisibility(View.INVISIBLE);
        } else {
            //text.setText("Firebase Login \n");
            //Picasso.get().load(R.drawable.avantika_logo).into(image);
            image.setImageDrawable(getResources().getDrawable(R.drawable.avantika_logo));
            btn_logout.setVisibility(View.INVISIBLE);
            btn_login.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(authRecver, new IntentFilter("broadcast.get.authentication"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(authRecver);
    }

    BroadcastReceiver authRecver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            @NonNull
            String action = intent.getAction();
            Log.d(TAG,"action: "+action);

            if(action.equalsIgnoreCase("broadcast.get.authentication")){
                String authJson = intent.getStringExtra("data");
                boolean failed = intent.getBooleanExtra("failed",true);

                if(failed){
                    Logout();
                } else {
                    userId profile = new Gson().fromJson(authJson,userId.class);
                    editor.putString("email",profile.personal_email);
                    editor.putString("first_name",profile.first_name);
                    editor.putString("last_name",profile.last_name);
                    editor.putInt("eid",profile.eid);
                    editor.commit();
                    //Toast.makeText(MainActivity.this,"Login Succesfull",Toast.LENGTH_SHORT).show();
                    Log.d(TAG,"==> recieved employee - "+profile.first_name);
                    showE();
                }
            }else{
                Toast.makeText(context,"Try again later, server down",Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void showE(){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }


}
