import java.util.Random;
import java.util.Scanner;

class Node {
    // Start of Node Class

    int data;
    Node left;
    Node right;

    // Node Class Constructor
    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    // End of Node Class
}

class BinarySearchTree {
    // Start of Binary Search Tree Class

    Node root;

    // Binary Search Tree Constructor
    public BinarySearchTree() {
        this.root = null;
    }

    //____________________________________________________________________________

    // Search for a key in the Binary Search Tree (BST)
    public int search(int value) {
        Node curNode = new Node(value);
        curNode = root;

        while (curNode != null) {
            if (curNode.data == value) {
                return curNode.data;
            }
            else if (value < curNode.data) {
                curNode = curNode.left;
            }
            else {
                curNode = curNode.right;
            }
        }

        return -1;
    }

    //____________________________________________________________________________

    // Binary Search Tree (BST) Recursion Search (Alternative Method)
    public int searchBST(Node key) {
        return searchBSTRecursion(root, key);
    }

    public int searchBSTRecursion(Node node, Node key) {
        if (node != null) {
            if (node.data == key.data) {
                return node.data;
            }
            else if (key.data < node.data) {
                return searchBSTRecursion(node.left, key);
            }
            else {
                return searchBSTRecursion(node.right, key);
            }
        }
        return -1;
    }

    //____________________________________________________________________________

    // Find a parent of the given key node in the Binary Search Tree (BST)
    public int getParent(Node key) {
        return getParentRecursion(root, key);
    }

    public int getParentRecursion(Node parNode, Node key) {
        if (parNode == null) {
            return -1;
        }

        if (parNode.left.data == key.data || parNode.right.data == key.data) {
            return parNode.data;
        }
        else {
            if (key.data < parNode.data) {
                return getParentRecursion(parNode.left, key);
            }
            else {
                return getParentRecursion(parNode.right, key);
            }
        }
    }

    //____________________________________________________________________________

    // Insert a new node in the Binary Search Tree (BST)
    public boolean insert(Node newNode) {
        if (root == null) {
            root = newNode;
            return true;
        }
        else {
            Node curNode = root;

            while (curNode != null) {
                if (newNode.data < curNode.data) {
                    if (curNode.left == null) {
                        curNode.left = newNode;
                        curNode = null;
                    }
                    else {
                        curNode = curNode.left;
                    }
                }
                else {
                    if (curNode.right == null) {
                        curNode.right = newNode;
                        curNode = null;
                    }
                    else {
                        curNode = curNode.right;
                    }
                }
            }
            return true;
        }
    }

    //____________________________________________________________________________

    // Remove a key node from the Binary Search Tree (BST)
    public boolean remove(Node key) {
        Node curNode = root, parNode = null;

        while (curNode != null) {
            if (curNode.data == key.data) {
                // -----------------------------------------------------------
                // CASE 1: Removing a leaf node (a node with no child)
                if (curNode.left == null && curNode.right == null) {
                    if (parNode == null) {
                        root = null;
                    }
                    else if (parNode.left == curNode) {
                        parNode.left = null;
                    }
                    else {
                        parNode.right = null;
                    }
                }
                // -----------------------------------------------------------
                // CASE 2: Removing internal node with left child
                else if (curNode.right == null) {
                    if (parNode == null) {
                        root = curNode.left;
                    }
                    else if (parNode.left == curNode) {
                        parNode.left = curNode.left;
                    }
                    else {
                        parNode.right = curNode.left;
                    }
                }
                // -----------------------------------------------------------
                // CASE 3: Removing internal node with right child
                else if (curNode.left == null) {
                    if (parNode == null) {
                        root = curNode.right;
                    }
                    else if (parNode.left == curNode) {
                        parNode.left = curNode.right;
                    }
                    else {
                        parNode.right = curNode.right;
                    }
                }
                // -----------------------------------------------------------
                // CASE 4 (Hardest): Removing internal node with two children
                else {
                    Node sucNode = curNode.right;

                    while (sucNode.left != null) {
                        sucNode = sucNode.left;
                    }

                    curNode.data = sucNode.data;
                    parNode = curNode;
                    curNode = curNode.right;
                    key.data = sucNode.data;
                }

                return true;
            }
            else if (key.data < curNode.data) {
                parNode = curNode;
                curNode = curNode.left;
            }
            else {
                parNode = curNode;
                curNode = curNode.right;
            }
        }
        return false;
    }

    //____________________________________________________________________________

    // Get a height of the Binary Search Tree
    public int getHeight() {
        return getHeightRecursion(root);
    }

