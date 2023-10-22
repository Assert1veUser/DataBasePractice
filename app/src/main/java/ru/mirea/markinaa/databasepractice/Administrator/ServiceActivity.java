package ru.mirea.markinaa.databasepractice.Administrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import ru.mirea.markinaa.databasepractice.R;
import ru.mirea.markinaa.databasepractice.databinding.ActivityEquipmentBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityServiceBinding;

public class ServiceActivity extends AppCompatActivity {

    private ActivityServiceBinding binding;
    private String id;
    private String name;
    private String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");

        binding.textViewIdService.setVisibility(View.GONE);
        binding.textViewNameService.setVisibility(View.GONE);
        binding.textViewPriceService.setVisibility(View.GONE);

        binding.editTextIdService.setVisibility(View.GONE);
        binding.editTextNameService.setVisibility(View.GONE);
        binding.editTextPriceService.setVisibility(View.GONE);

        binding.butSaveService.setVisibility(View.GONE);

        binding.butSearchService.setOnClickListener(new View.OnClickListener() {
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
                            ResultSet resultSet = statement.executeQuery("SELECT * FROM service " +
                                    "WHERE id = " + binding.editTextSearchService.getText().toString() + ";");
                            while (resultSet.next()) {
                                id = resultSet.getString("id");
                                name = resultSet.getString("name");
                                price = resultSet.getString("price");
                            }
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

                    binding.textViewIdService.setVisibility(View.VISIBLE);
                    binding.textViewNameService.setVisibility(View.VISIBLE);
                    binding.textViewPriceService.setVisibility(View.VISIBLE);

                    binding.editTextIdService.setVisibility(View.VISIBLE);
                    binding.editTextNameService.setVisibility(View.VISIBLE);
                    binding.editTextPriceService.setVisibility(View.VISIBLE);

                    binding.butSaveService.setVisibility(View.VISIBLE);

                    binding.editTextIdService.setText(id);
                    binding.editTextNameService.setText(name);
                    binding.editTextPriceService.setText(price);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        binding.butSaveService.setOnClickListener(new View.OnClickListener() {
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
                            String sql = "UPDATE service SET " +
                                    "id = '" + binding.editTextIdService.getText().toString() + "', " +
                                    "name = '" + binding.editTextNameService.getText().toString() + "', " +
                                    "price = '" + binding.editTextPriceService.getText().toString() +
                                    "' WHERE id = " + binding.editTextIdService.getText().toString() + ";";
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