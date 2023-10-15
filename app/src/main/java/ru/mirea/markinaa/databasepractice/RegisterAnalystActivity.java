package ru.mirea.markinaa.databasepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import ru.mirea.markinaa.databasepractice.databinding.ActivityRegisterAnalystBinding;

public class RegisterAnalystActivity extends AppCompatActivity {

    private ActivityRegisterAnalystBinding binding;
    String loginPut;
    String pasPut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterAnalystBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");
        binding.butregisterAnalyst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread gfgThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try  {
                            Connection connection = DriverManager.getConnection(
                                    "jdbc:postgresql://192.168.0.163:5432/data_center",
                                    loginGet,
                                    passwordGet);
                            Statement statement = connection.createStatement();
                            ResultSet resultSet = statement.executeQuery("SELECT add_role_analyst( "
                                    + binding.editTextIdDataEnter.getText().toString()
                                    + ", "
                                    + binding.editTextIdAnalyst.getText().toString()
                                    + ");");
                            String login = "";
                            while (resultSet.next()) {
                                login = resultSet.getString("add_role_analyst");
                            }

                            String log[] = login.split(",");
                            loginPut = log[0].substring(1);
                            pasPut = log[1].substring(0, log[1].length() - 1);

                            System.out.println(login);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                gfgThread.start();
                try {
                    gfgThread.join();
                    binding.textViewLoginLine.setText(loginPut);
                    binding.textViewPasswordLine.setText(pasPut);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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