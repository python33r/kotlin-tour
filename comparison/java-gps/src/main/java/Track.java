import java.io.IOException;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Track {
  private static final int NUM_CSV_COLUMNS = 4;
  private final List<Point> points;

  public Track() {
    points = new ArrayList<>();
  }

  public Track(String filename) throws IOException {
    points = new ArrayList<>();
    readFile(filename);
  }

  public void readFile(String filename) throws IOException {

    try (Scanner input = new Scanner(Paths.get(filename))) {
      if (!points.isEmpty()) {
        points.clear();
      }

      while (input.hasNextLine()) {
        String line = input.nextLine();

        if (line.startsWith("Time,")) {
          continue;
        }

        String[] parts = line.split(",");
        if (parts.length != NUM_CSV_COLUMNS) {
          throw new DataException("invalid file format");
        }

        ZonedDateTime time = ZonedDateTime.parse(parts[0]);
        double lon = Double.parseDouble(parts[1]);
        double lat = Double.parseDouble(parts[2]);
        double elev = Double.parseDouble(parts[3]);

        points.add(new Point(time, lon, lat, elev));
      }
    }
  }

  public int size() {
    return points.size();
  }

  public Point get(int index) {
    return points.get(index);
  }

  public void add(Point p) {
    points.add(p);
  }

  public Point lowestPoint() {
    requireMinSize(1);

    Point lowest = points.getFirst();

    for (Point p: points) {
      if (p.getElevation() < lowest.getElevation()) {
        lowest = p;
      }
    }

    return lowest;
  }

  public Point highestPoint() {
    requireMinSize(1);

    Point highest = points.getFirst();

    for (Point p: points) {
      if (p.getElevation() > highest.getElevation()) {
        highest = p;
      }
    }

    return highest;
  }

  public double totalDistance() {
    double distance = 0.0;

    if (points.size() > 1) {
      for (int i = 1; i < points.size(); ++i) {
        Point thisPoint = points.get(i);
        Point previousPoint = points.get(i - 1);
        distance += thisPoint.distanceTo(previousPoint);
      }
    }

    return distance;
  }

  public double averageSpeed() {
    requireMinSize(2);
    long time = ChronoUnit.SECONDS.between(
      points.getFirst().getTime(), points.getLast().getTime());
    return totalDistance() / time;
  }

  private void requireMinSize(int minPoints) {
    if (points.size() < minPoints) {
      String message = String.format(
        "insufficient data (minimum number of points is %d)", minPoints);
      throw new DataException(message);
    }
  }
}
