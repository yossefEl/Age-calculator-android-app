package com.app.android.agecalc.calc_age;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button btn_date;
    Calendar currentDate;
    int cday, cmonth, cyear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getting the current date
        currentDate = Calendar.getInstance();
        cday = currentDate.get(Calendar.DAY_OF_MONTH);
        cmonth = currentDate.get(Calendar.MONTH) + 1;
        cyear = currentDate.get(Calendar.YEAR);
        tv = (TextView) findViewById(R.id.textv);

//        calling DatePicker to get birthday date
        btn_date = findViewById(R.id.btn_date);
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int gyear, int gmonth, int gday) {
                        gmonth += 1;
                        if (gyear != 0 && gmonth != 0 && gday != 0 && cyear > gyear) {
                            int agey = cyear - gyear;
                            int agem = cmonth - gmonth;
                            int aged = cday - gday;
                            aged = aged < 0 ? (aged * -1) : aged;
                            String resultMessage = "";
                            if (agem < 0) {
                                resultMessage = (agem * -1) + " month\nand " + aged + " days to complete\n" + agey + " years old";
                            } else {
                                resultMessage = "Your age is: " + agey + " years old\n" + agem + " month and " + aged + " days.";
                            }
                            tv.setText(resultMessage);
                        } else {
                            tv.setText("Please select a valid birthday !");
                        }
                    }
                }, cyear, cmonth-1, cday);
                datePickerDialog.show();

            }
        });
    }
}
