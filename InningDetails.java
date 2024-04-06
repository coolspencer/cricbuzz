import java.util.ArrayList;
import java.util.List;

public class InningDetails {
    Team battingTeam;
    Team bowlingTeam;
    MatchType matchType;
    List<OverDetails> overs;

    public InningDetails(Team battingTeam, Team bowlingTeam, MatchType matchType) {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.matchType = matchType;
        overs = new ArrayList<>();
    }

    public void start(int runsToWin) {

        // set batting players
        try {
            battingTeam.chooseNextBatsMan();
        } catch (Exception e) {
            // System.out.println(e.printStackTrace());

        }
        int noOfOvers = matchType.noOfOvers();
        for (int overNumber = 1; overNumber <= noOfOvers; overNumber++) {

            // chooseBowler
            System.out.println(overNumber);
            bowlingTeam.chooseNextBowler(matchType.maxOverCountBowlers());

            OverDetails over = new OverDetails(overNumber, bowlingTeam.getCurrentBowler());
            overs.add(over);
            try {
                boolean won = over.startOver(battingTeam, bowlingTeam, runsToWin);
                if (won == true) {
                    break;
                }
            } catch (Exception e) {
                break;
            }

        }
    }

    public int getTotalRuns() {
        return battingTeam.getTotalRuns();
    }
}
