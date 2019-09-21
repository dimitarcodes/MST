import java.util.ArrayList;
import java.util.Random;

public class Graph {
    private int graph[][];
    private int key[];

    public int[] mst(int start, int end){
        int[] key = new int[graph.length];
        int[] parent = new int[graph.length];
        boolean[] explored = new boolean[graph.length];

        for(int i = 0; i<graph.length; i++) {
            key[i] = Integer.MAX_VALUE;
            explored[i] = false;
        }

        key[start] = 0;
        parent[start] = -1;

        ArrayList<Integer> queue = new ArrayList<>();
        queue.add(start);
        while(!queue.isEmpty()){
            int exploring = queue.remove(0);
            if (exploring<0)
                break;
            explored[exploring] = true;
            for(int i = 0; i < graph.length; i++)
                if (graph[exploring][i] > 0)
                    if (key[i] + graph[exploring][i]<key[i]){
                        key[i] = key[i] + graph[exploring][i];
                        parent[i] = exploring;
                    }
            if (key[end]<Integer.MAX_VALUE) {
                String record = "";
                int trace = end;
                while (trace != start) {
                    record = (char) (65 + trace) + record;
                    record = " -> " + record;
                    trace = parent[trace];
                }
                record = (char) (65 + start) + record;

                System.out.println(record);
                break;
            }
            queue.add(min(key,explored));
        }

        if (key[end] == Integer.MAX_VALUE)
            System.out.println("no path");
        return parent;
    }

    private int min(int[] keys, boolean[] explored) {
        int min = 0;
        for (int i = 0; i < keys.length; i++){
            if (keys[i] != 0 && keys[i] < keys[min] && !explored[i])
                min = i;}
        if (explored[min])
            return -1;
        return min;
    }

    public Graph (int vertices) {
        graph = new int[vertices][vertices];
        Random r = new Random();
        for (int i = 0; i < vertices; i++)
            for (int j = 0; j < vertices; j++) {
                if (j < i)
                    graph[i][j] = graph[j][i];
                else if (i == j)
                    graph[i][j] = 0;
                else
                    graph[i][j] = r.nextInt(10);
            }
    }

    public Graph(int[][] manualInput){
        this.graph = manualInput;
    }

    @Override
    public String toString(){
        String result = "  ";
        for(int i = 0; i<graph.length; i++)
            result+=(char)(i+65)+" ";
        result+="\n";
        for(int i=0; i<graph.length;i++){
            result+=(char)(i+65);
            for(int j=0; j<graph.length;j++)
                result+=" "+graph[i][j];
            result+="\n";
        }
        return result;
    }

}
