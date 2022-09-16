package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="Airlines")
public class Airline {
@Id
@GeneratedValue
@Column(name= "NAME")
private String name;
@Column(name= "STARTLOCATION")
private String startLocation;
@Column(name= "DESTINATION")
private String destination;

public Airline() {
	super();
}

/**
 * @return the name
 */
public String getName() {
	return name;
}

/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}

/**
 * @return the startLocation
 */
public String getStartLocation() {
	return startLocation;
}

/**
 * @param startLocation the startLocation to set
 */
public void setStartLocation(String startLocation) {
	this.startLocation = startLocation;
}

/**
 * @return the destination
 */
public String getDestination() {
	return destination;
}

/**
 * @param destination the destination to set
 */
public void setDestination(String destination) {
	this.destination = destination;
}

public Airline(String name, String startLocation, String destination) {
	super();
	this.name = name;
	this.startLocation = startLocation;
	this.destination = destination;
}

public String returnFlightDetails() {
	return this.name + ": " + this.startLocation + " to " + this.destination;
}
}
