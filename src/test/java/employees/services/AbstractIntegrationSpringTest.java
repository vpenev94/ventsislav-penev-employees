package employees.services;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import employees.configuration.ApplicationTestConfig;

/**
 * 
 * @author Ventsislav Penev
 * 
 * This class is responsible for setting up the spring execution
 * environment for the integration tests.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
classes = { ApplicationTestConfig.class })
public abstract class AbstractIntegrationSpringTest {

	//Configuration for spring execution environment for integration tests.
}
