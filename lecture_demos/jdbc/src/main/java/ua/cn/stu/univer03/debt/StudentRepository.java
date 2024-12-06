package ua.cn.stu.univer03.debt;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.UUID;

public interface StudentRepository extends ListCrudRepository<Student, UUID> {
    List<Student> findByGroupName(String groupName);
}
