package netscript.pojo;

public class Student {
	
int stud_id;
String stud_name,stud_city;

//getter and setter for Student table
public int getId() {
	return stud_id;
}
public void setId(int id) {
	this.stud_id = id;
}
public String getStud_name() {
	return stud_name;
}
public void setStud_name(String stud_name) {
	this.stud_name = stud_name;
}
public String getStud_city() {
	return stud_city;
}
public void setStud_city(String stud_city) {
	this.stud_city = stud_city;
}

}
