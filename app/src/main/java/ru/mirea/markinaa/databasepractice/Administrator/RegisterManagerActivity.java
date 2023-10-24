package ru.mirea.markinaa.databasepractice.Administrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import ru.mirea.markinaa.databasepractice.databinding.ActivityRegisterAnalystBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityRegisterManagerBinding;

public class RegisterManagerActivity extends AppCompatActivity {

    private ActivityRegisterManagerBinding binding;
    String loginPut;
    String pasPut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterManagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");

        binding.butregisterManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread gfgThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try  {
                            Connection connection = DriverManager.getConnection(
                                    "jdbc:postgresql://172.20.10.2:5432/data_center",
                                    loginGet,
                                    passwordGet);
                            Statement statement = connection.createStatement();
                            ResultSet resultSet = statement.executeQuery("SELECT add_role_manager( "
                                    + binding.editTextIdManager.getText().toString() + ");");
                            String login = "";
                            while (resultSet.next()) {
                                login = resultSet.getString("add_role_manager");
                            }

                            String log[] = login.split(",");
                            loginPut = log[0].substring(1);
                            pasPut = log[1].substring(0, log[1].length() - 1);

                            System.out.println(login);

                            resultSet.close();
                            statement.close();
                            connection.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                gfgThread.start();
                try {
                    gfgThread.join();
                    binding.textViewLoginLineManager.setText(loginPut);
                    binding.textViewPasswordLineManager.setText(pasPut);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}