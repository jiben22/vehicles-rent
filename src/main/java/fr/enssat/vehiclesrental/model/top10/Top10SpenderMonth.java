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
@Subselect("SELECT client.* FROM booking inner join client on booking.id_client = client.id where month(start_date) = month(now()) group by client.id order by sum(booking.expected_price) desc limit 10")
@Synchronize({"booking","client"})
public class Top10SpenderMonth extends Person implements Serializable {

}

