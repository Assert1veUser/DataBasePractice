package ru.mirea.markinaa.databasepractice.analyst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import ru.mirea.markinaa.databasepractice.R;
import ru.mirea.markinaa.databasepractice.databinding.ActivityAnalystBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityStartBinding;

public class AnalystActivity extends AppCompatActivity {

    private ActivityAnalystBinding binding;
    private String tableTrue;

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
        binding.butCheckTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AnalystActivity.this,
                        TableAnalystActivity.class);
                intent.putExtra("tableName", binding.spinnerTableAnalyst.getSelectedItem()
                        .toString());
                intent.putExtra("login", loginGet);
                intent.putExtra("password", passwordGet);
            }
        });
    }
}