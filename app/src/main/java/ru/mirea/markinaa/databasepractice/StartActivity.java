package ru.mirea.markinaa.databasepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.List;

import ru.mirea.markinaa.databasepractice.Administrator.AdminActivity;
import ru.mirea.markinaa.databasepractice.analyst.AnalystActivity;
import ru.mirea.markinaa.databasepractice.databinding.ActivityStartBinding;
import ru.mirea.markinaa.databasepractice.manager.ManagerActivity;

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
                            Connection connection = DriverManager.getConnection(
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
                            } else if (firstNChars(binding.editTextLogin.getText().toString(), 2)
                                    .equals("an")) {
                                Intent intent = new Intent(StartActivity.this,
                                        AnalystActivity.class);
                                intent.putExtra("login", binding.editTextLogin.getText()
                                        .toString());
                                intent.putExtra("password", binding.editTextPassword.getText()
                                        .toString());
                                startActivity(intent);
                            } else if(firstNChars(binding.editTextLogin.getText().toString(), 2)
                                    .equals("ma")){
                                Intent intent = new Intent(StartActivity.this,
                                        ManagerActivity.class);
                                intent.putExtra("login", binding.editTextLogin.getText()
                                        .toString());
                                intent.putExtra("password", binding.editTextPassword.getText()
                                        .toString());
                                startActivity(intent);
                            }

                            connection.close();

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
    public static List<String> employeeNChars(String str){
        if (str == null) {
            return null;
        }
        str = str.replaceAll("[^0-9]+", " ");
        return Arrays.asList(str.trim().split(" "));
    }

}