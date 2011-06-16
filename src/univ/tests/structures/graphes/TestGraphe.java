package univ.tests.structures.graphes;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import univ.structures.graphes.Arc;
import univ.structures.graphes.Graphe;
import univ.structures.graphes.IArc;
import univ.structures.graphes.IArcValue;
import univ.structures.graphes.IArete;
import univ.structures.graphes.IAreteValue;
import univ.structures.graphes.IGraphe;
import univ.structures.graphes.ISommet;

/**
 * <h2>Test de la classe Graphe</h2>
 * 
 *  Graphe 1 : <S,A> avec 
 *  <ul>
 *  	<li> S = [0, 1, 2, 3, 4, 5]
 *  	<li> A = [{0,0}, {0,1}, {1, 2}, {3, 4}]
 *  </ul> 
 * @author EliX
 *
 */
public class TestGraphe {
	
	private IGraphe<IArc> graphe;
	ISommet<IArc> s0, s1, s2, s3, s4, s5;
	IArc a00, a01, a12, a34;

	@Before
	public void setUp() throws Exception {
		graphe = new Graphe<IArc>(IArc.class);
		s0 = graphe.createSommet();
		s1 = graphe.createSommet();
		s2 = graphe.createSommet();
		s3 = graphe.createSommet();
		s4 = graphe.createSommet();
		s5 = graphe.createSommet();
		
		graphe.addPaire(a00 = new Arc(s0, s0));
		graphe.addPaire(a01 = new Arc(s0, s1));
		graphe.addPaire(a12 = new Arc(s1, s2));
		graphe.addPaire(a34 = new Arc(s3, s4));
	}
	
	@Test
	public void testGraphe() {
		try {
			new Graphe<IAreteValue<Integer>>(IArc.class);
			fail("cette configuration ne devrait pas exister");
		} catch(Exception e) {}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetSommets() {
		assertTrue("nb d'éléments", graphe.getSommets().size() == 6);
		Set<ISommet<IArc>> set = new HashSet<ISommet<IArc>>();
		Collections.addAll(set, s0, s2, s1, s4, s5, s3);
		assertTrue("éléments", graphe.getSommets().containsAll(set));
	}

	@Test
	public void testGetPaires() {
		assertTrue("nb d'éléments", graphe.getPaires().size() == 4);
		Set<IArc> set = new HashSet<IArc>();
		Collections.addAll(set, a00, a01, a12, a34);
		assertTrue("éléments", graphe.getPaires().containsAll(set));
	}

	@Test
	public void testAddPaire() {
		int old = graphe.getPaires().size();
		IArc s10 = new Arc(s1, s0);
		assertTrue(graphe.getPaires().contains(s10));
		graphe.addPaire(s10);
		assertTrue(old == graphe.getPaires().size());
	}

	@Test
	public void testRemoveSommet() {
		int old = graphe.getSommets().size(),
			oldp = graphe.getPaires().size();
		graphe.removeSommet(s4);
		assertTrue(graphe.getSommets().size() == old - 1);
		assertTrue(graphe.getPaires().size() == oldp - 1);
		assertFalse(graphe.getPaires().contains(a34));
		
		graphe.removeSommet(s0);
		assertTrue(graphe.getSommets().size() == old - 2);
		assertTrue(graphe.getPaires().size() == oldp - 3);
		assertFalse(graphe.getPaires().contains(a00));
		assertFalse(graphe.getPaires().contains(a01));
	}

	@Test
	public void testRemovePaire() {
		graphe.removePaire(a00);
		assertFalse(graphe.getPaires().contains(a00));
		graphe.removePaire(a01);
		IArc s10 = new Arc(s1, s0);
		assertFalse(graphe.getPaires().contains(s10));
	}

	@Test
	public void testIsValue() {
		assertFalse(graphe.isValue());
		
		IGraphe<IArete> graphe2 = new Graphe<IArete>(IArete.class);
		assertFalse(graphe2.isValue());
		
		IGraphe<IAreteValue<String>> graphe3 = new Graphe<IAreteValue<String>>(IAreteValue.class);
		assertTrue(graphe3.isValue());
		
		IGraphe<IArcValue<Set<Number>>> graphe4 = new Graphe<IArcValue<Set<Number>>>(IArcValue.class);
		assertTrue(graphe4.isValue());
	}

	@Test
	public void testIsOriente() {
		assertFalse(graphe.isOriente());
		
		IGraphe<IArete> graphe2 = new Graphe<IArete>(IArete.class);
		assertTrue(graphe2.isOriente());
		
		IGraphe<IAreteValue<String>> graphe3 = new Graphe<IAreteValue<String>>(IAreteValue.class);
		assertTrue(graphe3.isOriente());
		
		IGraphe<IArcValue<Set<Number>>> graphe4 = new Graphe<IArcValue<Set<Number>>>(IArcValue.class);
		assertFalse(graphe4.isOriente());
	}

}
