package ru.mirea.markinaa.databasepractice.analyst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ru.mirea.markinaa.databasepractice.databinding.ActivityServerTableAnalystBinding;

public class ServerTableAnalystActivity extends AppCompatActivity {

    private ActivityServerTableAnalystBinding binding;
    private List id = new ArrayList();
    private List name = new ArrayList();
    private List price = new ArrayList();
    private List idCpu = new ArrayList();
    private List idRam = new ArrayList();
    private List idDrive = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServerTableAnalystBinding.inflate(getLayoutInflater());
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
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM server;");
                    while (resultSet.next()) {
                        id.add(resultSet.getString("id"));
                        name.add(resultSet.getString("name"));
                        price.add(resultSet.getString("price"));
                        idCpu.add(resultSet.getString("id_cpu"));
                        idRam.add(resultSet.getString("id_ram"));
                        idDrive.add(resultSet.getString("id_drive"));

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
            int BOOKSHELF_ROWS = id.size();

            TableLayout tableLayout = (TableLayout) findViewById(binding.tableLayoutServerTableAnalyst.getId());
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView idTextViewName  = new TextView(this);
            TextView nameTextViewName = new TextView(this);
            TextView priceTextViewName  = new TextView(this);
            TextView idCpuTextViewName = new TextView(this);
            TextView idRamTextViewName  = new TextView(this);
            TextView idDriveTextViewName  = new TextView(this);

            idTextViewName.setText("id");
            nameTextViewName.setText("name");
            priceTextViewName.setText("price");
            idCpuTextViewName.setText("id_cpu");
            idRamTextViewName.setText("id_ram");
            idDriveTextViewName.setText("id_drive");

            idTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f

            ));
            nameTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            priceTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            idCpuTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            idRamTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            idDriveTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));

            idTextViewName.setTextColor(Color.WHITE);;
            idTextViewName.setTextSize(18);
            idTextViewName.setTypeface(null, Typeface.BOLD);
            idTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            nameTextViewName.setTextColor(Color.WHITE);;
            nameTextViewName.setTextSize(18);
            nameTextViewName.setTypeface(null, Typeface.BOLD);
            nameTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            priceTextViewName.setTextColor(Color.WHITE);;
            priceTextViewName.setTextSize(18);
            priceTextViewName.setTypeface(null, Typeface.BOLD);
            priceTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            idCpuTextViewName.setTextColor(Color.WHITE);;
            idCpuTextViewName.setTextSize(18);
            idCpuTextViewName.setTypeface(null, Typeface.BOLD);
            idCpuTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            idRamTextViewName.setTextColor(Color.WHITE);;
            idRamTextViewName.setTextSize(18);
            idRamTextViewName.setTypeface(null, Typeface.BOLD);
            idRamTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            idDriveTextViewName.setTextColor(Color.WHITE);;
            idDriveTextViewName.setTextSize(18);
            idDriveTextViewName.setTypeface(null, Typeface.BOLD);
            idDriveTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            tableRow.addView((View) idTextViewName, 0);
            tableRow.addView((View) nameTextViewName, 1);
            tableRow.addView((View) priceTextViewName, 2);
            tableRow.addView((View) idCpuTextViewName, 3);
            tableRow.addView((View) idRamTextViewName, 4);
            tableRow.addView((View) idDriveTextViewName, 5);

            tableLayout.addView(tableRow, 0);

            for (int i = 0; i < BOOKSHELF_ROWS; i++) {
                TableRow tableRow_all = new TableRow(this);
                tableRow_all.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

                TextView idTextView = new TextView(this);
                TextView nameTextView = new TextView(this);
                TextView priceTextView = new TextView(this);
                TextView idCpuTextView= new TextView(this);
                TextView idRamTextView = new TextView(this);
                TextView idDriveTextView  = new TextView(this);

                idTextView.setText(id.get(i).toString());
                nameTextView.setText(name.get(i).toString());
                priceTextView.setText(price.get(i).toString());
                idCpuTextView.setText(idCpu.get(i).toString());
                idRamTextView.setText(idRam.get(i).toString());
                idDriveTextView.setText(idDrive.get(i).toString());

                idTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f

                ));
                nameTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                priceTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                idCpuTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                idRamTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                idDriveTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));

                idTextView.setTextColor(Color.WHITE);;
                idTextView.setTextSize(18);
                idTextView.setTypeface(null, Typeface.BOLD);
                idTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                nameTextView.setTextColor(Color.WHITE);;
                nameTextView.setTextSize(18);
                nameTextView.setTypeface(null, Typeface.BOLD);
                nameTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                priceTextView.setTextColor(Color.WHITE);;
                priceTextView.setTextSize(18);
                priceTextView.setTypeface(null, Typeface.BOLD);
                priceTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                idCpuTextView.setTextColor(Color.WHITE);;
                idCpuTextView.setTextSize(18);
                idCpuTextView.setTypeface(null, Typeface.BOLD);
                idCpuTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                idRamTextView.setTextColor(Color.WHITE);;
                idRamTextView.setTextSize(18);
                idRamTextView.setTypeface(null, Typeface.BOLD);
                idRamTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                idDriveTextView.setTextColor(Color.WHITE);;
                idDriveTextView.setTextSize(18);
                idDriveTextView.setTypeface(null, Typeface.BOLD);
                idDriveTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                tableRow_all.addView((View) idTextView, 0);
                tableRow_all.addView((View) nameTextView, 1);
                tableRow_all.addView((View) priceTextView, 2);
                tableRow_all.addView((View) idCpuTextView, 3);
                tableRow_all.addView((View) idRamTextView, 4);
                tableRow_all.addView((View) idDriveTextView, 5);

                tableLayout.addView(tableRow_all, i + 1);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}