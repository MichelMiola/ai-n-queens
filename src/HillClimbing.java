import java.util.Arrays;

/**
 * Created by mrsfy on 05-Jun-17.
 */
public class HillClimbing {

    // make a move for hill climbing
    public int[] firstChoiceHillClimbing(int n, int maxNumOfIterations) {
        int[] r = SolverUtils.generateRandomState(n);
        int costToBeat = SolverUtils.getHeuristicCost(r);

        int x = 0;
        // terminate when it reaches max num of iterations or problem is solved.
        while (x < maxNumOfIterations && costToBeat > 0) {

            boolean flag = true;
            int tempCostToBeat = costToBeat;
            for (int col = 0; col < n && flag; col++) {

                for (int row = 0; row < n; row++) {
                    // we do not need to evaluate because we already know current cost by costToBeat.
                    if (row == r[col])
                        continue;

                    // init new copy
                    int[] rc = Arrays.copyOf(r, n);
                    rc[col] = row;
                    int cost = SolverUtils.getHeuristicCost(rc);
                    if (costToBeat > cost) {
                        r[col] = row;
                        costToBeat = cost;
                        flag = false;
                        break;
                    }
                }
                x++;
            }

            // if it gets stuck at local maxima
            if (tempCostToBeat == costToBeat)
                r = SolverUtils.generateRandomState(n);

        }
        System.out.println("Rainhas "+ n + " iterações para resolução Hill Climbing: " + x);
        return costToBeat == 0 ? r : null; // return solution if solved
    }


    public int[] solve(int n, int maxNumOfIterations) {

        return firstChoiceHillClimbing(n, maxNumOfIterations);
    }

}
