package assignment_1_final;

import java.util.ArrayList;

public class test {

public test() {
	
}
	
public void initialization(ArrayList<property> propertyList) {
	apartment i = new apartment("A_700BSMEL","Apartment","700","Bourke Steet","Melbourne",2);
	suite j = new suite("S_633WMSB","Premium Suite","633", "Whiteman Street","Southbank",3);
	DateTime t = new DateTime(5,6,2018);
	j.setLastMaintenance(t);
	apartment k = new apartment("A_668BSMEL","Apartment","701","Bourke Steet","Melbourne",2);
	propertyList.add(i);
	propertyList.add(j);
	propertyList.add(k);

}

}

