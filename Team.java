import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.Comparator;   
/**
 * The team class oversees the Team object, allowing you to manage many different things about a team,
 * including adding players, adding stats, and calculating the algorithms that rank the teams. 
 * @author oscarfilson
 */

public class Team {
	private ArrayList<Player> players;
	private ArrayList<Week> results;
	private ArrayList<String> opponentNames;
	private ArrayList<Team> opponents;
	private double adjMomentum = 0;
	private int totalWins = 0;
	private double momWins = 0.0;
	private double adjWins = 0.0;
	private double momScore = 0.0;
	private double playerWins = 0.0;
	private double sosScore = 0.0;
	private double sosWins = 0.0;
	private double totalPlayerScore = 0.0;
	private String teamName = "";
	private int currentWeek = 0;
	private double shotPercentage = 0.0;
	private int totalGoals = 0;
	private int totalAssists = 0;
	private int totalSaves = 0;
	private int totalShots = 0;
	private int totalGoalsA = 0;
	private double goalsScore = 0.0;
	private double assistsScore = 0.0;
	private double savesScore = 0.0;
	private double shotsScore = 0.0;
	private double shotPercentageScore = 0.0;
	private double goalsAgainstScore = 0.0;
	private double defenseScore = 0.0;
	private double offenseScore = 0.0;
	private double statsScore = 0.0;
	private double statWins = 0.0;
	double goalsMax = 0.0;
	double goalsMin = 0.0;
	double assistsMax = 0.0;
	double assistsMin = 0.0;
	double savesMax = 0.0;
	double savesMin = 0.0;
	double shotsMax = 0.0;
	double shotsMin = 0.0;
	double shotPercentageMax = 0.0;
	double shotPercentageMin = 0.0;
	double goalsAgainstMax = 0.0;
	double goalsAgainstMin = 0.0;
	double playerSalaryMax = 0.0;
	double playerSalaryMin = 0.0;
	double sosMin = 0.0;
	double sosMax = 0.0;
	double sos;
	
	/**
	 * Makes a new Team object
	 */
	public Team() {
		players = new ArrayList<Player>();
		results = new ArrayList<Week>();
		opponentNames = new ArrayList<String>();
		opponents = new ArrayList<Team>();
	}
	
	/**
	 * Makes a new Team Object, with the team name already set
	 */
	public Team(String teamName) {
		players = new ArrayList<Player>();
		results = new ArrayList<Week>();
		opponentNames = new ArrayList<String>();
		opponents = new ArrayList<Team>();
		this.teamName = teamName;
	}

	/**
	 * Performs all the calculations necessary for the teams various wins and scores
	 */
	public void calculations() {
		momentumCalculations(); 
		statsCalculations();
		playerCalculations();
	}
	
	/** 
	 * Performs the initial adjusted win calculation
	 */
	public void calculateAdjustedWins() {
		adjWins = (momWins + (double) totalWins + statWins + playerWins)/4.0;
	}
	
	/** 
	 * Performs the final adjusted win calculation, accounting for strength of schedule
	 */
	public void calculateAdjustedWinsFinal() {
		adjWins = (momWins + (double) totalWins + statWins + playerWins + (sosWins/2.0))/4.5;
	}

	/**
	 * Performs all the calculations necessary for the teams momentum
	 */
	public void momentumCalculations() {
		int top =0;
		int bottom = 0;
		for(int i = 1; i <= results.size(); i++) {
			top += (i*results.get(i-1).getWins());
			bottom += i;
		}
		adjMomentum = (double) top/bottom;
		momScore = adjMomentum*20;
		momWins = (currentWeek*5)*(momScore/100);
	}
	
	/**
	 * Adds up all the weekly stats to get the totals. 
	 */
	public void totalsCalculations() {
		totalWins = 0;
		totalGoals = 0;
		totalAssists = 0;
		totalSaves = 0;
		totalShots = 0;
		totalGoalsA = 0;
		
		for(Week week : results) {
			totalWins += week.getWins();
			totalGoals += week.getGoals();
			totalAssists += week.getAssists();
			totalSaves += week.getSaves();
			totalShots += week.getShots();
			totalGoalsA += week.getGoalsAgainst();
		}
		shotPercentage = ((double) totalGoals)/ ((double) totalShots);
	}
	
