package ru.mirea.markinaa.databasepractice.Administrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ru.mirea.markinaa.databasepractice.databinding.ActivityMainBinding;

public class AdminActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");

        binding.butRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, RegisterActivity.class);
                intent.putExtra("login", loginGet);
                intent.putExtra("password", passwordGet);
                startActivity(intent);
            }
        });
        binding.butDataCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, DataCanterActivity.class);
                intent.putExtra("login", loginGet);
                intent.putExtra("password", passwordGet);
                startActivity(intent);
            }
        });
        binding.butEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, EmployeeGetSetActivity.class);
                intent.putExtra("login", loginGet);
                intent.putExtra("password", passwordGet);
                startActivity(intent);
            }
        });
    }
}