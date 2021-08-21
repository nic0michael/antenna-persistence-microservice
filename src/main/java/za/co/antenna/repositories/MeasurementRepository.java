package za.co.antenna.repositories;

import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import za.co.antenna.dtos.MeasurementRecordPojo;
import za.co.antenna.utils.Utils;

@Repository
public class MeasurementRepository {
	private static final Logger log = LoggerFactory.getLogger(MeasurementRepository.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public final static String SQL_QUERY="";

	public List<MeasurementRecordPojo> getMeasurementRecords(String antennaCodes,String measurementCode) {
		String sqlQuery = makeSqlQuery(antennaCodes, measurementCode);
		List<MeasurementRecordPojo> result = jdbcTemplate.query(sqlQuery, new MeasurementRecordDataRowMapper());
		return result;
	}

	private String makeSqlQuery(String antennaCode, String measurementCode) {
		StringBuilder antennaCodes = new StringBuilder();
		String[] theAntennaCodes = null;

		if (antennaCode.contains(",")) {
			theAntennaCodes = antennaCode.split(",");
			int count = 0;
 
			for (String theAntennaCode : theAntennaCodes) {
				if(count > 0) {
					antennaCodes.append(",");
				}

				antennaCodes.append("'");
				antennaCodes.append(theAntennaCode);
				antennaCodes.append("'");
				count++;
			}
		}
		else {
			antennaCodes=new StringBuilder();
			antennaCodes.append("'");
			antennaCodes.append(antennaCode);
			antennaCodes.append("'");
		}

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT	d.data_id,");
		sql.append(" d.date_created,");
		sql.append(" d.measurement_code,");
		sql.append(" m.name as measurement_name,");
		sql.append(" a.antenna_type_code,");
		sql.append(" a.description as antenna_desription,");
		sql.append(" a.antenna_code,");
		sql.append(" a.name as antenna_name,");
		sql.append(" d.determinant,");
		sql.append(" d.value");
		sql.append(" FROM data d");
		sql.append(" INNER JOIN antenna a,");
		sql.append(" measurement m");
		sql.append(" WHERE d.antenna_code = a.antenna_code");
		sql.append(" AND");
		sql.append(" d.measurement_code = m.measurement_code");		
		sql.append(" AND a.antenna_code IN (");
		sql.append(antennaCodes);
		sql.append(")");
		sql.append(" AND m.measurement_code = '");
		sql.append(measurementCode);
		sql.append("' ORDER BY d.antenna_code,");
		sql.append("d.determinant;");
		
		log.info("ANTENNA : AntennaMeasurementRepository : makeSqlQuery : SQL:\n" + sql);

		return sql.toString();
	}

	class MeasurementRecordDataRowMapper implements RowMapper<MeasurementRecordPojo> {

		@Override
		public MeasurementRecordPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
			MeasurementRecordPojo measurementRecordPojo = new MeasurementRecordPojo();

			measurementRecordPojo.setDataId((new Integer(rs.getInt("data_id")).toString()));

			if (rs.getTimestamp("date_created") != null) {
				SimpleDateFormat sdf = new SimpleDateFormat(Utils.getDateTimeFormatRsa());
				measurementRecordPojo.setDateCreated(sdf.format(rs.getTimestamp("date_created")));
			}

			if (rs.getString("measurement_code") != null) {
				measurementRecordPojo.setMeasurementCode(rs.getString("measurement_code"));
			}

			if (rs.getString("measurement_name") != null) {
				measurementRecordPojo.setMeasurementName(rs.getString("measurement_name"));
			}

			if (rs.getString("antenna_type_code") != null) {
				measurementRecordPojo.setAntennaTypeCode(rs.getString("antenna_type_code"));
			}

			if (rs.getString("antenna_desription") != null) {
				measurementRecordPojo.setAntennaDesription(rs.getString("antenna_desription"));
			}

			if (rs.getString("antenna_code") != null) {
				measurementRecordPojo.setAntennaCode(rs.getString("antenna_code"));
			}

			if (rs.getString("antenna_name") != null) {
				measurementRecordPojo.setAntennaName(rs.getString("antenna_name"));
			}

			DecimalFormat df = new DecimalFormat("#.##");
			
			measurementRecordPojo.setDeterminant(df.format(rs.getDouble("determinant")));

			measurementRecordPojo.setValue(df.format(rs.getDouble("value")));
			
			return measurementRecordPojo;
		}
	}
}