	/**
	 * Performs all the calculations necessary for the teams stats
	 * @param maxAndMins
	 */
	public void statsCalculations() {
		//Variables for calculations
		double totalGames = currentWeek*5;
		double goalsPG = totalGoals/totalGames;
		double assistsPG = totalAssists/totalGames;
		double savesPG = totalSaves/totalGames;
		double shotsPG = totalShots/totalGames;
		double goalsAPG = totalGoalsA/totalGames;
		double goalsMax = this.goalsMax/totalGames;
		double goalsMin = this.goalsMin/totalGames;
		double assistsMax = this.assistsMax/totalGames;
		double assistsMin = this.assistsMin/totalGames;
		double savesMax = this.savesMax/totalGames;
		double savesMin = this.savesMin/totalGames;
		double shotsMax = this.shotsMax/totalGames;
		double shotsMin = this.shotsMin/totalGames;
		double goalsAgainstMax = this.goalsAgainstMax/totalGames;
		double goalsAgainstMin = this.goalsAgainstMin/totalGames;
		
		//calculations
		goalsScore = (goalsPG-goalsMin)/((goalsMax-goalsMin)/100);
		assistsScore = (assistsPG-assistsMin)/((assistsMax-assistsMin)/100);
		savesScore = (savesPG-savesMin)/((savesMax-savesMin)/100);
		shotsScore = (shotsPG-shotsMin)/((shotsMax-shotsMin)/100);
		shotPercentageScore = (shotPercentage-shotPercentageMin)/((shotPercentageMax-shotPercentageMin)/100);
		goalsAgainstScore = 100-(goalsAPG-goalsAgainstMin)/((goalsAgainstMax-goalsAgainstMin)/100);
		offenseScore = (goalsScore+assistsScore+shotsScore)/3;
		defenseScore = (goalsAgainstScore+savesScore+shotPercentageScore)/3;
		statsScore = (goalsScore+assistsScore+savesScore+shotsScore+shotPercentageScore+goalsAgainstScore)/6;
		statWins = totalGames*(statsScore/100);
	}
	
	public void playerCalculations() {
		double playerSalaryMax = this.playerSalaryMax;
		double playerSalaryMin = this.playerSalaryMin;
		
		for(Player player: getPlayers()) {
			player.calculateAdjustedSalary(totalWins);
			player.setPlayerScore((player.getAdjustedSalary() - playerSalaryMin)/((playerSalaryMax-playerSalaryMin)/100));
			totalPlayerScore += player.getPlayerScore();
		}
		totalPlayerScore = totalPlayerScore/((double) getPlayers().size());
		playerWins = ((currentWeek*5)*((totalPlayerScore/100)));
		
	}
	
	public void sosCalculations() {
		double sosMax = this.sosMax;
		double sosMin = this.sosMin;
		double sos = this.sos;
		
		sosScore = (sos - sosMin)/((sosMax-sosMin)/100);
		sosWins = ((currentWeek*5)*((sosScore/100)));
	}
	
	public void setPlayerWins(double playerWins) {
		this.playerWins = playerWins;
	}
	
	public double getPlayerWins() {
		return playerWins;
	}
	
	/**
	 * Returns the current array of Players in the team
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	/**
	 * Returns the current array of the names of opponent teams
	 * @return ArrayList<String> OpponentNames
	 */
	public ArrayList<String> getOpponentNames() {
		return opponentNames;
	}
	
	/**
	 * Returns the array of opponent teams
	 * @return ArrayList<Team> Opponents
	 */
	public ArrayList<Team> getOpponents() {
		return opponents;
	}
	
	/**
	 * Returns the SOS of the team
	 * @return double sos
	 */
	public double getSOS() {
		return sos;
	}
	
	/**
	 * Returns the SOS wins of the team
	 * @return double sosWins
	 */
	public double getSOSWins() { 
		return sosWins;
	}
	
	/**
	 * Returns the SOS score of the team
	 * @return double sosTeam
	 */
	public double getSOSScore() {
		return sosScore;
	}
	
	/**
	 * Returns the calculated score of a team's Score stats
	 * @return double scoreScore
	 */
	public double getStatWins() {
		return statWins;
	}
	
	/**
	 * Returns the calculated score of a team's Goals stats
	 * @return double goalsScore
	 */
	public double getGoalsScore() {
		return goalsScore;
	}
	
