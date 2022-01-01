package nal.vn.demorestapispringboot.repositories;

import nal.vn.demorestapispringboot.models.Work;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WorkRepositoriesSortPaging extends PagingAndSortingRepository<Work, Long> {
}
