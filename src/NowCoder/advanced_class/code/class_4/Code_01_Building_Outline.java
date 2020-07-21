package NowCoder.advanced_class.code.class_4;

import java.util.*;
import java.util.Map.Entry;

/*
	TreeMap红黑树的应用：大楼轮廓
		给定N行3列的二维数组，每行表示一座大楼，一共N座
		所有大楼底部都坐落在X轴上，每一行的三个值(a, b, c)代表每座大楼从(a, 0)开始，到(b, 0)结束，高度为c
		输入数据保证a<b，且a,b,c均为正数，大楼之间可以有重合
		请输入整体的轮廓线
	例：
	输入：[[1, 3, 3], [2, 4, 4], [5, 6, 1]]
	输出：[[1, 2, 3], [2, 4, 4], [5, 6, 1]]
 */
public class Code_01_Building_Outline {

    public static class Node {
        public boolean isUp; // 是否向上
        public int posi; // 位置
        public int h; // 高度

        public Node(boolean bORe, int position, int height) {
            isUp = bORe;
            posi = position;
            h = height;
        }
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            if (o1.posi != o2.posi) { // 位置不同时，位置小的排在前面
                return o1.posi - o2.posi; // 从小到大，谁小谁在前
            }
            if (o1.isUp != o2.isUp) { // 位置相同时，向上的排在前，向下的排在后
                return o1.isUp ? -1 : 1; // -1上在前，1下在后
            }
            return 0;
        }
    }

    // {{1, 3, 3}, {2, 4, 4}, {5, 6, 1}}
    public static List<List<Integer>> buildingOutline(int[][] buildings) {
        // 每个大楼会被拆成两个信息，先上后下，所以信息数组长度为大楼数*2
        Node[] nodes = new Node[buildings.length * 2];
        for (int i = 0; i < buildings.length; i++) { // 将每个大楼拆成两部分
            // 第一个大楼的上下索引：0，1  第二个大楼的上下索引：2，3   第三个大楼的上下索引：4，5  。。。
            // TODO buildings里存的是（起始（上的位置），终止（下的位置），高度）  Node里存的是（是否上，位置，高度）
            // 上的信息放在偶数位
            nodes[i * 2] = new Node(true, buildings[i][0], buildings[i][2]);
            // 下的信息放在奇数位
            nodes[i * 2 + 1] = new Node(false, buildings[i][1], buildings[i][2]);
        }
        // 按照位置排序
        Arrays.sort(nodes, new NodeComparator());

        // 高度Map    （高度，高度出现的次数）
        TreeMap<Integer, Integer> htMap = new TreeMap<>();
        // 每个出现位置的最大高度Map   （位置，最大高度）
        TreeMap<Integer, Integer> pmMap = new TreeMap<>();

        for (int i = 0; i < nodes.length; i++) {
            // 根据每个node的上下和高度设置出现次数
            // 上 +1 put，下 -1 remove
            if (nodes[i].isUp) { // 上，put
                if (!htMap.containsKey(nodes[i].h)) { // 高度第一次出现
                    htMap.put(nodes[i].h, 1);
                } else { // 高度不是第一次出现，次数+1
                    htMap.put(nodes[i].h, htMap.get(nodes[i].h) + 1);
                }
            } else { // 下，remove
                if (htMap.containsKey(nodes[i].h)) { // 高度不是第一次出现
                    if (htMap.get(nodes[i].h) == 1) { // 之前高度只出现一次
                        htMap.remove(nodes[i].h); // value=1，减1等于0，需要删除该key
                    } else { // 之前高度出现多次（大于1次）
                        htMap.put(nodes[i].h, htMap.get(nodes[i].h) - 1); // value直接减1
                    }
                }
            }

            // 记录当前位置的最大高度 （位置，最大高度）
            if (htMap.isEmpty()) { // TODO 如果htMap为空，说明是第一个起始位置
                pmMap.put(nodes[i].posi, 0);
            } else { // TODO 因为htMap存的是（高度，高度出现的次数），且TreeMap始终保持key的升序排列
                // TODO 所以只需要得到htMap的最后一个key即可获得当前最大高度
                pmMap.put(nodes[i].posi, htMap.lastKey());
            }
        }

        // 根据pmMap记录的每个位置的最大高度，画出轮廓线
        List<List<Integer>> res = new ArrayList<>();
        int start = 0;
        int height = 0;

        // 遍历最大高度pmMap
        for (Entry<Integer, Integer> entry : pmMap.entrySet()) {
            // pmMap的key位置是升序的
            int curPosition = entry.getKey(); // key当前位置
            int curMaxHeight = entry.getValue(); // value最大高度
            if (height != curMaxHeight) { // 之前的高度 ！= 当前的最大高度，说明高度变化了，产生轮廓线
                if (height != 0) { // 之前的高度要结束了，生成终止位置的轮廓
                    List<Integer> newRecord = new ArrayList<>();
                    // TODO 返回（开始位置，终止位置，高度）
                    newRecord.add(start); // 之前高度的开始位置
                    newRecord.add(curPosition); // 终止的位置
                    newRecord.add(height); // 之前的高度
                    res.add(newRecord);
                }
                // 之前的高度等于0，说明是开始产生的轮廓的位置，后续再记录终止的位置
                // 每次轮廓可以看成是从0往上然后再往下
                start = curPosition;
                height = curMaxHeight;
            }
        }
        return res;
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
