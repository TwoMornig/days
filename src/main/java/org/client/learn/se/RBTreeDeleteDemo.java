package org.client.learn.se;

/**
 * 红黑树 删除
 */
public class RBTreeDeleteDemo {

    static final boolean RED = true;
    static final boolean BLACK = false;

    static class Node {
        int val;
        boolean color;
        Node left, right, parent;

        Node(int val, boolean color) {
            this.val = val;
            this.color = color;
        }
    }

    Node root;

    // 简单左旋
    private Node leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;
        y.left = x;
        x.parent = y;
        return y;
    }

    // 简单右旋
    private Node rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != null) y.right.parent = x;
        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;
        y.right = x;
        x.parent = y;
        return y;
    }

    // 查找最小节点
    private Node minimum(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // 删除节点
    public void delete(Node z) {
        Node y = z;
        Node x; // 双黑节点
        boolean yOriginalColor = y.color;

        if (z.left == null) { // 0 或 1 子节点
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == null) {
            x = z.left;
            transplant(z, z.left);
        } else { // 2 子节点
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                if (x != null) x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (yOriginalColor == BLACK) {
            fixAfterDelete(x, z.parent);
        }
    }

    // 替换节点
    private void transplant(Node u, Node v) {
        if (u.parent == null) root = v;
        else if (u == u.parent.left) u.parent.left = v;
        else u.parent.right = v;
        if (v != null) v.parent = u.parent;
    }

    // 修复双黑
    private void fixAfterDelete(Node x, Node parent) {
        Node w;
        while ((x != root) && (x == null || x.color == BLACK)) {
            if (parent.left == x) { // x 是左孩子
                w = parent.right;
                if (w != null && w.color == RED) {
                    // Case1: 兄弟红 → 左旋 + 交换颜色
                    w.color = BLACK;
                    parent.color = RED;
                    leftRotate(parent);
                    w = parent.right;
                }
                if ((w.left == null || w.left.color == BLACK) && (w.right == null || w.right.color == BLACK)) {
                    // Case2: 兄弟黑，两个孩子黑 → 把 w 涂红，x 上升
                    if (w != null) w.color = RED;
                    x = parent;
                    parent = x.parent;
                } else {
                    if (w.right == null || w.right.color == BLACK) {
                        // Case3: 兄弟黑，右孩子黑，左孩子红 → 右旋 + 调色
                        if (w.left != null) w.left.color = BLACK;
                        w.color = RED;
                        rightRotate(w);
                        w = parent.right;
                    }
                    // Case4: 兄弟黑，右孩子红 → 左旋 + 调色，消掉双黑
                    w.color = parent.color;
                    parent.color = BLACK;
                    if (w.right != null) w.right.color = BLACK;
                    leftRotate(parent);
                    x = root;
                }
            } else { // x 是右孩子，对称处理
                w = parent.left;
                if (w != null && w.color == RED) {
                    w.color = BLACK;
                    parent.color = RED;
                    rightRotate(parent);
                    w = parent.left;
                }
                if ((w.left == null || w.left.color == BLACK) && (w.right == null || w.right.color == BLACK)) {
                    if (w != null) w.color = RED;
                    x = parent;
                    parent = x.parent;
                } else {
                    if (w.left == null || w.left.color == BLACK) {
                        if (w.right != null) w.right.color = BLACK;
                        w.color = RED;
                        leftRotate(w);
                        w = parent.left;
                    }
                    w.color = parent.color;
                    parent.color = BLACK;
                    if (w.left != null) w.left.color = BLACK;
                    rightRotate(parent);
                    x = root;
                }
            }
        }
        if (x != null) x.color = BLACK;
    }

    // 插入辅助，建树用
    public Node insert(int val) {
        Node node = new Node(val, RED);
        if (root == null) {
            root = node;
            node.color = BLACK;
            return node;
        }
        Node cur = root, parent = null;
        while (cur != null) {
            parent = cur;
            if (val < cur.val) cur = cur.left;
            else cur = cur.right;
        }
        node.parent = parent;
        if (val < parent.val) parent.left = node;
        else parent.right = node;
        fixAfterInsert(node);
        return node;
    }

    private void fixAfterInsert(Node x) {
        Node parent, g;
        while ((parent = x.parent) != null && parent.color == RED) {
            g = parent.parent;
            if (g.left == parent) {
                Node u = g.right;
                if (u != null && u.color == RED) {
                    parent.color = u.color = BLACK;
                    g.color = RED;
                    x = g;
                } else {
                    if (parent.right == x) {
                        x = parent;
                        leftRotate(x);
                        parent = x.parent;
                    }
                    parent.color = BLACK;
                    g.color = RED;
                    rightRotate(g);
                }
            } else {
                Node u = g.left;
                if (u != null && u.color == RED) {
                    parent.color = u.color = BLACK;
                    g.color = RED;
                    x = g;
                } else {
                    if (parent.left == x) {
                        x = parent;
                        rightRotate(x);
                        parent = x.parent;
                    }
                    parent.color = BLACK;
                    g.color = RED;
                    leftRotate(g);
                }
            }
        }
        root.color = BLACK;
    }
}
