import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
   
   private Node first;
   private Node last;
   private int size = 0;
	
   public Deque()   {
	   first = last = null;
	   // construct an empty deque
   }
   
   private class Node {
	   Item item;
	   Node next;
	   Node previous;
   }
   
   public boolean isEmpty()   {
	   // is the deque empty?
	   return (size == 0);
   }
   
   public int size()   {
	   // return the number of items on the deque
	   return size;
   }
   
   public void addFirst(Item item)  {
	   // add the item to the front
	   if (item == null) {
		   throw new IllegalArgumentException("Nope");
	   }
		   Node oldFirst = first;
		   first = new Node();
		   first.item = item;
		   first.next = oldFirst;
		   size++;
   }
   
   public void addLast(Item item)  {
	   // add the item to the end
	   if (size <= 1) {
		   last = first;
	   }
	   if (item == null) {
		   throw new IllegalArgumentException("Nope");
	   }
	   Node oldLast = last;
	   last = new Node();
	   last.item = item;
	   last.next = null;
	   if (isEmpty()) {
		   first = last;
	   } else {
		   oldLast.next = last;
		   last.previous = oldLast;
	   }
	   size++;
	   
   }
   
   public Item removeFirst()  {
	   // remove and return the item from the front
	   if (isEmpty()) {
		   throw new NoSuchElementException("Don't do this");
	   }
	   Item item = first.item;
	   first = first.next;
	   size--;
	   return item;
   }
   
   public Item removeLast()  {
	   // remove and return the item from the end
	   if (isEmpty()) {
		   throw new NoSuchElementException("Don't do this");
	   }
	   
	   Item item = last.item;
	   Node temp = last.previous;
	   last = temp;
	   last.next = null;
	   size--;
	   return item;
	   
	   
   }
  
   
   public Iterator<Item> iterator()   {
	   // return an iterator over items in order from front to end
	   return new ListIterator();
   }
   
   private class ListIterator implements Iterator<Item> {
	   
	    private Node current = first;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (current != null);
		}
	
		@Override
		public Item next() {
			// TODO Auto-generated method stub
			if (!hasNext()) {
				throw new NoSuchElementException("Nope");
			}
			Item item = current.item;
			current = current.next;
			return item;
		}
	
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new NoSuchElementException("Don't do this");
			
		}
	   
  }
   
   public static void main(String[] args)  {
	   // unit testing (optional)
	   Deque<String> deq = new Deque<String>();
	   deq.addFirst("Name");
	   deq.addLast("Is");
	   deq.addLast("Vincent");
	   deq.addFirst("My");
	   deq.addLast("Xu");
	   
//	   deq.removeFirst();
//	   deq.removeLast();
	   
	   
	   
	   Iterator<String> itr = deq.iterator();
	   while (itr.hasNext()) {
		   System.out.println(itr.next());
	   }
   }
}