package co.edu.uptc.controllers;

import java.util.ArrayList;

import co.edu.uptc.services.*;

public class Control {

    private String StringtoCapitals(String string,String separator) {
        StringBuilder stringtoCapitals = new StringBuilder();
        String[] cads = string.split(separator);

        for (String cad : cads) {
            if (cad.isEmpty()==false) {
                stringtoCapitals.append(Character.toUpperCase(cad.charAt(0)));
                stringtoCapitals.append(cad.substring(1).toLowerCase());
                stringtoCapitals.append(separator);
            }
        }

        return stringtoCapitals.toString();
    }

    public void run(int numberOfNames, int numberOfLastNames, String separator, char modification) {
        GenerateStrings gs = new GenerateStrings();
        PropiertiesService ps= new PropiertiesService();
        gs.setNumberOfNames(numberOfNames);
        gs.setNumberOfLastNames(numberOfLastNames);
        gs.setNamesPath(ps.readProperties("namesPath"));
        gs.setLastNamesPath(ps.readProperties("lastNamesPath"));
        gs.readData();
        gs.setSeparator(separator);
        ArrayList<String> cads = gs.generateAllCombinations();
        switch (modification) {
            case 'A':
                ArrayList<String> auxString1 = new ArrayList<>();
                for (String string : cads) {
                    auxString1.add(string.toUpperCase());
                }
                gs.saveData(auxString1);
                System.out.println("Ya he terminado");
                break;
            case 'a':
                ArrayList<String> auxString2 = new ArrayList<>();
                for (String string : cads) {
                    auxString2.add(string.toLowerCase());
                }
                gs.saveData(auxString2);
                System.out.println("Ya he terminado");
                break;
            case 'C':
                ArrayList<String> auxString3 = new ArrayList<>();
                for (String string : cads) {
                    auxString3.add(this.StringtoCapitals(string, separator));
                }
                gs.saveData(auxString3);
                System.out.println("Ya he terminado");
                break;
            default:
                gs.saveData(cads);
                System.out.println("Ya he terminado");
                break;
        }
    }
}