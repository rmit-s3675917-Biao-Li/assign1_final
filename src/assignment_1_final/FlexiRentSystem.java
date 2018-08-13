package assignment_1_final;

import java.util.ArrayList;

public class FlexiRentSystem {
	static void printMenu() {
		println("****** FLEXRENT SYSTEM MENU *******");
		println("Add Property:                     1");
		println("Rent Property:                    2");
		println("Return Property:                  3");
		println("Property Maintenance:             4");
		println("Complete Maintenance:             5");
		println("Display All Properties:           6");
		println("Exit Program:                     7");
		println("Pretend being several days after  8");
		System.out.print("Enter your choice:");
	}

	static int checkRepeatId(ArrayList<property> propertyList, String id){
		int i = 0;
		while (i<propertyList.size()) {
			if (propertyList.get(i).getId().equals(id)) return i;
			i++;
		}
		return -1;
	}
	
	//refresh
	static void refresh(ArrayList<property> propertyList) {
		int i = 0;
		while (i<propertyList.size()) {
			if (propertyList.get(i).getLastMaintenance().getTime() < main.CurrentTime.getTime() && propertyList.get(i).getStatus()==2 && propertyList.get(i).getWaitingMaintenance()) {
				propertyList.get(i).setStatus(0);
			}
			if (propertyList.get(i).getStatus() == 1) {
				if (propertyList.get(i).getCurrentRecord().getRentdate().getEightDigitDate().equals(main.CurrentTime.getEightDigitDate())) {
					System.out.println(propertyList.get(i).toString());
				}
			}
			i++;
		}
	}
	
	static void print(String s) {
		System.out.print(s);
	}
	
	static void println(String s) {
		System.out.println(s);
	}

}
