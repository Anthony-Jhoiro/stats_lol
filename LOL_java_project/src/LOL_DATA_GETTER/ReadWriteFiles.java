package LOL_DATA_GETTER;
import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author akarami
 *
 */

public class ReadWriteFiles {
	
	//Clear existing data in a file with fileName
	public static void clearFile(String fileName) throws IOException{
		PrintWriter writer = new PrintWriter(fileName);
		writer.print("");
		writer.close();
	}
	
	//Add one string line to an existing file with fileName
	public static void writeLineToFile(String line, String fileName) throws IOException{
		try (BufferedWriter bw = new BufferedWriter (new FileWriter(fileName, true))) {
			bw.write(line);
			bw.flush();
		} catch (IOException e) {
            throw e;
        }
	}

	//Load a text file into a JSON Object : used when reading matches or time lines
	public static JSONObject readJsonFromFile(String filename) throws Exception{
        FileReader file = new FileReader(filename);
        @SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(file);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(sb.toString()); 
        return json;
    }
}
