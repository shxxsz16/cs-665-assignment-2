/**
 * Name: Zihan Wang
 * Course: CS-665 Software Designs & Patterns
 * Date: 10/20/2026
 * File Name: ShopTest.java
 * Description: JUnit tests: delivery broadcasts to five drivers, observer removal,
 * duplicate registration, request ordering, and requests without observers.
 */
package edu.bu.met.cs665;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import edu.bu.met.cs665.delivery.DeliveryRequest;
import edu.bu.met.cs665.delivery.Driver;
import edu.bu.met.cs665.delivery.ScooterDriver;
import edu.bu.met.cs665.delivery.Shop;
import edu.bu.met.cs665.delivery.TaxiDriver;
import edu.bu.met.cs665.delivery.VanDriver;
import org.junit.Test;


public class ShopTest {

  /**
   * Verify all five drivers receive the same request.
   */
  @Test
  public void testBroadcastToFiveDrivers() {
    Shop shop = new Shop("Fruit Market", "171 Washington St");

    Driver[] drivers = {
        new VanDriver("D1", "Emily"),
        new TaxiDriver("D2", "Tony"),
        new ScooterDriver("D3", "Zai"),
        new VanDriver("D4", "Nan"),
        new TaxiDriver("D5", "Noella")
    };

    for (Driver driver : drivers) {
      shop.registerObserver(driver);
    }

    DeliveryRequest request =
        shop.createDeliveryRequest("R1", "685 Commonwealth Ave");

    for (Driver driver : drivers) {
      assertEquals(1, driver.getReceivedRequests().size());
      assertSame(request, driver.getReceivedRequests().get(0));
    }
  }

  /**
   * Verify that removing a driver stops future notifications.
   */
  @Test
  public void testRemovedDriverReceivesNoNewRequests() {
    Shop shop = new Shop("Fruit Market", "171 Washington St");
    Driver driver = new VanDriver("D1", "Emily");
    shop.registerObserver(driver);

    DeliveryRequest firstRequest =
        shop.createDeliveryRequest("R1", "685 Commonwealth Ave");

    shop.removeObserver(driver);
    shop.createDeliveryRequest("R2", "8 St'Marys St");

    assertEquals(1, driver.getReceivedRequests().size());
    assertSame(firstRequest, driver.getReceivedRequests().get(0));
  }

  /**
   * Verify that registering the same driver twice avoids duplicate notices.
   */
  @Test
  public void testDuplicateRegistration() {
    Shop shop = new Shop("Fruit Market", "171 Washington St");
    Driver driver = new TaxiDriver("D1", "Tony");

    shop.registerObserver(driver);
    shop.registerObserver(driver);

    DeliveryRequest request =
        shop.createDeliveryRequest("R1", "685 Commonwealth Ave");

    assertEquals(1, driver.getReceivedRequests().size());
    assertSame(request, driver.getReceivedRequests().get(0));
  }

  /**
   * Verify requests are received in broadcast order.
   */
  @Test
  public void testMultipleRequestsReceivedInOrder() {
    Shop shop = new Shop("Fruit Market", "171 Washington St");
    Driver driver = new ScooterDriver("D1", "Zai");
    shop.registerObserver(driver);

    DeliveryRequest firstRequest =
        shop.createDeliveryRequest("R1", "685 Commonwealth Ave");
    DeliveryRequest secondRequest =
        shop.createDeliveryRequest("R2", "8 St'Marys St");

    assertEquals(2, driver.getReceivedRequests().size());
    assertSame(firstRequest, driver.getReceivedRequests().get(0));
    assertSame(secondRequest, driver.getReceivedRequests().get(1));
  }

  /**
   * Verify the shop without drivers can still create a valid request.
   */
  @Test
  public void testCreateRequestWithoutDrivers() {
    Shop shop = new Shop("Fruit Market", "171 Washington St");

    DeliveryRequest request =
        shop.createDeliveryRequest("R1", "685 Commonwealth Ave");

    assertEquals("R1", request.getRequestId());
    assertEquals("Fruit Market", request.getShopName());
    assertEquals("171 Washington St", request.getPickupAddress());
    assertEquals("685 Commonwealth Ave", request.getDeliveryAddress());
  }
}