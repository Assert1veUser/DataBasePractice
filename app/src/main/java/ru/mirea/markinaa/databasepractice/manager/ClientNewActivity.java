package ru.mirea.markinaa.databasepractice.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import ru.mirea.markinaa.databasepractice.R;
import ru.mirea.markinaa.databasepractice.databinding.ActivityAdminBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityClientNewBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityEquipmentNewBinding;

public class ClientNewActivity extends AppCompatActivity {

    private ActivityClientNewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClientNewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");

        binding.butNewClient.setOnClickListener(new View.OnClickListener() {
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
                            System.out.println("DataBase start");
                            String sql = "INSERT INTO client VALUES (" +
                                    binding.editTextIDNew +
                                    ", '" +
                                    binding.editTextFullNameNew +
                                    "', '" +
                                    binding.editTextEmailNew +
                                    "', " +
                                    binding.editTextPhoneNumberNew +
                                    ", " +
                                    binding.editTextStatusNew +
                                    ", " +
                                    binding.editTextBonusNew +
                                    ", " +
                                    binding.editTextMoneyNew +
                                    ", " +
                                    binding.editTextIDManagerNew +
                                    ");";
                            statement.executeUpdate(sql);
                            statement.close();
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
}