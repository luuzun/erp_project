package setting;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class InitSettingService {
	private static final InitSettingService instance = new InitSettingService();
	private InitSettingService() {}

	public static InitSettingService getInstance() {
		return instance;
	}

	public void initSetting(){
		try {
			Dao dao = Dao.getInstance();
			dao.getUpdateResult("DROP DATABASE IF EXISTS " + Config.DB_NAME);
			System.out.println("Drop database if exists " + Config.DB_NAME);


			dao.getUpdateResult("create database " + Config.DB_NAME);
			System.out.println("Create database " + Config.DB_NAME);

			dao.getUpdateResult("use " + Config.DB_NAME);
			System.out.println("use " + Config.DB_NAME);

			for(int i=0;i<Config.CREATE_SQL_TABLE.length;i++){
				dao.getUpdateResult(Config.CREATE_SQL_TABLE[i]);
				System.err.println(Config.TABLE_NAME[i]+"Table 생성완료");
			}
			dao.getUpdateResult(Config.CREATE_USER);
			dao.getUpdateResult(Config.USER_GRANT);
			System.out.println(Config.USER + " 생성완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void reset(){
		File buFile = new File(Config.DATAFILES_PATH);// 현재 작업하고 있는 프로젝트 경로안의 BackupFiles폴더
		File[] buFiles = buFile.listFiles(); // BackupFiles 안에 있는 파일들을 배열에 넣음
		try{
			if(buFiles.length<1){}
			initSetting();
		}catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "복원 파일이 없습니다");
		}
	}

	public void restore(){
		for(int i=0 ; i<Config.TABLE_NAME.length ; i++){
			loadTableData(i); // BackupFiles폴더에 있는 파일들을 가져와 테이블에 삽입
		}

	}

	public void backUp(){
		File buFile = new File(Config.BACKUPFILES_PATH);// 현재 작업하고 있는 프로젝트 경로안의 BackupFiles폴더

		if(buFile.exists()==false){ // 폴더 존재여부
			buFile.mkdir(); // 없다면 폴더생성
			buFile = new File(Config.BACKUPFILES_PATH);// 현재 작업하고 있는 프로젝트 경로안의 BackupFiles폴더
		}

		File[] buFiles = buFile.listFiles(); // BackupFiles 안에 있는 파일들을 배열에 넣음
		if(buFiles.length!=0){
			for(File f : buFiles){ // BackupFiles 안에 있는 파일들을 하나씩 검사
				f.delete(); // 파일을 지움
			}
		}

		for(int i=0 ; i<Config.CREATE_SQL_TABLE.length ; i++){
			BackupTableData(i); // BackupFiles에 있는 파일안의 데이터를 가져와 DB테이블에 삽입
		}
		JOptionPane.showMessageDialog(null, "백업 완료");	
	}

	private void loadTableData(int tables){// 파일 복원
		File file = new File(Config.DATAFILES_PATH+Config.FILE_NAME[tables]);
		String sql = "load data local infile '%s' "
				+"into table "+Config.TABLE_NAME[tables]+" "
				+"character set 'UTF8' "
				+"fields terminated by ',' "
				+"lines terminated by '\r\n'";

		executeImportData(String.format(sql,file.getAbsolutePath().replace("\\", "/")), file.getName());
	}

	public void BackupTableData(int tables){// 파일 백업
		String sql = "select * from "+Config.TABLE_NAME[tables];
		Connection con = DBCon.getConnection(Config.URL+Config.DB_NAME,Config.ROOT_USER,Config.PWD );
		try(PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery();){
			StringBuilder sb = new StringBuilder();
			int colCnt = rs.getMetaData().getColumnCount();
			while(rs.next()){
				for(int i=1 ; i<=colCnt ; i++){
					Object obj = rs.getObject(i);
					if(obj.equals(true)){
						obj=1;
					}else if(obj.equals(false)){
						obj=0;
					}
					sb.append(obj+",");
				}
				sb.replace(sb.length()-1, sb.length(), "");
				sb.append("\r\n");
			}
			System.out.println(sb.toString());

			try(BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(Config.BACKUPFILES_PATH+Config.FILE_NAME[tables]));
					OutputStreamWriter osw = new OutputStreamWriter(bw, "UTF-8")){
				osw.write(sb.toString());

			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void executeImportData(String sql, String tableName) {
		Statement stmt = null;
		try {
			Connection con = DBCon.getConnection(Config.URL+Config.DB_NAME,Config.ROOT_USER,Config.PWD);
			stmt = con.createStatement();
			stmt.execute(sql);
			System.out.printf("Import Table(%s) %d Rows Success! %n",tableName, stmt.getUpdateCount());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
		}
	}
}