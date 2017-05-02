package erp_project.init;

public class Config {
	public static final String DB_NAME 		= "mysql";
	public static final String MY_DB_NAME	= "ncs_erp";
	public static final String USER  		= "root";
	public static final String PWD    		= "rootroot";
	public static final String URL    		= "jdbc:mysql://localhost:3306/";
	public static final String DRIVER 		= "com.mysql.jdbc.Driver";
	
	public static final String[] TABLE_NAME = {"title", "employee", "department"};
	
	public static final String[] CREATE_SQL_TABLE={
		"CREATE TABLE title ("
		+ "tcode    int(11)     not NULL,   "
		+ "tname    VARCHAR(10) ,   "
		+ "PRIMARY KEY (tcode))" ,
         
         "CREATE TABLE department ("
		+ "dcode    int(11)     not NULL, "
		+ "dname    char(10) 	not null, "
		+ "floor    int(11), "
		+ "PRIMARY KEY (dcode));",
         
		"CREATE TABLE employee (   "
		+ "eno      int(11)     not NULL, "
		+ "ename    VARCHAR(20) not null,   "
		+ "salary   int(11) ,   "
		+ "dno      int(11),   "
		+ "gender   tinyint(1),    "
		+ "joinDate Date,   "
		+ "title  int(11)  ,   "
		+ "PRIMARY KEY (eno),   "
		+ "FOREIGN KEY (title) REFERENCES title(tcode),   "
		+ "FOREIGN KEY (dno) REFERENCES department(dcode))  "
	};
	
	public static final String[] CREATE_DATABASE_AND_USER={
		"DROP DATABASE IF EXISTS ncs_erp;"
		+ "CREATE DATABASE ncs_erp;"
		+ "USE ncs_erp;"
		+ "GRANT USAGE ON *.* TO 'ncs_user'@'localhost' IDENTIFIED BY 'password';"
		+ "DROP USER 'ncs_user'@'localhost';"
		+ "UPDATE mysql.user SET Password=PASSWORD('rootroot') WHERE User='ncs_user' AND Host='%';"
		+ "GRANT DELETE ON ncs_erp.* TO 'ncs_user'@'%' ;"
		+ "GRANT INSERT ON ncs_erp.* TO 'ncs_user'@'%' ;"
		+ "GRANT SELECT ON ncs_erp.* TO 'ncs_user'@'%' ;"
		+ "GRANT UPDATE ON ncs_erp.* TO 'ncs_user'@'%' ;"	
	};
			
	public static final String DATAFILES_PATH= System.getProperty("user.dir")+ "\\src\\main\\DataFiles\\";
}