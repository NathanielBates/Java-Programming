/*
Nathaniel Bates
COP 3503C - Fall 2016
11/06/2016
*/

import java.io.*;
import java.util.*;

public class TopologicalSorts{

// The numConvert helper method takes in a string consisting of numbers
// and converts the string to an integer representation of that string.
// Primarily used for getting the number of vertices in a graph.

   public static int numConvert(String line)
   {
      int i, num = 0, x = 1, value = 0;
      char y;
      for(i = line.length() - 1; i >= 0; i--)
      {
         y = line.charAt(i);
         num = Character.getNumericValue(y);
         value += (x * num);
         x *= 10;
      }

      return value;
   }

// The intConvert helper method takes in an integer array along with its
// length and converts the number represented in the array into an integer.
// Used for parsing a line in the input file and extracting the individual
// vertices that a certain vertex is directed towards.

   public static int intConvert(Integer [] line, int length)
   {
      int i, num = 0, x = 1, value = 0;
      for(i = length - 1; i >= 0; i--)
      {
         num = line[i];
         value += (x * num);
         x *= 10;
      }

      return value;
   }

// The hasTopoPath method determines if a graph represented by an input
// file has a topological sort that corresponds to a path within the graph.

   public static boolean hasTopoPath(String filename) throws IOException
   {
      Scanner graph = new Scanner(new File(filename));
      char y;
      int i, j, num, vertices = 0,  p = 0,
      vNum, cnt = 0, cnt2 = 0, vertex, x = 0;

      ArrayList<String> list = new ArrayList<String>();
      ArrayDeque<Integer> topSort = new ArrayDeque<Integer>();

// Each line of the file is read into an ArrayList which is later parsed
// through. The first line in the graph determines the number of vertices,
// then the following arrays can be set to the appropriate lengths.  The
// first for loop initializes all of the values in the arrays to 0.
// value array is a buffer used when calculating a vertex.

      while(graph.hasNextLine())
         list.add(graph.nextLine());

      String line = list.get(0);
      vertices = numConvert(line);

      Integer [][] matrix = new Integer[vertices][vertices];
      Integer [] value = new Integer[10];
      Integer [] edges = new Integer[vertices];
      Integer [] outGoing = new Integer[vertices];
      Integer [] path = new Integer[vertices];

      for(i = 0; i < vertices; i++)
      {
         for(j = 0; j < vertices; j++)
            matrix[i][j] = 0;
         edges[i] = 0;
         outGoing[i] = 0;
      }

// The following nested for loop parses each line of the input file that
// was stored in the ArrayList. The first while loop parses through the
// string to get to the first vertex in the adjacency list of the line.

      for(i = 1; i < list.size(); i++)
      {
         line = list.get(i);
         if(line.isEmpty())
            continue;

         y = line.charAt(0);
         num = Character.getNumericValue(y);
         if(num == 0)
            continue;

         while(line.charAt(x) != ' ')
            x++;

// The for loop parses through the rest of the line and converts each
// subsequent numeric portion of the string into integer form. The
// adjacency matrix is updated reflecting the outgoing edges for
// each vertex in the graph. As a result the incoming edges of a
// vertex are represented in the columns of the adjacency matrix, and the
// outgoing edges of a vertex are represented in the rows of the matrix.

         for(j = x; j < line.length(); j++)
         {
            if(line.charAt(j) != ' ')
            {
               y = line.charAt(j);
               num = Character.getNumericValue(y);
               value[p++] = num;
               if(j == line.length() - 1 || line.charAt(j + 1) == ' ')
               {
                  vNum = intConvert(value, p);
                  matrix[i - 1][vNum - 1]++;
                  p = 0;
               }
               else
                  continue;
            }
         }
         x = 0;
      }

// The double for loop loops through the matrix and enters the number of
// incoming and outgoing edges for each vertex and stores the values.

      for(i = 0; i < vertices; i++)
      {
         for(j = 0; j < vertices; j++)
         {
            if(matrix[i][j] > 0)
               outGoing[i] += matrix[i][j];

            if(matrix[j][i] > 0)
               edges[i] += matrix[j][i];
         }
      }

// The for loop goes through the incoming edges array and adds the vertex
// to the topSort queue if there are no incoming edges for that vertex.
// The outGoing array is checked for vertices that have zero outgoing edges.
// If more than one vertex has zero incoming edges or zero outgoing edges
// then there is no path between those vertices and no possible TopoPath.

      for(i = 0; i < vertices; i++)
      {
         if(edges[i] == 0)
         {
            topSort.add(i);
            cnt++;
         }
         if(outGoing[i] == 0)
            cnt2++;

         if(cnt > 1 || cnt2 > 1)
            return false;
      }

// The following topological sort algorithm was shown to us in class. It was
// altered a bit and includes an array that keeps track of the path for the
// the resulting topological sort if it exists in the graph.
// Thank you Dr. Szumlanski!

      p = cnt = 0;
      while(!topSort.isEmpty())
      {
         vertex = topSort.remove();
         path[p++] = vertex;
         ++cnt;

         for(i = 0; i < vertices; i++)
            if(matrix[vertex][i] > 0 && --edges[i] == 0)
               topSort.add(i);
      }

// If the count is not equal to the number of vertices in the graph then
// there is no topological sort in the graph, or a cyle is present.

      if(cnt != vertices)
         return false;

// The following loop checks the path array to see if the path that correlates
// to the topological sort of the graph is indeed an actual path in the graph.
// If the corresponding value in the adjacency matrix is equal to zero, then
// there is no edge between two vertices in the graph and false is returned.

      for(i = 0; i < path.length; i++)
         if((i + 1) < vertices)
            if(matrix[path[i]][path[i + 1]] == 0)
               return false;

      return true;
   }
}
