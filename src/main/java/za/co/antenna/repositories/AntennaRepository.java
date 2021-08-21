package za.co.antenna.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * Development status: Locked by John - 05/01/2021
 */

import org.springframework.stereotype.Repository;

import za.co.antenna.dtos.AntennaPersistRequest;
import za.co.antenna.dtos.AntennaRecordPojo;
import za.co.antenna.utils.Utils;

@Repository
public class AntennaRepository {
	private static final Logger log = LoggerFactory.getLogger(AntennaRepository.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<AntennaRecordPojo> getAntennaRecord(String antennaCode) {
		log.info("ANTENNA : AntennaRepository : getAntennaRecord : antennaCode: " + antennaCode);
		String sqlQuery = makeGetAntennaRecordQuery(antennaCode);
		log.info("ANTENNA : AntennaRepository : getAntennaRecord : sqlQuery: " + sqlQuery);
		List<AntennaRecordPojo> result = jdbcTemplate.query(sqlQuery, new AntennaRecordDataRowMapper());
		return result;
	}

	public List<AntennaRecordPojo> getAllAntennaRecords(String orderBy) {
		String sqlQuery = makeGetAllAntennaRecordsQuery(orderBy);
		log.info("ANTENNA : AntennaRepository : getAllAntennaRecords : sqlQuery: " + sqlQuery);
		List<AntennaRecordPojo> result = jdbcTemplate.query(sqlQuery, new AntennaRecordDataRowMapper());
		return result;
	}

	public List<AntennaRecordPojo> getAllAntennaRecordsByType(String antennaType, String orderBy) {
		String sqlQuery = makeGetAllAntennaRecordsByTypeQuery(antennaType, orderBy);
		log.info("ANTENNA : AntennaRepository : getAllAntennaRecordsByType : sqlQuery: " + sqlQuery);
		List<AntennaRecordPojo> result = jdbcTemplate.query(sqlQuery, new AntennaRecordDataRowMapper());
		log.info("ANTENNA : AntennaRepository : getAllAntennaRecordsByType : AntennaRecordPojo: " + result);
		return result;
	}

	public List<AntennaRecordPojo> getAllBaseAntennaRecordsByAntennaCode(String baseAntennaCode, String orderBy) {
		String sqlQuery = makeGetAllBaseAntennaRecordsByAntennaCodeQuery(baseAntennaCode, orderBy);
		log.info("ANTENNA : AntennaRepository : getAllAntennaRecordsByBaseAntennaCode : sqlQuery: " + sqlQuery);
		List<AntennaRecordPojo> result = jdbcTemplate.query(sqlQuery, new AntennaRecordDataRowMapper());
		log.info("ANTENNA : AntennaRepository : getAllBaseAntennaRecordsByAntennaCode : AntennaRecordPojo: " + result);
		return result;
	}
	


	public List<AntennaRecordPojo> getAllSubAntennaRecordsByAntennaCode(String baseAntennaCode, String orderBy) {
		String sqlQuery = makeGetAllSubAntennaRecordsByAntennaCodeQuery(baseAntennaCode, orderBy);
		log.info("ANTENNA : AntennaRepository : getAllAntennaRecordsByBaseAntennaCode : sqlQuery: " + sqlQuery);
		List<AntennaRecordPojo> result = jdbcTemplate.query(sqlQuery, new AntennaRecordDataRowMapper());
		log.info("ANTENNA : AntennaRepository : getAllBaseAntennaRecordsByAntennaCode : AntennaRecordPojo: " + result);
		return result;
	}

	public int deleteAntennaRecordByAntennaCode(String antennaCode) {
		String sqlQuery = makeDeleteAntennaRecordByAntennaCodeQuery(antennaCode);
		log.info("ANTENNA : AntennaRepository : deleteAntennaRecordByAntennaCode : sqlQuery: " + sqlQuery);
		int numberOfRecordsDeleted = jdbcTemplate.update(sqlQuery);
		return numberOfRecordsDeleted;
	}

	public int insertAntennaRecord(AntennaPersistRequest antennaPersistRequest) {
		String sqlQuery = makeInsertAntennaRecordQuery(antennaPersistRequest);
		log.info("ANTENNA : AntennaRepository : insertAntennaRecord : sqlQuery: " + sqlQuery);
		int numberOfRecordsInserted = jdbcTemplate.update(sqlQuery);
		return numberOfRecordsInserted;
	}

	public int updateAntennaRecord(AntennaPersistRequest antennaPersistRequest) {
		String sqlQuery = makeUpdateAntennaRecordQuery(antennaPersistRequest);
		log.info("ANTENNA : AntennaRepository : updateAntennaRecord : sqlQuery: " + sqlQuery);
		int numberOfRecordsUpdated = jdbcTemplate.update(sqlQuery);
		return numberOfRecordsUpdated;
	}

	private String makeGetAntennaRecordQuery(String antennaCode) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT	* ");
		sql.append("FROM antenna ");
		sql.append("WHERE antenna_code = '");
		sql.append(antennaCode);
		sql.append("' ;");

		log.info("ANTENNA : AntennaRepository : makeGetAntennaRecordQuery : SQL:\n" + sql);

		return sql.toString();
	}

	private String makeGetAllAntennaRecordsQuery(String orderBy) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT	* ");
		sql.append("FROM antenna ");
		sql.append("ORDER BY antenna_code;");

