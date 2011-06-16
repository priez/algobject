package univ.structures.graphes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import univ.sous_typage.SousTypage;
import univ.structures.collections.DefaultSortedMap;
import univ.structures.collections.SetTools;

/**
 * <h2>Implantation de graphe</h2>
 * 
 * Cette structure de graphe autorise les 
 * graphe orienté, non-orienté, valué ou non valué (classiques).
 * 
 * @author EliX
 *
 * @param <E> le type des paires.
 * 
 * @see equals !??? qu'est ce que deux graphes égaux?
 */
public class Graphe<E extends IPaire<E>> implements IGraphe<E> {

	private PropertyChangeSupport pChangeSupport;
	
	private SortedSet<ISommet<E>> sommets;
	
	private Set<E> paires;
	
	private boolean value, oriente, init;
	
	// tab associative un sommet --> l'ensemble des entrants, des sortants
	private SortedMap<ISommet<E>, Set<E>> sortants, entrants;
	
	private int indice;
	
	/**
	 * Construit un graphe en accord avec le type cl
	 * @param cl le type qui va déterminer le type du graphe.
	 * 
	 * @exemple 
	 * Graphe(IArete.class) ==> construit une graphe orienté non valué.
	 * 
	 * FIXME le typage n'est pas nécessairement cohérent avec le 
	 * type générique! comment l'améliorer??? 
	 */
	@SuppressWarnings({ "deprecation", "rawtypes" })
	public Graphe(Class cl) {
		this();
		oriente = SousTypage.estSousType(cl, IOrientation.class); 
		value = SousTypage.estSousType(cl, IValuation.class);
		init = true;
	}
	
	@Deprecated
	public Graphe() {
		sommets = new TreeSet<ISommet<E>>();
		paires = new HashSet<E>();
		
		sortants = new DefaultSortedMap<ISommet<E>, Set<E>>(
			new TreeMap<ISommet<E>, Set<E>>(),
			new HashSet<E>());
		entrants = new DefaultSortedMap<ISommet<E>, Set<E>>( 
			new TreeMap<ISommet<E>, Set<E>>(),
			new HashSet<E>());

		indice = 0;
		init = false;
		value = false;
		oriente = false;
	}
	
	protected Set<E> getSortants(ISommet<E> s) {
		return sortants.get(s);
	}
	
	protected Set<E> getEntrants(ISommet<E> s) {
		return entrants.get(s);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener lst) {
		if (lst == null) {
			throw new IllegalArgumentException();
		}
		if (pChangeSupport == null) {
			pChangeSupport = new PropertyChangeSupport(this);
		}
		pChangeSupport.addPropertyChangeListener(lst);

	}

	@Override
	public void addPropertyChangeListener(String propName,
			PropertyChangeListener lst) {
		if (lst == null || propName == null || propName.equals("")) {
			throw new IllegalArgumentException();
		}
		if (pChangeSupport == null) {
			pChangeSupport = new PropertyChangeSupport(this);
		}
		pChangeSupport.addPropertyChangeListener(propName, lst);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener lst) {
		if (lst == null) {
			throw new IllegalArgumentException();
		}
		if (pChangeSupport != null) {
			pChangeSupport.removePropertyChangeListener(lst);
		}
	}

	@Override
	public Set<ISommet<E>> getSommets() {
		return Collections.unmodifiableSet(sommets);
	}

	@Override
	public Set<E> getPaires() {
		return Collections.unmodifiableSet(paires);
	}

	@SuppressWarnings("deprecation")
	@Override
	public ISommet<E> createSommet() {
		ISommet<E> sommet = new Sommet<E>(indice++, this);
		addSommet(sommet);
		return sommet;
	}

	@Override
	public void addPaire(E p) {
		if (p == null) throw new IllegalArgumentException("l'arg ne doit pas être nul");
		for (ISommet<E> s : p.getElements())
			if (!sommets.contains(s))
				throw new IllegalArgumentException("un des sommets n'appartient pas au graphe");
		if (!init)
			init(p);
		addPaireToMap(p);
		paires.add(p);
		firePropertyChange(PAIRE_AJOUTE, null, p);
	}
	
