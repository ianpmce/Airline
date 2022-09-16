import java.util.List;
import java.util.Scanner;

import controller.AirlineHelper;
import model.Airline;

public class StartProgram {
	
	static Scanner in = new Scanner(System.in);
	static AirlineHelper arh = new AirlineHelper();

	private static void addAFlight() {
		// TODO Auto-generated method stub
		System.out.print("Enter a Airline name: ");
		String name = in.nextLine();
		System.out.print("Enter a start location: ");
		String startLocation = in.nextLine();
		System.out.print("Enter a destination: ");
		String destination = in.nextLine();
		Airline toAdd = new Airline(name,startLocation,destination);
		arh.insertFlight(toAdd);

	}

	private static void deleteAFlight() {
		// TODO Auto-generated method stub
		System.out.print("Enter the Airline to delete: ");
		String name = in.nextLine();
		System.out.print("Enter the start location to delete: ");
		String  startLocation = in.nextLine();
		System.out.print("Enter the destination to delete: ");
		String  destination = in.nextLine();
		Airline toDelete = new Airline(name, startLocation, destination);
		arh.deleteFlight(toDelete);

	}

	private static void editAFlight() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Airline");
		System.out.println("2 : Search by Start Location");
		System.out.println("3 : Search by Destination");
		int searchBy = in.nextInt();
		in.nextLine();
		List<Airline> foundFlights;
		if (searchBy == 1) {
			System.out.print("Enter the Airline name: ");
			String flightName = in.nextLine();
			foundFlights = arh.searchForStartLocationByAirlineNames(flightName);
			
		} else if (searchBy == 2) {
			System.out.print("Enter the start location: ");
			String startName = in.nextLine();
			foundFlights = arh.searchForAirlineByStartLocation(startName);
		}
		else {
			System.out.print("Enter the destination: ");
			String destination = in.nextLine();
			foundFlights = arh.searchForStartLocationByDestination(destination);
		}

		if (!foundFlights.isEmpty()) {
			System.out.println("Found Results.");
			for (Airline l : foundFlights) {
				System.out.println(l.returnFlightDetails());
			}
			System.out.print("Which flight to edit: ");
			String flightToEdit = in.nextLine();

			Airline toEdit =  arh.searchForStartLocationByAirlineName(flightToEdit);
			System.out.println("Retrieved " + toEdit.getStartLocation() + " from " + toEdit.getDestination());
			System.out.println("1 : Update Start Location");
			System.out.println("2 : Update Destination");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Start Location: ");
				String newStartLocation = in.nextLine();
				toEdit.setStartLocation(newStartLocation);
			} else if (update == 2) {
				System.out.print("New Destination: ");
				String newDestination = in.nextLine();
				toEdit.setDestination(newDestination);
			}

			arh.updateStartLocation(toEdit);

		} else {
			System.out.println("---- No results found");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();

	}

	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to the Airport! ---");
		while (goAgain) {
			System.out.println("*  Select a flight:");
			System.out.println("*  1 -- Add a flight");
			System.out.println("*  2 -- Edit a flight");
			System.out.println("*  3 -- Delete a flight");
			System.out.println("*  4 -- View flights");
			System.out.println("*  5 -- Exit the airport terminal");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addAFlight();
			} else if (selection == 2) {
				editAFlight();
			} else if (selection == 3) {
				deleteAFlight();
			} else if (selection == 4) {
				viewTheList();
			} else {
				arh.cleanUp();
				System.out.println("   See you next time!   ");
				goAgain = false;
			}

		}

	}

	private static void viewTheList() {
		// TODO Auto-generated method stub
		List<Airline>allFlights = arh.showAllFlights();
		for(Airline singleFlight : allFlights) {
			System.out.println(singleFlight.returnFlightDetails());
		}
		

	}


}
