package org.client.learn.se;

/**
 * 自动平衡二叉树
 */
public class MiniAVL {

    static class Node {
        int val;
        int height = 1;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    private Node root;

    /* ========== 对外接口 ========== */
    public void add(int v) {
        root = insert(root, v);
    }

    public void print() {
        inorder(root);
        System.out.println();
    }

    /* ========== 核心逻辑 ========== */

    private Node insert(Node node, int v) {
        if (node == null) return new Node(v);

        // ① 普通BST插入
        if (v < node.val) node.left = insert(node.left, v);
        else node.right = insert(node.right, v);

        // ② 更新高度
        updateHeight(node);

        // ③ 平衡
        return balance(node);
    }

    /* ========== 平衡控制 ========== */

    private Node balance(Node node) {
        int bf = balanceFactor(node);

        // LL
        if (bf > 1 && balanceFactor(node.left) >= 0) return rightRotate(node);

        // LR
        if (bf > 1 && balanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RR
        if (bf < -1 && balanceFactor(node.right) <= 0) return leftRotate(node);

        // RL
        if (bf < -1 && balanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /* ========== 旋转 ========== */

    // 左旋
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // 右旋
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    /* ========== 工具 ========== */

    private void updateHeight(Node n) {
        n.height = Math.max(height(n.left), height(n.right)) + 1;
    }

    private int height(Node n) {
        return n == null ? 0 : n.height;
    }

    private int balanceFactor(Node n) {
        return height(n.left) - height(n.right);
    }

    private void inorder(Node n) {
        if (n == null) return;
        inorder(n.left);
        System.out.print(n.val + " ");
        inorder(n.right);
    }

    /* ========== 测试 ========== */
    public static void main(String[] args) {
        MiniAVL tree = new MiniAVL();
//
//        for (int i = 1; i <= 10; i++) {
//            tree.add(i);
//        }


        for (int i = 10; i >= 0; i--) {
            tree.add(i);
        }

        tree.print(); // 仍然有序，但高度很低
    }
}

