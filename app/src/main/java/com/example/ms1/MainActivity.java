package com.example.ms1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TableLayout mTableLayout,initTable;
    private  int numberOfStates;
    private  int numberOfJumpes;
    FloatingActionButton mFloatingActionButton;
    ArrayList<Integer> idsList ;
    ArrayList<Integer> idsList2 ;
    SharedPreferences sharedPreferences;

    private static double[][] final_result ;

    public static double[][] getFinal_result() {
        return final_result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
         sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this /* Activity context */);
        String number_of_states = sharedPreferences.getString("number_of_states", "");
        String number_of_jumpes = sharedPreferences.getString("number_of_jumpes", "");
        numberOfStates = Integer.parseInt(number_of_states);
        numberOfJumpes = Integer.parseInt(number_of_jumpes);
        createTable(MainActivity.this);
        createInitTable(MainActivity.this);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_prefernces:
                Intent intent = new Intent(MainActivity.this,PreferncesActivity.class);
                startActivity(intent);
                break;

            case R.id.action_insert_dummy_data: break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTableLayout = findViewById(R.id.tableLayout);
        initTable = findViewById(R.id.initTable);
        mFloatingActionButton = findViewById(R.id.floatingActionButton);


        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number_of_states = sharedPreferences.getString("number_of_states", "");
                String number_of_jumpes = sharedPreferences.getString("number_of_jumpes", "");
                numberOfStates = Integer.parseInt(number_of_states);
                numberOfJumpes = Integer.parseInt(number_of_jumpes);
             double[][] result = getTableData();
             double[] init = getInitData();
             if((result!=null)&&(init!=null))
             {
                 Intent intent = new Intent(MainActivity.this,DisplayActivity.class);
                 final_result = Utils.probabilty(0,init,result, numberOfJumpes);
                 startActivity(intent);

             }

            }
        });

    }

    private void createTable(Context context) {
        idsList =new ArrayList<Integer>();
        for(int i = 0; i< numberOfStates; i++)
        {



            TableRow tableRow = new TableRow(context);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT));
            mTableLayout.addView(tableRow);
            for(int j = 0; j< numberOfStates; j++)
            {
                EditText editText = new EditText(context);
                editText.setEllipsize(TextUtils.TruncateAt.END);
                editText.setId(View.generateViewId());
                idsList.add(editText.getId());
                tableRow.addView(editText);
            }
        }


    }

    private double[][] getTableData() {

        double[][] result= new double[numberOfStates][numberOfStates];
        int k = 0;
        for(int i = 0; i< numberOfStates; i++){
            for(int j = 0; j< numberOfStates; j++)
            {

                EditText editText = findViewById(idsList.get(k));
                String input = editText.getText()+"";
                String checkResult = checkInput(input);
                if(checkResult==null)
                {
                    double value = Double.parseDouble(input);
                    result[i][j] =value;
                    k++;
                    editText.setTextColor(Color.BLACK);
                    editText.setHintTextColor(Color.RED);
                }
                else
                {
                    Toast.makeText(MainActivity.this,checkResult,Toast.LENGTH_SHORT).show();
                    editText.setTextColor(Color.RED);
                    return null;
                }

            }
        }

        return result;
    }

    private String checkInput(String input) {
        if(TextUtils.isEmpty(input))
            return "There is a empty input";
        double value = Double.parseDouble(input);
        if((value<0)||(value>1))
            return "Input must be between 0 and 1";

        return null;


    }

    private void createInitTable(Context context) {
        idsList2 =new ArrayList<Integer>();
        TableRow tableRow = new TableRow(context);
        tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT));
        initTable.addView(tableRow);
            for(int j = 0; j< numberOfStates; j++)
            {
                EditText editText = new EditText(context);
                editText.setEllipsize(TextUtils.TruncateAt.END);
                editText.setId(View.generateViewId());
                idsList2.add(editText.getId());
                tableRow.addView(editText);
            }
    }

    private double[] getInitData () {

        double[] result= new double[numberOfStates];
        int k = 0;

            for(int j = 0; j< numberOfStates; j++)
            {

                EditText editText = findViewById(idsList2.get(k));
                String input = editText.getText()+"";
                String checkResult = checkInput(input);
                if(checkResult==null)
                {
                    double value = Double.parseDouble(input);
                    result[j] =value;
                    k++;
                    editText.setTextColor(Color.BLACK);
                    editText.setHintTextColor(Color.RED);
                }
                else
                {
                    Toast.makeText(MainActivity.this,checkResult,Toast.LENGTH_SHORT).show();
                    editText.setTextColor(Color.RED);
                    return null;
                }

            }

        return result;
    }

}