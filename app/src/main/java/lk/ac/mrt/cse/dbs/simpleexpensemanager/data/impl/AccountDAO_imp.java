
package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.database.Cursor;
import android.database.sqlite.*;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.DBHandler;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;

import java.util.ArrayList;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;

import android.database.sqlite.*;


public class AccountDAO_imp implements AccountDAO{

    private SQLiteDatabase database;


    public AccountDAO_imp(SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public List<String> getAccountNumbersList() {
        Cursor accounts = database.rawQuery("SELECT AccountNo FROM Account",null);

        List<String> AccountsList = new ArrayList<String>();

        if(accounts.moveToNext()){
            do{
            AccountsList.add(accounts.getString(accounts.getColumnIndex("AccountNo")));

        }while(accounts.moveToFirst());}
        accounts.close();
        return AccountsList;


    }



    @Override
    public List<Account> getAccountsList() {

        Cursor cursorOb = database.rawQuery("SELECT * FROM Account",null);

        List<Account> AccountList = new ArrayList<Account>();

        if(cursorOb.moveToFirst()){
            do{
            Account account1 = new Account(cursorOb.getString(cursorOb.getColumnIndex("AccountNo")),
                                           cursorOb.getString(cursorOb.getColumnIndex("BankName")),
                                           cursorOb.getString(cursorOb.getColumnIndex("Account_holderName")),
                                           cursorOb.getDouble(cursorOb.getColumnIndex("Balance")));
            AccountList.add(account1);
        }while(cursorOb.moveToNext());




    }cursorOb.close();
        return AccountList;}



    @Override
    public Account getAccount(String accountNo){

        Cursor cursorOb = database.rawQuery("Select * from account where AccountNo= ?",new String[] {accountNo});


        Account account1 = null;
        if(cursorOb.moveToFirst()) {
            do{
             account1 = new Account(cursorOb.getString(cursorOb.getColumnIndex("AccountNo")),
                    cursorOb.getString(cursorOb.getColumnIndex("BankName")),
                    cursorOb.getString(cursorOb.getColumnIndex("Account_holderName")),
                    cursorOb.getDouble(cursorOb.getColumnIndex("Balance")));
        }while(cursorOb.moveToNext());}
        cursorOb.close();
        return account1;

    }

    @Override
    public void addAccount(Account account) {

        String sql = "INSERT INTO Account (AccountNo,AccountHolderName,BankName,Balance) VALUES ('"+account.getAccountNo()+"','"+account.getAccountHolderName()+"','"+
                account.getBankName()+"','"+account.getBalance()+"')";
        database.execSQL(sql);


    }





    @Override
    public void removeAccount(String accountNo)
    {
        String sql = "DELETE FROM Account WHERE AccountNo = "+accountNo;
        database.execSQL(sql);
    }



    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount)  {


        if( expenseType== ExpenseType.EXPENSE){
            amount = -amount;
            String sql = "UPDATE Account SET AccountNo="+ amount  ;
            database.execSQL(sql);
        }else{
            String sql = "UPDATE Account SET AccountNo="+ amount;
            database.execSQL(sql);
        }









}}