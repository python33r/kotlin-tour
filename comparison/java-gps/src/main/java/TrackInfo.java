public class TrackInfo {
  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("Usage: java TrackInfo <filename>");
      System.exit(1);
    }

    try {
      Track track = new Track(args[0]);
      System.out.printf("%d points in track\n", track.size());

      Point lowest = track.lowestPoint();
      Point highest = track.highestPoint();
      System.out.printf("Lowest point is %s\n", lowest);
      System.out.printf("Highest point is %s\n", highest);

      double distKm = track.totalDistance() / 1000.0;
      System.out.printf("Total distance = %.3f km\n", distKm);

      double avgSpeed = track.averageSpeed();
      System.out.printf("Average speed = %.3f m/s\n", avgSpeed);
    }
    catch (Exception error) {
      System.err.println("Error: " + error.getMessage());
      System.exit(2);
    }
  }
}
