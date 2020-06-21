package NowCoder.advanced_class.exercise.exercise01;

import java.util.*;

public class code14 {
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

    public static List<List<Integer>> buildingOutline(int[][] arr) {
        Node[] nodes = new Node[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            nodes[2 * i] = new Node(arr[i][0], arr[i][2], true);
            nodes[2 * i + 1] = new Node(arr[i][1], arr[i][2], false);
        }

        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.position != o2.position){
                    return o1.position - o2.position;
                }else {
                    if (o1.isUp != o2.isUp){
                        return o1.isUp ? -1 : 1;
                    }
                }
                return 0;
            }
        });

        TreeMap<Integer, Integer> htMap = new TreeMap<>();
        TreeMap<Integer, Integer> pmMap = new TreeMap<>();

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].isUp){
                if (!htMap.containsKey(nodes[i].height)){
                    htMap.put(nodes[i].height, 1);
                }else {
                    htMap.put(nodes[i].height, htMap.get(nodes[i].height) + 1);
                }
            }else {
                if (htMap.get(nodes[i].height) == 1){
                    htMap.remove(nodes[i].height);
                }else {
                    htMap.put(nodes[i].height, htMap.get(nodes[i].height) - 1);
                }
            }

            if (htMap.isEmpty()){
                pmMap.put(nodes[i].position, 0);
            }else {
                pmMap.put(nodes[i].position, htMap.lastKey());
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        int start = 0;
        int height = 0;

        for(Map.Entry<Integer, Integer> entry : pmMap.entrySet()){
            int curPosition = entry.getKey();
            int curHeight = entry.getValue();

            if (height != curHeight){
                if (height != 0){
                    List<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.add(curPosition);
                    list.add(height);
                    res.add(list);
                }
                height = curHeight;
                start = curPosition;
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
