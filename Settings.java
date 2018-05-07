
public class Settings {
	
	public static final String whiskyCognacBrandyString = "WHISKY/COGNAC/BRANDY";
	public static final String ginRumTequilaString = "GIN/RUM/TEQUILA";
	public static final String wodkaString = "WÓDKA";
	public static final String wermuthString = "WERMUTH";
	public static final String liquerString = "LIKIERY";
	public static final String vineString = "WINA MUSUJACE";
	public static final String softDrinksString = "SOFTY";
	public static final String beerString = "PIWO";
	public static final String cigaretsString = "PAPIEROSY";
	public static final String chipsSticksPeanutsString = "CHIPSY / PALUSZKI / ORZESZKI";
	public static final String otherString = "POZOSTA£E" ;
	
	private static Integer itemsName = 0;
	private static Integer bootleCapacity = 1;
	private static Integer pricePerPortion = 2;
	private static Integer amountOfBottleAtTheBeginnin = 3;
	private static Integer mlAtTheBeginnin = 4;
	private static Integer amountOfSuppliedItems = 5;
	private static Integer costOfSuplly = 6;
	private static Integer amountOfBottleAtTheEnd = 7;
	private static Integer mlAtTheEnd = 8;
	private static Integer amountOfPortion = 9;
	private static Integer totalValue = 10;
	
	private static Integer whiskyCognacBrandyStartRow = 4;
	private static Integer whiskyCognacBrandyEndRow = 25;
	private static Integer ginRumTequilaStartRow = 26;
	private static Integer ginRumTequilaEndRow = 43;
	private static Integer wodkaStartRow = 45;
	private static Integer wodkaEndRow = 76;
	private static Integer wermuthStartRow = 78;
	private static Integer wermuthEndRow = 83;
	private static Integer liquerStartRow = 85;
	private static Integer liquerEndRow = 104;
	private static Integer vineStartRow = 106;
	private static Integer vineEndRow = 114;
	private static Integer softDrinkStartRow = 116;
	private static Integer softDrinkEndRow = 150;
	private static Integer beerStartRow = 152;
	private static Integer beerEndRow = 165;
	private static Integer cigaretsStartRow = 167;
	private static Integer cigaretsEndRow = 168;

	private static Integer chipsSticksPeanutsStartRow = 170;
	private static Integer chipsSticksPeanutsEndRow = 173;	
	private static Integer othersItemsStartRow = 175;
	private static Integer othersItemsEndRow = 192;
	
	Settings(){
		
	}
	public static Integer getWhiskyCognacBrandyStartRow() {
		return whiskyCognacBrandyStartRow;
	}


	public static void setWhiskyCognacBrandyStartRow(Integer whiskyCognacBrandyStartRow) {
		Settings.whiskyCognacBrandyStartRow = whiskyCognacBrandyStartRow;
	}


	public static Integer getWhiskyCognacBrandyEndRow() {
		return whiskyCognacBrandyEndRow;
	}


	public static void setWhiskyCognacBrandyEndRow(Integer whiskyCognacBrandyEndRow) {
		Settings.whiskyCognacBrandyEndRow = whiskyCognacBrandyEndRow;
	}


	public static Integer getGinRumTequilaStartRow() {
		return ginRumTequilaStartRow;
	}


	public static void setGinRumTequilaStartRow(Integer ginRumTequilaStartRow) {
		Settings.ginRumTequilaStartRow = ginRumTequilaStartRow;
	}


	public static Integer getGinRumTequilaEndRow() {
		return ginRumTequilaEndRow;
	}


	public static void setGinRumTequilaEndRow(Integer ginRumTequilaEndRow) {
		Settings.ginRumTequilaEndRow = ginRumTequilaEndRow;
	}


	public static Integer getWodkaStartRow() {
		return wodkaStartRow;
	}


	public static void setWodkaStartRow(Integer wodkaStartRow) {
		Settings.wodkaStartRow = wodkaStartRow;
	}


	public static Integer getWodkaEndRow() {
		return wodkaEndRow;
	}


	public static void setWodkaEndRow(Integer wodkaEndRow) {
		Settings.wodkaEndRow = wodkaEndRow;
	}


	public static Integer getWermuthStartRow() {
		return wermuthStartRow;
	}


	public static void setWermuthStartRow(Integer wermuthStartRow) {
		Settings.wermuthStartRow = wermuthStartRow;
	}


	public static Integer getWermuthEndRow() {
		return wermuthEndRow;
	}


	public static void setWermuthEndRow(Integer wermuthEndRow) {
		Settings.wermuthEndRow = wermuthEndRow;
	}


	public static Integer getLiquerStartRow() {
		return liquerStartRow;
	}


	public static void setLiquerStartRow(Integer liquerStartRow) {
		Settings.liquerStartRow = liquerStartRow;
	}


	public static Integer getLiquerEndRow() {
		return liquerEndRow;
	}


	public static void setLiquerEndRow(Integer liquerEndRow) {
		Settings.liquerEndRow = liquerEndRow;
	}


	public static Integer getVineStartRow() {
		return vineStartRow;
	}


	public static void setVineStartRow(Integer vineStartRow) {
		Settings.vineStartRow = vineStartRow;
	}


	public static Integer getVineEndRow() {
		return vineEndRow;
	}


