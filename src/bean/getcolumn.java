package bean;

import java.sql.*;
import java.sql.Connection;

import java.io.IOException;
import java.util.*;

import java.util.Random;
import com.maan.services.util.runner;
import proj.sql.QueryBuilder;

public class getcolumn {
	Connection conn = null;

	Statement smt = null;

	String qry = null;

	String data = null;

	int pid = 0;

	public void setConn(Connection con) {
		this.conn = con;
	}

	public Connection getConn() {
		return conn;
	}

	public String[] DataConvert(String colname[], int productid) {
		String[][] abc = new String[0][0];
		String sql = "";
		String values[] = new String[1];
		pid = productid;

		try {

			values[0] = "" + pid;
			sql = "select * from BHOMEPRODUCT_MASTER_CONF where product_id=? order by pmc_id";

			abc = runner.multipleSelection(sql, values);

		} catch (Exception e) {

		}

		String dbcolumn[] = new String[colname.length];

		/*
		 * if(colname.length==abc.length) {
		 * 
		 * for(int i=0;i<abc.length;i++) { System.out.println("both columns
		 * equal"+abc[i][2]); dbcolumn[i]=abc[i][2]; } } else{
		 */

		for (int i = 0; i < colname.length; i++) {

			for (int j = 0; j < abc.length; j++) {
				// System.out.println(colname[i]+""+abc[j][2]+""+abc[j][4]);

				if (((colname[i]).equalsIgnoreCase(abc[j][2]))
						&& ("Y".equalsIgnoreCase(abc[j][4]))) {

					// System.out.println(abc[j][0]+abc[j][1]+abc[j][2]+abc[j][3]);
					dbcolumn[i] = abc[j][3];
					abc[j][4] = "N";

					// System.out.println(abc[j][0]+abc[j][1]+abc[j][2]+abc[j][3]);
					break;
				}
			}
		}
		// } //if cond cheking col length = retrun array length
		System.out
				.println("-------------------------------------------------------------------------");
		for (int j = 0; j < dbcolumn.length; j++) {
			// System.out.println(dbcolumn[j]);
		}
		System.out
				.println("-------------------------------------------------------------------------");
		for (int j = 0; j < abc.length; j++) {
			// System.out.println(abc[j][0]+abc[j][1]+abc[j][2]+abc[j][3]+abc[j][4]);
		}

		System.out.println("Product id " + pid);
		return dbcolumn;
	}
}