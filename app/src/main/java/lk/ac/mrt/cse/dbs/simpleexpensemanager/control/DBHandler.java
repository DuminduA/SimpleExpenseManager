
package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;


import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.AccountDAO_imp;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.TransactionDAO_imp;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;

public class DBHandler extends ExpenseManager{

    private Context context;

    public DBHandler(Context context) {
        this.context = context;
        setup();
    }

    @Override
      public void setup(){

          SQLiteDatabase database = context.OpenOrCreateDatabase("140019E", context.MODE_PRIVATE, null);

          database.execSQL("CREATE TABLE Account (AccountNo varchar primary key,Account_holderName varchar not null," +
                  "BankName varchar,Balance Float);");

          database.execSQL("CREATE TABLE Transaction_tb (Transaction_id varchar primary key," +
                  "AccountNo varchar,Date DATE,Expense_type INT,Amount Float)" +
                  "FOREIGN KEY (AccountNo) REFERENCES Account(AccountNo));");


        AccountDAO_imp Account = new AccountDAO_imp(database);

        setAccountsDAO(Account);

        TransactionDAO_imp Trnsaction = new TransactionDAO_imp(database);

        setTransactionsDAO(Transaction);
      }


    }