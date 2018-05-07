import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;


import Impreza.Impreza;
import Pracownicy.Pracownicy;
import Pracownicy.Pracownik;
import Pracownicy.Stanowisko;

public class StartEventPanel extends JPanel {

	private final Pattern inputTimeRegex = Pattern.compile("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");
	private final Object[] eventEmployeesData = { "Imie i nazwisko ", "Stanowisko", "Godzina przyjscia" };
	private final String[] employee = { "Wybierz pracownika: ", "Marcin Ogrodniczak", "Jagoda Mossakowska", "Michal Serafin" };
	private final String[] jobs = { "Barman", "Szatnia", "Sala", "Zmywak" };
	private final String[] eventType = { "Osiemnastka", "Impreza Otwarta", "Impreza Firmowa" };

	private JComboBox<String> employeesList;
	private JSpinner eventDate, startEventTime;
	private JTextField eventName;
	private JTable eventEmployees;

	private DefaultTableModel eventEmployeesModel;
	private DefaultComboBoxModel<String> employeeListModel;
	private Matcher matcher;

	private JSpinner.DateEditor dateEditor;
	private JSpinner.DateEditor timeEditor;

	private Pracownicy p;

	StartEventPanel() {

		JPanel eventNamePanel = initEventNamePanel();

		JPanel groupedEmployeePanel = new JPanel();
		groupedEmployeePanel.setLayout(new BorderLayout());
		groupedEmployeePanel.add(initEmployeePanel(), BorderLayout.NORTH);
		groupedEmployeePanel.add(initEventEmployeesTable(), BorderLayout.SOUTH);

		JPanel groupedDateHourPanel = new JPanel();
		groupedDateHourPanel.setLayout(new BorderLayout());
		groupedDateHourPanel.add(initTimeComponents(), BorderLayout.WEST);
		groupedDateHourPanel.add(initDateInputPanel(), BorderLayout.EAST);

		JPanel eventInfoPanel = new JPanel();
		eventInfoPanel.setLayout(new BorderLayout());

		eventInfoPanel.add(eventNamePanel, BorderLayout.NORTH);
		eventInfoPanel.add(groupedDateHourPanel, BorderLayout.CENTER);
		eventInfoPanel.add(groupedEmployeePanel, BorderLayout.SOUTH);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		mainPanel.add(eventInfoPanel, BorderLayout.CENTER);
		
		
		add(mainPanel);

	}

	private JPanel initDateInputPanel() {

		JPanel temp = new JPanel();
		
		eventDate = new JSpinner(new SpinnerDateModel());
		dateEditor = new JSpinner.DateEditor(eventDate, "dd.MM.yyyy");
		eventDate.setEditor(dateEditor);
		eventDate.setValue(new Date());

		temp.add(new JLabel("Data imprezy: "));
		temp.add(eventDate);
		return temp;
	}

	private JPanel initTimeComponents() {

		JPanel temp = new JPanel();

		startEventTime = new JSpinner(new SpinnerDateModel());
		timeEditor = new JSpinner.DateEditor(startEventTime, "HH:mm");
		startEventTime.setEditor(timeEditor);
		startEventTime.setValue(new Date());

		temp.add(new JLabel("Godzina rozpoczecia: "));
		temp.add(startEventTime);

		return temp;
	}

	private JPanel initEmployeePanel() {
		JPanel temp = new JPanel();
		

		JButton addEmployee = new JButton("Dodaj");

		addEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String employeeStartWorkHour = JOptionPane.showInputDialog("Wprowadz godzine przyjscia");
					matcher = inputTimeRegex.matcher(employeeStartWorkHour);

					if (matcher.find()) {
						String employeeJob = (String) JOptionPane.showInputDialog(null, "Wybierz stanowisko pracy", "Stanowisko pracy",
								JOptionPane.QUESTION_MESSAGE, null, jobs, jobs[0]);
						eventEmployeesModel.addRow(new Object[] { employeesList.getSelectedItem(), employeeJob, employeeStartWorkHour });
						employeesList.removeItemAt(employeesList.getSelectedIndex());
					}

					if (employeeStartWorkHour.isEmpty())
						JOptionPane.showMessageDialog(null, "Wprowadz poprawna godzine");
				} catch (NullPointerException npe) {
				}
			}
		});

		JButton clearTable = new JButton("Wyczysc liste");
		clearTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eventEmployeesModel = new DefaultTableModel(new Object[][] {}, eventEmployeesData);
				eventEmployees.setModel(eventEmployeesModel);
				employeeListModel = new DefaultComboBoxModel<>(employee);
				employeesList.setModel(employeeListModel);

			}
		});
		employeeListModel = new DefaultComboBoxModel<String>(employee);
		employeesList = new JComboBox<String>(employeeListModel);

		employeesList.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				employeeListModel.removeElement("Wybierz pracownika: ");
				employeesList.setModel(employeeListModel);
			}

			public void popupMenuCanceled(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {

			}

		});

		temp.add(employeesList, BorderLayout.WEST);
		temp.add(addEmployee, BorderLayout.CENTER);
		temp.add(clearTable, BorderLayout.EAST);

		return temp;
	}

	private JPanel initEventNamePanel() {
		JPanel temp = new JPanel();

		eventName = new JTextField();
		eventName.setPreferredSize(new Dimension(250, 20));

		temp.add(new JLabel("Wprowadz nazwe imprezy"), BorderLayout.WEST);
		temp.add(eventName, BorderLayout.EAST);

		return temp;
	}

public Impreza getEventInfo(){
	Impreza currentEvent = new Impreza();
	

	/*
	 * System.out.println(dateEditor.getFormat().format(eventDate.
	 * getValue()));
	 * 
	 * System.out.println(timeEditor.getFormat().format(startEventTime
	 * .getValue())); System.out.println(eventName.getText());
	 */
	currentEvent.dodajNazweImprezy(eventName.getText());
	currentEvent.ustawGodzineRozpoczeciaImprezy(timeEditor.getFormat().format(startEventTime.getValue()));
	currentEvent.ustawDateRozpoczeciaImprezy(dateEditor.getFormat().format(eventDate.getValue()));

	Vector v = eventEmployeesModel.getDataVector();
	for (Object o : v) {

		String[] splitStr = o.toString().split(" ");

		currentEvent
				.dodajPracownikaDoImprezy(new Pracownik(splitStr[0], splitStr[1], new Stanowisko(splitStr[2]), splitStr[3]));

	}
	return currentEvent;
}

	private JScrollPane initEventEmployeesTable() {
		eventEmployeesModel = new DefaultTableModel(new Object[][] {}, eventEmployeesData);
		eventEmployees = new JTable(eventEmployeesModel);
		eventEmployees.setEnabled(false);
		eventEmployees.getTableHeader().setReorderingAllowed(false);

		eventEmployees.setPreferredScrollableViewportSize(new Dimension(470, 100));
		eventEmployees.setEnabled(false);

		return new JScrollPane(eventEmployees);
	}
	/*
	 * public static void main(String[] args) {
	 * 
	 * JFrame frame = new JFrame("Rozpocznij impreze"); StartEventPanel dpe =
	 * new StartEventPanel();
	 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.add(dpe);
	 * frame.setVisible(true); frame.pack();
	 * 
	 * }
	 */

}
