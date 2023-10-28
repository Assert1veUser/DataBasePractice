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

import ru.mirea.markinaa.databasepractice.databinding.ActivityTableAnalystBinding;

public class CheckOfClientTableAnalystActivity extends AppCompatActivity {

    private ActivityTableAnalystBinding binding;
    private String loginGet;
    private String passwordGet;
    private List id = new ArrayList();
    private List price = new ArrayList();
    private List dataOfPurchase = new ArrayList();
    private List idClient = new ArrayList();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTableAnalystBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intentGet = getIntent();
        loginGet = intentGet.getStringExtra("login");
        passwordGet = intentGet.getStringExtra("password");

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
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM check_of_client;");
                    while (resultSet.next()) {
                        id.add(resultSet.getString("id"));
                        price.add(resultSet.getString("price"));
                        dataOfPurchase.add(resultSet.getString("data_of_purchase"));
                        idClient.add(resultSet.getString("id_client"));
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

            System.out.println(BOOKSHELF_ROWS);

            TableLayout tableLayout = (TableLayout) findViewById(binding.tableLayoutAnalystTable.getId());
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView idNameTextView  = new TextView(this);
            TextView priceNameTextView   = new TextView(this);
            TextView dataOfPurchaseNameTextView   = new TextView(this);
            TextView idClientNameTextView  = new TextView(this);

            idNameTextView.setText("id");
            priceNameTextView.setText("price");
            dataOfPurchaseNameTextView.setText("data_of_purchase");
            idClientNameTextView.setText("id_client");

            idNameTextView.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f

            ));
            priceNameTextView.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            dataOfPurchaseNameTextView.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            idClientNameTextView.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));

            idNameTextView.setTextColor(Color.WHITE);;
            idNameTextView.setTextSize(18);
            idNameTextView.setTypeface(null, Typeface.BOLD);
            idNameTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            priceNameTextView.setTextColor(Color.WHITE);;
            priceNameTextView.setTextSize(18);
            priceNameTextView.setTypeface(null, Typeface.BOLD);
            priceNameTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            dataOfPurchaseNameTextView.setTextColor(Color.WHITE);;
            dataOfPurchaseNameTextView.setTextSize(18);
            dataOfPurchaseNameTextView.setTypeface(null, Typeface.BOLD);
            dataOfPurchaseNameTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            idClientNameTextView.setTextColor(Color.WHITE);;
            idClientNameTextView.setTextSize(18);
            idClientNameTextView.setTypeface(null, Typeface.BOLD);
            idClientNameTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            tableRow.addView((View) idNameTextView, 0);
            tableRow.addView((View) priceNameTextView, 1);
            tableRow.addView((View) dataOfPurchaseNameTextView, 2);
            tableRow.addView((View) idClientNameTextView, 3);

            tableLayout.addView(tableRow, 0);

            for (int i = 0; i < BOOKSHELF_ROWS; i++) {
                TableRow tableRow_all = new TableRow(this);
                tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

                TextView idTextView  = new TextView(this);
                TextView priceTextView   = new TextView(this);
                TextView dataOfPurchaseTextView   = new TextView(this);
                TextView idClientTextView = new TextView(this);

                System.out.println(id.get(0).toString());

                idTextView.setText(id.get(i).toString());
                priceTextView.setText(price.get(i).toString());
                dataOfPurchaseTextView.setText(dataOfPurchase.get(i).toString());
                idClientTextView.setText(idClient.get(i).toString());

                idTextView.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f

                ));
                priceTextView.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                dataOfPurchaseTextView.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                idClientTextView.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));

                idTextView.setTextColor(Color.WHITE);;
                idTextView.setTextSize(18);
                idTextView.setTypeface(null, Typeface.BOLD);
                idTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                priceTextView.setTextColor(Color.WHITE);;
                priceTextView.setTextSize(18);
                priceTextView.setTypeface(null, Typeface.BOLD);
                priceTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                dataOfPurchaseTextView.setTextColor(Color.WHITE);;
                dataOfPurchaseTextView.setTextSize(18);
                dataOfPurchaseTextView.setTypeface(null, Typeface.BOLD);
                dataOfPurchaseTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                idClientTextView.setTextColor(Color.WHITE);;
                idClientTextView.setTextSize(18);
                idClientTextView.setTypeface(null, Typeface.BOLD);
                idClientTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                tableRow.addView((View) idTextView, 0);
                tableRow.addView((View) priceTextView, 1);
                tableRow.addView((View) dataOfPurchaseTextView, 2);
                tableRow.addView((View) idClientTextView, 3);

                tableLayout.addView(tableRow, i + 1);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}