	/**
	 * Returns the calculated score of a team's Assists stats
	 * @return double assistsScore
	 */
	public double getAssistsScore() {
		return assistsScore;
	}
	
	/**
	 * Returns the calculated score of a team's Saves stats
	 * @return double savesScore
	 */
	public double getSavesScore() {
		return savesScore;
	}
	
	/**
	 * Returns the calculated score of a team's Shots stats
	 * @return double shotsScore
	 */
	public double getShotsScore() {
		return shotsScore;
	}
	
	/**
	 * Returns the calculated score of a team's ShotPercentage stats
	 * @return double shotPercentageScore
	 */
	public double getShotPercentageScore() {
		return shotPercentageScore;
	}
	
	/**
	 * Returns the calculated score of a team's Goals Against stats
	 * @return double goalsAgainstScore
	 */
	public double getGoalsAgainstScore() {
		return goalsAgainstScore;
	}
	
	/**
	 * Returns the calculated score of a team's Offense stats
	 * This contains Goals, Assists and Shots
	 * @return double offenseScore
	 */
	public double getOffenseScore() {
		return offenseScore;
	}
	
	/**
	 * Returns the calculated score of a team's Defense stats
	 * This contains GoalsAgainst, Saves and Shot Percentage
	 * @return double defenseScore
	 */
	public double getDefenseScore() {
		return defenseScore;
	}
	
	/**
	 * Returns the calculated score of a team's stats
	 * @return double statsScore
	 */
	public double getStatsScore() {
		return statsScore;
	}
	
	/**
	 * Returns the wins predicted by momentum
	 * @return double mometumWins
	 */
	public double getMomentumWins() {
		return momWins;
	}
	
	/**
	 * Returns the wins of any team, adjusted for the various power rankings factors
	 * 
	 * @return double adjustedWins
	 */
	public double getAdjustedWins() {
		return adjWins;
	}
	
	/**
	 * Returns a percentage that rates the level of momentum
	 * @return double MomentumScore
	 */
	public double getMomentumScore() {
		return momScore;
	}
	
	/**
	 * Returns the wins so far of any team 
	 * 
	 * @return int actualWins
	 */
	public int getActualWins() {
		return totalWins;
	}
	
	/**
	 * Returns the name of the team
	 * @return String teamName
	 */
	public String getName() {
		return teamName;
	}
	
	/**
	 * Sets the name of the team
	 * @param String name
	 */
	public void setName(String name) {
		this.teamName = name;
	}
	
	/**
	 * Returns the most recent week that the team  has been updated for 
	 * @return int currentWeek
	 */
	public int getCurrentWeek() {
		return currentWeek;
	}
	
	/**
	 * Sets the current week of the team
	 * @param int currentWeek
	 */
	public void setCurrentWeek(int currentWeek) {
		this.currentWeek = currentWeek;
	}
	
	/**
	 * Returns the total shot percentage of the team
	 * @return double shotPercentage
	 */
	public double getShotPercentage() {
		return shotPercentage;
	}
	
	/**
	 * Returns the total goals of the team.
	 * @return double totalGoals
	 */
	public double getTotalGoals() {
		return totalGoals;
	}
	
	/**
	 * Returns the total assists of the team.
	 * @return double totalAssists
	 */
	public double getTotalAssists() {
		return totalAssists;
	}
	
	/**
	 * Returns the total saves of the team.
	 * @return double totalSaves
	 */
	public double getTotalSaves() {
		return totalSaves;
	}
	
	/**
	 * Returns the total shots of the team.
	 * @return double totalShots
	 */
	public double getTotalShots() {
		return totalShots;
	}
	
	/**
	 * Returns the total goals against the team
	 * @return double totalGoalsA
	 */
	public double getTotalGoalsAgainst() {
		return totalGoalsA;
	}
	
	/**
	 * Sets the highest goals of all teams in the league
	 * @param int goalsMax
	 */
	public void setGoalsMax(double goalsMax) {
		this.goalsMax = goalsMax;
	}
	
	/**
	 * Sets the highest score of all teams in the league
	 * @param int goalsMin
	 */
	public void setGoalsMin(double goalsMin) {
		this.goalsMin = goalsMin;
	}
	
	/**
	 * Sets the highest assists of all teams in the league
	 * @param int assistsMax
	 */
	public void setAssistsMax(double assistsMax) {
		this.assistsMax = assistsMax;
	}
	
