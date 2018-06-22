/*
 * Copyright (c)2018 sdkevin.com, Inc.  All rights reserved.
 *
 * Owner: sdkevin@
 */
package klotski;

import lombok.Data;

/**
 * @author sdkevin@
 */
@Data
public class Situation
{
    private RoleType[][] checkBoardSituation;

    public void init()
    {
        checkBoardSituation = new RoleType[5][4];
        checkBoardSituation[0][0] = RoleType.SOLDIER;
        checkBoardSituation[0][1] = RoleType.KING;
        checkBoardSituation[0][2] = RoleType.KING;
        checkBoardSituation[0][3] = RoleType.SOLDIER;
        checkBoardSituation[1][0] = RoleType.BLANK;
        checkBoardSituation[1][1] = RoleType.KING;
        checkBoardSituation[1][2] = RoleType.KING;
        checkBoardSituation[1][3] = RoleType.SOLDIER;
        checkBoardSituation[2][0] = RoleType.SOLDIER;
        checkBoardSituation[2][1] = RoleType.BRAVE_GENERAL;
        checkBoardSituation[2][2] = RoleType.BRAVE_GENERAL;
        checkBoardSituation[2][3] = RoleType.LOYAL_GENERAL;
        checkBoardSituation[3][0] = RoleType.LOYAL_GENERAL;
        checkBoardSituation[3][1] = RoleType.LOYAL_GENERAL;
        checkBoardSituation[3][2] = RoleType.LOYAL_GENERAL;
        checkBoardSituation[3][3] = RoleType.LOYAL_GENERAL;
        checkBoardSituation[4][0] = RoleType.LOYAL_GENERAL;
        checkBoardSituation[4][1] = RoleType.LOYAL_GENERAL;
        checkBoardSituation[4][2] = RoleType.LOYAL_GENERAL;
        checkBoardSituation[4][3] = RoleType.BLANK;
    }

    public boolean success()
    {
        return checkBoardSituation[3][1] == RoleType.KING &&
                checkBoardSituation[3][2] == RoleType.KING &&
                checkBoardSituation[4][1] == RoleType.KING &&
                checkBoardSituation[4][2] == RoleType.KING
                ;
    }

    public static Situation copy(Situation situation)
    {
        Situation newSituation = new Situation();
        RoleType[][] roleTypes = situation.getCheckBoardSituation();
        RoleType[][] newRoleTypes = new RoleType[roleTypes.length][roleTypes[0].length];

        for (int i = 0; i < roleTypes.length; ++i)
        {
            for (int j = 0; j < roleTypes[0].length; ++j)
            {
                newRoleTypes[i][j] = roleTypes[i][j];
            }
        }

        newSituation.setCheckBoardSituation(newRoleTypes);
        return newSituation;
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof Situation))
        {
            return false;
        }

        Situation situation = (Situation) object;
        RoleType[][] roleTypes = situation.getCheckBoardSituation();

        int row = roleTypes.length;
        int col = roleTypes[0].length;

        if (row != this.checkBoardSituation.length || col != this.checkBoardSituation[0].length)
        {
            return false;
        }

        for (int i = 0; i < row; ++i)
        {
            for (int j = 0; j < col; ++j)
            {
                if (this.checkBoardSituation[i][j] != roleTypes[i][j])
                {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        for (int i = 0;  i < checkBoardSituation.length; ++i)
        {
            for (int j = 0; j < checkBoardSituation[0].length; ++j)
            {
                hash += checkBoardSituation[i][j].getHorizontalSize() + checkBoardSituation[i][j].getVerticalSize();
            }
        }

        return hash;
    }
}