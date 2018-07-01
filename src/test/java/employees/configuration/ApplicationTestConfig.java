package employees.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 
 * @author Ventsislav Penev
 * 
 * This class is responsible to build the spring configuration for the
 * integration tests.
 *
 */
@Configuration
@PropertySource("classpath:emp-props-test.properties")
@ComponentScan(basePackages = { "employees.services" })
public class ApplicationTestConfig {

	//Spring configuration for integration tests.
}