	/**
	 * Sets the highest score of all teams in the league
	 * @param int assistsMin
	 */
	public void setAssistsMin(double assistsMin) {
		this.assistsMin = assistsMin;
	}
	
	/**
	 * Sets the highest saves of all teams in the league
	 * @param int savesMax
	 */
	public void setSavesMax(double savesMax) {
		this.savesMax = savesMax;
	}
	
	/**
	 * Sets the highest score of all teams in the league
	 * @param int savesMin
	 */
	public void setSavesMin(double savesMin) {
		this.savesMin = savesMin;
	}
	
	/**
	 * Sets the highest shots of all teams in the league
	 * @param int shotsMax
	 */
	public void setShotsMax(double shotsMax) {
		this.shotsMax = shotsMax;
	}
	
	/**
	 * Sets the highest score of all teams in the league
	 * @param int shotsMin
	 */
	public void setShotsMin(double shotsMin) {
		this.shotsMin = shotsMin;
	}
	
	/**
	 * Sets the highest goalsAgainst of all teams in the league
	 * @param int goalsAgainstMax
	 */
	public void setGoalsAgainstMax(double goalsAgainstMax) {
		this.goalsAgainstMax = goalsAgainstMax;
	}
	
	/**
	 * Sets the highest score of all teams in the league
	 * @param int goalsAgainstMin
	 */
	public void setGoalsAgainstMin(double goalsAgainstMin) {
		this.goalsAgainstMin = goalsAgainstMin;
	}
	
	/**
	 * Sets the highest shotPercentage of all teams in the league
	 * @param int shotPercentageMax
	 */
	public void setShotPercentageMax(double shotPercentageMax) {
		this.shotPercentageMax = shotPercentageMax;
	}
	
	/**
	 * Sets the highest score of all teams in the league
	 * @param int shotPercentageMin
	 */
	public void setShotPercentageMin(double shotPercentageMin) {
		this.shotPercentageMin = shotPercentageMin;
	}
	
	/**
	 * Sets the highest playerSalary of all players in the league
	 * @param double playerSalaryMax
	 */
	public void setPlayerSalaryMax(double playerSalaryMax) {
		this.playerSalaryMax = playerSalaryMax;
	}
	
	/**
	 * Sets the lowest playerSalary of all players in the league
	 * @param double playerSalaryMin
	 */
	public void setPlayerSalaryMin(double playerSalaryMin) {
		this.playerSalaryMin = playerSalaryMin;
	}
	
	/**
	 * Sets the lowest strength of schedule out of all teams in the league
	 * @param double sosMin
	 */
	public void setSOSMin(double sosMin) {
		this.sosMin = sosMin;
	}
	
	/**
	 * Sets the highest strength of schedule out of all teams in the league
	 * @param double sosMax
	 */
	public void setSOSMax(double sosMax) {
		this.sosMax = sosMax;
	}
	
	/**
	 * Sets the Strength of Schedule value for a team
	 * @param double sos
	 */
	public void setSOS(double sos) {
		this.sos = sos;
	}
	
	/**
	 * Adds a player object to the team
	 * @param Player player
	 */
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	/**
	 * Adds a week of results to a team
	 * @param Week week
	 */
	public void addResult(Week week) {
		results.add(week);
	}
	
	/**
	 * Adds a base result with wins to the team
	 * @param int wins
	 */
	public void addResult(int wins) {
		Week week = new Week(wins);
		results.add(week);
	}
	
	/**
	 * Adds the opponent team name for that week
	 * @param String opponentName
	 */
	public void addOpponentName(String opponentName) {
		opponentNames.add(opponentName);
	}
	
	/**
	 * Adds the opponent to the list of teams played
	 * @param Team opponent
	 */
	public void addOpponent(Team opponent) {
		opponents.add(opponent);
	}
	
