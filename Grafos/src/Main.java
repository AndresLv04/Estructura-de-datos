import java.util.Scanner;

class Dijkstra {
    int[] distance;
    boolean[] visited;
    int[][] cost;

    public Dijkstra(int nodes) {
        distance = new int[nodes + 1];
        visited = new boolean[nodes + 1];
        cost = new int[nodes + 1][nodes + 1];
    }

    public void calc(int nodes, int source) {
        for (int i = 1; i <= nodes; i++) {
            distance[i] = cost[source][i];
            visited[i] = false;
        }

        distance[source] = 0;
        visited[source] = true;

        for (int count = 1; count <= nodes - 1; count++) {
            int minDistance = 999;
            int nextNode = -1;

            for (int i = 1; i <= nodes; i++) {
                if (!visited[i] && distance[i] < minDistance) {
                    minDistance = distance[i];
                    nextNode = i;
                }
            }

            visited[nextNode] = true;

            for (int i = 1; i <= nodes; i++) {
                if (!visited[i]) {
                    int newDistance = distance[nextNode] + cost[nextNode][i];
                    if (newDistance < distance[i]) {
                        distance[i] = newDistance;
                    }
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int nodes, source, i, j;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the Number of Nodes:");
        nodes = in.nextInt();

        Dijkstra d = new Dijkstra(nodes);

        System.out.println("Enter the Cost Matrix Weights:");
        for (i = 1; i <= nodes; i++) {
            for (j = 1; j <= nodes; j++) {
                d.cost[i][j] = in.nextInt();
                if (d.cost[i][j] == 0) {
                    d.cost[i][j] = 999; // 999 represents infinity
                }
            }
        }

        System.out.println("Enter the Source Vertex:");
        source = in.nextInt();

        d.calc(nodes, source);

        System.out.println("The Shortest Path from Source " + source + " to all other vertices are:");
        for (i = 1; i <= nodes; i++) {
            if (i != source) {
                System.out.println("Source: " + source + " Destination: " + i + " MinCost: " + d.distance[i]);
            }
        }

        in.close();
    }
}
