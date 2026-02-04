package org.client.learn.se;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNodeDemo {

    //NOTE 树结构
    //           1
    //        2    3
    //      4  5  6  7
    public static void main(String[] args) {
        TreeNode<Integer> treeNode = createFullBinaryTreeLevel4();
        flowOrder(treeNode);
    }

    //NOTE 层序遍历
    private static <E> void flowOrder(TreeNode<E> root) {
        if (root == null) return;

        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode<E> node = queue.poll();

            System.out.println(node.element);

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }


    //NOTE 中 左 右  前序
    private static <E> void preOrder(TreeNode<E> treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.println(treeNode.element);
        preOrder(treeNode.left);
        preOrder(treeNode.right);
    }

    //NOTE  左 中 右 中序
    private static <E> void midOrder(TreeNode<E> treeNode) {
        if (treeNode == null) return;
        midOrder(treeNode.left);
        System.out.println(treeNode.element);
        midOrder(treeNode.right);
    }

    //NOTE  左 右 中  后续
    private static <E> void postOrder(TreeNode<E> treeNode) {
        if (treeNode == null) return;
        postOrder(treeNode.left);
        postOrder(treeNode.right);
        System.out.println(treeNode.element);
    }

    static class TreeNode<E> {
        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        TreeNode(E e) {
            this.element = e;
        }
    }

    public static TreeNode<Integer> createFullBinaryTreeLevel4() {
        // 第1层（根）
        TreeNode<Integer> root = new TreeNode<>(1);

        // 第2层
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(3);

        // 第3层
        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<>(5);
        root.right.left = new TreeNode<>(6);
        root.right.right = new TreeNode<>(7);

        return root;
    }
}
