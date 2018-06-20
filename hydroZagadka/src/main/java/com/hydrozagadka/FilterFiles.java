package com.hydrozagadka;

public class FilterFiles {
    private History historyFiles;
    private LoadFile loadFile;

   public String sortByProvince(LoadFile loadedFile){
       if(loadedFile.getAllContainers().containsValue(loadedFile.getWaterContainer().getProvince())) {
           //code
       }
       return null;
   }
}
