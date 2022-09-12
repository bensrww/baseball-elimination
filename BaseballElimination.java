/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

import java.util.Objects;

public class BaseballElimination {

    private int _numOfTeams;
    private String[] _teams;
    private int[] _wins;
    private int[] _loses;
    private int[] _left;
    private int[][] _against;
    private FlowNetwork[] _network;
    private FordFulkerson[] _fordFulkerson;

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

        for (int teamId = 0; teamId < _numOfTeams; teamId += 1) {
            // construct network without team[teamId]
            FlowNetwork flowNetwork = new FlowNetwork(
                    2 + _numOfTeams - 1 + (_numOfTeams - 1 + _numOfTeams - 2) / 2);
            // 0 is source, {1 --- (_numOfTeams - 1 + _numOfTeams - 2) / 2}(6) is team matches,
            // { (1 + cutoff) }(7) --- { (1 + cutoff) + _numOfTeams - 1 }(10) is teams
            // {1 + cutoff + _numOfTeams}(11) is target
            for (int iii = 1; iii < getTeamComboCutoff(); iii += 1) {
                flowNetwork.addEdge(new FlowEdge(0, iii, Double.POSITIVE_INFINITY));
            }

            for (int iii = 1; iii < getTeamComboCutoff(); iii += 1) {
                flowNetwork.addEdge(new FlowEdge(0, iii, Double.POSITIVE_INFINITY));
            }
        }
    }

    private int getTeamComboCutoff() {
        return (_numOfTeams - 1) * (_numOfTeams - 2) / 2;
    }

    private int getTeamIndex(String teamName) {
        for (int iii = 0; iii < _numOfTeams; iii += 1) {
            if (Objects.equals(this._teams[iii], teamName)) {
                return iii;
            }
        }
        return -1;
    }

    // number of teams
    public int numberOfTeams() {
        return _numOfTeams;
    }

    // all teams
    public Iterable<String> teams() {
        Queue<String> q = new Queue<>();
        for (String team : this._teams) {
            q.enqueue(team);
        }
        return q;
    }

    // number of wins for given team
    public int wins(String team) {
        return this._wins[getTeamIndex(team)];
    }

    // number of losses for given team
    public int losses(String team) {
        return this._loses[getTeamIndex(team)];
    }

    // number of remaining games for given team
    public int remaining(String team) {
        return this._left[getTeamIndex(team)];
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        return this._against[getTeamIndex(team1)][getTeamIndex(team2)];
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
