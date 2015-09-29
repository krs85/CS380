/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs380homework1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author kellyshiptoski
 */
public class Moves {
    
    public enum Direction 
    {
        up, down, left, right;
    }
    
    //attributes
    private int intIdentifier;
    public int getIntIdentifier() {return intIdentifier;}
    public void setIntIdentifier(int intIdent) {this.intIdentifier = intIdent;}
    private Direction direction;
    public Direction getDirection() {return direction;}
    public void setDirection (Direction direction) {this.direction = direction;}
    
    static ArrayList<Moves> movesPossible (int piece, ArrayList<ArrayList<Integer>> gameState)
    {
        ArrayList<Boolean> moveUp = new ArrayList<Boolean>();
        ArrayList<Boolean> moveDown = new ArrayList<Boolean>();
        ArrayList<Boolean> moveLeft = new ArrayList<Boolean>();
        ArrayList<Boolean> moveRight = new ArrayList<Boolean>();
        
        for(int i = 0; i < gameState.size(); i++)
        {
            for(int j = 0; j < gameState.get(0).size(); j++)
            {
                if(gameState.get(i).get(j) == piece)
                {
                    int upPos = gameState.get(i-1).get(j);
                    int downPos = gameState.get(i+1).get(j);
                    int leftPos = gameState.get(i).get(j-1);
                    int rightPos = gameState.get(i).get(j+1);
                    moveUp.add(upPos == 0 || (piece == 2 && upPos == -1) || upPos == piece);
                    moveDown.add(downPos == 0 || (piece == 2 && downPos == -1) || downPos == piece);
                    moveLeft.add(leftPos == 0 || (piece == 2 && leftPos == -1) || leftPos == piece);
                    moveRight.add(rightPos == 0 || (piece == 2 && rightPos == -1) || rightPos == piece);
                }
            }
        }

        ArrayList<Moves> moves = new ArrayList<Moves>();
        if (sameComponents(moveUp))
        {
            Moves move = new Moves();
            move.direction = Direction.up;
            move.intIdentifier = piece;
            moves.add(move);
        }
        if (sameComponents(moveDown))
        {
            Moves move = new Moves();
            move.direction = Direction.down;
            move.intIdentifier = piece;
            moves.add(move);
        }
        if (sameComponents(moveLeft))
        {
            Moves move = new Moves();
            move.direction = Direction.left;
            move.intIdentifier = piece;
            moves.add(move);
        }
        if (sameComponents(moveRight))
        {
            Moves move = new Moves();
            move.direction = Direction.right;
            move.intIdentifier = piece;
            moves.add(move);
        }
        return moves;
    }
    
    static Boolean sameComponents(ArrayList<Boolean> boolList)
    {
        for (int i = 0; i < boolList.size(); i++)
        {
            if(!(boolList.get(i) == boolList.get(0)))
                return false;
        }
        return boolList.get(0);
    }
    
    static ArrayList<Moves> allMoves(ArrayList<ArrayList<Integer>> gameState)
    {
        ArrayList<Moves> list = new ArrayList<Moves>();
        HashSet<Integer> seenPieces = new HashSet<Integer>();
        for(int i = 0; i < gameState.size(); i++)
        {
            for(int j = 0; j < gameState.get(0).size(); j++)
            {
                int piece = gameState.get(i).get(j);
                if(piece > 1 && !seenPieces.contains(piece))
                {
                    seenPieces.add(piece);
                    list.addAll(movesPossible(piece, gameState));
                }
            }
        }
        
        return list;
    }
    
    static void applyMove(ArrayList<ArrayList<Integer>> gameState, Moves move)
    {
        int upperLeftColumn = 0;
        int upperLeftRow = 0;
        boolean found = false;
        for (int i = 0; i < gameState.size() && !found; i++)
        {
            for (int j = 0; j < gameState.get(0).size() && !found; j++)
            {
                if (move.intIdentifier == gameState.get(i).get(j))
                {
                    upperLeftRow = i;
                    upperLeftColumn = j;
                    found = true;
                }
            }
        }
        
        if (move.direction == Direction.up)
        {
            int lastRow = upperLeftRow;
            int lastColumn = upperLeftColumn;
            while(gameState.get(lastRow + 1).get(upperLeftColumn) == move.intIdentifier)
                lastRow++;
            
            do
            {
                gameState.get(upperLeftRow - 1).set(lastColumn, move.intIdentifier);
                gameState.get(lastRow).set(lastColumn, 0);
                lastColumn++;
            } while (gameState.get(lastRow).get(lastColumn) == move.intIdentifier);
        }
                
        else if (move.direction == Direction.down)
        {
            int lastRow = upperLeftRow;
            int lastColumn = upperLeftColumn;
            while(gameState.get(lastRow + 1).get(upperLeftColumn) == move.intIdentifier)
                lastRow++;

            do
            {
                gameState.get(lastRow + 1).set(lastColumn, move.intIdentifier);
                gameState.get(upperLeftRow).set(upperLeftColumn, 0);
                lastColumn++;
            } while(gameState.get(lastRow).get(lastColumn) == move.intIdentifier);
        }
        
        else if (move.direction == Direction.left)
        {
            int lastRow = upperLeftRow;
            int lastColumn = upperLeftColumn;
            while(gameState.get(upperLeftRow).get(lastColumn + 1) == move.intIdentifier)
                lastColumn++;
            
            do
            {
                gameState.get(lastRow).set(upperLeftColumn - 1, move.intIdentifier);
                gameState.get(lastRow).set(lastColumn, 0);
                lastRow++;
            } while (gameState.get(lastRow).get(lastColumn) == move.intIdentifier);
        }
        
        else if (move.direction == Direction.right)
        {
            int lastRow = upperLeftRow;
            int lastColumn = upperLeftColumn;
            while(gameState.get(upperLeftRow).get(lastColumn + 1) == move.intIdentifier)
                lastColumn++;
            
            do
            {
                gameState.get(lastRow).set(upperLeftColumn, 0);
                gameState.get(lastRow).set(lastColumn + 1, move.intIdentifier);
                lastRow++;
            } while (gameState.get(lastRow).get(lastColumn) == move.intIdentifier);
        }
    }
    
    static ArrayList<ArrayList<Integer>> applyMoveCloning (ArrayList<ArrayList<Integer>> gameState, Moves move)
    {
        ArrayList<ArrayList<Integer>> clonedState = CS380Homework1.cloneGameState(gameState);
        applyMove(clonedState, move);
        return clonedState;
    }
}
