/*
 *@author mechachki - Dimitar Dimitrov s1018291
 *@author Carla Schindler s1017233
 */
public class main {
    public static void main(String[] args) {

        Graph random_graph = new Graph(4,20);
        System.out.println(random_graph);
        random_graph.mst();

        Graph random_graph2 = new Graph("Different.txt");
        System.out.println(random_graph2);
        random_graph2.mst();

        Graph text_graph = new Graph("graphTwo.txt");
        System.out.println(text_graph);
        text_graph.mst();

        Graph hardcoded_graph = new Graph(new int[][]{
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
        System.out.println(hardcoded_graph);
        hardcoded_graph.mst();

    }
}
