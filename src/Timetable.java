import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class Timetable {


    private HashMap<DayOfWeek, TreeMap<TimeOfDay, ArrayList<TrainingSession>>> timetable = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        DayOfWeek day = trainingSession.getDayOfWeek();
        TimeOfDay time = trainingSession.getTimeOfDay();


        TreeMap<TimeOfDay, ArrayList<TrainingSession>> daySessions = timetable.get(day);


        if (daySessions == null) {
            daySessions = new TreeMap<>();
            timetable.put(day, daySessions);
        }


        ArrayList<TrainingSession> timeSessions = daySessions.get(time);


        if (timeSessions == null) {
            timeSessions = new ArrayList<>();
            daySessions.put(time, timeSessions);
        }


        timeSessions.add(trainingSession);
    }

    public TreeMap<TimeOfDay, ArrayList<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {

        TreeMap<TimeOfDay, ArrayList<TrainingSession>> daySessions = timetable.get(dayOfWeek);
        if (daySessions == null) {
            return new TreeMap<>();
        }
        return new TreeMap<>(daySessions);
    }

    public ArrayList<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        TreeMap<TimeOfDay, ArrayList<TrainingSession>> daySessions = timetable.get(dayOfWeek);

        if (daySessions == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(
                daySessions.getOrDefault(timeOfDay, new ArrayList<>())
        );
    }

    public HashMap<Coach, Integer> getCountByCoaches() {
        HashMap<Coach, Integer> coachesAndSalaries = new HashMap<>();
        for (TreeMap<TimeOfDay, ArrayList<TrainingSession>> iterDay : timetable.values()){
            for (ArrayList<TrainingSession> sessions : iterDay.values()) {
                for (TrainingSession session : sessions) {
                    Coach coach = session.getCoach();
                    coachesAndSalaries.put(coach, coachesAndSalaries.getOrDefault(coach, 0) + 1);
                }
            }
        }
        ArrayList<HashMap.Entry<Coach, Integer>> entries = new ArrayList<>(coachesAndSalaries.entrySet());
        entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        HashMap<Coach, Integer> res = new HashMap<>();
        for (HashMap.Entry<Coach, Integer> iter : entries){
            res.put(iter.getKey(), iter.getValue());
        }
        return res;
    }
}