package ru.mirea.markinaa.databasepractice.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import ru.mirea.markinaa.databasepractice.R;
import ru.mirea.markinaa.databasepractice.databinding.ActivityEquipmentNewBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityServiceNewBinding;

public class ServiceNewActivity extends AppCompatActivity {

    private ActivityServiceNewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceNewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");

        binding.butNewService.setOnClickListener(new View.OnClickListener() {
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
                            String sql = "INSERT INTO check_service VALUES (" +
                                    binding.editTextIdServiceNew +
                                    ", '" +
                                    binding.editTextIdRoomNew +
                                    "', '" +
                                    binding.editTextIdCheckNew +
                                    "', " +
                                    binding.editTextAmountOfDaysServiceNew +
                                    ", " +
                                    binding.editTextPriceServiceNew +
                                    ", " +
                                    binding.editTextNumberOfServiceNew +
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