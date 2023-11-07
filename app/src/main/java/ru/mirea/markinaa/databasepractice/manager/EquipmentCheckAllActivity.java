package ru.mirea.markinaa.databasepractice.manager;

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

import ru.mirea.markinaa.databasepractice.databinding.ActivityEquipmentCheckAllBinding;

public class EquipmentCheckAllActivity extends AppCompatActivity {

    private ActivityEquipmentCheckAllBinding binding;
    private List idCheck = new ArrayList();
    private List idServer = new ArrayList();
    private List idRoom= new ArrayList();
    private List amountOfDays = new ArrayList();
    private List price = new ArrayList();
    private List numberOfServers = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEquipmentCheckAllBinding.inflate(getLayoutInflater());
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
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM check_of_client WHERE id_client = " +

                            binding.editTextIDClient.getText().toString() +
                            ";");
                    while (resultSet.next()) {
                        idCheck.add(resultSet.getString("id"));
                    }
                    resultSet.close();
                    for (int i = 0; i <= idCheck.size(); i++){
                        ResultSet resultSet1 = statement.executeQuery("SELECT * FROM check_server WHERE id_check =" +
                                idCheck.get(i).toString() +
                                ";");
                        while (resultSet1.next()) {
                            idServer.add(resultSet.getString("id_server"));
                            idRoom.add(resultSet.getString("id_room"));
                            amountOfDays.add(resultSet.getString("amount_of_days"));
                            price.add(resultSet.getString("price"));
                            numberOfServers.add(resultSet.getString("number_of_servers"));
                        }
                        if (i == idCheck.size() - 1){
                            resultSet1.close();
                        }
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
            int BOOKSHELF_ROWS = idCheck.size();

            TableLayout tableLayout = (TableLayout) findViewById(binding.tableLayoutEquipmentCheck.getId());
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView idServerTextViewName  = new TextView(this);
            TextView idRoomTextViewName  = new TextView(this);
            TextView idCheckTextViewName  = new TextView(this);
            TextView amountOfDaysTextViewName = new TextView(this);
            TextView priceTextViewName  = new TextView(this);
            TextView numberOfServiceTextViewName = new TextView(this);

            idServerTextViewName.setText("id_server");
            idRoomTextViewName.setText("id_room");
            idCheckTextViewName.setText("id_check");
            amountOfDaysTextViewName.setText("amount_of_days");
            priceTextViewName.setText("price");
            numberOfServiceTextViewName.setText("number_of_service");

            idServerTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f

            ));
            idRoomTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            idCheckTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            amountOfDaysTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            priceTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            numberOfServiceTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));

            idServerTextViewName.setTextColor(Color.WHITE);;
            idServerTextViewName.setTextSize(18);
            idServerTextViewName.setTypeface(null, Typeface.BOLD);
            idServerTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            idRoomTextViewName.setTextColor(Color.WHITE);;
            idRoomTextViewName.setTextSize(18);
            idRoomTextViewName.setTypeface(null, Typeface.BOLD);
            idRoomTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            idCheckTextViewName.setTextColor(Color.WHITE);;
            idCheckTextViewName.setTextSize(18);
            idCheckTextViewName.setTypeface(null, Typeface.BOLD);
            idCheckTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            amountOfDaysTextViewName.setTextColor(Color.WHITE);;
            amountOfDaysTextViewName.setTextSize(18);
            amountOfDaysTextViewName.setTypeface(null, Typeface.BOLD);
            amountOfDaysTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            priceTextViewName.setTextColor(Color.WHITE);;
            priceTextViewName.setTextSize(18);
            priceTextViewName.setTypeface(null, Typeface.BOLD);
            priceTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            numberOfServiceTextViewName.setTextColor(Color.WHITE);;
            numberOfServiceTextViewName.setTextSize(18);
            numberOfServiceTextViewName.setTypeface(null, Typeface.BOLD);
            numberOfServiceTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            tableRow.addView((View) idServerTextViewName, 0);
            tableRow.addView((View) idRoomTextViewName, 1);
            tableRow.addView((View) idCheckTextViewName, 2);
            tableRow.addView((View) amountOfDaysTextViewName, 3);
            tableRow.addView((View) priceTextViewName, 4);
            tableRow.addView((View) numberOfServiceTextViewName, 5);

            tableLayout.addView(tableRow, 0);

            for (int i = 0; i < BOOKSHELF_ROWS; i++) {
                TableRow tableRow_all = new TableRow(this);
                tableRow_all.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

                TextView idServerTextView = new TextView(this);
                TextView idRoomTextView = new TextView(this);
                TextView idCheckTextView = new TextView(this);
                TextView amountOfDaysTextView= new TextView(this);
                TextView priceTextView = new TextView(this);
                TextView numberOfServiceTextView  = new TextView(this);

                idServerTextView.setText(idServer.get(i).toString());
                idRoomTextView.setText(idRoom.get(i).toString());
                idCheckTextView.setText(idCheck.get(i).toString());
                amountOfDaysTextView.setText(amountOfDays.get(i).toString());
                priceTextView.setText(price.get(i).toString());
                numberOfServiceTextView.setText(numberOfServers.get(i).toString());

                idServerTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f

                ));
                idRoomTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                idCheckTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                amountOfDaysTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                priceTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                numberOfServiceTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));

                idServerTextView.setTextColor(Color.WHITE);;
                idServerTextView.setTextSize(18);
                idServerTextView.setTypeface(null, Typeface.BOLD);
                idServerTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                idRoomTextView.setTextColor(Color.WHITE);;
                idRoomTextView.setTextSize(18);
                idRoomTextView.setTypeface(null, Typeface.BOLD);
                idRoomTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                idCheckTextView.setTextColor(Color.WHITE);;
                idCheckTextView.setTextSize(18);
                idCheckTextView.setTypeface(null, Typeface.BOLD);
                idCheckTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                amountOfDaysTextView.setTextColor(Color.WHITE);;
                amountOfDaysTextView.setTextSize(18);
                amountOfDaysTextView.setTypeface(null, Typeface.BOLD);
                amountOfDaysTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                priceTextView.setTextColor(Color.WHITE);;
                priceTextView.setTextSize(18);
                priceTextView.setTypeface(null, Typeface.BOLD);
                priceTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                numberOfServiceTextView.setTextColor(Color.WHITE);;
                numberOfServiceTextView.setTextSize(18);
                numberOfServiceTextView.setTypeface(null, Typeface.BOLD);
                numberOfServiceTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                tableRow_all.addView((View) idServerTextView, 0);
                tableRow_all.addView((View) idRoomTextView, 1);
                tableRow_all.addView((View) idCheckTextView, 2);
                tableRow_all.addView((View) amountOfDaysTextView, 3);
                tableRow_all.addView((View) priceTextView, 4);
                tableRow_all.addView((View) numberOfServiceTextView, 5);

                tableLayout.addView(tableRow_all, i + 1);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}