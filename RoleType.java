/*
 * Copyright (c)2018 sdkevin.com, Inc.  All rights reserved.
 *
 * Owner: sdkevin@
 */
package klotski;

/**
 * @author sdkevin@
 */
public enum RoleType
{
    BLANK(1, 1),
    SOLDIER(1, 1),
    KING(2, 2),
    BRAVE_GENERAL(2, 1),
    LOYAL_GENERAL(1, 2),
    ;

    private int horizontalSize;
    private int verticalSize;

    RoleType(int horizontalSize, int verticalSize)
    {
        this.horizontalSize = horizontalSize;
        this.verticalSize = verticalSize;
    }

    public int getHorizontalSize()
    {
        return horizontalSize;
    }

    public int getVerticalSize()
    {
        return verticalSize;
    }
}
