import java.util.ArrayList;
import java.util.List;

public class BinaryTree{

    // Root node pointer. Will be null for an empty tree.
    private Node root;

    /* 
    --Node-- 
    The binary tree is built using this nested node class. 
    Each node stores one data element, and has left and right 
    sub-tree pointer which may be null. 
    The node is a "dumb" nested class -- we just use it for 
    storage; it does not have any methods. 
    */ 
    private static class Node{
        int data;
        Node left, right;

        Node(int newData){
            left = null;
            right = null;
            data = newData;
        }
    }

    //Creates an empty binary tree -- a null root pointer.
    public BinaryTree(){
        root = null;
    }

    //Returns true if the given target is in the binary tree. 
    //Uses a recursive helper. 
    public boolean lookup(int data){
        return lookup(root, data);
    }

    //Recursive lookup  -- given a node, recur down searching for the given data.
    private boolean lookup(Node node, int data){
        if(node  == null){
            return false;
        }
        
        if(data == node.data){
            return true;
        }else if(data < node.data){
            return lookup(node.left, data);
        }else {
            return lookup(node.right, data);
        }
    }

    //Inserts the given data into the binary tree.
    //Uses a recursive helper.
    public void insert(int data){
        root = insert(root, data);
    }

    //Recursive insert -- given a node pointer, recur down and 
    //insert the given data into the tree. Returns the new
    //node pointer (the standard way to communicate 
    //a changed pointer back to the caller). 
    private Node insert(Node node, int data){
        if(node == null){
            node = new Node(data);
        }else if(data <= node.data){
            node.left = insert(node.left, data);
        }else{
            node.right = insert(node.right, data);
        }
        //// in any case, return the new pointer to the caller
        return node;
    }


    // Build 123 using three pointer variables.
    public void build123a(){
        root = new Node(2);
        Node lChild = new Node(1);
        Node rChild = new Node(3);
        root.left = lChild;
        root.right = rChild;
    }

    // Build 123 using only one pointer variable.
    public void build123b(){
        root = new Node(2);
        root.left = new Node(1);
        root.right = new Node(3);
    }

    // Build 123 by calling insert() three times. 
    // Note that the '2' must be inserted first.
    public void build123c(){
        root = null;
        //root = insert(root, 2);
        //root = insert(root, 1);
        //root = insert(root, 3);
        insert(2);
        insert(1);
        insert(3);

    }

    /*
    Given a binary search tree (aka an "ordered binary tree"), 
    iterate over the nodes to print them out in increasing order. 
    This is known as an "inorder" traversal of the tree.
    */
    // Prints the node values in the "inorder" order.
    // Uses a recursive helper to do the traversal. 
    public void printTree(){
        printTree(root);
        System.out.println();
    }

    private void printTree(Node node){
        if (node == null) return;
        // left, node itself, right
        printTree(node.left);
        System.out.println(node.data + " ");
        printTree(node.right);
    }

    /*
    Given a binary tree, print out the nodes of the tree according to a bottom-up "postorder" traversal 
    -- both subtrees of a node are printed out completely before the node itself is printed, 
    and each left subtree is printed before the right subtree. 
    This is the sort of  bottom-up traversal that would be used, for example, 
    to evaluate an expression tree where a node is an operation like '+' and its subtrees are, 
    recursively, the two subexpressions for the '+'.
    */
    //Prints the node values in the "postorder" order.
    //Uses a recursive helper to do the traversal.
    public void printPostOrder(){
        printPostOrder(root);
        System.out.println();
    }

    private void printPostOrder(Node node){
        if(node == null) return;
        printPostOrder(node.left);
        printPostOrder(node.right);
        System.out.println(node.data + " ");
    }

    //Returns the number of nodes in the tree.
    //Uses a recursive helper that recurs down the tree and counts the nodes.
    public int size(){
        return size(root);
    }

    private int size(Node node){
        if(node == null) return 0;
        else{
            return (size(node.left) + 1 + size(node.right));
        }
    }

    //Returns the max root-to-leaf depth of the tree.
    //Uses a recursive helper that recurs down to find the max depth.
    public int maxDepth(){
        return maxDepth(root);
    }

    private int maxDepth(Node node){
        if(node == null) return 0;
        else{
            return (Math.max(maxDepth(node.left), maxDepth(node.right)) + 1);
        }
    }

    //Returns the min value in a non-empty binary search tree.
    //Uses a helper method that iterates to the left to find the min value.
    public int minValue(){
        return minValue(root);
    }

    //Finds the min value in a non-empty binary search tree. 
    private int minValue(Node node){
        Node current = node;
        while(current.left != null){
            current = current.left;
        }
        return current.data;
    }

    //Given a tree and a sum, returns true if there is a path from the root
    //down to a leaf, such that adding up all the values along the path
    //equals the given sum.
    //Strategy: subtract the node value from the sum when recurring down, 
    //and check to see if the sum is 0 when you run out of tree. 
    public boolean hasPathSum(int sum){
        return hasPathSum(root, sum);
    }

