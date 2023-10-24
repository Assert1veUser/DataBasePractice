package ru.mirea.markinaa.databasepractice.task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import ru.mirea.markinaa.databasepractice.databinding.ActivityNewTaskBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityTaskBinding;

public class NewTaskActivity extends AppCompatActivity {

    private ActivityNewTaskBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");

        binding.butNewTaskAdd.setOnClickListener(new View.OnClickListener() {
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
                            System.out.println("DataBase start");
                            statement.executeQuery("INSERT INTO task (task, " +
                                    "active, id_employee) VALUES " + "('" +
                                    binding.editTextTask.getText().toString() +
                                    "',false," +
                                    binding.editTextIdEmployee.getText().toString() +
                                    ");");

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