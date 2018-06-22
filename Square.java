/*
 * Copyright (c)2018 sdkevin.com, Inc.  All rights reserved.
 *
 * Owner: sdkevin@
 */
package klotski;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author sdkevin@
 */
@AllArgsConstructor
@Data
public class Square
{
    int row;
    int col;

    @Override
    public boolean equals (Object o)
    {
        if (!(o instanceof Square))
        {
            return false;
        }

        return row == ((Square) o).getRow() &&
                col == ((Square) o).getCol();
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += row;
        hash *= col;

        return hash;
    }
}
