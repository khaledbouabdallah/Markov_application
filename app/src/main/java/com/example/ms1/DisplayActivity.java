package com.example.ms1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class DisplayActivity extends AppCompatActivity {

    TextView mTextView ;
    double[][] result;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_display);

        mTextView = findViewById(R.id.textviewdisplay);
        result = MainActivity.getFinal_result();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String number_of_states = sharedPreferences.getString("number_of_states", "");
        String number_of_jumpes = sharedPreferences.getString("number_of_jumpes", "");
        int numberOfStates = Integer.parseInt(number_of_states);
        int numberOfJumpes = Integer.parseInt(number_of_jumpes);

        for(int i=0;i<numberOfJumpes;i++)
        {
            mTextView.append("\n Jump ="+i+": ");
            for(int j=0;j<numberOfStates;j++)
            {
                String formattedValue = String.format("%.10f", result[i][j]);
                mTextView.append(formattedValue+" , ");
            }

        }



    }


    public double round (double f,double rounded){
        double result = Math.round(f * 100);
        return result /rounded;
    }


}
