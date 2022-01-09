package com.sreetej.spring.springcoreavanced.autowiring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Geektrust {
	static ArrayList<LedgerCo> customerlist = new ArrayList<LedgerCo>();
	public static void main(String[] args) throws IOException {
		   		
		
			 	
			 	String filePath = args[0];
			 	File file = new File(filePath);
			 	String params[]=null;
		 
		         // Creating an object of BufferedReader class
		        BufferedReader br= new BufferedReader(new FileReader(file));
		 
		        // Declaring a string variable
		        String st;
		        // Condition holds true till
		        // there is character in a string
		        while ((st = br.readLine()) != null)
		 
		        {// Print the string
		            //System.out.println(st);
		           params=st.split(" ");
		           if(params.length == 0)
		   	    {
		   	        System.out.println("Enter valid number of arguments To Program!!");
		   	        System.exit(0);
		   	    }
		   		
		   		else if(params.length==6 && params[0].equalsIgnoreCase("LOAN")) {
		   			//LOAN IDIDI Dale 5000 1 6

 		   			loan(params[0],params[1],params[2],params[3],params[4],params[5]);
		   			
		   		}
		   		
		   		else if(params.length==5 && params[0].equalsIgnoreCase("PAYMENT")) {
		   			//PAYMENT IDIDI Dale 1000 5

 		   			payment(params[0],params[1],params[2],params[3],params[4]);
		   		}
		   		
		   		else if(params.length==4 && params[0].equalsIgnoreCase("BALANCE")) {
		   			//BALANCE MBI Harry 12
 		   			balance(params[0],params[1],params[2],params[3]);
		   		}
		   		else {
		   			System.out.println("Enter valid arguments!!");
		   		}
		        }
		 
	 
	}

	private static void loan(String args, String args2, String args3, String args4, String args5,String args6) {
		
		// TODO Auto-generated method stub
		double amount;
		//double principal;
		double interest;
		double emino;
		double emiamount;
		LedgerCo lgco= new LedgerCo();
		lgco.setBANK_NAME(args2);
		lgco.setBORROWER_NAME(args3);
		lgco.setPRINCIPAL(Double.parseDouble(args4));
		lgco.setNO_OF_YEARS(Double.parseDouble(args5));
		lgco.setRATE_OF_INTEREST(Double.parseDouble(args6));
		
		interest=lgco.getPRINCIPAL() * lgco.getNO_OF_YEARS() * lgco.getRATE_OF_INTEREST()/100;
		
		amount=lgco.getPRINCIPAL()+interest;
		//System.out.println("The total amount for repaying loan"+amount);
		emiamount= Math.ceil(amount/(Double.parseDouble(args5)*12));
		emino=Math.ceil(Double.parseDouble(args5)*12);//should change if there is a lumpsum payment done.
		lgco.setRemaining_EMIs(emino);
		lgco.setAmount(amount);
		lgco.setInterest(interest);
		lgco.setEmino(emino);
		lgco.setEmiamount(emiamount);
		 
		customerlist.add(lgco);
	 	
	}
	
	
	  private static void payment(String args, String args2, String args3, String args4, String args5) {
		 //PAYMENT BANK_NAME BORROWER_NAME LUMP_SUM_AMOUNT EMI_NO
		  for(LedgerCo name : customerlist)
		  {
		      //System.out.println(name.BANK_NAME);
		      if(name.getBANK_NAME().equalsIgnoreCase(args2) && name.getBORROWER_NAME().equalsIgnoreCase(args3)) {
		    	  
		    	 name.addPaymentRecord(Integer.parseInt(args5), Double.parseDouble(args4));
 		    	 
		      }
		      
		  }
		  

//		  IDIDI Dale 1326 9
//		  IDIDI Dale 3652 4
//		  UON Shelly 15856 3
//		  MBI Harry 9044 10
		  
		  //Track on which EMI the payment is made. Best if its a map ( emino, amount)
		  // based on that modify balance method to display appropriate amount paid and remaining emis )
		  
	  }
	  
	  
	  private static void balance(String args, String args2, String args3, String args4) {
			 
		  for(LedgerCo name : customerlist)
		  {
		      //System.out.println(name.BANK_NAME);
		      if(name.getBANK_NAME().equalsIgnoreCase(args2) && name.getBORROWER_NAME().equalsIgnoreCase(args3)) {
		    	 
		    	  int emiNO = Integer.parseInt(args4);
 		    	  double lumpSumAmntPaidTillEmiNo = name.getLumpsumPaidTillEMINo(emiNO);
		    	  double emipaid = name.getEmiamount()*emiNO;
 		    	  double totalAmountPaid = lumpSumAmntPaidTillEmiNo + emipaid;
		    	  double remainingAmntToPay = name.getAmount() - totalAmountPaid;
		    	  
		    	  double emiRemaining = Math.ceil(remainingAmntToPay/name.getEmiamount());
		    	  System.out.println(name.getBANK_NAME()+" "+name.getBORROWER_NAME()+" "+totalAmountPaid+" "+emiRemaining);
		      }
		      
		  }
		  
		  
	  
		  
	  }
	  
	
}
