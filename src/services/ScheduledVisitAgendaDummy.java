package services;

import data.HealthCardID;
import exceptions.HealthCardException;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ScheduledVisitAgendaDummy implements ScheduledVisitAgenda{
    private HashMap<Date, HealthCardID> sva = new HashMap<>();
    private final Date today = new Date();

    public ScheduledVisitAgendaDummy() throws Exception{
        sva.put(new Date(99, 11, 27), new HealthCardID("314679172839"));
        sva.put(new Date(103, 7, 2), new HealthCardID("2154876963652"));
        sva.put(new Date(120, 4, 24), new HealthCardID("876954321535"));
    }

    @Override
    public HealthCardID getHealthCardID() throws HealthCardException {
        if (!sva.containsKey(new Date(today.getYear(), today.getMonth(), today.getDay()))) throw new HealthCardException("");
        return sva.get(new Date(today.getYear(), today.getMonth(), today.getDay(), today.getHours(), today.getMinutes())); // we need hours and minutes to specify the visit. If not, we can't assure the unique key value in the hashmap.
    }
}
