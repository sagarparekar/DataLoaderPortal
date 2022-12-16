package com.patient.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.patient.model.Role;
import com.patient.repository.RoleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {

	@Autowired 
	private RoleRepository repo;
	
	@Test
	public void testCreateRoles() {
		Role test = new Role("ROLE_TEST");
		Role test2 = new Role("ROLE_TEST2");
		
		repo.saveAll(List.of(test, test2));
		
		long count = repo.count();
		assertEquals(2, count);
	}
}
