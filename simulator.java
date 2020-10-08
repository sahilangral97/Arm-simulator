import org.omg.CORBA.INTERNAL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
class armsim{
    int address=1500;
    int reg[]=new int[16];
    //reg[13]=0;
    int mem[]=new int[4000];
    Long F,cond,opcode,immediate,operand1,destination,operand2,offset12,offset24,result,toy,C,N,Z,V;
    public void armsim(){
        reg[13] = 4000;
        reg[15]=0;
    }

    //fetches the instruction
    public void fetch(String s,String s1){

        reg[15]=reg[15]+4;
        System.out.println("Fetch: Fetch instruction "+ s+ " from Address " +s1);
        if(s1.equals("0xEF000011")){
            System.out.println("Exit - - Program terminated successfully");
        }
    }
    //read operand1 and operand2 and decides which instruction to be performed
    public void decode(Long inst1){
        //System.out.println();
        System.out.print("Decode: ");
        F=(inst1 & 0x0C000000) >> 26;
        System.out.println(F);
        cond=(inst1 & 0xF0000000) >> 28;
        if(F==0)
        {
            opcode = ((inst1 & 0x01E00000) >> 21);
            immediate = ((inst1  & 0x02000000) >> 25);
            operand1 = ((inst1 & 0x000F0000) >> 16);
           // System.out.println(immediate);
            destination = ((inst1 & 0x0000F000) >> 12);
            if(immediate == 0){
                operand2= inst1 & 0x0000000F;
                //System.out.println(operand2);
                if(opcode == 0){
                    System.out.println("Operation is AND, First operand is R"+ operand1+", "+"Second operand is R"+operand2+", "+"Destination register is R"+destination+", "+"\nRead Registers: R"+operand1+" = "+reg[operand1.intValue()]+" R"+operand2+" = "+reg[operand2.intValue()]);
                }
                else if(opcode == 1){
                    System.out.println("Operation is XOR, First operand is R"+ operand1+", "+"Second operand is R"+operand2+", "+"Destination register is R"+destination+", "+"\nRead Registers: R"+operand1+" = "+reg[operand1.intValue()]+" R"+operand2+" = "+reg[operand2.intValue()]);
                }
                else if(opcode == 2){
                    System.out.println("Operation is SUB, First operand is R"+ operand1+", "+"Second operand is R"+operand2+", "+"Destination register is R"+destination+", "+"\nRead Registers: R"+operand1+" = "+reg[operand1.intValue()]+" R"+operand2+" = "+reg[operand2.intValue()]);
                }
                else if(opcode == 4){
                    System.out.println("Operation is ADD, First operand is R"+ operand1+", "+"Second operand is R"+operand2+", "+"Destination register is R"+destination+", "+"\nRead Registers: R"+operand1+" = "+reg[operand1.intValue()]+" R"+operand2+" = "+reg[operand2.intValue()]);
                }
                else if(opcode == 5){
                    System.out.println("Operation is ADC, First operand is R"+ operand1+", "+"Second operand is R"+operand2+", "+"Destination register is R"+destination+", "+"\nRead Registers: R"+operand1+" = "+reg[operand1.intValue()]+" R"+operand2+" = "+reg[operand2.intValue()]);
                }
                else if(opcode == 10){
                    System.out.println("Operation is CMP, First operand is R"+ operand1+", "+"Second operand is R"+operand2+", "+"Destination register is R"+destination+", "+"\nRead Registers: R"+operand1+" = "+reg[operand1.intValue()]+" R"+operand2+" = "+reg[operand2.intValue()]);
                }
                else if(opcode == 12){
                    System.out.println("Operation is OR, First operand is R"+ operand1+", "+"Second operand is R"+operand2+", "+"Destination register is R"+destination+", "+"\nRead Registers: R"+operand1+" = "+reg[operand1.intValue()]+" R"+operand2+" = "+reg[operand2.intValue()]);
                }
                else if(opcode == 13){
                    System.out.println("Operation is MOV, First operand is R"+ operand1+", "+"Second operand is R"+operand2+", "+"Destination register is R"+destination+", "+"\nRead Registers: R"+operand1+" = "+reg[operand1.intValue()]+" R"+operand2+" = "+reg[operand2.intValue()]);
                }
                else if(opcode == 15){
                    System.out.println("Operation is MNV, First operand is R"+ operand1+", "+"Second operand is R"+operand2+", "+"Destination register is R"+destination+", "+"\nRead Registers: R"+operand1+" = "+reg[operand1.intValue()]+" R"+operand2+" = "+reg[operand2.intValue()]);
                }
            }
            else if(immediate == 1){
                operand2 = inst1 & 0x000000FF;
               // reg[destination.intValue()]=operand2.intValue();
               // System.out.println(reg[2]);
                if(opcode == 0){
                    System.out.println("Operation is AND, First operand is R"+ operand1+", "+"Immediate Second operand is "+operand2+", "+"Destination register is R"+destination+", "+"\nRead Registers: R"+operand1+" = "+reg[operand1.intValue()]);
                }
                else if(opcode == 1){
                    System.out.println("Operation is XOR, First operand is R"+ operand1+", "+"Immediate Second operand is "+operand2+", "+"Destination register is R"+destination+", "+"\nRead Registers: R"+operand1+" = "+reg[operand1.intValue()]);
                }
                else if(opcode == 2){
                    System.out.println("Operation is SUB, First operand is R"+ operand1+", "+"Immediate Second operand is "+operand2+", "+"Destination register is R"+destination+", "+"\nRead Registers: R"+operand1+" = "+reg[operand1.intValue()]);
                }
                else if(opcode == 4){
                    System.out.println("Operation is ADD, First operand is R"+ operand1+", "+"Immediate Second operand is "+operand2+", "+"Destination register is R"+destination+", "+"\nRead Registers: R"+operand1+" = "+reg[operand1.intValue()]);
                }
                else if(opcode == 5){
                    System.out.println("Operation is ADC, First operand is R"+ operand1+", "+"Immediate Second operand is "+operand2+", "+"Destination register is R"+destination+", "+"\nRead Registers: R"+operand1+" = "+reg[operand1.intValue()]);
                }
                else if(opcode == 10){
                    System.out.println("Operation is CMP, First operand is R"+ operand1+", "+"Immediate Second operand is "+operand2+", "+"Destination register is R"+destination+", "+"\nRead Registers: R"+operand1+" = "+reg[operand1.intValue()]);
                }
                else if(opcode == 12){
                    System.out.println("Operation is OR, First operand is R"+ operand1+", "+"Immediate Second operand is "+operand2+", "+"Destination register is R"+destination+", "+"\nRead Registers: R"+operand1+" = "+reg[operand1.intValue()]);
                }
                else if(opcode == 13){
                    System.out.println("Operation is MOV, First operand is R"+ operand1+", "+"Immediate Second operand is "+operand2+", "+"Destination register is R"+destination+", "+"\nRead Registers: R"+operand1+" = "+reg[operand1.intValue()]);
                    reg[destination.intValue()]=operand2.intValue();
                }
                else if(opcode == 15){
                    System.out.println("Operation is MNV, First operand is R"+ operand1+", "+"Immediate Second operand is "+operand2+", "+"Destination register is R"+destination+", "+"\nRead Registers: R"+operand1+" = "+reg[operand1.intValue()]);
                }
            }
        }
        else if(F==1) {
            opcode = (inst1 & 0x00100000) >> 20;
            destination = (inst1 & 0x0000F000) >> 12; //Value to be stored(STR)
                                                      //Destination register(LDR)

            operand1 = (inst1 & 0x000F0000) >> 16;    //Base Address
            offset12 = inst1 & 0x00000FFF;
            if (opcode == 1) {
                System.out.println("DECODE:Operation is LDR, Base register is R" + operand1 + ", " + "Offset is " + operand2 + ", " + "Destination register is R" + destination);
            } else if (opcode == 0) {
                System.out.println("DECODE:Operation is STR, Base register is R" + operand1 + ", " + "Offset is " + operand2 + ", " + "Register to be stored in memory is R" + destination + ", " + "Read Register : R" + destination + " = " + reg[destination.intValue()]);
            }
            else{
                System.out.println("Something wrong in Data Transfer");
            }
        }
        else if(F==2){
            opcode = (inst1 & 0x03000000) >> 24;
            offset24 = inst1 & 0x00FFFFFF;
            //System.out.println(offset24);
            Long msb= (offset24 & 0x800000) >> 23;
            //System.out.println(msb);
            if(msb==1){
                System.out.println(offset24);
                offset24=offset24 = 0xFF000000 + offset24;
            }
            else {
                offset24 = 0x00000000 + offset24;
            }
            //System.out.println(offset24);
            if(opcode==2){
                if(cond==0){
                    System.out.println("DECODE: Operation is BEQ");
                }
                else if(cond==1){
                    System.out.println("DECODE: Operation is BNE\n");
                }
                else if(cond==11){
                    System.out.println("DECODE: Operation is BLT\n");
                }
                else if(cond==13){
                    System.out.println("DECODE: Operation is BLE\n");
                }
                else if(cond==12){
                    System.out.println("DECODE: Operation is BGT\n");
                }
                else if(cond==10){
                    System.out.println("DECODE; Operation is BGE\n");
                }
                else if(cond==14){
                    System.out.println("DECODE: Operation is BAL\n");
                }
            }
        }

       // System.out.println();
    }

