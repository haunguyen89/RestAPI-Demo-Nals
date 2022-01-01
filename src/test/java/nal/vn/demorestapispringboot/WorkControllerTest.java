package nal.vn.demorestapispringboot;

import nal.vn.demorestapispringboot.models.Work;
import nal.vn.demorestapispringboot.repositories.WorkRepositories;
import nal.vn.demorestapispringboot.service.WorkService;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WorkControllerTest extends AbstractTest {
    private static final Long idTest = 9999L;
    @Autowired
    WorkRepositories repositories;

    @MockBean
    private WorkService service;

    @Before("")
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Order(1)
    public void getWorksList() throws Exception {
        super.setUp();
        String uri = "/api/v1/works/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @Order(2)
    public void insertWork() throws Exception {
        super.setUp();
        List<Work> listBefore = repositories.findAll();
        String uri = "/api/v1/works/insert";
        Work work = new Work();
        work.setWorkName("work name test");
        work.setStartingDate("20231223");
        work.setEndingDate("20231231");
        work.setStatus("2");
        String inputJson = super.mapToJson(work);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        List<Work> listApter = repositories.findAll();
        assertTrue(listApter.size() > listBefore.size());
    }

    @Test
    @Order(3)
    public void getWorksDetail() throws Exception {
        super.setUp();
        Long id = 0L;
        List<Work> list = repositories.findAll();
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).getWorkName().equals("work name test")) {
                id = list.get(i).getId();
                break;
            }
        }
        if (!id.equals(0)) {
            String uri = "/api/v1/works/" + id;
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                    .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
            int status = mvcResult.getResponse().getStatus();
            assertEquals(200, status);
        } else {
            assertTrue(false);
        }
    }

    @Test
    @Order(4)
    public void updateWork() throws Exception {
        super.setUp();
        Long id = 0L;
        List<Work> list = repositories.findAll();
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).getWorkName().equals("work name test")) {
                id = list.get(i).getId();
                break;
            }
        }
        String uri = "/api/v1/works/" + id;
        Work work = new Work();
        work.setWorkName("work name test new");
        String inputJson = super.mapToJson(work);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @Order(5)
    public void deleteWork() throws Exception {
        super.setUp();
        Long id = 0L;
        List<Work> list = repositories.findAll();
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).getWorkName().equals("work name test new")) {
                id = list.get(i).getId();
                break;
            }
        }
        String uri = "/api/v1/works/" + id;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

}
