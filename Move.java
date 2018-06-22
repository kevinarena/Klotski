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
@Data
@AllArgsConstructor
public class Move
{
    private String name;
    private String moveDirection;

    public void print()
    {
        System.out.print(name + "#" + moveDirection);
    }
}
