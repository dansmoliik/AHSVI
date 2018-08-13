package AHSVI;

import POMDPProblem.POMDPDummyProblems.POMDPDummyProblems;
import ilog.concert.IloException;
import org.junit.Test;

import static org.junit.Assert.*;

public class AHSVITest {
    // generic tests
    public void testUBGteUB(double ub, double lb) {
        assertTrue("UB must be greater than or equal LB",
                ub >= lb);
    }

    public void testUBCloseToLB(double ub, double lb, double eps) {
        assertTrue("UB must be max epsilon from LB",
                ub - lb <= eps);
    }

    public HSVIAlgorithm testDummyPOMDP(String pomdpProblemName, double epsilon) {
        System.out.printf("* TESTING \"%s\" DUMMY POMDP PROBLEM\n", pomdpProblemName);
        HSVIAlgorithm hsviAlgorithm = new HSVIAlgorithm(new POMDPDummyProblems(pomdpProblemName).load(), epsilon);
        try {
            hsviAlgorithm.solve();
        } catch (IloException e) {
            e.printStackTrace();
        }
        testUBGteUB(hsviAlgorithm.getUBValueInInitBelief(), hsviAlgorithm.getLBValueInInitBelief());
        testUBCloseToLB(hsviAlgorithm.getUBValueInInitBelief(), hsviAlgorithm.getLBValueInInitBelief(), epsilon);

        return hsviAlgorithm;
    }

    @Test(timeout = 300)
    public void test1SPOMDP() {
        String pomdpProblemName = "1s";
        double epsilon = 0.000000001;
        testDummyPOMDP(pomdpProblemName, epsilon);
    }

    @Test(timeout = 300)
    public void test2SPOMDP() {
        String pomdpProblemName = "2s";
        double epsilon = 0.0000000001;
        testDummyPOMDP(pomdpProblemName, epsilon);
    }

    @Test(timeout = 1000)
    public void test1DPOMDP() {
        String pomdpProblemName = "1d";
        double epsilon = 0.00001;
        double expectedValueInInitBelief = 1.360922;
        HSVIAlgorithm hsviAlgorithm = testDummyPOMDP(pomdpProblemName, epsilon);
        assertEquals(expectedValueInInitBelief, hsviAlgorithm.getUBValueInInitBelief(), epsilon);
    }

    @Test(timeout = 300)
    public void testTigerPOMDP() {
        String pomdpProblemName = "tiger";
        double epsilon = 0.0000000000001;
        testDummyPOMDP(pomdpProblemName, epsilon);
    }
}
