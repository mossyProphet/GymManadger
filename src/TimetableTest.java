//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TimetableTest {
    @Test
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();
        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach, DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        timetable.addNewTrainingSession(singleTrainingSession);
        TreeMap<TimeOfDay, ArrayList<TrainingSession>> mondaySessions = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        Assertions.assertEquals(1, mondaySessions.size());
        TimeOfDay mondayTime = (TimeOfDay)mondaySessions.firstKey();
        Assertions.assertEquals(13, mondayTime.getHours());
        Assertions.assertEquals(0, mondayTime.getMinutes());
        ArrayList<TrainingSession> mondayAt13 = (ArrayList)mondaySessions.get(new TimeOfDay(13, 0));
        Assertions.assertNotNull(mondayAt13);
        Assertions.assertEquals(1, mondayAt13.size());
        Assertions.assertSame(singleTrainingSession, mondayAt13.get(0));
        TreeMap<TimeOfDay, ArrayList<TrainingSession>> tuesdaySessions = timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY);
        Assertions.assertTrue(tuesdaySessions.isEmpty());
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach, DayOfWeek.THURSDAY, new TimeOfDay(20, 0));
        timetable.addNewTrainingSession(thursdayAdultTrainingSession);
        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach, DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach, DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach, DayOfWeek.SATURDAY, new TimeOfDay(10, 0));
        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);
        TreeMap<TimeOfDay, ArrayList<TrainingSession>> mondaySessions = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        Assertions.assertEquals(1, mondaySessions.size());
        TimeOfDay mondayTime = (TimeOfDay)mondaySessions.firstKey();
        Assertions.assertEquals(13, mondayTime.getHours());
        Assertions.assertEquals(0, mondayTime.getMinutes());
        ArrayList<TrainingSession> mondayAt13 = (ArrayList)mondaySessions.get(new TimeOfDay(13, 0));
        Assertions.assertEquals(1, mondayAt13.size());
        Assertions.assertSame(mondayChildTrainingSession, mondayAt13.get(0));
        TreeMap<TimeOfDay, ArrayList<TrainingSession>> thursdaySessions = timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY);
        Assertions.assertEquals(2, thursdaySessions.size());
        TimeOfDay firstThursdayTime = (TimeOfDay)thursdaySessions.firstKey();
        Assertions.assertEquals(13, firstThursdayTime.getHours());
        Assertions.assertEquals(0, firstThursdayTime.getMinutes());
        TimeOfDay lastThursdayTime = (TimeOfDay)thursdaySessions.lastKey();
        Assertions.assertEquals(20, lastThursdayTime.getHours());
        Assertions.assertEquals(0, lastThursdayTime.getMinutes());
        ArrayList<TrainingSession> thursdayAt13 = (ArrayList)thursdaySessions.get(new TimeOfDay(13, 0));
        Assertions.assertEquals(1, thursdayAt13.size());
        Assertions.assertSame(thursdayChildTrainingSession, thursdayAt13.get(0));
        ArrayList<TrainingSession> thursdayAt20 = (ArrayList)thursdaySessions.get(new TimeOfDay(20, 0));
        Assertions.assertEquals(1, thursdayAt20.size());
        Assertions.assertSame(thursdayAdultTrainingSession, thursdayAt20.get(0));
        TreeMap<TimeOfDay, ArrayList<TrainingSession>> tuesdaySessions = timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY);
        Assertions.assertTrue(tuesdaySessions.isEmpty());
    }

    @Test
    void testGetTrainingSessionsForDayAndTime() {
        Timetable timetable = new Timetable();
        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach, DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        timetable.addNewTrainingSession(singleTrainingSession);
        ArrayList<TrainingSession> mondayAt13 = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        Assertions.assertEquals(1, mondayAt13.size());
        Assertions.assertSame(singleTrainingSession, mondayAt13.get(0));
        ArrayList<TrainingSession> mondayAt14 = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(14, 0));
        Assertions.assertTrue(mondayAt14.isEmpty());
    }

    @Test
    void testAssertItAddsToBeginWith() {
        Timetable timetable = new Timetable();
        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach, DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        timetable.addNewTrainingSession(singleTrainingSession);
        HashMap<Coach, Integer> coachesAndSalaries = timetable.getCountByCoaches();
        Assertions.assertEquals(1, coachesAndSalaries.size());
    }

    @Test
    void testAssertHowManyWorkedSessionsItAdds() {
        Timetable timetable = new Timetable();
        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession mondayTrainingSession = new TrainingSession(group, coach, DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession sundayTrainingSession = new TrainingSession(group, coach, DayOfWeek.SUNDAY, new TimeOfDay(15, 0));
        timetable.addNewTrainingSession(mondayTrainingSession);
        timetable.addNewTrainingSession(sundayTrainingSession);
        HashMap<Coach, Integer> coachesAndSalaries = timetable.getCountByCoaches();
        Assertions.assertEquals(2, coachesAndSalaries.get(coach));
    }

    @Test
    void testAssertHowManyTrainersItAdds() {
        Timetable timetable = new Timetable();
        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        Coach coach2 = new Coach("Рюриков", "Иван", "Васильевич");
        TrainingSession mondayTrainingSession = new TrainingSession(group, coach, DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession sundayTrainingSession = new TrainingSession(group, coach2, DayOfWeek.SUNDAY, new TimeOfDay(15, 0));
        timetable.addNewTrainingSession(mondayTrainingSession);
        timetable.addNewTrainingSession(sundayTrainingSession);
        HashMap<Coach, Integer> coachesAndSalaries = timetable.getCountByCoaches();
        Assertions.assertEquals(2, coachesAndSalaries.size());
    }
}