    // Binary Search Tree (BST) Height Recursion Call
    public int getHeightRecursion(Node curNode) {
        if (curNode == null) {
            return -1;
        }

        int leftSubtree = getHeightRecursion(curNode.left);
        int rightSubtree = getHeightRecursion(curNode.right);
        return 1 + Math.max(leftSubtree, rightSubtree);
    }

    //____________________________________________________________________________

    // In-Order Tree Traversal
    public void inOrderTraversal() {
        inOrderTraversalRecursion(root);
    }

    // In-Order Tree Traversal Recursion Call
    public static void inOrderTraversalRecursion(Node curNode) {
        if (curNode == null) { return; }

        inOrderTraversalRecursion(curNode.left);
        System.out.print(curNode.data + " ");
        inOrderTraversalRecursion(curNode.right);
    }

    //____________________________________________________________________________

    // Pre-Order Tree Traversal
    public void preOrderTraversal() {
        preOrderTraversalRecursion(root);
    }

    // Pre-Order Tree Traversal Recursion Call
    public static void preOrderTraversalRecursion(Node curNode) {
        if (curNode == null) { return; }

        System.out.print(curNode.data + " ");
        preOrderTraversalRecursion(curNode.left);
        preOrderTraversalRecursion(curNode.right);
    }

    //____________________________________________________________________________

    // Post-Order Tree Traversal
    public void postOrderTraversal() {
        postOrderTraversalRecursion(root);
    }

    // Post-Order Tree Traversal Recursion Call
    public static void postOrderTraversalRecursion(Node curNode) {
        if (curNode == null) { return; }

        postOrderTraversalRecursion(curNode.left);
        postOrderTraversalRecursion(curNode.right);
        System.out.print(curNode.data + " ");
    }

    //____________________________________________________________________________

    // Command Instruction
    public void printInstruction() {
        System.out.print("Execution Algorithm Type" + " (Search, Insert, Remove, Height, Print, Exit): ");
    }

    //____________________________________________________________________________

    // End of Binary Search Tree Class
}

public class BST {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        Scanner scnr = new Scanner(System.in);
        Random rand = new Random();
        int[] array = new int[10];

        for (int i = 0; i < array.length; ++i) {
            array[i] = rand.nextInt(100);
            tree.insert(new Node(array[i]));
            System.out.print(array[i] + " ");
        }
        System.out.println();

        tree.printInstruction();
        String type = scnr.nextLine();
        int number;

        while (!type.equalsIgnoreCase("Exit")) {

            // Search for a given number, return available if exist, otherwise unavailable
            if (type.equalsIgnoreCase("Search")) {
                System.out.print("Enter a number: ");
                number = scnr.nextInt();
                if (tree.search(number) >= 0) {
                    System.out.println("Available!");
                }
                else {
                    System.out.println("Unavailable!");
                }
            }

            // Insert a given number in the tree, return success when inserted
            if (type.equalsIgnoreCase("Insert")) {
                System.out.print("Enter a number: ");
                number = scnr.nextInt();
                if (tree.insert(new Node(number))) {
                    System.out.println("Success!");
                }
            }

            // Remove a given number from the tree, return success if removed, otherwise failure
            if (type.equalsIgnoreCase("Remove")) {
                System.out.print("Enter a number: ");
                number = scnr.nextInt();

                if (tree.remove(new Node(number))) {
                    System.out.println("Success!");
                }
                else {
                    System.out.println("Failure!");
                }
            }

            // Return the height of the tree; maximum edges from root to leaf
            if (type.equalsIgnoreCase("Height")) {
                System.out.print("The height of the BST is: " + tree.getHeight());
            }

            // Print a tree in either inorder, preorder or postorder format
            if (type.equalsIgnoreCase("Print")) {
                System.out.print("In-Order? Pre-Order? Post-Order? ");
                type = scnr.next();

                if (type.equalsIgnoreCase("In-Order")) {
                    System.out.println("\nIn-Order BST: ");
                    tree.inOrderTraversal();
                    System.out.println("\n");
                }

                if (type.equalsIgnoreCase("Pre-Order")) {
                    System.out.println("\nPre-Order BST: ");
                    tree.preOrderTraversal();
                    System.out.println("\n");
                }

                if (type.equalsIgnoreCase("Post-Order")) {
                    System.out.println("\nPost-Order BST: ");
                    tree.postOrderTraversal();
                    System.out.println("\n");
                }
            }

            scnr.nextLine();
            tree.printInstruction();
            type = scnr.next();
        }
        scnr.close();
    }
}
