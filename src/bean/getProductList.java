package bean;

import java.sql.*;
import java.sql.Connection;
import java.io.IOException;
import java.util.*;
import java.util.Random;
import com.maan.DBCon.DBConnection;
import proj.sql.QueryBuilder;
import com.maan.services.util.runner;

public class getProductList {
	Connection conn = null;

	Statement smt = null;

	public String[][] product_list() {
		String[][] abc = new String[0][0];
		String sql = "";
		try {

			sql = "select * from BHOMEPRODUCT_MASTER";
			abc = runner.multipleSelection(sql, new String[0]);

			// system.out.println("Query is"+sql);

		} catch (Exception e) {
			System.out
					.println("prob in connnnnnnnnnn to get product master list"
							+ e);
		}

		return abc;
	}

	public String[][] getField_list(String p_id) {
		String[][] abc = new String[0][0];
		String sql = "";

		String values[] = new String[1];

		try {
			values[0] = p_id.trim();
			sql = "select distinct(database_name) from BHOMEPRODUCT_MASTER_CONF where product_id=? order by database_name";

			abc = runner.multipleSelection(sql, values);

		} catch (Exception e) {
			System.out
					.println("prob in connnnnnnnnnn to get product master list"
							+ e);
		}

		return abc;
	}

	public String[][] getexcel_list(String prod_id, String DBcol_name) {
		String[][] abc = new String[0][0];
		String sql = "";
		String values[] = new String[2];

		try {
			values[0] = prod_id.trim();
			values[1] = DBcol_name.trim();
			sql = "select pmc_id,excel_header_name from BHOMEPRODUCT_MASTER_CONF where product_id=? and database_name=? and status='Y'";

			abc = runner.multipleSelection(sql, values);

		} catch (Exception e) {
			System.out
					.println("prob in connnnnnnnnnn to get product master list"
							+ e);
		}

		return abc;
	}

	public int Update_newfield(String p_id, String DBcol_name,
			String xl_header, String upStatus) {
		String[][] abc = new String[0][0];
		String sql = "", sql1 = "";
		int x = 0, st = 0;
		// system.out.println("upstatus in bean"+upStatus);
		xl_header = xl_header.trim();

		try {
			conn = DBConnection.getInstance().getDBConnection();
			smt = conn.createStatement();
			QueryBuilder qry = new QueryBuilder(smt);
			// system.out.println("connnnnnnnnnn to get product master list");

			sql = "select max(pmc_id) from BHOMEPRODUCT_MASTER_CONF where product_id="
					+ p_id.trim();

			abc = qry.getResultSet(sql);

			// system.out.println("Query is"+sql);
			// system.out.println("abc length"+abc.length);
			if (abc.length > 0 && abc[0][0] != null)
				x = (Integer.parseInt(abc[0][0])) + 1;
			else
				x = 1;

			if ("XL".equalsIgnoreCase(upStatus))
				sql1 = "insert into BHOMEPRODUCT_MASTER_CONF(pmc_id,product_id,excel_header_name,database_name,status) values('"
						+ x
						+ "','"
						+ p_id
						+ "','"
						+ xl_header
						+ "','"
						+ DBcol_name + "','Y')";

			if ("DB".equalsIgnoreCase(upStatus)) {
				xl_header = xl_header.replaceAll(" ", "_");
				sql1 = "insert into BHOMEPRODUCT_MASTER_CONF(pmc_id,product_id,database_name,status) values('"
						+ x + "','" + p_id + "','" + xl_header + "','Y')";
			}

			// system.out.println("Query is"+sql1);
			smt.executeUpdate(sql1);

			// -----------------------------------------------
			if ("DB".equalsIgnoreCase(upStatus)) {
				sql = "select table_name from BHOMEPRODUCT_MASTER where product_id="
						+ p_id.trim();

				abc = qry.getResultSet(sql);

				// system.out.println("Query is"+sql);
				// system.out.println("abc length"+abc.length);
				if (abc.length > 0) {
					xl_header = xl_header.replaceAll(" ", "_");
					sql1 = "alter table " + abc[0][0].trim() + " add("
							+ xl_header + " varchar2(200))";
				}

				// system.out.println("Query is"+sql1);
				smt.executeUpdate(sql1);
			}

			st = 1;

		} catch (Exception e) {
			System.out
					.println("prob in connnnnnnnnnn to get product master list"
							+ e);
		} finally {
			try {

				if (smt != null)
					smt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e) {
			}
		}

		return 1;
	}

	public int Update_existingfield(String p_id, String pmc_id, String xl_header) {
		String sql = "";
		int st = 0;
		String values[] = new String[3];
		values[0] = xl_header;
		values[1] = pmc_id;
		values[2] = p_id;

		try {

			sql = "update BHOMEPRODUCT_MASTER_CONF set excel_header_name=? where pmc_id=? and product_id=? and status='Y'";
			// system.out.println("Query is"+sql);
			runner.multipleUpdation(sql, values);

			st = 1;

		} catch (Exception e) {
			System.out
					.println("prob in connnnnnnnnnn to get product master list"
							+ e);
		}
		return st;
	}

	public int Delete_existingfield(String p_id, String pmc_id) {
		String sql = "";
		int st = 0;
		String values[] = new String[2];
		values[0] = pmc_id;
		values[1] = p_id;

		try {

			sql = "update BHOMEPRODUCT_MASTER_CONF set status='N' where pmc_id=? and product_id=?";
			runner.multipleUpdation(sql, values);
			// system.out.println("Query is"+sql);
			st = 1;

		} catch (Exception e) {
			System.out
					.println("prob in connnnnnnnnnn to get product master list"
							+ e);
		}

		return st;
	}

}