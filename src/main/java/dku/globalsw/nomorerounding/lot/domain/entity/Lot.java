package dku.globalsw.nomorerounding.lot.domain.entity;

import dku.globalsw.nomorerounding.space.domain.entity.Space;
import dku.globalsw.nomorerounding.space.domain.entity.SpecificSpace;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Builder
@Getter
@Entity
@NoArgsConstructor
@ToString
@DynamicUpdate
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Lot {

    @CreatedDate
    @Column(updatable = false)
    private Date createdDate;

    @LastModifiedDate
    private Date updatedDate;

    @Id
    private String floor;

    private Integer lotRow;

    private Integer lotColumn;

    @OneToMany(mappedBy = "lot")
    private List<SpecificSpace> specificSpace = new ArrayList<>();

    @OneToMany(mappedBy = "lot")
    private List<Space> space = new ArrayList<>();
}
