package com.wholesalestore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class WholesaleStoreApplicationTests {

	@Autowired
	DataSource dataSource;

	@Test
	public void testschema() throws SQLException {
		String schema = dataSource.getConnection().getCatalog();
		assertTrue("wholesalestore1".equalsIgnoreCase(schema));
	}
}
