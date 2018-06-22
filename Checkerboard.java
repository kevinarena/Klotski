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
public class Checkerboard
{
    private Situation initialSituation;

    List<Situation> situationList;


    private List<IRole> roleList = new ArrayList<>();

    private King caocao;

    private General guanyu;
    private General zhangfei;
    private General huangzhong;
    private General machao;
    private General zhaoyun;

    private Soldier s1;
    private Soldier s2;
    private Soldier s3;
    private Soldier s4;

    int solutionCount;

    public void init()
    {
        initialSituation = new Situation();
        initialSituation.init();
        caocao = new King(this, "caocao");

        situationList = new ArrayList<>();
        situationList.add(initialSituation);

        guanyu = new General(this, "guanyu", RoleType.BRAVE_GENERAL);
        zhangfei = new General(this, "zhangfei", RoleType.LOYAL_GENERAL);
        huangzhong = new General(this, "huangzhong", RoleType.LOYAL_GENERAL);
        machao = new General(this, "machao", RoleType.LOYAL_GENERAL);
        zhaoyun = new General(this, "zhaoyun", RoleType.LOYAL_GENERAL);

        s1 = new Soldier(this, "s1");
        s2 = new Soldier(this, "s2");
        s3 = new Soldier(this, "s3");
        s4 = new Soldier(this, "s4");

        roleList.add(caocao);
        roleList.add(guanyu);
        roleList.add(zhangfei);
        roleList.add(huangzhong);
        roleList.add(machao);
        roleList.add(zhaoyun);
        roleList.add(s1);
        roleList.add(s2);
        roleList.add(s3);
        roleList.add(s4);

        caocao.setFirstSquare(new Square(0, 1));
        guanyu.setFirstSquare(new Square(2, 1));
        zhangfei.setFirstSquare(new Square(3, 0));
        huangzhong.setFirstSquare(new Square(3, 1));
        machao.setFirstSquare(new Square(3, 2));
        zhaoyun.setFirstSquare(new Square(2, 3));

        s1.setFirstSquare(new Square(0, 0));
        s2.setFirstSquare(new Square(2, 0));
        s3.setFirstSquare(new Square(0, 3));
        s4.setFirstSquare(new Square(1, 3));

        for (IRole role : roleList)
        {
            RoleBase roleBase = (RoleBase) role;
            Square square = roleBase.getFirstSquare();
            int row = square.getRow();
            int col = square.getCol();
            if (role instanceof Soldier)
            {
                initialSituation.getCheckBoardSituation()[row][col] = RoleType.SOLDIER;
            }
            else if (role instanceof King)
            {
                initialSituation.getCheckBoardSituation()[row][col] = RoleType.KING;
                initialSituation.getCheckBoardSituation()[row][col+1] = RoleType.KING;
                initialSituation.getCheckBoardSituation()[row+1][col] = RoleType.KING;
                initialSituation.getCheckBoardSituation()[row+1][col+1] = RoleType.KING;
            }
            else if (role instanceof General)
            {
                if (((General) role).getRoleType() == RoleType.BRAVE_GENERAL)
                {
                    initialSituation.getCheckBoardSituation()[row][col] = RoleType.BRAVE_GENERAL;
                    initialSituation.getCheckBoardSituation()[row][col+1] = RoleType.BRAVE_GENERAL;
                }
                else
                {
                    initialSituation.getCheckBoardSituation()[row][col] = RoleType.LOYAL_GENERAL;
                    initialSituation.getCheckBoardSituation()[row+1][col] = RoleType.LOYAL_GENERAL;
                }
            }
        }
    }

    public List<Solution> solute()
    {
        List<Solution> solutions = new ArrayList<>();
        Solution solution = new Solution();

        solute(solutions, solution, situationList);
        return solutions;
    }

    public void solute(List<Solution> solutions, Solution solution, List<Situation> situationList)
    {
        int situationLength = situationList.size();
        int solutionLength = solutions.size();
        if (solutionLength > solutionCount)
        {
            Solution solution1 = solutions.get(solutionLength - 1);
            System.out.println();
            System.out.println(solution1.getMoveCount());
            System.out.println();
            solution1.print();
            System.out.println();
            System.out.println();
            solutionCount = solutionLength;
            return;
        }
        Situation currentSituation = situationList.get(situationLength - 1);
        if (currentSituation.success())
        {
            solutions.add(Solution.copy(solution));
            situationList.remove(situationLength - 1);
            solution.removeLast();
            return;
        }

        for (IRole role : roleList)
        {
            role.move(solutions, solution, situationList);
        }

        return;
    }

    @Override
    public String toString()
    {
        return "Checkerboard";
    }
}
