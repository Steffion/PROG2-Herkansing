package nl.Steffion.PROG2_herkansing;

import java.util.ArrayList;
import java.util.HashMap;

public class Room {
	private String						description;
	private HashMap<Direction, Room>	exits;
	private ArrayList<Item>				items;

	public Room(String description) {
		this.description = description;
		items = new ArrayList<>();
		exits = new HashMap<>();
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public String getDescription() {
		return description;
	}

	public HashMap<Direction, Room> getExits() {
		return exits;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void printItems() {
		System.out.println("== Items in room ==");
		if (items.isEmpty()) System.out.println("No items around...");

		for (Item item : items)
			System.out.println(item.toString());
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void setExits(Room northExit, Room eastExit, Room southExit, Room westExit) {
		exits.put(Direction.NORTH, northExit);
		exits.put(Direction.EAST, eastExit);
		exits.put(Direction.SOUTH, southExit);
		exits.put(Direction.WEST, westExit);
	}
	
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public void printInfo() {
		System.out.println("== Room ==");
		System.out.println("Current room: " + description);

		String exits = "";
		for (Direction direction : Direction.values())
			if (this.exits.get(direction) != null) exits += direction.toString().toLowerCase() + ", ";
		
		System.out.println("Exits on: " + exits.substring(0, exits.length() - 2));

		if (!items.isEmpty()) {
			String items = "";
			for (Item item : this.items)
				items += item.getName() + ", ";
			
			System.out.println("Items in room: " + items.substring(0, items.length() - 2));
		}		
	}
}
