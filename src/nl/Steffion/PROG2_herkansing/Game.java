package nl.Steffion.PROG2_herkansing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Game {
	private Player			player;
	private ArrayList<Room>	rooms;

	public Game() {
		rooms = new ArrayList<>();
		
		Room startRoom = new Room("Start Room");
		rooms.add(startRoom);
		Room room1 = new Room("Room 1");
		rooms.add(room1);
		Room room2 = new Room("Room 2");
		rooms.add(room2);
		Room room3 = new Room("Room 3");
		rooms.add(room3);
		Room room4 = new Room("Room 4");
		rooms.add(room4);
		Room room5 = new Room("Room 5");
		rooms.add(room5);
		Room room6 = new Room("Room 6");
		rooms.add(room6);
		Room room7 = new Room("Room 7");
		rooms.add(room7);
		Room room8 = new Room("Room 8");
		rooms.add(room8);
		Room room9 = new Room("Room 9");
		rooms.add(room9);
		Room room10 = new Room("Room 10");
		rooms.add(room10);
		Room room11 = new Room("Room 11");
		rooms.add(room11);
		Room room12 = new Room("Room 12");
		rooms.add(room12);
		Room room13 = new Room("Room 13");
		rooms.add(room13);
		
		startRoom.setExits(room1, room4, room13, null);
		room1.setExits(room2, null, startRoom, room7);
		room2.setExits(null, room3, room1, null);
		room3.setExits(null, null, null, room2);
		room4.setExits(null, room5, null, startRoom);
		room5.setExits(room6, null, null, room4);
		room6.setExits(null, null, room5, null);
		room7.setExits(null, room1, null, room8);
		room8.setExits(room9, room7, null, null);
		room9.setExits(new Room("random"), null, room8, null);
		room10.setExits(room8, null, room11, null);
		room11.setExits(room10, room12, null, null);
		room12.setExits(null, room13, null, room11);
		room13.setExits(startRoom, null, null, room12);

		Item item1 = new Item("Stick", "You can hit something with this?");
		room7.addItem(item1);
		Item item2 = new Item("Golden Magic Apple",
				"Gold for the famous. Bring this item to the exit to win the game!");
		room3.addItem(item2);
		Item item3 = new Item("Diamond Sword", "This thing should be pretty useful!");
		room5.addItem(item3);
		Item item4 = new Item("Cooked Steak", "The good food, better than McDonalds.");
		room11.addItem(item4);
		Item item5 = new Item("Boots", "Better than walking on your feet.");
		room13.addItem(item5);
		
		player = new Player(startRoom);
		
		run();
	}

	/**
	 * @param command
	 *
	 *            Check if the command can take us to another room. If so: do
	 *            it! Let the caller know if we actually traveled.
	 */
	private boolean checkRoomTravel(String command) {
		Direction direction = null;
		try {
			direction = Direction.valueOf(command.split(" ")[1].toUpperCase());
		} catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
			System.out.println("Please enter a valid direction: north, east, south or west");
			return false;
		}
		
		Room currentRoom = player.getCurrentRoom();

		if (currentRoom.getExits().get(direction) != null) {
			Room goToRoom = currentRoom.getExits().get(direction);

			if (goToRoom.getDescription().equalsIgnoreCase("random")) {
				Random random = new Random();

				System.out
						.println("WOOOSHHHHHH..... You stepped into the portal and got teleported to a random room...");

				goToRoom = rooms.get(random.nextInt(rooms.size()));
			}

			player.goTo(goToRoom);

			if (goToRoom.getDescription().equalsIgnoreCase("Start Room")) {
				for (Item item : player.getBackpack()) {
					if (item.getName().equalsIgnoreCase("Golden Magic Apple")) {
						System.out.println("You won the game! Congratulations.");
						quit();
					}
				}
			}
			return true;
		} else {
			System.out.println("No room in the " + direction.toString().toLowerCase() + " direction.");
			return false;
		}
	}

	/**
	 * @param userInput
	 *            (This is the entire input string from the user.)
	 *
	 *            (Tell others to) Perform the task which belongs to the given
	 *            command.
	 */
	private void handleCommand(String userInput) {
		if (userInput.split(" ").length < 1) return;
		
		switch (userInput.split(" ")[0]) {
			case "go":
				checkRoomTravel(userInput);
				break;
			case "use":
				handleUseCommand(userInput);
				break;
			case "drop":
				handleDropCommand(userInput);
				break;
			case "get":
				handleGetCommand(userInput);
				break;
			case "pack":
				player.printBackpack();
				break;
			case "help":
				printHelp();
				break;
			case "look":
				player.getCurrentRoom().printItems();
				break;
			default:
				System.out.println("No command found \"" + userInput.split(" ")[0]
						+ "\". Use \"help\" for to show a list of commands.");
				break;
		}
	}

	private void handleDropCommand(String userInput) {
		String itemName = Utils.stringBuilder(userInput.split(" "), 1);
		Item item = null;

		for (Item itemLoop : player.getBackpack()) {
			if (itemLoop.getName().equalsIgnoreCase(itemName)) {
				item = itemLoop;
				break;
			}
		}

		if (item == null) {
			System.out.println("No item found in your backpack with the name \"" + itemName + "\"");
			return;
		}

		player.getBackpack().remove(item);
		player.getCurrentRoom().getItems().add(item);
		
		System.out.println("Dropped item: " + item.getName());
	}

	private void handleGetCommand(String userInput) {
		String itemName = Utils.stringBuilder(userInput.split(" "), 1);
		Item item = null;

		for (Item itemLoop : player.getCurrentRoom().getItems()) {
			if (itemLoop.getName().equalsIgnoreCase(itemName)) {
				item = itemLoop;
				break;
			}
		}

		if (item == null) {
			System.out.println("No item found in the room with the name \"" + itemName + "\"");
			return;
		}

		player.getCurrentRoom().getItems().remove(item);
		player.getBackpack().add(item);

		System.out.println("Picked up item: " + item.getName());
	}

	private void handleUseCommand(String userInput) {
		String itemName = Utils.stringBuilder(userInput.split(" "), 1);
		Item item = null;

		for (Item itemLoop : player.getBackpack()) {
			if (itemLoop.getName().equalsIgnoreCase(itemName)) {
				item = itemLoop;
				break;
			}
		}

		if (item == null) {
			for (Item itemLoop : player.getCurrentRoom().getItems()) {
				if (itemLoop.getName().equalsIgnoreCase(itemName)) {
					item = itemLoop;
					break;
				}
			}
		}

		if (item == null) {
			System.out.println("No item found in your backpack or in room with the name \"" + itemName + "\"");
			return;
		}

		System.out.println(item.toString());
	}

	private void printHelp() {
		System.out.println("== Help: Command list ==");
		System.out.println("go <direction>: Go into a direction, valid entries: north, east, south & west");
		System.out.println("use <item>: Use an item in the room or in your backpack");
		System.out.println("drop <item>: Drop an item from your backpack on the ground");
		System.out.println("get <item>: Pickup an item from the ground");
		System.out.println("pack: Display your backpack");
		System.out.println("help: Shows this dialog");
		System.out.println("look: Look around you and show all items around");
	}

	private void quit() {
		System.out.println("Thanks for playing!");
		System.exit(0);
	}

	private void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				String command = br.readLine();

				if (command.equalsIgnoreCase("quit")) {
					quit();
				}

				handleCommand(command);
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getClass() + ": " + e.getLocalizedMessage() + ".");
			System.out.println(e.getStackTrace()[0]);
			System.out.println("Please contact the developer for assistance.");
			run();
		}
	}
}