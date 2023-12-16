package ru.mirea.markinaa.databasepractice.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
    /*private List idCheck = new ArrayList();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEquipmentCheckOneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");

        binding.butSearchEquipmentManager.setOnClickListener(new View.OnClickListener() {
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
                            ResultSet resultSet1 = statement.executeQuery("SELECT * FROM check_server WHERE id_check =" +
                                    binding.editTextIDClientCheckOne +
                                    "AND id_server = " +
                                    binding.editTextIDServer.getText().toString() +
                                    ";");
                            while (resultSet1.next()) {
                                checkServer.setIdServer(resultSet1.getString("id_server"));
                                checkServer.setIdRoom(resultSet1.getString("id_room"));
                                checkServer.setIdCheck(resultSet1.getString("id_check"));
                                checkServer.setAmountOfDays(resultSet1.getString("amount_of_days"));
                                checkServer.setPrice(resultSet1.getString("price"));
                                checkServer.setNumberOfServers(resultSet1.getString("number_of_servers"));
                            }
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

                    binding.textViewIDServerEquipment.setVisibility(View.VISIBLE);
                    binding.textViewIDRoomEquipment.setVisibility(View.VISIBLE);
                    binding.textViewIDCheckEquipment.setVisibility(View.VISIBLE);
                    binding.textViewAmountOfDays.setVisibility(View.VISIBLE);
                    binding.textViewPriceEquipmentOne.setVisibility(View.VISIBLE);
                    binding.textViewNumberOfServers.setVisibility(View.VISIBLE);

                    binding.editTextIDServer.setVisibility(View.VISIBLE);
                    binding.editTextIDRoomEquipment.setVisibility(View.VISIBLE);
                    binding.editTextIDCheckEquipment.setVisibility(View.VISIBLE);
                    binding.editTextAmountOfDaysEquipment.setVisibility(View.VISIBLE);
                    binding.editTextPriceEquipmentOne.setVisibility(View.VISIBLE);
                    binding.editTextNumberOfServers.setVisibility(View.VISIBLE);

                    binding.buttonDeleteEquipmentOne.setVisibility(View.VISIBLE);

                    binding.editTextIDServer.setText(checkServer.getIdServer());
                    binding.editTextIDRoomEquipment.setText(checkServer.getIdRoom());
                    binding.editTextIDCheckEquipment.setText(checkServer.getIdCheck());
                    binding.editTextAmountOfDaysEquipment.setText(checkServer.getAmountOfDays());
                    binding.editTextPriceEquipmentOne.setText(checkServer.getPrice());
                    binding.editTextNumberOfServers.setText(checkServer.getNumberOfServers());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        binding.buttonDeleteEquipmentOne.setOnClickListener(new View.OnClickListener() {
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
                            String sql = "DELETE FROM check_server WHERE id_check = " +
                                    binding.editTextIDClientCheckOne.getText().toString() +
                                    "AND id_server = " +
                                    binding.editTextIDServer.getText().toString() +
                                    ";";
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