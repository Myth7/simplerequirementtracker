package srt.data.repository.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import srt.data.domain.management.Data;
import srt.data.repository.management.SimpleUserRepositoryImpl.UserRowMapper;

@Repository
public class SimpleDataRepositoryImpl implements IDataRepository {

	@Autowired
	private JdbcTemplate jdbc;
	private static final DataRowMapper ROWMAPPER = new DataRowMapper();
	@Override
	public Data getDatasByDataId(Long dataId, String Creator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Data> getDatasByData1(String data1, String Creator) {
		List<Data> datas=jdbc.query(new StringBuilder(UserRowMapper.SELECT_WITH_NO_CRITERIA).append(DataRowMapper.SELECT_CRITERIA_BY_DATA1).append(DataRowMapper.SELECT_CRITERIA_BY_CREATOR).toString(), ROWMAPPER, data1, Creator);
		if(datas!=null && datas.size()>0){
			return datas;
		}
		return null;
	}

	@Override
	public int addData(Data data) {
		int rows = jdbc.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql="INSERT INTO Datas (data1, data2, data3, data4, data5, created_by) VALUES (?,?,?,?,?,?)";
		        PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		        ps.setString(1, data.getData1());
		        ps.setString(2, data.getData2());
		        ps.setString(3, data.getData3());
		        ps.setString(4, data.getData4());
		        ps.setString(5, data.getData5());
		        ps.setString(6, data.getCreatedBy());
		        
		        return ps;
			}
		});
//		Long userId=keyHolder.getKey().longValue();
//		user.setUserId(userId);
		return rows;
	}

	@Override
	public int updateData(Data data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeDataByDataId(Long dataId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static class DataRowMapper implements RowMapper<Data> {

		public static final StringBuilder SELECT_WITH_NO_CRITERIA = new StringBuilder(
				"SELECT dataId, data1, data2, data3, data4, data5, created_by FROM Datas");
		public static final StringBuilder SELECT_CRITERIA_BY_DATAID = new StringBuilder(" WHERE dataId=?");
		public static final StringBuilder SELECT_CRITERIA_BY_DATA1 = new StringBuilder(" WHERE data1=?");
		public static final StringBuilder SELECT_CRITERIA_BY_CREATOR = new StringBuilder(" AND created_by=?");
		
		public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
			Data data = new Data();
			data.setDataId(rs.getLong(1));
			data.setData1(rs.getString(2));
			data.setData2(rs.getString(3));
			data.setData3(rs.getString(4));
			data.setData4(rs.getString(5));
			data.setData5(rs.getString(6));
			data.setCreatedBy(rs.getString(7));
			return data;
		}

	}
}
