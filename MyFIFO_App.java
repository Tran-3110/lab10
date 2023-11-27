package main;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MyFIFO_App {
		// method stutter that accepts a queue of integers as
		// a parameter and replaces
		// every element of the queue with two copies of that
		// element
		// front [1, 2, 3] back
		// becomes
		// front [1, 1, 2, 2, 3, 3] back
		public static <E> void stutter(Queue<E> input) {
			Queue<E> tamp = new ConcurrentLinkedQueue<>();
			while(!input.isEmpty()) {
				tamp.add(input.peek());
				tamp.add(input.poll());
			}
			input = tamp;
			System.out.println(input);
		}
		
		// Method mirror that accepts a queue of strings as a 
		//parameter and appends the
		// queue's contents to itself in reverse order
		// front [a, b, c] back
		// becomes
		// front [a, b, c, c, b, a] back
		public static <E> void mirror(Queue<E> input) {
			Queue<E> tamp = new ConcurrentLinkedQueue<>(input);
			Stack<E> stack = new Stack<>();
			while(!tamp.isEmpty()) {
				stack.push(tamp.poll());
			}
			while(!stack.isEmpty()) {
				input.add(stack.pop());
			}
			System.out.println(input);
		}


	public static void main(String[] args) {
		Queue<Integer> queue = new ConcurrentLinkedQueue<>();
		queue.add(1);
		queue.add(3);
		queue.add(5);
		queue.add(2);
//		System.out.println(queue);
//		System.out.println(queue.poll());
//		System.out.println(queue.poll());
//		System.out.println(queue.poll());
//		System.out.println(queue.poll());
//		System.out.println(queue.poll());
//		System.out.println(queue.poll());
//		System.out.println(queue.poll());
//		System.out.println(queue.poll());
		stutter(queue);
		mirror(queue);
	}

}
