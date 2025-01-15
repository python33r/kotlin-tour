import static java.lang.System.out;

public class TrackInfo {
  public static void main(String[] args) {
    if (args.length != 1) {
      out.println("Error: filename required on command line");
      System.exit(1);
    }

    try {
      Track track = new Track(args[0]);
      out.printf("Points in track: %d\n", track.size());

      Point lowest = track.lowestPoint();
      Point highest = track.highestPoint();
      out.printf("Lowest point is %s\n", lowest);
      out.printf("Highest point is %s\n", highest);

      double distKm = track.totalDistance() / 1000.0;
      out.printf("Total distance travelled = %.3f km\n", distKm);

      double avgSpeed = track.averageSpeed();
      out.printf("Average speed = %.1f m/s\n", avgSpeed);
    }
    catch (Exception error) {
      out.println("Error: " + error.getMessage());
      System.exit(2);
    }
  }
}
