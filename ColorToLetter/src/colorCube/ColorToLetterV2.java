package colorCube;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class ColorToLetterV2 {

	public static void main (String[] args) throws IOException {
		//Cube Cut Method
		//Define Cube block

		File file = new File(args[0]);

		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String line,rgbRaw,
		result = new String();
		StringTokenizer token;
		int[] pixleColor = new int[3];
		int count, base,
		row = 0,
		previous = 0,
		block = 16,
		width = 256/block,
		color = 0;

		while ((line = buffer.readLine()) != null) {
			line = line.trim();
			//System.out.println(line);
			token = new StringTokenizer(line);
			if(line.charAt(0) != '#'){
				row = Integer.parseInt(token.nextToken(":").split(",")[1]);
				//System.out.println(row);
				//If the row is different form previous, print out result and start new line
				if(row != previous){
					System.out.println(result);
					result = new String();
				}
				//Get Current color
				token.nextToken("#");
				rgbRaw = token.nextToken(" ").substring(1);
				//System.out.println(rgbRaw);
				//Split Color in to components
				for(count = 0; count < 3; count++){
					pixleColor[count] = Integer.parseInt(rgbRaw.substring(count*2,count*2+2), 16); 
					pixleColor[count] /= width;
				}
				color = 0;
				base = block*block;
				for(int i: pixleColor){
					color += i* base;
					base /= block;
				}
				//System.out.println(Arrays.toString(pixleColor));
				//System.out.println(color);
				result += "["+ Integer.toHexString(color)+ "]";
				//current now becomes previous
				previous = row;

			}

		}
	}
}