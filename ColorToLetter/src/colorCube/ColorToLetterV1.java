package colorCube;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ColorToLetterV1 {
	//Define Palette here
	final static Integer[] black = {0,0,0};
	final static Integer[] white = {255,255,255};
	final static Integer[] red = {255,0,0};
	final static Integer[] green = {0,255,0};
	final static Integer[] blue = {0,0,255};
	final static Integer[] yellow = {255,255,0};
	final static Integer[] magenta = {255,0,255};
	final static Integer[] cyan = {0,255,255};

	public static void main (String[] args) throws IOException {
		// make palette
		LinkedList<Integer[]> palette = makePalette();
		// start reading the file
		File file = new File(args[0]);
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String line, coordinates, colorRaw;
		String[] raw;
		String[] rgbRaw = new String[3];
		int[] pixleColor = new int[3];
		Integer[] referenceColor = new Integer[3];
		int count, row, difference, redD, blueD, greenD, lowestD, closestC;
		int previous = 0;
		String result = new String();
		while ((line = buffer.readLine()) != null) {
			line = line.trim();
			// Process line of input Here
			raw = line.split("\\(");
			//System.out.println(raw.length);
			//System.out.println(Arrays.toString(raw));
			if(raw.length > 1){
				coordinates = raw[0].trim().substring(0,raw[0].length()-2);
				colorRaw = raw[1].split("\\)")[0];
				//System.out.println(coordinates);
				//System.out.println(colorRaw);
				row = Integer.parseInt(coordinates.split(",")[1]);
				//System.out.println(row);
				rgbRaw = colorRaw.split(",");
				for(count = 0; count < rgbRaw.length; count ++ ){
					pixleColor[count] = Integer.parseInt(rgbRaw[count].trim());
				}
				//System.out.println(Arrays.toString(pixleColor));
				//Output if went on to next row
				if(previous != row){
					System.out.println(result);
					result = new String();
				}
				//Compare color
				difference = Integer.MAX_VALUE;
				lowestD = Integer.MAX_VALUE;
				closestC = -1;
				for(count = 0; count < palette.size(); count++){
					referenceColor = palette.get(count);
					redD = referenceColor[0] - pixleColor[0];
					greenD = referenceColor[1] - pixleColor[1];
					blueD = referenceColor[2] - pixleColor[2];
					difference = redD*redD+ blueD*blueD+ greenD*greenD;
					//System.out.println("Color Difference to: " + Arrays.toString(referenceColor)+" "+difference);
					if(difference < lowestD){
						lowestD = difference;
						closestC = count;
					}
				}
				//add result
				result += closestC;
				previous = row;
			}
		}
	}

	private static LinkedList<Integer[]> makePalette() {
		LinkedList<Integer[]> palette = new LinkedList<Integer[]>();
		palette.add(black);
		palette.add(white);
		palette.add(red);
		palette.add(green);
		palette.add(blue);
		palette.add(yellow);
		palette.add(magenta);
		palette.add(cyan);

		return palette;
	}
}
