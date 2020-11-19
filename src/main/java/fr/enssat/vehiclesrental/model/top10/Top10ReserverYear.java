package fr.enssat.vehiclesrental.model.top10;

import fr.enssat.vehiclesrental.model.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Immutable
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Subselect("SELECT client.* FROM booking inner join client on booking.id_client = client.id where year(start_date) = year(now()) group by client.id order by count(*) desc limit 10")
@Synchronize({"booking","client"})
public class Top10ReserverYear extends Person implements Serializable {
}