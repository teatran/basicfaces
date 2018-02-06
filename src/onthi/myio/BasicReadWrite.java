package onthi.myio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BasicReadWrite
{
	final static String INPUT_FILE = "D:\\indemo.txt";
	final static String OUTPUT_FILE = "D:\\outdemo.txt";
	final static Charset ENCODING = StandardCharsets.UTF_8;

	public static void main(String[] args)
	{
		/*System.out.println("What's your name? ");
		Scanner in = new Scanner(System.in);
		String name = in.nextLine();
		System.out.println("Hello, " + name);
		in.close();*/
		
		BasicReadWrite basic = new BasicReadWrite();
		try {
			// basic.ioSmallFile();
			basic.ioSmallFileInLines();
			// basic.ioLargeFile();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void ioSmallFile() throws IOException 
	{
		// read bytes then convert to String
		Path inpath = Paths.get(INPUT_FILE);
		byte[] bytes = Files.readAllBytes(inpath);
		String content = new String(bytes, ENCODING);
		
		System.out.println(content);
		
		// write file
		Path outpath = Paths.get(OUTPUT_FILE);
		Files.write(outpath, content.getBytes(ENCODING));
		
	}
	
	public void ioSmallFileInLines() throws IOException
	{
		Path path = Paths.get(INPUT_FILE);
		List<String> lines = Files.readAllLines(path);
		System.out.println("There are " + lines.size() + " lines");
		
		// count occurence of each word
		Map<String, Integer> occurs = new HashMap<>();
		for (String line : lines) {
			String[] words = line.split("[,!.?\\/ ]");
			for (String word : words) {
				if (occurs.get(word) == null) {
					occurs.put(word, 1);
				}
				else {
					occurs.put(word, occurs.get(word) + 1);
				}
			}
		}
		// write file
		Path outpath = Paths.get(OUTPUT_FILE);
		Files.write(outpath, occurs.toString().getBytes(ENCODING));
				
	}
	
	public void ioLargeFile() throws IOException 
	{
		Path inpath = Paths.get(INPUT_FILE);
		Path outpath = Paths.get(OUTPUT_FILE);
		try (Scanner scanner = new Scanner(inpath, ENCODING.name());
			 BufferedWriter writer = Files.newBufferedWriter(outpath, ENCODING)) 
		{
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.contains("Holmes")) System.out.println(line);
				writer.write(line);
				writer.newLine();
			}
		}
		
	}

}