		log.info("ANTENNA : AntennaRepository : makeGetAllAntennaRecordsQuery : SQL:\n" + sql);

		return sql.toString();
	}

	private String makeGetAllAntennaRecordsByTypeQuery(String antennaType, String orderBy) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT	* ");
		sql.append("FROM antenna ");
		sql.append("WHERE antenna_type_code = '");
		sql.append(antennaType);
		sql.append("' ORDER BY antenna_code;");

		log.info("ANTENNA : AntennaRepository : makeGetAllAntennaRecordsByTypeQuery : SQL:\n" + sql);

		return sql.toString();
	}

	private String makeGetAllBaseAntennaRecordsByAntennaCodeQuery(String baseAntennaCode, String orderBy) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT	* ");
		sql.append("FROM antenna ");
		sql.append("WHERE antenna_type_code = 'B' ");
		sql.append("ORDER BY antenna_code;");

		log.info("ANTENNA : AntennaRepository : makeGetAllAntennaRecordsByBaseAntennaCodeQuery : SQL:\n" + sql);

		return sql.toString();
	}
	


	private String makeGetAllSubAntennaRecordsByAntennaCodeQuery(String baseAntennaCode, String orderBy) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT	* ");
		sql.append("FROM antenna ");
		sql.append("WHERE antenna_type_code = 'S' ");
		sql.append("ORDER BY antenna_code;");

		log.info("ANTENNA : AntennaRepository : makeGetAllAntennaRecordsByBaseAntennaCodeQuery : SQL:\n" + sql);

		return sql.toString();
	}


	private String makeDeleteAntennaRecordByAntennaCodeQuery(String antennaCode) {
		StringBuilder sql = new StringBuilder();

		sql.append("DELETE FROM antenna WHERE antenna_code = '");
		sql.append(antennaCode);
		sql.append("';");

		log.info("ANTENNA : AntennaRepository : makeDeleteAntennaRecordByAntennaCode : SQL:\n" + sql);

		return sql.toString();
	}

	private String makeInsertAntennaRecordQuery(AntennaPersistRequest antennaPersistRequest) {
		log.info("ANTENNA : AntennaRepository : makeInsertAntennaRecord : antennaPersistRequest : "+antennaPersistRequest);
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO antenna ");
		sql.append("(antenna_code, antenna_type_code, date_created, description, main_antenna_code, name)");
		sql.append("VALUES ('");
		sql.append(antennaPersistRequest.getAntennaCode());
		sql.append("', '");
		sql.append(antennaPersistRequest.getAntennaTypeCode());
		sql.append("', '");
		sql.append(antennaPersistRequest.getDateCreated());
		sql.append("', '");
		sql.append(antennaPersistRequest.getDescription());
		sql.append("', '");
		sql.append(antennaPersistRequest.getMainAntennaCode());
		sql.append("', '");
		sql.append(antennaPersistRequest.getName());
		sql.append("');");

		log.info("ANTENNA : AntennaRepository : makeInsertAntennaRecord : SQL:\n" + sql);

		return sql.toString();
	}

	private String makeUpdateAntennaRecordQuery(AntennaPersistRequest antennaPersistRequest) {
		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE antenna ");
		sql.append("SET antenna_type_code = '");
		sql.append(antennaPersistRequest.getAntennaTypeCode());
		sql.append("', antenna_code = '");
		sql.append(antennaPersistRequest.getAntennaCode());		
		sql.append("', description= '");
		sql.append(antennaPersistRequest.getDescription());
		sql.append("', main_antenna_code= '");
		sql.append(antennaPersistRequest.getMainAntennaCode());
		sql.append("', name= '");
		sql.append(antennaPersistRequest.getName());
		sql.append("' WHERE antenna_code= '");
		sql.append(antennaPersistRequest.getAntennaCode());
		sql.append("' ;");

		log.info("ANTENNA : AntennaRepository : makeUpdateAntennaRecord : SQL:\n" + sql);

		return sql.toString();
	}

	class AntennaRecordDataRowMapper implements RowMapper<AntennaRecordPojo> {

		@Override
		public AntennaRecordPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
			AntennaRecordPojo antennaRecordPojo = new AntennaRecordPojo();

			antennaRecordPojo.setAntennaId((new Integer(rs.getInt("antenna_id")).toString()));

			if (rs.getTimestamp("date_created") != null) {
			   SimpleDateFormat sdf = new SimpleDateFormat(Utils.getDateTimeFormatRsa());
			   antennaRecordPojo.setDateCreated(sdf.format(rs.getTimestamp("date_created")));
			}

			if (rs.getString("antenna_type_code") != null) {
				antennaRecordPojo.setAntennaTypeCode(rs.getString("antenna_type_code"));
			}

			if (rs.getString("description") != null) {
				antennaRecordPojo.setAntennaDesription(rs.getString("description"));
			}

			if (rs.getString("antenna_code") != null) {
				antennaRecordPojo.setAntennaCode(rs.getString("antenna_code"));
			}

			if (rs.getString("main_antenna_code") != null) {
				antennaRecordPojo.setMainAntennaCode(rs.getString("main_antenna_code"));
			}

			if (rs.getString("name") != null) {
				antennaRecordPojo.setAntennaName(rs.getString("name"));
			}
			
			return antennaRecordPojo;
		}
	}
}
