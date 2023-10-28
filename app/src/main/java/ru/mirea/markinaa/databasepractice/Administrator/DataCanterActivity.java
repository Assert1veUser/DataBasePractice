package ru.mirea.markinaa.databasepractice.Administrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import ru.mirea.markinaa.databasepractice.DTO.Room;
import ru.mirea.markinaa.databasepractice.databinding.ActivityDataCanterBinding;

public class DataCanterActivity extends AppCompatActivity {

    private ActivityDataCanterBinding binding;
    private final Room room = new Room();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataCanterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");

        binding.textViewIdDataCenter.setVisibility(View.GONE);
        binding.textViewAddressDataCenter.setVisibility(View.GONE);
        binding.textViewPostCodeDataCenter.setVisibility(View.GONE);
        binding.textViewPhoneNumberDataCenter.setVisibility(View.GONE);
        binding.textViewNumberEmployeeDataCenter.setVisibility(View.GONE);

        binding.editTextIdDataCenter.setVisibility(View.GONE);
        binding.editTextAddressDataCenter.setVisibility(View.GONE);
        binding.editTextPostCodeDataCenter.setVisibility(View.GONE);
        binding.editTextPhoneNumberDataCenter.setVisibility(View.GONE);
        binding.editTextNumberEmployeeDataCenter.setVisibility(View.GONE);

        binding.butSaveDataCenter.setVisibility(View.GONE);


        binding.butSearchDataCenter.setOnClickListener(new View.OnClickListener() {
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
                            ResultSet resultSet = statement.executeQuery("SELECT * FROM room " +
                                    "WHERE id = " + binding.editTextSearchDataCenter.getText().toString() + ";");
                            while (resultSet.next()) {
                                room.setId(resultSet.getString("id"));
                                room.setAddress(resultSet.getString("address"));
                                room.setPostcode(resultSet.getString("postcode"));
                                room.setPhoneNumber(resultSet.getString("phone_number"));
                                room.setNumberOfEmployee(resultSet.getString("number_of_employee"));
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

                    binding.textViewIdDataCenter.setVisibility(View.VISIBLE);
                    binding.textViewAddressDataCenter.setVisibility(View.VISIBLE);
                    binding.textViewPostCodeDataCenter.setVisibility(View.VISIBLE);
                    binding.textViewPhoneNumberDataCenter.setVisibility(View.VISIBLE);
                    binding.textViewNumberEmployeeDataCenter.setVisibility(View.VISIBLE);

                    binding.editTextIdDataCenter.setVisibility(View.VISIBLE);
                    binding.editTextAddressDataCenter.setVisibility(View.VISIBLE);
                    binding.editTextPostCodeDataCenter.setVisibility(View.VISIBLE);
                    binding.editTextPhoneNumberDataCenter.setVisibility(View.VISIBLE);
                    binding.editTextNumberEmployeeDataCenter.setVisibility(View.VISIBLE);

                    binding.butSaveDataCenter.setVisibility(View.VISIBLE);

                    binding.editTextIdDataCenter.setText(room.getId());
                    binding.editTextAddressDataCenter.setText(room.getAddress());
                    binding.editTextPostCodeDataCenter.setText(room.getPostcode());
                    binding.editTextPhoneNumberDataCenter.setText(room.getPhoneNumber());
                    binding.editTextNumberEmployeeDataCenter.setText(room.getNumberOfEmployee());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        binding.butSaveDataCenter.setOnClickListener(new View.OnClickListener() {
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
                            String sql = "UPDATE room SET " +
                                    "id = '" + binding.editTextIdDataCenter.getText().toString() + "', " +
                                    "address = '" + binding.editTextAddressDataCenter.getText().toString() + "', " +
                                    "postcode = '" + binding.editTextPostCodeDataCenter.getText().toString() + "', " +
                                    "phone_number = '" + binding.editTextPhoneNumberDataCenter.getText().toString() + "', " +
                                    "number_of_employee = '" + binding.editTextNumberEmployeeDataCenter.getText().toString() +
                                    "' WHERE id = " + binding.editTextIdDataCenter.getText().toString() + ";";
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