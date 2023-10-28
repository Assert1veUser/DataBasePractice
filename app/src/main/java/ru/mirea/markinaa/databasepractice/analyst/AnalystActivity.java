package ru.mirea.markinaa.databasepractice.analyst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import ru.mirea.markinaa.databasepractice.Administrator.AdminActivity;
import ru.mirea.markinaa.databasepractice.R;
import ru.mirea.markinaa.databasepractice.application.ApplicationActivity;
import ru.mirea.markinaa.databasepractice.databinding.ActivityAnalystBinding;
import ru.mirea.markinaa.databasepractice.task.TaskActivity;

public class AnalystActivity extends AppCompatActivity {

    private ActivityAnalystBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAnalystBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");

        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.tableListAnalyst,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerTableAnalyst.setAdapter(adapter);

        binding.butTaskAnalyst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AnalystActivity.this, TaskActivity.class);
                intent.putExtra("login", loginGet);
                intent.putExtra("password", passwordGet);
                startActivity(intent);
            }
        });
        binding.butCheckTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(binding.spinnerTableAnalyst.getSelectedItem().toString());
                if (binding.spinnerTableAnalyst.getSelectedItem().toString().equals("check_of_client")){
                    Intent intent = new Intent(AnalystActivity.this,
                            CheckOfClientTableAnalystActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    startActivity(intent);
                } else if (binding.spinnerTableAnalyst.getSelectedItem().toString().equals("check_server")) {
                    Intent intent = new Intent(AnalystActivity.this,
                            CheckServerTableAnalystActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    startActivity(intent);
                } else if (binding.spinnerTableAnalyst.getSelectedItem().toString().equals("check_service")) {
                    Intent intent = new Intent(AnalystActivity.this,
                            CheckServiceTableAnalystActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                } else if (binding.spinnerTableAnalyst.getSelectedItem().toString().equals("client")) {
                    Intent intent = new Intent(AnalystActivity.this,
                            ClientTableAnalystActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    startActivity(intent);
                } else if (binding.spinnerTableAnalyst.getSelectedItem().toString().equals("cpu")) {
                    Intent intent = new Intent(AnalystActivity.this,
                            CpyTableAnalystActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                } else if (binding.spinnerTableAnalyst.getSelectedItem().toString().equals("data_center_equipment")) {
                    Intent intent = new Intent(AnalystActivity.this,
                            DataCenterEquipmentTableAnalystActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                }else if (binding.spinnerTableAnalyst.getSelectedItem().toString().equals("data_center_service")) {
                    Intent intent = new Intent(AnalystActivity.this,
                            DataCenterServiceTableAnalystActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                }else if (binding.spinnerTableAnalyst.getSelectedItem().toString().equals("drive")) {
                    Intent intent = new Intent(AnalystActivity.this,
                            DriveTableAnalystActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                }else if (binding.spinnerTableAnalyst.getSelectedItem().toString().equals("employee")) {
                    Intent intent = new Intent(AnalystActivity.this,
                            EmployeeTableAnalystActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                }else if (binding.spinnerTableAnalyst.getSelectedItem().toString().equals("rack")) {
                    Intent intent = new Intent(AnalystActivity.this,
                            RackTableAnalystActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                }else if (binding.spinnerTableAnalyst.getSelectedItem().toString().equals("ram")) {
                    Intent intent = new Intent(AnalystActivity.this,
                            RamTableAnalystActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                }else if (binding.spinnerTableAnalyst.getSelectedItem().toString().equals("room")) {
                    Intent intent = new Intent(AnalystActivity.this,
                            RoomTableAnalystActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                }else if (binding.spinnerTableAnalyst.getSelectedItem().toString().equals("section_of_rack")) {
                    Intent intent = new Intent(AnalystActivity.this,
                            SectionOfRackTableAnalystActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                }else if (binding.spinnerTableAnalyst.getSelectedItem().toString().equals("server")) {
                    Intent intent = new Intent(AnalystActivity.this,
                            ServerTableAnalystActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    startActivity(intent);
                }else if (binding.spinnerTableAnalyst.getSelectedItem().toString().equals("service")) {
                    Intent intent = new Intent(AnalystActivity.this,
                            ServiceTableAnalystActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                    startActivity(intent);
                }else if (binding.spinnerTableAnalyst.getSelectedItem().toString().equals("status")) {
                    Intent intent = new Intent(AnalystActivity.this,
                            StatusTableAnalystActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                }else if (binding.spinnerTableAnalyst.getSelectedItem().toString().equals("workers")) {
                    Intent intent = new Intent(AnalystActivity.this,
                            WorkersTableAnalystActivity.class);
                    intent.putExtra("login", loginGet);
                    intent.putExtra("password", passwordGet);
                }

            }
        });
    }
}