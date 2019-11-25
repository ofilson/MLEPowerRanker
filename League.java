import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * The league class oversees the League ArrayList, allowing you to add, remove, and get
 * different teams in the ArrayList. It also handles the loading of the CSV file and the sorts for ranking the teams. 
 * @author oscarfilson
 */
public class League {
	
	private ArrayList<Team> teams;
	private int currentWeek;
	TeamSorter teamSorter;
	DecimalFormat df = new DecimalFormat("###.##");
	boolean hasDoneSoS;
	
	/**
	 * Makes a new League array
	 */
	public League() {
		teams = new ArrayList<Team>();
		currentWeek = 0;
		hasDoneSoS = false;
	}
	
	/**
	 * Returns all the teams in the league
	 * @return ArrayList<Team> - The full list of all the teams
	 */
	public ArrayList<Team> getTeams(){
		return teams;
	}
	
	/**
	 * Adds a team to the end of the ArrayList
	 * @param Team - The team you are adding
	 */
	public void addTeam(Team team) {
		teams.add(team);
	}
	
	/**
	 * Returns the current week of the league
	 * @return int - the current week
	 */
	public int getCurrentWeek() {
		return currentWeek;
	}
	
	public void setMaxAndMins() {
		double goalsMin = 999.0;
		double goalsMax = 0.0;
		double assistsMin = 999.0;
		double assistsMax = 0.0;
		double savesMin = 999.0;
		double savesMax = 0.0;
		double shotsMin = 999.0;
		double shotsMax = 0;
		double shotPercentageMin = 1.0;
		double shotPercentageMax = 0.0;
		double goalsAgainstMin = 999.0;
		double goalsAgainstMax = 0;
		double playerSalaryMax = 0.0;
		double playerSalaryMin = 999.0;
		
		for(Team team : teams) {
			team.totalsCalculations();
			if(team.getTotalGoals() > goalsMax) {
				goalsMax = team.getTotalGoals();
			} if(team.getTotalGoals() < goalsMin ) {
				goalsMin = team.getTotalGoals();
			} if(team.getTotalAssists() > assistsMax) {
				assistsMax = team.getTotalAssists();
			} if(team.getTotalAssists() < assistsMin) {
				assistsMin = team.getTotalAssists();
			} if(team.getTotalSaves() > savesMax) {
				savesMax = team.getTotalSaves();
			} if(team.getTotalSaves() < savesMin) {
				savesMin = team.getTotalSaves();
			} if(team.getTotalShots() > shotsMax) {
				shotsMax = team.getTotalShots();
			} if(team.getTotalShots() < shotsMin) {
				shotsMin = team.getTotalShots();
			} if(team.getShotPercentage() > shotPercentageMax) {
				shotPercentageMax = team.getShotPercentage();
			} if(team.getShotPercentage() < shotPercentageMin) {
				shotPercentageMin = team.getShotPercentage();
			} if(team.getTotalGoalsAgainst() > goalsAgainstMax) {
				goalsAgainstMax = team.getTotalGoalsAgainst();
			} if(team.getTotalGoalsAgainst() < goalsAgainstMin) {
				goalsAgainstMin = team.getTotalGoalsAgainst();
			} for(Player p: team.getPlayers()) {
				p.calculateAdjustedSalary(team.getActualWins());
				if(p.getAdjustedSalary() > playerSalaryMax) {
					playerSalaryMax = p.getAdjustedSalary();
				}
				if(p.getAdjustedSalary() < playerSalaryMin) {
					playerSalaryMin = p.getAdjustedSalary();
				}
			}
		} 
		for(Team team : teams) {
			team.setGoalsMax(goalsMax);
			team.setGoalsMin(goalsMin);
			team.setAssistsMax(assistsMax);
			team.setAssistsMin(assistsMin);
			team.setSavesMax(savesMax);
			team.setSavesMin(savesMin);
			team.setShotsMax(shotsMax);
			team.setShotsMin(shotsMin);
			team.setShotPercentageMax(shotPercentageMax);
			team.setShotPercentageMin(shotPercentageMin);
			team.setGoalsAgainstMax(goalsAgainstMax);
			team.setGoalsAgainstMin(goalsAgainstMin);
			team.setPlayerSalaryMax(playerSalaryMax);
			team.setPlayerSalaryMin(playerSalaryMin);
		}
	}
	
