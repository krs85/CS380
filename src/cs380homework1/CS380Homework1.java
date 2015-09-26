/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs380homework1;

import java.util.List;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
/**
 *
 * @author kellyshiptoski
 */
public class CS380Homework1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        //ArrayList<ArrayList<Integer>> state = loadGameState("SBP-level1.txt");
        //showGameState(state);
        //System.out.println();
        //showGameState(cloneGameState(state));
        
        ArrayList<ArrayList<Integer>> state = loadGameState("SBP-level0.txt");
        ArrayList<ArrayList<Integer>> stateSolved = loadGameState("SBP-level0-solved.txt");
        boolean stateBool = puzzleCompleteCheck(state);
        boolean stateSolvedBool = puzzleCompleteCheck(stateSolved);
        
        System.out.println(stateBool);
        System.out.println();
        System.out.println(stateSolvedBool);
    }
    
    static ArrayList<ArrayList<Integer>> loadGameState(String filename) throws IOException
    {
        FileReader fr = new FileReader(filename);
        BufferedReader textReader = new BufferedReader(fr);

        String line;
        int numLines = 0;
        int i = 0;
        String firstline = textReader.readLine();

        ArrayList<ArrayList<Integer>> gameBoard = new ArrayList<ArrayList<Integer>>();
        while((line = textReader.readLine()) != null)
        {
            gameBoard.add(new ArrayList<Integer>());
            String[] noCommas = line.split(",");
            for(String number: noCommas)
            {
                int num = Character.getNumericValue(number.charAt(0));
                gameBoard.get(i).add(num);
            }
            i++;
            
        }
        textReader.close();
        return gameBoard;
    } 
    
    static void showGameState(ArrayList<ArrayList<Integer>> gameState) throws IOException
    {   
        for(int i = 0; i < gameState.size(); i++)
        {
            for (int j = 0; j < gameState.get(0).size(); j++)
            {
                System.out.print(gameState.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
    
    static ArrayList<ArrayList<Integer>> cloneGameState (ArrayList<ArrayList<Integer>> gameState)
    {
        ArrayList<ArrayList<Integer>> clonedGameState = new ArrayList<ArrayList<Integer>>();
    
        for(int i = 0; i < gameState.size(); i++)
        {
            clonedGameState.add(new ArrayList<Integer>());
            for (int j = 0; j < gameState.get(0).size(); j++)
            {
                clonedGameState.get(i).add(gameState.get(i).get(j));
            }
        }
        return clonedGameState;
    }
    
    static boolean puzzleCompleteCheck(ArrayList<ArrayList<Integer>> gameState)
    {
        for(int i = 0; i < gameState.size(); i++)
        {
            for(int j = 0; j < gameState.get(0).size(); j++)
            {
                int num = gameState.get(i).get(j);
                if (num == -1)
                    return false;
            }
        }
        return true;
    }
}

