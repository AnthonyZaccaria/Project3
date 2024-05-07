import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class Node {
    private int size;//either 6, 10, or 14
    private int[] nums;//going to be length size+1
    private int loc; //location of the biggest number
    private int biggest;
    //need to represen flower into computer
    // center number is 0
    // 1 is opposite of 2, 3 is opposite of 4, etc...
    // So 1+0+2 is a diagnol, 3+0+4 is a diagnol, etc...
    
    // constructor to generate random start node;
    public Node(int size,int loc){
        this.nums = new int[size+1];
        this.size=size;
        this.biggest = size+1;
        this.nums[loc] = biggest;
        //following needed for random restart
        ArrayList<Integer> numbers = new ArrayList<Integer>();//will be numbers 1 through size
        for (int i=0;i<size;i++) numbers.add(i+1); 
        for (int i=0;i<size;i++){
            if (i==loc) continue;
            else{
                Random gen = new Random();
                int rnum = numbers.remove(gen.nextInt(numbers.size()));//random integer
                //the remove makes sure there is no repeats
                this.nums[i]=rnum;
            }
            nums[size]=numbers.get(0);
        }
    }
// Constuctor which inputs the numbers into a node 
    public Node(int[] nums,int loc){
        this.size=nums.length-1;
        this.nums = new int[this.size+1];
        for (int i=0;i<this.size+1;i++){
            this.nums[i] = nums[i];
        }
        this.biggest = this.size+1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (!Arrays.equals(nums, other.nums))
            return false;
        return true;
    }
    //getters
    public int getSize() {
        return size;
    }
    public int[] getNums() {
        return nums;
    }
    public int getLoc() {
        return loc;
    }
    public int getBiggest() {
        return biggest;
    }

    //toString methos
    @Override
    public String toString() {
        return "Node "+Arrays.toString(nums);
    }

    
    






}