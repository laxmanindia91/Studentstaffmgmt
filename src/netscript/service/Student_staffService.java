package netscript.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import netscript.pojo.Student_staff;

@Path("/studstaff")
public class Student_staffService {
	Poolcon pcon=new Poolcon();
	
	@GET
	@Path("/studentbystaff")
	@Produces({MediaType.APPLICATION_JSON})
	public String getStudentByStaffId(@QueryParam("staffId") int id) throws SQLException, NamingException, JsonGenerationException, JsonMappingException, IOException {
	
		Connection con=pcon.getDbConnection();
		List<Student_staff> stdstaflist=new ArrayList<Student_staff>();
		String query="SELECT * FROM staff inner join stud_staff, students where staff.staf_id = stud_staff.staff_id and students.stud_id = stud_staff.student_id and staff.staf_id ="+id;
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(query);
		
		while(rs.next())
		{
		Student_staff stdstaf=new Student_staff();
		stdstaf.setStudent_id(rs.getInt(4));
		stdstaf.setStaff_id(rs.getInt(5));
		stdstaf.setStud_name(rs.getString(7));
		stdstaf.setStud_city(rs.getString(8));
		stdstaflist.add(stdstaf);
			
		}
		
		ObjectMapper mapper=new ObjectMapper();
		 return mapper.writeValueAsString(stdstaflist);
	}
}
