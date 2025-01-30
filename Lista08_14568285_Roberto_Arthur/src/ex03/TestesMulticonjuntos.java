package ex03;
import static org.junit.Assert.*;


import org.junit.jupiter.api.*;


public class TestesMulticonjuntos {

	@Test
	public void ArrayListTest() {
		MulticonjuntoArrayList<Integer> expected = new MulticonjuntoArrayList<Integer>();
		expected.add(56);
		expected.add(9);
		expected.add(56);
		expected.add(9);
		MulticonjuntoArrayList<Integer> alist = new MulticonjuntoArrayList<Integer>();
		alist.add(56);
		alist.add(9);
		MulticonjuntoArrayList<Integer> clone2x = new MulticonjuntoArrayList<Integer>();
		clone2x.addAll(alist);
		clone2x.addAll(alist);
		assertTrue(clone2x.equals(expected));
	}
	
	@Test
	public void LinkedListTest() {
		MulticonjuntoLinkedList<Integer> expected = new MulticonjuntoLinkedList<Integer>();
		expected.add(56);
		expected.add(9);
		expected.add(56);
		expected.add(9);
		MulticonjuntoLinkedList<Integer> linklist = new MulticonjuntoLinkedList<Integer>();
		linklist.add(56);
		linklist.add(9);
		MulticonjuntoLinkedList<Integer> clone2x = new MulticonjuntoLinkedList<Integer>();
		clone2x.addAll(linklist);
		clone2x.addAll(linklist);
		assertTrue(clone2x.equals(expected));
	}
	
	@Test
	public void SetTest() {
		MulticonjuntoSet<Integer> expected = new MulticonjuntoSet<Integer>();
		expected.add(56);
		expected.add(9);
		expected.add(56);
		expected.add(9);
		MulticonjuntoSet<Integer> set = new MulticonjuntoSet<Integer>();
		set.add(56);
		set.add(9);
		MulticonjuntoSet<Integer> clone2x = new MulticonjuntoSet<Integer>();
		clone2x.addAll(set);
		clone2x.addAll(set);
		assertTrue(clone2x.equals(expected));
	}
	
	@Test
	public void StackTest() {
		MulticonjuntoStack<Integer> expected = new MulticonjuntoStack<Integer>();
		expected.add(56);
		expected.add(9);
		expected.add(56);
		expected.add(9);
		MulticonjuntoStack<Integer> set = new MulticonjuntoStack<Integer>();
		set.add(56);
		set.add(9);
		MulticonjuntoStack<Integer> clone2x = new MulticonjuntoStack<Integer>();
		clone2x.addAll(set);
		clone2x.addAll(set);
		assertTrue(clone2x.equals(expected));
	}
}
