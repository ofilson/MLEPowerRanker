/**
 * The team class oversees the Week object, which is how the results of each week are entered and connected to a team. 
 * It tracks stats, opponents and wins
 * @author oscarfilson
 */

public class Week {
	private int wins;
	private int goals;
	private int assists;
	private int saves;
	private int shots;
	private int goalsAgainst;
	/**
	 * Makes a new Week object - with just wins so far added
	 * @param int wins - the wins they got in that week. 
	 */
	public Week(int wins) {
		this.wins = wins;
		this.goals = 0;
		this.assists = 0;
		this.saves = 0;
		this.shots = 0;
		this.goalsAgainst = 0;
	}
	
	/**
	 * Makes a new Week object - with all the stats added 
	 * @param int wins - the wins they got in that week. 
	 * @param int goals- the goals they got in that week
	 * @param int assists- the assists they got in that week
	 * @param int saves- the saves they got in that week
	 * @param int shots- the shots they got in that week
	 * @param int goalsAgainst- the goalsAgainst they got in that week
	 */
	public Week(int wins, int goals, int assists, int saves, int shots, int goalsAgainst) {
		this.wins = wins;
		this.goals = goals;
		this.assists = assists;
		this.saves = saves;
		this.shots = shots;
		this.goalsAgainst = goalsAgainst;
	}
	
	/**
	 * Returns the wins of that week
	 * @return int wins
	 */
	public int getWins() {
		return wins;
	}
	
	/**
	 * Sets the wins they got in the week
	 * @param int wins - the amount of wins
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}
	
	/**
	 * Returns the goals of that week
	 * @return int goals
	 */
	public int getGoals() {
		return goals;
	}
	
	/**
	 * Sets the goals they got in the week
	 * @param int goals - the amount of goals
	 */
	public void setGoals(int goals) {
		this.goals = goals;
	}
	
	/**
	 * Returns the assists of that week
	 * @return int assists
	 */
	public int getAssists() {
		return assists;
	}
	
	/**
	 * Sets the assists they got in the week
	 * @param int assists - the amount of assists
	 */
	public void setAssists(int assists) {
		this.assists = assists;
	}
	
	/**
	 * Returns the saves of that week
	 * @return int saves
	 */
	public int getSaves() {
		return saves;
	}
	
	/**
	 * Sets the saves they got in the week
	 * @param int saves - the amount of saves
	 */
	public void setSaves(int saves) {
		this.saves = saves;
	}
	
	/**
	 * Returns the shots of that week
	 * @return int shots
	 */
	public int getShots() {
		return shots;
	}
	
	/**
	 * Sets the shots they got in the week
	 * @param int shots - the amount of shots
	 */
	public void setShots(int shots) {
		this.shots = shots;
	}
	
	/**
	 * Returns the goals against of that week
	 * @return int goalsAgainst
	 */
	public int getGoalsAgainst() {
		return goalsAgainst;
	}
	
	/**
	 * Sets the goals against they got in the week
	 * @param int goalsAgaisnt - the amount of goalsAgainst
	 */
	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	/**
	 * Formats the information of any given week
	 * @return String output - formatted info
	 */
	public String toString() {
		String output = "";
		
		output += "Wins :: " + wins;
		output += ", Goals :: " + goals; 
		output += ", Assists :: " + assists;
		output += ", Saves :: " + saves;
		output += ", Shots :: " + shots;
		output += ", Goals Against :: " + goalsAgainst + "."; 
		
		return output;
	}
}