	public void setSOS() {
		double sos = 0.0;
		for(Team team: teams) {
			for(Team o:team.getOpponents()) {
				sos += o.getAdjustedWins();
			}
			team.setSOS(sos/7.0);
			sos = 0;
		}
	}
	
	public void setSOSMaxAndMins() {
		double sosMax = 0.0;
		double sosMin = 999.0;
		
		for(Team team : teams) {
			if(team.getSOS() > sosMax) {
				sosMax = team.getSOS();
			} if(team.getSOS() < sosMin ) {
				sosMin = team.getSOS();
			}
		}
		for(Team team : teams) {
			team.setSOSMax(sosMax);
			team.setSOSMin(sosMin);
		}
		hasDoneSoS = true;
	}
	
	/**
	 * Sets the current week of the league
	 * @param int - the current week
	 */
	public void setCurrentWeek(int currentWeek) {
		this.currentWeek = currentWeek;
	}
	
	/**
	 * Sorts the list of teams by total wins
	 */
	public void sortNormal() {
		teamSorter = new TeamSorter(teams);
		teamSorter.sortByActualWins();
	}
	
	/**
	 * Sorts the list of teams by adjusted wins
	 */
	public void sortAdjusted(){
		teamSorter = new TeamSorter(teams);
		teamSorter.sortByAdjustedWins();
	}

	
	/**
	 * Sorts the list of teams by momentum
	 */
	public void sortMomentum(){
		teamSorter = new TeamSorter(teams);
		teamSorter.sortByMomentumWins();
	}
	
	/**
	 * Sorts the list of teams by their name alphabetically
	 */
	public void sortName() {
		teamSorter = new TeamSorter(teams);
		teamSorter.sortByName();
	}
	
	/**
	 * Sorts the list of teams by their stats wins
	 */
	public void sortStats() {
		teamSorter = new TeamSorter(teams);
		teamSorter.sortByStatsWins();
	}
	
	/**
	 * Sorts the list of teams by their player's score
	 */
	public void sortPlayers() {
		teamSorter = new TeamSorter(teams);
		teamSorter.sortByPlayerWins();
	}
	
	/**
	 * Sorts the list of teams by their Strength Of Schedule
	 */
	public void sortSchedule() {
		teamSorter = new TeamSorter(teams);
		teamSorter.sortBySoSWins();
	}
	
	/**
	 * Loads the data necessary to perform the program's various functions from the csv file provided.
	 * @param filepath - the filepath of the csv being used. 
	 */
//	public void loadScheduleFromCSV(String filepath) {
//		File file = new File(filepath);
//		if(file.exists() && file.isFile()) {
//			try {
//				Scanner filescan = new Scanner(file);
//				
//				while(filescan.hasNextLine()) {
//					String line = filescan.nextLine();
//					Scanner linescan = new Scanner(line);
//					linescan.useDelimiter(",");
//					
//					Team team = new Team(linescan.next());
//					int currentWeek = linescan.nextInt();
//					setCurrentWeek(currentWeek);
//					team.setCurrentWeek(currentWeek);
//					int totalScore = linescan.nextInt();
//					
//					for(int i = 0; i < currentWeek; i++) {
//						int wins = linescan.nextInt();
//						int goals = linescan.nextInt();
//						int assists = linescan.nextInt();
//						int saves = linescan.nextInt();
//						int shots = linescan.nextInt();
//						int goalsAgainst = linescan.nextInt();
//						Week result = new Week(wins,goals,assists,saves,shots,goalsAgainst);
//						team.addResult(result);
//					}
//					this.addTeam(team);
//					linescan.close();
//				}
//				this.setMaxAndMins();
//				this.calculations();
//				filescan.close();
//			} catch (FileNotFoundException e) {
//				System.out.println("File not found: " + filepath);
//			}
//		}
//	}
	
