// Nathaniel Bates
// SkipList Assignment
// 10/16/2016

import java.io.*;
import java.util.*;

// The Node class contains two seperate constructors: one that takes in
// data as a parameter along with the height for a node in the skip list,
// and one that just takes in a specified height which is used to create
// the head node of the skip list.  Null references are added to each node
// in an ArrayList that will reference the next nodes at corresponding levels.

class Node<AnyType>
{
   int height;
   AnyType value;
   int maxLevel;
   ArrayList<Node<AnyType>> pointers;

   Node(int height){

   this.height = height;
   this.value = null;
   this.pointers = new ArrayList<Node<AnyType>>(height);
   this.maxLevel = height - 1;
   for(int i = 0; i < height; i++)
      this.pointers.add(i, null);
   }

   Node(AnyType data, int height){

   this.height = height;
   this.value = data;
   this.maxLevel = height - 1;
   this.pointers = new ArrayList<Node<AnyType>>(height);
   for(int i = 0; i < height; i++)
      this.pointers.add(i, null);
   }

// Two O(1) methods that return the height and value at any paricular node.

   public AnyType value(){

   return this.value;
   }

   public int height(){

   return this.height;
   }

// The next method returns the next node that is being referenced from an
// individual node in the skip list. The level parameter must be between
// 0 (the bottom level) and the current maximum level of the node.
// The maximum level for each node is one less than it's current height.

   public Node<AnyType> next(int level){

   if(level < 0 || level > this.maxLevel)
      return null;
   if(this.pointers.get(level) == null)
      return null;

   return this.pointers.get(level);
   }

// The Grow method increases the height of the node by one and adds a new
// reference to next node in the skip list and sets the reference to null.

   public void Grow(){

   this.pointers.ensureCapacity(this.height + 1);
   this.pointers.add(this.height, null);
   this.height++;
   this.maxLevel++;
   }
}

// The SkipList class contains two constructors: one that does not take in
// an initial height parameter which is set to a height of 1 by default.
// The other constructor creates a skip list with a head node at that height;
// SkipList is a subclass of Comparable so any other class that implements the
// Comparable interface can use the SkipList class for their objects.

public class ProbabilisticLinkedList<AnyType extends Comparable<AnyType>>
{
   int height, size;
   Node<AnyType> head;

   ProbabilisticLinkedList(){
   head = new Node<AnyType>(1);
   this.height = 1;
   this.size = 0;
   }

   ProbabilisticLinkedList(int height){
   if(height < 1)
   {
      head = new Node<AnyType>(1);
      this.height = 1;
      this.size = 0;
   }

   head = new Node<AnyType>(height);
   this.height = height;
   this.size = 0;
   }

// The following O(1) methods return the current size, height, and the head
// node of the skip list. No parameters needed for these methods.

   public int size(){

   return this.size;
   }

   public int height(){

   return this.height;
   }

   public Node<AnyType> head(){

   return this.head;
   }

// This method generates a random height of a new node being inserted into the
// skip list. The current maximum height is the largest value that can be
// returned for proper implementation of a probabilistic skip list. A variable
// is initialized to 0, and however many "rolls of the die" to land a 1 will
// be the height of the new node being inserted into the skip list. Unless
// that number is greater than the maxHeight, then maxHeight is returned.

   public int generateRandomHeight(int maxHeight){

   int count = 0, roll = 0;
   while(roll == 0)
   {
      Random rand = new Random();
      roll = rand.nextInt(2);
      count++;
   }

   if(count > maxHeight)
      return maxHeight;
   else
      return count;
   }

// This insert method takes in data and generates the random height of the new
// node with the method above.  Anytime a new node is inserted, the size of the
// skip list increases by 1 and is checked to see if the overall height of
// the skip list needs to be increased, along with the head node.

