/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs380homework1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author kellyshiptoski
 */
public class SlidingBrickPuzzle {
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
