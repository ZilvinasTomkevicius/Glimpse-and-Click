package lt.ziltom.zilvinastomkevicius.projektas.Activities;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import lt.ziltom.zilvinastomkevicius.projektas.R;

import lt.ziltom.zilvinastomkevicius.projektas.DynamicArray;
import lt.ziltom.zilvinastomkevicius.projektas.Game;
import lt.ziltom.zilvinastomkevicius.projektas.Level;
import lt.ziltom.zilvinastomkevicius.projektas.SavedGameHistory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    public static int BUTTON_AMOUNT;
    public static final int BUTTON_1 = 1;
    public static final int BUTTON_2 = 2;
    public static final int BUTTON_3 = 3;
    public static final int BUTTON_4 = 4;
    public static final int BUTTON_5 = 5;
    public static final int BUTTON_6 = 6;

    //params
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;

    private Button exitButton;
    private Button startOverButton;

    private TextView viewCountDown;
    private TextView viewClickRow;
    private TextView viewPlayerClicks;

    //additional params
    private CountDownTimer timer;

    private int[] buttonClickRow;
    private int rightAnswerCount = 0;
    private int buttonClicksLeft;

    private ArrayList<Integer> playerClicks = new ArrayList<>();

    private int buttonToClick;
    private int buttonToClickPosition = 0;

    private Game game = new Game();
    private Level level;

    private boolean isLevelCompleted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if(SavedGameHistory.gameHistory == null) {
            SavedGameHistory.gameHistory = new DynamicArray<>();
        }

        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);

        exitButton = findViewById(R.id.game_exit);
        startOverButton = findViewById(R.id.button_startOver);

        viewCountDown = findViewById(R.id.textView_countDown);
        viewClickRow = findViewById(R.id.clickRow_textView);
        viewPlayerClicks = findViewById(R.id.playerClicks_textView);

        viewPlayerClicks.setVisibility(View.INVISIBLE);

        startOverButton.setVisibility(View.INVISIBLE);

        BUTTON_AMOUNT = 6;
        buttonClicksLeft = BUTTON_AMOUNT;
        buttonClickRow = fillRow();
        buttonToClick  = buttonClickRow[0];
        displayRow();

        level = new Level();
        level.setLevelCount(0);
        level.setSeconds(4000);
        game.setPoints(0);

        setButtonsInvisible();
        startTimer(level.getSeconds(), 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
      //  setHistory();
    }

    //============================= COUNTDOWN TIMER ===============================

    private void startTimer(long milisInFuture, final long countDownInterval) {

        viewCountDown.setVisibility(View.VISIBLE);
        timer = new CountDownTimer(milisInFuture, countDownInterval) {

            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished / countDownInterval == 0) {
                    viewCountDown.setVisibility(View.INVISIBLE);
                    viewPlayerClicks.setVisibility(View.VISIBLE);
                    viewPlayerClicks.setText(Integer.toString(BUTTON_AMOUNT));
                    viewClickRow.setText("Clicks left");
                    timer.cancel();
                    setButtonsVisible();
                }
                viewCountDown.setText(Long.toString(millisUntilFinished / countDownInterval));
            }

            @Override
            public void onFinish() {
                viewCountDown.setVisibility(View.INVISIBLE);
            }
        };

        timer.start();
    }


    //================================= BUTTON VISIBILITY MANAGEMENT ======================

    public void setButtonsInvisible() {
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        button4.setVisibility(View.INVISIBLE);
        button5.setVisibility(View.INVISIBLE);
        button6.setVisibility(View.INVISIBLE);
    }

    public void setButtonsVisible() {
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        button6.setVisibility(View.VISIBLE);
    }

    //========================= METHODS FOR BUTTON CLICKS =========================

    public void button1Click(View v) {
        if(buttonClicksLeft != 0) {
            if(buttonToClick == BUTTON_1) {
                rightAnswerCount++;
                rightAnswer();
            }
            playerClicks.add(BUTTON_1);
            updateParams();
        }
    }

    public void button2Click(View v) {
        if(buttonClicksLeft != 0) {
            if(buttonToClick == BUTTON_2) {
                rightAnswerCount++;
                rightAnswer();
            }
            playerClicks.add(BUTTON_2);
            updateParams();
        }
    }

    public void button3Click(View v) {
        if(buttonClicksLeft != 0) {
            if(buttonToClick == BUTTON_3) {
                rightAnswerCount++;
                rightAnswer();
            }
            playerClicks.add(BUTTON_3);
            updateParams();
        }
    }

    public void button4Click(View v) {
        if(buttonClicksLeft != 0) {
            if(buttonToClick == BUTTON_4) {
                rightAnswerCount++;
                rightAnswer();
            }
            playerClicks.add(BUTTON_4);
            updateParams();
        }
    }

    public void button5Click(View v) {
        if(buttonClicksLeft != 0) {
            if(buttonToClick == BUTTON_5) {
                rightAnswerCount++;
                rightAnswer();
            }
            playerClicks.add(BUTTON_5);
            updateParams();
        }
    }

    public void button6Click(View v) {
        if(buttonClicksLeft != 0) {
            if(buttonToClick == BUTTON_6) {
                rightAnswerCount++;
                rightAnswer();
            }
            playerClicks.add(BUTTON_6);
            updateParams();
        }
    }

    //================================ METHODS FOR GAME MANAGEMENT ==========================

    //Updates row if the right button was pressed
    public void rightAnswer() {
        buttonToClickPosition++;
        if(buttonClicksLeft > 1) {
            buttonToClick = buttonClickRow[buttonToClickPosition];
        }
    }

    //Updates parameters after every click
    public void updateParams() {
        buttonClicksLeft--;
        viewPlayerClicks.setText(Integer.toString(buttonClicksLeft));
        if(buttonClicksLeft == 0) {
            onLevelFinished();
        }
    }

    //determines when game is completed or not
    public void onLevelFinished() {
        if(rightAnswerCount == BUTTON_AMOUNT) {
            viewCountDown.setVisibility(View.VISIBLE);
            viewCountDown.setText("Completed!");
            displayPlayerClicks();
            displayRow();

            setButtonsInvisible();
            startOverButton.setVisibility(View.VISIBLE);
            startOverButton.setText("Next level");

            level.setLevelCount(level.getLevelCount() + 1);
            game.setPoints(game.getPoints() + 1);
            isLevelCompleted = true;

        } else {

            isLevelCompleted = false;
            setButtonsInvisible();
            viewCountDown.setVisibility(View.VISIBLE);
            viewCountDown.setText("Failed :(");
            displayPlayerClicks();
            displayRow();

            setButtonsInvisible();
            startOverButton.setVisibility(View.VISIBLE);
            startOverButton.setText("Start over");
        }
    }

    //========================== START OVER AND EXIT BUTTONS ============================

    public void setStartOver_NextLevelButtonClick(View v) {
        if(isLevelCompleted) {
            if(level.getLevelCount() == 3) {
                BUTTON_AMOUNT = BUTTON_AMOUNT + 1;
                level.setSeconds(level.getSeconds() + 1000);
                level.setLevelCount(0);
            }

            setParamsToNextLevel();

        } else {
            setParamsToBeginning();
        }
    }

    public void setParamsToNextLevel() {
        buttonClickRow = fillRow();
        buttonToClick  = buttonClickRow[0];
        displayRow();

        buttonToClickPosition = 0;
        rightAnswerCount = 0;
        buttonClicksLeft = BUTTON_AMOUNT;

        setButtonsInvisible();
        startTimer(level.getSeconds(), 1000);

        startOverButton.setVisibility(View.INVISIBLE);
        viewPlayerClicks.setVisibility(View.INVISIBLE);
    }

    public void setParamsToBeginning() {
        game.setDate(new Date());
        int gameNameNumber = SavedGameHistory.gameHistory.count() + 1;
        game.setName("Game " + gameNameNumber);
        SavedGameHistory.gameHistory.add(game);
        game = new Game();

        game.setPoints(0);

        buttonToClickPosition = 0;
        rightAnswerCount = 0;
        BUTTON_AMOUNT = 6;
        buttonClicksLeft = BUTTON_AMOUNT;

        buttonClickRow = fillRow();
        buttonToClick  = buttonClickRow[0];
        displayRow();

        setButtonsInvisible();
        level.setSeconds(4000);
        level.setLevelCount(0);
        startTimer(level.getSeconds(), 1000);

        startOverButton.setVisibility(View.INVISIBLE);
        viewPlayerClicks.setVisibility(View.INVISIBLE);
    }

    public void setExitButtonClick(View v) {
        game.setDate(new Date());
        int gameNameNumber = SavedGameHistory.gameHistory.count() + 1;
        game.setName("Game " + gameNameNumber);
        SavedGameHistory.gameHistory.add(game);

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    //============================ DISPLAYING AND FILLING ROW =============================

    //fills click row with random nubmers from 1 to 6
    public int[] fillRow() {
        int[] arr = new int[BUTTON_AMOUNT];

        for(int i = 0; i < arr.length; i++) {
            Random random = new Random();
            arr[i] = random.nextInt(6 - 1 + 1) + 1;
        }

        return arr;
    }

    //displays number row while countdown
    public void displayRow() {
        StringBuilder str = new StringBuilder();
        for(int i : buttonClickRow) {
            str.append(i + " ");
        }
        viewClickRow.setText("Click row: " + str);
    }

    public void displayPlayerClicks() {
        StringBuilder str = new StringBuilder();
        for(int i : playerClicks) {
            str.append(i + " ");
        }
        viewPlayerClicks.setVisibility(View.VISIBLE);
        if(playerClicks.size() > 6) {
            viewPlayerClicks.setTextSize(30);
        }
        viewPlayerClicks.setText("Your clicks: " + str);
        playerClicks.clear();
    }
}
