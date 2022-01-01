package nal.vn.demorestapispringboot.database;

import nal.vn.demorestapispringboot.models.Work;
import nal.vn.demorestapispringboot.repositories.WorkRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Autowired
    WorkRepositories repositories;

    @Bean
    CommandLineRunner initDatabase(WorkRepositories WorkRepositories) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                //Create data test
                List<Work> list = repositories.findAll();
                if (list.size() < 90) {
                    int j = 1;
                    int k = 1;
                    for (int i = 0; i < 100; i++) {
                        if (i % 30 == 0) {
                            j++;
                            k = 1;
                        }
                        Work WorkA;
                        if (k < 10) {
                            WorkA = new Work("Work " + i, "20220" + j + "0" + k, "20220" + (j + 3) + "0" + k, "" + i % 3);
                        } else {
                            WorkA = new Work("Work " + i, "20220" + j + k, "20220" + (j + 3) + "0" + k, "" + i % 3);
                        }
                        logger.info("insert data:" + WorkRepositories.save(WorkA));
                        k++;
                    }
                }
            }
        };
    }
}
