package fr.enssat.vehiclesrental.repository.top10;

import fr.enssat.vehiclesrental.model.top10.Top10SpenderMonth;
import fr.enssat.vehiclesrental.model.top10.Top10SpenderWeek;
import fr.enssat.vehiclesrental.repository.Top10Repository;
import org.springframework.stereotype.Repository;

@Repository
public interface Top10SpenderWeekRepository extends Top10Repository<Top10SpenderWeek,Long> {

}
