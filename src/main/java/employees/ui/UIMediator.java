package employees.ui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import emoployees.utils.DateUtils;
import employees.commons.EmployeePairDetails;
import employees.commons.ProjectTime;

/**
 * 
 * @author Ventsislav Penev
 * 
 * This class is responsible to manage the communication between the UI
 * components with each other. It is an example of <b>Mediator</b>
 * design pattern.
 */
@Component
class UIMediator {

	private Environment env;

	private JLabel informationLabel;
	private JTable employeeTable;
	private JTextField fileNameField;

	public UIMediator(final Environment env) {

		this.env = env;
	}

	void registerInformationLabel(final JLabel infoLabel) {

		this.informationLabel = infoLabel;
	}

	void registerEmployeeTable(final JTable empTable) {

		this.employeeTable = empTable;
	}

	void registerFileField(final JTextField fileField) {

		this.fileNameField = fileField;
	}

	void showFileChooser() {

		final JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		fileChooser.setDialogTitle(this.env.getProperty("screen.fileChooser.title")); //$NON-NLS-1$
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int returnValue = fileChooser.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			
			if(validateSelectedFile(fileChooser.getSelectedFile().getAbsolutePath())){
				
				this.fileNameField.setText(fileChooser.getSelectedFile().getAbsolutePath());
			}
			else{
				JOptionPane.showMessageDialog(null,this.env.getProperty("screen.noValidSelectedFile"), "Error", JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
	}

	void addEmployeePairDetails(final EmployeePairDetails empPairDetails) {

		if (empPairDetails == null) {

			JOptionPane.showMessageDialog(null, this.env.getProperty("screen.noEmployeePairFound"), "Info", //$NON-NLS-1$ //$NON-NLS-2$
					JOptionPane.INFORMATION_MESSAGE);

			return;
		}
		for (ProjectTime projTime : empPairDetails.getCommonProjects()) {

			@SuppressWarnings("boxing")
			final Object[] empTableRowData = new Object[] { empPairDetails.getFirstEmployee(),
					empPairDetails.getSecondEmployee(), projTime.getProjectId(), projTime.getStartDate(),
					projTime.getEndDate(),
					DateUtils.calculateDaysBetweenDates(projTime.getStartDate(), projTime.getEndDate()) };

			final DefaultTableModel empTableModel = ((DefaultTableModel) this.employeeTable.getModel());
			empTableModel.addRow(empTableRowData);
		}
	}

	void changeInformationLabel(final String text) {

		this.informationLabel.setText(text);
	}

	void changeFileFieldContent(final String text) {

		this.fileNameField.setText(text);
	}

	String getSelectedFile() {

		return this.fileNameField.getText();
	}
	
	@SuppressWarnings("static-method")
	private boolean validateSelectedFile(final String pathToFile){
		
		final File selectedFile = new File(pathToFile);
		
		return pathToFile.endsWith(".txt") && selectedFile.exists(); //$NON-NLS-1$
		
	}
}
