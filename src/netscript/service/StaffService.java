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

import netscript.pojo.Staff;

@Path("/staff")
public class StaffService {
	Poolcon pcon=new Poolcon();

	@GET
	@Path("/allstaff")
	@Produces({MediaType.APPLICATION_JSON})
	public String getAllStaff() throws NamingException, SQLException, JsonGenerationException, JsonMappingException, IOException{
		
		List<Staff> stafflist=new ArrayList<Staff>();
		Connection con=pcon.getDbConnection();
		String query="select * from staff";
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(query);
		
		while(rs.next())
		{
			Staff staff=new Staff();
			staff.setStaf_id(rs.getInt(1));
			staff.setStaf_name(rs.getString(2));
			stafflist.add(staff);
		}
		
		ObjectMapper mapper=new ObjectMapper();
		 return mapper.writeValueAsString(stafflist);
		
	}
	

}
