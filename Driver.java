
public class Driver {
    public static void main(String[] args) {
        Node n1 = new Node(6,0);
        System.out.println(n1);
        System.out.println("value of initial node: "+f(n1));
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
     * @param n
     * @returns a list of nodes that is the children
     */
    public static Node[] getChildren(Node n){
        return null;
    }

    /**
     * performs the hill climbing algorithm
     * @param start: starting node
     * @returns solution node (or no solution)
     */
    public static Node hill_climb(Node start){
        
        return null;
    }

}
