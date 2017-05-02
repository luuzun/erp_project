package erp_project.init;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisSqlSessionFactory {
	private static SqlSessionFactory sqlSessionFactory;

	public static SqlSessionFactory getSqlSessionFactory() {
		if (sqlSessionFactory==null){
			InputStream inputStream;
			try {
				System.out.println("SqlSessionFactory Created!");
				inputStream = Resources.getResourceAsStream("mybatis_config.xml");
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sqlSessionFactory;
	}
	
	public static SqlSession openSession(){
		return getSqlSessionFactory().openSession();
	}
}