	/**
	 * Loads the data necessary to perform the program's various functions from the csv file provided.
	 * @param filepath - the filepath of the csv being used. 
	 */
	public void loadScheduleFromCSV(String filepath) {
		File file = new File(filepath);
		if(file.exists() && file.isFile()) {
			try {
				Scanner filescan = new Scanner(file);
				
				while(filescan.hasNextLine()) {
					String line = filescan.nextLine();
					Scanner linescan = new Scanner(line);
					linescan.useDelimiter(",");
					
					int week = linescan.nextInt();
					String teamOneName = linescan.next();
					int teamOneWins = linescan.nextInt();
					String teamOnePlayerOneName = linescan.next();
					double teamOnePlayerOneSalary = linescan.nextDouble();
					int teamOnePlayerOneGoals = linescan.nextInt();
					int teamOnePlayerOneAssists = linescan.nextInt();
					int teamOnePlayerOneSaves = linescan.nextInt();
					int teamOnePlayerOneShots = linescan.nextInt();
					String teamOnePlayerTwoName = linescan.next();
					double teamOnePlayerTwoSalary = linescan.nextDouble();
					int teamOnePlayerTwoGoals = linescan.nextInt();
					int teamOnePlayerTwoAssists = linescan.nextInt();
					int teamOnePlayerTwoSaves = linescan.nextInt();
					int teamOnePlayerTwoShots = linescan.nextInt();
					String teamTwoName = linescan.next();
					int teamTwoWins = linescan.nextInt();
					String teamTwoPlayerOneName = linescan.next();
					double teamTwoPlayerOneSalary = linescan.nextDouble();
					int teamTwoPlayerOneGoals = linescan.nextInt();
					int teamTwoPlayerOneAssists = linescan.nextInt();
					int teamTwoPlayerOneSaves = linescan.nextInt();
					int teamTwoPlayerOneShots = linescan.nextInt();
					String teamTwoPlayerTwoName = linescan.next();
					double teamTwoPlayerTwoSalary = linescan.nextDouble();
					int teamTwoPlayerTwoGoals = linescan.nextInt();
					int teamTwoPlayerTwoAssists = linescan.nextInt();
					int teamTwoPlayerTwoSaves = linescan.nextInt();
					int teamTwoPlayerTwoShots = linescan.nextInt();
					
					//Team one set ups
					if(week > 1) {
						for(Team team : teams) {
							if(team.getName().equalsIgnoreCase(teamOneName)) {
								Week result = new Week(teamOneWins);
								result.setGoals(teamOnePlayerOneGoals + teamOnePlayerTwoGoals);
								result.setAssists(teamOnePlayerOneAssists + teamOnePlayerTwoAssists);
								result.setSaves(teamOnePlayerOneSaves + teamOnePlayerTwoSaves);
								result.setShots(teamOnePlayerOneShots + teamOnePlayerTwoShots);
								result.setGoalsAgainst(teamTwoPlayerOneGoals + teamTwoPlayerTwoGoals);
								team.setCurrentWeek(week);
								team.addResult(result);
								team.addOpponentName(teamTwoName);
								int count = 0;
								for(Player player : team.getPlayers()) {
									if(teamOnePlayerOneName.equalsIgnoreCase(player.getName())) {
										player.addGoals(teamOnePlayerOneGoals);
										player.addAssists(teamOnePlayerOneAssists);
										player.addSaves(teamOnePlayerOneSaves);
										player.addShots(teamOnePlayerOneShots);
										player.addGamesPlayed();
										player.addWins(teamOneWins);
										player.setSalary(teamOnePlayerOneSalary);
										count = 1;
									} 
								}
								if(count == 0) {
									Player playerOne = new Player(teamOnePlayerOneName);
									playerOne.addGoals(teamOnePlayerOneGoals);
									playerOne.addAssists(teamOnePlayerOneAssists);
									playerOne.addSaves(teamOnePlayerOneSaves);
									playerOne.addShots(teamOnePlayerOneShots);
									playerOne.addGamesPlayed();
									playerOne.addWins(teamOneWins);
									playerOne.setSalary(teamOnePlayerOneSalary);
									team.addPlayer(playerOne);
								}
								count = 0;
								for(Player player : team.getPlayers()) {
									if(teamOnePlayerTwoName.equals(player.getName())) {
										player.addGoals(teamOnePlayerTwoGoals);
										player.addAssists(teamOnePlayerTwoAssists);
										player.addSaves(teamOnePlayerTwoSaves);
										player.addShots(teamOnePlayerTwoShots);
										player.addGamesPlayed();
										player.addWins(teamOneWins);
										player.setSalary(teamOnePlayerTwoSalary);
										count = 1;
									} 
								}
								if(count == 0) {
									Player playerTwo = new Player(teamOnePlayerTwoName);
									playerTwo.addGoals(teamOnePlayerTwoGoals);
									playerTwo.addAssists(teamOnePlayerTwoAssists);
									playerTwo.addSaves(teamOnePlayerTwoSaves);
									playerTwo.addShots(teamOnePlayerTwoShots);
									playerTwo.addGamesPlayed();
									playerTwo.addWins(teamOneWins);
									playerTwo.setSalary(teamOnePlayerTwoSalary);
									team.addPlayer(playerTwo);
								}
							}
						}
					} else {
						Team team = new Team(teamOneName);
						Week result = new Week(teamOneWins);
						result.setGoals(teamOnePlayerOneGoals + teamOnePlayerTwoGoals);
						result.setAssists(teamOnePlayerOneAssists + teamOnePlayerTwoAssists);
						result.setSaves(teamOnePlayerOneSaves + teamOnePlayerTwoSaves);
						result.setShots(teamOnePlayerOneShots + teamOnePlayerTwoShots);
						result.setWins(teamOneWins);
						result.setGoalsAgainst(teamTwoPlayerOneGoals + teamTwoPlayerTwoGoals);
						Player playerOne = new Player(teamOnePlayerOneName);
						playerOne.addGoals(teamOnePlayerOneGoals);
						playerOne.addAssists(teamOnePlayerOneAssists);
						playerOne.addSaves(teamOnePlayerOneSaves);
						playerOne.addShots(teamOnePlayerOneShots);
						playerOne.addGamesPlayed();
						playerOne.addWins(teamOneWins);
						playerOne.setSalary(teamOnePlayerOneSalary);
						Player playerTwo = new Player(teamOnePlayerTwoName);
						playerTwo.addGoals(teamOnePlayerTwoGoals);
						playerTwo.addAssists(teamOnePlayerTwoAssists);
						playerTwo.addSaves(teamOnePlayerTwoSaves);
						playerTwo.addShots(teamOnePlayerTwoShots);
						playerTwo.addGamesPlayed();
						playerTwo.addWins(teamOneWins);
						playerTwo.setSalary(teamOnePlayerTwoSalary);
						team.setCurrentWeek(week);
						team.addPlayer(playerOne);
						team.addPlayer(playerTwo);
						team.addResult(result);
						this.addTeam(team);
						team.addOpponentName(teamTwoName);
					}
					
					//Team Two SetUps
					if(week > 1) {
						for(Team team : teams) {
							if(team.getName().equalsIgnoreCase(teamTwoName)) {
								Week result = new Week(teamTwoWins);
								result.setGoals(teamTwoPlayerOneGoals + teamTwoPlayerTwoGoals);
								result.setAssists(teamTwoPlayerOneAssists + teamTwoPlayerTwoAssists);
								result.setSaves(teamTwoPlayerOneSaves + teamTwoPlayerTwoSaves);
								result.setShots(teamTwoPlayerOneShots + teamTwoPlayerTwoShots);
								result.setWins(teamTwoWins);
								result.setGoalsAgainst(teamOnePlayerOneGoals + teamOnePlayerTwoGoals);
								team.setCurrentWeek(week);
								team.addResult(result);
								team.addOpponentName(teamOneName);
								int count = 0;
								for(Player player : team.getPlayers()) {
									if(teamTwoPlayerOneName.equalsIgnoreCase(player.getName())) {
										player.addGoals(teamTwoPlayerOneGoals);
										player.addAssists(teamTwoPlayerOneAssists);
										player.addSaves(teamTwoPlayerOneSaves);
										player.addShots(teamTwoPlayerOneShots);
										player.addGamesPlayed();
										player.addWins(teamTwoWins);
										player.setSalary(teamTwoPlayerOneSalary);
										count = 1;
									} 
								}
								if(count == 0) {
									Player playerOne = new Player(teamTwoPlayerOneName);
									playerOne.addGoals(teamTwoPlayerOneGoals);
									playerOne.addAssists(teamTwoPlayerOneAssists);
									playerOne.addSaves(teamTwoPlayerOneSaves);
									playerOne.addShots(teamTwoPlayerOneShots);
									playerOne.addGamesPlayed();
									playerOne.addWins(teamTwoWins);
									playerOne.setSalary(teamTwoPlayerOneSalary);
									team.addPlayer(playerOne);
								}
								count = 0;
								for(Player player : team.getPlayers()) {
									if(teamTwoPlayerTwoName.equals(player.getName())) {
										player.addGoals(teamTwoPlayerTwoGoals);
										player.addAssists(teamTwoPlayerTwoAssists);
										player.addSaves(teamTwoPlayerTwoSaves);
										player.addShots(teamTwoPlayerTwoShots);
										player.addGamesPlayed();
										player.addWins(teamTwoWins);
										player.setSalary(teamTwoPlayerTwoSalary);
										count = 1;
									} 
								}
								if(count == 0) {
									Player playerTwo = new Player(teamTwoPlayerTwoName);
									playerTwo.addGoals(teamTwoPlayerTwoGoals);
									playerTwo.addAssists(teamTwoPlayerTwoAssists);
									playerTwo.addSaves(teamTwoPlayerTwoSaves);
									playerTwo.addShots(teamTwoPlayerTwoShots);
									playerTwo.addGamesPlayed();
									playerTwo.addWins(teamTwoWins);
									playerTwo.setSalary(teamTwoPlayerTwoSalary);
									team.addPlayer(playerTwo);
								}
							}
						}
					} else {
						Team team = new Team(teamTwoName);
						Week result = new Week(teamTwoWins);
						result.setGoals(teamTwoPlayerOneGoals + teamTwoPlayerTwoGoals);
						result.setAssists(teamTwoPlayerOneAssists + teamTwoPlayerTwoAssists);
						result.setSaves(teamTwoPlayerOneSaves + teamTwoPlayerTwoSaves);
						result.setShots(teamTwoPlayerOneShots + teamTwoPlayerTwoShots);
						result.setWins(teamTwoWins);
						result.setGoalsAgainst(teamOnePlayerOneGoals + teamOnePlayerTwoGoals);
						Player playerOne = new Player(teamTwoPlayerOneName);
						playerOne.addGoals(teamTwoPlayerOneGoals);
						playerOne.addAssists(teamTwoPlayerOneAssists);
						playerOne.addSaves(teamTwoPlayerOneSaves);
						playerOne.addShots(teamTwoPlayerOneShots);
						playerOne.addGamesPlayed();
						playerOne.addWins(teamTwoWins);
						playerOne.setSalary(teamTwoPlayerOneSalary);
						Player playerTwo = new Player(teamTwoPlayerTwoName);
						playerTwo.addGoals(teamTwoPlayerTwoGoals);
						playerTwo.addAssists(teamTwoPlayerTwoAssists);
						playerTwo.addSaves(teamTwoPlayerTwoSaves);
						playerTwo.addShots(teamTwoPlayerTwoShots);
						playerTwo.addGamesPlayed();
						playerTwo.addWins(teamTwoWins);
						playerTwo.setSalary(teamTwoPlayerTwoSalary);
						team.setCurrentWeek(week);
						team.addPlayer(playerOne);
						team.addPlayer(playerTwo);
						team.addResult(result);
						this.addTeam(team);
						this.setCurrentWeek(week); 
						team.addOpponentName(teamOneName);
					}
					linescan.close();
				}
				setMaxAndMins();
				calculations();
				fillOpponents();
				setSOS();
				setSOSMaxAndMins();
				calculations();
				filescan.close();
			} catch (FileNotFoundException e) {
				System.out.println("File not found: " + filepath);
			}
		}
	}
	
