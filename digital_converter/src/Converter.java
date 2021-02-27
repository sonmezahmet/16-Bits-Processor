import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Converter {

    public static void main(String[] args) {
        String output="";
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] instruction = myReader.nextLine().split(" ");
                String[] registers = instruction[1].split(",");
                String binary = "";
                String dest;
                String register1;
                String imm;
                String register2;
                String hexStr;
                String addr;
                int decimal;
                switch (instruction[0]) {
                    case "ADD":
                        binary += "000";
                        dest = decToBin(Character.getNumericValue(registers[0].charAt(1)), 3);
                        binary += dest;
                        register1 = decToBin(Character.getNumericValue(registers[1].charAt(1)), 3);
                        binary += register1;
                        register2 = decToBin(Character.getNumericValue(registers[2].charAt(1)), 3);
                        binary += register2;
                        binary += "0000";
                        decimal = Integer.parseInt(binary, 2);
                        hexStr = Integer.toString(decimal, 16);
                        output+=hexStr+" ";
                        break;
                    case "ADDI":
                        binary += "001";
                        dest = decToBin(Character.getNumericValue(registers[0].charAt(1)), 3);
                        binary += dest;
                        register1 = decToBin(Character.getNumericValue(registers[1].charAt(1)), 3);
                        binary += register1;
                        imm = decToBin(Integer.parseInt(registers[2]), 7);
                        binary += imm;
                        decimal = Integer.parseInt(binary, 2);
                        hexStr = Integer.toString(decimal, 16);
                        output+=hexStr+" ";
                        break;
                    case "AND":
                        binary += "010";
                        dest = decToBin(Character.getNumericValue(registers[0].charAt(1)), 3);
                        binary += dest;
                        register1 = decToBin(Character.getNumericValue(registers[1].charAt(1)), 3);
                        binary += register1;
                        register2 = decToBin(Character.getNumericValue(registers[2].charAt(1)), 3);
                        binary += register2;
                        binary += "0000";
                        decimal = Integer.parseInt(binary, 2);
                        hexStr = Integer.toString(decimal, 16);
                        output+=hexStr+" ";
                        break;
                    case "ANDI":
                        binary += "011";
                        dest = decToBin(Character.getNumericValue(registers[0].charAt(1)), 3);
                        binary += dest;
                        register1 = decToBin(Character.getNumericValue(registers[1].charAt(1)), 3);
                        binary += register1;
                        imm = decToBin(Integer.parseInt(registers[2]), 7);
                        binary += imm;
                        decimal = Integer.parseInt(binary, 2);
                        hexStr = Integer.toString(decimal, 16);
                        output+=hexStr+" ";
                        break;
                    case "JUMP":
                        binary += "100";
                        addr = decToBin(Integer.parseInt(registers[0]), 9);
                        binary += addr + "0000";
                        decimal = Integer.parseInt(binary, 2);
                        hexStr = Integer.toString(decimal, 16);
                        output+=hexStr+" ";
                        break;
                    case "CMP":
                        binary += "111";
                        register1 = decToBin(Character.getNumericValue(registers[0].charAt(1)), 3);
                        binary += register1;
                        register2 = decToBin(Character.getNumericValue(registers[1].charAt(1)), 3);
                        binary += register2;
                        binary += "0000000";
                        decimal = Integer.parseInt(binary, 2);
                        hexStr = Integer.toString(decimal, 16);
                        output+=hexStr+" ";
                        break;
                    case "ST":
                        binary += "110";
                        register1 = decToBin(Character.getNumericValue(registers[0].charAt(1)), 3);
                        binary += register1;
                        addr = decToBin(Integer.parseInt(registers[1]), 10);
                        binary += addr;
                        decimal = Integer.parseInt(binary, 2);
                        hexStr = Integer.toString(decimal, 16);
                        output+=hexStr+" ";
                        break;
                    case "LD":
                        binary += "101";
                        register1 = decToBin(Character.getNumericValue(registers[0].charAt(1)), 3);
                        binary += register1;
                        addr = decToBin(Integer.parseInt(registers[1]), 10);
                        binary += addr;
                        decimal = Integer.parseInt(binary, 2);
                        hexStr = Integer.toString(decimal, 16);
                        output+=hexStr+" ";
                        break;
                    case "JE":
                        binary += "100";
                        addr = decToBin(Integer.parseInt(registers[0]), 9);
                        binary += addr + "0001";
                        decimal = Integer.parseInt(binary, 2);
                        hexStr = Integer.toString(decimal, 16);
                        output+=hexStr+" ";
                        break;
                    case "JA":
                        binary += "100";
                        addr = decToBin(Integer.parseInt(registers[0]), 9);
                        binary += addr + "0010";
                        decimal = Integer.parseInt(binary, 2);
                        hexStr = Integer.toString(decimal, 16);
                        output+=hexStr+" ";
                        break;
                    case "JB":
                        binary += "100";
                        addr = decToBin(Integer.parseInt(registers[0]), 9);
                        binary += addr + "0011";
                        decimal = Integer.parseInt(binary, 2);
                        hexStr = Integer.toString(decimal, 16);
                        output+=hexStr+" ";
                        break;
                    case "JBE":
                        binary += "100";
                        addr = decToBin(Integer.parseInt(registers[0]), 9);
                        binary += addr + "0100";
                        decimal = Integer.parseInt(binary, 2);
                        hexStr = Integer.toString(decimal, 16);
                        output+=hexStr+" ";
                        break;
                    case "JAE":
                        binary += "100";
                        addr = decToBin(Integer.parseInt(registers[0]), 9);
                        binary += addr + "0101";
                        decimal = Integer.parseInt(binary, 2);
                        hexStr = Integer.toString(decimal, 16);
                        output+=hexStr;
                        break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("output.txt");
            myWriter.write(output);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String decToBin(int dec, int controlNumber) {
        String dest;
        if (dec < 0) {
            dec = dec * -1;
            dest = Integer.toBinaryString(dec);
            int[] destCharArray = new int[dest.length()];
            for (int i = 0; i < dest.length(); i++) {
                if (dest.charAt(i)=='0'){
                    destCharArray[i]=1;
                }else {
                    destCharArray[i]=0;
                }
            }
            dest="";
            for (int i = 0; i < destCharArray.length; i++) {
                dest+=(destCharArray[i]+"");
            }
            int decimalDest = Integer.parseInt(dest, 2) + 1;
            dest = decToBin(decimalDest,dest.length());
            dest = "1"+dest;
            String control = "";
            for (int i = 0; i < controlNumber - dest.length(); i++) {
                control += "1";
            }
            dest = control + dest;
        } else {
            dest = Integer.toBinaryString(dec);
            String control = "";
            for (int i = 0; i < controlNumber - dest.length(); i++) {
                control += "0";
            }
            dest = control + dest;
        }
        return dest;
    }



}
