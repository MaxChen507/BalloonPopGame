package com.example.balloonpopgame;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

/*
  Written by Max Chen for CS6326.001, assignment 6, starting November 16, 2019.
    NetID: mmc170330
 */
public class AddHighScoreActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    // Declare text edit variables
    private EditText mNameEditText;
    private EditText mScoreEditText;
    private EditText mDateEditText;

    // Declare text view variables
    private TextView mBalloonsMissedTextView;

    // Declare btn variables
    private Button mSaveBtn;

    // High Score variable
    private String score;

    // Balloons Missed variable
    private String balloonsMissed;

    // Method to start the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_high_score);

        // Sets our views to our variables
        mNameEditText = findViewById(R.id.nameEditText);
        mScoreEditText = findViewById(R.id.scoreEditText);
        mDateEditText = findViewById(R.id.dateEditText);
        mSaveBtn = findViewById(R.id.saveBtn);
        mBalloonsMissedTextView = findViewById(R.id.balloonsMissedTextView);

        // Sets a default date
        Long currentDate = new Date().getTime();
        Date date=new Date(currentDate);
        SimpleDateFormat df = new SimpleDateFormat("d/M/yy");
        String dateText = df.format(date);
        mDateEditText.setText(dateText);

        // Sets the high score
        score = getIntent().getStringExtra("EXTRA_SCORE");
        mScoreEditText.setText(score);

        // Sets the balloons missed
        balloonsMissed = getIntent().getStringExtra("EXTRA_MISSED_BALLOONS");
        mBalloonsMissedTextView.setText(balloonsMissed);

        // Sets a on click listener for our date time picker
        findViewById(R.id.dateTimeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Sets a listener to see when text changes
        mNameEditText.addTextChangedListener(saveTextWatcher);
        mScoreEditText.addTextChangedListener(saveTextWatcher);
        mDateEditText.addTextChangedListener(saveTextWatcher);

        // Method when save button is clicked
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new temp highscoreinfo
                HighScoreInfo highScoreInfo = new HighScoreInfo(mNameEditText.getText().toString(), mScoreEditText.getText().toString(), mDateEditText.getText().toString());

                // New intent to go to scores list
                Intent intent = new Intent(getApplicationContext(), ListHighScoreActivity.class);

                intent.putExtra("EXTRA_HIGH_SCORE_NAME", mNameEditText.getText().toString());
                intent.putExtra("EXTRA_HIGH_SCORE_SCORE", mScoreEditText.getText().toString());
                intent.putExtra("EXTRA_HIGH_SCORE_DATE", mDateEditText.getText().toString());
                startActivity(intent);
                finish();
            }
        });

    }

    // Creates dateTimePicker Dialog
    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        // Sets the date to limit only current date
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        datePickerDialog.show();
    }

    // Method that sets our date text edit when a date is picked
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = month+1 + "/" + dayOfMonth + "/" + year;
        mDateEditText.setText(date);
    }

    // Method that watches if certain text has changed
    // Validation
    private TextWatcher saveTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Get the actual value of text edit
            String nameInput = mNameEditText.getText().toString().trim();
            String scoreInput = mScoreEditText.getText().toString().trim();
            String dateInput = mDateEditText.getText().toString().trim();

            // Only enable save button if they are not empty
            mSaveBtn.setEnabled(!nameInput.isEmpty() && !scoreInput.isEmpty() && !dateInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
