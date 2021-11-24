package com.program.practice.bank.account.util;

import java.util.HashMap;

import java.util.Map;

public class VehicleUtils {

   static Map<VehicleType,Integer> rateMap = new HashMap<>();
    static {
        rateMap.put(VehicleType.SUV,15);
        rateMap.put(VehicleType.SEDAN,12);
        rateMap.put(VehicleType.HATCHBACK,10);
        rateMap.put(VehicleType.BIKE,3);
    }

  public static Integer getRate(VehicleType vehicleType){
      return  rateMap.get(vehicleType);
    }
}
