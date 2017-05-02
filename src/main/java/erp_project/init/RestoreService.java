package erp_project.init;

import java.io.File;

public class RestoreService {
	File file = new File(Config.DATAFILES_PATH);// 현재 작업하고 있는 프로젝트 경로안의 DataFiles폴더
	File[] dataFiles = file.listFiles(); // BackupFiles 안에 있는 파일들을 배열에 넣음


		for(int i=0; i<dataFiles.length; i++){
		file = new File(Config.DATAFILES_PATH+Config.TABLE_NAME[i]);
			String sql = "load data local infile '%s' "
						+"into table "+dataFiles[i].getName()+" "
						+"character set 'UTF8' "
						+"fields terminated by ',' "
						+"lines terminated by '\r\n'";
			
			executeImportData(String.format(sql,file.getAbsolutePath().replace("\\", "/")), file.getName());
		}
	}
}
