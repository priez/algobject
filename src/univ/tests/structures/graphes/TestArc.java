package univ.tests.structures.graphes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import univ.structures.graphes.Arc;
import univ.structures.graphes.Graphe;
import univ.structures.graphes.IArc;
import univ.structures.graphes.IGraphe;
import univ.structures.graphes.ISommet;

public class TestArc {
	
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

	@SuppressWarnings("deprecation")
	@Test
	public void testArc() {
		try {
			new Arc(null, null);
			fail("pb constructeur");
		} catch (Exception e) {	}
		
		try {
			new Arc(s1, new Graphe<IArc>().createSommet());
			fail("pb constructeur");
		} catch (Exception e) {	}
	}

	@Test
	public void testGetSommetA() {
		assertEquals(a00.getSommetA(), s0);
		assertTrue(a01.getSommetA().equals(s0) || a01.getSommetA().equals(s1));
		assertTrue(a12.getSommetA().equals(s1) || a01.getSommetA().equals(s2));
		assertTrue(a34.getSommetA().equals(s3) || a01.getSommetA().equals(s4));
	}

	@Test
	public void testGetSommetB() {
		assertEquals(a00.getSommetB(), s0);
		assertTrue(a01.getSommetB().equals(s0) || a01.getSommetB().equals(s1));
		assertTrue(a12.getSommetB().equals(s1) || a12.getSommetB().equals(s2));
		assertTrue(a34.getSommetB().equals(s3) || a34.getSommetB().equals(s4));
	}

	@Test
	public void testEqualsObject() {
		assertEquals(a00, new Arc(s0, s0));
		assertEquals(a01, new Arc(s1, s0));
		assertEquals(a01, new Arc(s0, s1));
		assertEquals(a12, new Arc(s2, s1));
		assertEquals(a12, new Arc(s1, s2));
		assertEquals(a34, new Arc(s4, s3));
		assertEquals(a34, new Arc(s3, s4));
	}

}
