/**
 * Name: Zihan Wang
 * Course: CS-665 Software Designs & Patterns
 * Date: 10/20/2026
 * File Name: Driver.java
 * Description: Stores shared driver information and handles delivery notifications.
 */

package edu.bu.met.cs665.delivery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class Driver implements DeliveryObserver {

  private final String driverId;
  private final String name;
  private final List<DeliveryRequest> receivedRequests;


  protected Driver(String driverId, String name) {
    this.driverId = driverId;
    this.name = name;
    this.receivedRequests = new ArrayList<>();
  }


  @Override
  public void update(DeliveryRequest request) {
    receivedRequests.add(request);
  }

  public String getDriverId() {
    return driverId;
  }

  public String getName() {
    return name;
  }


  
  public List<DeliveryRequest> getReceivedRequests() {
    return Collections.unmodifiableList(receivedRequests);
  }
}