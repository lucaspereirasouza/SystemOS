package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class Reader extends FileReader {

	public Reader(File file, Charset charset) throws IOException {
		super(file, charset);
		
	}
	public Reader(File file) throws FileNotFoundException {
		super(file);
	}
	// Read all content of a file and returns a String
	public String getText() {
		return null;
	}
	//Read text until find the content inside name
	public String getContent(String content) {
		return null;
	}
	//Read text until find name
	public String getName() {
		return null;
	}
	
}

