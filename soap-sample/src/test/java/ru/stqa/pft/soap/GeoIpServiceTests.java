package ru.stqa.pft.soap;

import org.testng.Assert;
import org.testng.annotations.Test;
import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
/**
 * Created by oasmir12 on 17.08.2017.
 */
public class GeoIpServiceTests {
  @Test
  public void testMyIp(){
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("194.28.29.152");
    Assert.assertEquals(geoIP.getCountryCode(), "RUS");
  }
}
