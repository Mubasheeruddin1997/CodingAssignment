
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class RecentlyPlayedSongsStore2 {

	private int capacity;
	private Map<String, LinkedList<String>> userSongsMap;

	public RecentlyPlayedSongsStore2(int capacity) {
		this.capacity = capacity;
		this.userSongsMap = new LinkedHashMap<>();
	}

	public void playSong(String user, String song) {
		userSongsMap.putIfAbsent(user, new LinkedList<>());

		LinkedList<String> userSongs = userSongsMap.get(user);
		userSongs.remove(song);
		userSongs.addFirst(song);

		if (userSongs.size() > capacity) {
			userSongs.removeLast();
		}
	}

	public void displayRecentlyPlayedSongs(String user) {
		LinkedList<String> userSongs = userSongsMap.getOrDefault(user, new LinkedList<>());
		System.out.println("Recently played songs for user " + user + ": " + userSongs);
	}

	public static void main(String[] args) {
		int initialCapacity = 3;
		RecentlyPlayedSongsStore2 store = new RecentlyPlayedSongsStore2(initialCapacity);

		store.playSong("User1", "S1");
		store.playSong("User1", "S2");
		store.playSong("User1", "S3");
		store.displayRecentlyPlayedSongs("User1");

		store.playSong("User1", "S4");
		store.displayRecentlyPlayedSongs("User1");
		store.playSong("User1", "S2");
		store.displayRecentlyPlayedSongs("User1");

		store.playSong("User1", "S1");
		store.displayRecentlyPlayedSongs("User1");
	}
}
