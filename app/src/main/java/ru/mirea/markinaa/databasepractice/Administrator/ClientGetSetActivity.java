package ru.mirea.markinaa.databasepractice.Administrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import ru.mirea.markinaa.databasepractice.DTO.Client;
import ru.mirea.markinaa.databasepractice.R;
import ru.mirea.markinaa.databasepractice.databinding.ActivityAdminBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityClientGetSetBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityEmployeeBinding;

public class ClientGetSetActivity extends AppCompatActivity {

    private ActivityClientGetSetBinding binding;
    private final Client client = new Client();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClientGetSetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.editTextBonus.setVisibility(View.GONE);
        binding.editTextEmailClient.setVisibility(View.GONE);
        binding.editTextFullNameClient.setVisibility(View.GONE);
        binding.editTextIdManagerForClient.setVisibility(View.GONE);
        binding.editTextMoney.setVisibility(View.GONE);
        binding.editTextStatus.setVisibility(View.GONE);
        binding.editTextPhoneNumberClient.setVisibility(View.GONE);
        binding.editTextIdClient.setVisibility(View.GONE);

        binding.textViewBonus.setVisibility(View.GONE);
        binding.textViewFullNameClient.setVisibility(View.GONE);
        binding.textViewIdClient.setVisibility(View.GONE);
        binding.textViewMoney.setVisibility(View.GONE);
        binding.textViewStatus.setVisibility(View.GONE);
        binding.textViewPhoneNumberClient.setVisibility(View.GONE);
        binding.textViewIDManager.setVisibility(View.GONE);
        binding.textViewEmailClient.setVisibility(View.GONE);

        /*binding.butSaveClient.setVisibility(View.GONE);*/
        binding.butDeleteClient.setVisibility(View.GONE);

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");

        binding.butSearchClient.setOnClickListener(new View.OnClickListener() {
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
                            ResultSet resultSet = statement.executeQuery("SELECT * FROM client " +
                                    "WHERE id = " + binding.editTextSearchLineClient.getText().toString() + ";");
                            while (resultSet.next()) {
                                client.setId(resultSet.getString("id"));
                                client.setFullName(resultSet.getString("full_name"));
                                client.setEmail(resultSet.getString("email"));
                                client.setPhoneNumber(resultSet.getString("phone_number"));
                                client.setStatus(resultSet.getString("status"));
                                client.setBonus(resultSet.getString("bonus"));
                                client.setMoney(resultSet.getString("money"));
                                client.setIdManager(resultSet.getString("id_manager"));
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

                    binding.editTextBonus.setVisibility(View.VISIBLE);
                    binding.editTextEmailClient.setVisibility(View.VISIBLE);
                    binding.editTextFullNameClient.setVisibility(View.VISIBLE);
                    binding.editTextIdManagerForClient.setVisibility(View.VISIBLE);
                    binding.editTextMoney.setVisibility(View.VISIBLE);
                    binding.editTextStatus.setVisibility(View.VISIBLE);
                    binding.editTextPhoneNumberClient.setVisibility(View.VISIBLE);
                    binding.editTextIdClient.setVisibility(View.VISIBLE);

                    binding.textViewBonus.setVisibility(View.VISIBLE);
                    binding.textViewFullNameClient.setVisibility(View.VISIBLE);
                    binding.textViewIdClient.setVisibility(View.VISIBLE);
                    binding.textViewMoney.setVisibility(View.VISIBLE);
                    binding.textViewStatus.setVisibility(View.VISIBLE);
                    binding.textViewPhoneNumberClient.setVisibility(View.VISIBLE);
                    binding.textViewIDManager.setVisibility(View.VISIBLE);
                    binding.textViewEmailClient.setVisibility(View.VISIBLE);

                    /*binding.butSaveClient.setVisibility(View.VISIBLE);*/
                    binding.butDeleteClient.setVisibility(View.VISIBLE);

                    binding.editTextIdClient.setText(client.getId());
                    binding.editTextFullNameClient.setText(client.getFullName());
                    binding.editTextEmailClient.setText(client.getEmail());
                    binding.editTextPhoneNumberClient.setText(client.getPhoneNumber());
                    binding.editTextStatus.setText(client.getStatus());
                    binding.editTextBonus.setText(client.getBonus());
                    binding.editTextMoney.setText(client.getMoney());
                    binding.editTextIdManagerForClient.setText(client.getIdManager());


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        /*binding.butSaveClient.setOnClickListener(new View.OnClickListener() {
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
                            String sql = "UPDATE client SET " +
                                    "id = '" + binding.editTextIdClient.getText().toString() + "', " +
                                    "full_name = '" + binding.editTextFullNameClient.getText().toString() + "', " +
                                    "email = '" + binding.editTextEmailClient.getText().toString() + "', " +
                                    "phone_number = '" + binding.editTextPhoneNumberClient.getText().toString() + "', " +
                                    "status = '" + binding.editTextStatus.getText().toString() + "', " +
                                    "bonus = '" + binding.editTextBonus.getText().toString() + "', " +
                                    "money = '" + binding.editTextMoney.getText().toString() + "', " +
                                    "id_manager = '" + binding.editTextIdManagerForClient.getText().toString() +
                                    "' WHERE id = " + binding.editTextIdClient.getText().toString() + ";";
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
        });*/
        binding.butDeleteClient.setOnClickListener(new View.OnClickListener() {
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
                            String sql = "DELETE FROM client WHERE id = " +
                                    binding.editTextIdClient.getText().toString() +
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