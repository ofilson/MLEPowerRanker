import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Personal Project: PowerRanker
 * 
 * This serves as the driver for the Power Ranking Program, welcomes the user
 * and gets the .csv information necessary to run the program
 *
 * @author Oscar Filson
 */

public class PowerRanker {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);

		String welcome = "Welcome to the MLE PowerRanker!";
		String starline = "*******************************";
		System.out.print(welcome + "\n" + starline + "\n");

		System.out.print("What is the file path of the league's data? ");
		String filepath = scanny.next();

		League league = new League();
		league.loadScheduleFromCSV(filepath);

		////////////////////////////////
		// SHOW ALL DATA ON EACH TEAM //
		////////////////////////////////

//		league.sortAdjusted();
//		System.out.print("Adjusted:\n" + league.toString());

		///////////////////////////////
		// SORT ALL CATEGORIES SHORT //
		///////////////////////////////

//		league.sortAdjusted();
//		System.out.print("Adjusted:\n" + league.toStringShort());
//
//		league.sortNormal();
//		System.out.print("Normal:\n" + league.toStringShort());
//		
//		league.sortMomentum();
//		System.out.print("Momentum:\n" + league.toStringShort());
//		
//		league.sortStats();
//		System.out.print("Stats:\n" + league.toStringShort());
//		
//		league.sortPlayers();
//		System.out.print("Players:\n" + league.toStringShort());
//		
//		league.sortSchedule();
//		System.out.println("Strength Of Schedule: \n" + league.toStringShort());

		///////////////////////////////////////////
		// SORT ON ADJUSTED, SHOW ALL CATEGORIES //
		///////////////////////////////////////////

		league.sortAdjusted();
		System.out.print("Adjusted:\n" + league.toStringMed());

		////////////////
		// OUTPUT CSV //
		////////////////
		
		league.sortAdjusted();
		System.out.print("CSV: " + "\n" + league.toCSV());
		
		scanny.close();
	}
}