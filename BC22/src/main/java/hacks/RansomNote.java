package hacks;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {
  static String ARTICOL_ZIAR = "Avand in vedere ca prin media online circula zvonul ca Ana si Gigel Punem in";
  static String TEXT_AMENINTARE = "Punem in vedere in in";

  public static void main(String[] args) {
    System.out.println(checkTextPossible1());
  }

  static boolean checkTextPossible1() {
    String[] cuvinteTextAmenintare = TEXT_AMENINTARE.split(" ");
    String[] cuvinteZiar = ARTICOL_ZIAR.split(" ");
    // if(cuvinteZiar.length < cuvinteTextAmenintare.length) {
    // return false;
    // }
    Map<String, Integer> textAmenintareMap = new HashMap<>();
    Map<String, Integer> textZiarMap = new HashMap<>();
    ///////////////
    for (String cuvant : cuvinteTextAmenintare) {
      if (textAmenintareMap.containsKey(cuvant)) {
        Integer numarAparitii = textAmenintareMap.get(cuvant);
        textAmenintareMap.put(cuvant, new Integer(numarAparitii.intValue() + 1));
      } else {
        textAmenintareMap.put(cuvant, new Integer(1));
      }
    }
    ////////////////////
    for (String cuvant : cuvinteZiar) {
      if (textZiarMap.containsKey(cuvant)) {
        Integer numarAparitii = textZiarMap.get(cuvant);
        textZiarMap.put(cuvant, new Integer(numarAparitii.intValue() + 1));
      } else {
        textZiarMap.put(cuvant, new Integer(1));
      }
    }

    for (String cuvantMenintare : textAmenintareMap.keySet()) {
      if(!textZiarMap.containsKey(cuvantMenintare)) {
        return false;
      }
      Integer numarAparitiiInZiar = textZiarMap.get(cuvantMenintare);
      Integer numarAparitiiInTextAmenintare = textAmenintareMap.get(cuvantMenintare);
      if(numarAparitiiInZiar.intValue() < numarAparitiiInTextAmenintare.intValue()) {
        // numarAparitiiInZiar.compareTo(numarAparitiiInTextAmenintare) < 0
        return false;
      }
    }
    return true;
  }

}
