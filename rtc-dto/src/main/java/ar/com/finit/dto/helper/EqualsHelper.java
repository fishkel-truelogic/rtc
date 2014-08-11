package ar.com.finit.dto.helper;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author leo
 */
public abstract class EqualsHelper {
	public static boolean equals(Object o1, Object o2) {
		if (o1 == null && o2 == null) {
			return true;
		}
		if (o1 == null && o2 != null) {
			return false;
		}
		return o1.equals(o2);
	}

	@SuppressWarnings("rawtypes")
	public static boolean equalsCollection(Collection c1, Collection c2) {
		if (c1 == null && c2 == null) {
			return true;
		}
		if (c1 == null && c2 != null) {
			return false;
		}
		if (c1.size() != c2.size()) {
			return false;
		}
		Iterator it1 = c1.iterator();
		Iterator it2 = c2.iterator();
		while (it1.hasNext() && it2.hasNext()) {
			if (!EqualsHelper.equals(it1.next(), it2.next())) {
				return false;
			}
		}
		return true;
	}
}