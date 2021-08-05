package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * Employeeテーブルを操作するリポジトリ
 * @author kanekojota
 *
 */
@Repository
public class EmployeeRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER
	= new BeanPropertyRowMapper<>(Employee.class);
	
	/**
	 * 全件検索を行う
	 *@return 全従業員一覧
	 */
	public List<Employee> findAll(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count");
		sql.append(" FROM employees ORDER BY hire_date DESC;");
		List<Employee>employeeList = template.query(sql.toString(), EMPLOYEE_ROW_MAPPER);
		return employeeList;
	}
	
	/**
	 * 主キー検索を行う
	 *@param id ID
	 *@return 検索された従業員情報
	 */
	public Employee load(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count");
		sql.append(" FROM employees WHERE id=:id");
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Employee employee = template.queryForObject(sql.toString(), param, EMPLOYEE_ROW_MAPPER);
		return employee;
	}
	
	/**
	 * 渡した従業員情報を更新する
	 * @param employee
	 */
	public void update(Employee employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE employees SET name=:name,image=:image,gender=:gender,hire_date=:hire_date,");
		sql.append("mail_address=:mail_address,zip_code=:zip_code,address=:address,telephone=:telephone,");
		sql.append("salary=:salary,characteristics=:characteristics,dependents_count=:dependents_count");
		sql.append("WHERE id=:id");
		template.update(sql.toString(), param);
	}
	
}
