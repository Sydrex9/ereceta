package services;

import data.HealthCardID;
import exceptions.HealthCardException;
import java.util.Date;
import java.util.HashMap;

public class ScheduledVisitAgendaDummy implements ScheduledVisitAgenda{
    private HashMap<Date, HealthCardID> sva = new HashMap<>();
    private final Date today = new Date();

    public ScheduledVisitAgendaDummy() throws Exception{
        // falta la inicialització de les diferents classes a utilitzar en els testos!!!!
    }

    @Override
    public HealthCardID getHealthCardID() throws HealthCardException {
        if (!sva.containsKey(new Date(today.getYear(), today.getMonth(), today.getDay()))) throw new HealthCardException("");
        return sva.get(new Date(today.getYear(), today.getMonth(), today.getDay(), today.getHours(), today.getMinutes())); // we need hours and minutes to specify the visit. If not, we can't assure the unique key value in the hashmap.
    }

}
