package employees.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import employees.commons.EmployeePairDetails;

/**
 * 
 * @author Ventsislav Penev
 * 
 * This class represents the UI main screen.
 * It is also responsible for handling the user interaction.
 */
@Component
class EmployeesScreen extends JFrame {

	private static final long serialVersionUID = 1L;

	private Environment env;
	private UIMediator mediator;

	@Autowired
	private UIEmployeeService uiService;

	private JLabel informationLabel;
	private JPanel filePanel;
	private JTextField fileNameField;
	private JButton chooseFileButton;
	private JButton longestEmpPairButton;
	private JTable empTable;
	private JScrollPane empTableScrollPane;

	public EmployeesScreen(Environment env, UIMediator mediator) {

		this.env = env;
		this.mediator = mediator;

		initUIComponents();
		registerComponentsInMediator();
		attachListeners();

		this.setTitle(this.env.getProperty("screen.title")); //$NON-NLS-1$
		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		this.add(Box.createRigidArea(new Dimension(0, 10)));
		this.add(this.informationLabel);
		this.add(this.filePanel);
		this.add(this.fileNameField);
		this.add(this.empTableScrollPane);

		setLocationRelativeTo(null);
		setVisible(true);

	}

	private void initUIComponents() {

		this.informationLabel = new JLabel(this.env.getProperty("screen.informationLabel")); //$NON-NLS-1$
		this.informationLabel.setAlignmentX(CENTER_ALIGNMENT);

		this.fileNameField = new JTextField();
		this.fileNameField.setAlignmentX(CENTER_ALIGNMENT);

		this.chooseFileButton = new JButton(this.env.getProperty("screen.chooseFile")); //$NON-NLS-1$
		this.longestEmpPairButton = new JButton(this.env.getProperty("screen.showLongestEmpPair")); //$NON-NLS-1$

		this.filePanel = new JPanel();
		this.filePanel.setSize(200, 200);
		final FlowLayout layout = new FlowLayout();
		layout.setHgap(10);
		layout.setVgap(10);
		this.filePanel.setLayout(layout);
		this.filePanel.add(this.chooseFileButton);
		this.filePanel.add(this.longestEmpPairButton);

		@SuppressWarnings("nls")
		final String empTableColumns[] = { "Emp1Id", "Emp2Id", "ProjectId", "StartDate", "EndDate", "DaysWorked" };

		final DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(empTableColumns);
		this.empTable = new JTable(tableModel);
		this.empTable.setEnabled(false);

		this.empTableScrollPane = new JScrollPane(this.empTable);
	}

	private void registerComponentsInMediator() {

		this.mediator.registerInformationLabel(this.informationLabel);
		this.mediator.registerFileField(this.fileNameField);
		this.mediator.registerEmployeeTable(this.empTable);
	}

	private void attachListeners() {

		this.chooseFileButton.addActionListener(ev -> this.mediator.showFileChooser());

		this.longestEmpPairButton.addActionListener(ev -> {

			final String selectedFile = this.mediator.getSelectedFile();
			
			try{
				
				final EmployeePairDetails longestPair = this.uiService.findLongestEmployeePair(selectedFile);
				this.mediator.addEmployeePairDetails(longestPair);
			
			}catch(@SuppressWarnings("unused") IOException ex){
				
				JOptionPane.showMessageDialog(null,this.env.getProperty("screen.erorReadingFromFIle"), "Error", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
			}
		});
	}
}