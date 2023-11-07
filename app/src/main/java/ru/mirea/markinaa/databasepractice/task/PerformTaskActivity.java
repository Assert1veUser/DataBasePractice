package ru.mirea.markinaa.databasepractice.task;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import ru.mirea.markinaa.databasepractice.R;
import ru.mirea.markinaa.databasepractice.databinding.ActivityNewTaskBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityPerformTaskBinding;

public class PerformTaskActivity extends AppCompatActivity {

    private ActivityPerformTaskBinding binding;
    private String task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPerformTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");

        binding.butSearchTask.setOnClickListener(new View.OnClickListener() {
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
                            ResultSet resultSet = statement.executeQuery("SELECT * FROM task " +
                                    "WHERE id = " + binding.editTextSerchTask.getText().toString() + ";");
                            while (resultSet.next()) {
                                task = resultSet.getString("task");
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

                    binding.textViewTaskContent.setVisibility(View.VISIBLE);
                    binding.butPerformTaskThis.setVisibility(View.VISIBLE);

                    binding.textViewTaskContent.setText(task);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        binding.butPerformTaskThis.setOnClickListener(new View.OnClickListener() {
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
                            String sql = "UPDATE task SET " +
                                    "active = '" + true +
                                    "' WHERE id = " + binding.editTextSerchTask.getText().toString() + ";";
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