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
import ru.mirea.markinaa.databasepractice.databinding.ActivityApplicationBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityCheckServiceTableAnalystBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityClientTableAnalystBinding;

public class ClientTableAnalystActivity extends AppCompatActivity {

    private ActivityClientTableAnalystBinding binding;
    private List id = new ArrayList();
    private List fullName = new ArrayList();
    private List email = new ArrayList();
    private List phoneNumber = new ArrayList();
    private List status = new ArrayList();
    private List bonus = new ArrayList();
    private List money = new ArrayList();
    private List idManager = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClientTableAnalystBinding.inflate(getLayoutInflater());
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
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM client;");
                    while (resultSet.next()) {
                        id.add(resultSet.getString("id"));
                        fullName.add(resultSet.getString("full_name"));
                        email.add(resultSet.getString("email"));
                        phoneNumber.add(resultSet.getString("phone_number"));
                        status.add(resultSet.getString("status"));
                        bonus.add(resultSet.getString("bonus"));
                        money.add(resultSet.getString("money"));
                        idManager.add(resultSet.getString("id_manager"));
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


            TableLayout tableLayout = (TableLayout) findViewById(binding.tableLayoutClientTable.getId());
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView idTextViewName  = new TextView(this);
            TextView fullNameTextViewName  = new TextView(this);
            TextView emailTextViewName  = new TextView(this);
            TextView phoneNumberNameTextViewName = new TextView(this);
            TextView statusTextViewName  = new TextView(this);
            TextView bonusTextViewName  = new TextView(this);
            TextView moneyTextViewName  = new TextView(this);
            TextView idManagerTextViewName  = new TextView(this);



            idTextViewName.setText("id");
            fullNameTextViewName.setText("full_name");
            emailTextViewName.setText("email");
            phoneNumberNameTextViewName.setText("phone_number");
            statusTextViewName.setText("status");
            bonusTextViewName.setText("bonus");
            moneyTextViewName.setText("money");
            idManagerTextViewName.setText("id_manager");

            idTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f

            ));
            fullNameTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            emailTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            phoneNumberNameTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            statusTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            bonusTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            moneyTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));
            idManagerTextViewName.setLayoutParams(new TableRow.LayoutParams(
                    300,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1f
            ));

            idTextViewName.setTextColor(Color.WHITE);;
            idTextViewName.setTextSize(18);
            idTextViewName.setTypeface(null, Typeface.BOLD);
            idTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            fullNameTextViewName.setTextColor(Color.WHITE);;
            fullNameTextViewName.setTextSize(18);
            fullNameTextViewName.setTypeface(null, Typeface.BOLD);
            fullNameTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            emailTextViewName.setTextColor(Color.WHITE);;
            emailTextViewName.setTextSize(18);
            emailTextViewName.setTypeface(null, Typeface.BOLD);
            emailTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            phoneNumberNameTextViewName.setTextColor(Color.WHITE);;
            phoneNumberNameTextViewName.setTextSize(18);
            phoneNumberNameTextViewName.setTypeface(null, Typeface.BOLD);
            phoneNumberNameTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            statusTextViewName.setTextColor(Color.WHITE);;
            statusTextViewName.setTextSize(18);
            statusTextViewName.setTypeface(null, Typeface.BOLD);
            statusTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            bonusTextViewName.setTextColor(Color.WHITE);;
            bonusTextViewName.setTextSize(18);
            bonusTextViewName.setTypeface(null, Typeface.BOLD);
            bonusTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            moneyTextViewName.setTextColor(Color.WHITE);;
            moneyTextViewName.setTextSize(18);
            moneyTextViewName.setTypeface(null, Typeface.BOLD);
            moneyTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            idManagerTextViewName.setTextColor(Color.WHITE);;
            idManagerTextViewName.setTextSize(18);
            idManagerTextViewName.setTypeface(null, Typeface.BOLD);
            idManagerTextViewName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            tableRow.addView((View) idTextViewName, 0);
            tableRow.addView((View) fullNameTextViewName, 1);
            tableRow.addView((View) emailTextViewName, 2);
            tableRow.addView((View) phoneNumberNameTextViewName, 3);
            tableRow.addView((View) statusTextViewName, 4);
            tableRow.addView((View) bonusTextViewName, 5);
            tableRow.addView((View) moneyTextViewName, 6);
            tableRow.addView((View) idManagerTextViewName, 7);

            tableLayout.addView(tableRow, 0);

            for (int i = 0; i < BOOKSHELF_ROWS; i++) {
                TableRow tableRow_all = new TableRow(this);
                tableRow_all.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

                TextView idTextView = new TextView(this);
                TextView fullNameTextView = new TextView(this);
                TextView emailTextView = new TextView(this);
                TextView phoneNumberNameTextView= new TextView(this);
                TextView statusTextView = new TextView(this);
                TextView bonusTextView  = new TextView(this);
                TextView moneyTextView = new TextView(this);
                TextView idManagerTextView  = new TextView(this);

                idTextView.setText(id.get(i).toString());
                fullNameTextView.setText(fullName.get(i).toString());
                emailTextView.setText(email.get(i).toString());
                phoneNumberNameTextView.setText(phoneNumber.get(i).toString());
                statusTextView.setText(status.get(i).toString());
                bonusTextView.setText(bonus.get(i).toString());
                moneyTextView.setText(money.get(i).toString());
                /*idManagerTextView.setText(idManager.get(i));*/

                idTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f

                ));
                fullNameTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                emailTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                phoneNumberNameTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                statusTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                bonusTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                moneyTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));
                idManagerTextView.setLayoutParams(new TableRow.LayoutParams(
                        300,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                ));



                idTextView.setTextColor(Color.WHITE);;
                idTextView.setTextSize(18);
                idTextView.setTypeface(null, Typeface.BOLD);
                idTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                fullNameTextView.setTextColor(Color.WHITE);;
                fullNameTextView.setTextSize(18);
                fullNameTextView.setTypeface(null, Typeface.BOLD);
                fullNameTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                emailTextView.setTextColor(Color.WHITE);;
                emailTextView.setTextSize(18);
                emailTextView.setTypeface(null, Typeface.BOLD);
                emailTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                phoneNumberNameTextView.setTextColor(Color.WHITE);;
                phoneNumberNameTextView.setTextSize(18);
                phoneNumberNameTextView.setTypeface(null, Typeface.BOLD);
                phoneNumberNameTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                statusTextView.setTextColor(Color.WHITE);;
                statusTextView.setTextSize(18);
                statusTextView.setTypeface(null, Typeface.BOLD);
                statusTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                bonusTextView.setTextColor(Color.WHITE);;
                bonusTextView.setTextSize(18);
                bonusTextView.setTypeface(null, Typeface.BOLD);
                bonusTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                moneyTextView.setTextColor(Color.WHITE);;
                moneyTextView.setTextSize(18);
                moneyTextView.setTypeface(null, Typeface.BOLD);
                moneyTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                idManagerTextView.setTextColor(Color.WHITE);;
                idManagerTextView.setTextSize(18);
                idManagerTextView.setTypeface(null, Typeface.BOLD);
                idManagerTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                tableRow_all.addView((View) idTextView, 0);
                tableRow_all.addView((View) fullNameTextView, 1);
                tableRow_all.addView((View) emailTextView, 2);
                tableRow_all.addView((View) phoneNumberNameTextView, 3);
                tableRow_all.addView((View) statusTextView, 4);
                tableRow_all.addView((View) bonusTextView, 5);
                tableRow_all.addView((View) moneyTextView, 6);
                tableRow_all.addView((View) idManagerTextView, 7);

                tableLayout.addView(tableRow_all, i + 1);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}