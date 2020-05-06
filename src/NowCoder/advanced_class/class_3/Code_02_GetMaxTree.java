package NowCoder.advanced_class.class_3;

import java.util.HashMap;
import java.util.Stack;

/*
    构造数组的MaxTree：
        数组建树，使得数的每个子树最大值都是头部，且要求时间空间复杂度为O(N)
        定义二叉树节点，一个数组的MaxTree定义如下：
        1. 数组没有重复元素
        2. MaxTree是一颗二叉树，数组的每一个值对应一个节点
        3. 包括MaxTree树在内的任一个子树，值最大的节点都是头部

遍历元素时，依次压入，不满足条件时弹出：
    弹出值的左信息：他的下一个
    弹出值的右信息：压入的数
遍历完栈中还有元素时，依次弹出：
    弹出值的左信息：他的下一个
    弹出值的右信息：null
 */
public class Code_02_GetMaxTree {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node method(int[] arr){
        Node[] nArr = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) { // nArr就是每个节点
            nArr[i] = new Node(arr[i]);
        }

        Stack<Node> stack = new Stack<>(); // 单调栈
        HashMap<Node, Node> lBigMap = new HashMap<>(); // 左边离他最近比他大的
        HashMap<Node, Node> rBigMap = new HashMap<>(); // 右边离他最近比他大的

        // TODO 遍历每个节点，压入栈中
        for (int i = 0; i < nArr.length; i++) {
            Node curNode = nArr[i];
            while (!stack.isEmpty() && curNode.value > stack.peek().value){
                // 弹出元素，并收集左右信息
                Node popNode = stack.pop();
                // TODO 右信息
                rBigMap.put(popNode, curNode);
                // TODO 左信息
                if (stack.isEmpty()){
                    lBigMap.put(popNode, null);
                }else {
                    lBigMap.put(popNode, stack.peek());
                }
            }
            stack.push(curNode);
        }

        // TODO 遍历完后，如果栈中还有值，则依次弹出并收集左右信息
        while (!stack.isEmpty()){
            Node popNode = stack.pop();
            // TODO 右信息
            rBigMap.put(popNode, null);
            // TODO 左信息
            if (stack.isEmpty()){
                lBigMap.put(popNode, null);
            }else {
                lBigMap.put(popNode, stack.peek());
            }
        }

        // TODO 建树
        // 1. 如果一节点左右均为null，则他为头节点
        // 2. 如果一节点有一边为null，则他挂在左右有值的底下
        // 3. 如果一节点两边都不为null，则他挂在左右较小的一侧底下
        Node head = null;
        for (int i = 0; i < nArr.length; i++) {
            Node curNode = nArr[i]; // 对当前节点进行建树
            Node left = lBigMap.get(curNode); // 当前节点的左信息
            Node right = rBigMap.get(curNode); // 当前节点的右信息
            // TODO 优先放左孩子
            if (left == null && right == null){ // 情况1
                head = curNode;
            }else if (left == null){ // 情况2，此时右信息有值，挂在右信息的底下，优先右信息的左孩子
                if (right.left == null){
                    right.left = curNode;
                }else {
                    right.right = curNode;
                }
            }else if (right == null){ // 情况2，此时左信息有值，挂在左信息的底下，优先左信息的左孩子
                if (left.left == null){
                    left.left = curNode;
                }else {
                    left.right = curNode;
                }
            }else { // 情况3
                Node parent = left.value < right.value ? left : right; // 左右信息的较小值，优先左孩子
                if (parent.left == null){
                    parent.left = curNode;
                }else {
                    parent.right = curNode;
                }
            }
        }
        return head;
    }

    public static Node getMaxTree(int[] arr){
        Node[] nArr = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) { // nArr就是每个节点
            nArr[i] = new Node(arr[i]);
        }

        Stack<Node> stack = new Stack<>(); // 单调栈
        HashMap<Node, Node> lBigMap = new HashMap<>(); // 左边离他最近比他大的
        HashMap<Node, Node> rBigMap = new HashMap<>(); // 右边离他最近比他大的

        // TODO 收集左信息，弹出节点的左信息一定是他的下一个
        // 遍历每个节点：节点依次压入栈，并收集弹出节点的左信息
        for (int i = 0; i < nArr.length; i++) {
            Node curNode = nArr[i]; // 将当前节点存入单调栈中
            while ((!stack.isEmpty()) && stack.peek().value < curNode.value){
                // 因为单调栈从栈底到栈顶是从大到小的
                // 如果栈顶元素<当前元素，则弹出栈顶，并收集该弹出节点的左信息
                popStackSetMap(stack, lBigMap);
            }
            // 直到满足从大到小，将当前节点压入栈
            stack.push(curNode);
        }
        // 遍历完节点：当节点都压入栈后，如果栈不为空，则依次弹出并收集弹出节点的左信息
        while (!stack.isEmpty()){
            popStackSetMap(stack, lBigMap);
        }

        // TODO 收集右信息，压入时是压入的元素，压完后是null
        for (int i = nArr.length - 1; i >= 0; i--) {
            Node curNode = nArr[i];
            while ((!stack.isEmpty()) && stack.peek().value < curNode.value){
                popStackSetMap(stack, rBigMap);
            }
            stack.push(curNode);
        }
        while (!stack.isEmpty()){
            popStackSetMap(stack, rBigMap);
        }

        Node head = null;
        for (int i = 0; i < nArr.length; i++) {
            Node curNode = nArr[i];
            Node left = lBigMap.get(curNode);
            Node right = rBigMap.get(curNode);
            if (left == null && right == null){
                head = curNode;
            }else if (left == null){
                if (right.left == null){
                    right.left = curNode;
                }else {
                    right.right = curNode;
                }
            }else if (right == null){
                if (left.left == null){
                    left.left = curNode;
                }else {
                    left.right = curNode;
                }
            }else {
                Node parent = left.value < right.value ? left : right;
                if (parent.left == null){
                    parent.left = curNode;
                }else {
                    parent.right = curNode;
                }
            }
        }
        return head;
    }

    public static void popStackSetMap(Stack<Node> stack, HashMap<Node, Node> map){
        Node popNode = stack.pop(); // 栈顶弹出的节点
        if (stack.isEmpty()){ // 如果弹出后栈为空，则弹出节点的左边比他大的是null
            map.put(popNode, null);
        }else { // 如果弹出后栈还有元素，则弹出节点的左边比他大的是他下一个元素，也就是当前栈顶
            map.put(popNode, stack.peek());
        }
    }

    public static void printPreOrder(Node head){
        if (head == null){
            return;
        }
        System.out.print(head.value + " ");
        printPreOrder(head.left);
        printPreOrder(head.right);
    }

    public static void printInOrder(Node head){
        if (head == null){
            return;
        }
        printPreOrder(head.left);
        System.out.print(head.value + " ");
        printPreOrder(head.right);
    }

    public static void main(String[] args) {
        int[] arr = {3,4,5,1,2};
//        Node head = getMaxTree(arr);
        Node head = method(arr);
        printPreOrder(head); // 5 4 3 2 1
        System.out.println();
        printInOrder(head); // 4 3 5 2 1
    }
}
