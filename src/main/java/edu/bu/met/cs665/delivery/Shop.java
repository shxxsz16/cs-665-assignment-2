/**
 * Name: Zihan Wang
 * Course: CS-665 Software Designs & Patterns
 * Date: 10/20/2026
 * File Name: Shop.java
 * Description: Manages observers and broadcasts delivery requests.
 */

package edu.bu.met.cs665.delivery;

import java.util.LinkedHashSet;
import java.util.Set;


public class Shop {

  private final String name;
  private final String address;
  private final Set<DeliveryObserver> observers;


  public Shop(String name, String address) {
    this.name = name;
    this.address = address;
    this.observers = new LinkedHashSet<>();
  }

  public void registerObserver(DeliveryObserver observer) {
    observers.add(observer);
  }


  public void removeObserver(DeliveryObserver observer) {
    observers.remove(observer);
  }


  public DeliveryRequest createDeliveryRequest(String requestId, String deliveryAddress) {
    DeliveryRequest request = new DeliveryRequest(requestId, name, address, deliveryAddress);
    notifyObservers(request);
    return request;
  }


  private void notifyObservers(DeliveryRequest request) {
    for (DeliveryObserver observer : observers) {
      observer.update(request);
    }
  }


  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }
}