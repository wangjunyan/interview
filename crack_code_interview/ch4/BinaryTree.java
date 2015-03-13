public class BinaryTree<Item>{
    private Node root;
    private class Node{
        private Item item;
        private Node left, right;
        private int N;

        public Node(Item item){
            this.item = item;
        }
    }

    public interface TraverseOperation{
        void nodeOps(Node node);
    }

    public int size(){
        return size(root);
    }

    public int size(Node x){
        if(x == null) return 0;
        else return x.N;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void traverseInOrder(TraverseOperation ops){
        traverseInOrder(root, ops);
    }

    private void traverseInOrder(Node node, TraverseOperation ops){
        if(node != null){
            traverseInOrder(node.left, ops);
            ops.nodeOps(node);
            traverseInOrder(node.right, ops);
        }
    }

    public static void main(String[] args){
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        tree.root = n4;
        n4.left = n2;
        n4.right = n6;
        n2.left = n1;
        n2.right = n3;
        n6.left = n5;
        n6.right = n7;
        tree.traverseInOrder(new TraverseOperation(){
            public void nodeOps(Node node){
                System.out.println(node.item);
            }
        });
    }
}
