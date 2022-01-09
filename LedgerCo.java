package com.sreetej.spring.springcoreavanced.autowiring;


import java.util.NavigableMap;
import java.util.TreeMap;


public class LedgerCo {
	
	String BANK_NAME;
	String BORROWER_NAME;
	double PRINCIPAL;
	double NO_OF_YEARS;
	double RATE_OF_INTEREST;
	double LUMP_SUM_AMOUNT = 0;
	double emino;
	double amount;
	//double balance;
	double remaining_EMIs;

	//Using TreeMap instead of HAshMap just because we don't have to sort the map again. 
	//We need a sorted map for checking balances based on the amount until that emi.  
	TreeMap<Integer,Double> paymentRecord = new TreeMap<>();
	 
	public void addPaymentRecord(Integer emiNo, Double amount)
	{
		double lumpSumAmnt = amount.doubleValue();
		if(paymentRecord.containsKey(emiNo))
		{
			lumpSumAmnt += paymentRecord.get(emiNo).doubleValue();
		}
		paymentRecord.put(emiNo,lumpSumAmnt);//check : passing double instead of Double : are these interchangable in this context ??
		
		LUMP_SUM_AMOUNT += lumpSumAmnt;
	}
	
	public double getLumpsumPaidTillEMINo(int emiNo)
	{
		double lumpSumPaid = 0;
			
		NavigableMap<Integer, Double> subRecords = paymentRecord.headMap(emiNo, true);
		for(Double amnt : subRecords.values())
		{
			lumpSumPaid += amnt.doubleValue();
		}
		
		return lumpSumPaid;
	}
	public double getRemaining_EMIs() {
		return remaining_EMIs;
	}
	public void setRemaining_EMIs(double remaining_EMIs) {
		this.remaining_EMIs = remaining_EMIs;
	}
//	public double getBalance() {
//		return balance;
//	}
//	public void setBalance(double balance) {
//		this.balance = balance;
//	}
	public double getAmount() {
		return amount;
	}
	public double getInterest() {
		return interest;
	}
	public double getEmino() {
		return emino;
	}
	public double getEmiamount() {
		return emiamount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public void setEmino(double emino) {
		this.emino = emino;
	}
	public void setEmiamount(double emiamount) {
		this.emiamount = emiamount;
	}
	double interest;
	double emiamount;
	public double getLUMP_SUM_AMOUNT() {
		return LUMP_SUM_AMOUNT;
	}
	
//	public void setLUMP_SUM_AMOUNT(double lUMP_SUM_AMOUNT) {
//		LUMP_SUM_AMOUNT = lUMP_SUM_AMOUNT;
//	}
	
	
	public String getBANK_NAME() {
		return BANK_NAME;
	}
	public String getBORROWER_NAME() {
		return BORROWER_NAME;
	}
	public double getPRINCIPAL() {
		return PRINCIPAL;
	}
	public double getNO_OF_YEARS() {
		return NO_OF_YEARS;
	}
	public double getRATE_OF_INTEREST() {
		return RATE_OF_INTEREST;
	}
	public void setBANK_NAME(String bANK_NAME) {
		BANK_NAME = bANK_NAME;
	}
	public void setBORROWER_NAME(String bORROWER_NAME) {
		BORROWER_NAME = bORROWER_NAME;
	}
	public void setPRINCIPAL(double pRINCIPAL) {
		PRINCIPAL = pRINCIPAL;
	}
	public void setNO_OF_YEARS(double nO_OF_YEARS) {
		NO_OF_YEARS = nO_OF_YEARS;
	}
	public void setRATE_OF_INTEREST(double rATE_OF_INTEREST) {
		RATE_OF_INTEREST = rATE_OF_INTEREST;
	}
	
	
	

}
