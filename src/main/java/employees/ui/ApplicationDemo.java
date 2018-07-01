package employees.ui;

import javax.swing.SwingUtilities;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import employees.configuration.ApplicationConfig;

/**
 * 
 * @author Ventsislav Penev
 * 
 * This class is responsible to load the spring configuration and start
 * the Swing application by loading the main screen.
 */
class ApplicationDemo {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		final ApplicationContext appCcontext = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		SwingUtilities.invokeLater(() -> {

			EmployeesScreen empScreen = appCcontext.getBean(EmployeesScreen.class);
			empScreen.setVisible(true);

		});

	}

}