public class main {
    public static void main(String[] args) {
        Graph random_graph = new Graph(8);
        Graph manual_graph = new Graph(new int[][]{
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 0, 0, 0, 0, 6, 7, 0}
                });

        System.out.println(random_graph);

        int[] trace_random = random_graph.mst(1,7);

        System.out.println(manual_graph);

        int[] trace_manual = manual_graph.mst(0,4);

    }
}
