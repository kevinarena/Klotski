/*
 * Copyright (c)2018 sdkevin.com, Inc.  All rights reserved.
 *
 * Owner: sdkevin@
 */
package klotski;

/**
 * @author sdkevin@
 */
public class King extends RoleBase
{
    public King(Checkerboard checkerboard, String roleName)
    {
        this.checkerboard = checkerboard;
        this.roleName = roleName;
        this.roleType = RoleType.KING;
    }
}
