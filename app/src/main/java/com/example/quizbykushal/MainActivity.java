package com.example.quizbykushal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Array to store the questions
    private String[] questions = {
            "What is the capital of France?",
            "What is the currency of Japan?",
            "What is the tallest mountain in the world?"
    };

    // Array to store the options for each question
    private String[][] options = {
            {"Paris", "London", "Berlin", "Madrid"},
            {"Yen", "Dollar", "Euro", "Pound"},
            {"Mount Everest", "K2", "Kangchenjunga", "Lhotse"}
    };

    // Array to store the images for each question
    private int[] images = {
            R.drawable.france,
            R.drawable.japan,
            R.drawable.mount_everest
    };

    // Array to store the correct answers
    private int[] answers = {0, 0, 0};

    // Variables to keep track of current question and score
    private int currentQuestion = 0;
    private int score = 0;

    // Views
    private TextView questionTextView;
    private ImageView questionImageView;
    private RadioGroup optionsRadioGroup;
    private RadioButton option1RadioButton;
    private RadioButton option2RadioButton;
    private RadioButton option3RadioButton;
    private RadioButton option4RadioButton;
    private TextView questionCounterTextView;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the views
        questionTextView = findViewById(R.id.questionTextView);
        questionImageView = findViewById(R.id.questionImageView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        option1RadioButton = findViewById(R.id.option1RadioButton);
        option2RadioButton = findViewById(R.id.option2RadioButton);
        option3RadioButton = findViewById(R.id.option3RadioButton);
        option4RadioButton = findViewById(R.id.option4RadioButton);
        questionCounterTextView = findViewById(R.id.questionCounterTextView);
        nextButton = findViewById(R.id.nextButton);

        // Set the first question
        setQuestion();

        // On click of next button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected radio button id
                int selectedId = optionsRadioGroup.getCheckedRadioButtonId();
                // If no option is selected, show a toast message
                if (selectedId == -1) {
                    Toast.makeText(MainActivity.this, "Please select an option!", Toast.LENGTH_SHORT).show();
                }else {
                // Check if the selected option is correct
                    RadioButton selectedRadioButton = findViewById(selectedId);
                    int selectedOption = optionsRadioGroup.indexOfChild(selectedRadioButton);
                    if (selectedOption == answers[currentQuestion]) {
                        score++;
                    }
                    // If all questions have been answered, show the score
                    if (currentQuestion == questions.length - 1) {
                        Toast.makeText(MainActivity.this, "Your score is: " + score + "/" + questions.length, Toast.LENGTH_SHORT).show();
                        currentQuestion = 0;
                        score = 0;
                        setQuestion();
                    } else {
                    // Move to the next question
                        currentQuestion++;
                        setQuestion();
                    }
                }
            }
        });
    }
    // Method to set the question and options
    private void setQuestion() {
        questionTextView.setText(questions[currentQuestion]);
        questionImageView.setImageResource(images[currentQuestion]);
        option1RadioButton.setText(options[currentQuestion][0]);
        option2RadioButton.setText(options[currentQuestion][1]);
        option3RadioButton.setText(options[currentQuestion][2]);
        option4RadioButton.setText(options[currentQuestion][3]);
        questionCounterTextView.setText("Question " + (currentQuestion + 1) + "/" + questions.length);
        optionsRadioGroup.clearCheck();
    }

}