package ru.mirea.markinaa.databasepractice.Administrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import ru.mirea.markinaa.databasepractice.DTO.Server;
import ru.mirea.markinaa.databasepractice.R;
import ru.mirea.markinaa.databasepractice.databinding.ActivityDataCanterBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityEquipmentBinding;

public class EquipmentActivity extends AppCompatActivity {

    private ActivityEquipmentBinding binding;
    private final Server server = new Server();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEquipmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");

        binding.textViewIdEquipment.setVisibility(View.GONE);
        binding.textViewNameEquipment.setVisibility(View.GONE);
        binding.textViewPriceEquipment.setVisibility(View.GONE);
        binding.textViewIdCPU.setVisibility(View.GONE);
        binding.textViewIdDrive.setVisibility(View.GONE);
        binding.textViewIdRam.setVisibility(View.GONE);

        binding.editTextIdEquipment.setVisibility(View.GONE);
        binding.editTextNameEquipment.setVisibility(View.GONE);
        binding.editTextPriceEquipment.setVisibility(View.GONE);
        binding.editTextIdCPU.setVisibility(View.GONE);
        binding.editTextIdDrive.setVisibility(View.GONE);
        binding.editTextIdRam.setVisibility(View.GONE);

        binding.butSaveEquipment.setVisibility(View.GONE);
        binding.butDeleteEquipment.setVisibility(View.GONE);

        binding.butSearchEquipment.setOnClickListener(new View.OnClickListener() {
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
                            ResultSet resultSet = statement.executeQuery("SELECT * FROM server " +
                                    "WHERE id = " + binding.editTextSearchEquipment.getText().toString() + ";");
                            while (resultSet.next()) {
                                server.setId(resultSet.getString("id"));
                                server.setName(resultSet.getString("name"));
                                server.setPrice(resultSet.getString("price"));
                                server.setIdCpu(resultSet.getString("id_cpu"));
                                server.setIdRam(resultSet.getString("id_ram"));
                                server.setIdDrive(resultSet.getString("id_drive"));
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

                    binding.textViewIdEquipment.setVisibility(View.VISIBLE);
                    binding.textViewNameEquipment.setVisibility(View.VISIBLE);
                    binding.textViewPriceEquipment.setVisibility(View.VISIBLE);
                    binding.textViewIdCPU.setVisibility(View.VISIBLE);
                    binding.textViewIdDrive.setVisibility(View.VISIBLE);
                    binding.textViewIdRam.setVisibility(View.VISIBLE);

                    binding.editTextIdEquipment.setVisibility(View.VISIBLE);
                    binding.editTextNameEquipment.setVisibility(View.VISIBLE);
                    binding.editTextPriceEquipment.setVisibility(View.VISIBLE);
                    binding.editTextIdCPU.setVisibility(View.VISIBLE);
                    binding.editTextIdDrive.setVisibility(View.VISIBLE);
                    binding.editTextIdRam.setVisibility(View.VISIBLE);

                    binding.butSaveEquipment.setVisibility(View.VISIBLE);
                    binding.butDeleteEquipment.setVisibility(View.VISIBLE);

                    binding.editTextIdEquipment.setText(server.getId());
                    binding.editTextNameEquipment.setText(server.getName());
                    binding.editTextPriceEquipment.setText(server.getPrice());
                    binding.editTextIdCPU.setText(server.getIdCpu());
                    binding.editTextIdRam.setText(server.getIdRam());
                    binding.editTextIdDrive.setText(server.getIdDrive());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        binding.butSaveEquipment.setOnClickListener(new View.OnClickListener() {
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
                            String sql = "UPDATE server SET " +
                                    "id = '" + binding.editTextIdEquipment.getText().toString() + "', " +
                                    "name = '" + binding.editTextNameEquipment.getText().toString() + "', " +
                                    "price = '" + binding.editTextPriceEquipment.getText().toString() + "', " +
                                    "id_cpu = '" + binding.editTextIdCPU.getText().toString() + "', " +
                                    "id_ram = '" + binding.editTextIdRam.getText().toString() + "', " +
                                    "id_drive = '" + binding.editTextIdDrive.getText().toString() +
                                    "' WHERE id = " + binding.editTextIdEquipment.getText().toString() + ";";
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
        binding.butDeleteEquipment.setOnClickListener(new View.OnClickListener() {
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
                            String sql = "DELETE FROM server WHERE id = " +
                                    binding.editTextIdEquipment.getText().toString() +
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