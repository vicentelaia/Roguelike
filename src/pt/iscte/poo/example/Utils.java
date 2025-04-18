package pt.iscte.poo.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Utils {

	static <T> Iterable<T> select(Iterable<T> iterable, Predicate<T> pred) {
		List<T> selection = new ArrayList<T>();

		for (T elem : iterable)
			if (pred.test(elem))
				selection.add(elem);

		return selection;
	}

	static <T> boolean check(Iterable<T> iterable, Predicate<T> pred) {
		for (T elem : iterable)
			if (pred.test(elem))
				return true;

		return false;
	}

	static <T> T find(Iterable<T> iterable, Predicate<T> pred) {
		for (T elem : iterable)
			if (pred.test(elem))
				return elem;

		return null;
	}
	
	
}
