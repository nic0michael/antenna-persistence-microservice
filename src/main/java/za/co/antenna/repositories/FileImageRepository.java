package za.co.antenna.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.stereotype.Repository;

import za.co.antenna.dtos.FileImagePersistRequest;
import za.co.antenna.dtos.FileImageRecordPojo;
import za.co.antenna.utils.Utils;

@Repository
public class FileImageRepository {
	private static final Logger log = LoggerFactory.getLogger(FileImageRepository.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<FileImageRecordPojo> getFileImageRecordByAntennaCode(String antennaCode) {
		String sqlQuery = makeGetFileImageRecordQuery(antennaCode);
		List<FileImageRecordPojo> result = jdbcTemplate.query(sqlQuery, new FileImageRecordDataRowMapper());
		return result;
	}

	public List<FileImageRecordPojo> getAllFileImageRecords(String orderBy) {
		String sqlQuery = makeGetAllFileImageRecordsQuery(orderBy);
		List<FileImageRecordPojo> result = jdbcTemplate.query(sqlQuery, new FileImageRecordDataRowMapper());
		return result;
	}

	public List<FileImageRecordPojo> getAllFileImageRecordsByType(String antennaType, String orderBy) {
		String sqlQuery = makeGetAllFileImageRecordsByTypeQuery(antennaType, orderBy);
		List<FileImageRecordPojo> result = jdbcTemplate.query(sqlQuery, new FileImageRecordDataRowMapper());
		return result;
	}

	public List<FileImageRecordPojo> getAllFileImageRecordsByStatus(String status, String orderBy) {
		String sqlQuery = makeGetAllFileImageRecordsByStatusQuery(status, orderBy);
		List<FileImageRecordPojo> result = jdbcTemplate.query(sqlQuery, new FileImageRecordDataRowMapper());
		return result;
	}

	public int deleteFileImageRecordByAntennaCode(String antennaCode) {
		String sqlQuery = makeDeleteFileImageRecordByAntennaCodeQuery(antennaCode);
		int numberOfRecordsDeleted = jdbcTemplate.update(sqlQuery);
		return numberOfRecordsDeleted;
	}

	public int insertFileImageRecord(FileImagePersistRequest fileImagePersistRequest) {
		String sqlQuery = makeInsertFileImageRecordQuery(fileImagePersistRequest);
		int numberOfRecordsInserted = jdbcTemplate.update(sqlQuery);
		return numberOfRecordsInserted;
	}

	private String makeGetFileImageRecordQuery(String antennaCode) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT	* ");
		sql.append("FROM file_image ");
		sql.append("WHERE antenna_code = '");
		sql.append(antennaCode);
		sql.append("';");

		log.info("ANTENNA : FileImageRepository : makeGetFileImageRecordQuery : SQL:\n" + sql);

		return sql.toString();
	}

	private String makeGetAllFileImageRecordsQuery(String orderBy) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT	* ");
		sql.append("FROM file_image ");
		sql.append("ORDER BY ");
		sql.append(orderBy);
		sql.append(";");

		log.info("ANTENNA : FileImageRepository : makeGetAllFileImageRecordsQuery : SQL:\n" + sql);

		return sql.toString();
	}

	private String makeGetAllFileImageRecordsByTypeQuery(String antennaType, String orderBy) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT	* ");
		sql.append("FROM file_image ");
		sql.append("WHERE antenna_type_code = '");
		sql.append(antennaType);
		sql.append("' ORDER BY ");
		sql.append(orderBy);
		sql.append(";");

		log.info("ANTENNA : FileImageRepository : makeGetAllFileImageRecordsByTypeQuery : SQL:\n" + sql);

		return sql.toString();
	}

	private String makeGetAllFileImageRecordsByStatusQuery(String status, String orderBy) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT	* ");
		sql.append("FROM file_image ");
		sql.append("WHERE status = '");
		sql.append(status);
		sql.append("' ORDER BY ");
		sql.append(orderBy);
		sql.append(";");

		log.info("ANTENNA : FileImageRepository : makeGetAllFileImageRecordsByStatusQuery : SQL:\n" + sql);

		return sql.toString();
	}

	private String makeDeleteFileImageRecordByAntennaCodeQuery(String antennaCode) {
		StringBuilder sql = new StringBuilder();

		sql.append("DELETE FROM file_image WHERE antenna_code = '");
		sql.append(antennaCode);
		sql.append("';");

		log.info("ANTENNA : FileImageRepository : makeDeleteFileImageRecordByAntennaCode : SQL:\n" + sql);

		return sql.toString();
	}

	private String makeInsertFileImageRecordQuery(FileImagePersistRequest fileImagePersistRequest) {
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO file_image ");
		sql.append("(antenna_code, antenna_type_code, date_uploaded, file_name, measurement_code, number_of_records, status) ");
		sql.append("VALUES ('");
		sql.append(fileImagePersistRequest.getAntennaCode());
		sql.append("', '");
		sql.append(fileImagePersistRequest.getAntennaTypeCode());
		sql.append("', '");
		sql.append(fileImagePersistRequest.getDateUploaded());
		sql.append("', '");
		sql.append(fileImagePersistRequest.getFileName());
		sql.append("', '");
		sql.append(fileImagePersistRequest.getMeasurementCode());
		sql.append("', ");
		sql.append(fileImagePersistRequest.getNumberOfRecords());
		sql.append(", '");
		sql.append(fileImagePersistRequest.getStatus());
		sql.append("');");

		log.info("ANTENNA : FileImageRepository : makeInsertFileImageRecord : SQL:\n" + sql);

		return sql.toString();
	}

	class FileImageRecordDataRowMapper implements RowMapper<FileImageRecordPojo> {

		@Override
		public FileImageRecordPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
			FileImageRecordPojo fileImageRecordPojo = new FileImageRecordPojo();

			fileImageRecordPojo.setFileId((new Integer(rs.getInt("file_id")).toString()));

			if (rs.getString("antenna_code") != null) {
				fileImageRecordPojo.setAntennaCode(rs.getString("antenna_code"));
			}

			if (rs.getString("antenna_type_code") != null) {
				fileImageRecordPojo.setAntennaTypeCode(rs.getString("antenna_type_code"));
			}

			if (rs.getTimestamp("date_uploaded") != null) {
				SimpleDateFormat sdf = new SimpleDateFormat(Utils.getDateTimeFormatRsa());
				fileImageRecordPojo.setDateUploaded(sdf.format(rs.getTimestamp("date_uploaded")));
			}

			if (rs.getString("file_name") != null) {
				fileImageRecordPojo.setFileName(rs.getString("file_name"));
			}

			if (rs.getString("measurement_code") != null) {
				fileImageRecordPojo.setMeasurementCode(rs.getString("measurement_code"));
			}

			fileImageRecordPojo.setNumberOfRecords((new Integer(rs.getInt("number_of_records")).toString()));

			if (rs.getString("status") != null) {
				fileImageRecordPojo.setStatus(rs.getString("status"));
			}
			
			return fileImageRecordPojo;
		}
	}
}
