/*
Nathaniel Bates
COP 3503C - Fall 2016
11/20/2016
*/

import java.io.*;
import java.util.*;

public class BacktrackingTops{

// The intConvert helper method takes in an integer array along with its
// length and converts the number represented in the array into an integer
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

// The addToSet method takes in the LinkedList produced by each topological
// sort, along with the sorts HashSet, and the total number of vertices. The
// StringBuilder class is used to create a string from the LinkedList.
// The resulting string is then added to the HashSet sorts.

   public static void addToSet(LinkedList<Integer> topSort,
   HashSet<String> sorts, int vertices)
   {
      int k;
      String toposort;
      StringBuilder sort = new StringBuilder();
      ListIterator<Integer> vertex = topSort.listIterator();
      while(vertex.hasNext())
      {
         k = vertex.next();
         toposort = Integer.toString(k);
         sort.append(toposort);
         if(vertex.hasNext())
            sort.append(" ");
      }

      toposort = sort.toString();
      sorts.add(toposort);
   }

// The recursive backtracking method finds every possible topological sort.
// The base case is when the size of the LinkedList used to hold the vertex
// values of the sort reaches the total number of vertices. At this point a
// valid topological sort has been found, is passed to the addToSet function
// for conversion to a string, and added to the hash set.

   private static void findTopSorts(Integer [] incoming, HashSet<String> sorts,
   Integer [][] matrix, boolean [] usedVertex, LinkedList<Integer> topSort)
   {
      if(topSort.size() == (matrix.length))
      {
         addToSet(topSort, sorts, matrix.length);
         return;
      }

// For each vertex in the graph, if there are no incoming edges and it hasn't
// been visited yet, it is added to the LinkedList. When a vertex is added, the
// corresponding vertices the vertex is directed towards have their incoming
// edges degree counts decremented in the incoming array and the usedVertex
// array is marked true. 1 + i is added to reflect the actual vertex number.

      for(int i = 0; i < matrix.length; i++)
      {
         if(!usedVertex[i] && incoming[i] == 0)
         {
            topSort.add(i + 1);
            usedVertex[i] = true;
            for(int j = 0; j < matrix.length; j++)
               if(matrix[i][j] > 0)
                  incoming[j]--;

// The recursive call is made and this process continues until each vertex is
// added to LinkedList. On the "return trip", the state is restored to false,
// the incoming edges are incremented, and the last vertex that was added is
// removed. As a result all topological sorts can be found without repetitive
// permutations being added to the HashSet in the base case.

            findTopSorts(incoming, sorts, matrix, usedVertex, topSort);
            usedVertex[i] = false;
            for(int j = 0; j < matrix.length; j++)
               if(matrix[i][j] > 0)
                  incoming[j]++;

            topSort.removeLast();
         }
      }
   }

// The findTopSorts method takes in the adjacency matrix, incoming edges array,
// and the empty HashSet, sorts, that will contain any topological sorts.  The
// recursive "backtracking" function is called from within this helper method.
// The objects created in this method are used in the recursive method.

   public static HashSet<String> findTopSorts(Integer[][] matrix,
   Integer[] incoming, HashSet<String> sorts)
   {
      boolean [] usedVertex = new boolean[matrix.length];
      LinkedList<Integer> topSort = new LinkedList<Integer>();

      findTopSorts(incoming, sorts, matrix, usedVertex, topSort);

      return sorts;
   }

// The allTopologicalSorts methods reads in a graph from a file and determines
// all of the possible topological sorts that can be produced from the graph.

   public static HashSet<String> allTopologicalSorts(String filename)
   throws IOException
   {
      HashSet<String> sorts = new HashSet<String>();
      ArrayList<String> list = new ArrayList<String>();
      char y;
      int i, j, num, vertices = 0,  p = 0,
      vNum, cnt = 0, cnt2 = 0, vertex, x = 0;

// The try catch block handles exceptions that may occur. Each line of the
// file is read into an ArrayList which is later parsed through. If there is an
// invalid file name passed to the method, then the empty HashSet is returned.

      try
      {
         Scanner graph = new Scanner(new File(filename));
         while(graph.hasNextLine())
            list.add(graph.nextLine());
      }
      catch(IOException e)
      {
         return sorts;
      }

// The first line in the graph determines the number of vertices,
// then the following arrays can be set to the appropriate lengths.  The
// first for loop initializes all of the values in the arrays to 0.
// value array is a buffer used when calculating a vertex.

      String line = list.get(0);
      vertices = Integer.parseInt(line);

      Integer [][] matrix = new Integer[vertices][vertices];
      Integer [] value = new Integer[10];
      Integer [] incoming = new Integer[matrix.length];
      Integer [] outgoing = new Integer[matrix.length];

      for(i = 0; i < vertices; i++)
      {
         for(j = 0; j < vertices; j++)
            matrix[i][j] = 0;
         incoming[i] = 0;
         outgoing[i] = 0;
      }

// The following nested for loop parses each line of the input file that
// was stored in the ArrayList. The first while loop parses through the
// string to get to the first vertex in the adjacency list of the line.
// If 0 is read, then that vertex is not directed towards any other vertex.

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

// The loops traverse the matrix and the incoming and outgoing arrays are
// are incremented by the values in the matrix. Each index in the arrays
// correspond to a vertex and the value at the index specifies the degree
// of outgoing and incoming edges.

      for(i = 0; i < matrix.length; i++)
      {
         for(j = 0; j < matrix.length; j++)
         {
            outgoing[i] += matrix[i][j];
            incoming[i] += matrix[j][i];
         }
      }

// The loop traverses the incoming and outgoing arrays counting the number
// of vertices that have 0 incoming and 0 outgoing edges.  If there are 0
// vertices with 0 incoming edges or 0 vertices with 0 outgoing edges,
// then there are no topological sorts and the empty hash set is returned.

      for(i = 0; i < matrix.length; i++)
      {
         if(incoming[i] == 0)
            cnt++;
         if(outgoing[i] == 0)
            cnt2++;
      }

      if(cnt == 0 || cnt2 == 0)
        return sorts;

// Hashset containing every possible topological sort of the graph is returned.

      return findTopSorts(matrix, incoming, sorts);
   }
}
