package ru.mirea.markinaa.databasepractice.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ru.mirea.markinaa.databasepractice.Administrator.EquipmentActivity;
import ru.mirea.markinaa.databasepractice.R;
import ru.mirea.markinaa.databasepractice.analyst.ServerTableAnalystActivity;
import ru.mirea.markinaa.databasepractice.application.ApplicationActivity;
import ru.mirea.markinaa.databasepractice.databinding.ActivityClientTableManagerBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityEquipmentTableManagerBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityManagerBinding;

public class EquipmentTableManagerActivity extends AppCompatActivity {
    ActivityEquipmentTableManagerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEquipmentTableManagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");

        binding.butEquipmentAllTableManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EquipmentTableManagerActivity.this, EquipmentCheckAllActivity.class);
                intent.putExtra("login", loginGet);
                intent.putExtra("password", passwordGet);
                startActivity(intent);
            }
        });
        binding.butEquipmentOneTableManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EquipmentTableManagerActivity.this, EquipmentActivity.class);
                intent.putExtra("login", loginGet);
                intent.putExtra("password", passwordGet);
                startActivity(intent);
            }
        });
        binding.butNewEquipmentTableManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EquipmentTableManagerActivity.this, EquipmentNewActivity.class);
                intent.putExtra("login", loginGet);
                intent.putExtra("password", passwordGet);
                startActivity(intent);
            }
        });
    }
}