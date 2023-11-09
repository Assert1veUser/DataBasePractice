package ru.mirea.markinaa.databasepractice.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import ru.mirea.markinaa.databasepractice.R;
import ru.mirea.markinaa.databasepractice.databinding.ActivityEquipmentCheckOneBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityEquipmentNewBinding;

public class EquipmentNewActivity extends AppCompatActivity {

    private ActivityEquipmentNewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEquipmentNewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");

        binding.butNewEquipment.setOnClickListener(new View.OnClickListener() {
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
                            String sql = "INSERT INTO check_server VALUES (" +
                                    binding.editTextIDServerNew +
                                    ", '" +
                                    binding.editTextIDRoomNew +
                                    "', '" +
                                    binding.editTextIDCheckNew +
                                    "', " +
                                    binding.editTextAmountOfDaysNew +
                                    ", " +
                                    binding.editTextPriceNew +
                                    ", " +
                                    binding.editTextNumberOfServersNew +
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