	public static void setVineEndRow(Integer vineEndRow) {
		Settings.vineEndRow = vineEndRow;
	}


	public static Integer getSoftDrinkStartRow() {
		return softDrinkStartRow;
	}


	public static void setSoftDrinkStartRow(Integer softDrinkStartRow) {
		Settings.softDrinkStartRow = softDrinkStartRow;
	}


	public static Integer getSoftDrinkEndRow() {
		return softDrinkEndRow;
	}


	public static void setSoftDrinkEndRow(Integer softDrinkEndRow) {
		Settings.softDrinkEndRow = softDrinkEndRow;
	}


	public static Integer getBeerStartRow() {
		return beerStartRow;
	}


	public static void setBeerStartRow(Integer beerStartRow) {
		Settings.beerStartRow = beerStartRow;
	}


	public static Integer getBeerEndRow() {
		return beerEndRow;
	}


	public static void setBeerEndRow(Integer beerEndRow) {
		Settings.beerEndRow = beerEndRow;
	}


	public static Integer getCigaretsStartRow() {
		return cigaretsStartRow;
	}


	public static void setCigaretsStartRow(Integer cigaretsStartRow) {
		Settings.cigaretsStartRow = cigaretsStartRow;
	}


	public static Integer getCigaretsEndRow() {
		return cigaretsEndRow;
	}


	public static void setCigaretsEndRow(Integer cigaretsEndRow) {
		Settings.cigaretsEndRow = cigaretsEndRow;
	}


	public static Integer getChipsSticksPeanutsStartRow() {
		return chipsSticksPeanutsStartRow;
	}


	public static void setChipsSticksPeanutsStartRow(Integer chipsSticksPeanutsStartRow) {
		Settings.chipsSticksPeanutsStartRow = chipsSticksPeanutsStartRow;
	}


	public static Integer getChipsSticksPeanutsEndRow() {
		return chipsSticksPeanutsEndRow;
	}


	public static void setChipsSticksPeanutsEndRow(Integer chipsSticksPeanutsEndRow) {
		Settings.chipsSticksPeanutsEndRow = chipsSticksPeanutsEndRow;
	}


	public static Integer getOthersItemsStartRow() {
		return othersItemsStartRow;
	}


	public static void setOthersItemsStartRow(Integer othersItemsStartRow) {
		Settings.othersItemsStartRow = othersItemsStartRow;
	}


	public static Integer getOthersItemsEndRow() {
		return othersItemsEndRow;
	}


	public static void setOthersItemsEndRow(Integer othersItemsEndRow) {
		Settings.othersItemsEndRow = othersItemsEndRow;
	}
	
	public static Integer getItemsName() {
		return itemsName;
	}

	public static void setItemsName(Integer itemsName) {
		Settings.itemsName = itemsName;
	}

	public static Integer getBootleCapacity() {
		return bootleCapacity;
	}

	public static void setBootleCapacity(Integer bootleCapacity) {
		Settings.bootleCapacity = bootleCapacity;
	}

	public static Integer getPricePerPortion() {
		return pricePerPortion;
	}

	public static void setPricePerPortion(Integer pricePerPortion) {
		Settings.pricePerPortion = pricePerPortion;
	}

	public static Integer getAmountOfBottleAtTheBeginnin() {
		return amountOfBottleAtTheBeginnin;
	}

	public static void setAmountOfBottleAtTheBeginnin(Integer amountOfBottleAtTheBeginnin) {
		Settings.amountOfBottleAtTheBeginnin = amountOfBottleAtTheBeginnin;
	}

	public static Integer getMlAtTheBeginnin() {
		return mlAtTheBeginnin;
	}

	public static void setMlAtTheBeginnin(Integer mlAtTheBeginnin) {
		Settings.mlAtTheBeginnin = mlAtTheBeginnin;
	}

	public static Integer getAmountOfSuppliedItems() {
		return amountOfSuppliedItems;
	}

	public static void setAmountOfSuppliedItems(Integer amountOfSuppliedItems) {
		Settings.amountOfSuppliedItems = amountOfSuppliedItems;
	}

	public static Integer getCostOfSuplly() {
		return costOfSuplly;
	}

	public static void setCostOfSuplly(Integer costOfSuplly) {
		Settings.costOfSuplly = costOfSuplly;
	}

	public static Integer getAmountOfBottleAtTheEnd() {
		return amountOfBottleAtTheEnd;
	}

	public static void setAmountOfBottleAtTheEnd(Integer amountOfBottleAtTheEnd) {
		Settings.amountOfBottleAtTheEnd = amountOfBottleAtTheEnd;
	}

	public static Integer getMlAtTheEnd() {
		return mlAtTheEnd;
	}

	public static void setMlAtTheEnd(Integer mlAtTheEnd) {
		Settings.mlAtTheEnd = mlAtTheEnd;
	}

	public static Integer getAmountOfPortion() {
		return amountOfPortion;
	}

	public static void setAmountOfPortion(Integer amountOfPortion) {
		Settings.amountOfPortion = amountOfPortion;
	}

	public static Integer getTotalValue() {
		return totalValue;
	}

	public static void setTotalValue(Integer totalValue) {
		Settings.totalValue = totalValue;
	}

}