    public void execute(){
        result=Long.parseLong("0");
        toy=Long.parseLong("0");
        if(F==0){
            if(immediate==0){
                if(opcode==0){
                    System.out.println("Execute: AND "+reg[operand1.intValue()]+" and "+reg[operand2.intValue()]);
                    int y=(reg[operand1.intValue()])&(reg[operand2.intValue()]);
                    result= Long.valueOf(y);
                }
                else if(opcode==1){
                    System.out.println("Execute: XOR "+reg[operand1.intValue()]+" and "+reg[operand2.intValue()]);
                    int y=(reg[operand1.intValue()])^(reg[operand2.intValue()]);
                    result= Long.valueOf(y);
                }
                else if(opcode==2){
                    System.out.println("Execute: SUB "+reg[operand1.intValue()]+" and "+reg[operand2.intValue()]);
                    int y=(reg[operand1.intValue()])-(reg[operand2.intValue()]);
                    result= Long.valueOf(y);
                }
                else if(opcode==4){
                    System.out.println("Execute: ADD "+reg[operand1.intValue()]+" and "+reg[operand2.intValue()]);
                    int y=(reg[operand1.intValue()])+(reg[operand2.intValue()]);
                    result= Long.valueOf(y);
                }
                else if(opcode==5){
                    System.out.println("Execute: ADC "+reg[operand1.intValue()]+" and "+reg[operand2.intValue()]);
                    int y=(reg[operand1.intValue()])+(reg[operand2.intValue()]);
                    result= Long.valueOf(y);
                    if(result>Long.parseLong("4294967296")){
                        C=Long.valueOf(1);
                        System.out.println("Carry: C = "+C);
                    }
                }
                else if(opcode==10){
                    System.out.println("Execute: CMP "+reg[operand1.intValue()]+" and "+reg[operand2.intValue()]);
                    int y=(reg[operand1.intValue()]);
                    int y1=(reg[operand2.intValue()]);
                    if(y==y1){
                        result=Long.valueOf(0);
                        Z=Long.valueOf(1);
                    }
                    else if(y>y1){
                        result=Long.valueOf(1);
                    }
                    else{
                        result=Long.valueOf(-1);
                        N=Long.valueOf(1);
                    }
                }
                else if(opcode==12){
                    System.out.println("Execute: OR "+reg[operand1.intValue()]+" and "+reg[operand2.intValue()]);
                    int y=(reg[operand1.intValue()])|(reg[operand2.intValue()]);
                    result= Long.valueOf(y);
                }
                else if(opcode==13){
                    System.out.println("Execute: MOV value "+reg[operand2.intValue()]+" in R"+destination);
                    //int y=(reg[operand1.intValue()])|(reg[operand2.intValue()]);
                    result= Long.valueOf(reg[operand2.intValue()]);
                }
                else if(opcode==15){
                    System.out.println("Execute: MNV NOT  "+operand2+" in R"+destination);
                    //int y=(reg[operand1.intValue()])|(reg[operand2.intValue()]);
                    result= ~Long.valueOf(reg[operand2.intValue()]);
                }
            }
            else if(immediate==1){
                if(opcode==0){
                    System.out.println("Execute: AND "+reg[operand1.intValue()]+" and "+operand2);
                    int y=(reg[operand1.intValue()]) & operand2.intValue();
                    result= Long.valueOf(y);
                }
                else if(opcode==1){
                    System.out.println("Execute: XOR "+reg[operand1.intValue()]+" and "+operand2);
                    int y=(reg[operand1.intValue()])^operand2.intValue();
                    result= Long.valueOf(y);
                }
                else if(opcode==2){
                    System.out.println("Execute: SUB "+reg[operand1.intValue()]+" and "+operand2);
                    int y=(reg[operand1.intValue()])-operand2.intValue();
                    result= Long.valueOf(y);
                }
                else if(opcode==4){
                    System.out.println("Execute: ADD "+reg[operand1.intValue()]+" and "+operand2);
                    int y=(reg[operand1.intValue()])+operand2.intValue();
                    result= Long.valueOf(y);
                }
                else if(opcode==5){
                    System.out.println("Execute: ADC "+reg[operand1.intValue()]+" and "+operand2);
                    int y=(reg[operand1.intValue()])+operand2.intValue();
                    result= Long.valueOf(y);
                    if(result>Long.parseLong("4294967296")){
                        C=Long.valueOf(1);
                        System.out.println("Carry: C = "+C);
                    }
                }
                else if(opcode==10){
                    System.out.println("Execute: CMP "+reg[operand1.intValue()]+" and "+operand2);
                    int y=(reg[operand1.intValue()]);
                    int y1=operand2.intValue();
                    if(y==y1){
                        result=Long.valueOf(0);
                        Z=Long.valueOf(1);
                    }
                    else if(y>y1){
                        result=Long.valueOf(1);
                    }
                    else{
                        result=Long.valueOf(-1);
                        N=Long.valueOf(1);
                    }
                }
                else if(opcode==12){
                    System.out.println("Execute: OR "+reg[operand1.intValue()]+" and "+operand2);
                    int y=(reg[operand1.intValue()])|operand2.intValue();
                    result= Long.valueOf(y);
                }
                else if(opcode==13){
                    System.out.println("Execute: MOV value "+operand2+" in R"+destination);
                    //int y=(reg[operand1.intValue()])|(reg[operand2.intValue()]);
                    result= operand2;
                }
                else if(opcode==15){
                    System.out.println("Execute: MNV NOT "+operand2+" in R"+destination);
                    //int y=(reg[operand1.intValue()])|(reg[operand2.intValue()]);
                    result= ~operand2;
                }
            }
        }
        else if(F==1){
            if(opcode==0){
                result=Long.valueOf(reg[operand1.intValue()]);
                //System.out.println(operand1);
                System.out.println("Store " +reg[destination.intValue()]+" in register R"+operand1);
            }
            else if(opcode==1){
                result=Long.valueOf(reg[operand1.intValue()]);
                System.out.println(" "+reg[operand1.intValue()]+" and "+offset12);
            }
        }
        else if(F==2){
            reg[15]=reg[15]-4;
            if(opcode==2){
                if(cond==0){
                    System.out.println("Execute: BEQ offset is: "+offset24);
                    if(Z==1){
                        System.out.println(reg[15]+(offset24.intValue() * 4)+4);
                        reg[15]=reg[15]+(offset24.intValue() * 4)+4;
                        address=reg[15];
                    }
                }
                else if(cond==1){
                    System.out.println("Execute: BNE offset is: "+offset24);
                    if(Z!=Long.valueOf(1)){
                        System.out.println(reg[15]);
                        reg[15]=reg[15]+(offset24.intValue() * 4)+4;
                        System.out.println(Integer.toHexString(12));
                        address=reg[15];
                    }
                }
                else if(cond==11){
                    System.out.println("Execute: BLT offset is: "+offset24);
                    if((N==1)&&(Z==0)){
                        reg[15]=reg[15]+(offset24.intValue() * 4)+4;
                        address=reg[15];
                    }
                }
                else if(cond==12){
                    System.out.println("Execute: BGT offset is: "+offset24);
                    if((Z==0)&&(N==0)){
                        reg[15]=reg[15]+(offset24.intValue() * 4)+4;
                        address=reg[15];
                    }
                }
                else if(cond==13){
                    System.out.println("Execute: BLE offset is: "+offset24);
                    if((Z==1)||(N==1)){
                        reg[15]=reg[15]+(offset24.intValue() * 4)+4;
                        address=reg[15];
                    }
                }
                else if(cond==14){
                    System.out.println("Execute: BAL offset is: "+offset24);
                    reg[15]=reg[15]+(offset24.intValue() * 4)+4;
                    address=reg[15];
                }
                else if(cond==10){
                    System.out.println("Execute: BGE offset is: "+offset24);
                   // System.out.println(Z +"+"+ N );
                    if((Z==1) || (N==0)){
                        reg[15]=reg[15]+(offset24.intValue() * 4)+4;
                        address=reg[15];
                    }
                }
            }
        }
    }

