package nl.Steffion.PROG2_herkansing;

import java.util.ArrayList;

public class Player {
	private ArrayList<Item>	backpack;
	private Room			currentRoom;

	public Player(Room currentRoom) {
		backpack = new ArrayList<>();
		setCurrentRoom(currentRoom);
	}

	public ArrayList<Item> getBackpack() {
		return backpack;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void goTo(Room goToRoom) {
		setCurrentRoom(goToRoom);
	}

	public void printBackpack() {
		System.out.println("== Backpack ==");
		if (backpack.isEmpty()) System.out.println("Empty...");
		
		for (Item item : backpack)
			System.out.println(item.toString());
	}

	public void setBackpack(ArrayList<Item> backpack) {
		this.backpack = backpack;
	}
	
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
		this.currentRoom.printInfo();
	}
}
