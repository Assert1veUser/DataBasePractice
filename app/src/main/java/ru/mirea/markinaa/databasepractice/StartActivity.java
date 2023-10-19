package ru.mirea.markinaa.databasepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.sql.DriverManager;

import ru.mirea.markinaa.databasepractice.Administrator.AdminActivity;
import ru.mirea.markinaa.databasepractice.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {

    private ActivityStartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.butSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread gfgThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try  {
                            DriverManager.getConnection(
                                    "jdbc:postgresql://192.168.0.163:5432/data_center",
                                    binding.editTextLogin.getText().toString(),
                                    binding.editTextPassword.getText().toString());
                            if (firstNChars(binding.editTextLogin.getText().toString(), 2)
                                    .equals("ad")){
                                Intent intent = new Intent(StartActivity.this,
                                        AdminActivity.class);
                                intent.putExtra("login", binding.editTextLogin.getText()
                                        .toString());
                                intent.putExtra("password", binding.editTextPassword.getText()
                                        .toString());
                                startActivity(intent);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                    }
                });

                gfgThread.start();
            }
        });
    }
    public static String firstNChars(String str, int n) {
        if (str == null) {
            return null;
        }

        return str.length() < n ? str : str.substring(0, n);
    }

}