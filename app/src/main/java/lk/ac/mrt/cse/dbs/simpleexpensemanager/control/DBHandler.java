
package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;


import android.app.Application;
import android.content.Context;
import android.database.sqlite.*;

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

          SQLiteDatabase database = context.openOrCreateDatabase("140019E",Context.MODE_PRIVATE,null);
          database.execSQL("CREATE TABLE IF NOT EXISTS Account (" +
                  "AccountNo varchar(255) primary key," +
                  "AccountHolderName varchar(255) not null," +
                  "BankName varchar(255)," +
                  "Balance Float)");

          database.execSQL("CREATE TABLE IF NOT EXISTS Transaction_tb (" +
                  "Transaction_id varchar(255) primary key," +
                  "AccountNo varchar(255)," +
                  "Date DATE," +
                  "Expense_type INT," +
                  "Amount Float)" );
                  //"FOREIGN KEY (AccountNo) REFERENCES Account(AccountNo));"


        AccountDAO_imp Account = new AccountDAO_imp(database);

        setAccountsDAO(Account);

        TransactionDAO_imp Transaction = new TransactionDAO_imp(database);

        setTransactionsDAO(Transaction);
      }


    }