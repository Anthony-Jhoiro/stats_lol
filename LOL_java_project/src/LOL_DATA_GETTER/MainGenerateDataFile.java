package LOL_DATA_GETTER;

import java.io.File;

import java.util.ArrayList;

import org.json.simple.JSONObject;

/**
 * @author akarami
 * @author anthony
 */

public class MainGenerateDataFile {

    public static final String MATCHES_FILES_DIRECTORY = "files/matches/";
    public static final String OUTPUT_DATA_DIRECTORY = "files/data/";
    public static final String DESTINATION = OUTPUT_DATA_DIRECTORY + "player_lol_data.CSV";
    public static final int MATCH_PLAYER_COUNT = 10;

    private static final String[] FIELDS = {
            "xp",
            "totalDamageDealt",
            "goldEarned",
            "totalMinionsKilled",
            "championId",
            "assists",
            "inhibitorKills",
            "firstBloodKill",
            "doubleKills",
            "tripleKills",
            "quadraKills",
            "pentaKills",
            "win",
            "matchDuration"
    };
    public static void main(String[] args) throws Exception {

        //get the list of matches ids from the files name in the matches info directory
        ReadWriteFiles.clearFile(DESTINATION);
        File matchesFolder = new File(MATCHES_FILES_DIRECTORY);
        ArrayList<String> listMatchesID = new ArrayList<String>();
        for (File fileEntry : matchesFolder.listFiles()) {
            String[] parts = fileEntry.getName().split("_");
            if (parts[1].startsWith("match"))
                listMatchesID.add(parts[0]);
        }

        System.out.println("Total number of matches " + listMatchesID.size());
        String output = String.join(",", FIELDS) + ",\n";
        ReadWriteFiles.writeLineToFile(output, DESTINATION);
        int cpt = 0;
        //Treate all matches
        for (String matchId : listMatchesID) {

//			System.out.println("Processing : ");
//			JSONObject matchData = ReadWriteFiles.readJsonFromFile(MATCHES_FILES_DIRECTORY + "" + matchId + "_match.JSON");
//			JSONObject matchTimelines = ReadWriteFiles.readJsonFromFile(MATCHES_FILES_DIRECTORY + "" + matchId + "_timelines.JSON");
            System.out.println(cpt + " match id = " + matchId + " treated");
            cpt++;

            MatchData matchData = new MatchData(MATCHES_FILES_DIRECTORY + "" + matchId + "_match.JSON");

            double[] xps = matchData.getMatchPayersXp();
            long[] totalDamageDealt = matchData.getFromMatchParticipantStat("totalDamageDealt");
            long[] goldEarned = matchData.getFromMatchParticipantStat("goldEarned");
            long[] totalMinionsKilled = matchData.getFromMatchParticipantStat("totalMinionsKilled");
            long[] championIds = matchData.getFromMatchParticipant("championId");
            long[] assists = matchData.getFromMatchParticipantStat("assists");
            long[] inhibitorKills = matchData.getFromMatchParticipantStat("inhibitorKills");
            long[] firstBloodKill = matchData.getFromMatchParticipantStatBoolean("firstBloodKill");
            long[] doubleKills  = matchData.getFromMatchParticipantStat("doubleKills");
            long[] tripleKills = matchData.getFromMatchParticipantStat("tripleKills");
            long[] quadraKills = matchData.getFromMatchParticipantStat("quadraKills");
            long[] pentaKills = matchData.getFromMatchParticipantStat("pentaKills");
            long[] win = matchData.getFromMatchParticipantStatBoolean("win");
            long[] matchDuration = matchData.getMatchDurationForAllPayers();

            long[][] toLoad = {
                    totalDamageDealt,
                    goldEarned,
                    totalMinionsKilled,
                    championIds, assists,
                    inhibitorKills,
                    firstBloodKill,
                    doubleKills,
                    tripleKills,
                    quadraKills,
                    pentaKills,
                    win,
                    matchDuration
            };

            StringBuilder lines = new StringBuilder();
            for (int i = 0; i < MATCH_PLAYER_COUNT; i++) {
                lines.append(xps[i]).append(",");
                for (long[] field : toLoad) {
                    lines.append(field[i]).append(",");
                }
                lines.append("\n");
            }

            ReadWriteFiles.writeLineToFile(lines.toString(), DESTINATION);

//			//get data from match file
//			long duration = MatchDataTreatement.getGameDuration(matchData);
//
//			//Participant related information
//			long[] championID = MatchDataTreatement.getChampionID(matchData);
//			long[] championLevel = MatchDataTreatement.getFromMatchParticipantStat(matchData, "champLevel");
//			long[] kills = MatchDataTreatement.getFromMatchParticipantStat(matchData, "kills");
//			long[] deaths = MatchDataTreatement.getFromMatchParticipantStat(matchData, "deaths");
//			long[] assists = MatchDataTreatement.getFromMatchParticipantStat(matchData, "assists");
//			long[] minionskill = MatchDataTreatement.getFromMatchParticipantStat(matchData, "totalMinionsKilled");
//			long[] gold = MatchDataTreatement.getFromMatchParticipantStat(matchData, "goldEarned");
//			String[] lane = MatchDataTreatement.getLane(matchData);
//
//
//			//team related information
//			long[] inhibitorKills = MatchDataTreatement.getFromMatchTeams(matchData, "inhibitorKills");
//			long[] dragonKills = MatchDataTreatement.getFromMatchTeams(matchData, "dragonKills");
//			long[] baronKills = MatchDataTreatement.getFromMatchTeams(matchData, "baronKills");
//			long[] towerKills = MatchDataTreatement.getFromMatchTeams(matchData, "towerKills");
//			String[] win = MatchDataTreatement.getFromMatchTeamsString(matchData, "win");
//
//
//			output += duration + ", ";
//			//regroup information about first 5 players
//			for (int p=0; p<5; p++) {
//				output += championID[p] + ", " + championLevel[p] + ", " + kills[p] + ", " + deaths[p] + ", "
//						+ assists[p] + ", " +minionskill[p] + ", " + gold[p] + ", " + lane[p] + ", ";
//			}
//			//regroup information about the first team
//			output += inhibitorKills[0] + ", " + dragonKills[0] + ", " + baronKills[0] + ", " + towerKills[0] + ", "
//						+ win[0] + ", ";
//
//			//regroup information about players of the second team
//			for (int p=5; p<10; p++) {
//				output += championID[p] + ", " + championLevel[p] + ", " + kills[p] + ", " + deaths[p] + ", "
//						+ assists[p] + ", " +minionskill[p] + ", " + gold[p] + ", " + lane[p] + ", ";
//			}
//			//regroup information about the second team
//			output += inhibitorKills[1] + ", " + dragonKills[1] + ", " + baronKills[1] + ", " + towerKills[1] + ", "
//						+ win[1] + "\n";
//			//print data line into file
        }
    }
}


