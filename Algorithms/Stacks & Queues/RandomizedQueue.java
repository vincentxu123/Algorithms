import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] arr;
	private int pointer = 0;
	
	public RandomizedQueue() {
		// construct an empty randomized queue
		arr = (Item[]) new Object[1];
	}
	
	public boolean isEmpty() {
		return pointer == 0;
		// is the queue empty?
	}
	
	public int size()  {
		return pointer;
		// return the number of items on the queue
	}
	
	public void enqueue(Item item) {
		// add the item
		if (item == null) {
			throw new IllegalArgumentException();
		}
		if (arr.length - 1 == pointer) {
			arr = changeSize(2 * arr.length);
		}
		arr[pointer] = item;
		pointer++;
	}
	
	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		int x = (int)(Math.random() * pointer);
		Item item = arr[x];
		pointer--;
		arr[x] = arr[pointer];
		arr[pointer] = null;
		if (pointer > 0 && pointer == arr.length/4) {
			changeSize(arr.length/2);
		}
		return item;
		// remove and return a random item
	}
	
	
	public Item sample() {
		int x = (int)(Math.random() * arr.length);
		return arr[x];
		// return (but do not remove) a random item
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator();
		// return an independent iterator over items in random order
	}
	
	private class ListIterator implements Iterator<Item> {
		 
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (pointer > 0);
		}



		@Override
		public Item next() {
			// TODO Auto-generated method stub
			if (pointer == 0) {
				throw new NoSuchElementException();
			}
			return dequeue();
		}



		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		
	}
	
	private Item[] changeSize(int size) {
		Item[] newArr = (Item []) new Object[size];
		for (int i = 0; i < pointer; i++) {
			newArr[i] = arr[i];
		}
		return newArr;
	}

	
	
	public static void main(String[] args) {
		// unit testing (optional)
		RandomizedQueue<String> rand = new RandomizedQueue<String>();
		rand.enqueue("Hello");
		rand.enqueue("My");
		rand.enqueue("Name");
		rand.enqueue("Is");
		rand.enqueue("Vincent");
		
//		System.out.println(rand.dequeue());
//		System.out.println(rand.size());
//		System.out.println(rand.dequeue());
//		System.out.println(rand.size());
//		System.out.println(rand.dequeue());
//		System.out.println(rand.size());
//		System.out.println(rand.dequeue());
//		System.out.println(rand.size());
//		System.out.println(rand.dequeue());
		
		
		Iterator<String> itr = rand.iterator();
		   while (itr.hasNext()) {
			   System.out.println(itr.next());
		   }
	}



	
	

}
