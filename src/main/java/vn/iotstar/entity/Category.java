package vn.iotstar.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;

	@Column(nullable = false, length = 200, columnDefinition = "NVARCHAR(200)")
	private String categoryName;

	@Column(length = 300)
	private String icon;

	// Khong tra ve danh sach product trong JSON cua Category (tranh vong lap)
	@JsonIgnore
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private Set<Product> products = new HashSet<>();
}
