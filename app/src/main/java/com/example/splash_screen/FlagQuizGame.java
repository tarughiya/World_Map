package com.example.splash_screen;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import tyrantgit.explosionfield.ExplosionField;

public class FlagQuizGame extends Activity {
    private static final String TAG = "FlagQuizGame";
    private int questionNumber = 0;
    private int numberOfCorrectAnswers = 0;
    private List<String> flagImageNameList = new ArrayList<String>();
    private List<String> quizQuestionsList = new ArrayList<String>();
    private String[] imageFilePaths;
    private String correctAnswer;
    private Handler handler;
    private final DialogInterface.OnDismissListener onDismissListener = new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialog) {
            Log.i("onDismiss()", "onDismiss()");
            resetQuiz();
            loadNextQuestion();
        }
    };
    private View.OnClickListener answerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            submitAnswer((Button) v);
            mExplosionField.explode(v);
        }
    };

    private TextView resultBox;
    private TableLayout buttonTableLayout;
    private TextView questionNumberTextView;
    private ImageView flagImageView;

    private ExplosionField mExplosionField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_quiz_game);

        mExplosionField = ExplosionField.attach2Window(this);

        if(savedInstanceState != null) {
            questionNumber = savedInstanceState.getInt("questionNumber");
        }

        handler = new Handler();
        initUIReferences();

        resetQuiz();
        loadNextQuestion();
    }

    private void initUIReferences() {
        questionNumberTextView = (TextView) findViewById(R.id.questionNumberTextView);
        flagImageView = (ImageView) findViewById(R.id.flagImageView);
        buttonTableLayout = (TableLayout) findViewById(R.id.buttonTableLayout);
        resultBox = (TextView) findViewById(R.id.resultTextView);
    }

    private void loadNextQuestion() {
        clearResultBox();
        final String nextImage = quizQuestionsList.remove(0);
        correctAnswer = deriveCountryName(nextImage);
        Log.i("CORRECT_ANSWER", correctAnswer);

        removeOldAnswerOptionButtons();
        addNewAnswerOptionButtons();
        loadFlag(nextImage);

        incrementQuestionNumberAndUpdateTitle();
    }

    private void addNewAnswerOptionButtons() {
        List<String> shuffledCountryNames = answerOptions();
        Log.i("ANSWER_OPTIONS", shuffledCountryNames.get(0) + "," + shuffledCountryNames.get(1) + ","  + shuffledCountryNames.get(2));

        TableRow row = (TableRow) buttonTableLayout.getChildAt(0);
        row.addView(newOptionButton(shuffledCountryNames.get(0)));
        row.addView(newOptionButton(shuffledCountryNames.get(1)));
        row.addView(newOptionButton(shuffledCountryNames.get(2)));
    }

    private Button newOptionButton(String text) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Button optionButton1 = (Button) inflater.inflate(R.layout.option_button, null);
        optionButton1.setText(text);
        optionButton1.setOnClickListener(answerClickListener);
        return optionButton1;
    }

    private void submitAnswer(Button submittedAnswerButton) {
        String guess = submittedAnswerButton.getText().toString();
        removeClickablilityFromAllAnswerOptionButtons();
        submittedAnswerButton.setEnabled(false);
        if (guess.equals(correctAnswer)) {
            numberOfCorrectAnswers++;
            displayResultAsCorrect();
        }else {
            displayResultAsWrong();
        }
        if(questionNumber == 10) {
            resultDialog();
            //alertDialog();
            return;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadNextQuestion();
            }
        }, 1000);
    }

    private void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FlagQuizGame.this);
        builder.setTitle("Final Score");
        builder.setMessage("Your got " + numberOfCorrectAnswers + " right out of 10.");

        AlertDialog dialog = builder.create();
        dialog.setOnDismissListener(onDismissListener);
        dialog.show();
    }
    private void resultDialog(){
        final Dialog dialog1 = new Dialog(FlagQuizGame.this);
        dialog1.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog1.setContentView(R.layout.finalresult);
        dialog1.setCancelable(true);
        dialog1.getWindow().getAttributes().gravity= Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        dialog1.getWindow().getAttributes().windowAnimations=R.style.DialogTheme;
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        TextView txt_result=(TextView)dialog1.findViewById(R.id.txt_result);
        txt_result.setText("Your got " + numberOfCorrectAnswers + " right out of 10.");
        Button button_complete= (Button) dialog1.findViewById(R.id.button_complete);
        button_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                dialog1.dismiss();
            }
        });

        dialog1.show();
    }

    private void displayResultAsWrong() {
        resultBox.setText("Wrong!");
        resultBox.setTextColor(getResources().getColor(R.color.red));
    }

    private void displayResultAsCorrect() {
        //starDialog();
        resultBox.setText("Correct!");
        resultBox.setTextColor(getResources().getColor(R.color.b_yellow));
    }
    private List<String> answerOptions() {
        List<String> shuffledCountryNames = Arrays.asList(new String[] {correctAnswer, pickIncorrectCountryName(), pickIncorrectCountryName()});
        Collections.shuffle(shuffledCountryNames);
        return shuffledCountryNames;
    }

    private void removeOldAnswerOptionButtons() {
        for (int row = 0; row < buttonTableLayout.getChildCount(); ++row)
            ((TableRow) buttonTableLayout.getChildAt(row)).removeAllViews();
    }

    private void removeClickablilityFromAllAnswerOptionButtons() {
        for (int row = 0; row < buttonTableLayout.getChildCount(); ++row)
            ((TableRow) buttonTableLayout.getChildAt(row)).setClickable(false);
    }

    private String pickIncorrectCountryName() {
        String countryName = null;
        while(true) {
            countryName = deriveCountryName(randomFlag());
            if(! correctAnswer.equalsIgnoreCase(countryName)) break;
        }
        return countryName;
    }

    private String deriveCountryName(final String nextImage) {
        return nextImage.substring(nextImage.indexOf("-")+1);
    }

    private void incrementQuestionNumberAndUpdateTitle() {
        questionNumber++;
        questionNumberTextView.setText("Question " + questionNumber + "of 10.");
    }

    private void loadFlag(String nextImage) {
        String imagePath = "R.drawable/" +nextImage + ".png";
        try {
            getAssets().open(imagePath);
            flagImageView.setImageDrawable(Drawable.createFromStream(getAssets().open(imagePath),imagePath));
        } catch (IOException e) {
            Log.e(TAG, "Error loading " + imagePath, e);
        }
    }

    private void resetQuiz() {
        Log.i("resetQuiz()", "resetQuiz()");
        numberOfCorrectAnswers = 0;
        questionNumber = 0;
        reLoadFlagImageNameList();
        reloadQuizQuestions();
    }

    private void clearResultBox() {
        resultBox.setText("");
    }

    private void reloadQuizQuestions() {
        final int MAX_QUESTIONS = 10;
        for(int i=0; i< MAX_QUESTIONS;) {
            final String flagName = randomFlag();
            if(! quizQuestionsList.contains(flagName)) {
                quizQuestionsList.add(flagName);
                i++;
            }
        }
    }

    private String randomFlag() {
        Random random = new Random();
        int flagIndex = random.nextInt(flagImageNameList.size());
        return flagImageNameList.get(flagIndex);
    }

    private void reLoadFlagImageNameList() {
        flagImageNameList.clear();

        final AssetManager assets = getAssets();
        imageFilePaths = fetchImageFilePaths(assets);
        addToFlagImageNames(imageFilePaths);
    }

    private void addToFlagImageNames(String[] imageFilePaths) {
        for (String path : imageFilePaths) {
            flagImageNameList.add(path.replace(".png", ""));
        }
    }

    private String[] fetchImageFilePaths(final AssetManager assets) {
        try {
            return assets.list("R.drawable");
        } catch (IOException e) {
            Log.e(TAG, "Error loading image file names ", e);
            return new String[0];
        }
    }

    private void starDialog(){
        final Dialog dialog1 = new Dialog(FlagQuizGame.this);
        dialog1.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog1.setContentView(R.layout.dialogstar);
        dialog1.setCancelable(true);
        dialog1.getWindow().getAttributes().gravity= Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        dialog1.getWindow().getAttributes().windowAnimations=R.style.DialogTheme;
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        //LikeButtonView likeButton= (LikeButtonView) dialog1.findViewById(R.id.likeButton);
        //LikeButtonView.getInstance().clickMethod();

        dialog1.show();

        // Hide after some seconds
        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (dialog1.isShowing()) {
                    dialog1.dismiss();
                }
            }
        };
        handler.postDelayed(runnable, 2000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
