import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Impreza.Impreza;
import Pracownicy.Pracownicy;
import Pracownicy.Pracownik;
import Pracownicy.Stanowisko;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Datebase db;
	private Impreza currentEvent;
	private List<Pracownik> pracownicy;
	private JMenuBar menuBar;
	private JMenuItem startParty, closeProgram, addEmployee, soldAlcohol, staffHours, addProduct;
	private JPanel mainPanel, addEmployeePanel;
	private JTextField imie, nazwisko, stanowisko;
	private JButton dodaj, fillStocktalking,startEvent;
	private JComboBox<String> alcohol;
	private StartEventPanel startEventPanel;
	private JScrollPane sp;
	private DefaultTableModel model;
	private Remanent r;
	

	MainFrame() {

		db = new Datebase();
		init();
		setVisible(true);
		setLayout(new FlowLayout());
		setSize(640, 320);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}

	public void init() {

		mainPanel = new JPanel();
		startEventPanel = new StartEventPanel();
		fillStocktalking = new JButton("Wypelnij remanent");
		startEvent=new JButton("Rozpocznij impreze");
		startEvent.setEnabled(false);

		initAddEmployeePanel();
		addMenuBarPanel();
		initActionListeners();

		add(mainPanel);
	}

	private void initAddEmployeePanel() {

		addEmployeePanel = new JPanel();
		addEmployeePanel.setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout());

		imie = new JTextField(15);
		nazwisko = new JTextField(15);
		stanowisko = new JTextField(15);

		inputPanel.add(imie, BorderLayout.WEST);
		inputPanel.add(nazwisko, BorderLayout.CENTER);
		inputPanel.add(stanowisko, BorderLayout.EAST);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());

		dodaj = new JButton("Dodaj");
		dodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!imie.getText().isEmpty() && !nazwisko.getText().isEmpty() && !stanowisko.getText().isEmpty()) {

					db.insertPracownik(imie.getText(), nazwisko.getText(), new Stanowisko(stanowisko.getText()));
					addEmployeePanel.remove(sp);
					addEmployeePanel.add(sp = new JScrollPane(new JTable(loadTableModelFromDb())));
					mainPanel.revalidate();
					mainPanel.repaint();
				}
			}
		});
		topPanel.add(inputPanel, BorderLayout.WEST);
		topPanel.add(dodaj, BorderLayout.EAST);

		sp = new JScrollPane(new JTable(loadTableModelFromDb()));

		addEmployeePanel.add(topPanel, BorderLayout.NORTH);
		addEmployeePanel.add(sp, BorderLayout.SOUTH);

	}

	private JPanel initAddProductPanel() {

		JPanel tmp = new JPanel();
		String[] alkohol = { "Gin", "Rum" };
		alcohol = new JComboBox<>(alkohol);
		tmp.add(alcohol);
		return tmp;
	}

	private void addMenuBarPanel() {
		menuBar = new JMenuBar();

		menuBar.setName("menuBar");

		JMenu firstCol = new JMenu("Plik");
		JMenu secondCol = new JMenu("Pracownicy");
		JMenu thirdCol = new JMenu("Asortyment");
		JMenu fourthCol = new JMenu("Raporty");

		startParty = new JMenuItem("Rozpocznij impreze");
		closeProgram = new JMenuItem("Zakoncz");
		addEmployee = new JMenuItem("Dodaj pracownika");
		addProduct = new JMenuItem("Dodaj produkt");
		soldAlcohol = new JMenuItem("Sprzedany alkohol");
		staffHours = new JMenuItem("Godziny pracy personelu");

		firstCol.add(startParty);
		firstCol.add(closeProgram);
		secondCol.add(addEmployee);
		thirdCol.add(addProduct);

		fourthCol.add(soldAlcohol);
		fourthCol.add(staffHours);
		menuBar.add(firstCol);
		menuBar.add(secondCol);
		menuBar.add(thirdCol);
		menuBar.add(fourthCol);

		setJMenuBar(menuBar);

	}

	private void initActionListeners() {

		startEvent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		
				mainPanel.removeAll();
				mainPanel.revalidate();
				mainPanel.repaint();
				
				pack();
			}
		});
		fillStocktalking.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				JPanel panel = new JPanel();
				panel.setLayout(new BorderLayout());
				panel.add(new Remanent(), BorderLayout.SOUTH);
				panel.add(startEvent, BorderLayout.NORTH);
				
				currentEvent = startEventPanel.getEventInfo();
				mainPanel.removeAll();
				mainPanel.revalidate();
				mainPanel.repaint();
				mainPanel.add(panel);
				
				pack();

			}
		});

		startParty.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				JPanel panel = new JPanel();
				panel.setLayout(new BorderLayout());
				panel.add(startEventPanel, BorderLayout.NORTH);
				panel.add(fillStocktalking, BorderLayout.SOUTH);

				mainPanel.removeAll();
				mainPanel.revalidate();
				mainPanel.repaint();

				mainPanel.add(panel);

				pack();
			}
		});

		addEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				mainPanel.removeAll();
				mainPanel.revalidate();
				mainPanel.repaint();
				mainPanel.add(addEmployeePanel);
				pack();

			}
		});

		addProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				mainPanel.removeAll();
				mainPanel.revalidate();
				mainPanel.repaint();
				mainPanel.add(initAddProductPanel());

			}
		});

		closeProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				db.closeConnection();
			}

		});

	}

	private static void nameOrder(List<Pracownik> persons) {

		Collections.sort(persons, new Comparator<Object>() {

			public int compare(Object o1, Object o2) {
				
				int sComp= 0;
					String firstPersonName = ((Pracownik) o1).getImie();
				String 	secondPersonName = ((Pracownik) o2).getImie();
					try {
						sComp = firstPersonName.compareTo(secondPersonName);	
						

				} catch (NullPointerException e) {
					if(firstPersonName == null) 
						firstPersonName =" ";
					secondPersonName = " ";
					sComp = firstPersonName.compareTo(secondPersonName);	
					
				}
				if (sComp != 0) {
					if (!firstPersonName.isEmpty() && !secondPersonName.isEmpty())
						return sComp;
					return (firstPersonName.isEmpty()) ? 1 : -1;
				}
				return sComp;
			}
		});
	}

	private static void secondNameOrder(List<Pracownik> persons) {

		Collections.sort(persons, new Comparator() {

			public int compare(Object o1, Object o2) {
				int sComp;
				String firstPersonSecondName = ((Pracownik) o1).getImie();
				String secondPersonSecondName = ((Pracownik) o2).getImie();

				sComp = firstPersonSecondName.compareTo(secondPersonSecondName);

				if (sComp != 0) {
					if (!firstPersonSecondName.isEmpty() && !secondPersonSecondName.isEmpty())
						return sComp;
					return (firstPersonSecondName.isEmpty()) ? 1 : -1;
				}
				return sComp;
			}

		});
	}

	private static void jobOrder(List<Pracownik> persons) {

		Collections.sort(persons, new Comparator<Object>() {

			public int compare(Object o1, Object o2) {

				String firstPersonJob = ((Pracownik) o1).getStanowsiko();
				String secondPersonJob = ((Pracownik) o2).getStanowsiko();

				int sComp = firstPersonJob.compareTo(secondPersonJob);

				if (sComp != 0) {
					if (!firstPersonJob.isEmpty() && !secondPersonJob.isEmpty())
						return sComp;
					return (firstPersonJob.isEmpty()) ? 1 : -1;
				}
				return sComp;
			}
		});
	}

	private DefaultTableModel loadTableModelFromDb() {

		pracownicy = db.selectPracownicy();

		nameOrder(pracownicy);

		model = new DefaultTableModel(new String[] { "Imie", "Nazwisko", "Stanowisko" }, 0);

		for (Pracownik p : pracownicy) {
			String d = p.getImie();
			String e = p.getNazawisko();
			String f = p.getStanowsiko();
			model.addRow(new Object[] { d, e, f });
		}
		return model;

	}

}
