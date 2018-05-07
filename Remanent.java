import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;

import jxl.write.WriteException;

public class Remanent extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, Object[][]> remanent;
	private boolean isFilled = false;

	private final Object[] columnsNames = { "Asortyment", "Stan poczatkowy(szt)", "Stan poczatkowy(ml)" };
	private final String[] assortmentCategory = { "Wybierz rodzaj alkoholu", "WHISKY/COGNAC/BRANDY", "GIN/RUM/TEQUILA",
			"WÓDKA", "WERMUTH", "LIKIERY", "WINA MUSUJACE", "SOFTY", "PIWO", "PAPIEROSY",
			"CHIPSY / PALUSZKI / ORZESZKI", "POZOSTA£E" };

	private JComboBox<String> assortmentCategoryList;
	private DefaultComboBoxModel<String> modelBox;
	private JButton zapiszDoPliku, zapisz;
	private JPanel temp;
	private JTable table;
	private DefaultTableModel tableModel;

	private ReadExcel readFromExcel;
	private WriteExcel writeToExcel;
	public static final String writeFile = "C:\\temp\\remanent_start.xls";
	public static final String readFile = "C:\\temp\\remanent_awaryjny.xls";
	Remanent() {

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		JPanel groupPanel = new JPanel();
		groupPanel.setLayout(new BorderLayout());

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());

		JPanel assortmentCategoryPanel = initAssortmentCategoryComponents();

		remanent = new HashMap<String, Object[][]>();

		
		readFromExcel = new ReadExcel(readFile);
		table = new JTable();
		

		groupPanel.add(assortmentCategoryPanel, BorderLayout.NORTH);
		groupPanel.add(tablePanel, BorderLayout.SOUTH);

		tablePanel.add(new JScrollPane(table), BorderLayout.SOUTH);

		mainPanel.add(groupPanel, BorderLayout.NORTH);

		add(mainPanel);

	}

	private JPanel initAssortmentCategoryComponents() {

		temp = new JPanel();
		JPanel buttonsPanel = new JPanel();

		zapiszDoPliku = new JButton("Zapisz do pliku");
		zapiszDoPliku.setEnabled(false);
		
		 zapisz = new JButton("Zapisz");
		 zapisz.setEnabled(false);
		JButton exit = new JButton("Zamknij");
		
		buttonsPanel.setLayout(new BorderLayout());
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
		});
		
		buttonsPanel.add(zapiszDoPliku,BorderLayout.WEST);
		buttonsPanel.add(zapisz,BorderLayout.CENTER);
		buttonsPanel.add(exit,BorderLayout.EAST);

		modelBox = new DefaultComboBoxModel<String>(assortmentCategory);

		assortmentCategoryList = new JComboBox<String>(modelBox);

		temp.setLayout(new BorderLayout());

		zapiszDoPliku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				writeToExcel.close();
				
				for(Component c : getParent().getComponents()) {
					System.out.print("1. "+ c.getClass() +"\n "+"2. "+c.getClass().toString());
					c.setEnabled(true);
				}
				zapiszDoPliku.setEnabled(false);
			}
		});

		zapisz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(modelBox.getSize() == 2){
					temp.remove(assortmentCategoryList);
					zapiszDoPliku.setEnabled(true);
					repaint();
					revalidate();
				}
				Object[][] r;
				int count;
				String s = String.valueOf(assortmentCategoryList.getSelectedItem());
		
				switch (s) {
				case Settings.whiskyCognacBrandyString:
					//Ponizsza linia do usuniecia gdy Excel bedzie mnial wypelnione pola nazw produktow
					r = remanent.get(s);
					count = Settings.getWhiskyCognacBrandyEndRow() - Settings.getWhiskyCognacBrandyStartRow();
					for (int row = 0; row < count; row++) {

						try {
							//Ponizsza linia do usuniecia gdy Excel bedzie mnial wypelnione pola nazw produktow
							WriteExcel.getInstance().writeInt(3, Settings.getWhiskyCognacBrandyStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 1)));							
							WriteExcel.getInstance().writeInt(4, Settings.getWhiskyCognacBrandyStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 2)));
						
							System.out.println( "Row: "+row+" \n"
												+"First value: "+(String) r[row][0]+" \n"
												+"Second value: "+(String)table.getValueAt(row, 1)+" \n"
												+"Count: "+ count);
					
						
						} catch (WriteException we) {
							System.out.print("Write Exception");
						}catch(IOException e) {
							System.out.print("IO Exception");
						}catch(NumberFormatException nfe) {
							System.out.print("NumberFormatException");
						}

					}
					modelBox.removeElementAt(assortmentCategoryList.getSelectedIndex());
					zapisz.setEnabled(false);


					break;
				case Settings.ginRumTequilaString:
					//Ponizsza linia do usuniecia gdy Excel bedzie mnial wypelnione pola nazw produktow
					r = remanent.get(s);
					count = Settings.getGinRumTequilaEndRow() - Settings.getGinRumTequilaStartRow();
					for (int row = 0; row < count; row++) {

						try {
							writeToExcel.writeInt(3, Settings.getGinRumTequilaStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 1)));
							//Ponizsza linia do usuniecia gdy Excel bedzie mnial wypelnione pola nazw produktow
							writeToExcel.writeInt(4, Settings.getGinRumTequilaStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 2)));
						} catch (WriteException | IOException e) {
							e.printStackTrace();
						}catch(NumberFormatException nfe) {
							
						}

					}
					modelBox.removeElementAt(assortmentCategoryList.getSelectedIndex());
					zapisz.setEnabled(false);

					break;
				case Settings.wodkaString:
					//Ponizsza linia do usuniecia gdy Excel bedzie mnial wypelnione pola nazw produktow
					r = remanent.get(s);
					count = Settings.getWodkaEndRow() - Settings.getWodkaStartRow();
					for (int row = 0; row < count; row++) {

						try {
							writeToExcel.writeInt(3, Settings.getWodkaStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 1)));
							//Ponizsza linia do usuniecia gdy Excel bedzie mnial wypelnione pola nazw produktow
							writeToExcel.writeInt(4, Settings.getWodkaStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 2)));
						} catch (WriteException | IOException e) {
							e.printStackTrace();
						}catch(NumberFormatException nfe) {
							
						}

					}
					modelBox.removeElementAt(assortmentCategoryList.getSelectedIndex());
					zapisz.setEnabled(false);
					break;
					
				case Settings.wermuthString:
					r = remanent.get(s);
					count = Settings.getWermuthEndRow() - Settings.getWermuthStartRow();
					for (int row = 0; row < count; row++) {

						try {
							writeToExcel.writeInt(3, Settings.getWermuthStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 1)));
							//Ponizsza linia do usuniecia gdy Excel bedzie mnial wypelnione pola nazw produktow
							writeToExcel.writeInt(4, Settings.getWermuthStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 2)));
						} catch (WriteException | IOException e) {
							e.printStackTrace();
						}catch(NumberFormatException nfe) {
							
						}

					}
					modelBox.removeElementAt(assortmentCategoryList.getSelectedIndex());
					zapisz.setEnabled(false);
					break;
				case Settings.liquerString:
					r = remanent.get(s);
					count = Settings.getLiquerEndRow() - Settings.getLiquerStartRow();
					for (int row = 0; row < count; row++) {

						try {
							writeToExcel.writeInt(3, Settings.getLiquerStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 1)));
							//Ponizsza linia do usuniecia gdy Excel bedzie mnial wypelnione pola nazw produktow
							writeToExcel.writeInt(4, Settings.getLiquerStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 2)));
						} catch (WriteException | IOException e) {
							e.printStackTrace();
						}catch(NumberFormatException nfe) {
							
						}

					}
					modelBox.removeElementAt(assortmentCategoryList.getSelectedIndex());
					zapisz.setEnabled(false);
					break;
				case Settings.vineString:
					r = remanent.get(s);
					count = Settings.getVineEndRow() - Settings.getVineStartRow();
					for (int row = 0; row < count; row++) {

						try {
							writeToExcel.writeInt(3, Settings.getVineStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 1)));
							//Ponizsza linia do usuniecia gdy Excel bedzie mnial wypelnione pola nazw produktow
							writeToExcel.writeInt(4, Settings.getVineStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 2)));
						} catch (WriteException | IOException e) {
							e.printStackTrace();
						}catch(NumberFormatException nfe) {
							
						}

					}
					modelBox.removeElementAt(assortmentCategoryList.getSelectedIndex());
					zapisz.setEnabled(false);
					break;
				case Settings.softDrinksString:
					r = remanent.get(s);
					count = Settings.getSoftDrinkEndRow() - Settings.getSoftDrinkStartRow();
					for (int row = 0; row < count; row++) {

						try {
							writeToExcel.writeInt(3, Settings.getSoftDrinkStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 1)));
							//Ponizsza linia do usuniecia gdy Excel bedzie mnial wypelnione pola nazw produktow
							writeToExcel.writeInt(4, Settings.getSoftDrinkStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 2)));
						} catch (WriteException | IOException e) {
							e.printStackTrace();
						}catch(NumberFormatException nfe) {
							
						}

					}
					modelBox.removeElementAt(assortmentCategoryList.getSelectedIndex());
					zapisz.setEnabled(false);
					break;
				case Settings.beerString:
					r = remanent.get(s);
					count = Settings.getBeerEndRow()- Settings.getBeerStartRow();
					for (int row = 0; row < count; row++) {

						try {
							writeToExcel.writeInt(3, Settings.getBeerStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 1)));
							//Ponizsza linia do usuniecia gdy Excel bedzie mnial wypelnione pola nazw produktow
							writeToExcel.writeInt(4, Settings.getBeerStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 2)));
						} catch (WriteException | IOException e) {
							e.printStackTrace();
						}catch(NumberFormatException nfe) {
							
						}

					}
					modelBox.removeElementAt(assortmentCategoryList.getSelectedIndex());
					zapisz.setEnabled(false);
					break;
				case Settings.cigaretsString:
					r = remanent.get(s);
					count = Settings.getCigaretsEndRow() - Settings.getCigaretsStartRow();
					for (int row = 0; row < count; row++) {

						try {
							writeToExcel.writeInt(3, Settings.getCigaretsStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 1)));
							//Ponizsza linia do usuniecia gdy Excel bedzie mnial wypelnione pola nazw produktow
							writeToExcel.writeInt(4, Settings.getCigaretsStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 2)));
						} catch (WriteException | IOException e) {
							e.printStackTrace();
						}catch(NumberFormatException nfe) {
							
						}

					}
					modelBox.removeElementAt(assortmentCategoryList.getSelectedIndex());
					zapisz.setEnabled(false);
					break;
				case Settings.chipsSticksPeanutsString:
					r = remanent.get(s);
					count = Settings.getChipsSticksPeanutsEndRow() - Settings.getChipsSticksPeanutsStartRow();
					for (int row = 0; row < count; row++) {

						try {
							writeToExcel.writeInt(3, Settings.getChipsSticksPeanutsStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 1)));
							//Ponizsza linia do usuniecia gdy Excel bedzie mnial wypelnione pola nazw produktow
							writeToExcel.writeInt(4, Settings.getChipsSticksPeanutsStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 2)));
						} catch (WriteException | IOException e) {
							e.printStackTrace();
						}catch(NumberFormatException nfe) {
							
						}

					}
					modelBox.removeElementAt(assortmentCategoryList.getSelectedIndex());
					zapisz.setEnabled(false);
					break;
				case Settings.otherString:
					r = remanent.get(s);
					count = Settings.getOthersItemsEndRow() - Settings.getOthersItemsStartRow();
					for (int row = 0; row < count; row++) {

						try {
							writeToExcel.writeInt(3, Settings.getOthersItemsStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 1)));
							//Ponizsza linia do usuniecia gdy Excel bedzie mnial wypelnione pola nazw produktow
							writeToExcel.writeInt(4, Settings.getOthersItemsStartRow()+row, Integer.parseInt((String)table.getValueAt(row, 2)));
						} catch (WriteException | IOException e) {
							e.printStackTrace();
						}catch(NumberFormatException nfe) {
							
						}

					}
					modelBox.removeElementAt(assortmentCategoryList.getSelectedIndex());
					zapisz.setEnabled(false);
					break;
				default:
					System.out.println("nieprzewidziana sytuacja");
				}
				

			}
		});

		assortmentCategoryList.addPopupMenuListener(new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				
					modelBox.removeElement("Wybierz rodzaj alkoholu");
				
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				
			
				modelBox.insertElementAt("Wybierz rodzaj alkoholu",0);
				zapisz.setEnabled(false);
				String s = (String) assortmentCategoryList.getSelectedItem();
				System.out.print(String.valueOf(assortmentCategoryList.getSelectedItem()));

				switch (s) {
				case Settings.whiskyCognacBrandyString:
					zapisz.setEnabled(true);
					try {
						if (!remanent.containsValue(Settings.whiskyCognacBrandyString)) {
						remanent.put(
								Settings.whiskyCognacBrandyString,
								readFromExcel.makeListOfCategorizedAlcohol(Settings.getWhiskyCognacBrandyStartRow(),
										Settings.getWhiskyCognacBrandyEndRow()));
						}
						table.setModel(new DefaultTableModel(remanent.get(Settings.whiskyCognacBrandyString), columnsNames));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					break;

				case Settings.ginRumTequilaString:
					zapisz.setEnabled(true);
					try {
						if (!remanent.containsValue(Settings.ginRumTequilaString)) {
							remanent.put(
									Settings.ginRumTequilaString,
									readFromExcel.makeListOfCategorizedAlcohol(Settings.getGinRumTequilaStartRow(),
											Settings.getGinRumTequilaEndRow()));
						}
						table.setModel(new DefaultTableModel(remanent.get(Settings.ginRumTequilaString), columnsNames));
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;

				case Settings.wodkaString:
					zapisz.setEnabled(true);
					try {
						if (!remanent.containsValue(Settings.wodkaString)) {
						remanent.put(Settings.wodkaString,
								readFromExcel.makeListOfCategorizedAlcohol(Settings.getWodkaStartRow(), 
										Settings.getWodkaEndRow()));
						}
						table.setModel(new DefaultTableModel(remanent.get(Settings.wodkaString), columnsNames));
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case Settings.wermuthString:
					zapisz.setEnabled(true);
					try {
						if (!remanent.containsValue(Settings.wermuthString)) {
						remanent.put(Settings.wermuthString,
								readFromExcel.makeListOfCategorizedAlcohol(Settings.getWermuthStartRow(), 
										Settings.getWermuthEndRow()));
						}
						table.setModel(new DefaultTableModel(remanent.get(Settings.wermuthString), columnsNames));

					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case Settings.liquerString:
					zapisz.setEnabled(true);
					try {
						
						remanent.put(Settings.liquerString,
								readFromExcel.makeListOfCategorizedAlcohol(Settings.getLiquerStartRow(), 
										Settings.getLiquerEndRow()));
						
						table.setModel(new DefaultTableModel(remanent.get(Settings.liquerString), columnsNames));
					
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case Settings.vineString:
					zapisz.setEnabled(true);
					try {
						
						remanent.put(Settings.vineString,
								readFromExcel.makeListOfCategorizedAlcohol(Settings.getVineStartRow(), 
										Settings.getVineEndRow()));
						
						table.setModel(new DefaultTableModel(remanent.get(Settings.vineString), columnsNames));

					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case Settings.softDrinksString:
					zapisz.setEnabled(true);
					try {
						
						remanent.put(Settings.softDrinksString,
								readFromExcel.makeListOfCategorizedAlcohol(Settings.getSoftDrinkStartRow(), 
										Settings.getSoftDrinkEndRow()));
						
						table.setModel(new DefaultTableModel(remanent.get(Settings.softDrinksString), columnsNames));

					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case Settings.beerString:
					zapisz.setEnabled(true);
					try {
					
						remanent.put(Settings.beerString,
								readFromExcel.makeListOfCategorizedAlcohol(Settings.getBeerStartRow(), 
										Settings.getBeerEndRow()));
						
						table.setModel(new DefaultTableModel(remanent.get(Settings.beerString), columnsNames));

					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case Settings.cigaretsString:
					zapisz.setEnabled(true);
					try {
						
						remanent.put(Settings.cigaretsString,
								readFromExcel.makeListOfCategorizedAlcohol(Settings.getCigaretsStartRow(), 
										Settings.getCigaretsEndRow()));
						
						table.setModel(new DefaultTableModel(remanent.get(Settings.cigaretsString), columnsNames));

					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case Settings.chipsSticksPeanutsString:
					zapisz.setEnabled(true);
					try {
						
						remanent.put(Settings.chipsSticksPeanutsString,
								readFromExcel.makeListOfCategorizedAlcohol(Settings.getChipsSticksPeanutsStartRow(), 
										Settings.getChipsSticksPeanutsEndRow()));
						
						table.setModel(new DefaultTableModel(remanent.get(Settings.chipsSticksPeanutsString), columnsNames));

					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case Settings.otherString:
					zapisz.setEnabled(true);
					try {
						
						remanent.put(Settings.otherString,
								readFromExcel.makeListOfCategorizedAlcohol(Settings.getOthersItemsStartRow(), 
										Settings.getOthersItemsEndRow()));
						
						table.setModel(new DefaultTableModel(remanent.get(Settings.otherString), columnsNames));

					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				default:
					System.out.println("nieprzewidziana sytuacja");
				}
				


			};

			@Override
			public void popupMenuCanceled(PopupMenuEvent arg0) {
				
			}
		});

		temp.add(assortmentCategoryList, BorderLayout.WEST);
		temp.add(buttonsPanel, BorderLayout.EAST);
		

		return temp;
	}

	private DefaultTableModel initModelFromExcel() {
		try {
			readFromExcel = new ReadExcel(readFile);
			tableModel = new DefaultTableModel(readFromExcel.makeListOfCategorizedAlcohol(
					Settings.getWhiskyCognacBrandyStartRow(), Settings.getWhiskyCognacBrandyEndRow()), columnsNames);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return tableModel;
	}
	public boolean isFilled() {
		return isFilled;
	}
}
