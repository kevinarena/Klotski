/*
 * Copyright (c)2018 sdkevin.com, Inc.  All rights reserved.
 *
 * Owner: sdkevin@
 */
package klotski;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sdkevin@
 */
@Data
public class Solution
{
    private List<Move> moveList = new ArrayList<>();

    public Solution() { }

    public static Solution copy(Solution solution)
    {
        Solution newSolution = new Solution();
        newSolution.setMoveList(new ArrayList<>(solution.getMoveList()));
        return newSolution;
    }

    public void print()
    {
        for (int i = 0; i < moveList.size(); ++i)
        {
            if (i != 0)
            {
                System.out.print("->");
            }
            Move currentMove = moveList.get(i);
            currentMove.print();
        }
        System.out.println();
    }

    public void add(Move move)
    {
        moveList.add(move);
    }

    public void removeLast()
    {
        int length = moveList.size();
        moveList.remove(length - 1);
    }

    public int getMoveCount()
    {
        return moveList.size();
    }
}
