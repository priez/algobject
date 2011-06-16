package univ.tests.structures.graphes;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import univ.structures.graphes.Arete;
import univ.structures.graphes.Graphe;
import univ.structures.graphes.IArete;
import univ.structures.graphes.IGraphe;
import univ.structures.graphes.ISommet;

/**
 * <h2>Test de la classe Sommet</h2>
 * 
 *  Graphe 1 : <S,A> avec 
 *  <ul>
 *  	<li> S = [0, 1, 2, 3, 4, 5]
 *  	<li> A = [(0,0, (0,1), (1, 2), (3, 4)]
 *  </ul> 
 * @author EliX
 *
 */
public class TestSommet {
	
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

	@Test
	public void testDegre() {
		// (0,0, (0,1), (1, 2), (3, 4)
		assertTrue(s0.degre() == 2);
		assertTrue(s1.degre() == 2);
		assertTrue(s2.degre() == 1);
		assertTrue(s3.degre() == 1);
		assertTrue(s4.degre() == 1);
		assertTrue(s5.degre() == 0);
	}

	@Test
	public void testDegreEntrant() {
		// (0,0, (0,1), (1, 2), (3, 4)
		assertTrue(s0.degreEntrant() == 1);
		assertTrue(s1.degreEntrant() == 1);
		assertTrue(s2.degreEntrant() == 1);
		assertTrue(s3.degreEntrant() == 0);
		assertTrue(s4.degreEntrant() == 1);
		assertTrue(s5.degreEntrant() == 0);
	}

	@Test
	public void testDegreSortant() {
		// (0,0, (0,1), (1, 2), (3, 4)
		assertTrue(s0.degreSortant() == s0.getSortants().size());
		assertTrue(s1.degreSortant() == s1.getSortants().size());
		assertTrue(s2.degreSortant() == s2.getSortants().size());
		assertTrue(s3.degreSortant() == s3.getSortants().size());
		assertTrue(s4.degreSortant() == s4.getSortants().size());
		assertTrue(s5.degreSortant() == s5.getSortants().size());
	}

	@Test
	public void testIsIsole() {
		// (0,0, (0,1), (1, 2), (3, 4)
		assertFalse(s0.isIsole());
		assertFalse(s1.isIsole());
		assertFalse(s2.isIsole());
		assertFalse(s3.isIsole());
		assertFalse(s4.isIsole());
		assertTrue(s5.isIsole());
	}

	@Test
	public void testIsAdjacent() {
		// (0,0, (0,1), (1, 2), (3, 4)
		assertTrue(s0.isAdjacent(s0));
		assertTrue(s0.isAdjacent(s1));
		assertFalse(s0.isAdjacent(s2));
		assertFalse(s0.isAdjacent(s3));
		assertFalse(s0.isAdjacent(s4));
		assertFalse(s0.isAdjacent(s5));
		
		assertFalse(s1.isAdjacent(s0));
		assertFalse(s2.isAdjacent(s1));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetAdjacents() {
		// (0,0, (0,1), (1, 2), (3, 4)
		Set<ISommet<IArete>> set = new HashSet<ISommet<IArete>>(); 
		Collections.addAll(set, s0, s1);
		//System.out.println(s0.getAdjacents());
		assertTrue(s0.getAdjacents().size() == 2);
		assertTrue(s0.getAdjacents().containsAll(set));
		
		Collections.addAll(set, s2, s3);
		assertFalse(s0.getAdjacents().containsAll(set));
	}

	@Test
	public void testGetEntrants() {
		// (0,0, (0,1), (1, 2), (3, 4)
		System.out.println(s0.getEntrants());
		assertTrue(s0.getEntrants().size() == 1);
		assertTrue(s1.getEntrants().size() == 1);
		assertTrue(s2.getEntrants().size() == 1);
		assertTrue(s3.getEntrants().size() == 0);
		assertTrue(s4.getEntrants().size() == 1);
		assertTrue(s5.getEntrants().size() == 0);
		
		assertTrue(s0.getEntrants().contains(a00));
		assertTrue(s1.getEntrants().contains(a01));
		assertTrue(s2.getEntrants().contains(a12));
		assertTrue(s4.getEntrants().contains(a34));
	}

	@Test
	public void testGetSortants() {
		// (0,0, (0,1), (1, 2), (3, 4)
		assertTrue(s0.getSortants().size() == 2);
		assertTrue(s1.getSortants().size() == 1);
		assertTrue(s2.getSortants().size() == 0);
		assertTrue(s3.getSortants().size() == 1);
		assertTrue(s4.getSortants().size() == 0);
		assertTrue(s5.getSortants().size() == 0);
		
		assertTrue(s0.getSortants().contains(a00));
		assertTrue(s0.getSortants().contains(a01));
		assertTrue(s1.getSortants().contains(a12));
		assertTrue(s3.getSortants().contains(a34));
	}

	@Test
	public void testGetPairesIncidentes() {
		// (0,0, (0,1), (1, 2), (3, 4)
		Set<IArete> set = new HashSet<IArete>(); 
		Collections.addAll(set, a00, a01);
		assertTrue(s0.getPairesIncidentes().size() == 2);
		assertTrue(s0.getPairesIncidentes().containsAll(set));
		
		set = new HashSet<IArete>(); 
		Collections.addAll(set, a12, a01);
		assertTrue(s1.getPairesIncidentes().size() == 2);
		assertTrue(s1.getPairesIncidentes().containsAll(set));
		
		set = new HashSet<IArete>(); 
		Collections.addAll(set, a12);
		assertTrue(s2.getPairesIncidentes().size() == 1);
		assertTrue(s2.getPairesIncidentes().containsAll(set));
		
		set = new HashSet<IArete>(); 
		Collections.addAll(set, a34);
		assertTrue(s3.getPairesIncidentes().size() == 1);
		assertTrue(s3.getPairesIncidentes().containsAll(set));
		assertTrue(s4.getPairesIncidentes().size() == 1);
		assertTrue(s4.getPairesIncidentes().containsAll(set));
		
		assertTrue(s5.getPairesIncidentes().size() == 0);
	}

}
