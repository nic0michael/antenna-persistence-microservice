package za.co.antenna.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.stereotype.Repository;

import za.co.antenna.dtos.FileImageMeasurementPersistRequest;
import za.co.antenna.dtos.FileImageMeasurementRecordPojo;

@Repository
public class FileImageMeasurementRepository {
	private static final Logger log = LoggerFactory.getLogger(FileImageMeasurementRepository.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<FileImageMeasurementRecordPojo> getFileImageMeasurementRecordsByAntennaCode(String antennaCode, String orderBy) {
		String sqlQuery = makeGetFileImageMeasurementRecordsByAntennaCodeQuery(antennaCode, orderBy);
		List<FileImageMeasurementRecordPojo> result = jdbcTemplate.query(sqlQuery, new FileImageMeasurementRecordDataRowMapper());
		return result;
	}

	public List<FileImageMeasurementRecordPojo> getAllFileImageMeasurementRecords(String orderBy) {
		String sqlQuery = makeGetAllFileImageMeasurementRecordsQuery(orderBy);
		List<FileImageMeasurementRecordPojo> result = jdbcTemplate.query(sqlQuery, new FileImageMeasurementRecordDataRowMapper());
		return result;
	}

	public int deleteFileImageMeasurementRecordsByAntennaCode(String antennaCode) {
		String sqlQuery = makeDeleteFileImageMeasurementRecordsByAntennaCodeQuery(antennaCode);
		int numberOfRecordsDeleted = jdbcTemplate.update(sqlQuery);
		return numberOfRecordsDeleted;
	}

	public int insertFileImageMeasurementRecord(FileImageMeasurementPersistRequest fileImageMeasurementPersistRequest) {
		String sqlQuery = makeInsertFileImageMeasurementRecordQuery(fileImageMeasurementPersistRequest);
		int numberOfRecordsInserted = jdbcTemplate.update(sqlQuery);
		return numberOfRecordsInserted;
	}

	public int deleteDataRecordsByAntennaCode(String antennaCode) {
		String sqlQuery = makeDeleteDataRecordsByAntennaCodeQuery(antennaCode);
		int numberOfRecordsDeleted = jdbcTemplate.update(sqlQuery);
		return numberOfRecordsDeleted;
	}

	public int publishFileImageMeasurementRecords(String antennaCode) {
		String sqlQuery = makePublishFileImageMeasurementRecordsQuery(antennaCode);
		int numberOfRecordsPublished = jdbcTemplate.update(sqlQuery);
		return numberOfRecordsPublished;
	}

	public int setFileImageRecordStatusByAntennaCode(String antennaCode, String status) {
		String sqlQuery = makeSetFileImageRecordStatusByAntennaCodeQuery(antennaCode, status);
		int numberOfRecordsPublished = jdbcTemplate.update(sqlQuery);
		return numberOfRecordsPublished;
	}

	private String makeGetFileImageMeasurementRecordsByAntennaCodeQuery(String antennaCode, String orderBy) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT	* ");
		sql.append("FROM file_image_measurement ");
		sql.append("WHERE antenna_code = '");
		sql.append(antennaCode);
		sql.append("' ORDER BY ");
		sql.append(orderBy);
		sql.append(";");

		log.info("ANTENNA : FileImageMeasurementRepository : makeGetFileImageMeasurementRecordsByAntennaCodeQuery : SQL:\n" + sql);

		return sql.toString();
	}

	private String makeGetAllFileImageMeasurementRecordsQuery(String orderBy) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT	* ");
		sql.append("FROM file_image_measurement ");
		sql.append("ORDER BY ");
		sql.append(orderBy);
		sql.append(";");

		log.info("ANTENNA : FileImageMeasurementRepository : makeGetAllFileImageMeasurementRecordsQuery : SQL:\n" + sql);

		return sql.toString();
	}

	private String makeDeleteFileImageMeasurementRecordsByAntennaCodeQuery(String antennaCode) {
		StringBuilder sql = new StringBuilder();

		sql.append("DELETE FROM file_image_measurement WHERE antenna_code = '");
		sql.append(antennaCode);
		sql.append("';");

		log.info("ANTENNA : FileImageMeasurementRepository : makeDeleteFileImageMeasurementRecordsByAntennaCodeQuery : SQL:\n" + sql);

		return sql.toString();
	}

	private String makeInsertFileImageMeasurementRecordQuery(FileImageMeasurementPersistRequest fileImageMeasurementPersistRequest) {
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO file_image_measurement ");
		sql.append("(antenna_code, determinant, value) ");
		sql.append("VALUES ('");
		sql.append(fileImageMeasurementPersistRequest.getAntennaCode());
		sql.append("', '");
		sql.append(fileImageMeasurementPersistRequest.getDeterminant());
		sql.append("', '");
		sql.append(fileImageMeasurementPersistRequest.getValue());
		sql.append("');");

		log.info("ANTENNA : FileImageMeasurementRepository : makeInsertFileImageMeasurementRecordQuery : SQL:\n" + sql);

		return sql.toString();
	}

	private String makeDeleteDataRecordsByAntennaCodeQuery(String antennaCode) {
		StringBuilder sql = new StringBuilder();

		sql.append("DELETE FROM data WHERE antenna_code = '");
		sql.append(antennaCode);
		sql.append("';");

		log.info("ANTENNA : FileImageMeasurementRepository : makeDeleteDataRecordsByAntennaCodeQuery : SQL:\n" + sql);

		return sql.toString();
	}

	private String makePublishFileImageMeasurementRecordsQuery(String antennaCode) {
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO data (date_created, determinant, value, antenna_code, measurement_code) ");
		sql.append("SELECT NOW(), fim.determinant, fim.value, fim.antenna_code, fi.measurement_code ");
		sql.append("FROM file_image fi, file_image_measurement fim ");
		sql.append("WHERE fi.antenna_code = fim.antenna_code AND fim.antenna_code = '");
		sql.append(antennaCode);
		sql.append("';");

		log.info("ANTENNA : FileImageMeasurementRepository : makePublishFileImageMeasurementRecordsQuery : SQL:\n" + sql);

		return sql.toString();
	}

	private String makeSetFileImageRecordStatusByAntennaCodeQuery(String antennaCode, String status) {
		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE file_image SET status = '");
		sql.append(status);
		sql.append("' WHERE antenna_code = '");
		sql.append(antennaCode);
		sql.append("';");

		log.info("ANTENNA : FileImageMeasurementRepository : makePublishFileImageMeasurementRecordsQuery : SQL:\n" + sql);

		return sql.toString();
	}

	class FileImageMeasurementRecordDataRowMapper implements RowMapper<FileImageMeasurementRecordPojo> {

		@Override
		public FileImageMeasurementRecordPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
			FileImageMeasurementRecordPojo fileImageMeasurementRecordPojo = new FileImageMeasurementRecordPojo();

			fileImageMeasurementRecordPojo.setFileMeasurementId((new Integer(rs.getInt("file_image_measurement_id")).toString()));

			if (rs.getString("antenna_code") != null) {
				fileImageMeasurementRecordPojo.setAntennaCode(rs.getString("antenna_code"));
			}

			DecimalFormat df = new DecimalFormat("#.##");

			fileImageMeasurementRecordPojo.setDeterminant(df.format(rs.getDouble("determinant")));

			fileImageMeasurementRecordPojo.setValue(df.format(rs.getDouble("value")));

			return fileImageMeasurementRecordPojo;
		}
	}
}
