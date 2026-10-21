/**
 * Name: Zihan Wang
 * Course: CS-665 Software Designs & Patterns
 * Date: 10/20/2026
 * File Name: DeliveryRequest.java
 * Description: Stores delivery request information
 */

package edu.bu.met.cs665.delivery;
/**
 * Initializes the delivery request information.
 */
public class DeliveryRequest {

  private final String requestId;
  private final String shopName;
  private final String pickupAddress;
  private final String deliveryAddress;


  public DeliveryRequest(String requestId, String shopName, 
  String pickupAddress, String deliveryAddress) {
    this.requestId = requestId;
    this.shopName = shopName;
    this.pickupAddress = pickupAddress;
    this.deliveryAddress = deliveryAddress;
  }

  public String getRequestId() {
    return requestId;
  }

  public String getShopName() {
    return shopName;
  }

  public String getPickupAddress() {
    return pickupAddress;
  }

  public String getDeliveryAddress() {
    return deliveryAddress;
  }
}