import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TravelRestrictions {

	public static void main(String[] args) {
		File file = new File("travel_restrictions_input.txt");
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int numberOfAirlines = Integer.parseInt(sc.nextLine());
		try {
			FileWriter fw = new FileWriter("output.txt");
			for (int i = 1; i <= numberOfAirlines; i++) {
				int numberOfCountries = Integer.parseInt(sc.nextLine());
				String[] restrictions = new String[2];
				restrictions[0] = sc.nextLine();
				restrictions[1] = sc.nextLine();
				String[] outputArray = new String[numberOfCountries];

				fw.write("Case #" + i + ":\n");
				String originalString = new String("");
				for (int indexOfN = 0; indexOfN < numberOfCountries; indexOfN++)
					originalString += "N";
				for (int inputCountry = 0; inputCountry < numberOfCountries; inputCountry++) {
					StringBuilder sb = new StringBuilder(originalString);
					if (inputCountry > 0 && restrictions[1].charAt(inputCountry) == 'Y'
							&& restrictions[0].charAt(inputCountry - 1) == 'Y') {
						sb.replace(0, inputCountry, outputArray[inputCountry - 1].substring(0, inputCountry));
					}
					sb.replace(inputCountry, inputCountry + 1, "Y");
					outputArray[inputCountry] = sb.toString();
				}
				for (int inputCountry = numberOfCountries - 1; inputCountry >= 0; inputCountry--) {
					StringBuilder sb = new StringBuilder(outputArray[inputCountry]);
					if (inputCountry < numberOfCountries - 1 && restrictions[1].charAt(inputCountry) == 'Y'
							&& restrictions[0].charAt(inputCountry + 1) == 'Y') {
						sb.replace(inputCountry + 1, numberOfCountries,
								outputArray[inputCountry + 1].substring(inputCountry + 1, numberOfCountries));
					}
					outputArray[inputCountry] = sb.toString();
				}
				for (int j = 0; j < numberOfCountries; j++) {
					for (int k = 0; k < numberOfCountries; k++) {
						fw.write(outputArray[j].charAt(k));
					}
					fw.write("\n");
				}
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
