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

        TableRow tableRow = new TableRow(this);
        TableRow tableRow_all = new TableRow(this);

        TextView idServerTextViewName  = new TextView(this);
        TextView idRoomTextViewName  = new TextView(this);
        TextView idCheckTextViewName  = new TextView(this);
        TextView amountOfDaysTextViewName = new TextView(this);
        TextView priceTextViewName  = new TextView(this);
        TextView numberOfServersTextViewName = new TextView(this);

        TextView idServerTextView = new TextView(this);
        TextView idRoomTextView = new TextView(this);
        TextView idCheckTextView = new TextView(this);
        TextView amountOfDaysTextView= new TextView(this);
        TextView priceTextView = new TextView(this);
        TextView numberOfServersTextView = new TextView(this);

        binding.button.setOnClickListener(new View.OnClickListener() {
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
                            ResultSet resultSet1 = statement.executeQuery("SELECT * FROM check_server WHERE id_check = " +
                                    binding.editTextIDClient.getText().toString() +
                                    ";");
                            while (resultSet1.next()) {
                                idServer.add(resultSet1.getString("id_server"));
                                idRoom.add(resultSet1.getString("id_room"));
                                idCheck.add(resultSet1.getString("id_check"));
                                amountOfDays.add(resultSet1.getString("amount_of_days"));
                                price.add(resultSet1.getString("price"));
                                numberOfServers.add(resultSet1.getString("number_of_servers"));
                            }
                            System.out.println(idServer.get(0));
                            resultSet1.close();
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
                    int BOOKSHELF_ROWS = idServer.size();

                    TableLayout tableLayout = (TableLayout) findViewById(binding.tableLayoutEquipmentCheck.getId());
                    tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));

                    idServerTextViewName.setText("id_server");
                    idRoomTextViewName.setText("id_room");
                    idCheckTextViewName.setText("id_check");
                    amountOfDaysTextViewName.setText("amount_of_days");
                    priceTextViewName.setText("price");
                    numberOfServersTextViewName.setText("number_of_servers");

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
                    numberOfServersTextViewName.setLayoutParams(new TableRow.LayoutParams(
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

                    numberOfServersTextViewName.setTextColor(Color.WHITE);;
                    numberOfServersTextViewName.setTextSize(18);
                    numberOfServersTextViewName.setTypeface(null, Typeface.BOLD);
                    numberOfServersTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    tableRow.addView((View) idServerTextViewName, 0);
                    tableRow.addView((View) idRoomTextViewName, 1);
                    tableRow.addView((View) idCheckTextViewName, 2);
                    tableRow.addView((View) amountOfDaysTextViewName, 3);
                    tableRow.addView((View) priceTextViewName, 4);
                    tableRow.addView((View) numberOfServersTextViewName, 5);

                    tableLayout.addView(tableRow, 0);


                    for (int i = 0; i < BOOKSHELF_ROWS; i++) {
                        tableRow_all.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));

                        idServerTextView.setText(idServer.get(i).toString());
                        idRoomTextView.setText(idRoom.get(i).toString());
                        idCheckTextView.setText(idCheck.get(i).toString());
                        amountOfDaysTextView.setText(amountOfDays.get(i).toString());
                        priceTextView.setText(price.get(i).toString());
                        numberOfServersTextView.setText(numberOfServers.get(i).toString());

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
                        numberOfServersTextView.setLayoutParams(new TableRow.LayoutParams(
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

                        numberOfServersTextView.setTextColor(Color.WHITE);;
                        numberOfServersTextView.setTextSize(18);
                        numberOfServersTextView.setTypeface(null, Typeface.BOLD);
                        numberOfServersTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                        tableRow_all.addView((View) idServerTextView, 0);
                        tableRow_all.addView((View) idRoomTextView, 1);
                        tableRow_all.addView((View) idCheckTextView, 2);
                        tableRow_all.addView((View) amountOfDaysTextView, 3);
                        tableRow_all.addView((View) priceTextView, 4);
                        tableRow_all.addView((View) numberOfServersTextView, 5);

                        tableLayout.addView(tableRow_all, i + 1);

                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}