

package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;


public class TransactionDAO_imp implements TransactionDAO{

    private SQLiteDatabase database;

    public TransactionDAO_imp(SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public void logTransaction(Date date, String accountNo, ExpenseType expenseType, double amount) {

        String sql = "INSERT INTO Transaction_tb (AccountNo,Date,Expense_type,Amount) VALUES(?,?,?,?)";
        SQLiteStatement statement = database.compliteStatement(sql);
		
		statement.bindString(1,accountNo);
		statement.bindLong(2,date.getTime());
		statement.bindLong(3,(expenseType == ExpenseType.EXPENSE) ? 0 : 1);
		statement.bindDouble(4,amount);
		
		statement.executeInsert();

    @Override
    public List<Transaction> getAllTransactionLogs() {

		Cursor cursor = database.rawQuery("SELECT * FROM Transaction_tb",null);

		List<Transaction> trnsactionsList= new ArrayList<Transaction>();
		if(cursor.moveToFirst()){
		do{
			
			Transaction transaction = new Transaction(cursor.getString(cursor.getColumnIndex("AccountNo")),
                    new Date(cursor.getLong(cursor.getColumnIndex("Date"))),
                    cursor.getLong(cursor.getColumnIndex("Expense_Type"))
                    cursor.getDouble(cursor.getColumnIndex("Balance")));

            transactionsList.add(transaction);
		}while(cursor.moveToNext())};
            cursor.close();
            return transactionsList;

    }

    @Override
    public List<Transaction> getPaginatedTransactionLogs(int limit) {

            Cursor cursor = database.rawQuery("SELECT * FROM Transaction_tb " + limit,null);

            cursor.moveToFirst();

            List<Transaction> TransactionLimit = new ArrayList<Transaction>();

            while(cursor.moveToNext()){

                Transaction transaction = new Transaction(cursor.getString(cursor.getColumnIndex("AccountNo")),
                        new Date(cursor.getLong(cursor.getColumnIndex("Date"))),
                        cursor.getLong(cursor.getColumnIndex("Expense_Type"))
                        cursor.getDouble(cursor.getColumnIndex("Balance")));

                transactionsList.add(transaction);
            }

            cursor.close();
            return TransactionLimit;


        }
    }


}