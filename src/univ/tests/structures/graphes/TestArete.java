package univ.tests.structures.graphes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import univ.structures.graphes.Arc;
import univ.structures.graphes.Arete;
import univ.structures.graphes.Graphe;
import univ.structures.graphes.IArc;
import univ.structures.graphes.IArete;
import univ.structures.graphes.IGraphe;
import univ.structures.graphes.ISommet;

public class TestArete {
	
	private IGraphe<IArete> graphe;
	ISommet<IArete> s0, s1, s2, s3, s4, s5;
	IArete a00, a01, a12, a34;

	@Before
	public void setUp() throws Exception {
		graphe = new Graphe<IArete>(IArete.class);
		s0 = graphe.createSommet();
		s1 = graphe.createSommet();
		s2 = graphe.createSommet();
		s3 = graphe.createSommet();
		s4 = graphe.createSommet();
		s5 = graphe.createSommet();
		
		graphe.addPaire(a00 = new Arete(s0, s0));
		graphe.addPaire(a01 = new Arete(s0, s1));
		graphe.addPaire(a12 = new Arete(s1, s2));
		graphe.addPaire(a34 = new Arete(s3, s4));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testArete() {
		try {
			new Arete(null, null);
			fail("pb constructeur");
		} catch (Exception e) {	}
		
		try {
			new Arete(s1, new Graphe<IArete>().createSommet());
			fail("pb constructeur");
		} catch (Exception e) {	}
	}

	@Test
	public void testGetElements() {
		assertTrue(a00.getElements().contains(s0));
		assertTrue(a01.getElements().contains(s0));
		assertTrue(a01.getElements().contains(s1));
	}

	@Test
	public void testContains() {
		assertTrue(a00.contains(s0));
		assertTrue(a01.contains(s0));
		assertTrue(a01.contains(s1));
	}

	@Test
	public void testGetSortant() {
		assertEquals(a00.getSortant(), s0);
		assertEquals(a01.getSortant(), s0);
		assertEquals(a12.getSortant(), s1);
		assertEquals(a34.getSortant(), s3);
	}

	@Test
	public void testGetEntrant() {
		assertEquals(a00.getEntrant(), s0);
		assertEquals(a01.getEntrant(), s1);
		assertEquals(a12.getEntrant(), s2);
		assertEquals(a34.getEntrant(), s4);
	}

	@Test
	public void testEqualsObject() {
		assertFalse(a00.equals(a01));
		assertFalse(a01.equals(new Arete(s1, s0)));
		
		IGraphe<IArc> g2 = new Graphe<IArc>(IArc.class);
		ISommet<IArc> ss0 = g2.createSommet();
		ISommet<IArc> ss1 = g2.createSommet();
		
		assertEquals(new Arc(ss0, ss1), new Arc(ss1, ss0));
	}

}
