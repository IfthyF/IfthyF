import java.util.Scanner;
import java.io.*;

class QuizQuestion
{
    String question;
    String answer_1;
    int point_score_1;
    String answer_2;
    int point_score_2;
    String answer_3;
    int point_score_3;
}
class TotalScore
{
    String name;
    String score;
}

class Scratch {
    public static void main(String[] args) throws IOException
    {
        QuizDetails();
        System.exit(0);
    }

    public static String inputString(String message)
    {
        Scanner scanner = new Scanner(System.in);
        String answer;
        System.out.println(message);
        answer = scanner.nextLine();
        return answer;
    }

    public static int inputInt(String message)
    {
        Scanner scanner = new Scanner(System.in);
        String textInput;
        int numberInput;
        System.out.println(message);
        textInput = scanner.nextLine();
        numberInput = Integer.parseInt(textInput);
        return numberInput;
    }
    public static int Question_score(QuizQuestion question1, String answer)
    {
        int score2;
        if (answer.equals(getAnswer1(question1)))
        {
            score2 = getPoints1(question1);
            System.out.println("This answer gives you " + score2 + " points.");
        }
        else if (answer.equals(getAnswer2(question1)))
        {
            score2 = getPoints2(question1);
            System.out.println("This answer gives you " + score2 + " points.");
        }
        else if (answer.equals(getAnswer3(question1)))
        {
            score2 = getPoints3(question1);
            System.out.println("This answer gives you " + score2 + " points.");
        }
        else
        {
            score2 = 100;
            System.out.println("This is not one of the options");
            System.out.println("This answer gives you 100 points.");
        }
        return score2;
    }
    public static void QuizDetails() throws IOException
    {
        final QuizQuestion [] Quiz_questions = createBank();
        final int number_of_times = inputInt("How many people want to do the Quiz?");
        int [] board = new int[number_of_times];
        String [] playersScores = new String[number_of_times];
        for (int i=0; i<number_of_times; i++)
        {
            System.out.println();
            String user_name = "Person " + (i + 1);
            System.out.println(user_name + " to answer question from question bank:");
            int total = 0;
            int total2 = 0;
            String tries = "Y";
            while (tries.equals("Y"))
            {
                total = printQuestion(Quiz_questions);
                total2 = total2 + total;
                tries = inputString("Do you want to answer another question (Y/N)");
            }
            TotalScore player = createScore(user_name, Integer.toString(total2));
            AddToArray(playersScores, i, player);
            board [i] = total2;
            System.out.println();
        }
        printScores(playersScores);
        createTableScore(board);
        printTableScore(board);
        return;
    }
    public static int printQuestion(QuizQuestion [] question_bank)
    {
        int score = 0;
        int choice = inputInt("Out of question 1, 2 or 3, which one do you want to answer?");
        if (choice <= 3 & choice >= 1)
        {
            QuizQuestion question_used = question_bank[(choice - 1)];
            String answer = inputString(getQuestion(question_used));
            score = Question_score(question_used, answer);
        }
        else
        {
            System.out.println("That is not an option");
        }
        return score;
    }
    public static QuizQuestion createQuestion(String question1, String answer1, int point_score1,
                                          String answer2, int point_score2, String answer3, int point_score3)
    {
        QuizQuestion question_1 = new QuizQuestion();
        question_1.question = question1;
        question_1.answer_1 = answer1;
        question_1.point_score_1 = point_score1;
        question_1.answer_2 = answer2;
        question_1.point_score_2 = point_score2;
        question_1.answer_3 = answer3;
        question_1.point_score_3 = point_score3;
        return question_1;
    }

    public static QuizQuestion[] createBank() throws IOException
    {
        String question1_file = fileOutputQuestion1();
        String question2_file = fileOutputQuestion2();
        String question3_file = fileOutputQuestion3();
        QuizQuestion question1 = fileInputQuestion(question1_file);
        QuizQuestion question2 = fileInputQuestion(question2_file);
        QuizQuestion question3 = fileInputQuestion(question3_file);
        QuizQuestion [] Question_Bank = new QuizQuestion[3];
        Question_Bank [0] = question1;
        Question_Bank [1] = question2;
        Question_Bank [2] = question3;
        return Question_Bank;
    }


    public static TotalScore createScore(String given_name, String given_score)
    {
        TotalScore player = new TotalScore();
        player.name = given_name;
        player.score = given_score;
        return player;
    }

