/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;

public class BaseballElimination {

    private int _numOfTeams;
    private String[] _teams;
    private int[] _wins;
    private int[] _loses;
    private int[] _left;
    private int[][] _against;

    // create a baseball division from given filename in format specified below
    public BaseballElimination(
            String filename) {
        In in = new In(filename);
        _numOfTeams = in.readInt();

        this._teams = new String[_numOfTeams];
        this._wins = new int[_numOfTeams];
        this._loses = new int[_numOfTeams];
        this._left = new int[_numOfTeams];
        this._against = new int[_numOfTeams][_numOfTeams];

        for (int teamId = 0; teamId < _numOfTeams; teamId += 1) {
            _teams[teamId] = in.readString();
            _wins[teamId] = in.readInt();
            _loses[teamId] = in.readInt();
            _left[teamId] = in.readInt();
            for (int iii = 0; iii < _numOfTeams; iii += 1) {
                _against[teamId][iii] = in.readInt();
            }
        }
    }

    // number of teams
    public int numberOfTeams() {
        return _numOfTeams;
    }

    // all teams
    public Iterable<String> teams() {
        return null;
    }

    // number of wins for given team
    public int wins(String team) {
        return 0;
    }

    // number of losses for given team
    public int losses(String team) {
        return 0;
    }

    // number of remaining games for given team
    public int remaining(String team) {
        return 0;
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        return 0;
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        return false;
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        return null;
    }

    public static void main(String[] args) {
        BaseballElimination be = new BaseballElimination(args[0]);
        System.out.println(be.numberOfTeams());
    }
}
