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

import ru.mirea.markinaa.databasepractice.R;
import ru.mirea.markinaa.databasepractice.databinding.ActivityServerTableAnalystBinding;

public class ServiceTableAnalystActivity extends AppCompatActivity {

    private ActivityServerTableAnalystBinding binding;
    private List id = new ArrayList();
    private List name = new ArrayList();
    private List price = new ArrayList();

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

            idTextViewName.setText("id");
            nameTextViewName.setText("name");
            priceTextViewName.setText("price");

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

            tableRow.addView((View) idTextViewName, 0);
            tableRow.addView((View) nameTextViewName, 1);
            tableRow.addView((View) priceTextViewName, 2);

            tableLayout.addView(tableRow, 0);

            for (int i = 0; i < BOOKSHELF_ROWS; i++) {
                TableRow tableRow_all = new TableRow(this);
                tableRow_all.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

                TextView idTextView = new TextView(this);
                TextView nameTextView = new TextView(this);
                TextView priceTextView = new TextView(this);

                idTextView.setText(id.get(i).toString());
                nameTextView.setText(name.get(i).toString());
                priceTextView.setText(price.get(i).toString());

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

                tableRow_all.addView((View) idTextView, 0);
                tableRow_all.addView((View) nameTextView, 1);
                tableRow_all.addView((View) priceTextView, 2);

                tableLayout.addView(tableRow_all, i + 1);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}