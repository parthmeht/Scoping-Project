package com.app.scopingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    TextView name;

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        name = findViewById(R.id.txtName);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if(user==null){
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        name.setText("Hello, "+ user.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_logout:
                mAuth.signOut();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
