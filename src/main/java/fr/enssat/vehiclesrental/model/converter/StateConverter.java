package fr.enssat.vehiclesrental.model.converter;

import fr.enssat.vehiclesrental.model.State;
import fr.enssat.vehiclesrental.model.Vehicle;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/**
 * Convert enumeration named 'state' to switch between database and java object and vice-versa
 * see(https://www.baeldung.com/jpa-persisting-enums-in-jpa#converter)
 *
 * @see Vehicle
 */
@Converter(autoApply = true)
public class StateConverter implements AttributeConverter<State,String> {
    /**
     * Converts the value stored in the entity attribute into the
     * data representation to be stored in the database.
     *
     * @param state the entity attribute value to be converted
     * @return the converted data to be stored in the database
     * column
     */
    @Override
    public String convertToDatabaseColumn(State state) {
        if(state == null){
            return null;
        }
        return state.description;
    }

    /**
     * Converts the data stored in the database column into the
     * value to be stored in the entity attribute.
     * Note that it is the responsibility of the converter writer to
     * specify the correct <code>dbData</code> type for the corresponding
     * column for use by the JDBC driver: i.e., persistence providers are
     * not expected to do such type conversion.
     *
     * @param description the data from the database column to be
     *               converted
     * @return the converted value to be stored in the entity
     * attribute
     */
    @Override
    public State convertToEntityAttribute(String description) {
        if (description == null) {
            return null;
        }

        return Stream.of(State.values())
                .filter(c -> c.description.equals(description))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
