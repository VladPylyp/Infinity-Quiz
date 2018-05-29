package com.stormarts.infinityquiz.presentation.presenter;


import android.util.Log;


import com.stormarts.infinityquiz.AndroidResourceManager;
import com.stormarts.infinityquiz.QuizApp;
import com.stormarts.infinityquiz.ResourceManager;
import com.stormarts.infinityquiz.data.CloudDatabase;
import com.stormarts.infinityquiz.data.CloudDatabaseError;
import com.stormarts.infinityquiz.data.DatabaseUserEventListener;
import com.stormarts.infinityquiz.model.GameAuth;
import com.stormarts.infinityquiz.model.Level;
import com.stormarts.infinityquiz.model.Question;
import com.stormarts.infinityquiz.model.Questions;
import com.stormarts.infinityquiz.model.User;
import com.stormarts.infinityquiz.presentation.view.GameView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.inject.Inject;

@InjectViewState
public class GamePresenter extends MvpPresenter<GameView> {



    private String localeLanguage;
    private static final String TAG = GamePresenter.class.getSimpleName();
    @Inject
    ResourceManager resourceManager;
    @Inject
    CloudDatabase cloudDatabase;
    private List<Question> questionsList;
    private Random rand;
    private Question currentQuestion;
    private int currentLevel = Level.EASY;
    private int currentQuestionNumber = 0;
    private DatabaseUserEventListener databaseUserEventListener;


    /***  todo
     implement score and coins adding with coefficient ***/
    private float coefficient;
    private int coins;
    private int score;

    public GamePresenter() {
        QuizApp.getApplication().getApplicationComponent().inject(this);
        cloudDatabase.setUserDataListener(databaseUserEventListener);
        localeLanguage = Locale.getDefault().getLanguage();
        rand = new Random();
        databaseUserEventListener = new DatabaseUserEventListener() {
            @Override
            public void onUserExist(User user) {
                Log.d(TAG, "onUserExist: " + user.toString());
            }

            @Override
            public void onUserNotExist() {

            }

            @Override
            public void onError(CloudDatabaseError error) {

            }
        };
    }



    public void loadLevel(String selectedLevel){

            String levelPath = ResourceManager.constructPassToJsonLevel(selectedLevel, localeLanguage);
            String levelJson = resourceManager.loadJsonFromAsset(levelPath);

            if (levelJson == null) {
                levelPath = ResourceManager.constructPassToJsonLevel(selectedLevel, Locale.ENGLISH.getLanguage());
                levelJson = resourceManager.loadJsonFromAsset(levelPath);
            }
            currentLevel = Level.getLevelInt(selectedLevel);

            //deserialization of questions object
            Questions questions = resourceManager.getJavaObject(Questions.class, levelJson);
            questionsList = questions.getQuestions();

            // remove all null objects
            questionsList.removeAll(Collections.singleton(null));



            startGame();

        Log.d(TAG, "loadLevel: Level Json: " + levelJson );
    }

    private void resetGame(){

        currentQuestionNumber = 0;
        getViewState().setQuestionsCount(questionsList.size());
    }

    private void startGame(){
        resetGame();
        nextQuestion();
    }

    private void wrongAnswer(){
        Log.d(TAG, "selectAnswer:  false");
    }

    private void rightAnswer(){
        Log.d(TAG, "selectAnswer:  true"  );
        nextQuestion();
    }

    public void selectAnswer(String answer){
            Log.d(TAG, "selectAnswer: selected " + answer );

        if (answer.equals(currentQuestion.getCorrectAnswer())) {
            rightAnswer();
        } else {
            wrongAnswer();
        }

    }

    private void nextQuestion(){
        getViewState().setScore(GameAuth.getInstance().getCurrentUser().getScore());
        getViewState().setCoins(GameAuth.getInstance().getCurrentUser().getCoins());

        if(questionsList.size() > 0){
            currentQuestionNumber++;
            int questionIndex = rand.nextInt(questionsList.size());
            currentQuestion = questionsList.get(questionIndex);
            getViewState().setCurrentQuestion(currentQuestion.getQuestion());
            getViewState().setCurrentAnswers(currentQuestion.getAnswersList());
            getViewState().setCurrentQuestionNumber(currentQuestionNumber);
            questionsList.remove(questionIndex);
        } else
        {
            getViewState().showNewLevelAskDialog();
        }

    }

    public void loadNextLevel() {
        if (GameAuth.getInstance().getCurrentUser().getAllowedLevel() > currentLevel) {
            if (Level.isLevelExist(currentLevel + 1)) {
                loadLevel(Level.getLevelString(currentLevel + 1));
            }
        }
        //todo show "buy next level" dilaog and implement him
    }

    public void saveData(){
        // todo save new data like score, coins
    }



}
