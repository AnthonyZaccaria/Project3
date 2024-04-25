public class Node {
    private int size;//either 6, 10, or 14
    private int[] nums;//going to be length size+1
    private int loc; //location of the biggest number
    private int biggest;
    //need to represen flower into computer
    // center number is 0
    // 1 is opposite of 2, 3 is opposite of 4, etc...
    // So 1+0+2 is a diagnol, 3+0+4 is a diagnol, etc...
    public Node(int size){
        nums = new int[size+1];
        biggest = size+1;
        nums[loc] = biggest;
    }








}