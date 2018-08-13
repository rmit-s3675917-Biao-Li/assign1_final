package assignment_1_final;

import java.util.ArrayList;
import java.util.Scanner;

public class main {
	public static DateTime CurrentTime = new DateTime();

	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		ArrayList<property> propertyList = new ArrayList<property>();
		String choice;
		String property_id;
		String customer_id;
		ArrayList<String> array = new ArrayList<String>();
		String stname;
		String suburb;
		String type;
		// int status;// 0=available for rent, 1=being rented, 2=under maintenance
		String stnum;
		int bednum;
		int days;
		boolean end = false;
		int i;
		int m;
		DateTime k;
		property j;
		String date;
		String[] dateSplit;

		for (i = 0; i < 8; i++)
			array.add(String.valueOf(i + 1));

		// test
		test a = new test();
		a.initialization(propertyList);

		while (!end) {
			FlexiRentSystem.printMenu();
			FlexiRentSystem.refresh(propertyList);
			choice = input.next();
			if (!array.contains(choice)) {
				System.out.println("You should enter number 1-8");
				continue;
			}
			switch (choice) {

			// add property
			case "1":
				System.out.print("Property Id:");
				property_id = input.next();
				input.nextLine();
				if (!property_id.startsWith("A_") && !property_id.startsWith("S_")) {
					System.out.println("Property Id should begin wih \"A_\" or \"S_\"");
					break;
				}

				i = 0;
				if (FlexiRentSystem.checkRepeatId(propertyList, property_id) != -1) {
					System.out.println("This property id already existed");
					break;
				} else {
					System.out.print("Property Type:");
					type = input.nextLine();
					System.out.print("Street Number:");
					stnum = input.next();
					System.out.print("Street Name:");
					stname = input.next();
					System.out.print("Suburb:");
					suburb = input.next();
					if (type.equals("Apartment")) {
						System.out.print("Number Of Bedrooms:");
						bednum = input.nextInt();
					} else
						bednum = 3;
				}

				if (type.equals("Apartment"))
					j = new apartment(property_id, type, stnum, stname, suburb, bednum);
				else {
					if (type.equals("Premium Suite")) {
						System.out.print("Last Maintenance Date:");
						date = input.next();
						dateSplit = date.split("/");
						j = new suite(property_id, type, stnum, stname, suburb, bednum);
						k = new DateTime(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]),
								Integer.parseInt(dateSplit[2]));
						j.setLastMaintenance(k);
					} else {
						System.out.println("This property is not added successfully");
						break;
					}
				}
				propertyList.add(j);
				System.out.println("Property " + property_id + " has been successfully added");
				System.out.println("______________________________________");
				break;

			// Rent property
			case "2":
				System.out.print("Enter Property Id:");
				property_id = input.next();
				m = FlexiRentSystem.checkRepeatId(propertyList, property_id);
				if (m == -1) {
					System.out.println("This property id is not existed");
					break;
				}
				System.out.print("Customer Id:");
				customer_id = input.next();
				System.out.print("Rent date(dd/mm/yyyy):");
				date = input.next();
				dateSplit = date.split("/");// to do
				k = new DateTime(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]),
						Integer.parseInt(dateSplit[2]));
				System.out.print("How many days?:");
				days = input.nextInt();
				if (!propertyList.get(m).rent(customer_id, k, days)) {
					System.out.println(
							propertyList.get(m).getType() + " " + propertyList.get(m).getId() + " could not be rented");
					break;
				} else
					System.out.println(propertyList.get(m).getType() + " " + propertyList.get(m).getId()
							+ " is now rented by customer " + customer_id);

				break;

			// test
			// k = new DateTime(2,7,2018);
			// days = 3;
			// if (propertyList.get(2).rent("aa", k, days)) System.out.println("done");
			// break;

			// return property
			case "3":
				System.out.print("Enter Property Id:");
				property_id = input.next();
				m = FlexiRentSystem.checkRepeatId(propertyList, property_id);
				if (m == -1) {
					System.out.println("This property id is not existed");
					break;
				}
				System.out.print("Return date(dd/mm/yyyy):");
				date = input.next();
				dateSplit = date.split("/");// to do
				k = new DateTime(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]),
						Integer.parseInt(dateSplit[2]));

				if (propertyList.get(m).Return(k)) {
					System.out.println(propertyList.get(m).getType() + " " + propertyList.get(m).getId()
							+ " is returned by customer " + propertyList.get(m).getRentRecords()[0].getCustomer_id());
					String s = propertyList.get(m).getReadableDetails();
					s += "RENTAL RECORD\n";
					s += propertyList.get(m).printOneRecord(propertyList.get(m).getRentRecords()[0]);
					System.out.print(s);
					break;
				} 

				// maintain property
			case "4":
				System.out.print("Enter property id: ");
				property_id = input.next();
				m = FlexiRentSystem.checkRepeatId(propertyList, property_id);
				if (m == -1) {
					System.out.println("This property id is not existed");
					break;
				} else if (propertyList.get(m).performMaintenance()) {
					System.out.println(propertyList.get(m).getType() + " " + propertyList.get(m).getId()
							+ " is now under maintenance");
				} else {
					System.out.println("This property could not be maintained. ");
				}
				break;

			// complete maintenance
			case "5":
				System.out.print("Enter property id: ");
				property_id = input.next();
				m = FlexiRentSystem.checkRepeatId(propertyList, property_id);
				if (m == -1) {
					System.out.println("This property id is not existed");
					break;
				}
				if (propertyList.get(m) instanceof suite) {
					System.out.print("Maintenance completion date (dd/mm/yyyy): ");
					date = input.next();
					dateSplit = date.split("/");// to do
					k = new DateTime(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]),
							Integer.parseInt(dateSplit[2]));
				} else
					k = new DateTime();

				if (propertyList.get(m).completeMaintenance(k)) {
					System.out.println(propertyList.get(m).getType() + " " + propertyList.get(m).getId()
							+ " has all maintenance completed and ready for rent");
				} else if (propertyList.get(m).getStatus() == 2) {
					System.out.println("This property will finish maintenance on " + k.getFormattedDate());

				} else
					System.out.println("This property is not being maintained");

				break;

			// display all properties
			case "6":
				m = 0;
				String s = "";
				if (propertyList.isEmpty()) {
					System.out.println("There is no property recorded in this system");
					break;
				}
				while (m < propertyList.size()) {
					s += propertyList.get(m).getDetails();
					System.out.println(s);
					s = "";
					System.out.println("______________________________________");
					m++;
				}
				break;

			// end
			case "7":
				end = true;
				System.out.println("System Is Closed");
				System.out.println("______________________________________");

				// test
			case "8":
				System.out.println("what date you wanna set to be the current time (dd/mm/yyyy)");
				date = input.next();
				dateSplit = date.split("/");
				k = new DateTime(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]),
						Integer.parseInt(dateSplit[2]));
				main.CurrentTime = k;
				break;
			}
		}
	}
}