    private boolean hasPathSumMy(Node node, int sum){
        if(node == null){
            return false;
        }

        System.out.printf("call hasPathSum(%d, %d)\n", node.data, sum);
        if(node.left == null && node.right == null){
            return (sum == node.data);
        }else{
            return (hasPathSumMy(node.left, sum-node.data) || hasPathSumMy(node.right, sum-node.data));
        }

    }

    private boolean hasPathSum(Node node, int sum){
        //return true if we run out of tree and sum==0
        if(node == null){
            return (sum == 0);
        }
        //otherwise check both subtrees 
        else{
            int subSum = sum - node.data;
            return (hasPathSum(node.left, subSum) || hasPathSum(node.right, subSum));
        }
    }

    // print all leaves of the tree
    public void printLeaves(){
        printLeaves(root);
    }

    private void printLeaves(Node node){
        if(node == null){
            return;
        }
        if(node.left == null & node.right == null){
            System.out.println(node.data);
        }else{
            printLeaves(node.left);
            printLeaves(node.right);
        }
    }

    //Given a binary tree, prints out all of its root-to-leaf 
    //paths, one per line. Uses a recursive helper to do the work. 
    public void printPaths(){
        int[] path = new int[100];
        printPaths(root, path, 0);
    }

    //Recursive printPaths helper -- given a node, and an array containing
    //the path from the root node up to but not including this node, 
    //prints out all the root-leaf paths. 
    private void printPaths(Node node, int[] path, int pathLen){
        if(node == null) return;

        //
        String s = "[ ";
        for(int i = 0; i < pathLen; i++) s += (path[i] + " ");
        s += "]";
        System.out.printf("printPaths(%d, %s, %d)\n", node.data, s, pathLen);

        // append this node to the path array
        path[pathLen] = node.data;
        pathLen++;

        // it's a leaf, so print the path that led to here 
        if(node.left == null && node.right == null){
            printArray(path, pathLen);
        }else{
            printPaths(node.left, path, pathLen);
            printPaths(node.right, path, pathLen);
        }
    }

    //Utility that prints ints from an array on one line. 
    private void printArray(int[] path, int pathLen){
        for(int i = 0; i < pathLen; i++){
            System.out.print(path[i] + " ");
        }
        System.out.println();
    }

    /*
    Changes the tree into its mirror image.
    So the tree... 
          4 
         / \ 
        2   5
       / \ 
      1   3
    is changed to
           4
          / \
         5   2
            / \ 
           3   1
    Uses a recursive helper that recurs over the tree, 
    swapping the left/right pointers.
    */
    public void mirror(){
        mirror(root);
    }

    private void mirror(Node node){
        if(node == null) return;
        // do the sub-trees
        mirror(node.left);
        mirror(node.right);
        // swap the left/right pointers
        Node tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }

    /*
    For each node in a binary search tree, create a new duplicate node, 
    and insert the duplicate as the left child of the original node. 
    The resulting tree should still be a binary search tree.
    Changes the tree by inserting a duplicate node on each nodes's .left.
    So the tree... 
        2 
       / \ 
      1   3
    is changed to...
           2 
          / \ 
         2   3 
        /   / 
       1   3 
      / 
     1
    Uses a recursive helper to recur over the tree and insert the duplicates.
    */
    public void doubleTree(){
        doubleTree(root);
    }

    private void doubleTree(Node node){
        if(node != null){
            doubleTree(node.left);
            Node oldLeft = node.left;
            Node newNode = new Node(node.data);
            newNode.left = oldLeft;
            newNode.right = null;
            node.left = newNode;
            doubleTree(node.right);
        }
    }

    //Compares the receiver to another tree to see if they are structurally identical. 
    public boolean sameTree(BinaryTree other){
        return sameTree(root, other.root);
    }

    //Recursive helper -- recurs down two trees in parallel, checking to see if they are identical. 
    private boolean sameTree(Node a, Node b){
        //both empty -> true
        //both non-empty -> compare them
        //one empty, one not -> false
        if(a==null && b==null){
            return true;
        }else if (a!=null && b!=null && a.data==b.data){ 
            return (sameTree(a.left, b.left) && sameTree(a.right, b.right));
        }else{
            return false;
        }
    }

    public int countTrees(int numKeys){
        
    }

    public void printByLevel(){
        if(root == null) return;
        LinkedQueue<Node> queue = new LinkedQueue<Node>();
        queue.enqueue(root);
        int num = 1;
        int newNum = 0;
        while(!queue.isEmpty()){
            for(int i = 0; i < num; i++){
                Node node = queue.dequeue();
                System.out.print(node.data + " ");
                if(node.left != null){
                    queue.enqueue(node.left);
                    newNum++;
                }
                if(node.right != null){
                    queue.enqueue(node.right);
                    newNum++;
                }
            }
            System.out.println();
            num = newNum;
            newNum = 0;
        }
    }

