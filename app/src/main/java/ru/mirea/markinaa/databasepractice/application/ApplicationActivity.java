package ru.mirea.markinaa.databasepractice.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
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
import ru.mirea.markinaa.databasepractice.databinding.ActivityAdminBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityApplicationBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityStartBinding;

public class ApplicationActivity extends AppCompatActivity {

    private ActivityApplicationBinding binding;
    private List application = new ArrayList();
    private List date = new ArrayList();
    private List idClient = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityApplicationBinding.inflate(getLayoutInflater());
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
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM application;");
                    while (resultSet.next()) {
                        application.add(resultSet.getString("application"));
                        date.add(resultSet.getDate("date"));
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
            int BOOKSHELF_ROWS = application.size();

            TableLayout tableLayout = (TableLayout) findViewById(binding.tableLayoutApplication.getId());


            for (int i = 0; i < BOOKSHELF_ROWS; i++) {
                TableRow tableRow = new TableRow(this);
                tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

                TextView count_textView = new TextView(this);
                TextView application_textView = new TextView(this);
                TextView date_textView = new TextView(this);
                TextView idClient_textView = new TextView(this);

                count_textView.setText(Integer.toString(i + 1) + ".   ");
                application_textView.setText(application.get(i).toString());
                date_textView.setText(date.get(i).toString());
                idClient_textView.setText(idClient.get(i).toString());

                count_textView.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT
                ));

                application_textView.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        3f
                ));

                date_textView.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        3f
                ));

                idClient_textView.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        3f
                ));
                count_textView.setTextColor(Color.WHITE);;
                count_textView.setTextSize(18);
                count_textView.setTypeface(null, Typeface.BOLD);
                count_textView.setGravity(Gravity.CENTER_HORIZONTAL);


                application_textView.setTextColor(Color.WHITE);;
                application_textView.setTextSize(18);
                application_textView.setTypeface(null, Typeface.BOLD);

                date_textView.setTextColor(Color.WHITE);;
                date_textView.setTextSize(18);
                date_textView.setTypeface(null, Typeface.BOLD);
                date_textView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);

                idClient_textView.setTextColor(Color.WHITE);;
                idClient_textView.setTextSize(18);
                idClient_textView.setTypeface(null, Typeface.BOLD);
                idClient_textView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);



                tableRow.addView((View) count_textView, 0);
                tableRow.addView((View) application_textView, 1);
                tableRow.addView((View) date_textView, 2);
                tableRow.addView((View) idClient_textView, 3);


                tableLayout.addView(tableRow, i);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}