	public void calculations() {
		for(Team team : teams) {
			team.calculations();
			if(hasDoneSoS) {
				team.calculateAdjustedWinsFinal();
			} else {
				team.calculateAdjustedWins();
			}
		}
	}
	
	/**
	 * Fill in the opponents for each team
	 */
	public void fillOpponents() {
		for(Team team: teams) {
			for(Team t: teams) {
				if(team.getOpponentNames().contains(t.getName())) {
					team.addOpponent(t);
				}
			}
		}
		setSOS();
		setSOSMaxAndMins();
		for(Team team: teams) {
			team.sosCalculations();
			team.calculateAdjustedWinsFinal();
		}
	}
	
	/**
	 * Formats the league into a readable and easily understandable string.
	 * 
	 * @return String - each team in the league and their various stats. 
	 */
	public String toString() {
		String output = "";
		String starline = "*******************************";
		output += "Week :: " + getCurrentWeek() + "\n";
		output += starline + "\n";
		for(Team team:teams) {
			output += team.toString();
			output += "\n" + starline + "\n";
		}
		return output;
	}
	
	public String toStringMed() {
		String output = "";
		String starline = "*******************************";
		int count = 0;
		output += starline + "\n";
		for(Team team:teams) {
			count += 1;
			output += count + ". ";
			output += team.getName();
			output += "\tActualWins = " + df.format(team.getActualWins());
			output += "\t\tAdjustedWins = " + df.format(team.getAdjustedWins());
			output += "\tMomentumWins = " + df.format(team.getMomentumWins());
			output += "\tStatsWins = " + df.format(team.getStatWins());
			output += "\tPlayerWins = " + df.format(team.getPlayerWins());
			output += "\tSoSWins = " + df.format(team.getSOSWins());
			output += "\n";
		}
		output += "\n";
		return output;
	}
	
