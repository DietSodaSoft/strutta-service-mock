package com.schultzco.webservices;

import com.schultzco.webservices.SchultzCoServicesApplication;
import com.schultzco.webservices.config.IntegrationesqueTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IntegrationesqueTestConfiguration.class)
@WebAppConfiguration
@ActiveProfiles("integration-testing")
public class SchultzCoServicesApplicationTests {

	@Test
	public void contextLoads() {
	}

}
