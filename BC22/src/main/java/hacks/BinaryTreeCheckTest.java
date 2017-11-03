package hacks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class BinaryTreeCheckTest {
  static class Node {
    int data;
    Node left;
    Node right;

    public Node(int pData, Node pLeft, Node pRight) {
      super();
      data = pData;
      left = pLeft;
      right = pRight;
    }
  }

  public static List<Integer> inorderTraversal(Node root) {
    ArrayList<Integer> lst = new ArrayList<Integer>();

    if (root == null)
      return lst;

    Stack<Node> stack = new Stack<Node>();
    // define a pointer to track nodes
    Node p = root;

    while (!stack.empty() || p != null) {

      // if it is not null, push to stack
      // and go down the tree to left
      if (p != null) {
        stack.push(p);
        p = p.left;

        // if no left child
        // pop stack, process the node
        // then let p point to the right
      } else {
        Node t = stack.pop();
        lst.add(t.data);
        p = t.right;
      }
    }

    return lst;
  }

  static int[] minMaxValue(Node pNode) {
    if (pNode.left == null && pNode.right == null) {
      return new int[] { pNode.data, pNode.data };
    }
    if (pNode.left == null) {
      int[] minMaxRight = minMaxValue(pNode.right);
      return new int[] { Math.min(pNode.data, minMaxRight[0]), Math.max(pNode.data, minMaxRight[1]) };
    } else if (pNode.right == null) {
      int[] minMaxLeft = minMaxValue(pNode.left);
      return new int[] { Math.min(pNode.data, minMaxLeft[0]), Math.max(pNode.data, minMaxLeft[1]) };
    } else {
      int[] minMaxRight = minMaxValue(pNode.right);
      int[] minMaxLeft = minMaxValue(pNode.left);
      return new int[] { Math.min(pNode.data, Math.min(minMaxRight[0], minMaxLeft[0])),
          Math.max(pNode.data, Math.max(minMaxRight[1], minMaxLeft[1])) };
    }

  }

  static boolean checkIsBinaryTree(Node pNode) {
    if (pNode == null) {
      return true;
    }
    if (pNode.left != null && pNode.left.data > pNode.data) {
      return false;
    }
    if (pNode.right != null && pNode.right.data < pNode.data) {
      return false;
    }
    if (!checkIsBinaryTree(pNode.left)) {
      return false;
    }
    if (!checkIsBinaryTree(pNode.right)) {
      return false;
    }
    if (pNode.left != null) {
      int[] mm = minMaxValue(pNode.left);
      if (pNode.data < mm[1]) {
        return false;
      }
    }
    if (pNode.right != null) {
      int[] mm = minMaxValue(pNode.right);
      if (pNode.data > mm[0]) {
        return false;
      }
    }
    return true;
  }
  
  static boolean checkIsBinaryTreeVersion2(Node pNode) {
    List<Integer> treeOrder = inorderTraversal(pNode);
    List<Integer> treeSorted = new ArrayList<>(treeOrder);
    Collections.sort(treeSorted);
    return treeSorted.equals(treeOrder);
  }

  public static void main(String[] args) {
    Node root = new Node(100,
        new Node(89, new Node(75, new Node(70, null, null), new Node(80, null, null)), new Node(91, null, null)),
        new Node(110, new Node(102, null, null), new Node(115, null, null)));
    System.out.println(checkIsBinaryTree(root));
    System.out.println(checkIsBinaryTreeVersion2(root));
  }

}