    public static void AddToArray(String [] scoreArray, int position, TotalScore player)
    {
        scoreArray [position] = player.name + " has score of " + player.score + " points.";
        return;
    }

    public static void printScores(String [] scoreArray)
    {
        System.out.println("The scores of each user:");
        for (int i=0; i<scoreArray.length; i++)
        {
            System.out.println(scoreArray[i]);
        }
        System.out.println();
        return;
    }

    public static void createTableScore(int [] board)
    {
        String option = inputString("Would you like to see the leaderboard? (Y/N)");
        if (option.equals("Y"))
        {
            int temporary = 0;
            for (int pass=0; pass<board.length; pass++)
            {
                for (int b=1; b<board.length-pass; b++)
                {
                    if (board[b-1] > board[b])
                    {
                        temporary = board[b-1];
                        board[b-1] = board[b];
                        board[b] = temporary;
                    }
                }
            }
        }
        else
        {
            System.out.println("Thank you for taking part in the Quiz.");
            System.exit(0);
        }
        return;
    }
    public static void printTableScore(int [] board)
    {
        for (int i=0; i<board.length; i++)
        {
            System.out.println((i + 1) + ": " + board[i] + " points.");
        }
        return;
    }

    public static String getQuestion(QuizQuestion question1)
    {
        return question1.question;
    }

    public static String getAnswer1(QuizQuestion question1)
    {
        return question1.answer_1;
    }

    public static String getAnswer2(QuizQuestion question1)
    {
        return question1.answer_2;
    }

    public static String getAnswer3(QuizQuestion question1)
    {
        return question1.answer_3;
    }

    public static int getPoints1(QuizQuestion question1)
    {
        return question1.point_score_1;
    }

    public static int getPoints2(QuizQuestion question1)
    {
        return question1.point_score_2;
    }

    public static int getPoints3(QuizQuestion question1)
    {
        return question1.point_score_3;
    }

    public static String fileOutputQuestion1() throws IOException
    {
        String question1 = "actors.txt";
        PrintWriter outputStream = new PrintWriter(new FileWriter(question1));
        outputStream.println("Out of the three options: Option 1: Rami Malek, Option 2: Adam Sandler, " +
                "Option 3: Denzel Washington, Who is an Oscar winning actor?");
        outputStream.println("Rami Malek");
        outputStream.println("20");
        outputStream.println("Adam Sandler");
        outputStream.println("5");
        outputStream.println("Denzel Washington");
        outputStream.println("75");
        outputStream.close();
        return question1;
    }

    public static String fileOutputQuestion2() throws IOException
    {
        String question2 = "presidents.txt";
        PrintWriter outputStream = new PrintWriter(new FileWriter(question2));
        outputStream.println("Out of the three options: Option 1: Benjamin Franklin, Option 2: George Washington, " +
                "Option 3: Barack Obama, Who is an American president?");
        outputStream.println("Benjamin Franklin");
        outputStream.println("12");
        outputStream.println("George Washington");
        outputStream.println("28");
        outputStream.println("Barack Obama");
        outputStream.println("60");
        outputStream.close();
        return question2;
    }

    public static String fileOutputQuestion3() throws  IOException
    {
        String question3 = "cities.txt";
        PrintWriter outputStream = new PrintWriter(new FileWriter(question3));
        outputStream.println("Out of the three options: Option 1: Agra, Option 2: Mumbai, " +
                "Option 3: Hyderabad, Which city is India?");
        outputStream.println("Agra");
        outputStream.println("25");
        outputStream.println("Mumbai");
        outputStream.println("63");
        outputStream.println("Hyderabad");
        outputStream.println("12");
        outputStream.close();
        return question3;
    }

    public static QuizQuestion fileInputQuestion(String question_file) throws IOException
    {
        BufferedReader inputStream = new BufferedReader(new FileReader(question_file));
        String question = inputStream.readLine();
        String answer1 = inputStream.readLine();
        int score1 = Integer.parseInt(inputStream.readLine());
        String answer2 = inputStream.readLine();
        int score2 = Integer.parseInt(inputStream.readLine());
        String answer3 = inputStream.readLine();
        int score3 = Integer.parseInt(inputStream.readLine());
        inputStream.close();
        QuizQuestion questionRecord = createQuestion(question, answer1, score1, answer2, score2, answer3, score3);
        return questionRecord;
    }
}
