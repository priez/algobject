package univ.listener;

import java.beans.VetoableChangeListener;

/**
 * <h2>Interface des méthodes pour les VetoableChangeListener</h2>
 * 
 * TODO faire la documentation...
 * @author EliX
 * 
 * @implantation <code>private VetoableChangeSupport vChangeSupport;</code>
 *
 */
public interface VCL {
	
	
	/**
     * @pre lst != null
     * @post lst a été retiré de la liste des écouteurs
     * 
     * @implantation
     * <code><pre>
public void removeVetoableChangeListener(VetoableChangeListener lst) {
  if (lst == null) {
    throw new IllegalArgumentException();
  }
  if (vChangeSupport != null) {
    vChangeSupport.removeVetoableChangeListener(lst);
  }
}
     * </pre></code>
     */
    void removeVetoableChangeListener(VetoableChangeListener lst);
    
    /**
     * @pre lst != null
     * @post lst a été ajouté à la liste des écouteurs
     * 
     * @implantation <code><pre>
public void addVetoableChangeListener(VetoableChangeListener lst) {
  if (lst == null) {
    throw new IllegalArgumentException();
  }
  if (vChangeSupport == null) {
    vChangeSupport = new VetoableChangeSupport(this);
  }
  vChangeSupport.addVetoableChangeListener(lst);
}
     * </pre></code>
     */
    void addVetoableChangeListener(VetoableChangeListener lst);

}
