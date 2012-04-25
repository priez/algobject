package univ.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import univ.components.edges.ICurveEdge.EdgeStyle;
import univ.components.edges.DirectEdge;
import univ.components.nodes.IShapeNode;
import univ.components.nodes.IShapeNode.ColorSetting;
import univ.components.nodes.CircleNode;
import univ.components.nodes.RectangleNode;
import univ.structures.graphes.Graphe;
import univ.structures.graphes.IArc;

public class Appli extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 580891487640006604L;

	Appli() {
		createComponents();
		placeComponents();
		createControllers();
	}
	
	public void view() {
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	JMenu fichier,noeuds,paires,couleurs;
	JTextArea output;
	JRadioButtonMenuItem circle,
		rectangle, simple,
		doublz, 
		creerNoeuds, creerPaires, 
		supprimerNoeuds, supprimerPaires,
		neutreNoeuds, neutrePaires;
	JRadioButtonMenuItem[] colors,edgestyles;
	Map<JRadioButtonMenuItem, ColorSetting> mapcol;
	GrapheGridPanel<IArc> ggp;
	
	ColorSetting selected;

	private void createControllers() {
		selected = IShapeNode.FILL_DEF_COL;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		fichier.setMnemonic(KeyEvent.VK_F);
		noeuds.setMnemonic(KeyEvent.VK_N);
		paires.setMnemonic(KeyEvent.VK_P);
		couleurs.setMnemonic(KeyEvent.VK_C);
		
		// quitter
		quit.setMnemonic(KeyEvent.VK_R);
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		// ajouter un noeud
		creerNoeuds.setMnemonic(KeyEvent.VK_A);
		creerNoeuds.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				creerNoeuds.setSelected(!creerNoeuds.isSelected());
				neutrePaires.setSelected(true);
			}
		});
		ggp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (creerNoeuds.isSelected()) {
					String nom = JOptionPane.showInputDialog(null, "", "Nommer du noeud", JOptionPane.QUESTION_MESSAGE);
					if (nom != null) {
						IShapeNode n = null;
						// FIXME modifier pour généraliser à plusieurs formes
						if (circle.isSelected()) {
							n = new CircleNode(nom, true, selected);
						}
						if (rectangle.isSelected()) {
							n = new RectangleNode(nom, true, selected);
						}
						if (simple.isSelected()) {
							n = new RectangleNode(nom, false, selected);
						}
						
						if (n != null) {
							n.setPosition(e.getPoint());
							ggp.addNode(n);
			}	}	}	}
		});
		
		// supprimer un noeud
		supprimerNoeuds.setMnemonic(KeyEvent.VK_S);
		supprimerNoeuds.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				supprimerNoeuds.setSelected(!supprimerNoeuds.isSelected());
				neutrePaires.setSelected(true);
			}
		});
		ggp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (supprimerNoeuds.isSelected()) {
					// TODO
				}
			}
		});
		
		// ne rien faire des noeuds
		neutreNoeuds.setMnemonic(KeyEvent.VK_E);
		neutreNoeuds.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				neutreNoeuds.setSelected(!neutreNoeuds.isSelected());
			}
		});
		ggp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (neutreNoeuds.isSelected()) {
					// TODO
				}
			}
		});
		
		// créer une paire
		creerPaires.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				creerPaires.setSelected(true);
				neutreNoeuds.setSelected(true);
			}
		});
		ggp.addMouseListener(new MouseAdapter() {
			IShapeNode select = null;
			@Override
			public void mouseClicked(MouseEvent e) {
				if (creerPaires.isSelected()) {
					if (ggp.selectedNodes.size() == 1) {
						if (select != null) {
							String nom = JOptionPane.showInputDialog(null, "", "Nommer l'arc", JOptionPane.QUESTION_MESSAGE);
							if (nom != null) {
								Color col = selected.col();;
								if (selected.equals(ColorSetting.WHITE)) {
									col = ColorSetting.BLACK.col();
								}
								ggp.addEdge(new DirectEdge(
									select, 
									(IShapeNode) ggp.selectedNodes.toArray()[0],
									nom, col));
								
								select = null;
							}	
						} else
							select = (IShapeNode) ggp.selectedNodes.toArray()[0];
			}	}	}
		});
		
		// supprimer des paires
		supprimerPaires.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				supprimerPaires.setSelected(true);
				neutreNoeuds.setSelected(true);
			}
		});
		
		neutrePaires.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				neutrePaires.setSelected(true);
			}
		});
		
		// couleurs
		for (JRadioButtonMenuItem c : colors) {
			c.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent a) {
					selected = mapcol.get(a.getSource());
				}
			});
		}
		
		// imprime dans l'output une sortie latex
		latex.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println(ggp.latex());
				output.setText(ggp.latex());
			}
		});
		// vide le panel
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ggp.setModel(new Graphe<IArc>(IArc.class));
				output.setText("");
			}
		});
		
		// créer une image à partir d'une compilation latex
		image.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showSaveDialog(Appli.this);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                StringBuffer sb = new StringBuffer("\\documentclass{beamer}\n");
					sb.append("\\usepackage{tikz}\n");
					sb.append("\\setbeamertemplate{navigation symbols}{}\n");
					sb.append("\\begin{document}\n");
					sb.append("\\begin{center}\n");
					sb.append("\\ \\\\ \n");
					sb.append(ggp.latex());
					sb.append("\\end{center}\n");
					sb.append("\\end{document}\n");
	                try {
						BufferedWriter bw = new BufferedWriter(new FileWriter(file));
						bw.write(sb.toString());
						bw.flush();
						bw.close();
						Runtime r = Runtime.getRuntime();
						Process p;
						try {
							p = r.exec("mkdir tmp");
							p.waitFor();
							p = r.exec("/usr/texbin/pdflatex -output-directory ./tmp " + file.getAbsolutePath());
							p.waitFor();
	                        p = r.exec("mv ./tmp/" + file.getName() + ".pdf " + "file.pdf");
	                        p.waitFor();
	                        p = r.exec("sh sc.sh");
	                        //p.waitFor();
	                        //p = r.exec("ls");
	                        int c = p.getInputStream().read();
	                        while (c != -1) {
	                        	System.out.print((char) c);
	                        	c = p.getInputStream().read();
	                        }
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                        //System.out.println("mv ./tmp/" + file.getName() + ".pdf file.pdf");
                        
                        //r.exec("rm -r tmp");
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
			}
		});
	}

	private void placeComponents() {
		JMenuBar jmb = new JMenuBar();
		jmb.add(fichier); {
			fichier.add(latex);
			fichier.add(image);
			fichier.addSeparator();
			fichier.add(clear);
			fichier.addSeparator();
			fichier.add(quit);
		}
		jmb.add(noeuds); {
			noeuds.add(creerNoeuds);
			noeuds.add(supprimerNoeuds);
			noeuds.add(neutreNoeuds);
			
			noeuds.addSeparator();
			// FIXME généraliser à plusieurs formes
			noeuds.add(circle);
			noeuds.add(rectangle);
			noeuds.add(simple);
			
			noeuds.addSeparator();
			noeuds.add(doublz);
		}
		jmb.add(paires); {
			paires.add(creerPaires);
			paires.add(supprimerPaires);
			paires.add(neutrePaires);
			
			paires.addSeparator();
			for (JRadioButtonMenuItem jrbmi : edgestyles)
				paires.add(jrbmi);
		}
		jmb.add(couleurs); {
			for (JRadioButtonMenuItem jrbmi : colors) 
				couleurs.add(jrbmi);
		}
		this.setJMenuBar(jmb);
		
		//GridBagLayout gbl = new GridBagLayout();
		//this.setLayout(gbl);
		this.add(ggp, BorderLayout.CENTER);
		this.add(new JScrollPane(output), BorderLayout.EAST);
	}
	
	JMenuItem quit,latex,clear,image;
	private void createComponents() {
		ButtonGroup group;
		int i;
		// menu 
		fichier = new JMenu("Fichier");
		noeuds = new JMenu("Noeuds");
		paires = new JMenu("Paires");
		couleurs = new JMenu("Couleurs");
		
		// fichier
		quit = new JMenuItem("Quitter");
		clear = new JMenuItem("Clear");
		latex = new JMenuItem("LateX");
		image = new JMenuItem("Image");
		
		// noeud
		creerNoeuds = new JRadioButtonMenuItem("Ajouter");
			creerNoeuds.setSelected(true);
		supprimerNoeuds = new JRadioButtonMenuItem("Supprimer");
		neutreNoeuds = new JRadioButtonMenuItem("Neutre");
		group = new ButtonGroup(); {
			group.add(creerNoeuds);
			group.add(supprimerNoeuds);
			group.add(neutreNoeuds);
		}
		
		circle = new JRadioButtonMenuItem("Cercle");
			circle.setSelected(true);
		rectangle = new JRadioButtonMenuItem("Rectangle");
		simple = new JRadioButtonMenuItem("Simple");
		group = new ButtonGroup(); {
			group.add(circle);
			group.add(rectangle);
			group.add(simple);
		}
		doublz = new JRadioButtonMenuItem("doublé");
		
		// paires
		creerPaires = new JRadioButtonMenuItem("Ajouter");
		supprimerPaires = new JRadioButtonMenuItem("Supprimer");
		neutrePaires = new JRadioButtonMenuItem("Neutre");
		neutrePaires.setSelected(true);
		group = new ButtonGroup(); {
			group.add(creerPaires);
			group.add(supprimerPaires);
			group.add(neutrePaires);
		}
		edgestyles = new JRadioButtonMenuItem[EdgeStyle.values().length];
		i = 0;
		group = new ButtonGroup();
		for (EdgeStyle es : EdgeStyle.values()) {
			edgestyles[i] = new JRadioButtonMenuItem(es.toString());
			group.add(edgestyles[i]);
			if (i == 0) 
				edgestyles[i].setSelected(true);
			i++;
		}
		// couleurs
		colors = new JRadioButtonMenuItem[ColorSetting.values().length];
		mapcol = new HashMap<JRadioButtonMenuItem, ColorSetting>();
		i = 0;
		group = new ButtonGroup();
		for (ColorSetting cs : ColorSetting.values()) {
			colors[i] = new JRadioButtonMenuItem(cs.toString());
			group.add(colors[i]);
			mapcol.put(colors[i], cs);
			i++;
		}
		
		ggp = new GrapheGridPanel<IArc>(Color.white, new Graphe<IArc>(IArc.class));
		output = new JTextArea(20, 30);
	}

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Appli().view();
			}
		});
	}
}
