package nal.vn.demorestapispringboot.repositories;

import nal.vn.demorestapispringboot.models.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkRepositories extends JpaRepository<Work, Long> {
    List<Work> findByWorkName(String workName);
}
