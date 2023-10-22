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
    private String id;
    private String fullName;
    private String jobTitle;
    private String phoneNumber;
    private String experience;
    private String email;
    private String salary;
    private String placeWork;
    private String age;

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
                                    "jdbc:postgresql://192.168.0.163:5432/data_center",
                                    loginGet,
                                    passwordGet);
                            Statement statement = connection.createStatement();
                            System.out.println("DataBase start");
                            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee " +
                                    "WHERE id = " + binding.editTextSearchLine.getText().toString() + ";");
                            while (resultSet.next()) {
                                id = resultSet.getString("id");
                                fullName = resultSet.getString("full_name");
                                jobTitle = resultSet.getString("job_title");
                                phoneNumber = resultSet.getString("phone_number");
                                experience = resultSet.getString("experience");
                                email = resultSet.getString("email");
                                salary = resultSet.getString("salary");
                                placeWork = resultSet.getString("place_of_work");
                                age = resultSet.getString("age");

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

                    binding.editTextAge.setVisibility(View.VISIBLE);
                    binding.editTextExperience.setVisibility(View.VISIBLE);
                    binding.editTextFullName.setVisibility(View.VISIBLE);
                    binding.editTextId.setVisibility(View.VISIBLE);
                    binding.editTextJobTitle.setVisibility(View.VISIBLE);
                    binding.editTextPhoneNumber.setVisibility(View.VISIBLE);
                    binding.editTextPlaceWork.setVisibility(View.VISIBLE);
                    binding.editTextSalary.setVisibility(View.VISIBLE);
                    binding.editTextEmail.setVisibility(View.VISIBLE);

                    binding.textViewAge.setVisibility(View.VISIBLE);
                    binding.textViewExperience.setVisibility(View.VISIBLE);
                    binding.textViewFullName.setVisibility(View.VISIBLE);
                    binding.textViewId.setVisibility(View.VISIBLE);
                    binding.textViewJobTitle.setVisibility(View.VISIBLE);
                    binding.textViewPhoneNumber.setVisibility(View.VISIBLE);
                    binding.textViewPlaceWork.setVisibility(View.VISIBLE);
                    binding.textViewSalary.setVisibility(View.VISIBLE);
                    binding.textViewEmail.setVisibility(View.VISIBLE);

                    binding.butSave.setVisibility(View.VISIBLE);

                    binding.editTextId.setText(id);
                    binding.editTextFullName.setText(fullName);
                    binding.editTextJobTitle.setText(jobTitle);
                    binding.editTextPhoneNumber.setText(phoneNumber);
                    binding.editTextExperience.setText(experience);
                    binding.editTextEmail.setText(email);
                    binding.editTextSalary.setText(salary);
                    binding.editTextPlaceWork.setText(placeWork);
                    binding.editTextAge.setText(age);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        binding.butSave.setOnClickListener(new View.OnClickListener() {
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
                            String sql = "UPDATE employee SET " +
                                    "id = '" + binding.editTextId.getText().toString() + "', " +
                                    "full_name = '" + binding.editTextFullName.getText().toString() + "', " +
                                    "job_title = '" + binding.editTextJobTitle.getText().toString() + "', " +
                                    "phone_number = '" + binding.editTextPhoneNumber.getText().toString() + "', " +
                                    "experience = '" + binding.editTextExperience.getText().toString() + "', " +
                                    "email = '" + binding.editTextEmail.getText().toString() + "', " +
                                    "salary = '" + binding.editTextSalary.getText().toString() + "', " +
                                    "place_of_work = '" + binding.editTextPlaceWork.getText().toString() + "', " +
                                    "age = '" + binding.editTextAge.getText().toString() +
                                    "' WHERE id = " + binding.editTextId.getText().toString() + ";";
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
