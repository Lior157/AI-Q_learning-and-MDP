import java.util.List;
import java.util.Random;

public class QValueUpdate {

    public static double newQValue(double oldQValue, double reward, double nextStatesMaxQ, double discountFactor, double learningRate) {
        // TODO: Replace this with the Q-learning update rule
        double newQ = (1-learningRate)*oldQValue+learningRate*(reward+discountFactor*nextStatesMaxQ);
        return newQ;

    }

    public static CoffeeWorldAction chooseAction(CoffeeWorldState state, double epsilon, CoffeeEnvironment environment, AbstractQLearningAgent agent) {

        // TODO: Explore: choose a random action with epsilon probability
        Random rand = new Random();
        boolean exploration = epsilon <= rand.nextDouble() ;
        List<CoffeeWorldAction>  ls = environment.getLegalActions(state);
        CoffeeWorldAction randomCoffeeAction = ls.get((int)(rand.nextDouble()*ls.size()));
        // TODO: Exploit: get the best (highest Q value) action legal in this state
        double maxQ = 0;
        double nextActionQ;
        CoffeeWorldAction bestAction = environment.getLegalActions(state).get(0);
        for(CoffeeWorldAction nextAction : environment.getLegalActions(state)){
            nextActionQ = agent.getQValue(state,nextAction);
            if(maxQ<nextActionQ) {
                maxQ = nextActionQ;
                bestAction = nextAction ;
            }
        }
        // TODO: replace with your chosen action
        if(exploration) return randomCoffeeAction;
        return bestAction;

    }

}
