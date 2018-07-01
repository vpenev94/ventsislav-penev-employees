package employees.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 
 * @author Ventsislav Penev
 * 
 * This class is responsible to build the spring configuration.
 *
 */
@Configuration
@PropertySource("classpath:emp-props.properties")
@ComponentScan(basePackages = { "employees.services", "employees.ui" })
public class ApplicationConfig {

	//Spring configuration.
}
