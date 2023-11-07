package ru.mirea.markinaa.databasepractice.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ru.mirea.markinaa.databasepractice.Administrator.ClientGetSetActivity;
import ru.mirea.markinaa.databasepractice.R;
import ru.mirea.markinaa.databasepractice.analyst.ClientTableAnalystActivity;
import ru.mirea.markinaa.databasepractice.databinding.ActivityClientTableManagerBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityManagerBinding;
import ru.mirea.markinaa.databasepractice.task.TaskActivity;

public class ClientTableManagerActivity extends AppCompatActivity {

    private ActivityClientTableManagerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClientTableManagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");

        binding.butAllTableClientManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClientTableManagerActivity.this, ClientTableAnalystActivity.class);
                intent.putExtra("login", loginGet);
                intent.putExtra("password", passwordGet);
                startActivity(intent);
            }
        });
        binding.butOneTableClientManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClientTableManagerActivity.this, ClientGetSetActivity.class);
                intent.putExtra("login", loginGet);
                intent.putExtra("password", passwordGet);
                startActivity(intent);
            }
        });
        binding.butNewTableClientManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClientTableManagerActivity.this, ClientNewActivity.class);
                intent.putExtra("login", loginGet);
                intent.putExtra("password", passwordGet);
                startActivity(intent);
            }
        });
    }
}