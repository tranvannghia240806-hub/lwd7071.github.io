package vn.iotstar.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	@Column(nullable = false, length = 500, columnDefinition = "NVARCHAR(500)")
	private String productName;

	@Column(nullable = false)
	private Integer quantity;

	@Column(nullable = false)
	private Double unitPrice;

	@Column(length = 300)
	private String images;

	@Column(columnDefinition = "NVARCHAR(1000)")
	private String description;

	@Column(nullable = false)
	private Double discount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Column(nullable = false)
	private Short status;

	// Chi bo qua field "products" cua Category khi serialize (tranh vong lap),
	// van tra ve categoryId + categoryName de AJAX hien thi ten loai san pham
	@JsonIgnoreProperties({ "products" })
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
}
