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
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

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
        
        //ArrayList<ArrayList<Integer>> state = loadGameState("SBP-level0.txt");
        //ArrayList<ArrayList<Integer>> stateSolved = loadGameState("SBP-level0-solved.txt");
        //boolean stateBool = puzzleCompleteCheck(state);
        //boolean stateSolvedBool = puzzleCompleteCheck(stateSolved);
        
        //System.out.println(stateBool);
        //System.out.println();
        //System.out.println(stateSolvedBool);
        //ArrayList<ArrayList<Integer>> state = loadGameState("SBP-level1.txt");
        //Moves move = new Moves();
        //move.setIntIdentifier(4);
        //move.setDirection(Moves.Direction.left);
        //ArrayList<Moves.Direction> directions = move.movesPossible(4, state);
        
        /*HashMap<Integer, ArrayList<Moves.Direction>> allMoves = move.allMoves(state);
        Set<Integer> keys = allMoves.keySet();
        for (Integer key: keys)
        {
           ArrayList<Moves.Direction> moves = allMoves.get(key);
           System.out.println("Moves for " + key);
           for(Moves.Direction direction: moves)
           {
               System.out.println(direction);
           }
        }*/

//        for(int i = 0; i < state.size(); i++)
//        {
//            for(int j = 0; j < state.get(0).size(); j++)
//            {
//                System.out.print(state.get(i).get(j) + " ");
//            }
//            System.out.println();
//        }
//        
//        System.out.println();
//
//        Moves.applyMove(state, move);
//        
//        for(int i = 0; i < state.size(); i++)
//        {
//            for(int j = 0; j < state.get(0).size(); j++)
//            {
//                System.out.print(state.get(i).get(j) + " ");
//            }
//            System.out.println();
//        }
//        
//        Moves move2 = new Moves();
//        move2.setDirection(Moves.Direction.down);
//        move2.setIntIdentifier(4);
//        
//        Moves.applyMove(state, move2);
//        
//        for(int i = 0; i < state.size(); i++)
//        {
//            for(int j = 0; j < state.get(0).size(); j++)
//            {
//                System.out.print(state.get(i).get(j) + " ");
//            }
//            System.out.println();
//        }
        ArrayList<ArrayList<Integer>> state = loadGameState("SBP-level0.txt");
        randomWalk(state, 3);
        
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
        System.out.println(gameState.size() + "," + gameState.get(0).size() + ",");
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
    
    static boolean stateComparison (ArrayList<ArrayList<Integer>> firstState, ArrayList<ArrayList<Integer>> secondState)
    {
        if (firstState.size() != secondState.size())
            return false;
        if (firstState.get(0).size() != secondState.get(0).size())
            return false;
        
        for(int i = 0; i < firstState.size(); i++)
        {
            for(int j = 0; j < firstState.get(0).size(); j++)
            {
                if(firstState.get(i).get(j) != secondState.get(i).get(j))
                    return false;
            }
        }
        return true;
    }
    
    static void swapIndex (int idx1, int idx2, int height, int width, ArrayList<ArrayList<Integer>> matrix)
    {
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                if(matrix.get(i).get(j) == idx1)
                {
                    matrix.get(i).set(j, idx2);
                }
                else if (matrix.get(i).get(j) == idx2)
                {
                    matrix.get(i).set(j, idx1);
                }
            }
        }
    }
    
    static void normalize(int height, int width, ArrayList<ArrayList<Integer>> matrix)
    {
        int nextIdx = 3;
        for (int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                if(matrix.get(i).get(j) == nextIdx)
                    nextIdx++;
                else if(matrix.get(i).get(j) > nextIdx)
                {
                    swapIndex(nextIdx, matrix.get(i).get(j), height, width, matrix);
                    nextIdx++;
                }
            }
        }
    }
    
    static void randomWalk(ArrayList<ArrayList<Integer>> state, int number) throws IOException
    {
        int i = 0;
        Random rand = new Random();
        showGameState(state);
        while(!puzzleCompleteCheck(state) && i < number )
        {
            ArrayList<Moves> allMoves = Moves.allMoves(state);
            int index = rand.nextInt(allMoves.size());
            System.out.println();
            System.out.println("(" + allMoves.get(index).getIntIdentifier() + "," + allMoves.get(index).getDirection().toString() + ")");
            System.out.println();
            Moves.applyMove(state, allMoves.get(index));
            normalize(state.size(), state.get(0).size(), state);
            showGameState(state);
            i++;
        }
    }
}

