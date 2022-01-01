package nal.vn.demorestapispringboot.models;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tblWork")
public class Work {
    @Id
    @SequenceGenerator(
            name = "work_sequence",
            sequenceName = "work_sequence",
            allocationSize = 1 //increment by 1
    )
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,
            generator = "work_sequence")
    private Long id;
    @Column(nullable = false, unique = true, length = 200)
    private String workName;
    private String startingDate;
    private String endingDate;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Work() {
    }

    public Work(String workName, String startingDate, String endingDate, String status) {
        this.workName = workName;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Work{" +
                "id=" + id +
                ", workName='" + workName + '\'' +
                ", startingDate='" + startingDate + '\'' +
                ", endingDate='" + endingDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Work work = (Work) o;
        return Objects.equals(workName, work.workName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workName);
    }
}
