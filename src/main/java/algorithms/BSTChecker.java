package algorithms;

import utils.test.Test;

/**
 * Given a binary tree, write an algorithm to check if it is BST or not.
 * <p>
 * A binary search tree (BST) is a node based binary tree data structure which has the following properties.
 * <ul>
 * <li>The left subtree of a node contains only nodes with keys less than the node’s key</li>
 * <li>The right subtree of a node contains only nodes with keys greater than the node’s key</li>
 * <li>Both the left and right subtrees must also be binary search trees</li>
 * </ul>
 * <p>
 * From the above properties it naturally follows that: each node (item in the tree) has a distinct key.
 */
public class BSTChecker {


    private static boolean isBST(Node n) {
        return isBST(n, null);
    }

    private static boolean isBST(Node n, Node prev) {
        if (n != null) {
            if (!isBST(n.getLeft(), prev))
                return false;

            if (prev != null && n.getVal() <= prev.getVal())
                return false;

            return isBST(n.getRight(), n);
        }
        return true;
    }

    private static boolean isBST(Node node, int min, int max) {
        // an empty tree is BST
        if (node == null)
            return true;

        // false if this node violates the min/max constraints
        if (node.getVal() < min || node.getVal() > max)
            return false;

        // otherwise check the subtrees recursively tightening the min/max constraints Allow only distinct values
        return (isBST(node.getLeft(), min, node.getVal() - 1) &&
                isBST(node.getRight(), node.getVal() + 1, max));
    }

    public static void main(String[] args) {
        Node n = new Node(5);
        Test.equals(true, isBST(n));

        Node n1 = new Node(10, n, null);
        Test.equals(true, isBST(n1));

        Node n2 = new Node(20, n1, null);
        Test.equals(true, isBST(n2));

        Node n3 = new Node(15, n1, n2);
        Test.equals(isBST(n3), false);

        Node n4 = new Node(20,
                // root left side
                new Node(10,
                        new Node(5,
                                new Node(4, null, null),
                                new Node(6, null, null)),
                        new Node(15,
                                new Node(13, null, null),
                                new Node(17, null, null))),
                // root right side
                new Node(30,
                        new Node(27,
                                new Node(21, null, null),
                                new Node(28, null, null)),
                        new Node(32, null, null)));
        Test.equals(true, isBST(n4));
        print(n4, "", 0);

        Node n5 = new Node(20,
                // root left side
                new Node(10,
                        new Node(5,
                                new Node(4, null, null),
                                new Node(6, null, null)),
                        new Node(15,
                                new Node(13, null, null),
                                new Node(17, null, null))),
                // root right side
                new Node(30,
                        new Node(27,
                                new Node(21, null, null),
                                new Node(28, null, null)),
                        new Node(12, null, null)));
        Test.equals(isBST(n5), false);
        print(n5, "", 0);
    }

    public static void print(Node n, String prefix, int level) {
        if (n == null)
            return;

        String suffix = "";
        for (int i = 0; i <= level; i++)
            suffix += "     ";
        print(n.getLeft(), suffix + "┌── ", level + 1);
        System.out.println(prefix + "(" + n.getVal() + ")");
        print(n.getRight(), suffix + "└── ", level + 1);
    }

}
