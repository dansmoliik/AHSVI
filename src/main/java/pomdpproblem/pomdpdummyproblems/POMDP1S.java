package pomdpproblem.pomdpdummyproblems;

import pomdpproblem.POMDPProblem;

import java.util.ArrayList;
import java.util.HashMap;

public class POMDP1S implements POMDPDummyProblemI {
    @Override
    public POMDPProblem load() {
        // preamble
        double discount = 0.75;


        ArrayList<String> stateNames = new ArrayList<>();
        HashMap<String, Integer> stateNameToIndex = new HashMap<>();
        stateNames.add("s");
        for (int i = 0; i < stateNames.size(); ++i) {
            stateNameToIndex.put(stateNames.get(i), i);
        }


        ArrayList<String> actionNames = new ArrayList<>();
        HashMap<String, Integer> actionNameToIndex = new HashMap<>();

        actionNames.add("a");
        for (int i = 0; i < actionNames.size(); ++i) {
            actionNameToIndex.put(actionNames.get(i), i);
        }


        ArrayList<String> observationNames = new ArrayList<>();
        HashMap<String, Integer> observationNameToIndex = new HashMap<>();

        observationNames.add("nothing");
        for (int i = 0; i < observationNames.size(); ++i) {
            observationNameToIndex.put(observationNames.get(i), i);
        }

        // pomdp body
        double[][][] actionProbabilities =
                new double[actionNames.size()][stateNames.size()][stateNames.size()];

        actionProbabilities[0][0][0] = 1.0;


        double[][][] observationProbabilities =
                new double[actionNames.size()][stateNames.size()][observationNames.size()];

        observationProbabilities[0][0][0] = 1.0;


        double[][][][] rewards =
                new double[actionNames.size()][stateNames.size()][stateNames.size()][observationNames.size()];

        rewards[0][0][0][0] = 1.0;


        double[] initBelief = new double[stateNames.size()];

        initBelief[0] = 1.0;

        return new POMDPProblem(stateNames, stateNameToIndex,
                actionNames, actionNameToIndex, actionProbabilities,
                observationNames, observationNameToIndex, observationProbabilities,
                rewards, discount, initBelief);
    }
}