    private static Node[] nodeArray;
    private static final int NODE_NUM = 20;
    private static void initNodeArray(){
        nodeArray = new Node[NODE_NUM];
        for(int i = 0; i < NODE_NUM; i++){
            nodeArray[i] = new Node(i);
            nodeArray[i].left = null;
            nodeArray[i].right = null;
        }
    }

    public static void main(String[] args){
        BinaryTree t1 = new BinaryTree();
        t1.build123a();
        t1.printTree();
        t1.printPretty();
        //t1.doubleTree();
        //t1.printPretty();

        BinaryTree t2 = new BinaryTree();
        t2.build123b();
        t2.printTree();
        System.out.println("t2 == t1 : " + t2.sameTree(t1));

        BinaryTree t3 = new BinaryTree();
        t3.build123c();
        t3.printPostOrder();
        System.out.println("size of t3 = " + t3.size());
        System.out.println("maxDepth of t3 = " + t3.maxDepth());
        System.out.println("minValue of t3 = " + t3.minValue());

        initNodeArray();
        BinaryTree t4 = new BinaryTree();
        t4.root = nodeArray[5];
        nodeArray[5].left = nodeArray[4];
        nodeArray[5].right = nodeArray[8];
        nodeArray[4].left = nodeArray[11];
        nodeArray[8].left = nodeArray[13];
        nodeArray[8].right = nodeArray[6];
        nodeArray[11].left = nodeArray[7];
        nodeArray[11].right = nodeArray[2];
        nodeArray[6].right = nodeArray[1];
        t4.printTree();
        System.out.println("t4.hasPathSum(27) = " + t4.hasPathSum(27));
        System.out.println("t4.hasPathSum(22) = " + t4.hasPathSum(22));
        System.out.println("t4.hasPathSum(26) = " + t4.hasPathSum(26));
        System.out.println("t4.hasPathSum(18) = " + t4.hasPathSum(18));
        System.out.println("t4.hasPathSum(9) = " + t4.hasPathSum(9));
        System.out.println("t4 leaves:");
        t4.printLeaves();
        System.out.println("paths from root to leaf:");
        t4.printPaths();
        System.out.println("print by level:");
        t4.printByLevel();
        t4.printPretty2();
        t4.mirror();
        t4.printPretty2();
    }


    private static int WIDTH = 2;
    public void printPretty(){
        int maxLevel = maxDepth();
        List<Node> nodes = new ArrayList<Node>();
        nodes.add(root);
        printPretty(nodes, 1, maxLevel);
    }

    private void printPretty(List<Node> nodes, int level, int maxLevel){
        if(nodes.isEmpty() || isAllElementNull(nodes)) return;
        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;
        printWhiteSpaces(firstSpaces*WIDTH);
        List<Node> newNodes = new ArrayList<Node>();
        for(Node node : nodes){
            if(node != null){
                System.out.printf("%2d", node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            }else{
                printWhiteSpaces(WIDTH);
                newNodes.add(null);
                newNodes.add(null);
            }
            printWhiteSpaces(betweenSpaces*WIDTH);
        }
        System.out.println();
        for (int i = 1; i <= endgeLines; i++){
            for (int j = 0; j < nodes.size(); j++){
                printWhiteSpaces((firstSpaces - i)*WIDTH);
                if (nodes.get(j) == null) {
                    printWhiteSpaces((endgeLines + endgeLines + i + 1)*WIDTH);
                    continue;
                }
                if (nodes.get(j).left != null) System.out.print(" /");
                else printWhiteSpaces(WIDTH);
                printWhiteSpaces((i + i - 1)*WIDTH);
                if (nodes.get(j).right != null) System.out.print(" \\");
                else printWhiteSpaces(WIDTH);
                printWhiteSpaces((endgeLines + endgeLines - i)*WIDTH);
            }
            System.out.println();
        }
        printPretty(newNodes, level+1, maxLevel);
    }

    private static void printWhiteSpaces(int count){
        for(int i = 0; i < count; i++){
            System.out.print(" ");
        }
    }

    private static <T> boolean isAllElementNull(List<T> list){
        for(Object obj : list){
            if(obj != null) return false;
        }
        return true;
    }


    private static String UP = "\u2514\u2500\u2500 ";
    private static String DOWN = "\u250c\u2500\u2500 ";
    private static String VERT = "\u2502   ";
    private static String EMPT = "    ";
    private void printPretty2(Node node, String prefix, boolean isTail){
        if(node.right != null){
            printPretty2(node.right, prefix + (isTail ? VERT : EMPT), false);
        }
        System.out.println(prefix + (isTail ? UP : DOWN) + node.data);
        if(node.left != null){
            printPretty2(node.left, prefix + (isTail ? EMPT : VERT), true);
        }
    }

    public void printPretty2(){
        if(root != null){
            printPretty2(root, "", true);
        }
    }
}
