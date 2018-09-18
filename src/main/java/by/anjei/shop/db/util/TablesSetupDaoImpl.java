package by.anjei.shop.db.util;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TablesSetupDaoImpl implements TablesSetupDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void createDefaultTables() {
        File file = new File("src/main/resources/sql/default_tables.sql");
        List<String> queries = getQueries(file);
        for (String query : queries) {
            jdbcTemplateObject.execute(query);
        }
    }

    private List<String> getQueries(File file) {
        List<String> queries = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                queries.add(scanner.next() + ";");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return queries;
    }



}
