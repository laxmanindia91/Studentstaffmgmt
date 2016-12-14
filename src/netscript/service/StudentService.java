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
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import netscript.pojo.Student;
@Path("/student")
public class StudentService {
	Poolcon pc=new Poolcon();
	
	@GET
	@Path("/allstudent")
	@Produces({MediaType.APPLICATION_JSON}) 
	public String getAllStudent() throws NamingException, SQLException, JsonGenerationException, JsonMappingException, IOException{
		
	List <Student> stulist=new ArrayList<Student>();	
	Connection con=pc.getDbConnection();
	String query="Select * from students";
	Statement statement=con.createStatement();
	ResultSet rs=statement.executeQuery(query);
	
	
	while(rs.next())
	{
		Student student=new Student();
		student.setId(rs.getInt(1));
		student.setStud_name(rs.getString(2));
		student.setStud_city(rs.getString(3));
		stulist.add(student);
	}

		//Gson gson = new Gson();
		//return gson.toJson(stulist);
	 ObjectMapper mapper=new ObjectMapper();
	 return mapper.writeValueAsString(stulist);
	 
	

	}

}
