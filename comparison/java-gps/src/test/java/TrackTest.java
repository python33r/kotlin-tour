import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class TrackTest {

  private static final double TOLERANCE = 1e-5;

  private ZonedDateTime t1;
  private ZonedDateTime t2;
  private ZonedDateTime t3;
  private ZonedDateTime t4;

  private Point p1;
  private Point p2;
  private Point p3;
  private Point p4;

  private Track emptyTrack, trackOnePoint, trackFourPoints;

  @BeforeEach
  public void perTestSetup() {
    t1 = ZonedDateTime.parse("2022-02-17T09:52:39Z");
    t2 = ZonedDateTime.parse("2022-02-17T09:53:31Z");
    t3 = ZonedDateTime.parse("2022-02-17T09:54:29Z");
    t4 = ZonedDateTime.parse("2022-02-17T09:55:31Z");

    p1 = new Point(t1, -1.547720, 53.803941, 69.8);
    p2 = new Point(t2, -1.548531, 53.804616, 72.5);
    p3 = new Point(t3, -1.549418, 53.805238, 68.1);
    p4 = new Point(t4, -1.550828, 53.805469, 70.5);

    emptyTrack = new Track();

    trackOnePoint = new Track();
    trackOnePoint.add(p1);

    trackFourPoints = new Track();
    trackFourPoints.add(p1);
    trackFourPoints.add(p2);
    trackFourPoints.add(p3);
    trackFourPoints.add(p4);
  }

  @Test
  @DisplayName("Size of a Track returned correctly")
  public void trackSize() {
    assertAll(
      () -> assertThat(emptyTrack.size(), is(0)),
      () -> assertThat(trackFourPoints.size(), is(4))
    );
  }

  @Test
  @DisplayName("Points stored in and retrieved from a Track correctly")
  public void getPoint() {
    assertAll(
      () -> assertThat(trackFourPoints.get(0).getTime(), is(t1)),
      () -> assertThat(trackFourPoints.get(3).getTime(), is(t4))
    );
  }

  @Test
  @DisplayName("Exception when retrieving from an empty Track")
  public void getPointEmptyDataset() {
    assertThrows(IndexOutOfBoundsException.class, () -> emptyTrack.get(0));
  }

  @Test
  @DisplayName("Exception if index is too low")
  public void pointIndexTooLow() {
    assertThrows(IndexOutOfBoundsException.class, () -> trackFourPoints.get(-1));
  }

  @Test
  @DisplayName("Exception if index is too high")
  public void pointIndexTooHigh() {
    assertThrows(IndexOutOfBoundsException.class, () -> trackFourPoints.get(4));
  }

  @Test
  @DisplayName("Appropriate exception if data file does not exist")
  public void readFileNotFound() {
    Throwable t = assertThrows(IOException.class, () -> new Track("this_file_does_not_exist"));
    boolean fileNotFound = t.getClass().equals(FileNotFoundException.class);
    boolean noSuchFile = t.getClass().equals(NoSuchFileException.class);
    assertThat(fileNotFound || noSuchFile, is(true));
  }

  @Test
  @DisplayName("Exception if data file has fewer than 4 columns")
  public void readMissingData() {
    assertThrows(IOException.class, () -> new Track("data/bad.csv"));
  }

  @Test
  @DisplayName("Points read into a Track correctly")
  public void readFile() throws IOException {
    Track track = new Track("data/test.csv");
    assertAll(
      () -> assertThat(track.size(), is(4)),
      () -> assertThat(track.get(0).getElevation(), closeTo(69.8, TOLERANCE)),
      () -> assertThat(track.get(1).getElevation(), closeTo(72.5, TOLERANCE))
    );
  }

  @Test
  @DisplayName("Old data cleared when reading a new file")
  public void readClearOldData() throws IOException {
    trackFourPoints.readFile("data/test.csv");
    assertThat(trackFourPoints.size(), is(4));
  }

  @Test
  @DisplayName("Lowest point of a Track found correctly")
  public void lowestPoint() {
    assertThat(trackFourPoints.lowestPoint(), is(p3));
  }

  @Test
  @DisplayName("Exception if seeking lowest point in an empty Track")
  public void lowestPointNotEnoughData() {
    assertThrows(DataException.class, () -> emptyTrack.lowestPoint());
  }

  @Test
  @DisplayName("Highest point of a Track found correctly")
  public void highestPoint() {
    assertThat(trackFourPoints.highestPoint(), is(p2));
  }

  @Test
  @DisplayName("Exception if seeking highest point in an empty Track")
  public void highestPointNotEnoughData() {
    assertThrows(DataException.class, () -> emptyTrack.highestPoint());
  }

  @Test
  @DisplayName("Total distance computed correctly")
  public void totalDistance() {
    assertThat(trackFourPoints.totalDistance(), closeTo(278.53495, TOLERANCE));
  }

  @Test
  @DisplayName("Total distance is 0 if fewer than 2 points in Track")
  public void totalDistanceNotEnoughData() {
    assertThat(trackOnePoint.totalDistance(), closeTo(0, 0.00001));
  }

  @Test
  @DisplayName("Average speed computed correctly")
  public void averageSpeed() {
    assertThat(trackFourPoints.averageSpeed(), closeTo(1.61939, TOLERANCE));
  }

  @Test
  @DisplayName("Exception if fewer than 2 points when computing average speed")
  public void averageSpeedNotEnoughData() {
    assertThrows(DataException.class, trackOnePoint::averageSpeed);
  }
}
