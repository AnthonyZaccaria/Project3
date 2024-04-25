public class Driver {
    public static void main(String[] args) {
        Node n1 = new Node(6);
    }

    /**
     * Value function
     * @param n: node
     * @returns int that is the value
     */
    public static int f(Node n){
        int val = 0;
        int len = (n.getSize()/2);//length of sums array
        int[] sums = new int[len];
        for (int i = 1;i<n.getNums().length;i+=2){
            sums[i%len] = n.getNums()[0]+n.getNums()[i]+n.getNums()[i+1];
        }


        return val;
    }

    public static int[] getChildren(Node n){
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
