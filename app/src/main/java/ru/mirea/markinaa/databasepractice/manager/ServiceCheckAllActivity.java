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

import ru.mirea.markinaa.databasepractice.databinding.ActivityServiceCheckAllBinding;

public class ServiceCheckAllActivity extends AppCompatActivity {

    private ActivityServiceCheckAllBinding binding;
    private List idCheck = new ArrayList();
    private List idService = new ArrayList();
    private List idRoom= new ArrayList();
    private List amountOfDays = new ArrayList();
    private List price = new ArrayList();
    private List numberOfService = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceCheckAllBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        String loginGet = intentGet.getStringExtra("login");
        String passwordGet = intentGet.getStringExtra("password");

        TableRow tableRow = new TableRow(this);
        TableRow tableRow_all = new TableRow(this);

        TextView idServiceTextViewName = new TextView(this);
        TextView idRoomTextViewName  = new TextView(this);
        TextView idCheckTextViewName  = new TextView(this);
        TextView amountOfDaysTextViewName = new TextView(this);
        TextView priceTextViewName  = new TextView(this);
        TextView numberOfServiceTextViewName = new TextView(this);

        TextView idServiceTextView = new TextView(this);
        TextView idRoomTextView = new TextView(this);
        TextView idCheckTextView = new TextView(this);
        TextView amountOfDaysTextView= new TextView(this);
        TextView priceTextView = new TextView(this);
        TextView numberOfServiceTextView  = new TextView(this);

        binding.butSearchIdClientService.setOnClickListener(new View.OnClickListener() {
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
                    /*ResultSet resultSet = statement.executeQuery("SELECT * FROM check_of_client WHERE id_client = " +

                            binding.editTextIDClient.getText().toString() +
                            ";");
                    while (resultSet.next()) {
                        idCheck.add(resultSet.getString("id"));
                    }
                    resultSet.close();*/
                            for (int i = 0; i <= idCheck.size(); i++){
                                ResultSet resultSet1 = statement.executeQuery("SELECT * FROM check_servicr WHERE id_check =" +
                                        binding.butSearchIdClientService.getText().toString() +
                                        ";");
                                while (resultSet1.next()) {
                                    idService.add(resultSet1.getString("id_service"));
                                    idRoom.add(resultSet1.getString("id_room"));
                                    amountOfDays.add(resultSet1.getString("amount_of_days"));
                                    price.add(resultSet1.getString("price"));
                                    numberOfService.add(resultSet1.getString("number_of_service"));
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

                    TableLayout tableLayout = (TableLayout) findViewById(binding.tableLayoutsService.getId());

                    tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));

                    idServiceTextViewName.setText("id_service");
                    idRoomTextViewName.setText("id_room");
                    idCheckTextViewName.setText("id_check");
                    amountOfDaysTextViewName.setText("amount_of_days");
                    priceTextViewName.setText("price");
                    numberOfServiceTextViewName.setText("number_of_service");

                    idServiceTextViewName.setLayoutParams(new TableRow.LayoutParams(
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

                    idServiceTextViewName.setTextColor(Color.WHITE);;
                    idServiceTextViewName.setTextSize(18);
                    idServiceTextViewName.setTypeface(null, Typeface.BOLD);
                    idServiceTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

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

                    tableRow.addView((View) idServiceTextViewName, 0);
                    tableRow.addView((View) idRoomTextViewName, 1);
                    tableRow.addView((View) idCheckTextViewName, 2);
                    tableRow.addView((View) amountOfDaysTextViewName, 3);
                    tableRow.addView((View) priceTextViewName, 4);
                    tableRow.addView((View) numberOfServiceTextViewName, 5);

                    tableLayout.addView(tableRow, 0);

                    for (int i = 0; i < BOOKSHELF_ROWS; i++) {

                        tableRow_all.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));



                        idServiceTextView.setText(idService.get(i).toString());
                        idRoomTextView.setText(idRoom.get(i).toString());
                        idCheckTextView.setText(idCheck.get(i).toString());
                        amountOfDaysTextView.setText(amountOfDays.get(i).toString());
                        priceTextView.setText(price.get(i).toString());
                        numberOfServiceTextView.setText(numberOfService.get(i).toString());

                        idServiceTextView.setLayoutParams(new TableRow.LayoutParams(
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

                        idServiceTextView.setTextColor(Color.WHITE);;
                        idServiceTextView.setTextSize(18);
                        idServiceTextView.setTypeface(null, Typeface.BOLD);
                        idServiceTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

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

                        tableRow_all.addView((View) idServiceTextView, 0);
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
        });
    }
}