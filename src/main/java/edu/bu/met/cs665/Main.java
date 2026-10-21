/**
 * Name: FIRST_NAME Zihan Wang
 * Course: CS-665 Software Designs & Patterns
 * Date: 10/20/2001
 * File Name: Main.java
 * Description: create 5 drivers and demonstrates delivery notifications to them
 */

package edu.bu.met.cs665;

import edu.bu.met.cs665.delivery.DeliveryRequest;
import edu.bu.met.cs665.delivery.Driver;
import edu.bu.met.cs665.delivery.ScooterDriver;
import edu.bu.met.cs665.delivery.Shop;
import edu.bu.met.cs665.delivery.TaxiDriver;
import edu.bu.met.cs665.delivery.VanDriver;

/**
 * create 5 drivers and demonstrates delivery notifications to them
 */
public class Main {

  public static void main(String[] args) {
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

    System.out.println("Delivery request: " + request.getRequestId());
    System.out.println("Shop: " + request.getShopName());
    System.out.println("Pickup: " + request.getPickupAddress());
    System.out.println("Destination: " + request.getDeliveryAddress());
    System.out.println();

    for (Driver driver : drivers) {
      for (DeliveryRequest received : driver.getReceivedRequests()) {
        System.out.println(
            driver.getDriverId() + " - " + driver.getName()
                + " received request " + received.getRequestId());
      }
    }
  }
}