import java.util.*;
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

    //traverse pre-order recursively
    public void printPreOrder(){
        printPreOrder(root);
        System.out.println();
    }

    private void printPreOrder(Node node){
        if(node != null){
            System.out.println(node.data + " ");
            printPreOrder(node.left);
            printPreOrder(node.right);
        }
    }

    //traverse pre-order iteratively
    public void printPreOrderIter(){
        Stack<Node> s = new Stack<Node>();
        Node current = root;
        do{
            while(current != null){
                System.out.print(current.data + " ");
                s.push(current);
                current = current.left;
            }
            current = s.pop().right;
        }while(!s.isEmpty() || current!=null);
        System.out.println();
    }

    //1) Create an empty stack nodeStack and push root node to stack.
    //2) Do following while nodeStack is not empty.
    //….a) Pop an item from stack and print it.
    //….b) Push right child of popped item to stack
    //….c) Push left child of popped item to stack
    //Right child is pushed before left child to make sure that left subtree is processed first.
    public void printPreOrderIter2(){
        Stack<Node> s = new Stack<Node>();
        Node current = root;
        s.push(current);
        while(!s.isEmpty()){
            current = s.pop();
            System.out.print(current.data + " ");
            if(current.right!=null) s.push(current.right);
            if(current.left!=null) s.push(current.left);
        }
        System.out.println();
    }

    //Excessive recursive function calls may cause memory to run out of stack space and extra overhead. 
    //Since the depth of a balanced binary search tree is about lg(n), you might not worry about running out of stack space, 
    //even when you have a million of elements. But what if the tree is not balanced? Then you are asking for trouble, 
    //because in the worst case the height of the tree may go up to n. If that is the case, stack space will eventually 
    //run out and your program will crash.
    //Given a binary search tree, print the elements in-order iteratively without using recursion.
	//First, the current pointer is initialized to the root. Keep traversing to its left child while pushing visited nodes 
	//onto the stack. When you reach a NULL node (ie, you’ve reached a leaf node), you would pop off an element from the stack 
	//and set it to current. Now is the time to print current’s value. Then, current is set to its right child and repeat the 
	//process again. When the stack is empty, this means you’re done printing.
    public void printInOrderIter(){
        Stack<Node> s = new Stack<Node>();
        Node current = root;
		/*
        do{
            while(current!=null){
                s.push(current);
                current = current.left;
            }
            current = s.pop();
            System.out.print(current.data + " ");
            current = current.right;
        }while(!s.isEmpty() || current!=null);
		*/
		//The last traversed node must not have a right child.
		while(!s.isEmpty() || current!=null){
			if(current != null){
				s.push(current);
				current = current.left;
			}else{
				current = s.pop();
				System.out.print(current.data + " ");
				current = current.right;
			}
		}
        System.out.println();
    }
	
	/*
	Using Morris Traversal, we can traverse the tree without using stack and recursion. The idea of Morris Traversal is 
	based on Threaded Binary Tree. In this traversal, we first create links to Inorder successor and print the data using 
	these links, and finally revert the changes to restore original tree.
	1. Initialize current as root 
	2. While current is not NULL
	   If current does not have left child
	     a) Print current’s data
		 b) Go to the right, i.e., current = current->right
	   Else
		 a) Make current as right child of the rightmost node in current's left subtree
		 b) Go to this left child, i.e., current = current->left
	Although the tree is modified through the traversal, it is reverted back to its original shape after the completion. 
	Unlike Stack based traversal, no extra space is required for this traversal.

	*/
	public void printInOrderMorris(){
		Node curr = root;
		Node prev = null;
		while(curr != null){
			if(curr.left == null){
				System.out.print(curr.data + " ");
				curr = curr.right;
			}else{
				//Find the inorder predecessor of current
				prev = curr.left;
				while(prev.right != null && prev.right!=curr) prev = prev.right;
				//Make current as right child of its inorder predecessor
				if(prev.right == null){
					prev.right = curr;
					curr = curr.left;
				}
				//Revert the changes made in if part to restore the original tree i.e., fix the right child of predecssor
				else{
					prev.right = null;
					System.out.print(curr.data + " ");
					curr = curr.right;
				}
			}
		}
		System.out.println();
	}

    /*
    Write a recursive function treeToList(Node root) that takes an ordered binary tree and rearranges the internal pointers 
    to make a circular doubly linked list out of the tree nodes. The "previous" pointers should be stored in the "small" field 
    and the "next" pointers should be stored in the "large" field. The list should be arranged so that the nodes are in increasing order. 
    Return the head pointer to the new list. The operation can be done in O(n) time -- essentially operating on each node once.
    */
    public Node treeToList(){
        Node curr;
        Node head = null;
        Node tail = null;
        curr = root;
        while(curr != null){
            if(curr.left == null) break;
            else{
                head = curr.left;
                while(head.right != null && head.right != curr) head=head.right;
                if(head.right == null){
                    head.right = curr;
                    curr = curr.left;
                }
            }
        }
        System.out.println("head = " + head.data);
        curr = root;
        while(curr != null){
            if(curr.right == null) break;
            else{
                tail = curr.right;
                while(tail.left != null && tail.left != curr) tail=tail.left;
                if(tail.left == null){
                    tail.left = curr;
                    curr = curr.right;
                }
            }
        }
        System.out.println("tail = " + tail.data);
        for(curr=head; curr!=root; curr=curr.right) curr.right.left = curr;
        for(curr=tail; curr!=root; curr=curr.left) curr.left.right = curr;
        tail.right = head;
        head.left = tail;
        return head;
    }

    //helper function -- given two list nodes, join them together so the second immediately follow the first.
    //Sets the .next of the first and the .previous of the second.
    public static void join(Node a, Node b){
        a.right = b;
        b.left = a;
    }

    //helper function -- given two circular doubly linked lists, append them and return the new list.
    public static Node append(Node a, Node b){
        if(a == null) return b;
        if(b == null) return a;
        Node aLast = a.left;
        Node bLast = b.left;
        join(aLast, b);
        join(bLast, a);
        return a;
    }
    
    public Node treeToListRecur(){
        return treeToListRecur(root);
    }

    //Given an ordered binary tree, recursively change it into a circular doubly linked list which is returned.
    private Node treeToListRecur(Node node){
        // base case: empty tree -> empty list
        if(node == null) return null;
        // Recursively do the subtrees (leap of faith!)
        Node aList = treeToListRecur(node.left);
        Node bList = treeToListRecur(node.right);

        // Make the single root node into a list length-1 in preparation for the appending
        node.right = node;
        node.left = node;

        //At this point we have three lists, and it's just a matter of appending them together in the right order (aList, root, bList)
        aList = append(aList, node);
        aList = append(aList, bList);
        
        return aList;
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

	//We use a prev variable to keep track of the previously-traversed node. Let’s assume curr is the current 
	//node that’s on top of the stack. When prev is curr‘s parent, we are traversing down the tree. In this case, 
	//we try to traverse to curr‘s left child if available (ie, push left child to the stack). If it is not available, 
	//we look at curr‘s right child. If both left and right child do not exist (ie, curr is a leaf node), 
	//we print curr‘s value and pop it off the stack.
	//If prev is curr‘s left child, we are traversing up the tree from the left. We look at curr‘s right child. 
	//If it is available, then traverse down the right child (ie, push right child to the stack), otherwise print 
	//curr‘s value and pop it off the stack.
	//If prev is curr‘s right child, we are traversing up the tree from the right. In this case, 
	//we print curr‘s value and pop it off the stack.
	public void printPostOrderIter(){
		if(root == null) return;
		Stack<Node> s = new Stack<Node>();
		s.push(root);
		Node prev = null;
		Node curr = null;
		while(!s.isEmpty()){
			curr = s.peek();
			/*
			//we are traversing down the tree
			if(prev==null || prev.left==curr || prev.right==curr){
				if(curr.left != null){
					s.push(curr.left);
				}else if(curr.right != null){
					s.push(curr.right);
				}else{
					System.out.print(curr.data + " ");
					s.pop();
				}
			}
			//we are traversing up the tree from the left
			else if(curr.left == prev){
				if(curr.right != null){
					s.push(curr.right);
				}else{
					System.out.print(curr.data + " ");
					s.pop();
				}
			}
			//we are traversing up the tree from the right
			else if(curr.right == prev){
				System.out.print(curr.data + " ");
				s.pop();
			}
			*/
			if(prev==null || prev.left==curr || prev.right==curr){
				if(curr.left != null) s.push(curr.left);
				else if(curr.right != null) s.push(curr.right);
			}else if(curr.left == prev){
				if(curr.right != null) s.push(curr.right);
			}else{
				System.out.print(curr.data + " ");
				s.pop();
			}
			prev = curr;
		}
		System.out.println();
	}

	//An alternative solution is to use two stacks. in fact it is doing a reversed pre-order traversal. That is, 
	//the order of traversal is a node, then its right child followed by its left child. This yields post-order traversal in 
	//reversed order. Using a second stack, we could reverse it back to the correct order.
	//(1)Push the root node to the first stack.
	//(2)Pop a node from the first stack, and push it to the second stack.
	//(3)Then push its left child followed by its right child to the first stack.
	//(4)Repeat step 2) and 3) until the first stack is empty.
	//(5)Once done, the second stack would have all the nodes ready to be traversed in post-order. 
	//...Pop off the nodes from the second stack one by one and you’re done.
	/*
	The two-stack solution takes up more space compared to the first solution using one stack. In fact, the first solution has 
	a space complexity of O(h), where h is the maximum height of the tree. The two-stack solution however, has a space 
	complexity of O(n), where n is the total number of nodes.
	*/
	public void printPostOrderIter2(){
		if(root == null) return;
		Stack<Node> s = new Stack<Node>();
		Stack<Node> output = new Stack<Node>();
		s.push(root);
		Node curr;
		while(!s.isEmpty()){
			curr = s.peek();
			output.push(curr);
			s.pop();
			if(curr.left != null) s.push(curr.left);
			if(curr.right != null) s.push(curr.right);
		}
		while(!output.isEmpty()){
			System.out.print(output.pop().data + " ");
		}
		System.out.println();
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

    public int maxValue(){
	return maxValue(root);
    }

    private int maxValue(Node node){
	Node current = node;
	while(current.right != null){
	    current = current.right;
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
    
    //For the key values 1...numKeys, how many structurally unique
    //binary search trees are possible that store those keys?
    //Strategy: consider that each value could be the root. 
    //Recursively find the size of the left and right subtrees.
    public static int countTrees2(int numKeys){
        if(numKeys <= 1) return 1;
        else{
            //there will be one value at the root, with whatever remains
            //on the left and right each forming their own subtrees. 
            //Iterate through all the values that could be the root...
            int sum = 0;
            int left, right, root;
            for(root = 1; root <= numKeys; root++){
                left = countTrees2(root - 1);
                right = countTrees2(numKeys-root);
                // number of possible trees with this root == left*right 
                sum += left*right;
            }
            return sum;
        }
    }

    public static void countTrees(int numKeys){
        int[] counts = new int[numKeys+1];
        counts[0] = 1;
        counts[1] = 1;
        for(int i = 2; i <= numKeys; i++){
            int count = 0;
            for(int j = 0; j < i; j++){
                count += counts[j]*counts[i-1-j];
            }
            counts[i] = count;
            System.out.println("countTrees " + i + " : " + count);
        }
    }

    //Tests if a tree meets the conditions to be a binary search tree (BST). 
    public boolean isBST1(){
        return isBST1(root);
    }

    //Recursive helper -- checks if a tree is a BST using minValue() and maxValue() (not efficient). 
    private boolean isBST1(Node node){
        if(node == null) return true;
        else if(node.left == null && node.right == null) return true;
        else if(node.left != null && node.right == null) return ((node.data>=maxValue(node.left) && isBST1(node.left)));
        else if(node.left == null && node.right != null) return ((node.data<=minValue(node.right) && isBST1(node.right)));
        else return (node.data>=maxValue(node.left) && isBST1(node.left) && node.data<=minValue(node.right) && isBST1(node.right));
    }
    
    public boolean isBST2(){
        return isBST2(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    //Efficient BST helper -- Given a node, and min and max values,
    //recurs down the tree to verify that it is a BST, and that all
    //its nodes are within the min..max range. Works in O(n) time -- 
    //visits each node only once.
    private boolean isBST2(Node node, int min, int max){
        if(node == null) return true;
        else{
            if(node.data>=min && node.data<=max){
                // left should be in range  min...node.data
                // right should be in range node.data(+1)..max
                return (isBST2(node.left, min, node.data) && isBST2(node.right, node.data, max));
            }else{
                return false;
            }
        }
    }

    //using Breadth First Search (BFS)
    public void printByLevel(){
        if(root == null) return;
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        int num = 1;
        int newNum = 0;
        while(!queue.isEmpty()){
            for(int i = 0; i < num; i++){
                Node node = queue.remove();
                System.out.print(node.data + " ");
                if(node.left != null){
                    queue.add(node.left);
                    newNum++;
                }
                if(node.right != null){
                    queue.add(node.right);
                    newNum++;
                }
            }
            System.out.println();
            num = newNum;
            newNum = 0;
        }
    }

    //using Depth First Search (DFS)
    /*
    If you look carefully, you will notice that the DFS solution traverses the same node multiple times. 
    Since BFS traverses each node exactly one time, BFS is much more efficient than DFS.
    Although the DFS solution traverse the same node multiple times, it is not another order slower than the BFS solution. 
    Here is the proof that the DFS solution above runs in O(N) time, where N is the number of nodes in the binary tree and 
    we assume that the binary tree is balanced.
    We first compute the complexity of printLevel for the kth level:
    T(k) = 2T(k-1) + c = 2^(k-1)T(1) + c = 2^(k-1)
    Assuming it’s a balanced binary tree, then it would have a total of lg N levels.
    Therefore, the complexity of printing all levels is:
    T(1) + T(2) + ... + T(lg N) = 1 + 2 + 2^2 + ... + 2^(lgN-1) + c = O(N)
    Finding the maximum height of the tree also takes O(N) time, therefore the overall complexity is still O(N).
    */
    public void printByLevelDFS(){
        int height = maxDepth();
        for(int level = 1; level <= height; level++){
            printByLevelDFS(root, level);
            System.out.println();
        }
    }

    private void printByLevelDFS(Node node, int level){
        if(node == null) return;
        if(level == 1) System.out.print(node.data + " ");
        else{
            printByLevelDFS(node.left, level-1);
            printByLevelDFS(node.right, level-1);
        }
    }

    /*
    To understand what's same vertical line, we need to define horizontal distances first. If two nodes have the same 
    Horizontal Distance (HD), then they are on same vertical line. The idea of HD is simple. HD for root is 0, a right edge 
    (edge connecting to right subtree) is considered as +1 horizontal distance and a left edge is considered as -1 horizontal distance. 
    */
    public void printByHD(){
        int minhd = minHD();
        int maxhd = maxHD();
        System.out.println("array list size = " + (maxhd-minhd+1));
        ArrayList<ArrayList<Node>> hdList = new ArrayList<ArrayList<Node>>(maxhd-minhd+1);
        for(int i = 0; i < maxhd-minhd+1; i++) hdList.add(new ArrayList<Node>());
        printByHD(hdList, root, 0-minhd);
        for(int i = 0; i < maxhd-minhd+1; i++){
            for(Node node : hdList.get(i)) System.out.print(node.data + " ");
            System.out.println();
        }
    }

    private void printByHD(ArrayList<ArrayList<Node>> hdList, Node node, int pos){
        hdList.get(pos).add(node);
        if(node.left != null) printByHD(hdList, node.left, pos-1);
        if(node.right != null) printByHD(hdList, node.right, pos+1);
    }

    public int minHD(){
        return minHD(root, 0);
    }

    private int minHD(Node node, int hd){
        int mhd = hd;
        if(node.left != null){
            mhd = Math.min(minHD(node.left, hd-1), mhd);
        }
        if(node.right != null){
            mhd = Math.min(minHD(node.right, hd+1), mhd);
        }
        return mhd;
    }

    public int maxHD(){
        return maxHD(root, 0);
    }

    private int maxHD(Node node, int hd){
        int mhd = hd;
        if(node.left != null){
            mhd = Math.max(maxHD(node.left, hd-1), mhd);
        }
        if(node.right != null){
            mhd = Math.max(maxHD(node.right, hd+1), mhd);
        }
        return mhd;
    }

    /*
    Time complexity of above algorithm is O(w*n) where w is width of Binary Tree and n is number of nodes in Binary Tree. 
    In worst case, the value of w can be O(n) (consider a complete tree for example) and time complexity can become O(n^2).
    */
    public void printVerticalLine(){
        int min = minHD();
        int max = maxHD();
        for(int line = min; line <= max; line++){
            printVerticalLine(root, line, 0);
            System.out.println();
        }
    }

    private void printVerticalLine(Node node, int line, int hd){
        if(node == null) return;
        if(line == hd) System.out.print(node.data + " ");
        printVerticalLine(node.left, line, hd-1);
        printVerticalLine(node.right, line, hd+1);
    }

    //Describe an algorithm to save a Binary Search Tree (BST) to a file in terms of run-time and disk space complexity. 
    //You must be able to restore to the exact original BST using the saved format.
    /*
    When we encounter a new node to insert, we should always place it into the first empty space which it will fit using 
    a pre-order traversal. How do we determine the first empty space which a node will fit in? We can use the ordinary BST 
    insertion algorithm, but the run-time complexity will be O(n lg n), since inserting a node takes O(lg n) time. Not efficient enough!
    */


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
        System.out.println("print by level DFS:");
        t4.printByLevelDFS();
        t4.printPretty2();
        t4.mirror();
        t4.printPretty2();
        System.out.println("print t4 pre-order:");
        t4.printPreOrder();
        System.out.println("print t4 pre-order iteratively:");
        t4.printPreOrderIter2();
        System.out.println("print t4 in-order:");
        t4.printTree();
        System.out.println("print t4 in-order iteratively:");
        t4.printInOrderIter();
		t4.printInOrderMorris();
		System.out.println("print t4 post-order:");
		t4.printPostOrder();
		System.out.println("print t4 post-order iteratively:");
		t4.printPostOrderIter();
		t4.printPostOrderIter2();

        countTrees(10);
        for(int i = 1; i <=10; i++)
        System.out.println("countTrees2 " + i + " : " + countTrees2(i));
	
        initNodeArray();
        BinaryTree t5 = new BinaryTree();
        t5.root = nodeArray[5];
        nodeArray[5].left = nodeArray[2];
        nodeArray[5].right = nodeArray[7];
        nodeArray[2].left = nodeArray[1];
        nodeArray[2].right = nodeArray[6];
        t5.printPretty2();
        System.out.println("t5 is BST = " + t5.isBST1());
        System.out.println("t5 is BST2 = " + t5.isBST2());

        initNodeArray();
        BinaryTree t6 = new BinaryTree();
        t6.root = nodeArray[5];
        nodeArray[5].left = nodeArray[2];
        nodeArray[5].right = nodeArray[7];
        nodeArray[2].left = nodeArray[1];
        t6.printPretty2();
        System.out.println("t6 is BST = " + t6.isBST1());
        System.out.println("t6 is BST2 = " + t6.isBST2());

        initNodeArray();
        BinaryTree t7 = new BinaryTree();
        t7.root = nodeArray[4];
        nodeArray[4].left = nodeArray[2];
        nodeArray[4].right = nodeArray[6];
        nodeArray[2].left = nodeArray[1];
        nodeArray[2].right = nodeArray[3];
        nodeArray[6].left = nodeArray[5];
        nodeArray[6].right = nodeArray[7];
        nodeArray[5].right = nodeArray[8];
        nodeArray[8].right = nodeArray[9];
        nodeArray[9].right = nodeArray[10];
        nodeArray[8].left = nodeArray[11];
        nodeArray[11].left = nodeArray[12];
        nodeArray[12].left = nodeArray[13];
        nodeArray[13].left = nodeArray[14];
        t7.printPretty2();
        t7.printVerticalLine();
        System.out.println("min hd = " + t7.minHD());
        System.out.println("max hd = " + t7.maxHD());
        t7.printByHD();
        //t7.printTree();
        //Node head = t7.treeToListRecur();
        //System.out.print(head.data + " ");
        //for(Node n = head.right; n != head; n=n.right) System.out.print(n.data + " ");
        //System.out.println();
        //System.out.print(head.data + " ");
        //for(Node n = head.left; n != head; n=n.left) System.out.print(n.data + " ");
        //System.out.println();
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
