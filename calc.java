package calc;

import java.util.*;

class Ans {

	public Integer value;

	public Integer steps;

	Ans(Integer v, Integer s) {

		value = v;

		steps = s;

	}

	@Override

	public String toString() {

		return "Ans [value=" + value + ", steps=" + steps + "]";

	}

}

public class calc {

	public static Integer a;

	public static Integer b;

	public static Integer c;

	public static Integer t1;

	static Integer BFS()

	{

		// As the targe value could not be greater than 10^6 we can have

		// visited array of 10^6.

		boolean visited[] = new boolean[1000000];

		// Queue used in BFS.

		LinkedList<Ans> queue = new LinkedList<Ans>();

		visited[0] = true;

		queue.add(new Ans(0, 0));

		while (queue.size() != 0)

		{

			Ans s1 = queue.poll();

			// check if we have found the target value.

			// if so return the number of steps. As in BFS we will reach in

			// minimum number of steps.

			if (s1.value.equals(calc.t1))

				return s1.steps;

			// Add element in queue based on the condition.

			// Assuming next element to be current plus a

			Integer add = (s1.value + calc.a) % 1000000;

			// Assuming next element to be current multiplied by b

			Integer mult = (s1.value * calc.b) % 1000000;

			// Assuming next element to be current divided by c

			Integer divide = (s1.value / calc.c) % 1000000;

			if (!visited[add]) {

				visited[add] = true;

				queue.add(new Ans(add, s1.steps + 1));

			}

			if (!visited[mult]) {

				visited[mult] = true;

				queue.add(new Ans(mult, s1.steps + 1));

			}

			if (!visited[divide]) {

				visited[divide] = true;

				queue.add(new Ans(divide, s1.steps + 1));

			}

			// Add the element only if they are not visited.

			// Re adding elements will cause redundancy in computation.

		}

		// Once queue is empty return -1 as the target element is unreachable.

		return -1;

	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		// Take input from user.

		int t = in.nextInt();

		for (int i = 0; i < t; i++) {

			a = in.nextInt();

			b = in.nextInt();

			c = in.nextInt();

			t1 = in.nextInt();

			System.out.println("\n"+BFS()+"\n");

		}

		in.close();

	}

}