package ru.mirea.markinaa.databasepractice.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ru.mirea.markinaa.databasepractice.Administrator.ServiceActivity;
import ru.mirea.markinaa.databasepractice.R;
import ru.mirea.markinaa.databasepractice.analyst.ServiceTableAnalystActivity;
import ru.mirea.markinaa.databasepractice.databinding.ActivityManagerBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityServiceTableManagerBinding;
import ru.mirea.markinaa.databasepractice.task.TaskActivity;

public class ServiceTableManagerActivity extends AppCompatActivity {

    private ActivityServiceTableManagerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceTableManagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");

        binding.butServiceAllTableManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ServiceTableManagerActivity.this, ServiceCheckAllActivity.class);
                intent.putExtra("login", loginGet);
                intent.putExtra("password", passwordGet);
                startActivity(intent);
            }
        });
        binding.butServiceOneTableManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceTableManagerActivity.this, ServiceCheckOneActivity.class);
                intent.putExtra("login", loginGet);
                intent.putExtra("password", passwordGet);
                startActivity(intent);
            }
        });
        binding.butServiceNewTableManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceTableManagerActivity.this, ServiceNewActivity.class);
                intent.putExtra("login", loginGet);
                intent.putExtra("password", passwordGet);
                startActivity(intent);
            }
        });
    }
}