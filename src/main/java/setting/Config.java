package setting;

public class Config {
	public static final String DB_NAME = "ncs_erp";
	public static final String ROOT_USER="root";
	public static final String USER   = "user_ncs_erp";
	public static final String PWD    = "rootroot";
	public static final String URL    = "jdbc:mysql://localhost:3306/";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	
	public static final String[] TABLE_NAME = {"title","department","employee"};
	public static final String[] FILE_NAME = {"title.txt","department.txt","employee.txt"};
	
	public static final String CREATE_USER="GRANT USAGE ON *.* TO 'user_ncs_erp'@'localhost' IDENTIFIED BY 'rootroot'";
	
	public static final String USER_GRANT= String.format(
			" grant select, insert, update ,delete on %s.* to %s@localhost identified by '%s'", Config.DB_NAME,Config.USER,Config.PWD);
	
	public static final String[] CREATE_SQL_TABLE={
			//직급
			"CREATE TABLE if not exists title "
			+ "(	tcode    int(11)     not NULL,	"
			+ "tname  VARCHAR(10) ,	"
			+ "PRIMARY KEY (tcode))" ,
			
			//부서
			"CREATE TABLE if not exists department (	"
			+ "dcode    int(11)     not NULL,	"
			+ "dname    char(10) not null,	"
			+ "floor    int(11),	"
			+ " PRIMARY KEY (dcode));",
			
			//사원
			
			"CREATE TABLE if not exists employee (	"
			+ "eno     int(11)     not NULL, "
			+ "ename   VARCHAR(20) not null,	"
			+ "salary  int(11) ,	"
			+ "dno     int(11),	"
			+ "gender tinyint(1),    "
			+ "joinDate Date,	"
			+ "title  int(11)  ,	"
			+ "PRIMARY KEY (eno),	"
			+ "FOREIGN KEY (title) REFERENCES title(tcode),	"
			+ "FOREIGN KEY (dno) REFERENCES department(dcode))  "		
	};
	
	public static final String[] IS_EXIST_TABLE={
		"select count(*) as isExists from information_schema.TABLES where TABLE_SCHEMA = 'ncs_erp' and TABLE_NAME='employee';",
		"select count(*) as isExists from information_schema.TABLES where TABLE_SCHEMA = 'ncs_erp' and TABLE_NAME='title';",
		"select count(*) as isExists from information_schema.TABLES where TABLE_SCHEMA = 'ncs_erp' and TABLE_NAME='department';"
	};
	
	public static final String DATAFILES_PATH = System.getProperty("user.dir")+ "\\src\\main\\DataFiles\\";
	public static final String BACKUPFILES_PATH = System.getProperty("user.dir")+ "\\src\\main\\BackUpFiles\\";
}
