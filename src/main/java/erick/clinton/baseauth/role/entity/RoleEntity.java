package erick.clinton.baseauth.role.entity;

import jakarta.persistence.*;

@Entity
@Table(name="role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public RoleEntity() {}

    public RoleEntity(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum ValuesEnum{
        BASIC(1L,"BASIC"),
        ADMIN(2L,"ADMIN");

        private final long id;
        private final String name;

        ValuesEnum(long id, String name){
            this.id = id;
            this.name = name;
        }

        public RoleEntity get(){
            return new RoleEntity(id,name);
        }

        public long getId() {
            return id;
        }
    }
}
