package com.penguintoast.mvfbla.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.esotericsoftware.kryo.util.IntMap;
import com.esotericsoftware.kryo.util.ObjectMap;
import com.esotericsoftware.kryo.util.ObjectMap.Values;

public class QuestionParser {

	public static void main(String[] args) throws Exception {
		IntMap<Integer> letterToNumber = new IntMap<Integer>();
		letterToNumber.put('A', 1);
		letterToNumber.put('B', 2);
		letterToNumber.put('C', 3);
		letterToNumber.put('D', 4);
		
		File inputFile = new File("questions2006unparsed.txt");
		File outputFile = new File("questions2006.txt");
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		PrintWriter pw = new PrintWriter(new FileWriter(outputFile));
		
		boolean inAnswers = false;
		ObjectMap<Integer, String[]> questions = new ObjectMap<Integer, String[]>();
		int currentQuestion = -1;
		
		String line = null;
		while((line = br.readLine()) != null) {
			if(line.trim().equals("")) {
				continue;
			}
			if(line.equalsIgnoreCase("Answers:")) {
				inAnswers = true;
				continue;
			}
			
			if(!inAnswers) {
				String[] split = line.split("\\)");
				if(split.length == 1) {
					questions.get(currentQuestion)[0] += "  " + line;
				} else {
					char firstChar = split[0].charAt(0);
					if(firstChar == '\t') {
						questions.get(currentQuestion)[letterToNumber.get(split[0].charAt(1))] = split[1].substring(1);
					} else {
						currentQuestion = Integer.parseInt(split[0]);
						String[] toPut = new String[5];
						toPut[0] = split[1].substring(1);
						questions.put(currentQuestion, toPut);
					}
				}
			} else {
				String[] split = line.split("\\)");
				int num = Integer.parseInt(split[0]);
				if(questions.containsKey(num)) {
					int correct = letterToNumber.get(split[1].charAt(1));
					if(correct != 1) {
						String temp = questions.get(num)[1];
						questions.get(num)[1] = questions.get(num)[correct];
						questions.get(num)[correct] = temp;
					}
				}
			}
		}
		
		Values<String[]> values = questions.values();
		while(values.hasNext()) {
			String[] next = values.next();
			for(int i = 0; i < next.length; i++) {
				pw.println(next[i]);
			}
			pw.println();
		}
		
		pw.flush();
		pw.close();
		br.close();
	}

}
