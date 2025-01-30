package ex04;
import ex03.*;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.*;


public class AdaptadorTeste {
	private Set<Integer> expected;
	private Adaptador<Integer> adapt;
	
	@BeforeEach
	public void setup() {
		expected = new HashSet<Integer>();
		expected.add(56);
		expected.add(9);
		
	}
	
	@AfterEach
	public void cleanup() {
		adapt = null;
		expected = null;
	}
	
	@Test
	public void ArrayListTest() {
		MulticonjuntoArrayList<Integer> alist = new MulticonjuntoArrayList<Integer>();
		alist.add(56);
		alist.add(9);
		adapt = new Adaptador<Integer>(alist);
		adapt.addAll(alist);
		adapt.addAll(alist);
		assertTrue(adapt.pegaSet().equals(expected));
	}
	
	@Test
	public void LinkedListTest() {
		MulticonjuntoLinkedList<Integer> alist = new MulticonjuntoLinkedList<Integer>();
		alist.add(56);
		alist.add(9);
		adapt = new Adaptador<Integer>(alist);
		adapt.addAll(alist);
		adapt.addAll(alist);
		assertTrue(adapt.pegaSet().equals(expected));
	}
	
	@Test
	public void SetTest() {
		MulticonjuntoSet<Integer> alist = new MulticonjuntoSet<Integer>();
		alist.add(56);
		alist.add(9);
		adapt = new Adaptador<Integer>(alist);
		adapt.addAll(alist);
		adapt.addAll(alist);
		assertTrue(adapt.pegaSet().equals(expected));
	}
	
	@Test
	public void StackTest() {
		MulticonjuntoStack<Integer> alist = new MulticonjuntoStack<Integer>();
		alist.add(56);
		alist.add(9);
		adapt = new Adaptador<Integer>(alist);
		adapt.addAll(alist);
		adapt.addAll(alist);
		assertTrue(adapt.pegaSet().equals(expected));
	}

}
