/*
 * Copyright (c)2018 sdkevin.com, Inc.  All rights reserved.
 *
 * Owner: sdkevin@
 */
package klotski;

import java.util.Comparator;
import java.util.List;

/**
 * @author sdkevin@
 */
public class Main
{
    public static void main(String[] args)
    {
        Checkerboard checkerboard = new Checkerboard();
        checkerboard.init();

        List<Solution> solutions = checkerboard.solute();

        solutions.sort(new Comparator<Solution>() {
            @Override
            public int compare(Solution o1, Solution o2)
            {
                return Integer.compare(o1.getMoveCount(), o2.getMoveCount());
            }
        });

        for (int i = 0; i < solutions.size(); ++i)
        {
            Solution currentSolution = solutions.get(i);
            currentSolution.print();
        }
    }

}
