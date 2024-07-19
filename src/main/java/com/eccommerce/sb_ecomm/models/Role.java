package com.eccommerce.sb_ecomm.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="roles")
public class Role {

        @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="role_id")
        private Integer id;
        @ToString.Exclude
        //persist AppRole as string not integer
        @Enumerated(EnumType.STRING)
        @Column(length = 20 ,name="role_name")
        private AppRole roleName;
        public Role(AppRole roleName){
            this.roleName=roleName;
        }

}
