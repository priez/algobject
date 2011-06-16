package univ.listener;

import java.beans.PropertyChangeListener;

/**
 * <h2>Interface des méthodes pour les PropertyChangeListener</h2>
 * 
 * TODO faire la docs...
 * 
 * @author EliX
 * @implantation 
 * <code>private PropertyChangeSupport pChangeSupport;</code>
 * 
 * <code><pre>protected void firePropertyChange(
			String prop_name, Object oldValue, Object newValue
	) {
		if (pChangeSupport != null) {
			pChangeSupport.firePropertyChange(s.name(), oldValue, newValue);
		}
	}</pre></code>
 */
public interface PCL {
	
	/**
     * @pre lst != null
     * @post lst a été ajouté à la liste des écouteurs
     * 
     * @implantation
     *<code><pre> 
public void addPropertyChangeListener(PropertyChangeListener lst) {
  if (lst == null) {
    throw new IllegalArgumentException();
  }
  if (pChangeSupport == null) {
    pChangeSupport = new PropertyChangeSupport(this);
  }
  pChangeSupport.addPropertyChangeListener(lst);
}</pre></code>
     */
    void addPropertyChangeListener(PropertyChangeListener lst);

    /**
     * @pre lst != null
     * @post lst a été ajouté à la liste des écouteurs
     *     de la propriété propName
     *     
     * @implantation
     *<code><pre> 
public void addPropertyChangeListener(String propName, PropertyChangeListener lst) {
  if (lst == null || propName == null || propName.equals("")) {
    throw new IllegalArgumentException();
  }
  if (pChangeSupport == null) {
    pChangeSupport = new PropertyChangeSupport(this);
  }
  pChangeSupport.addPropertyChangeListener(propName, lst);
}</pre></code>
     */
    void addPropertyChangeListener(String propName, PropertyChangeListener lst);
    
    /**
     * @pre lst != null
     * @post lst a été retiré de la liste des écouteurs
     * 
     * @implantation
     * <code><pre>
public void removePropertyChangeListener(PropertyChangeListener lst) {
  if (lst == null) {
    throw new IllegalArgumentException();
  }
  if (pChangeSupport != null) {
    pChangeSupport.removePropertyChangeListener(lst);
  }
}
		</pre></code>
     */
    void removePropertyChangeListener(PropertyChangeListener lst);

}
