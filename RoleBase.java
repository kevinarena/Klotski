/*
 * Copyright (c)2018 sdkevin.com, Inc.  All rights reserved.
 *
 * Owner: sdkevin@
 */
package klotski;

import lombok.Data;

import java.util.List;

/**
 * @author sdkevin@
 */
@Data
public abstract class RoleBase implements IRole
{
    protected Checkerboard checkerboard;

    protected RoleType roleType;

    protected String roleName;

    private Square firstSquare;

    int squareCount;

    @Override
    public boolean canMoveUp(Situation situation)
    {
        int row = firstSquare.getRow();
        if (row == 0)
        {
            return false;
        }

        int previousRow = row - 1;
        int col = firstSquare.getCol();
        int horizontalSize = getHorizontalSize();

        for (int i = col; i < col + horizontalSize; ++i)
        {
            if (situation.getCheckBoardSituation()[previousRow][i] != RoleType.BLANK)
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canMoveDown(Situation situation)
    {
        int row = firstSquare.getRow();
        if (row == situation.getCheckBoardSituation().length - 1)
        {
            return false;
        }

        int verticalSize = getVerticalSize();
        int nextRow = row + verticalSize;
        if (nextRow == situation.getCheckBoardSituation().length)
        {
            return false;
        }

        int col = firstSquare.getCol();
        int horizontalSize = getHorizontalSize();

        for (int i = col; i < col + horizontalSize; ++i)
        {
            if (situation.getCheckBoardSituation()[nextRow][i] != RoleType.BLANK)
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canMoveLeft(Situation situation)
    {
        int col = firstSquare.getCol();

        if (col == 0)
        {
            return false;
        }

        int previousCol = col - 1;
        int row = firstSquare.getRow();
        int verticalSize = getVerticalSize();

        for (int i = row; i < row + verticalSize; ++i)
        {
            if (situation.getCheckBoardSituation()[i][previousCol] != RoleType.BLANK)
            {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean canMoveRight(Situation situation)
    {
        int col = firstSquare.getCol();

        if (col == situation.getCheckBoardSituation()[0].length - 1)
        {
            return false;
        }

        int horizontalSize = getHorizontalSize();
        int nextCol = col + horizontalSize;
        if (nextCol == situation.getCheckBoardSituation()[0].length)
        {
            return false;
        }
        int row = firstSquare.getRow();
        int verticalSize = getVerticalSize();

        for (int i = row; i < row + verticalSize; ++i)
        {
            if (situation.getCheckBoardSituation()[i][nextCol] != RoleType.BLANK)
            {
                return false;
            }
        }

        return true;
    }

    @Override
    public void move(List<Solution> solutions, Solution solution, List<Situation> situationList)
    {
        Situation currentSituation = situationList.get(situationList.size() - 1);

        if (canMoveUp(currentSituation))
        {
            moveUp(solutions, solution, situationList);
        }

        if (canMoveDown(currentSituation))
        {
            moveDown(solutions, solution, situationList);
        }

        if (canMoveLeft(currentSituation))
        {
            moveLeft(solutions, solution, situationList);
        }

        if (canMoveRight(currentSituation))
        {
            moveRight(solutions, solution, situationList);
        }
    }

    @Override
    public void moveUp(List<Solution> solutions, Solution solution, List<Situation> situationList)
    {
        int row = firstSquare.getRow();
        int col = firstSquare.getCol();

        Situation currentSituation = situationList.get(situationList.size() - 1);

        int horizontalSize = getHorizontalSize();
        int verticalSize = getVerticalSize();

        Situation newSituation = Situation.copy(currentSituation);

        for (int i = col; i < col + horizontalSize; ++i)
        {
            newSituation.getCheckBoardSituation()[row-1][i] = getType();
            newSituation.getCheckBoardSituation()[row+verticalSize-1][i] = RoleType.BLANK;
        }

        if (situationList.contains(newSituation))
        {
            return;
        }
        else
        {
            firstSquare.setRow(firstSquare.getRow()-1);
            situationList.add(newSituation);
            Move move = new Move(getRoleName(), "up");
            solution.add(move);
            checkerboard.solute(solutions, solution, situationList);
            firstSquare.setRow(firstSquare.getRow()+1);
            situationList.remove(situationList.size() - 1);
            solution.removeLast();
        }
    }

    @Override
    public void moveDown(List<Solution> solutions, Solution solution, List<Situation> situationList)
    {
        int row = firstSquare.getRow();
        int col = firstSquare.getCol();

        Situation currentSituation = situationList.get(situationList.size() - 1);

        int horizontalSize = getHorizontalSize();
        int verticalSize = getVerticalSize();

        Situation newSituation = Situation.copy(currentSituation);

        for (int i = col; i < col + horizontalSize; ++i)
        {
            newSituation.getCheckBoardSituation()[row+verticalSize][i] = getType();
            newSituation.getCheckBoardSituation()[row][i] = RoleType.BLANK;
        }

        if (situationList.contains(newSituation))
        {
            return;
        }
        else
        {
            firstSquare.setRow(firstSquare.getRow()+1);
            situationList.add(newSituation);
            Move move = new Move(getRoleName(), "down");
            solution.add(move);
            checkerboard.solute(solutions, solution, situationList);
            firstSquare.setRow(firstSquare.getRow()-1);
            situationList.remove(situationList.size() - 1);
            solution.removeLast();
        }
    }

    @Override
    public void moveLeft(List<Solution> solutions, Solution solution, List<Situation> situationList)
    {
        int row = firstSquare.getRow();
        int col = firstSquare.getCol();

        Situation currentSituation = situationList.get(situationList.size() - 1);

        int horizontalSize = getHorizontalSize();
        int verticalSize = getVerticalSize();

        Situation newSituation = Situation.copy(currentSituation);

        for (int i = row; i < row + verticalSize; ++i)
        {
            newSituation.getCheckBoardSituation()[i][col-1] = getType();
            newSituation.getCheckBoardSituation()[i][col+horizontalSize-1] = RoleType.BLANK;
        }

        if (situationList.contains(newSituation))
        {
            return;
        }
        else
        {
            firstSquare.setCol(firstSquare.getCol()-1);
            situationList.add(newSituation);
            Move move = new Move(getRoleName(), "left");
            solution.add(move);
            checkerboard.solute(solutions, solution, situationList);
            firstSquare.setCol(firstSquare.getCol()+1);
            situationList.remove(situationList.size() - 1);
            solution.removeLast();
        }
    }

    @Override
    public void moveRight(List<Solution> solutions, Solution solution, List<Situation> situationList)
    {
        int row = firstSquare.getRow();
        int col = firstSquare.getCol();

        Situation currentSituation = situationList.get(situationList.size() - 1);

        int horizontalSize = getHorizontalSize();
        int verticalSize = getVerticalSize();

        Situation newSituation = Situation.copy(currentSituation);

        for (int i = row; i < row + verticalSize; ++i)
        {
            newSituation.getCheckBoardSituation()[i][col+horizontalSize] = getType();
            newSituation.getCheckBoardSituation()[i][col] = RoleType.BLANK;
        }

        if (situationList.contains(newSituation))
        {
            return;
        }
        else
        {
            firstSquare.setCol(firstSquare.getCol()+1);
            situationList.add(newSituation);
            Move move = new Move(getRoleName(), "right");
            solution.add(move);
            checkerboard.solute(solutions, solution, situationList);
            firstSquare.setCol(firstSquare.getCol()-1);
            situationList.remove(situationList.size() - 1);
            solution.removeLast();
        }
    }

    private RoleType getType()
    {
        return roleType;
    }

    private int getHorizontalSize()
    {
        return roleType.getHorizontalSize();
    }

    private int getVerticalSize()
    {
        return roleType.getVerticalSize();
    }

    private String getRoleName()
    {
        return roleName;
    }
}
