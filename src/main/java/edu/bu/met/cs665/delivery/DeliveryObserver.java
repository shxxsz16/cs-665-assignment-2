/**
 * Name: Zihan Wang
 * Course: CS-665 Software Designs & Patterns
 * Date: 10/20/2026
 * File Name: DeliveryObserver.java
 * Description: Defines how an observer receives delivery notifications.
 */

package edu.bu.met.cs665.delivery;


public interface DeliveryObserver {

  void update(DeliveryRequest request);
}