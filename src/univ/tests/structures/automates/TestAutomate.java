package univ.tests.structures.automates;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import univ.structures.automates.Automate;
import univ.structures.automates.IAutomate;
import univ.structures.automates.IAutomate.GenEtat;
import univ.structures.automates.IEtat;
import univ.structures.automates.IEtatDFA;
import univ.structures.automates.IEtatNFA;
import univ.structures.automates.ITransition;
import univ.structures.automates.Transition;

public class TestAutomate {
	
	IAutomate<Character, IEtatNFA<Character>> auto;

	@Before
	public void setUp() throws Exception {
		auto = new Automate<Character, IEtatNFA<Character>>(GenEtat.NFA);
	}
	
	@Test
	public void testAddSymbole() {
		auto.addSymbole('a');
		assertTrue(auto.getAlphabet().contains('a'));
	}

	@Test
	public void testCreateEtat() {
		IEtat<Character> e = auto.createEtat();
		
		assertFalse(e == null);
		assertTrue(e.getId() == 0);
	}

	@Test
	public void testAddTransition() {
		IEtat<Character> e1, e2;
		ITransition<Character> t;
		
		e1 = auto.createEtat();
		e2 = auto.createEtat();
		auto.addSymbole('a');
		t = new Transition<Character>(e1, e2, 'a');
		
		auto.addTransition(t);
		
		assertTrue(auto.getPaires().contains(t));
	}
	
	@Test
	public void testAddTransition2() {
		Automate<Character, IEtatDFA<Character>> auto = new Automate<Character, IEtatDFA<Character>>(GenEtat.DFA);
		IEtat<Character> 	e1 = auto.createEtat(),
							e2 = auto.createEtat();
		
		auto.addSymbole('a');
		
		auto.addTransition(new Transition<Character>(e1, e2, 'a'));
		try {
			auto.addTransition(new Transition<Character>(e1, e2, 'a'));
			fail();
		} catch (IllegalStateException e) {	}
		
		
		try {
			auto.addTransition(new Transition<Character>(e1, e1, 'a'));
			fail();
		} catch (IllegalStateException e) {	}
	}

	@Test
	public void testSetInitial() {
		IEtatNFA<Character> e1 = auto.createEtat(),
							e2 = auto.createEtat();
		
		auto.setInitial(e1);
		assertTrue(auto.getInitial().equals(e1));
		auto.setInitial(e2);
		assertTrue(auto.getInitial().equals(e2));
	}

	@Test
	public void testSetFinalE() {
		IEtatNFA<Character> e1 = auto.createEtat(),
							e2 = auto.createEtat();

		auto.setFinal(e1);
		assertTrue(auto.getEtatsFinaux().contains(e1));
		auto.setFinal(e2);
		assertTrue(auto.getEtatsFinaux().contains(e1));
		assertTrue(auto.getEtatsFinaux().contains(e2));
		
		auto.removeEtat(e1);
		assertFalse(auto.getEtatsFinaux().contains(e1));
		assertTrue(auto.getEtatsFinaux().contains(e2));
	}

	@Test
	public void testSetFinalEBoolean() {
		IEtatNFA<Character> e1 = auto.createEtat(),
							e2 = auto.createEtat();

		auto.setFinal(e1, true);
		assertTrue(auto.getEtatsFinaux().contains(e1));
		auto.setFinal(e2, true);
		assertTrue(auto.getEtatsFinaux().contains(e1));
		assertTrue(auto.getEtatsFinaux().contains(e2));
		
		auto.setFinal(e1, false);
		assertFalse(auto.getEtatsFinaux().contains(e1));
		assertTrue(auto.getEtatsFinaux().contains(e2));
	}

	@Test
	public void testRemoveEtat() {
		IEtatNFA<Character> e1 = auto.createEtat(),
							e2 = auto.createEtat();

		e1.setFinal();
		e1.setInitial();
		auto.removeEtat(e1);
		assertFalse(auto.getEtats().contains(e1));
		
		e2.setFinal();
		auto.removeEtat(e2);
		assertFalse(auto.getEtatsFinaux().contains(e2));
	}

	@Test
	public void testRemoveTransition() {
		IEtatNFA<Character> e1 = auto.createEtat(),
							e2 = auto.createEtat();
		auto.addSymbole('a');
		ITransition<Character> t = new Transition<Character>(e1, e2, 'a');
		auto.addTransition(t);
		assertTrue(auto.getPaires().contains(t));
		auto.removeTransition(t);
		assertFalse(auto.getPaires().contains(t));
	}

	@Test
	public void testGetAlphabet() {
		assertTrue(auto.getAlphabet().isEmpty());
		auto.addSymbole('a');
		assertTrue(auto.getAlphabet().size() == 1);
	}

	@Test
	public void testGetInitial() {
		IEtatNFA<Character> e1 = auto.createEtat(),
							e2 = auto.createEtat();
		
		assertTrue(auto.getInitial() == null);
		e1.setInitial();
		assertTrue(auto.getInitial().equals(e1));
		e2.setInitial();
		assertTrue(auto.getInitial().equals(e2));
	}

	@Test
	public void testGetEtats() {
		IEtatNFA<Character> e1 = auto.createEtat(),
							e2 = auto.createEtat();
		
		assertTrue(auto.getEtats().contains(e1));
		assertTrue(auto.getEtats().contains(e2));
		assertTrue(auto.getEtats().size() == 2);
		
		auto.removeEtat(e1);
		assertFalse(auto.getEtats().contains(e1));
		assertTrue(auto.getEtats().size() == 1);
	}

	@Test
	public void testGetEtatsFinaux() {
		IEtatNFA<Character> e1 = auto.createEtat(),
							e2 = auto.createEtat();
		
		assertFalse(auto.getEtatsFinaux().contains(e1));
		assertFalse(auto.getEtatsFinaux().contains(e2));
		assertTrue(auto.getEtatsFinaux().size() == 0);
		
		e2.setFinal();
		assertTrue(auto.getEtatsFinaux().contains(e2));
		e2.setFinal(false);
		assertFalse(auto.getEtatsFinaux().contains(e2));
	}	

}
