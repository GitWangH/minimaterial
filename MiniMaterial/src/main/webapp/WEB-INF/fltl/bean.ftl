package ${packageName};

import javax.persistence.*;
import javax.persistence.GenerationType.IDENTITY;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import ${extendPackageName};

 /**
  * 代码自动生成model类.
  * @ClassName: ${annotation.className}
  * @FullClassPath :${annotation.fullClassPath}
  * @Description: TODO.
  * @author: ${annotation.authorName}
  * @Email : ${annotation.authorMail}
  * @date: ${annotation.date}
  * @version: ${annotation.version}
  */

@Entity
@Table(name = "${bean.tableName}")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ${bean.className} extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	<#list attrs as a> 
	<#if a.isKey == 0>
	@Id
	@GeneratedValue(strategy = IDENTITY)
    @Column(name= "${a.dateBaseField}", nullable = ${a.nullable} )
 	private ${a.type} ${a.field};
 
	<#else>
	<#if a.type == "String" >
	/** @Fields ${a.field} : ${a.comment}*/ 
	@Column(name= "${a.dateBaseField}", nullable = ${a.nullable}, length=${a.length} )
    private ${a.type} ${a.field};
    
    <#elseif a.type == "float" ||  a.type == "double" ||  a.type == "BigDecimal">
     /** @Fields ${a.field} : ${a.comment}*/ 
    @Column(name= "${a.dateBaseField}", nullable = ${a.nullable} , precision=${a.precision} , scale=${a.scale})
    private ${a.type} ${a.field};
    
	<#elseif a.type == "Integer">
	/** @Fields ${a.field} : ${a.comment} */
	@Column(name= "${a.dateBaseField}", nullable = ${a.nullable})
    private ${a.type} ${a.field};
    
    <#elseif a.type == "Date">
	/** @Fields ${a.field} : ${a.comment} */
	@Column(name= "${a.dateBaseField}", nullable = ${a.nullable})
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private ${a.type} ${a.field};
    
    <#elseif a.type == "Long">
	/** @Fields ${a.field} : ${a.comment} */
	@Column(name= "${a.dateBaseField}", nullable = ${a.nullable})
    private ${a.type} ${a.field};
    
    <#else>
    <#if a.isOneToMany == 0 >
	/** @Fields ${a.field} : ${a.comment} */
	//mappedBy通过维护端的属性关联
	@OneToMany(cascade = { CascadeType.ALL}, fetch = FetchType.LAZY,mappedBy= "${a.mappedByName}")
    private ${a.type} ${a.field};
    
    <#elseif a.isOneToMany == 1 >
    /** @Fields ${a.field} : ${a.comment} */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name = "${a.joinColumn}")
    private ${a.type} ${a.field};
    
    </#if>
    </#if>
    </#if>
    
    </#list>
      
    <#list attrs as a>
    public void set${a.field?cap_first}(${a.type} ${a.field}){
        this.${a.field} = ${a.field};
    }
      
    public ${a.type} get${a.field?cap_first}(){
        return this.${a.field};
    }
      
    </#list>

}
