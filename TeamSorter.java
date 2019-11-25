import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class handles the sort functions for the Leagues, using the collections.sort method. 
 * @author oscarfilson
 */
public class TeamSorter {
	ArrayList<Team> teams = new ArrayList<>();
	
	/**
	 * Makes a new TeamSorter
	 * @param ArrayList<Team> - teams
	 */
	public TeamSorter(ArrayList<Team> teams) {
		this.teams = teams;
	}
	
	/**
	 * Sorts by the total wins a team has
	 */
	public void sortByActualWins() {
		Collections.sort(teams, Team.normalComparator);
	}
	
	/**
	 * Sorts by the adjusted wins a team has
	 */
	public void sortByAdjustedWins() {
		Collections.sort(teams, Team.adjustedComparator);
	}
	
	/**
	 * Sorts by the momentum wins a team has
	 */
	public void sortByMomentumWins() {
		Collections.sort(teams, Team.momentumComparator);
	}
	
	/**
	 * Sorts by the stats wins a team has
	 */
	public void sortByStatsWins() {
		Collections.sort(teams, Team.statsComparator);
	}
	
	/**
	 * Sorts by the the name of the team alphabetically
	 */
	public void sortByName() {
		Collections.sort(teams, Team.nameComparator);
	}
	
	/**
	 * Sorts by the player wins a team has
	 */
	public void sortByPlayerWins() {
		Collections.sort(teams, Team.playerComparator);
	}
	
	/**
	 * Sorts by the Strength of Schedule wins a team has
	 */
	public void sortBySoSWins() {
		Collections.sort(teams, Team.scheduleComparator);
	}
}