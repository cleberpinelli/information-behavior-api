package br.uniriotec.sal.ib.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "T_RESULT")
@Audited
@Data
@SuperBuilder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Result extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_RESULT")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_QUERY", nullable = false, foreignKey = @ForeignKey(name = "RESULT_QUERY_FK"))
    private Query query;

    @Column(name="DS_TITLE", nullable=false)
    private String title;

    @Column(name="DS_SNIPPET", nullable=false)
    private String snippet;

    @Column(name="DS_LINK", nullable=false)
    private String link;
}