package ru.mirea.markinaa.databasepractice.analyst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import ru.mirea.markinaa.databasepractice.R;
import ru.mirea.markinaa.databasepractice.databinding.ActivityAnalystBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityTableAnalystBinding;

public class TableAnalystActivity extends AppCompatActivity {

    private ActivityTableAnalystBinding binding;
    private String tableNameThis;
    private String loginGet;
    private String passwordGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTableAnalystBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        tableNameThis = intentGet.getStringExtra("tableName");


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
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM " +
                            tableNameThis + ";");
                    while (resultSet.next()) {
                        /*application.add(resultSet.getString("application"));
                        date.add(resultSet.getDate("date"));
                        idClient.add(resultSet.getString("id_client"));*/
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
    }
}