	/**
	 * Formats a team into a string 
	 * @return String output
	 */
	public String toString() {
		String output = "";
		DecimalFormat df = new DecimalFormat("###.##");

		output += ("Team :: " + getName() + "\n");
		output += ("Wins :: " + getActualWins() + "\n");
		output += ("- Momentum Wins :: " + df.format(getMomentumWins())) + "\n";
		output += ("\tMomentum Score :: " + df.format(getMomentumScore()) + "%\n");
		output += ("- Stat Wins :: " + df.format(statWins)) + "\n";
		output += ("\tStats Score :: " + df.format(statsScore) + "%,");
		output += (" Offense Score :: " + df.format(offenseScore) + "%,");
		output += (" Defense Score :: " + df.format(defenseScore) + "%\n");
		output += ("\tGoals Score :: " + df.format(goalsScore) + "%,");
		output += (" Assists Score :: " + df.format(assistsScore) + "%\n");
		output += ("\tSaves Score :: " + df.format(savesScore) + "%,");
		output += (" Shots Score :: " + df.format(shotsScore) + "%\n");
		output += ("\tShot Percentage Score :: " + df.format(shotPercentageScore) + "%,");
		output += (" Goals Against Score :: " + df.format(goalsAgainstScore) + "%\n");
		output += ("- Player Wins :: " + df.format(playerWins) + "\n");
		output += ("\tPlayer Score :: " + df.format(totalPlayerScore) + "%\n");
		for(Player p:getPlayers()) {
			output += "\t" + p.getName() + ": " + df.format(p.getAdjustedSalary()) + "\n";
		}
		output += ("Strength Of Schedule Wins :: " + df.format(sosWins) + "\n");
		output += ("\t SoS Score :: " + df.format(sosScore) + "%");
		output += ("\t SoS :: " + df.format(sos) + "\n");
		output += ("- Adjusted Wins :: " + df.format(getAdjustedWins()) + "\n");
//		this.calculateAdjustedWins();
//		output += ("- Adjusted Wins Before SoS :: " + df.format(getAdjustedWins()) + "\n");
		
		return output;
	}
	
	/**
	 * Makes a comparator to sort the League ArrayList by actualWins
	 */
	public static Comparator<Team> normalComparator = new Comparator<Team>() {
		public int compare(Team t1, Team t2) {
			return(t2.getActualWins() < t1.getActualWins() ? -1 : 
				(t2.getActualWins() == t1.getActualWins() ? 0 : 1));
		}
	};
	
	/**
	 * Makes a comparator to sort the League ArrayList by adjustedWins
	 */
	public static Comparator<Team> adjustedComparator = new Comparator<Team>() {
		public int compare(Team t1, Team t2) {
			return(t2.getAdjustedWins() < t1.getAdjustedWins() ? -1 : 
				(t2.getAdjustedWins() == t1.getAdjustedWins() ? 0 : 1));
		}
	};
	
	/**
	 * Makes a comparator to sort the League ArrayList by momentumWins
	 */
	public static Comparator<Team> momentumComparator = new Comparator<Team>() {
		public int compare(Team t1, Team t2) {
			return(t2.getMomentumWins() < t1.getMomentumWins() ? -1 : 
				(t2.getMomentumWins() == t1.getMomentumWins() ? 0 : 1));
		}
	};
	
	/**
	 * Makes a comparator to sort the League ArrayList by statsWins
	 */
	public static Comparator<Team> statsComparator = new Comparator<Team>() {
		public int compare(Team t1, Team t2) {
			return(t2.getStatWins() < t1.getStatWins() ? -1 : 
				(t2.getStatWins() == t1.getStatWins() ? 0 : 1));
		}
	};
	
	/**
	 * Makes a comparator to sort the League ArrayList by name alphabetically
	 */
	public static Comparator<Team> nameComparator = new Comparator<Team>() {
		public int compare(Team t1, Team t2) {
			return (int) (t1.getName().compareTo(t2.getName()));
		}
	};
	
	/**
	 * Makes a comparator to sort the League ArrayList by PlayerScores
	 */
	public static Comparator<Team> playerComparator = new Comparator<Team>() {
		public int compare(Team t1, Team t2) {
			return(t2.getPlayerWins() < t1.getPlayerWins() ? -1 : 
				(t2.getPlayerWins() == t1.getPlayerWins() ? 0 : 1));
		}
	};
	
	/**
	 * Makes a comparator to sort the League ArrayList by Strength of Schedule
	 */
	public static Comparator<Team> scheduleComparator = new Comparator<Team>() {
		public int compare(Team t1, Team t2) {
			return(t2.getSOS() < t1.getSOS() ? -1 : 
				(t2.getSOS() == t1.getSOS() ? 0 : 1));
		}
	};
}