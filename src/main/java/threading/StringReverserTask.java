package threading;

import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * giuseppe.adaldo
 */
public class StringReverserTask extends RecursiveTask<List<String>> {

	private static final long serialVersionUID = -966323836521289141L;

	private final List<String> words;

	private int limit;

	public StringReverserTask(List<String> words, int limit) {
		this.words = words;
		this.limit = limit;
	}

	@Override
	protected List<String> compute() {
		if (words.size() < limit) {
			return reverseListString(words);
		} else {
			final int currentLength = words.size();
			final int mid = currentLength / 2;

			// splitting tasks
			final StringReverserTask subTask = new StringReverserTask(words.subList(0, mid), limit);
			subTask.fork();
			final StringReverserTask subTask2 = new StringReverserTask(words.subList(mid, currentLength), limit);

			// combining results
			return Stream.concat(subTask.join().stream(), subTask2.compute().stream())
					.collect(Collectors.toList());
		}
	}

	/**
	 * Basically this is the compute directly method that reverse string of an array small enough.
	 *
	 * @param strings
	 * @return
	 */
	public List<String> reverseListString(List<String> strings) {
		return strings.stream()
				.map(s -> s = new StringBuilder(s).reverse().toString())
				.collect(Collectors.toList());
	}
}
