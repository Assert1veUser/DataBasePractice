package ru.mirea.markinaa.databasepractice.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ru.mirea.markinaa.databasepractice.DTO.CheckServer;
import ru.mirea.markinaa.databasepractice.R;
import ru.mirea.markinaa.databasepractice.databinding.ActivityEquipmentCheckAllBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityEquipmentCheckOneBinding;

public class EquipmentCheckOneActivity extends AppCompatActivity {

    private ActivityEquipmentCheckOneBinding binding;
    private CheckServer checkServer = new CheckServer();
    private List idCheck = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEquipmentCheckOneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");

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
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM check_of_client WHERE id_client = " +

                            binding.editTextIDClientCheckOne.getText().toString() +
                            ";");
                    while (resultSet.next()) {
                        idCheck.add(resultSet.getString("id"));
                    }
                    resultSet.close();
                    for (int i = 0; i <= idCheck.size(); i++){
                        ResultSet resultSet1 = statement.executeQuery("SELECT * FROM check_server WHERE id_check =" +
                                idCheck.get(i).toString() +
                                ";");
                        while (resultSet1.next()) {
                            /*idServer.add(resultSet.getString("id_server"));
                            idRoom.add(resultSet.getString("id_room"));
                            amountOfDays.add(resultSet.getString("amount_of_days"));
                            price.add(resultSet.getString("price"));
                            numberOfServers.add(resultSet.getString("number_of_servers"));*/
                        }
                        if (i == idCheck.size() - 1){
                            resultSet1.close();
                        }
                    }
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