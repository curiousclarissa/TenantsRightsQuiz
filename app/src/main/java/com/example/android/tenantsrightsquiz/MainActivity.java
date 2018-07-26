package com.example.android.tenantsrightsquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    //starting score is zero
    int score = 0;
    int radioScore;

    /**
     * this radio button sets the score to 1 if the user correctly identifies SDCI as the answer.
     * @param view
     */
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_departmentalenforcementSDCI:
                if (checked) {
                    radioScore = 1;
                }
                break;
        }
    }

    /**
     * This method is called when the submit button is clicked and reports score to the user as a toast.
     * This submit also tabulates the string comparison and adds 1 point to the users score for the text entries being correct.
     * Correct entry for question three is SDCI
     * Correct entry for question 4 is Office of Hearing Examiner, not case sensitive.
     */
    public String submit(View view) {
        checkedScore();
        EditText sdciField = findViewById(R.id.q3SDCI);
        String sdciTranslate = sdciField.getText().toString();
        EditText oheField = findViewById(R.id.q4OHE);
        String oheTranslate = oheField.getText().toString();
        if (sdciTranslate.equalsIgnoreCase(getString(R.string.answer3))) {
            score += 1;
        }
        if (oheTranslate.equalsIgnoreCase(getString(R.string.answer4))) {
            score += 1;
        }
        score = score + radioScore;
        Toast.makeText(this, "Your score is " + score, Toast.LENGTH_SHORT).show();
        return String.valueOf(score);
    }

    private int checkedScore() {
        /**
         * handle user checked boxes
         * One point is granted if the user correctly identifies all three parts of the correct answer.
         * No points are added if the user does not meet this requirement
         * No points are deducted for incorrect answers.
         */
        CheckBox checkBoxState = findViewById(R.id.checkbox_State);
        CheckBox checkBoxFederal = findViewById(R.id.checkbox_Federal);
        CheckBox checkBoxCounty = findViewById(R.id.checkbox_County);
        CheckBox checkBoxCity = findViewById(R.id.checkbox_City);
        boolean stateProtections = checkBoxState.isChecked();
        boolean federalProtections = checkBoxFederal.isChecked();
        boolean cityProtections = checkBoxCity.isChecked();
        boolean countyProtections = checkBoxCounty.isChecked();
        if (cityProtections == true && federalProtections == true && stateProtections == true && !countyProtections){
            score += 1;
        } else if (countyProtections == true) {
            score += 0;
        }
        return score;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
