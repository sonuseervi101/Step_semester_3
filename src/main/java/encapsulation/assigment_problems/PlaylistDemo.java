package encapsulation.assigment_problems;

import java.util.Arrays;

class Playlist {
    private final String[] songs;   // the REAL playlist, never handed out directly
    private int songCount;

    public Playlist(int maxSongs) {
        songs = new String[maxSongs];
        songCount = 0;
    }

    public void addSong(String title) {
        if (songCount >= songs.length) {
            System.out.println("Rejected: playlist is full");
            return;
        }
        songs[songCount] = title;
        songCount++;
    }

    // Returns a NEW array with the same songs: a safe copy
    public String[] getSongs() {
        return Arrays.copyOf(songs, songCount);
    }

    public int getSongCount() {
        return songCount;
    }
}

public class PlaylistDemo {
    public static void main(String[] args) {
        Playlist p = new Playlist(10);
        p.addSong("Song A");
        p.addSong("Song B");

        System.out.println("Playlist: " + Arrays.toString(p.getSongs()));

        String[] copy = p.getSongs();
        copy[0] = "Hacked";   // try to tamper with the playlist from outside

        System.out.println("Modified copy: " + Arrays.toString(copy));
        System.out.println("Real playlist: " + Arrays.toString(p.getSongs()));
        System.out.println("Song count: " + p.getSongCount());
    }
}