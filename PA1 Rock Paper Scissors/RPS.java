/**
 * TODO: Add your file header
 * Name: Ruoqing Song
 * ID: A17191150
 * Email: r2song@ucsd.edu
 * Sources used: Tutors, Zybooks, and Lecture Slides
 * Some example of sources used would be Tutors, Zybooks, and Lecture Slides
 * This is a computer program to play the game of Rock-Paper-Scissors between 
 * the computer and a user. After the game, the computer will output the 
 * statistics of the games played.
 */

import java.util.Scanner;

/**
 * This is a class that extends from RPSAbstract that plays the game of 
 * Rock-Paper-Scissors between a user and computer
 */
public class RPS extends RPSAbstract {
     
    /**
     * This function will initialize the rock-paper-scissors class
     * @param moves - all possible moves in a rock-paper-scissors game instance
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }

    /**
     * Takes the user move, the CPU move, and determines who wins.
     * @param playerMove - move of the player
     * @param cpuMove - move of the CPU
     * @return -1 for invalid move, 0 for tie, 1 for player win, 2 for cpu win
     */
    public int determineWinner(String playerMove, String cpuMove) {
        // TODO 
        int playerIndex = -1;
        int cpuIndex = -1;
        for (int i = 0; i < possibleMoves.length; i++) {
            if (playerMove.equals(possibleMoves[i])){
                playerIndex = i;
            }
            if (cpuMove.equals(possibleMoves[i])){
                cpuIndex = i;
            }
        }
        if (playerIndex == -1 || cpuIndex == -1) {
            return INVALID_INPUT_OUTCOME;
        }
        if (cpuIndex == playerIndex + 1){
            return PLAYER_WIN_OUTCOME;
        }
        if (playerIndex == possibleMoves.length - 1 && cpuIndex == 0){
            return PLAYER_WIN_OUTCOME;
        }
        if (playerIndex == cpuIndex + 1){
            return CPU_WIN_OUTCOME;
        }
        if (cpuIndex == possibleMoves.length - 1 && playerIndex == 0){
            return CPU_WIN_OUTCOME;
        }
        return TIE_OUTCOME;
    }

    /**
     * Main method that reads user input, generates cpu move, and plays game
     * @param args - arguments passed in from command line in String form
     */
    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES){
            for (int i = 0; i < args.length; i++) {
                moves[i] = args[i];
            }
        } else {
            moves = RPS.DEFAULT_MOVES;
        }

        // Create new game and scanner
        RPS game = new RPS(moves);
        System.out.println(PROMPT_MOVE);
        Scanner in = new Scanner(System.in);

        // While user does not input "q", play game
        String move = in.nextLine();
        while (!move.equals(game.QUIT)){
            String cpuMove = game.genCPUMove();
            game.play(move, cpuMove); 
            System.out.println();
            System.out.println(PROMPT_MOVE);
            move = in.nextLine();
        }

        game.end();

        in.close();
    }
}
