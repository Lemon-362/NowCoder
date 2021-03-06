package NowCoder.advanced_class.exercise.advance;

import java.util.*;

/*
	TreeMap红黑树的应用：大楼轮廓
		给定N行3列的二维数组，每行表示一座大楼，一共N座
		所有大楼底部都坐落在X轴上，每一行的三个值(a, b, c)代表每座大楼从(a, 0)开始，到(b, 0)结束，高度为c
		输入数据保证a<b，且a,b,c均为正数，大楼之间可以有重合
		请输入整体的轮廓线
	例：
	输入：[[1, 3, 3], [2, 4, 4], [5, 6, 1]]
	输出：[[1, 2, 3], [2, 4, 4], [5, 6, 1]]

	TODO 红黑树时刻都在调整平衡性
	 lastKey()：key最大的，firstKey()：key最小的
 */
public class code14_BuildingOutline {
    public static class Node {
        private int position;
        private int height;
        private boolean isUp;

        public Node(int position, int height, boolean isUp) {
            this.position = position;
            this.height = height;
            this.isUp = isUp;
        }
    }

    public static List<List<Integer>> buildingOutline(int[][] arr){
        Node[] nodes = new Node[2 * arr.length];

        for (int i = 0; i < arr.length; i++) {
            nodes[2*i] = new Node(arr[i][0], arr[i][2], true);
            nodes[2*i+1] = new Node(arr[i][1], arr[i][2], false);
        }

        Arrays.sort(nodes, new myComparator());

        TreeMap<Integer, Integer> htMap = new TreeMap<>();
        TreeMap<Integer, Integer> pmMap = new TreeMap<>();

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].isUp){
                if (htMap.containsKey(nodes[i].height)){
                    htMap.put(nodes[i].height, htMap.get(nodes[i].height) + 1);
                }else {
                    htMap.put(nodes[i].height, 1);
                }
            }else {
                if (htMap.containsKey(nodes[i].height)) {
                    if (htMap.get(nodes[i].height) == 1) {
                        htMap.remove(nodes[i].height);
                    } else {
                        htMap.put(nodes[i].height, htMap.get(nodes[i].height) - 1);
                    }
                }
            }

            if (htMap.isEmpty()){
                pmMap.put(nodes[i].position, 0);
            }else {
                pmMap.put(nodes[i].position, htMap.lastKey());
            }
        }

        List<List<Integer>> lists = new ArrayList<>();
        int start = 0;
        int height = 0;

        for (Map.Entry<Integer, Integer> entry : pmMap.entrySet()){
            int curPosition = entry.getKey();
            int curHeight = entry.getValue();
            if (height != curHeight){
                if (height != 0){
                    List<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.add(curPosition);
                    list.add(height);
                    lists.add(list);
                }
                height = curHeight;
                start = curPosition;
            }
        }

        return lists;
    }

    public static class myComparator implements Comparator<Node> {
        public int compare(Node o1, Node o2){
            if (o1.position != o2.position){
                return o1.position - o2.position;
            }else {
                if (o1.isUp != o2.isUp){
                    return o1.isUp ? -1 : 1;
                }
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        int[][] buildings = {{1, 3, 3}, {2, 4, 4}, {5, 6, 1}};
        List<List<Integer>> lists = buildingOutline(buildings);
        for (List<Integer> list : lists) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
//		1 2 3
//		2 4 4
//		5 6 1
    }
}
