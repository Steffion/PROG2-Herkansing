package nl.Steffion.PROG2_herkansing;

public class Item {
	private String	name;
	private String	usageText;
	
	public Item(String name, String usageText) {
		this.name = name;
		this.usageText = usageText;
	}
	
	public String getName() {
		return name;
	}
	
	public String getUsageText() {
		return usageText;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUsageText(String usageText) {
		this.usageText = usageText;
	}
	
	@Override
	public String toString() {
		return name + ": " + usageText;
	}
}
