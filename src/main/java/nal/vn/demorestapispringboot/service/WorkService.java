package nal.vn.demorestapispringboot.service;

import nal.vn.demorestapispringboot.models.Work;
import nal.vn.demorestapispringboot.repositories.WorkRepositoriesSortPaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkService {
    @Autowired
    WorkRepositoriesSortPaging repository;

    public List<Work> getAllWork(Integer pageNo, Integer pageSize, String sortBy, String desc)
    {
        Pageable paging;
        if (desc.equals("true")) {
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        } else {
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        }

        Page<Work> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Work>();
        }
    }
}