   public void insert(AnyType data){

   int r = 0, level;
   double newHeight = 0;
   Node<AnyType> temp, prev, drop;
   boolean updated = false;

   this.size++;
   newHeight = (Math.log(this.size) / Math.log(2));
   newHeight = Math.ceil(newHeight);

// If the insertion causes the height of the skip list to grow, then the
// Grow method is used upon the head node. We then traverse the current
// maximum level of the skip list.  There is a 50% chance that the nodes
// that were at maximum height will have their height increased by 1.
// The Grow method is used at each of the nodes that will be increased.

   if(this.height < newHeight)
   {
      this.height++;
      this.head.Grow();
      prev = head;
      temp = head.next(head.maxLevel - 1);
      while(temp != null)
      {
         Random rand = new Random();
         r = rand.nextInt(2);
         if(r == 1)
         {
            temp.Grow();
            prev.pointers.set(head.maxLevel, temp);
            prev = temp;
         }

         temp = temp.next(head.maxLevel - 1);
      }
   }

// The height of the node being inserted will be generated and a new node
// is created for the data being passed in. We begin the traversal of the
// skip list at the maximum level and continue to the bottom level referencing
// the new node at each of it's "previous" node's ArrayList reference point.
// That point will be at the current level being traversed.

   r = generateRandomHeight(head.height);
   Node<AnyType> newNode = new Node<AnyType>(data, r);
   level = head.maxLevel;
   prev = temp = head;

// The prev node will hold the node that comes right before the node that
// temp is holding. As long as the compareTo method returns a value less than
// 0, the list is traversed at the current level. The newNode.maxLevel is
// checked against the current level since any levels above the maxLevel of
// the new nodes cannot be used. When a value greater than or equal to 0 is
// returned by the compareTo method, the node is inserted at prev's
// ArrayList next reference corresponding to the current level.

   while(level >= 0)
   {
      if(temp.next(level) == null && level <= newNode.maxLevel)
         temp.pointers.set(level, newNode);
      else
      {
         if(temp.next(level) != null)
         {
            temp = temp.next(level);
            while(temp.value.compareTo(newNode.value) < 0)
            {
               if(temp.next(level) != null)
               {
                  prev = temp;
                  temp = temp.pointers.get(level);
               }
               else
               {
                  if(level <= newNode.maxLevel)
                  {
                     temp.pointers.set(level, newNode);
                     updated = true;
                  }
                  break;
               }
            }

// The updated flag tells us that insertion at the end occurred. Meaning the
// new node's next reference at this level is null. Since temp is the
// "traversal node", it is set to prev to begin the same process at the next
// level down. Level is decremented and continue is used here.

            if(updated == true)
            {
               updated = false;
               temp = prev;
               level--;
               continue;
            }

// This handles insertion in between two nodes at the corresponding level
// in the skip list. The temp node is set to prev, and level is decremented.

            if(level <= newNode.maxLevel)
            {
               prev.pointers.set(level, newNode);
               newNode.pointers.set(level, temp);
            }
         }
      }
      temp = prev;
      level--;
   }
   }

// This method is the exact same as the one above except that a height is
// specified so a random height does not need to be generated for the new
// node. The insertion algorithm is the exact same that is used above as well.

   public void insert(AnyType data, int height){
   double newHeight = 0;
   int level, r;
   Node<AnyType> temp, prev;
   Node<AnyType> newNode = new Node<AnyType>(data, height);
   boolean updated = false;
   this.size++;
   newHeight = (Math.log(this.size) / Math.log(2));
   newHeight = Math.ceil(newHeight);

   if(this.height < newHeight)
   {
      this.height++;
      this.head.Grow();
      prev = head;
      temp = head.next(head.maxLevel - 1);
      while(temp != null)
      {
         Random rand = new Random();
         r = rand.nextInt(2);
         if(r == 1)
         {
            temp.Grow();
            prev.pointers.set(head.maxLevel, temp);
            prev = temp;
         }

         temp = temp.next(head.maxLevel - 1);
      }
   }

   level = head.maxLevel;
   prev = temp = head;

   while(level >= 0)
   {
      if(temp.next(level) == null && level <= newNode.maxLevel)
         temp.pointers.set(level, newNode);
      else
      {
         if(temp.next(level) != null)
         {
            temp = temp.next(level);
            while(temp.value.compareTo(newNode.value) < 0)
            {
               if(temp.next(level) != null)
               {
                  prev = temp;
                  temp = temp.pointers.get(level);
               }
               else
               {
                  if(level <= newNode.maxLevel)
                  {
                     temp.pointers.set(level, newNode);
                     updated = true;
                  }
                  break;
               }
            }

            if(updated == true)
            {
               updated = false;
               temp = prev;
               level--;
               continue;
            }

            if(level <= newNode.maxLevel)
            {
               prev.pointers.set(level, newNode);
               newNode.pointers.set(level, temp);
            }
         }
      }
      temp = prev;
      level--;
   }
   }

// The delete method follows the same alogrithm that is used for insertion
// in order to find the node that is to be deleted. If deletion causes the
// the height of the skip list to decrease, then the nodes at that current
// height will be decreased. Otherwise the nodes heights stay the same.
// Also if deletion causes the size to be 0 or 1, it is updated accordingly.

