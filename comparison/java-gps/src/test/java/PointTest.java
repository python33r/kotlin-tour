import java.time.ZonedDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class PointTest {

  private static final double TOLERANCE = 1e-5;
  private static final double HALF_CIRCUMFERENCE = Math.PI * Point.MEAN_EARTH_RADIUS;

  private ZonedDateTime time;
  private Point point;

  @BeforeEach
  public void perTestSetup() {
    time = ZonedDateTime.now();
    point = new Point(time, 1.5, 50.0, 25.0);
  }

  @Test
  @DisplayName("Time stored and retrieved correctly")
  public void time() {
    assertThat(point.getTime(), is(time));
  }

  @Test
  @DisplayName("Longitude stored and retrieved correctly")
  public void longitude() {
    assertThat(point.getLongitude(), closeTo(1.5, TOLERANCE));
  }

  @Test
  @DisplayName("Latitude stored and retrieved correctly")
  public void latitude() {
    assertThat(point.getLatitude(), closeTo(50.0, TOLERANCE));
  }

  @Test
  @DisplayName("Exception if longitude is too low")
  public void longitudeTooLow() {
    assertThrows(IllegalArgumentException.class, () -> new Point(time, -180.5, 0.0, 0.0));
  }

  @Test
  @DisplayName("Exception if longitude is too high")
  public void longitudeTooHigh() {
    assertThrows(IllegalArgumentException.class, () -> new Point(time, 180.5, 0.0, 0.0));
  }

  @Test
  @DisplayName("Exception if longitude is too low")
  public void latitudeTooLow() {
    assertThrows(IllegalArgumentException.class, () -> new Point(time, 0.0, -90.5, 0.0));
  }

  @Test
  @DisplayName("Exception if latitude is too high")
  public void latitudeTooHigh() {
    assertThrows(IllegalArgumentException.class, () -> new Point(time, 0.0, 90.5, 0.0));
  }

  @Test
  @DisplayName("Elevation stored and retrieved correctly")
  public void elevation() {
    assertThat(point.getElevation(), closeTo(25.0, TOLERANCE));
  }

  @Test
  @DisplayName("Correct string representation of a Point")
  public void pointAsString() {
    assertThat(point.toString(), is("(1.50000, 50.00000), 25.0 m"));
  }

  @Test
  @DisplayName("Correct distance between poles")
  public void distanceBetweenPoles() {
    Point northPole = new Point(time, 0.0, 90.0, 0.0);
    Point southPole = new Point(time, 0.0, -90.0, 0.0);
    assertAll(
      () -> assertThat(northPole.distanceTo(northPole), closeTo(0.0, TOLERANCE)),
      () -> assertThat(northPole.distanceTo(southPole), closeTo(HALF_CIRCUMFERENCE, TOLERANCE))
    );
  }

  @Test
  @DisplayName("Correct distance between opposing points on equator")
  public void distanceBetweenOpposingPointsOnEquator() {
    Point p1 = new Point(time, 0.0, 0.0, 0.0);
    Point p2 = new Point(time, 180.0, 0.0, 0.0);
    Point p3 = new Point(time, -180.0, 0.0, 0.0);
    assertAll(
      () -> assertThat(p1.distanceTo(p1), closeTo(0.0, TOLERANCE)),
      () -> assertThat(p1.distanceTo(p2), closeTo(HALF_CIRCUMFERENCE, TOLERANCE)),
      () -> assertThat(p1.distanceTo(p3), closeTo(HALF_CIRCUMFERENCE, TOLERANCE)),
      () -> assertThat(p2.distanceTo(p3), closeTo(0.0, TOLERANCE))
    );
  }
}
