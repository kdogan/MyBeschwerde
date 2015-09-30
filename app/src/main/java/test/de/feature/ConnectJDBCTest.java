package test.de.feature;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.features.ConnectJDBC;

public class ConnectJDBCTest {
	
	ConnectJDBC db_connect;
	@Before
	public void init(){
		db_connect = new ConnectJDBC();
	}
	@Test
	public void test() {
//		db_connect.customerData();
	}

}
