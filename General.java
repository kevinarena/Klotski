/*
 * Copyright (c)2018 sdkevin.com, Inc.  All rights reserved.
 *
 * Owner: sdkevin@
 */
package klotski;

/**
 * @author sdkevin@
 */
public class General extends RoleBase
{
    public General(Checkerboard checkerboard, String roleName, RoleType roleType)
    {
        this.checkerboard = checkerboard;
        this.roleName = roleName;
        this.roleType = roleType;
    }
}
