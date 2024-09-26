import java.util.Random;
import java.util.Scanner;

public class Main {

    private static int playersWin=0;
    private static int computerWin=0;
    private static int ties=0;

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Random random=new Random();
        String[] choises={"Rock", "Paper","Scissor"};
        System.out.println("Welcome to the -|Rock|-|Paper|-|Scissor|- game");

        while(true){
            System.out.println("\nEnter your choice {Rock|Paper|Scissor}. Type exit to quit: ");
            String userChoice=scanner.nextLine();

            //Checks if user wants to exit!!
            if (userChoice.equalsIgnoreCase("exit")){
                break;
            }

            //Validate user input
            if (!isValidChoice(userChoice)){
                System.out.println("Invalid Choice! Please try again");
                continue; ///Restart the loop for a valid choice
            }

            //Generate the Computer choice
            int computerChoiceIndex = random.nextInt(3);
            String computerChoice=choises[computerChoiceIndex];

            //Display choices
            System.out.println("Compter's Choice: "+ computerChoice);

            //Determine the winner
            determineWinner(userChoice,computerChoice);
        }

        //display the scores
        displayScores();
        scanner.close();
    }


    //Method to validate the user choice
    public static boolean isValidChoice(String choice){
        return choice.equalsIgnoreCase("Rock")
                || choice.equalsIgnoreCase("Paper")
                || choice.equalsIgnoreCase("Scissor");
    }

    //Method to determine the winner
    public static void determineWinner(String userChoice, String computerChoice){

        if (userChoice.equalsIgnoreCase(computerChoice)){
            System.out.println("it's ties");
            ties++;
        } else if ( (userChoice.equalsIgnoreCase("Rock") && computerChoice.equals("Scissor"))
                  || (userChoice.equalsIgnoreCase("Paper") && computerChoice.equals("Rock"))
                || (userChoice.equalsIgnoreCase("Scissor") && computerChoice.equals("Paper"))) {
            System.out.println("You Win!");
            playersWin++;
        }
        else {
            System.out.println("Computer Win!");
            computerWin++;
        }
    }

    //Method to display Scores
    public static void displayScores(){
        System.out.println("\n Game Summary: ");
        System.out.println("Player's Win: "+playersWin);
        System.out.println("Computer's Win: "+computerWin);
        System.out.println("Ties: "+ties);
        System.out.println("Thank you for playing!!");
    }
}
