/*
 * Copyright (c)2018 sdkevin.com, Inc.  All rights reserved.
 *
 * Owner: sdkevin@
 */
package klotski;

import java.util.List;

/**
 * @author sdkevin@
 */
public interface IRole
{
    void move(List<Solution> solutions, Solution solution, List<Situation> situationList);
    void moveUp(List<Solution> solutions, Solution solution, List<Situation> situationList);
    void moveDown(List<Solution> solutions, Solution solution, List<Situation> situationList);
    void moveLeft(List<Solution> solutions, Solution solution, List<Situation> situationList);
    void moveRight(List<Solution> solutions, Solution solution, List<Situation> situationList);

    boolean canMoveUp(Situation situation);
    boolean canMoveDown(Situation situation);
    boolean canMoveLeft(Situation situation);
    boolean canMoveRight(Situation situation);
}
