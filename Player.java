/**
 * The player class oversees the Player object, which is used in part to rank a team's strength and hold stats
 * The player holds stats and ranked mmr in its calculations
 * @author oscarfilson
 */
public class Player {
	
	private String name;
	private double playerScore;
	private double salary;
	private int goals;
	private int assists;
	private int saves;
	private int shots;
	private int gamesPlayed;
	private int wins;
	private double adjustedSalary;
	
	/**
	 * Makes a new Player object
	 * @param String - the name of the player
	 */
	public Player(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the name of the player
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	public void setPlayerScore(double playerScore) {
		this.playerScore = playerScore;
	}
	
	public double getPlayerScore() {
		return playerScore;
	}
	
	/**
	 * Returns the salary of the player
	 * @return salary
	 */
	public double getSalary() {
		return salary;
	}
	
	/**
	 * Sets the salary of the player, which is based of ranked MMR
	 * @param salary
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	/**
	 * Returns the total goals of the player
	 * @return goals
	 */
	public int getGoals() {
		return goals;
	}
	
	/**
	 * Adds to the total goals of the player
	 * @param goals
	 */
	public void addGoals(int goals) {
		this.goals += goals;
	}
	
	/**
	 * Returns the assists of the player
	 * @return assists
	 */
	public int getAssists() {
		return assists;
	}
	
	/**
	 * Adds to the total assists of the player
	 * @param assists
	 */
	public void addAssists(int assists) {
		this.assists += assists;
	}
	
	/**
	 * Returns the saves of the player
	 * @return saves
	 */
	public int getSaves() {
		return saves;
	}

	/**
	 * Adds to the total saves of the player
	 * @param saves
	 */
	public void addSaves(int saves) {
		this.saves += saves;
	}
	
	/**
	 * Returns the shots of the player
	 * @return shots
	 */
	public int getShots() {
		return shots;
	}
	
	/**
	 * Adds to the total shots of the player
	 * @param shots
	 */
	public void addShots(int shots) {
		this.shots += shots;
	}
	
	/**
	 * Returns the amount of games the player has played
	 * @return gamesPlayed
	 */
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	
	/**
	 * Adds to the 5 games to the player's gamesPlayed
	 */
	public void addGamesPlayed() {
		gamesPlayed += 5;
	}
	
	/**
	 * Returns the amount of games the player has won
	 * @return wins
	 */
	public int getWins() {
		return wins;
	}
	
	/**
	 * Adds wins to the player's totalWins
	 * @param wins
	 */
	public void addWins(int wins) {
		this.wins += wins;
	}
	
	/**
	 * Sets the adjusted salary of the player, which is based off MVPR and win percentages. 
	 * @param adjustedSalary
	 */
	public void setAdjustedSalary(double adjustedSalary) {
		this.adjustedSalary = adjustedSalary;
	}
	
	/**
	 * Returns the adjusted salary of the player, which is based off MVPR and win percentages. 
	 * @return adjustedSalary
	 */
	public double getAdjustedSalary() {
		return adjustedSalary;
	}
	
	/**
	 * Calculates the adjusted salary of a player.
	 * @param int teamWins, the amount of wins the team has had as a whole.
	 */
	public void calculateAdjustedSalary(int teamWins) {
		double adjSalary = 0.0;
		double MVPR = 0.0;
		//double shotPercentage = 0.0;
		double winPercentage = 0.0;
		double teamWinPercentage = 0.0;
		
		MVPR = (((double) goals) + ((double) assists*.75) + ((double) saves*.6) + ((double) shots/3))/((double) gamesPlayed);
		//shotPercentage = ((double) goals)/((double) shots);
		winPercentage = ((double) wins)/ ((double) gamesPlayed);
		teamWinPercentage = ((double) wins)/ ((double) teamWins);
		adjSalary = (double) (salary*teamWinPercentage*winPercentage) + MVPR;
		
		this.setAdjustedSalary(adjSalary);
	}
 }