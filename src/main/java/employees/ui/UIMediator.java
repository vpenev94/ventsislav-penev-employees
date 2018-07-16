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

import employees.commons.EmployeePairDetails;
import employees.commons.ProjectTime;
import employees.utils.DateUtils;

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

			if (validateFile(fileChooser.getSelectedFile().getAbsolutePath())) {

				this.fileNameField.setText(fileChooser.getSelectedFile().getAbsolutePath());

			} else {

				showWarningMessage(this.env.getProperty("screen.noValidSelectedFile")); //$NON-NLS-1$
			}
		}
	}

	void addEmployeePairDetails(final EmployeePairDetails empPairDetails) {

		if (empPairDetails == null) {

			showInfoMessage(this.env.getProperty("screen.noEmployeePairFound")); //$NON-NLS-1$

			return;
		}
		for (ProjectTime projTime : empPairDetails.getCommonProjects()) {

			@SuppressWarnings("boxing")
			final Object[] empTableRowData = new Object[] { empPairDetails.getEmployeePair().getFirstEmployeeId(),
					empPairDetails.getEmployeePair().getSecondEmployeeId(), projTime.getProjectId(),
					projTime.getStartDate(), projTime.getEndDate(),
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

	boolean hasSelectedFile() {

		return !this.fileNameField.getText().isEmpty();

	}

	boolean isValidSelectedFile() {

		return validateFile(this.fileNameField.getText());

	}

	String getSelectedFile() {

		return this.fileNameField.getText();
	}

	@SuppressWarnings("static-method")
	void showWarningMessage(final String message) {

		JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
	}

	@SuppressWarnings("static-method")
	void showErrorMessage(final String message) {

		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
	}

	@SuppressWarnings("static-method")
	void showInfoMessage(final String message) {

		JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE); //$NON-NLS-1$
	}

	@SuppressWarnings("static-method")
	private boolean validateFile(final String pathToFile) {

		final File selectedFile = new File(pathToFile);

		return pathToFile.endsWith(".txt") && selectedFile.exists(); //$NON-NLS-1$

	}

}
