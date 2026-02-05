package org.client.learn.se;

/**
 * 红黑树 新增
 */
public class MiniRBTree {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    static class Node {
        int val;
        boolean color = RED;

        Node left, right, parent;

        Node(int v) {
            val = v;
        }
    }

    private Node root;

    /* ========= 对外接口 ========= */
    public void add(int v) {
        Node node = new Node(v);
        root = insert(root, node);
        fixAfterInsert(node);
    }

    /* ========= BST插入 ========= */
    private Node insert(Node root, Node node) {
        if (root == null) {
            node.color = BLACK;
            return node;
        }

        Node cur = root, parent = null;

        while (cur != null) {
            parent = cur;
            if (node.val < cur.val) cur = cur.left;
            else cur = cur.right;
        }

        node.parent = parent;

        if (node.val < parent.val) parent.left = node;
        else parent.right = node;

        return root;
    }

    /* ========= 红黑修复核心 ========= */
    private void fixAfterInsert(Node x) {
        while (x != root && colorOf(parentOf(x)) == RED) {

            // 父是爷爷的左
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {

                Node uncle = rightOf(parentOf(parentOf(x)));

                // ① 叔叔红 -> 变色
                if (colorOf(uncle) == RED) {
                    setBlack(parentOf(x));
                    setBlack(uncle);
                    setRed(parentOf(parentOf(x)));
                    x = parentOf(parentOf(x));
                }
                // ② 叔叔黑 -> 旋转
                else {
                    if (x == rightOf(parentOf(x))) {
                        x = parentOf(x);
                        leftRotate(x);
                    }
                    setBlack(parentOf(x));
                    setRed(parentOf(parentOf(x)));
                    rightRotate(parentOf(parentOf(x)));
                }
            }
            // 对称情况
            else {
                Node uncle = leftOf(parentOf(parentOf(x)));

                if (colorOf(uncle) == RED) {
                    setBlack(parentOf(x));
                    setBlack(uncle);
                    setRed(parentOf(parentOf(x)));
                    x = parentOf(parentOf(x));
                } else {
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rightRotate(x);
                    }
                    setBlack(parentOf(x));
                    setRed(parentOf(parentOf(x)));
                    leftRotate(parentOf(parentOf(x)));
                }
            }
        }

        root.color = BLACK;
    }

    /* ========= 旋转 ========= */

    private void leftRotate(Node x) {
        Node y = x.right;

        x.right = y.left;
        if (y.left != null) y.left.parent = x;

        y.parent = x.parent;

        if (x.parent == null) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;

        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node y) {
        Node x = y.left;

        y.left = x.right;
        if (x.right != null) x.right.parent = y;

        x.parent = y.parent;

        if (y.parent == null) root = x;
        else if (y == y.parent.left) y.parent.left = x;
        else y.parent.right = x;

        x.right = y;
        y.parent = x;
    }

    /* ========= 小工具 ========= */

    private boolean colorOf(Node n) {
        return n == null ? BLACK : n.color;
    }

    private Node parentOf(Node n) {
        return n == null ? null : n.parent;
    }

    private Node leftOf(Node n) {
        return n == null ? null : n.left;
    }

    private Node rightOf(Node n) {
        return n == null ? null : n.right;
    }

    private void setRed(Node n) {
        if (n != null) n.color = RED;
    }

    private void setBlack(Node n) {
        if (n != null) n.color = BLACK;
    }

    /* ========= 测试 ========= */
    public static void main(String[] args) {
        MiniRBTree t = new MiniRBTree();

        for (int i = 1; i <= 20; i++) {
            t.add(i);
        }

        System.out.println("done");
    }
}
