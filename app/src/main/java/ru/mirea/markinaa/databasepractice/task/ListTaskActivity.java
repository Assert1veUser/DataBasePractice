package ru.mirea.markinaa.databasepractice.task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ru.mirea.markinaa.databasepractice.databinding.ActivityListTaskBinding;
import ru.mirea.markinaa.databasepractice.databinding.ActivityTaskBinding;

public class ListTaskActivity extends AppCompatActivity {

    private ActivityListTaskBinding binding;
    private List task = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListTaskBinding.inflate(getLayoutInflater());
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
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM task;");
                    while (resultSet.next()) {
                        task.add(resultSet.getString("task"));
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
            int BOOKSHELF_ROWS = task.size();

            TableLayout tableLayout = (TableLayout) findViewById(binding.tableLayout.getId());


            for (int i = 0; i < BOOKSHELF_ROWS; i++) {
                TableRow tableRow = new TableRow(this);
                tableRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT));

                TextView task_textView = new TextView(this);
                TextView count_textView = new TextView(this);

                task_textView.setText(task.get(i).toString());
                count_textView.setText(Integer.toString(i + 1) + ".   ");

                task_textView.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        3f
                ));
                count_textView.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT
                ));
                task_textView.setTextColor(Color.WHITE);;
                task_textView.setTextSize(18);
                task_textView.setTypeface(null, Typeface.BOLD);

                count_textView.setTextColor(Color.WHITE);;
                count_textView.setTextSize(18);
                count_textView.setTypeface(null, Typeface.BOLD);
                count_textView.setGravity(Gravity.CENTER_HORIZONTAL);

                tableRow.addView((View) count_textView, 0);
                tableRow.addView((View) task_textView, 1);

                tableLayout.addView(tableRow, i);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}