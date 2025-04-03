package dataStructures;

// Node class representing each element in the binary tree
class Node {
    int data;
    Node left, right;

    public Node(int data) {
        this.data = data;
        this.left = this.right = null; // Initialize left and right pointers to null
    }
}

// BinaryTree class containing operations
class LinkedBinaryTree {
    Node root;

    // Insert a new node in the binary tree
    public Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data); // Create a new node if tree is empty
        }

        if (data < root.data) {
            root.left = insert(root.left, data); // Insert into the left subtree
        } else {
            root.right = insert(root.right, data); // Insert into the right subtree
        }

        return root;
    }

    // Inorder Traversal (Left -> Root -> Right)
    public void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    // Preorder Traversal (Root -> Left -> Right)
    public void preorder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    // Postorder Traversal (Left -> Right -> Root)
    public void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    // Search for a node in the binary tree
    public boolean search(Node root, int key) {
        if (root == null) {
            return false; // Base case: If node is not found
        }

        if (root.data == key) {
            return true; // Found the node
        }

        return key < root.data ? search(root.left, key) : search(root.right, key);
    }

    // Find the minimum value node in the tree
    public Node findMin(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    // Delete a node from the binary tree
    public Node delete(Node root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.data) {
            root.left = delete(root.left, key);
        } else if (key > root.data) {
            root.right = delete(root.right, key);
        } else {
            // Case 1: Node has no children
            if (root.left == null && root.right == null) {
                return null;
            }
            // Case 2: Node has one child
            else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // Case 3: Node has two children
            Node temp = findMin(root.right); // Get inorder successor
            root.data = temp.data; // Copy inorder successor value to the node
            root.right = delete(root.right, temp.data); // Delete the inorder successor
        }

        return root;
    }
}

// Main class to test the BinaryTree
public class binaryTree {
    public static void main(String[] args) {
        LinkedBinaryTree tree = new LinkedBinaryTree();

        // Insert nodes into the binary tree
        tree.root = tree.insert(tree.root, 50);
        tree.insert(tree.root, 30);
        tree.insert(tree.root, 70);
        tree.insert(tree.root, 20);
        tree.insert(tree.root, 40);
        tree.insert(tree.root, 60);
        tree.insert(tree.root, 80);

        // Display the tree in different orders
        System.out.println("Inorder traversal:");
        tree.inorder(tree.root);

        System.out.println("\nPreorder traversal:");
        tree.preorder(tree.root);

        System.out.println("\nPostorder traversal:");
        tree.postorder(tree.root);

        // Searching for nodes
        System.out.println("\nSearching for 40: " + tree.search(tree.root, 40));
        System.out.println("Searching for 100: " + tree.search(tree.root, 100));

        // Deleting a node and displaying inorder traversal after deletion
        System.out.println("Deleting 20...");
        tree.root = tree.delete(tree.root, 20);
        System.out.println("Inorder traversal after deletion:");
        tree.inorder(tree.root);

        System.out.println("\nDeleting 50...");
        tree.root = tree.delete(tree.root, 50);
        System.out.println("Inorder traversal after deletion:");
        tree.inorder(tree.root);
    }
}

