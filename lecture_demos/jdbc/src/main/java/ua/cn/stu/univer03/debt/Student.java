package ua.cn.stu.univer03.debt;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("students")
public record Student(@Id UUID id, String firstName, String lastName, String groupName) {
}