    public void memory(){
        System.out.print("Memory: ");
        if(F==0 || F==1){
            if(opcode==0){
                System.out.println("Storing "+reg[destination.intValue()]+" at address "+result);
            }
            else if(opcode==1){
                System.out.println("Loading "+reg[destination.intValue()]+" from address "+result);
            }
        }

    }


    public void write_back(){
        if(F==0){
            if(opcode==10) {
                System.out.println("WRITEBACK: No writeback operation required");
            }
            else
            {
                reg[destination.intValue()] = result.intValue();
                System.out.println("WRITEBACK: write "+result+ " to R"+destination);
            }
        }
        else if(F==1) {
            if(opcode==1) {
                reg[destination.intValue()] = result.intValue();
                System.out.println("WRITEBACK: write "+result+ " to R"+destination);
            }
            else if(opcode==0) {
             toy= Long.valueOf(reg[destination.intValue()]);
                System.out.println("WRITEBACK: write "+toy+" to memory array");
            }
        }
        else if(F==2) {
            System.out.println("WRITEBACK: No writeback operation required");
        }
        else if(F==3)
        {
            System.out.println("EXIT:");
        }
    }
}




public class simulator {
    //static int reg[]=new int[16];
    //reg[15]=1;
    //int mem[]=new int[4000];

