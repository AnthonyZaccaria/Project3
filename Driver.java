import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Driver {
    public static void main(String[] args) {
        Random gen = new Random();
        int loc = gen.nextInt(6);
        Node n = run(6,loc);
        System.out.println(n+" value: "+ f(n));
        
    }

    /**
     * Runs the hill climbing method. Performs 10 random restarts before giving up
     * if a solution is not found
     * @param size: size of puzzle flower
     * @param loc: location of fixed number
     * @return solution node
     */
    public static Node run(int size,int loc){
        int count = 0;
        while (true){
            count++;
            Node n1 = new Node(size,loc);
            System.out.println("value of initial node: "+f(n1));
            Node n2 = hill_climb(n1);
            System.out.println(n2+" value: "+f(n2));
            if (count==10||f(n2)==0){
                return n2;
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
        int count = 0;//will count number of children
        boolean stop = false;
        //int i = 0;//index for while loop
        int limit=0;
        for (int k=1;k<size-1;k++){
            limit+=k;
        }
        for (int i = 0; i < size; i++) 
        {
            //makes sure fixed number stays fixed
            if (i==n.getLoc()) continue;
            int[] newNums = numsCopy.clone();
            for (int j=i;j<size;j++){
                //makes sure fixed number stays fixed
                if (i==j || j==n.getLoc()) continue;
                int temp = newNums[i];
                newNums[i] = newNums[(i + 1) % size];
                newNums[(i + 1) % size] = temp;
                Node child = new Node(newNums,n.getLoc());
                if (children.contains(child))
                    children.add(child);
                }
                
            //i ++;
            
            }
        return children;    
        }
    
    

    /**
     * performs the hill climbing algorithm
     * @param start: starting node
     * @returns solution node (or no solution)
     */
    public static Node hill_climb(Node start){
        Node current = start;
        double currentValue = f(current);
        boolean stop = false;
        while (true) {
            System.out.println("current: "+current+" value: "+f(current));
            List<Node> children = getChildren(current);
            double bestValue = currentValue;
            Node bestChild = null;
            List<Double> childValues = new ArrayList<Double>(); // contains all the child values in a given interation
            //gets the best value
            for (Node child : children) {
                System.out.println("       child: "+child+" value: "+f(child));
                double childValue = f(child);
                childValues.add(childValue);
                if (childValue < bestValue) {
                    bestValue = childValue;
                    bestChild = child;
                }
            }
            System.out.println(bestValue);
            
            //returns node if it is a solution or if no solution
            if (bestValue == 0) return bestChild;
            else if (bestChild==null) return current;
            current = bestChild;
            currentValue = bestValue;
        }
        //only reachable if stuck

    }

}
