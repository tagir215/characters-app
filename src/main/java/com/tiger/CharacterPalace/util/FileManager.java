package com.tiger.CharacterPalace.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import com.tiger.CharacterPalace.model.LmTrie;

public class FileManager {
	public static String getResourcePath(String path) {
		URL url = FileManager.class.getClassLoader().getResource(path);
		return url.getFile();
	}
	
	public static String fileToString(String path) {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			File file = new File(getResourcePath(path));	
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine())!=null) {
				stringBuilder.append(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return stringBuilder.toString();
	}
	
	public static LmTrie fileToTrie(String path) {
		LmTrie trie = new LmTrie();
		try(BufferedReader reader = new BufferedReader(new FileReader(getResourcePath(path)))){
			String line;
			while((line = reader.readLine())!=null) {
				trie.insert(line);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return trie;
	}
	
	public static Font createFont(String path) {
		Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File(path));
		} catch (FontFormatException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return font;
	}
	
	
	 public static void writeToFile(String chineseCharacters)  {
	        File tempFile = null;
			try {
				tempFile = File.createTempFile("chinese_characters", ".txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        tempFile.deleteOnExit(); // Delete the temporary file when the JVM exits

	        try (FileWriter writer = new FileWriter(tempFile)) {
	            writer.write(chineseCharacters);
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        System.out.println("Chinese characters have been written to the temporary file: " + tempFile.getAbsolutePath());
	    }
}
