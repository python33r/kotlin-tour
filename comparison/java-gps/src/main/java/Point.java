import java.time.ZonedDateTime;

import static java.lang.Math.abs;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;


public class Point {
  public static final double MIN_LONGITUDE = -180.0;
  public static final double MAX_LONGITUDE = 180.0;
  public static final double MIN_LATITUDE = -90.0;
  public static final double MAX_LATITUDE = 90.0;
  public static final double MEAN_EARTH_RADIUS = 6.371009e+6;

  private final ZonedDateTime time;
  private final double longitude;
  private final double latitude;
  private final double elevation;

  public Point(ZonedDateTime tm, double lon, double lat, double elev) {
    if (lon < MIN_LONGITUDE || lon > MAX_LONGITUDE) {
      throw new IllegalArgumentException("invalid longitude");
    }

    if  (lat < MIN_LATITUDE || lat > MAX_LATITUDE) {
      throw new IllegalArgumentException("invalid latitude");
    }

    time = tm;
    longitude = lon;
    latitude = lat;
    elevation = elev;
  }

  public ZonedDateTime getTime() {
    return time;
  }

  public double getLongitude() {
    return longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getElevation() {
    return elevation;
  }

  @Override
  public String toString() {
    return String.format("(%.5f, %.5f), %.1f m", longitude, latitude, elevation);
  }

  public double distanceTo(Point p) {
    double phi1 = toRadians(getLatitude());
    double phi2 = toRadians(p.getLatitude());

    double lambda1 = toRadians(getLongitude());
    double lambda2 = toRadians(p.getLongitude());
    double delta = abs(lambda1 - lambda2);

    double firstTerm = cos(phi2)*sin(delta);
    double secondTerm = cos(phi1)*sin(phi2) - sin(phi1)*cos(phi2)*cos(delta);
    double top = sqrt(firstTerm*firstTerm + secondTerm*secondTerm);
    double bottom = sin(phi1)*sin(phi2) + cos(phi1)*cos(phi2)*cos(delta);

    return MEAN_EARTH_RADIUS * atan2(top, bottom);
  }
}
