package ru.mirea.markinaa.databasepractice.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import ru.mirea.markinaa.databasepractice.DTO.CheckService;
import ru.mirea.markinaa.databasepractice.R;
import ru.mirea.markinaa.databasepractice.databinding.ActivityServiceCheckOneBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityServiceTableManagerBinding;

public class ServiceCheckOneActivity extends AppCompatActivity {

    private ActivityServiceCheckOneBinding binding;
    private CheckService checkService = new CheckService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceCheckOneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");

        binding.butSearchSevrviceOne.setOnClickListener(new View.OnClickListener() {
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
                            /*ResultSet resultSet = statement.executeQuery("SELECT * FROM check_of_client WHERE id_client = " +

                                    binding.editTextIDClientCheckOne.getText().toString() +
                                    ";");
                            while (resultSet.next()) {
                                idCheck.add(resultSet.getString("id"));
                            }
                            resultSet.close();*/
                            ResultSet resultSet1 = statement.executeQuery("SELECT * FROM check_service WHERE id_check =" +
                                    binding.editTextIdClientServiceOne +
                                    "AND id_service = " +
                                    binding.editTextIdServiceOne.getText().toString() +
                                    ";");
                            while (resultSet1.next()) {
                                checkService.setIsService(resultSet1.getString("id_service"));
                                checkService.setIdRoom(resultSet1.getString("id_room"));
                                checkService.setIdCheck(resultSet1.getString("id_check"));
                                checkService.setAmountOfDays(resultSet1.getString("amount_of_days"));
                                checkService.setPrice(resultSet1.getString("price"));
                                checkService.setNumberOfService(resultSet1.getString("number_of_service"));
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

                    binding.textViewIdServiceOneManager.setVisibility(View.VISIBLE);
                    binding.textViewIdRoomOne.setVisibility(View.VISIBLE);
                    binding.textViewIdCheckOne.setVisibility(View.VISIBLE);
                    binding.textViewAmountOfDaysOne.setVisibility(View.VISIBLE);
                    binding.textViewPriceOne.setVisibility(View.VISIBLE);
                    binding.textViewNumberOfServiceOne.setVisibility(View.VISIBLE);

                    binding.editTextIdServiceOneManager.setVisibility(View.VISIBLE);
                    binding.editTextIdRoomOne.setVisibility(View.VISIBLE);
                    binding.editTextIdCheckOne.setVisibility(View.VISIBLE);
                    binding.editTextAmountOfDaysOne.setVisibility(View.VISIBLE);
                    binding.editTextPriceOne.setVisibility(View.VISIBLE);
                    binding.editTextNumberOfServiceOne.setVisibility(View.VISIBLE);

                    binding.butDeleteOneService.setVisibility(View.VISIBLE);

                    binding.editTextIdServiceOneManager.setText(checkService.getIsService());
                    binding.editTextIdRoomOne.setText(checkService.getIdRoom());
                    binding.editTextIdCheckOne.setText(checkService.getIdCheck());
                    binding.editTextAmountOfDaysOne.setText(checkService.getAmountOfDays());
                    binding.editTextPriceOne.setText(checkService.getPrice());
                    binding.editTextNumberOfServiceOne.setText(checkService.getNumberOfService());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        binding.butDeleteOneService.setOnClickListener(new View.OnClickListener() {
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
                            String sql = "DELETE FROM check_service WHERE id_check = " +
                                    binding.editTextIdClientServiceOne.getText().toString() +
                                    "AND id_service = " +
                                    binding.editTextIdServiceOne.getText().toString() +
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