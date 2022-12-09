package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InvalidObjectException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class PathFinder {

  private static class Vertex {
    private String data;
    private int row, col;
    private boolean visited = false;
    private Vertex cameFrom = null;

    Vertex(String data, int row, int col) {
      this.data = data;
      this.row = row;
      this.col = col;
    }

    /**
     * Helper method used to get vertices adjacent to the current vertex
     * @param maze the maze represented by 2D array
     * @param rows rows of 2D array
     * @param cols columns of 2D array
     * @return array of adjacent vertices
     */
    public Vertex[] getNeighbors(Vertex[][] maze, int rows, int cols) {
      Vertex[] neighbors = new Vertex[4]; // up down left right
      int index = 0; // index
      for (int i = -1; i <= 1; i++) { // row
        for (int j = -1; j <= 1; j++) { // col
          if (i == 0 && j == 0) continue;
          else if (i == 0 || j == 0) { // adjacent to that vertex
            Vertex neighbor;
            if (this.row + i > rows || this.row + i < 0 || this.col + j >= cols || this.col + j < 0) { // exceed bound
              neighbor = null;
            } else {
              neighbor = maze[this.row + i][this.col + j];
            }
            if (neighbor == null || neighbor.data.equals("X")) {
              neighbors[index] = null;
            } else {
              neighbors[index] = neighbor;
            }
            index++;
          }
        }
      }
      return neighbors;
    }
  }

  private static class Graph {
    private Vertex[][] maze;
    private int rows, cols; // rows and columns of the maze
    private Vertex start, goal;

    // Graph constructor using maze text file
    Graph(String filename) throws FileNotFoundException {
//      this.start = null;
//      this.goal = null;
      try {
        Scanner sc = new Scanner(new File(filename));
        String dimension = sc.nextLine(); // 1st row, dimensions of maze
        String[] dimensions = dimension.split(" ");
        try {
          this.rows = Integer.parseInt(dimensions[0]);
          this.cols = Integer.parseInt(dimensions[1]);
        } catch (NumberFormatException e) {
          e.printStackTrace();
          throw new NumberFormatException("Invalid # of rows / cols");
        }
        this.maze = new Vertex[this.rows][this.cols];
        // write in 2d vertex array, fill the maze with vertices
        for (int i = 0; i < this.rows; i++) {
          String[] rowI = sc.nextLine().split(""); // array contains all the vertices in one row
          for (int j = 0; j< this.cols; j++) {
            this.maze[i][j] = new Vertex(rowI[j], i, j);
            if (rowI[j].equals("S")) {
              if (this.start != null) throw new InvalidObjectException("Start point already exists");
              this.start = this.maze[i][j];
            }
            if (rowI[j].equals("G")) {
              if (this.goal != null) throw new InvalidObjectException("Goal point already exists");
              this.goal = this.maze[i][j];
            }
          }
        }
        if (this.goal == null || this.start == null) throw new InvalidObjectException("No start or goal point");
      } catch (FileNotFoundException | InvalidObjectException e) {
        e.printStackTrace();
        throw new FileNotFoundException("Fle not exist");
      }
    }

    /**
     * Helper function used to find the shortest path between two vertices
     * @param start starting vertex
     * @param goal ending vertex
     */
    public void findPath(Vertex start, Vertex goal) {
      start.visited = true;
      LinkedList<Vertex> vertices = new LinkedList<>();
      vertices.add(start);
      while (!vertices.isEmpty()) {
        Vertex current = vertices.remove();
        if (current.equals(goal)) { // go back
          while (!current.cameFrom.equals(start)) {
            current.cameFrom.data = ".";
            current = current.cameFrom;
          }
          return;
        }
        Vertex[] currentNeighbors = current.getNeighbors(this.maze, this.rows, this.cols);
        for (int i = 0; i < currentNeighbors.length; i++) {
          if (currentNeighbors[i] != null && !currentNeighbors[i].visited) {
            Vertex nextVertex = currentNeighbors[i];
            nextVertex.visited = true;
            nextVertex.cameFrom = current;
            vertices.add(nextVertex);
          }
        }
      }
    }

    public void writeFile(String filename) throws FileNotFoundException {
      PrintWriter pw = new PrintWriter(filename);
      pw.println(this.rows + " " + this.cols); // first row
      for (int i = 0; i < this.rows; i++) {
        String s = "";
        for (int j = 0; j < this.cols; j++) {
          s += this.maze[i][j].data;
        }
        pw.println(s);
      }
      pw.flush();
      pw.close();
    }
  }

  public static void solveMaze(String inputFile, String outputFile) throws FileNotFoundException {
    Graph mazeGraph = new Graph(inputFile);
    mazeGraph.findPath(mazeGraph.start, mazeGraph.goal);
    mazeGraph.writeFile(outputFile);
  }
}
