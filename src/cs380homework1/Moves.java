/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs380homework1;

import java.util.ArrayList;
import java.util.HashMap;

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
    
    ArrayList<Direction> movesPossible (int piece, ArrayList<ArrayList<Integer>> gameState)
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

        ArrayList<Direction> moves = new ArrayList<Direction>();
        if (sameComponents(moveUp))
            moves.add(Direction.up);
        if (sameComponents(moveDown))
            moves.add(Direction.down);
        if (sameComponents(moveLeft))
            moves.add(Direction.left);
        if (sameComponents(moveRight))
            moves.add(Direction.right);
        return moves;
    }
    
    Boolean sameComponents(ArrayList<Boolean> boolList)
    {
        for (int i = 0; i < boolList.size(); i++)
        {
            if(!(boolList.get(i) == boolList.get(0)))
                return false;
        }
        return boolList.get(0);
    }
    
    HashMap<Integer, ArrayList<Direction>> allMoves(ArrayList<ArrayList<Integer>> gameState)
    {
        HashMap<Integer, ArrayList<Direction>> allMoves = new HashMap<Integer, ArrayList<Direction>>();
        for(int i = 0; i < gameState.size(); i++)
        {
            for(int j = 0; j < gameState.get(0).size(); j++)
            {
                if(gameState.get(i).get(j) > 1)
                    allMoves.put(gameState.get(i).get(j), movesPossible(gameState.get(i).get(j), gameState));
            }
        }
        return allMoves;
    }
    
    static void applyMove(ArrayList<ArrayList<Integer>> gameState, Moves move)
    {
        for(int i = 0; i < gameState.size(); i++)
        {
            for(int j = 0; j < gameState.get(0).size(); j++)
            {
                if(gameState.get(i).get(j) == move.intIdentifier)
                {
                    if(move.direction == Direction.up)
                    {
                        int replacement = gameState.get(i).get(j-1);
                        gameState.get(i).set(j, replacement); 
                        gameState.get(i).set(j-1, move.intIdentifier);
                    }
                    else if(move.direction == Direction.down)
                    {
                        int replacement = gameState.get(i).get(j+1);
                        gameState.get(i).set(j, replacement);
                        gameState.get(i).set(j+1, move.intIdentifier);
                    }
                    else if(move.direction == Direction.left)
                    {
                        int replacement = gameState.get(i-1).get(j);
                        gameState.get(i).set(j, replacement);
                        gameState.get(i-1).set(j, move.intIdentifier);
                    }
                    else if(move.direction == Direction.right)
                    {
                        int replacement = gameState.get(i+1).get(j);
                        gameState.get(i).set(j, replacement);
                        gameState.get(i+1).set(j, move.intIdentifier);
                    }
                }
            }
        }
    }
}
