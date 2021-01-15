package services;

import data.HealthCardID;
import exceptions.*;

public interface ScheduledVisitAgenda {

    HealthCardID getHealthCardID() throws HealthCardException;

}