	public String toStringShort() {
		String output = "";
		
		String starline = "*******************************";
		int count = 0;
		output += starline + "\n";
		for(Team team:teams) {
			count += 1;
			output += count + ". ";
			output += team.getName();
			output += "		AdjustedWins = " + df.format(team.getAdjustedWins()) + "\n";
		}
		output += "\n";
		return output;
	}
	
	public String toCSV() {
		try {
			PrintWriter writer = new PrintWriter(new File("Results.csv"));

			StringBuilder sb = new StringBuilder();

			sb.append("Team");
			sb.append(",");
			sb.append("Total Wins");
			sb.append(",");
			sb.append("Adjusted Wins");
			sb.append(",");
			sb.append("Momentum Wins");
			sb.append(",");
			sb.append("Momentum Score");
			sb.append(",");
			sb.append("Stat Wins");
			sb.append(",");
			sb.append("Stat Score");
			sb.append(",");
			sb.append("Player Wins");
			sb.append(",");
			sb.append("Strength Of Schedule Wins");
			sb.append(",");
			sb.append("Strength Of Schedule Score");
			sb.append(",");
			sb.append("Offense Score");
			sb.append(",");
			sb.append("Defense Score");
			sb.append(",");
			sb.append("Goals Score");
			sb.append(",");
			sb.append("Assists Score");
			sb.append(",");
			sb.append("Saves Score");
			sb.append(",");
			sb.append("Shots Score");
			sb.append(",");
			sb.append("Shot Percentage Score");
			sb.append(",");
			sb.append("Goals Against Score");
			sb.append(",");
			sb.append("Strength Of Schedule");
			sb.append(",");
			sb.append("Player Name");
			sb.append(",");
			sb.append("Player Score");
			sb.append("\n");

			for (Team t : teams) {
				sb.append(t.getName());
				sb.append(",");
				sb.append(t.getActualWins());
				sb.append(",");
				sb.append(t.getAdjustedWins());
				sb.append(",");
				sb.append(t.getMomentumWins());
				sb.append(",");
				sb.append(t.getMomentumScore());
				sb.append(",");
				sb.append(t.getStatWins());
				sb.append(",");
				sb.append(t.getStatsScore());
				sb.append(",");
				sb.append(t.getPlayerWins());
				sb.append(",");
				sb.append(t.getSOSWins());
				sb.append(",");
				sb.append(t.getSOSScore());
				sb.append(",");
				sb.append(t.getOffenseScore());
				sb.append(",");
				sb.append(t.getDefenseScore());
				sb.append(",");
				sb.append(t.getGoalsScore());
				sb.append(",");
				sb.append(t.getAssistsScore());
				sb.append(",");
				sb.append(t.getSavesScore());
				sb.append(",");
				sb.append(t.getShotsScore());
				sb.append(",");
				sb.append(t.getShotPercentageScore());
				sb.append(",");
				sb.append(t.getGoalsAgainstScore());
				sb.append(",");
				sb.append(t.getSOS());
				sb.append(",");
				for (Player p : t.getPlayers()) {
					p.getName();
					sb.append(",");
					p.getPlayerScore();
					sb.append(",");
				}
				sb.append("\n");
			}

			writer.write(sb.toString());
			writer.close();

			return "CSV Outputted";

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error found";
		}
	}
}