package ru.mirea.markinaa.databasepractice.Administrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import ru.mirea.markinaa.databasepractice.databinding.ActivityEmployeeBinding;

public class EmployeeGetSetActivity extends AppCompatActivity {

    private ActivityEmployeeBinding binding;
    String loginPut;
    String pasPut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmployeeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");

        binding.editTextAge.setVisibility(View.GONE);
        binding.editTextExperience.setVisibility(View.GONE);
        binding.editTextFullName.setVisibility(View.GONE);
        binding.editTextId.setVisibility(View.GONE);
        binding.editTextJobTitle.setVisibility(View.GONE);
        binding.editTextPhoneNumber.setVisibility(View.GONE);
        binding.editTextPlaceWork.setVisibility(View.GONE);
        binding.editTextSalary.setVisibility(View.GONE);
        binding.editTextEmail.setVisibility(View.GONE);

        binding.textViewAge.setVisibility(View.GONE);
        binding.textViewExperience.setVisibility(View.GONE);
        binding.textViewFullName.setVisibility(View.GONE);
        binding.textViewId.setVisibility(View.GONE);
        binding.textViewJobTitle.setVisibility(View.GONE);
        binding.textViewPhoneNumber.setVisibility(View.GONE);
        binding.textViewPlaceWork.setVisibility(View.GONE);
        binding.textViewSalary.setVisibility(View.GONE);
        binding.textViewEmail.setVisibility(View.GONE);

        binding.butSave.setVisibility(View.GONE);

        binding.butEmployeeSearch.setOnClickListener(new View.OnClickListener() {
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
                            /*ResultSet resultSet = statement.executeQuery("SELECT add_role_analyst( "
                                    + binding.editTextIdDataEnter.getText().toString()
                                    + ", "
                                    + binding.editTextIdAnalyst.getText().toString()
                                    + ");");
                            String login = "";
                            while (resultSet.next()) {
                                login = resultSet.getString("add_role_analyst");
                            }*/
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