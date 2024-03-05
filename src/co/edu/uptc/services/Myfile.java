package co.edu.uptc.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class Myfile {
	File f;
	FileWriter fw;
	FileOutputStream fo;
	OutputStreamWriter osw;
	BufferedWriter bw = null;
	FileReader fr;
	BufferedReader br = null;
	FileInputStream fl = null;
	InputStreamReader isr = null;

	public Myfile(String nameFile) {
		f = new File(nameFile);
	}

	public void open(char modo) {
		try {
			// modo escritura "w" crea el archivo
			if (modo == 'w') {
				fw = new FileWriter(f);
				fo = new FileOutputStream(f);
				osw = new OutputStreamWriter(fo, StandardCharsets.UTF_8);
				bw = new BufferedWriter(osw);
			} else {
				// modo lectura "r" abre el archivo
				fr = new FileReader(f);
				fl = new FileInputStream(f);
				isr = new InputStreamReader(fl, StandardCharsets.UTF_8);
				br = new BufferedReader(isr);
				
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Almacena la cadena ingresada por parametro
	public void save(String cad) {
		if (bw != null) {
			try {
				bw.write(cad);
				bw.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Lee un archivo 
	public ArrayList<String> read() {
		ArrayList<String> cads = new ArrayList<>();
		String cad;
		try {
			while ((cad=br.readLine())!=null) {
				cads.add(cad);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cads;
	}
	// lee byte a byte un archivo;
	public byte[] readByte() {
		byte[] bytes = new byte[(int) f.length()];
		try {
			bytes = fl.readAllBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	// cierra archivo modos R/W
	public void close() {
		try {
			if (br != null)
				br.close();
			if (bw != null)
				bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
