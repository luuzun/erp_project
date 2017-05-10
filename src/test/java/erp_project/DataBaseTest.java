package erp_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import setting.Config;
import setting.DBCon;
import setting.InitSettingService;

public class DataBaseTest {
   private static InitSettingService init;

   @BeforeClass
   public static void setUpBeforeClass() throws Exception {
      init = InitSettingService.getInstance();
   }

   @AfterClass
   public static void tearDownAfterClass() throws Exception {
      init = null;
   }
   
   @Test
   public void aTestDBConnection() {
      Connection connection = DBCon.getConnection(Config.URL, Config.USER, Config.PWD);
      Assert.assertNotEquals(1, connection);
   }
   
   @Test
   public void bTestDBExists(){
      Connection connection = DBCon.getConnection(Config.URL, Config.USER, Config.PWD);
      PreparedStatement pStmt = null;
      ResultSet rs = null;
      int res = 0;
      try{
         pStmt = connection.prepareStatement("select 1 from information_schema.SCHEMATA where SCHEMA_NAME = 'ncs_erp'");
         rs = pStmt.executeQuery();
         if(rs.next()){
            res=rs.getInt(1);
         }
      }catch(Exception e){
         e.printStackTrace();
      }finally{
         try{
            rs.close();
            pStmt.close();
         }catch(SQLException e){
            e.printStackTrace();
         }
      }
      Assert.assertEquals(1, res);
   }
   
   @Test
   public void cTestEmployeeTableExists(){
      Connection connection = DBCon.getConnection(Config.URL, Config.USER, Config.PWD);
      PreparedStatement pStmt = null;
      ResultSet rs = null;
      String res = null;
      try{
         pStmt = connection.prepareStatement(Config.IS_EXIST_TABLE[0]);
         rs = pStmt.executeQuery();
         if(rs.next()){
            res=rs.getString("isExists");
         }
      }catch(Exception e){
         e.printStackTrace();
      }finally{
         try{
            rs.close();
            pStmt.close();
         }catch(SQLException e){
            e.printStackTrace();
         }
      }
      Assert.assertEquals("1", res);
   }
   
   @Test
   public void dTestDepartmentTableExists(){
      Connection connection = DBCon.getConnection(Config.URL, Config.USER, Config.PWD);
      PreparedStatement pStmt = null;
      ResultSet rs = null;
      String res = null;
      try{
         pStmt = connection.prepareStatement(Config.IS_EXIST_TABLE[1]);
         rs = pStmt.executeQuery();
         if(rs.next()){
            res=rs.getString("isExists");
         }
      }catch(Exception e){
         e.printStackTrace();
      }finally{
         try{
            rs.close();
            pStmt.close();
         }catch(SQLException e){
            e.printStackTrace();
         }
      }
      Assert.assertEquals("1", res);
   }
   
   @Test
   public void eTestTitleTableExists(){
      Connection connection = DBCon.getConnection(Config.URL, Config.USER, Config.PWD);
      PreparedStatement pStmt = null;
      ResultSet rs = null;
      String res = null;
      try{
         pStmt = connection.prepareStatement(Config.IS_EXIST_TABLE[2]);
         rs = pStmt.executeQuery();
         if(rs.next()){
            res=rs.getString("isExists");
         }
      }catch(Exception e){
         e.printStackTrace();
      }finally{
         try{
            rs.close();
            pStmt.close();
         }catch(SQLException e){
            e.printStackTrace();
         }
      }
      Assert.assertEquals("1", res);
   }
}