   public void delete(AnyType data){

   int level = head.maxLevel, finalHeight;
   int dupCheck = 0;
   double newHeight;
   boolean found = false;
   Node<AnyType> temp, prev, first = null;

// This follows the insertion algorithm for the most part except no references
// are changed.  The "first" node (declared above) is set to the first
// occurrence of the data being searched for (assuming there is a duplicate).
// This is why every level is checked down to the bottom of the skip list.
// First is initialized to null, so after traversing the skip list if it is
// still null, then the data to be deleted is not in the skip list.

   temp = prev = head;
   while(level >= 0)
   {
      if(temp.next(level) != null)
      {
         temp = temp.next(level);
         while(temp != null)
         {
            if(temp.value.compareTo(data) < 0)
            {
               prev = temp;
               temp = temp.next(level);
            }
            else if(temp.value.compareTo(data) > 0)
            {
               temp = prev;
               break;
            }
            else
            {
               first = temp;
               break;
            }
         }
      }
      temp = prev;
      level--;
   }

// If first is not null, then the first occurence of the specified data has
// been found. We then start at the head at the maxLevel of the node to be
// deleted. We update all of the reference points in the skip list that point
// to that node to point to the node that follows the specified node to be
// deleted. If it is the last node at the current level being traversed,
// then null is referenced in the prev's next reference at that level.

   if(first != null)
   {
      dupCheck = first.maxLevel;
      temp = prev = head;
      while(dupCheck >= 0)
      {
         if(temp.next(dupCheck) != null)
         {
            temp = temp.next(dupCheck);
            while(temp != null)
            {
               if(temp.value.compareTo(first.value) < 0)
               {
                  prev = temp;
                  temp = temp.next(dupCheck);
               }
               else
               {
                  if(temp.next(dupCheck) == null)
                     prev.pointers.set(dupCheck, null);
                  else
                     prev.pointers.set(dupCheck, temp.next(dupCheck));
                  break;
               }
            }

            dupCheck--;
            temp = prev;
         }
      }

// If the deletion causes the skip list height to be decreased, then the
// new height is determined and applied to the skip list. If the new height is
// less than the current height, the head height is decreased along with every
// node that had that height. The reference point in that node's ArrayList at
// the old maxLevel is removed and the level before it becomes the new maxLevel.
// All nodes at the old height are then updated to the new maxLevel values.

      this.size--;
      if(this.size > 0)
      {
         newHeight = (Math.log(this.size) / Math.log(2));
         newHeight = Math.ceil(newHeight);
         finalHeight = (int)newHeight;

         if(finalHeight < this.height)
         {
            level = head.maxLevel;
            while(level >= (finalHeight - 1))
            {
               temp = head.next(level);
               while(temp != null)
               {
                  if(temp.height > finalHeight)
                  {
                     temp.height = finalHeight;
                     temp.pointers.remove(level);
                     temp.maxLevel = temp.height - 1;
                  }
                  temp = temp.next(level);
               }
               level--;
            }

// If the size of the skip list is decreased to 1, then height is set to the 1
// along with the height of the head. Otherwise the new skip list height, head
// height, and new maxLevel is set.

            if(this.size == 1)
            {
               this.height = head.height = 1;
               head.maxLevel = 0;
            }
            else
            {
               this.height = head.height = finalHeight;
               head.maxLevel = head.height - 1;
            }
         }
      }

// If deletion caused the size to be 0, then these values are set.

      else
      {
         head.pointers.set(0, null);
         this.height = head.height = 1;
      }
   }
   }

// The contains method searches through the skip list with the same algorithm
// that is used for insertion and deletion.  True is returned if the specified
// data is found, false is returned if it is not found.

   public boolean contains(AnyType data){

   int level = head.maxLevel;
   Node<AnyType> temp, prev;

   temp = prev = head;

   while(level >= 0)
   {
      if(temp.next(level) != null)
      {
         temp = temp.next(level);
         while(temp != null)
         {
            if(temp.value.compareTo(data) < 0)
            {
               prev = temp;
               temp = temp.next(level);
            }
            else if(temp.value.compareTo(data) > 0)
            {
               temp = prev;
               break;
            }
            else
               return true;
         }
      }

      temp = prev;
      level--;
   }

   return false;
   }
}
