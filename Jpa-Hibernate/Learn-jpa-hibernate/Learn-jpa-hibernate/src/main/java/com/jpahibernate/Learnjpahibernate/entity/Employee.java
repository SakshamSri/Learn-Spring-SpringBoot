package com.jpahibernate.Learnjpahibernate.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
// @Entity (not an Entity when using MappedSuperClass)
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @Inheritance(strategy = InheritanceType.JOINED)
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// @DiscriminatorColumn(name = "EmployeeType") (for SINGLE_TABLE discriminator column)
public abstract class Employee {

	@Id
	@GeneratedValue
	private Long id;

	// @Column(name = "FullName", nullable = false)
	private String name;

	protected Employee() {
	}

	public Employee(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "\nEmployee [id=" + id + ", name=" + name + "]";
	}

}
