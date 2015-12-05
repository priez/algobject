# Les différents graphes #

Un _graphe_ est un couple `(S,A)` où `S` est un ensemble de sommet et `A` un ensemble d'arcs ou d'arêtes liant des sommets de `S`.

On parle de _graphe orienté_ lorsque `A` est un ensemble _arcs_. Un _arc_ est une paire de sommets `{a,b}` formant un ensemble non orienté.

On parle de _graphe non-orienté_ lorsque `A` est un ensemble _arêtes_. Une _arête_ est une paire de sommet `(a,b)` forme un couple (ensemble avec notion d'ordre).

Un _graphe valué_ est un graphe dont les arcs/arêtes sont valué(e)s `(a, v, b)`.

# Exemple d'utilisation #

## Graphe orienté, valué ##
```
// Déclaration et initialisation du graphe orienté et valué par des entiers.
IGraphe<IAreteValue<Integer>> G = new Graphe<IAreteValue<Integer>>(IAreteValue.class);
	
// création des sommets
ISommet<IAreteValue<Integer>>
	s0 = G.createSommet(),
	s1 = G.createSommet(),
	s2 = G.createSommet(),
	s3 = G.createSommet();
		
// création des arêtes valués
G.addPaire(new AreteValue<Integer>(s0, s0, 1));
G.addPaire(new AreteValue<Integer>(s0, s1, -1));
G.addPaire(new AreteValue<Integer>(s2, s3, -4));
G.addPaire(new AreteValue<Integer>(s3, s2, 4));
G.addPaire(new AreteValue<Integer>(s3, s1, 0));
		
System.out.println("Affichage du graphe :");
System.out.println(G);
		
System.out.println();
System.out.println("Affichage des sommets adjacents à " + s0 + " :");
System.out.println(s0.getAdjacents());
	
System.out.println();
System.out.println("Affichage des arêtes adjacents à " + s3 + " :");
System.out.println(s3.getSortants());
		
System.out.println();
System.out.println("Affichage des arêtes incidents à " + s3 + " :");
System.out.println(s3.getPairesIncidentes());
		
System.out.println();
System.out.println("Récupération de la valeur de la première arête adjacente à " + s3 + " :");
System.out.println(s3.degreSortant() > 0 ? s3.getSortants().iterator().next().getValeur() : "null");
```

## Graphe non-orienté ##

```
// Déclaration et initialisation du graphe orienté et valué par des entiers.
IGraphe<IArc> G = new Graphe<IArc>(IAreteValue.class);

// création des sommets
ISommet<IArc>
	s0 = G.createSommet(),
	s1 = G.createSommet(),
	s2 = G.createSommet(),
	s3 = G.createSommet();

// création des arêtes valués
G.addPaire(new Arc(s0, s0));
G.addPaire(new Arc(s0, s1));
G.addPaire(new Arc(s2, s3));
G.addPaire(new Arc(s3, s2));
G.addPaire(new Arc(s3, s1));

System.out.println("Affichage du graphe :");
System.out.println(G);

System.out.println();
System.out.println("Affichage des sommets adjacents à " + s0 + " :");
System.out.println(s0.getAdjacents());

System.out.println();
System.out.println("Affichage des arcs adjacents à " + s3 + " :");
System.out.println(s3.getSortants());

System.out.println();
System.out.println("Affichage des arcs incidents à " + s3 + " :");
System.out.println(s3.getPairesIncidentes());

System.out.println();
System.out.println("Le graphe contient l'arc {-1-, -3-} : " + G.getPaires().contains(new Arc(s1, s3)));
```

### Bibliographie ###

Définitions issues de l'<u><i>Introduction à l'algorithmique</i></u> de Thomas H. <span>Cormen</span>, Charles E. <span>Leiserson</span>, Ronald L. <span>Rivest</span> et Clifford <span>Stein</span>.