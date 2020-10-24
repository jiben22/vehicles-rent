package fr.enssat.vehiclesrental

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VehiclesRentalManagerApplication

fun main(args: Array<String>) {
	runApplication<VehiclesRentalManagerApplication>(*args)
}