	@SuppressWarnings("unchecked")
	private void addPaireToMap(E p) {
		if (p instanceof IOrientation) {
			ISommet<E> entrant = ((IOrientation<E>) p).getEntrant();
			ISommet<E> sortant = ((IOrientation<E>) p).getSortant();
			if (!entrants.containsKey(entrant))
				entrants.put((ISommet<E>) entrant, new HashSet<E>());
			entrants.get(entrant).add(p);
			if (!sortants.containsKey(sortant))
				sortants.put((ISommet<E>) sortant, new HashSet<E>());
			sortants.get(sortant).add(p);
		} else {
			for (ISommet<E> s : p.getElements()) {
				if (!sortants.containsKey(s)) 
					sortants.put(s, new HashSet<E>());
				sortants.get(s).add(p);
				if (!entrants.containsKey(s)) 
					entrants.put(s, new HashSet<E>());
				entrants.get(s).add(p);
			}
		}
	}

	private void init(E p) {
		if (p instanceof IValuation)
			value = true;
		if (p instanceof IOrientation) 
			oriente = true;
	}

	@Override
	public void removeSommet(ISommet<E> s) {
		if (s == null) throw new IllegalArgumentException("l'arg ne doit pas être nul");
		Set<E> set = SetTools.union(entrants.get(s), sortants.get(s));
		for (E p : set) {
			for (ISommet<E> r : p.getElements())
				removePaire(r, p);
			paires.remove(p);
		}
		sommets.remove(s);
		firePropertyChange(SOMMET_ENLEVE, s, null);
	}
	
	private void removePaire(ISommet<E> s, E p) {
		sortants.get(s).remove(p);
		entrants.get(s).remove(p);
	}

	@Override
	public void removePaire(E p) {
		if (p == null) throw new IllegalArgumentException("l'arg ne doit pas être nul");
		paires.remove(p);
		for (ISommet<E> s : p.getElements()) {
			removePaire(s, p);
		}
		firePropertyChange(PAIRE_ENLEVE, null, p);
	}

	@Override
	public boolean isValue() {
		return value;
	}

	@Override
	public boolean isOriente() {
		return oriente;
	}
	
	protected void firePropertyChange(
			String prop_name, Object oldValue, Object newValue
	) {
		if (pChangeSupport != null) {
			pChangeSupport.firePropertyChange(prop_name, oldValue, newValue);
		}
	}

	@Override
	public int hashCode() {
		return 23 * sommets.size() + 11 * paires.size();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("L'ensemble des sommets : ");
		sb.append(System.getProperty("line.separator"));
		sb.append("\t" + sommets.toString());
		sb.append(System.getProperty("line.separator"));
		sb.append("L'ensemble des " + (oriente ? "arêtes" : "arcs") + " : ");
		sb.append(System.getProperty("line.separator"));
		sb.append("\t" + paires.toString());
		return sb.toString();
	}

	public static void main(String[] args) {
		@SuppressWarnings("deprecation")
		IGraphe<IAreteValue<Integer>> g = new Graphe<IAreteValue<Integer>>();
		ISommet<IAreteValue<Integer>> s0 = g.createSommet();
		ISommet<IAreteValue<Integer>> s1 = g.createSommet();
		ISommet<IAreteValue<Integer>> s2 = g.createSommet();
		IAreteValue<Integer> a = new AreteValue<Integer>(s0, s1, 1); g.addPaire(a);
		IAreteValue<Integer> b = new AreteValue<Integer>(s1, s0, 2); g.addPaire(b);
		//IArc c = new Arc(s0, s2); g.addPaire(c);
		
		/*IGraphe<IArete> g = new Graphe<IArete>(IArete.class);
		System.out.println(g.isOriente() + " " + g.isValue());
		ISommet<IArete> s0 = g.createSommet();
		ISommet<IArete> s1 = g.createSommet();
		ISommet<IArete> s2 = g.createSommet();
		IArete a = new Arete(s0, s1); g.addPaire(a);
		IArete b = new Arete(s1, s0); g.addPaire(b);
		IArete c = new Arete(s0, s2); g.addPaire(c);*/
		System.out.println(g.isOriente() + " " + g.isValue());
		//g.removePaire(b);
		//g.removeSommet(s1);
		System.out.println(g);
		System.out.println(s2.degre());
		System.out.println(s2.degreEntrant());
		System.out.println(s2.degreSortant());
		System.out.println(s2.isAdjacent(s1));
		System.out.println(s2.isAdjacent(s0));
		System.out.println(s2.isIsole());
		System.out.println(s2.getAdjacents());
		System.out.println(s2.getEntrants());
		System.out.println(s2.getSortants());
		
	}

	@Override
	@Deprecated
	public void addSommet(ISommet<E> s) {
		sommets.add(s);
		firePropertyChange(SOMMET_AJOUTE, null, s);
	}

}
