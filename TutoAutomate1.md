# Présentation des automates #
Les représentations d'automates que l'on choisit vont être une extension des graphes valués. L'ensemble des valeurs associées au graphe vont représenter l'alphabet de l'automate.

Un automate fini va reconnaître un langage régulier (cf _théorème de Kleene_). Nous dirons qu'un automate	est un quintuplet :
  * A _l'alphabet_
  * Q l'_ensemble d'états_ (les sommets du graphes)
  * i un _états initial_ (un sommet particulier, utilisé comme "point de  départ" des "mots" reconnus par le langage).
  * F inclut dans Q un _ensemble d'états terminaux_ (des sommets particuliers, utilisés comme "marqueurs" de mots reconnus par le langage).
  * _d_ une _fonction de transition_ d'un état par un symbole (défini suivant le type de l'automate :	DFA, NFA, ...)

On étendra la fonction de transition <u><i>d</i></u> aux "mots" de A (A = {_e_} union A union A² union ... et _e_ le _mot vide_).

Soient _w_ un mot de _A`*`_ et _q_ un état de _Q_ un état :
  * <u>d</u>(q, w) = q_(ou_{q}_selon la définition de_d_)_<b>si</b> _w = e_,
  * <u>d</u>(q, w) = <u>d</u>(d (q, a), u)<b>sinon</b> (avec _w = a.u_, _a_ un symbole de _A_, _u_ un mot de **A).**

Certaines définitions d'automate fini autorisent plusieurs états initiaux, nous donnerons une définition n'en possédant qu'un, l'opération de _standardisation_ permettant toujours de se ramener à un automate ayant un unique état initial.

Cette interface va définir une interface commune pour les DFA et les NFA. Les NFA sont les automates finis non-déterministes. Les DFA sont les automates finis déterministes. La différence entre NFA et DFA va être sur la fonction de transition.

  * DFA : _d : Q x A -> Q_
  * NFA : _d : Q x A -> P(Q)_ (_P(Q)_ l'ensemble des parties de _Q_)

# Exemple #

## DFA ##
```
IAutomate<Character, IEtatDFA<Character>> M;
// initialisation de l'automate M avec comme paramètres :
// -- le générateur DFA
// -- l'alphabet {a, b}
M = new Automate<Character, IEtatDFA<Character>>(
		GenEtat.DFA,
		SetTools.setFactory('a', 'b'));

// création des deux états
IEtatDFA<Character>	e0 = M.createEtat(),
			e1 = M.createEtat(),
			e2 = M.createEtat();	// état inutil...

// paramétrage de l'état initial
M.setInitial(e0);
// paramétrage des états finaux
M.setFinal(e1);
M.setFinal(e2);

// ajout des transitions (-0-, a, -1-) et (-0-, b, -1-)
M.addTransition(new Transition<Character>(e0, e1, 'a'));
M.addTransition(new Transition<Character>(e0, e1, 'b'));

// affichage de l'automate
System.out.println(M);

IEtatDFA<Character> init, q;

// récupération de l'état initial
init = M.getInitial();

// récupération l'état résultat de la transition &delta; (i, a)
q = init.delta('a');

// affichage de l'état + test s'il est final
System.out.println(q + " : " + q.isFinal());

// tentative d'ajout une nouvelle transition non viable dans un DFA
// -- (-0-, a, -2-)
try { M.addTransition(new Transition<Character>(e0, e2, 'a'));
} catch (Exception e) {	System.out.println(e.getMessage());	}
```

## NFA ##
```
IAutomate<Character, IEtatNFA<Character>> M;
// initialisation du NFA M
M = new Automate<Character, IEtatNFA<Character>>(
	GenEtat.NFA);

// construction de l'alphabet 
M.addSymbole('a');
M.addSymbole('b');

// création des états
IEtatNFA<Character> e0 = M.createEtat(),
		    e1 = M.createEtat(),
		    e2 = M.createEtat();

// paramétrage de l'état initial
e0.setInitial();

// paramétrage des états finaux
e1.setFinal();
e2.setFinal();

// ajout des transitions
M.addTransition(new Transition<Character>(e0, e1, 'a'));
M.addTransition(new Transition<Character>(e0, e1, 'b'));
M.addTransition(new Transition<Character>(e0, e2, 'a'));
M.addTransition(new Transition<Character>(e1, e2, 'a'));

// affichage du graphe
System.out.println(M);

// affichage de l'ensemble des états de la transition &delta(i,a)
System.out.println(e0.delta('a'));
```

### Bibliographie ###

Définitions issues de <u><i>Introduction to Automata Theory, Languages, and Computation</i></u> de John E.<span>Hopcroft</span>, Rajeev. <span>Motwani</span> et Jeffrey D. <span>Ullman</span>