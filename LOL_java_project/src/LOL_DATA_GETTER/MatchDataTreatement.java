package LOL_DATA_GETTER;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author akarami
 *
 */

public class MatchDataTreatement {
	
	//Get the total game duration from the match file 
	public static long getGameDuration(JSONObject match){
		return (long)match.get("gameDuration");
	}
	
	public static long[] getChampionID(JSONObject match){
		long[] championId = new long[10];
		JSONArray participants = (JSONArray) match.get("participants");
		for (int i=0; i<participants.size(); i++){
			Object partObj = participants.get(i);
			JSONObject participant = (JSONObject) partObj;
			championId[i] = (long) participant.get("championId");
		}
		return championId;
	}
	
	//get information by subject name from match file : the participants -> stats array
	public static long[] getFromMatchParticipantStat(JSONObject match, String subjectName){
		long[] infoStat = new long[10];
		JSONArray participants = (JSONArray) match.get("participants");
		for (int i=0; i<participants.size(); i++){
			JSONObject participant = (JSONObject) participants.get(i);
			JSONObject stat = (JSONObject) participant.get("stats");
			infoStat[i] = (long) stat.get(subjectName);
		}
		return infoStat;
	}
	
	// get information by subject from match file : teams
	public static long[] getFromMatchTeams(JSONObject match, String subjectName){
		long[] infoStat = new long[10];
		JSONArray teams = (JSONArray) match.get("teams");
		for (int i=0; i<teams.size(); i++){
			JSONObject team = (JSONObject) teams.get(i);
			infoStat[i] = (long) team.get(subjectName);
		}
		return infoStat;
	}
	
	// get information by subject from match file : teams
	public static String[] getFromMatchTeamsString(JSONObject match, String subjectName){
		String[] infoStat = new String[10];
		JSONArray teams = (JSONArray) match.get("teams");
		for (int i=0; i<teams.size(); i++){
			JSONObject team = (JSONObject) teams.get(i);
			infoStat[i] = (String) team.get(subjectName);
		}
		return infoStat;
	}

	// get participant lane
	public static String[] getLane(JSONObject match){
		String[] lane = new String[10];
		JSONArray participants = (JSONArray) match.get("participants");
		for (int i=0; i<participants.size(); i++){
			JSONObject participant = (JSONObject) participants.get(i);
			JSONObject timeLine = (JSONObject) participant.get("timeline");
			lane[i] = (String) timeLine.get("lane");
		}
		return lane;
	}
}