    public static void main(String[] args)throws IOException{
        FileReader freader = new FileReader("fib.mem");
        BufferedReader br = new BufferedReader(freader);
        ArrayList inp=new ArrayList();
        String s;
        while((s = br.readLine()) != null) {
            inp.add(s);
        }
        freader.close();
        armsim arm=new armsim();
        arm.reg[13]=4000;
        arm.N=Long.valueOf("5");
        arm.Z=Long.valueOf("5");
        arm.V=Long.valueOf("5");
        arm.C=Long.valueOf("5");
        for(int i=0;i<inp.size();i++){  //Iterates over arraylist containing input from mem file
            String inst=(String)inp.get(i);//first instruction
            StringTokenizer str=new StringTokenizer(inst);
            int a=0;
            String pc[]=new String[2];
            while(str.hasMoreTokens()){
                pc[a]=(String)str.nextElement();
                a++;
            }
            Long hex=Long.decode(pc[1]);
            //System.out.println((0xE0821003 & 0x0C000000) >> 26);

            arm.fetch(pc[0],pc[1]);
            arm.decode(hex);
            arm.execute();
            System.out.println(arm.F);
            if(arm.address!=1500){
                i=(arm.address/4)-1;
                System.out.println(i);
               // System.out.println(inp.get(i));
                arm.address=1500;
            }
            arm.memory();
            arm.write_back();

            System.out.println();


        }

    }


}
