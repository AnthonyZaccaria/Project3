import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Driver {
    public static void main(String[] args) {
        Random gen = new Random();
        int loc = gen.nextInt(7);
        Node n = run(6,loc);
        System.out.println("Solution: "+n+" value: "+ f(n));
        
    }

    /**
     * Runs the hill climbing method. Performs 10 random restarts before giving up
     * if a solution is not found, picks best node
     * @param size: size of puzzle flower
     * @param loc: location of fixed number
     * @return solution node or best node
     */
    public static Node run(int size,int loc){
        int count = 0;
        double[] values = new double[10];
        Node[] solutions = new Node[10];
        double minVal = 100000;
        while (true){
            count++;
            Node n1 = new Node(size,loc);
            System.out.println("value of initial node: "+f(n1));
            Node n2 = hill_climb(n1);
            values[count-1]=f(n2);
            solutions[count-1]=n2;
            System.out.println(n2+" value: "+values[count-1]);
            if (f(n2)==0){
                return n2;
            }
            //returns best solution 0 wasn't present
            if (count==10){
            int indexOf=0;//will contain index of minimum value
            for (int l=0;l<count;l++){
            if (values[count-1]<=minVal) {
                minVal = values[count-1];
                indexOf = count-1;
            }
        }
            return solutions[indexOf];
        }
        }
    }
    /**
     * Value function
     * @param n: node
     * @returns double that is the value of a node
     */
    public static double f(Node n){
        double val = 0;//value of node (will be mean absolute error of nums)
        int len = (n.getSize()/2);//length of sums array
        int[] sums = new int[len];
        int sum = 0;//sum of sums
        double mean = 0;//mean of sums
        int[] numsCopy = n.getNums();//not changing it at all so we can do this
        //gets sums of diagnols
        for (int i = 1;i<numsCopy.length;i+=2){
            sums[i%len] = numsCopy[0]+numsCopy[i]+numsCopy[i+1];
            sum+=sums[i%len];
        }
        mean = sum*1.0/len*1.0; 

        for (int i=0;i<sums.length;i++){
            double diff = 0.0;
            diff = sums[i]-mean;
            if (diff<0) diff *= -1.0;
            val += diff;            
        }
        val /= len*1.0;
        return val;
    }

    /**
     * gets children of a node
     * @param n: node that we are getting children of
     * @returns a list of nodes that is the children
     */
    public static List<Node> getChildren(Node n){
        List<Node> children = new ArrayList<>();
        int[] numsCopy = n.getNums();
        int size = n.getSize();
        //int count = 0;//will count number of children
        //boolean stop = false;
        for (int i = 0; i < size; i++) {
            //makes sure fixed number stays fixed
            if (i==n.getLoc()) continue;
            int[] newNums = numsCopy.clone();
            for (int j=i;j<size;j++){
                //makes sure fixed number stays fixed
                if (i==j || j==n.getLoc())continue;   
                int temp = newNums[i];
                newNums[i] = newNums[j];
                newNums[j] = temp;
                Node child = new Node(newNums,n.getLoc());
                if (!children.contains(child)){
                //System.out.println(child);
                    children.add(child);
                }
                } 
            }
        return children;    
        }

    /**
     * performs the hill climbing algorithm
     * @param start: starting node
     * @returns solution node (or no solution)
     */
    public static Node hill_climb(Node start){
        System.out.println("Start Loc: "+start.getLoc());
        Node current = start;
        double currentValue = f(current); //f returns the value of a node
        while (true) {
            //System.out.println("current: "+current+" value: "+f(current)+" loc: "+current.getLoc());
            List<Node> children = getChildren(current);
            double bestValue = currentValue;
            Node bestChild = null;
            //List<Double> childValues = new ArrayList<Double>(); // contains all the child values in a given iteration
            //gets the best value
            for (Node child : children) {
                //System.out.println("       child: "+child+" value: "+f(child)); //testing purposes
                double childValue = f(child);
                //childValues.add(childValue);
                if (childValue < bestValue) {
                    bestValue = childValue;
                    bestChild = child;
                }
            }
            System.out.println("\tBest Child Value: "+bestValue);
            //returns node if it is a solution or if no solution
            if (bestValue == 0&&bestChild!=null) return bestChild;
            else if (bestChild==null) return current;
            current = bestChild;
            currentValue = bestValue;
        }
    }

}


/* For the poster: 
public static Node hill_climb1(Node start){
            Node current = start;
            double currentValue = f(current);
            //f returns the value of a node
            while (true) {
                List<Node> children = getChildren(current);
                 //gets the children
                double bestValue = currentValue;
                Node bestChild = null;
                //gets the best value of child
                for (Node child : children) {
                    double childValue = f(child);
                    if (childValue < bestValue) {
                        //updates who best child is
                        bestValue = childValue; 
                        bestChild = child; }
                //returns node if it is a solution or if stuck
                if (bestValue == 0) return bestChild;
                //found global minimum
                else if (bestChild==null) return current;
                //no child was better than the parent
                current = bestChild;
                currentValue = bestValue;
                }
            }
        } */