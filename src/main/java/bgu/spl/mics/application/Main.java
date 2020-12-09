package bgu.spl.mics.application;

import bgu.spl.mics.JsonParse;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.passiveObjects.Attack;
import bgu.spl.mics.application.passiveObjects.Diary;
import bgu.spl.mics.application.services.LeiaMicroservice;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

/** This is the Main class of the application. You should parse the input file,
 * create the different components of the application, and run the system.
 * In the end, you should output a JSON.
 */
public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		Gson gson = new Gson();
		Reader reader = new FileReader(args[0]);
		JsonParse input =gson.fromJson(reader, JsonParse.class);
		MicroService Leia = new LeiaMicroservice(input.getAttacks());
		Diary diary = Diary.getInstance();
		diary.writeOutput("Information");
	}
}
