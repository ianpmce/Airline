package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Airline;





public class AirlineHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Airline");
	
	public void insertFlight(Airline Ai){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(Ai);
		em.getTransaction().commit();
		em.close();	
	}	

	public List<Airline> showAllFlights(){
		EntityManager em = emfactory.createEntityManager();
		List<Airline>allItems = em.createQuery("SELECT i FROM Airline i").getResultList();
		return allItems;
		
	}
	
	public void deleteFlight(Airline toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Airline>typedQuery = em.createQuery("select Ai from Airline Ai where Ai.name = :selectedAirline to Ai.startLocation =  :selectedstartLocation, Ai.destination = :selectedDestination", Airline.class);
		
		typedQuery.setParameter("selectedAirline", toDelete.getName());
		typedQuery.setParameter("selectedStartLocation", toDelete.getStartLocation());
		typedQuery.setParameter("selectedDestination", toDelete.getDestination());
		
		typedQuery.setMaxResults(1);
		
		Airline result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Airline> searchForStartLocationByAirlineNames(String airlineName){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Airline>typedQuery = em.createQuery("select Ai from Airline Ai where Ai.name = :selectedName", Airline.class);
		typedQuery.setParameter("selectedName", airlineName);
		
		List <Airline> foundStartLocations = typedQuery.getResultList();
		em.close();
		return foundStartLocations;
	}
	
	public Airline searchForStartLocationByAirlineName(String airlineName){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Airline>typedQuery = em.createQuery("select Ai from Airline Ai where Ai.name = :selectedName", Airline.class);
		typedQuery.setParameter("selectedName", airlineName);
		
		Airline foundStart = em.find(Airline.class, airlineName);
		em.close();
		return foundStart;
	}
	
	
	public List<Airline>searchForStartLocationByDestination(String destinationName){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Airline>typedQuery = em.createQuery("select Ai from destination Ai where Ai.destination = :selectedDestination", Airline.class);
		typedQuery.setParameter("selectedDestination", destinationName);
		
		List<Airline>foundStartLocations = typedQuery.getResultList();
		em.close();
		return foundStartLocations;
	}
	
	public List<Airline>searchForAirlineByStartLocation(String startLocationName){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Airline>typedQuery = em.createQuery("select Ai from startLocation Ai where Ai.startLocation = :selectedStartLocation", Airline.class);
		typedQuery.setParameter("selectedStartLocation", startLocationName);
		
		List<Airline>foundAirlines = typedQuery.getResultList();
		em.close();
		return foundAirlines;
	}
	
	
	public void updateStartLocation(Airline toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	
	public void cleanUp() {
		emfactory.close();
	}
	
	
	
	

}
