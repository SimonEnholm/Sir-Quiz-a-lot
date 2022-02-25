package SirQuizALot;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Statistics {

    List<List<Integer>> questionFrequencies = new ArrayList<List<Integer>>();
    int[][] highscoreList = new int[5][2]; // [UserId, Score]

    public Statistics (QuestionRepo questionRepo) { // todo maybe move into question&questionRepo class
        // Add a list of [id, option1Freq, option2Freq, option3Freq]
        List<Questions> allQuestions = questionRepo.getAll();
        for (int i = 0; i < allQuestions.size(); i++)
            questionFrequencies.add(List.of(allQuestions.get(i).getId(),0,0,0));

        //Fixa highscore lista
        highscoreList[0][0] = 1;
        highscoreList[0][1] = 1337;
        highscoreList[1][0] = 3;
        highscoreList[1][1] = 1;
    }

    public List<List<Integer>> getQuestionFrequencies() {
        return questionFrequencies;
    }

    public int[][] getHighscoreList() {
        return highscoreList;
    }


    public void addToHighscoreList(User user) {
        int[][] tempList = new int[5][2];
        for (int i = 0; i < highscoreList.length; i++) {
            if (highscoreList[i][1] < user.getPoint()) {
                tempList[i][0] = user.getId();
                tempList[i][1] = user.getPoint();
                for (int j = i + 1; j < highscoreList.length; j++) {
                    tempList[j][0] = highscoreList[j - 1][0];
                    tempList[j][1] = highscoreList[j - 1][1];
                }
                break;
            } else {
                tempList[i][0] = highscoreList[i][0];
                tempList[i][1] = highscoreList[i][1];
            }
        }
        highscoreList = tempList;
    }
}
