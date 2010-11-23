package shz.spikes;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class ReadTextFile {

	public static void main(String[] args) throws FileNotFoundException {
		InputStream stream = ClassLoader
				.getSystemResourceAsStream("shz/spikes/Transactions.txt");

		Scanner scanner = new Scanner(stream);
		while (scanner.hasNext()) {
			System.out.println("Line : " + scanner.nextLine());
		}
	}
}
