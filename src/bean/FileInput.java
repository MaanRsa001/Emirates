package bean;
 
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;

/**
 * This program reads a text file line by line and print to the console. It uses
 * FileOutputStream to read the file.
 * 
 */

public class FileInput {

	String path = "", value = "";

	boolean sts = false;

	public String WriteFiles(String pathh, String valuee) {
		path = pathh;
		value = valuee;
		// system.out.println("path is"+path);
		// system.out.println("values is"+value);

		//Vinoth
		
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		File file = new File(path);
		try {

			boolean exist = file.createNewFile();
			if (!exist) {
				// system.out.println("File already exists.");
				FileOutputStream fop = new FileOutputStream(file);
				fop.write(value.getBytes());
				fop.flush();
				fop.close();
				// system.out.println("The data has been written");
				// system.out.println("File created successfully.");
				sts = true;

			} else {
				// system.out.println("File already exists.");
				FileOutputStream fop = new FileOutputStream(file);
				fop.write(value.getBytes());
				fop.flush();
				fop.close();
				// system.out.println("The data has been written");
				// system.out.println("File created successfully.");
				sts = true;

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (sts)
			return "success";
		else
			return "failure";
	}
}