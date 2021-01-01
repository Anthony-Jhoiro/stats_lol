package LOL_DATA_GETTER;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Set;

public class MatchData {
    private JSONObject matchData;
    private JSONArray participantsData;

    public MatchData(String filePath) throws Exception {
        this.matchData = ReadWriteFiles.readJsonFromFile(filePath);
        this.participantsData = (JSONArray) this.matchData.get("participants");
    }

    public long[] getFromMatchParticipantStat(String field) {
        long[] infoStat = new long[10];
        for (int i = 0; i < this.participantsData.size(); i++) {
            JSONObject participant = (JSONObject) this.participantsData.get(i);
            JSONObject stat = (JSONObject) participant.get("stats");
            infoStat[i] = (long) stat.get(field);
        }
        return infoStat;
    }

    public long[] getFromMatchParticipantStatBoolean(String field) {
        long[] infoStat = new long[10];
        for (int i = 0; i < this.participantsData.size(); i++) {
            JSONObject participant = (JSONObject) this.participantsData.get(i);
            JSONObject stat = (JSONObject) participant.get("stats");
            Object a = stat.get(field);

            if (a == null) {
                infoStat[i] = 0L;
            } else {
                boolean hasWin = (boolean) stat.get(field);
                infoStat[i] = hasWin ? 1L : 0L;
            }

        }
        return infoStat;
    }

    public long[] getMatchDurationForAllPayers() {
        long matchDuration = (long) this.matchData.get("gameDuration");

        long[] usrDuration = new long[10];
        for (int i = 0;i < 10; i++) {
            usrDuration[i] = matchDuration;
        }
        return usrDuration;
    }

    public long[] getFromMatchParticipant(String field) {
        long[] championIds = new long[10];
        for (int i = 0; i < this.participantsData.size(); i++) {
            Object partObj = this.participantsData.get(i);
            JSONObject participant = (JSONObject) partObj;
            championIds[i] = (long) participant.get(field);
        }
        return championIds;
    }



    public double[] getMatchPayersXp() {
        double[] xps = new double[10];

        for (int i = 0; i < this.participantsData.size(); i++) {
            JSONObject participant = (JSONObject) this.participantsData.get(i);
            JSONObject timelines = (JSONObject) participant.get("timeline");
            JSONObject xpDeltas = (JSONObject) timelines.get("xpPerMinDeltas");
            if (xpDeltas == null) {
                xps[i] = 0;
                continue;
            }
            double xp = 0L;
            Set<String> ds = xpDeltas.keySet();
            String[] deltas = new String[ds.size()];
            ds.toArray(deltas);
            for (int j = 0; j < deltas.length; j++) {
                Object ret = xpDeltas.get(deltas[j]);
                if (ret instanceof Long) {
                    xp +=((Long) ret).doubleValue();
                } else {
                    xp += (Double) ret;
                }
            }
            xps[i] = xp;
        }
        return xps;
    }

    public JSONObject getMatchData() {
        return matchData;
    }
}
