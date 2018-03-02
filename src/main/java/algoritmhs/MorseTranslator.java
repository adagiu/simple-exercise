package algoritmhs;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MorseTranslator {

	private static final String ENGLISH_SEPARATOR = " ";
	private static final String MORSE_SEPARATOR = "   ";

	public static String run(boolean morseToEnglish, String textToTranslate) {
		if (textToTranslate == null || textToTranslate.length() > 1000000)
			return "INVALID INPUT";

		try {
			return morseToEnglish
					? translate(textToTranslate, MORSE_SEPARATOR, MorseTranslator::morseToEnglishWord, ENGLISH_SEPARATOR)
					: translate(textToTranslate, ENGLISH_SEPARATOR, MorseTranslator::englishToMorseWord, MORSE_SEPARATOR);
		} catch (IllegalArgumentException e) {
			return e.getMessage();
		}
	}

	private static String translate(String text, String splitter, Function<String, String> fn, String separator) {
		return Stream.of(text.split(splitter))
				.map(fn)
				.collect(Collectors.joining(separator));
	}

	private static String morseToEnglishWord(String word) {
		return Stream.of(word.split(ENGLISH_SEPARATOR))
				.map(c -> getChar(c, morseAlphabet))
				.collect(Collectors.joining());
	}

	private static String englishToMorseWord(String word) {
		return word.chars()
				.mapToObj(c -> String.valueOf((char) c))
				.map(c -> getChar(c, englishAlphabet))
				.collect(Collectors.joining(" "));
	}

	private static String getChar(String c, Map<String, String> alphabet) {
		if (alphabet.containsKey(c))
			return alphabet.get(c);
		else
			throw new IllegalArgumentException("WRONG CHARACTER");
	}

	@SuppressWarnings("serial")
	private static final Map<String, String> englishAlphabet = new HashMap<String, String>() {
		{
			put("a", ".-");
			put("b", "-...");
			put("c", "-.-.");
			put("d", "-..");
			put("e", ".");
			put("f", "..-.");
			put("g", "--.");
			put("h", "....");
			put("i", "..");
			put("j", ".---");
			put("k", "-.-");
			put("l", ".-..");
			put("m", "--");
			put("n", "-.");
			put("o", "---");
			put("p", ".--.");
			put("q", "--.-");
			put("r", ".-.");
			put("s", "...");
			put("t", "-");
			put("u", "..-");
			put("v", "...-");
			put("w", ".--");
			put("x", "-..-");
			put("y", "-.--");
			put("z", "--..");
			put("0", "-----");
			put("1", ".----");
			put("2", "..---");
			put("3", "...--");
			put("4", "....-");
			put("5", ".....");
			put("6", "-....");
			put("7", "--...");
			put("8", "---..");
			put("9", "----.");
			put(".", ".-.-.-");
		}
	};

	private static final Map<String, String> morseAlphabet;

	static {
		morseAlphabet = new HashMap<>();
		englishAlphabet.forEach((a, b) -> morseAlphabet.put(b, a));
	}

	public static void main(String[] args) {
		System.out.println(MorseTranslator.run(true,
				"- .... .   .-- .. --.. .- .-. -..   --.- ..- .. -.-. -.- .-.. -.--   .--- .. -. -..- . -..   - .... .   --. -. --- -- . ...   -... . ..-. --- .-. .   - .... . -.--   ...- .- .--. --- .-. .. --.. . -.. .-.-.-"));
		System.out.println(MorseTranslator.run(true,
				"- .... .   .-- .. --.. .- .-. -..   oooo .-. .   - .... . -.--   "));
		System.out.println(MorseTranslator.run(false,
				"the wizard quickly jinxed the gnomes before they vaporized."));
		System.out.println(MorseTranslator.run(false, null));
	}
}