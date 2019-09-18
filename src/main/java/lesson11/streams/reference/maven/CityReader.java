package lesson11.streams.reference.maven;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class CityReader {
    public static void main(String[] args) {
        City city17 = new City();
        CityReader cityFile = new CityReader(Lesson11.fileAddress2());
        cityFile.loader(city17);


    }
    private String cityFileAddress;

    CityReader(String cityFileAddress) {
        this.cityFileAddress = cityFileAddress;
    }

    private int readCondition;

    private String foundSector = "";
    private String foundName = "";
    private String foundID = "";
    private String foundLength = "";
    private String foundWidth = "";
    private int intLength = 0;
    private int intWidth = 0;

    void loader(City cityObj) {
        try (BufferedReader bfCityReader = new BufferedReader
                (new FileReader(cityFileAddress))) {

            List<String> yy = bfCityReader.lines()
                    .filter(line -> line.endsWith(","))
                    .collect(Collectors.toList());

            System.out.println(yy.get(9));



//            do {
//                if (!sectorFinder(bfCityReader)) continue;
//                nameReader(bfCityReader);
//                lengthReader(bfCityReader);
//                widthReader(bfCityReader);
//                addSector(cityObj);
//
//                do{
//                    if(!damageFinder(bfCityReader))continue;
//                    lengthReader(bfCityReader);
//                    widthReader(bfCityReader);
//                    addDamage(cityObj);
//                }while (readCondition != '-');
//                namesReset();
//            } while (readCondition != -1);

        } catch (IOException exc) {
            System.out.println("File access error!");
        }
    }

    private boolean sectorFinder(BufferedReader bf) throws IOException {
        readCondition = bf.read();
        if (readCondition == '#') {
            readCondition = bf.read();//skip '#'
            sectorReader(bf);
            return true;
        }
        return false;
    }
    private boolean damageFinder(BufferedReader bf) throws IOException {
        readCondition = bf.read();
        if(readCondition == 'D'){
            while (readCondition != ' ') {
                readCondition = bf.read();
            }
            return true;
        }
        return false;
    }

    private void sectorReader(BufferedReader bf) throws IOException {
        while (readCondition != ' ') {
            foundSector += (char) readCondition;
            readCondition = bf.read();
        }
        successFindReport(foundSector);
    }
    private void nameReader(BufferedReader bf) throws IOException {
        readCondition = bf.read(); //skip space
        while (readCondition != ' ') {
            foundName += (char) readCondition;
            readCondition = bf.read();
        }
        successFindReport(foundName);
    }
    private void lengthReader(BufferedReader bf) throws IOException {
        readCondition = bf.read(); //skip space
        while (readCondition != ' ') {
            foundLength += (char) readCondition;
            readCondition = bf.read();
        }
        successFindReport(foundLength);
        intLength = Integer.parseInt(foundLength);
    }
    private void widthReader(BufferedReader bf) throws IOException {
        readCondition = bf.read(); //skip space
        while (readCondition != '.') {
            foundWidth += (char) readCondition;
            readCondition = bf.read();
        }
        successFindReport(foundWidth);
        intWidth = Integer.parseInt(foundWidth);
    }

//    private void addSector(City cityObj) {
//        switch (foundSector) {
//            case "Park":
//                cityObj.addSector(new Park(foundName, intLength, intWidth));
//                successAddReport(foundSector);
//                sizesReset();
//                break;
//
//            case "Street":
//                cityObj.addSector(new Street(foundName, intLength, intWidth));
//                successAddReport(foundSector);
//                sizesReset();
//                break;
//
//            case "LivingDistrict":
//                cityObj.addSector(new LivingDistrict(foundName, intLength, intWidth));
//                successAddReport(foundSector);
//                sizesReset();
//                break;
//            default:
//                System.out.println("Sector not found!");
//        }
//    }
//    private void addDamage(City cityObj) {
//        switch (foundSector) {
//            case "Park":
//                City.addDamage(cityObj.getCityParks(),foundName,intLength,intWidth);
//                successAddReport("Damage in "+foundSector);
//                sizesReset();
//                break;
//
//            case "Street":
//                City.addDamage(cityObj.getCityStreets(),foundName,intLength,intWidth);
//                successAddReport("Damage in "+foundSector);
//                sizesReset();
//                break;
//
//            case "LivingDistrict":
//                City.addDamage(cityObj.getCityDistricts(),foundName,intLength,intWidth);
//                successAddReport("Damage in "+foundSector);
//                sizesReset();
//                break;
//            default:
//                System.out.println("Sector not found!");
//        }
//    }

    private void sizesReset(){
        foundLength = "";
        foundWidth = "";
        intLength = 0;
        intWidth = 0;
    }
    private void namesReset(){
        foundSector = "";
        foundName = "";
        foundID = "";
        System.out.println();
    }

    private void successAddReport(String sector){
        System.out.println("\n * Sector " + sector + " is successful added(" +
                foundSector + " " + foundName + " " + intLength + " " + intWidth + ")");
    }
    private void successFindReport(String who){
        System.out.print(" ^ found:" + who);
    }
}