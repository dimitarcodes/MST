/*
 *@author mechachki - Dimitar Dimitrov s1018291
 *@author Carla Schindler s1017233
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Graph {
    //Vertex connectivity; 0 = no edge
    private int graph[][];
    //weight needed to achieve vertex at Index
    private int key[];
    //track which vertex is the parent of each vertex, inital vertex has parent -1
    private int parent[];
    //track whether a vertex has been explored
    private boolean explored[];
    //runtime complexity counter
    int counter;

    public void mst(){
        counter=0;
        int start = 0;
        key = new int[graph.length];
        //Index of previous Vertex
        parent = new int[graph.length];
        //Previous explored = true
        explored = new boolean[graph.length];
        //set all keys to a high number, set all points to unexplored
        for(int i = 0; i<graph.length; i++) {
            key[i] = Integer.MAX_VALUE;
            explored[i] = false;
        }
        //set the key of the initial Vertex to 0, parent to -1
        key[start] = 0;
        parent[start] = -1;
        int exploring = start;
        //while the queue is not empty
        while(exploring>-1) {
            //tag current Vertex as explored
            System.out.println("Exploring Vertex " + (char)(exploring+65) + " (index : "+ exploring+" )");
            explored[exploring] = true;
            //go through all the vertices
            for (int i = 0; i < graph.length; i++) {
                System.out.println("Considering Vertex: " + (char)(i+65) + " (index : " + i + " )");
                counter++;
                //check if the current vertex is connected to the one being explored
                if (graph[exploring][i] > 0) {
                    System.out.println(("Adjacency found between vertices " + (char)(exploring+65) + " and "+ (char)(i+65) + "  (respectively indices " + exploring + " and " + i + " )"));
                    //if the key for that adjacent vertex is lower than the weight of edge connecting it
                    //to the current explored vertex AND the adjacent vertex hasn't been explored yet
                    if (graph[exploring][i] < key[i] && !explored[i]) {
                        System.out.println("Weight between the aforementioned vertices is lower than the key of Vertex " + (char)(i+65) + " (index: "+ i + " )");
                        //replace the key of the current vertex with the new lowest weight needed to achieve it
                        key[i] = graph[exploring][i];
                        System.out.println("Key of Vertex " + (char)(i+65) + " set to " + graph[exploring][i]);
                        //set the parent of the current vertex to the explored vertex
                        System.out.println(("Parent of Vertex " + (char)(i+65) + " set to " + (char)(exploring+65)));
                        parent[i] = exploring;
                    }
                }
            }
            exploring = upnext(key, explored);
        }
        System.out.println("Graph explored. Parenthood with starting point A: ");
        int sum = 0;
        for (int i=1; i<parent.length;i++){
            System.out.println((char)(i+65) + " is child of "+ (char)(parent[i]+65));
            sum+=graph[parent[i]][i];
        }
        System.out.println("tree weight: " + sum);
        System.out.println("complexity: " + counter);
    }

    private int upnext(int[] key, boolean[] explored){
        System.out.println("Initializing search");
        int min = Integer.MAX_VALUE;
        int search = -1;
        for (int i=0; i<key.length; i++){
            System.out.println("Considering edge: " + (char)(i+65));
            counter++;
            if (key[i]<min && !explored[i]){
                System.out.println("New candidate found: " + (char)(i+65) + " with key " + key[i]);
                min = key[i];
                search = i;
            }
        }
        if (search == -1)
            System.out.println("No candidate found, returning -1 and terminating the MST search...");
        else System.out.println("Candidate to be returned: " + (char)(search+65));
        return search;
    }

    public Graph (int vertices, int bound) {
        graph = new int[vertices][vertices];
        Random r = new Random();
        for (int i = 0; i < vertices; i++)
            for (int j = 0; j < vertices; j++) {
                if (j < i)
                    graph[i][j] = graph[j][i];
                else if (i == j)
                    graph[i][j] = 0;
                else
                    graph[i][j] = r.nextInt(bound);
            }
    }

    public Graph (int vertices, int bound, int edges) {
        int nrEdges = edges;
        graph = new int[vertices][vertices];
        Random r = new Random();
        for (int i = 0; i < vertices; i++)
            for (int j = 0; j < vertices; j++) {
                if (j < i)
                    graph[i][j] = graph[j][i];
                else if (i == j || nrEdges == 0)
                    graph[i][j] = 0;
                else {
                    graph[i][j] = r.nextInt(bound);
                    nrEdges--;
                }

            }
    }

    public Graph(int[][] manualInput){
        this.graph = manualInput;
    }

    public Graph(String filename){
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String line;
            int nr = 0;
            ArrayList<Integer> values = new ArrayList<>();

            while((line=in.readLine())!= null){
                String[] vals = line.split(" ");
                for (String str : vals)
                    values.add(Integer.parseInt(str));
                nr++;
            }
            graph = new int[nr][nr];
            for (int i = 0; i < nr; i++)
                for (int j = 0; j < nr; j++)
                    graph[i][j] = values.remove(0);
        }catch (IOException e){
            System.out.println("Reading file failed, generating random graph with 10 vertices");
            new Graph(10,20);
        }
    }


    @Override
    public String toString(){
        String result = "   ";
        for(int i = 0; i<graph.length; i++)
            result+=(char)(i+65)+"  ";
        result+="\n";
        for(int i=0; i<graph.length;i++){
            result+=(char)(i+65) + "  ";
            for(int j=0; j<graph.length;j++)
                if (graph[i][j]>9)
                    result+=graph[i][j] + " ";
                else result+=graph[i][j] + "  ";
            result+="\n";
        }
        return result;
    }

}
