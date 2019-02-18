package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManagement {

	public void save(String data, File file) throws IOException {
		FileWriter out = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(out);
		bw.write(data);
		bw.close();
	}

	public String fileToString(File file) {
		String content = "";
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String line;
			while ((line = br.readLine()) != null)
				content += line + '\n';

			fr.close();
			br.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
}