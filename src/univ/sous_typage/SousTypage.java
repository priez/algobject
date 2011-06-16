package univ.sous_typage;

public class SousTypage {
	
	/**
	 * Test si s1 est sous-type de s2
	 * @param s1 type sur le quel on teste le sous-typage
	 * @param s2 interface de référence
	 * @return <code>true</code> <b> s1 <: s2; <code>false</code> <b>sinon</b>.
	 */
	@SuppressWarnings("rawtypes")
	public static boolean estSousType(Class s1, Class s2) {
		if (s1 == null || s2 == null)
			throw new IllegalArgumentException("les arg ne doivent pas être nuls");
		if (!s2.isInterface())
			throw new IllegalArgumentException("l'arg s2 doit être une interface");
		
		for (Class c : s1.getInterfaces()) {
			if (c == s2 || estSousType(c, s2)) return true;
		}
		return false;
	}

}
